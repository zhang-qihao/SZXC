
let RouterPlugin = function () {
    this.$router = null;
    this.$store = null;

};
RouterPlugin.install = function (vue, option = {}) {
    this.$router = option.router;
    this.$store = option.store;
    this.$vue = new vue({ i18n: option.i18n });
    function isURL (s) {
        if (s.includes('html')) return true;
        return /^http[s]?:\/\/.*/.test(s)
    }
    function objToform (obj) {
        console.log(obj)
        let result = [];
        Object.keys(obj).forEach(ele => {
            result.push(`${ele}=${obj[ele]}`);
        })
        return result.join('&');
    }
    this.$router.$avueRouter = {
        //全局配置
        $website: this.$store.getters.website,
        group: '',
        meta: {},
        safe: this,
        // 设置标题
        setTitle: (title) => {
            const defaultTitle = this.$vue.$t('title');
            // title = title ? `${title}——${defaultTitle}` : defaultTitle;
            title = defaultTitle;
            document.title = title;
        },
        closeTag: (value) => {
            let tag = value || this.$store.getters.tag;
            if (typeof value === 'string') {
                tag = this.$store.getters.tagList.filter(ele => ele.value === value)[0]
            }
            this.$store.commit('DEL_TAG', tag)
        },
        generateTitle: (title, key) => {
            if (!key) return title;
            const hasKey = this.$vue.$te('route.' + key)
            if (hasKey) {
                // $t :this method from vue-i18n, inject in @/lang/index.js
                const translatedTitle = this.$vue.$t('route.' + key)

                return translatedTitle
            }
            return title
        },
        //处理路由
        getPath: function (params = {}, meta = {}) {
            let { src } = params;
            let result = src || '/';
            if (isURL(src)) {
                result = `/myiframe/urlPath?${objToform(Object.assign(meta, params))}`;
            }
            return result;
        },
        //正则处理路由
        vaildPath: function (list, path) {
            let result = false;
            list.forEach(ele => {
                if (new RegExp("^" + ele + ".*", "g").test(path)) {
                    result = true
                }

            })
            return result;
        },
        //设置路由值
        getValue: function (route) {
            let value = "";
            if (route.query.src) {
                value = route.query.src;
            } else {
                value = route.path;
            }
            return value;
        },
        //动态路由
        formatRoutes: function (aMenu = [], first) {
            const aRouter = []
            const propsConfig = this.$website.menu.props;
            const propsDefault = {
                label: propsConfig.label || 'label',
                path: propsConfig.path || 'path',
                icon: propsConfig.icon || 'icon',
                children: propsConfig.children || 'children',
                meta: propsConfig.meta || 'meta',
            }
            if (aMenu.length === 0) return;
            for (let i = 0; i < aMenu.length; i++) {
                const oMenu = aMenu[i];
                let path = (() => {
                        if (first) {
                            return oMenu[propsDefault.path].replace('/index', '')
                        } else {
                            return oMenu[propsDefault.path]
                        }
                    })(),
                    component = oMenu.component,
                    name = oMenu[propsDefault.label],
                    icon = oMenu[propsDefault.icon],
                    children = oMenu[propsDefault.children],
                    meta = oMenu[propsDefault.meta] || {}

                meta = Object.assign(meta, (function () {
                    if (option.keepAlive === true) {
                        return {
                            keepAlive: true
                        }
                    }
                })());
                const isChild = children && children.length !== 0;
                const oRouter = {
                    path: path,
                    component (resolve) {
                        // 判断是否为首路由
                        if (first) {
                            require(['../page/index'], resolve)
                            return
                            // 判断是否为多层路由
                        } else if (isChild && !first) {
                            require(['../page/index/layout'], resolve)
                            return
                            // 判断是否为最终的页面视图
                        } else {
                            if(component.indexOf("http://")>=0||component.indexOf("https://")>=0){
                                require([`${component}`], resolve)
                            }else {
                                require([`../${component}.vue`], resolve)
                            }
                        }
                    },
                    name: name,
                    icon: icon,
                    meta: meta,
                    redirect: (() => {
                        if (!isChild && first && !isURL(path)) return `${path}/index`
                        else return '';
                    })(),
                    // 处理是否为一级路由
                    children: !isChild ? (() => {
                        if (first) {
                            if (!isURL(path)) oMenu[propsDefault.path] = `${path}/index`;
                            if(component.indexOf("http://")>=0||component.indexOf("https://")>=0){
                                return [{
                                    component(resolve) {
                                        require([`${component}`], resolve)
                                    },
                                    icon: icon,
                                    name: name,
                                    meta: meta,
                                    path: 'index',
                                }];
                            }else {
                                return [{
                                    component(resolve) {
                                        require([`../${component}.vue`], resolve)
                                    },
                                    icon: icon,
                                    name: name,
                                    meta: meta,
                                    path: 'index',
                                }];
                            }
                        }
                        return [];
                    })() : (() => {
                        return this.formatRoutes(children, false)
                    })()
                }
                aRouter.push(oRouter)
            }
            if (first) {
                this.safe.$router.addRoutes(aRouter)
            } else {
                return aRouter
            }

        },

        //动态路由
        formatviewsRoutes: function (aMenu = [], first) {
            const aRouter = []
            if (aMenu.length === 0) return;
            for (let i = 0; i < aMenu.length; i++) {
                const oMenu = aMenu[i];
                const isChild = oMenu.children.length > 0;
                const oRouter = {
                    path: oMenu.path,
                    component (resolve) {
                        // 判断是否为首路由
                        if (first) {
                            require(['../page/index'], resolve)
                            return
                            // 判断是否为多层路由
                        } else if (isChild && !first) {
                            require(['../page/index/layout'], resolve)
                            return
                        }else{
                            require([`@/${oMenu.component}.vue`], resolve)
                            return
                        }
                    },
                    name: oMenu.name,
                    meta: oMenu.meta,
                    redirect: (() => {
                        if (isChild && first) return `${oMenu.redirect}`
                        else return '';

                    })(),
                    // 处理是否为一级路由
                    children: !isChild ? (() => {
                        if (first) {
                            return [{
                                component(resolve) {
                                    require([`${oMenu.component}`], resolve)
                                },
                                name: oMenu.name,
                                meta: oMenu.meta,
                                path: oMenu.path
                            }];
                        }
                        return [];
                    })() : (() => {
                        return this.formatviewsRoutes(oMenu.children, false)
                    })()
                }
                aRouter.push(oRouter)
            }
            if (first) {
                this.safe.$router.addRoutes(aRouter)
            } else {
                return aRouter
            }
        }

    }
}
export default RouterPlugin;