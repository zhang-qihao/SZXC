//参数JS
import eframe from '/src/util/eframe';
import store from "/src/store/index";
import {getStore, setStore} from "./store";

/*查询系统参数值*/
export function getServPara(para_type_code) {
    if (eframe.isNull(para_type_code)) {
        eframe.alertError("para_type_code值不可为空！");
        return null;
    }
    let servParaMap = store.state.user.userInfo.servParaMap;
    if (eframe.isNull(servParaMap)) {
        eframe.alertError("servParaMap值不可为空！");
        return null;
    }

    let data = servParaMap[para_type_code];
    // if(eframe.isNull(data)){
    //     eframe.alertError(para_type_code+"参数不可为空！");
    //     return null;
    // }
    return data;
}

export function getServParaValue(para_type_code) {
    let servPara = getServPara(para_type_code);
    if (servPara != null) {
        return servPara.paraval;
    } else {
        return "";
    }
}

//查询数据中的参数值
export function getServParaByDB(params, succFun, failFun) {
    if (eframe.isNull(params)) {
        eframe.alertError("参数信息获取错误，参数不可为空！");
        return;
    }
    if (eframe.isNull(params.para_type_code)) {
        eframe.alertError("参数信息获取错误，参数类型不可为空！");
        return;
    }
    if (eframe.isNull(params.fixmedins_id)) {
        eframe.alertError("参数信息获取错误，机构不可为空！");
        return;
    }
    let backFun = function (data) {
        reSetServPara(data);
        if (typeof (succFun) == "function") {
            succFun(data);
        }
    };
    eframe.submitTargetForm({
        url: "/ehis/servparamgmt/servpara.action",
        form: params,
        actionFunName: "loadServPara",
        succFun: backFun,
        failFun: failFun
    });
}

//重置参数值
export function reSetServPara(servPara) {
    let para_type_code = servPara.para_type_code;
    let servParaMap = store.state.user.userInfo.servParaMap;
    servParaMap[para_type_code] = servPara;
    store.state.user.userInfo.servParaMap = servParaMap;
}

export function getConfigArea(succFun) {
    //缓存
    let area = getStore({name: "config:area"});
    if (eframe.isNotNull(area)) {
        if (typeof (succFun) == "function") {
            succFun(area);
        }
        return area;
    }
    //后台查询
    let backFun = function (data) {
        if (eframe.isNotNull(data)) {
            setStore({name: "config:area", content: data});
            if (typeof (succFun) == "function") {
                succFun(data);
            }
            return data;
        }
    };
    eframe.submitTargetForm({
        url: "/ehis/servparamgmt/config.action",
        form: {},
        actionFunName: "getConfigArea",
        succFun: backFun
    });
}


export default {
    getServParaValue: getServParaValue, //取缓存参数值
    getServPara: getServPara,   //取系统参数
    reSetServPara: reSetServPara, //重置参数值
    getServParaByDB: getServParaByDB, //从数据库中获取参数值
    getConfigArea:getConfigArea,//获取
}