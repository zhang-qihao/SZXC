import { setStore, getStore } from '@/util/store'
import eframe from "@/util/eframe";
import constData from "../../const";

const dict = {
    state: {
        dictMap: getStore({ name: 'dictMap' })||{},
    },
    actions: {

        //读取字典
        LoadDicItem ({ state,commit }, { dicId, dicListGroup, language,  catchFlag}) {
            return new Promise((resolve, reject)  => {
                if(eframe.isNull(dicId)){
                    eframe.alertError("LoadDicItem参数错误，dicId不可以为空！");
                    return;
                }
                if(eframe.isNull(dicListGroup)){
                    dicListGroup = "";
                }
                if(eframe.isNull(language)){
                    language = "1";
                }

                let id = "EF_DIC_"+dicId+"_"+dicListGroup+"_"+language;
                if(catchFlag===true){
                    if (state.dictMap[id]) { //从缓存中取出
                        let dictStr = state.dictMap[id];
                        let items = JSON.parse(dictStr);
                        resolve(items);
                        return;
                    }
                }
                let url = "/wechat/common/dictionary.action";
                let form={
                    dicId:dicId,
                    dicListGroup:dicListGroup,
                    language:language,
                };



                let actionFunName="loadDicItem";
                let callbackFun = function (data) {
                    if(eframe.isNotNull(data) && data.length > 0) {
                        commit('ADD_DICT', {id:id,dictList:data});
                        resolve( data );
                    }
                };
                let errorFun = function (data) {
                    reject("未找到数据字典配置信息 dicId:"+dicId);
                };
                eframe.submitTargetForm({
                    url:url,form:form,actionFunName:actionFunName,succFun:callbackFun,failFun:errorFun});
            });
        },
    },
    mutations: {

        ADD_DICT: (state, { id, dictList }) => {
            let dictStr = JSON.stringify(dictList);
            state.dictMap[id]=dictStr;
            setStore({ name: 'dictMap', content: state.dictMap })
        },

        CLEAR_ALL_DICT: (state) => {
            state.dictMap = {};
            setStore({ name: 'dictMap', content: state.dictMap })
        },

        CLEAR_DICT: (state,{ id }) => {
            delete state.dictMap[id];
            setStore({ name: 'dictMap', content: state.dictMap })
        },
    }



};

export default dict;