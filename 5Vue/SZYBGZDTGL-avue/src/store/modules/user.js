import {
    setToken,
    removeToken
} from '@/util/auth';
import {
    setStore,
    getStore
} from '@/util/store';
import {
    isURL,
    validatenull
} from '@/util/validate';
import {
    encryption,
    deepClone
} from '@/util/util';
import website from '@/config/website';

import request from '@/router/axios';
import eframe from "@/util/eframe";
import md5 from 'js-md5';
import {
    loginByUsername,
} from '@/api/user';
import {removeStore} from "../../util/store";
import store from "../index";
function addPath(ele, first) {
    const menu = website.menu;
    const propsConfig = menu.props;
    const propsDefault = {
        label: propsConfig.label || 'label',
        path: propsConfig.path || 'path',
        icon: propsConfig.icon || 'icon',
        children: propsConfig.children || 'children'
    };
    const icon = ele[propsDefault.icon];
    ele[propsDefault.icon] = validatenull(icon) ? menu.iconDefault : icon;
    const isChild = ele[propsDefault.children] && ele[propsDefault.children].length !== 0;
    if (isURL(ele[propsDefault.path])) {
        ele[propsDefault.path] = ele[propsDefault.path].replace(/&/g, "$")
    }
    if (!isChild && first && !isURL(ele[propsDefault.path])) {
        let path = ele[propsDefault.path] + '/index';
        path = path.replace(/\/\//g, "/");
        ele[propsDefault.path] = path;
    } else {

        ele[propsDefault.children] && ele[propsDefault.children].forEach(child => {
            if (!isURL(child[propsDefault.path])) {
                let path =
                    `${ele[propsDefault.path]}/${child[propsDefault.path] ? child[propsDefault.path] : 'index'}`;
                path = path.replace(/\/\//g, "/");
                child[propsDefault.path] = path;
            }
            addPath(child);
        })
    }

}

const user = {
    state: {
        userInfo: {},
        permission: {},
        roles: [],
        menuId: {},
        menu: getStore({
            name: 'menu'
        }) || [],
        menuAll: getStore({
            name: 'menuAll'
        }) || [],
        token: getStore({
            name: 'token'
        }) || '',
        topMenu:getStore({
            name:'topMenu'
        }) || [],
    },
    actions: {
        //根据用户名登录
        LoginByUsername({
                            commit
                        }, userInfo) {
            return new Promise((resolve, reject) => {
                let extUserId = '';
                let loginType = '';

                let url = '/login/token.action';
                let username = "{'loginType':'" + loginType + "'," +
                    "'userId':'" + userInfo.username + "'," +
                    "'extUserId':['" + extUserId + "']," +
                    "'loginMethod':'0'," +
                    "'programNo':'" + website.key + "'}";

                let form = {
                    grant_type: "password",
                    username: username,
                    password: md5(userInfo.password),
                };
                let actionFunName = "getTokenId";

                let callbackFun = function (data) {
                    commit('SET_TOKEN', data);
                    commit('DEL_ALL_TAG');
                    commit('CLEAR_LOCK');
                    resolve();
                };
                let errorFun = function (data) {
                    eframe.alertError(data.msg);
                    reject("登录失败【" + form.userId + "】");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });

            })
        },

        //根据手机号登录
        LoginByPhone({commit}, userInfo) {
            return new Promise((resolve) => {
                loginByUsername(userInfo.phone, userInfo.code).then(res => {
                    const data = res.data.data;
                    commit('SET_TOKEN', data);
                    commit('DEL_ALL_TAG');
                    commit('CLEAR_LOCK');
                    resolve();
                })
            })
        },
        //获取登录用户
        GetUserInfo({commit}) {
            return new Promise((resolve, reject) => {
                let url= '/frame/systemmanagement/usermanagement/user/usermanagement.action'
                let form = {};
                let actionFunName = "loadUserInfo";
                let callbackFun = function (data) {

                    let role = "1";
                    if (data.isAdmin == "0") {
                        role = "admin"
                    }
                    let roles = {
                        roles: role,
                    };
                    let permissions = [];
                    let permission = {
                        permission: permissions
                    };
                    commit('SET_USERIFNO', data);
                    commit('SET_ROLES', roles.roles);
                    commit('SET_PERMISSION', permission.permission);
                    resolve(data);
                };
                let errorFun = function (data) {
                    reject("获取用户信息失败");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            })
        },
        //刷新token
        RefeshToken({state, commit}) {
            return new Promise((resolve, reject) => {
                let token = getStore({
                    name: 'token'
                });

                let refreshToken = token.refreshToken;
                if(isNull(refreshToken)){
                    return;
                }
                // let url = '/userloginmgmt/token.action';
                let url = '/login/token.action';
                let form = {
                    grant_type: "refreshtoken",
                    refreshToken: refreshToken,
                };
                let actionFunName = "getTokenId";
                let callbackFun = function (data) {
                    console.log("refreshtoken");
                    commit('SET_TOKEN', data);
                    resolve();
                };
                let errorFun = function (data) {
                    console.log("refreshtoken errorFun");
                    store.dispatch('FedLogOut').then(() => router.push({ path: '/login' }));
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            });
        },

        // 登出
        LogOut({commit}) {
            return new Promise((resolve, reject) => {
                let succFun = function (data) {
                    commit('SET_USERINFO', {});
                    commit('SET_ROLES', []);
                    commit('SET_TOKEN', '')
                    commit('SET_MENUALL_NULL', []);
                    commit('SET_MENU', [])
                    commit('SET_TAG_LIST', [])
                    commit('SET_ROLES', [])
                    commit('DEL_ALL_TAG');
                    commit('CLEAR_LOCK');
                    removeToken();
                    removeStore({name: 'showPageFlag'});
                    resolve();
                };
                let errorFun = function (data) {
                    reject("登出失败【" + userInfo.username + "】");
                };
                eframe.submitTargetForm({
                    // url: "/userloginmgmt/login.action",
                    url: "/login/login.action",
                    form: {},
                    actionFunName: "logout",
                    succFun: succFun,
                    failFun: errorFun
                });

            })
        },
        //注销session
        FedLogOut({commit}) {
            return new Promise(resolve => {
                let succFun = function (data) {
                    commit('SET_USERINFO', {});
                    commit('SET_TOKEN', '')
                    commit('SET_MENUALL_NULL', []);
                    commit('SET_MENU', [])
                    commit('SET_TAG_LIST', [])
                    commit('SET_ROLES', [])
                    commit('DEL_ALL_TAG');
                    commit('CLEAR_LOCK');
                    commit('SET_TOPMENU_NULL',[])
                    removeToken()
                    resolve()
                }
                let errorFun = function (data) {
                    reject("登出失败【" + userInfo.username + "】");
                };
                eframe.submitTargetForm({
                    url: "/login/login.action",
                    form: {},
                    actionFunName: "logout",
                    succFun: succFun,
                    failFun: errorFun
                });
            })
        },
        //获取顶部菜单
        GetTopMenu() {
            return new Promise((resolve, reject) => {
                let url = '/menu.action';
                let form = {};
                let actionFunName = "loadTopMenu";
                let callbackFun = function (data) {
                    let menu = deepClone(data);
                    resolve(menu);
                };
                let errorFun = function (data) {
                    reject("获取菜单错误！");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            });
        },
        //获取T2顶部菜单
        GetT2TopMenu({commit}) {
            return new Promise((resolve, reject) => {
                let url = '/menu.action?moduleType=1';
                let form = {};
                let actionFunName = "loadTopMenu";
                let callbackFun = function (data) {
                    let menu = deepClone(data);
                    commit('SET_TOPMENU', menu);
                    resolve(menu);
                };
                let errorFun = function (data) {
                    reject("获取菜单错误！");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            });
        },
        //获取系统菜单
        GetMenu({commit}, parentId) {
            return new Promise((resolve, reject) => {
                let url = '/menu.action';
                if (parentId) {
                    url = url + '?parentId=' + parentId;
                }
                let form = {};
                let actionFunName = "loadMenu";
                let callbackFun = function (data) {
                    let menu = deepClone(data);
                    menu.forEach(ele => {
                        addPath(ele, true);
                    });
                    commit('SET_MENUALL', menu);
                    commit('SET_MENU', menu);
                    resolve(menu);
                };
                let errorFun = function (data) {
                    reject("获取菜单错误！");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            })
        },
        //获取T2侧边菜单系统
        GetT2Menu({commit}, parentId) {
            return new Promise((resolve, reject) => {
                let url = '/menu.action';
                // if (parentId) {
                    url = url + '?parentId=' + 'all' + "&rootMenuNo=" + parentId;
                // }
                let form = {};
                let actionFunName = "loadMenu";
                let callbackFun = function (data) {
                    let menu = deepClone(data);
                    menu.forEach(ele => {
                        addPath(ele, true);
                    });
                    commit('SET_MENUALL', menu);
                    // commit('SET_MENU', menu);
                    resolve(menu);
                };
                let errorFun = function (data) {
                    reject("获取菜单错误！");
                };
                eframe.submitTargetForm({
                    url: url,
                    form: form,
                    actionFunName: actionFunName,
                    succFun: callbackFun,
                    failFun: errorFun
                });
            })
        },
    },
    mutations: {
        SET_TOPMENU_NULL:(state,topMenu)=>{
            state.topMenu = []
            setStore({
                name:'topMenu',
                content:state.topMenu
            })
        },
        SET_TOPMENU:(state,topMenu) => {
            state.topMenu = topMenu
            setStore({
                name:'topMenu',
                content:state.topMenu
            })
        },
        SET_TOKEN: (state, token) => {
            setToken(token)
            state.token = token;
            setStore({
                name: 'token',
                content: state.token
            })

        },
        SET_MENUID(state, menuId) {
            state.menuId = menuId;
        }
        ,
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo;
        },
        SET_MENUALL:(state, menuAll) => {
            let menu = state.menuAll;
            menuAll.forEach(ele => {
                if (!menu.find(item => item.label == ele.label && item.path == ele.path)) {
                    menu.push(ele);
                }
            })
            state.menuAll = menu
            setStore({
                name: 'menuAll',
                content: state.menuAll
            })
        },
        SET_MENUALL_NULL:(state) => {
            state.menuAll = []
            setStore({
                name: 'menuAll',
                content: state.menuAll
            })
        },
        SET_MENU:(state, menu) => {
            state.menu = menu
            setStore({
                name: 'menu',
                content: state.menu
            })
        },
        //设置t2模式下的menu
        SET_T2MENU:(state,topId) => {
            function setTopId(list,topId){
                list.map((item,index)=>{
                    Vue.set(item,'topId',topId)
                    if(item.children.length > 0){
                        setTopId(item.children,topId)
                    }
                })
            }
            let currentMenu = state.menuAll.filter(item =>
                (item.path == ('/' + topId)) || (item.path == '/wel/' + topId)
            )
            if(currentMenu.length > 0){
                setTopId(currentMenu,topId)
                state.menu = currentMenu[0].children
            }else{
                state.menu = []
            }
            setStore({
                name: 'menu',
                content: state.menu
            })
        },
        SET_ROLES:(state, roles) => {
            state.roles = roles;
        },
        SET_PERMISSION:(state, permission) => {
            state.permission = {};
            permission.forEach(ele => {
                state.permission[ele] = true;
            });
        },
    }
}
export default user;
