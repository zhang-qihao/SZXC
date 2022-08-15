import { setStore, getStore } from '@/util/store'
import eframe from "@/util/eframe";
import constData from "../../const";

const querys = {
    state: {
        querysMap: getStore({ name: 'querysMap' })||{},
    },
    actions: {

        //分页查询（分时获取）
        GetPageGrid ({ state,commit }, { widgetUid, queryNo, queryWindow,connectId, showType, pageSize, catchFlag}) {
            return new Promise((resolve, reject)  => {
                if(eframe.isNull(widgetUid)){
                    eframe.alertError("GetPageGrid参数错误，widgetUid不可以为空！");
                    return;
                }
                if(eframe.isNull(queryNo)){
                    eframe.alertError("GetPageGrid参数错误，queryNo不可以为空！");
                    return;
                }
                if(eframe.isNull(queryWindow)){
                    eframe.alertError("GetPageGrid参数错误，queryWindow不可以为空！");
                    return;
                }
                if(eframe.isNull(showType)){
                    eframe.alertError("GetPageGrid参数错误，showType不可以为空！");
                    return;
                }
                if(eframe.isNull(pageSize)){
                    eframe.alertError("GetPageGrid参数错误，pageSize不可以为空！");
                    return;
                }
                let id = "EF_PageGrid_"+queryNo+"_"+queryWindow+"_"+showType;
                if(catchFlag===true) {
                    if (state.querysMap[id]) { //从缓存中取出
                        let options = state.querysMap[id];
                        let reg = new RegExp(id, "g");
                        options = options.replace(reg, widgetUid);
                        options = options.replace("pageSize: 20", "pageSize: " + pageSize);
                        resolve({options: options});
                        return;
                    }
                }
                let url="/frame/component/pagegrid/pagegrid.action";
                let form={
                    widgetUid:id,
                    queryNo:queryNo,  //查询号
                    queryWindow:queryWindow, //窗口号
                    connectId:connectId,//数据库链接
                    showType:showType, // 0不显示check框   1 显示check框， 默认为：1
                    pageSize:20, //每页显示条数
                };
                let actionFunName="config";
                let callbackFun = function (data) {
                    if(data.options) {
                        commit('ADD_QUERY', {id:id,queryCfg:data.options});
                        let options = data.options;
                        let reg = new RegExp( id , "g" );
                        options = options.replace( reg , widgetUid );
                        options = options.replace( "pageSize: 20" , "pageSize: "+pageSize );
                        resolve( {options:options} );
                    }
                };
                let errorFun = function (data) {
                    reject("未找到查询配置信息 queryNo:"+queryNo+" queryWindow:"+queryWindow);
                };

                //2022-03-18 分时取查询配置中心配置记录，调整为实时获取
                // eframe.submitTargetForm({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

                //2022-03-18 实时获取查询配置（测试）
                eframe.submitTargetFormSync({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});



            })
        },


        //查询编辑（分时获取）
        GetGridEditor ({ state,commit }, { widgetUid, queryNo, queryWindow,connectId, catchFlag}) {
            return new Promise((resolve, reject)  => {
                if(eframe.isNull(widgetUid)){
                    eframe.alertError("GetGridEditor参数错误，widgetUid不可以为空！");
                    return;
                }
                if(eframe.isNull(queryNo)){
                    eframe.alertError("GetGridEditor参数错误，queryNo不可以为空！");
                    return;
                }
                if(eframe.isNull(queryWindow)){
                    eframe.alertError("GetGridEditor参数错误，queryWindow不可以为空！");
                    return;
                }

                let id = "EF_GridEditor_"+queryNo+"_"+queryWindow;
                if(catchFlag===true) {
                    if (state.querysMap[id]) { //从缓存中取出
                        let options = state.querysMap[id];
                        let reg = new RegExp(id, "g");
                        options = options.replace(reg, widgetUid);
                        resolve({options: options});
                        return;
                    }
                }
                let url="/frame/component/pagegrid/grideditor.action";
                let form={
                    widgetUid:id,
                    queryNo:queryNo,  //查询号
                    queryWindow:queryWindow, //窗口号
                    connectId:connectId,//数据库链接
                };
                let actionFunName="config";
                let callbackFun = function (data) {
                    if(data.options) {
                        commit('ADD_QUERY', {id:id,queryCfg:data.options});
                        let options = data.options;
                        let reg = new RegExp( id , "g" );
                        options = options.replace( reg , widgetUid );
                        resolve( {options:options} );
                    }
                };
                let errorFun = function (data) {
                    reject("未找到查询配置信息 queryNo:"+queryNo+" queryWindow:"+queryWindow);
                };


                //2022-03-18 分时取查询配置中心配置记录，调整为实时获取
                // eframe.submitTargetForm({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

                //2022-03-18 实时获取查询配置（测试）
                eframe.submitTargetFormSync({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

            })
        },



        //树Tree查询（分时获取）
        GetTree ({ state,commit }, { widgetUid, queryNo, queryWindow, connectId,showType, catchFlag}) {
            return new Promise((resolve, reject)  => {
                if(eframe.isNull(widgetUid)){
                    eframe.alertError("GetTree参数错误，widgetUid不可以为空！");
                    return;
                }
                if(eframe.isNull(queryNo)){
                    eframe.alertError("GetTree参数错误，queryNo不可以为空！");
                    return;
                }
                if(eframe.isNull(queryWindow)){
                    eframe.alertError("GetTree参数错误，queryWindow不可以为空！");
                    return;
                }
                if(eframe.isNull(showType)){
                    eframe.alertError("GetTree参数错误，showType不可以为空！");
                    return;
                }
                let id = "EF_Tree_"+queryNo+"_"+queryWindow+"_"+showType;
                if(catchFlag===true) {
                    if (state.querysMap[id]) { //从缓存中取出
                        let options = state.querysMap[id];
                        let reg = new RegExp(id, "g");
                        options = options.replace(reg, widgetUid);
                        resolve({options: options});
                        return;
                    }
                }
                let url="/frame/component/tree/tree.action";
                let form={
                    widgetUid:id,
                    queryNo:queryNo,  //查询号
                    queryWindow:queryWindow, //窗口号
                    connectId:connectId,//数据库链接
                    showType:showType, // 0不显示check框   1 显示check框， 默认为：1
                };
                let actionFunName="config";
                let callbackFun = function (data) {
                    if(data.options) {
                        commit('ADD_QUERY', {id:id,queryCfg:data.options});
                        let options = data.options;
                        let reg = new RegExp( id , "g" );
                        options = options.replace( reg , widgetUid );
                        resolve( {options:options} );
                    }
                };
                let errorFun = function (data) {
                    reject("未找到查询配置信息 queryNo:"+queryNo+" queryWindow:"+queryWindow);
                };

                //2022-03-18 分时取查询配置中心配置记录，调整为实时获取
                // eframe.submitTargetForm({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

                //2022-03-18 实时获取查询配置（测试）
                eframe.submitTargetFormSync({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

            })
        },




        //树状Grid查询（分时获取）
        GetTreeGrid ({ state,commit }, { widgetUid, queryNo, queryWindow, connectId,showType, catchFlag}) {
            return new Promise((resolve, reject)  => {
                if(eframe.isNull(widgetUid)){
                    eframe.alertError("GetTreeGrid参数错误，widgetUid不可以为空！");
                    return;
                }
                if(eframe.isNull(queryNo)){
                    eframe.alertError("GetTreeGrid参数错误，queryNo不可以为空！");
                    return;
                }
                if(eframe.isNull(queryWindow)){
                    eframe.alertError("GetTreeGrid参数错误，queryWindow不可以为空！");
                    return;
                }
                if(eframe.isNull(showType)){
                    eframe.alertError("GetTreeGrid参数错误，showType不可以为空！");
                    return;
                }
                let id = "EF_TreeGrid_"+queryNo+"_"+queryWindow+"_"+showType;
                if(catchFlag===true) {
                    if (state.querysMap[id]) { //从缓存中取出
                        let options = state.querysMap[id];
                        let reg = new RegExp(id, "g");
                        options = options.replace(reg, widgetUid);
                        resolve({options: options});
                        return;
                    }
                }
                let url="/frame/component/treegrid/treegrid.action";
                let form={
                    widgetUid:id,
                    queryNo:queryNo,  //查询号
                    queryWindow:queryWindow, //窗口号
                    connectId:connectId,//数据库链接
                    showType:showType, // 0不显示check框   1 显示check框， 默认为：1
                };
                let actionFunName="config";
                let callbackFun = function (data) {
                    if(data.options) {
                        commit('ADD_QUERY', {id:id,queryCfg:data.options});
                        let options = data.options;
                        let reg = new RegExp( id , "g" );
                        options = options.replace( reg , widgetUid );
                        resolve( {options:options} );
                    }
                };
                let errorFun = function (data) {
                    reject("未找到查询配置信息 queryNo:"+queryNo+" queryWindow:"+queryWindow);
                };

                //2022-03-18 分时取查询配置中心配置记录，调整为实时获取
                // eframe.submitTargetForm({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

                //2022-03-18 实时获取查询配置（测试）
                eframe.submitTargetFormSync({url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});

            })
        },




    },
    mutations: {

        ADD_QUERY: (state, { id, queryCfg }) => {
            state.querysMap[id]=queryCfg;
            setStore({ name: 'querysMap', content: state.querysMap })
        },

        CLEAR_ALL_QUERYS: (state) => {
            state.querysMap = {};
            setStore({ name: 'querysMap', content: state.querysMap })
        },

        CLEAR_QUERY: (state,{ id }) => {
            delete state.querysMap[id];
            setStore({ name: 'querysMap', content: state.querysMap })
        },
    }

};

export default querys;