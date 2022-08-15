// import vue from 'vue'
// import Element from 'element-ui'
// import $ from 'jquery'
import request from '@/router/axios';

import constData from '../const/index'
// import request from '@/router/axios';
// import router from '@/router/router'

// import store from '@/store/';

// import Vue from 'vue'
var CryptoJS = require("crypto-js");
import {MessageBox} from 'element-ui';
import {Message} from 'element-ui';
import {Loading} from 'element-ui';
import {getStore, setStore} from "./store";
import {baseUrl} from "../config/env";


var frameCrypto = "false";
var frameCryptoKey = "12345678901234567890123456789012";

/*判断空*/
function isNull(data) {
    return (data === "" || data === undefined || data === null);
}

/*判断非空*/
function isNotNull(data) {
    return !isNull(data);
}


/*判断List为空*/
function isListEmpty(value) {
    if (isNull(value)) {
        return true;
    } else {
        return (Array.isArray(value) && value.length === 0) || (Object.keys(value).length === 0);
    }
}

/*扩展Object*/
function extend() {
    var options, name, src, copy, copyIsArray, clone,
        target = arguments[0] || {},
        i = 1,
        length = arguments.length,
        deep = false;

    if (typeof target === "boolean") {
        deep = target;

        target = arguments[i] || {};
        i++;
    }

    if (typeof target !== "object" && !isFunction(target)) {
        target = {};
    }

    if (i === length) {
        target = this;
        i--;
    }

    for (; i < length; i++) {
        if ((options = arguments[i]) != null) {
            for (name in options) {
                src = target[name];
                copy = options[name];

                if (target === copy) {
                    continue;
                }

                if (deep && copy && (isPlainObject(copy) || (copyIsArray = isArray(copy)))) {
                    if (copyIsArray) {
                        copyIsArray = false;
                        clone = src && isArray(src) ? src : [];

                    } else {
                        clone = src && isPlainObject(src) ? src : {};
                    }

                    target[name] = extend(deep, clone, copy);

                } else if (copy !== undefined) {
                    target[name] = copy;
                }
            }
        }
    }

    return target;
}


function isArray(object) {
    return object instanceof Array;
}

function isFunction(fun) {
    let flag = typeof fun == 'function';
    return flag;
}

function isObject(val) {
    return val != null && typeof val === 'object' && Array.isArray(val) === false;
}

function isPlainObject(obj) {
    return isObject(obj) && Object.getPrototypeOf(obj) === Object.prototype;
}


/*提示信息(气泡窗)*/
function showToast(msg) {
    Message.success(msg)
}

/*提示信息(气泡窗)*/
function showToastError(msg) {
    Message.error(msg)
}

/*提示信息*/
function alertMessage(msg) {
    MessageBox.alert(msg, "提示", {
        type: 'success',
        closeOnClickModal: false,//false:点击空白不能关闭提示
    });
}

/*错误提示*/
function alertError(msg) {
    MessageBox.alert(msg, "错误", {
        type: 'error',
        closeOnClickModal: false,//false:点击空白不能关闭提示
    });
}

/*显示等待*/
function showWaiting() {
    Loading.service({fullscreen: true, background: 'rgba(255,255,255,0)'});
}

/*隐藏等待*/
function hiddenWaiting() {
    Loading.service({fullscreen: true, background: 'rgba(255,255,255,0)'}).close();
}

/*confirm*/
function confirm(title, msg, okFun, cancelFun) {
    if (title == null || typeof (title) == "undefined") {
        title = "提示";
    }
    if (msg == null || typeof (msg) == "undefined") {
        alertError("入参不可为空！");
        return;
    }

    MessageBox.confirm(msg, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        closeOnClickModal: false,//false:点击空白不能关闭提示
    }).then(() => {
        if (typeof (okFun) == 'function') {
            okFun();
        }
    }).catch(() => {
        if (typeof (cancelFun) == 'function') {
            cancelFun();
        }
    });
}


/*生成UUID*/
function uuid() {
    var len = 32; //32长度
    var radix = 16; //16进制
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [],
        i;
    radix = radix || chars.length;
    if (len) {
        for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
    } else {
        var r;
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | Math.random() * 16;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }
    return uuid.join('');
}


/**
 * 加密处理
 */
function encryptAES(data) {
    if (isNull(data)) {
        return "";
    }
    let M_SALT = '40b92f21b7356c04b79fbe18c10752cb';
    let M_IV = '6a83227ace94510ca9b77878a2d0c6da';
    let M_PASSPHRASE = 'D0so6K';
    let M_KEY_SIZE = 128 / 32;
    let M_ITERATION_COUNT = 1000;

    let key = CryptoJS.PBKDF2(
        M_PASSPHRASE,
        CryptoJS.enc.Hex.parse(M_SALT),
        {keySize: M_KEY_SIZE, iterations: M_ITERATION_COUNT});

    let encrypted = CryptoJS.AES.encrypt(
        data,
        key,
        {iv: CryptoJS.enc.Hex.parse(M_IV)});

    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);

}

/**
 * 加密（需要先加载lib/aes/aes.min.js文件）
 * @param word
 * @returns {*}
 */
// var cryptoKey = "abcdefgabcdefg12";

function encryptForm(word, cryptoKey) {
    let key = CryptoJS.enc.Utf8.parse(cryptoKey);
    let srcs = CryptoJS.enc.Utf8.parse(word);
    let encrypted = CryptoJS.AES.encrypt(srcs, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
}


/**
 * 解密
 * @param word
 * @returns {*}
 */
function decryptForm(word, cryptoKey) {
    let key = CryptoJS.enc.Utf8.parse(cryptoKey);
    let decrypt = CryptoJS.AES.decrypt(word, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}



//同步请求
async function submitTargetFormSync({url, form, actionFunName, succFun, failFun}) {
    console.log("submitTargetFormSync");
    if (url == null || typeof (url) == 'undefined') {
        alert("submitTargetForm  url参数不能为空");
        return;
    }
    if (form == null || typeof (form) == 'undefined') {
        return;
    }
    if (actionFunName == null || typeof (actionFunName) == 'undefined') {
        alert("submitTargetForm  actionFunName参数不能为空");
        return;
    }
    url = formatURL(url);
    if (url.indexOf("?") >= 0) {
        url = url + "&frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    } else {
        url = url + "?frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    }
    showWaiting();
    let successFun = function (data) {
        hiddenWaiting();
        if (typeof (data) == "object") {
            data = data;
        } else {
            data = decryptAES(data);
            data = JSON.parse(data);
        }
        if (data.code) {
            if (data.code == "200") {
                data = data.data;
            } else {
                if (typeof failFun == 'function') {
                    failFun(data.msg);
                }
                alertError(data.msg);
                return;
            }
        }
        if (data.frameControlIsError === "1") {
            if (typeof failFun == 'function') {
                failFun(data.frameControlErrorMessage);
            }
            alertError(data.frameControlErrorMessage);
            return;
        }
        if (isNotNull(data.frameControlBackMessage)) {
            if (data.frameControlIsBack === "2") {
                showToast(data.frameControlBackMessage);
            } else {
                alertMessage(data.frameControlBackMessage);
            }
        }
        for (let item in data) {
            if (item === "frameControlIsError" || item === "frameControlErrorMessage"
                || item === "frameControlIsBack" || item === "frameControlBackMessage"
                || item === "frameControlOption" || item === "frameControlSubmitFunction") {
                continue;
            } else {
                form[item] = data[item];
            }
        }
        delete data["frameControlIsError"];
        delete data["frameControlErrorMessage"];
        delete data["frameControlIsBack"];
        delete data["frameControlBackMessage"];
        delete data["frameControlOption"];
        delete data["frameControlSubmitFunction"];
        if (typeof succFun == 'function') {
            succFun(data);
        }
    };
    let requestStr = JSON.stringify(form);
    if(isNotNull(requestStr) && typeof (requestStr)!='object'){
        requestStr=encryptAES(requestStr);
    }
    let headers = {
        "Content-Type": "application/json;charset=utf-8"
    };
    let token = getStore({name: 'token'});
    if (token && token.token) {
        headers["Authorization"] = token.tokenHead + token.token;
    }
    let loginInfo=getStore({name: 'LoginInfo'});
    if (loginInfo) {
        headers["LoginInfo"] = JSON.stringify(loginInfo);
    }
    headers["Service-Type"] = "Microservices";
    let config = {
        headers: headers
    }
    let failFunc=function (error) {
        if (typeof failFun == 'function') {
            failFun(error);
        }
    }
    $.ajax(url, {
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        //timeout:30,
        async:false,
        headers: headers,
        data: requestStr,
        //data:form,
        dataType: "json",
        xhrFields: {
            withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
        },
        crossDomain: true,
        success: successFun,
        error: failFunc
    });

    // await request.post(url, requestStr, config)
    //     .then(successFun)
    //     .catch(function (error) {
    //         alertError(error.toString());
    //         hiddenWaiting();
    //         // store.dispatch('LogOut').then(() => router.push({ path: '/login' }));
    //         failFunc(error);
    //         return;
    //     })
}


//异步请求
function submitTargetForm({url, form, actionFunName, succFun, failFun}) {
    console.log("submitTargetForm");
    if (url == null || typeof (url) == 'undefined') {
        alert("submitTargetForm  url参数不能为空");
        return;
    }
    if (form == null || typeof (form) == 'undefined') {
        return;
    }
    if (actionFunName == null || typeof (actionFunName) == 'undefined') {
        alert("submitTargetForm  actionFunName参数不能为空");
        return;
    }
    url = formatURL(url);
    if (url.indexOf("?") >= 0) {
        url = url + "&frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    } else {
        url = url + "?frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    }
    showWaiting();
    let successFun = function (data) {
        hiddenWaiting();
        if (typeof (data) == "object") {
            data = data;
        } else {
            data = decryptAES(data);
            data = JSON.parse(data);
        }
        if (data.code) {
            if (data.code == "200") {
                data = data.data;
            } else {
                if (typeof failFun == 'function') {
                    failFun(data.msg);
                }
                alertError(data.msg);
                return;
            }
        }
        if (data.frameControlIsError === "1") {
            if (typeof failFun == 'function') {
                failFun(data.frameControlErrorMessage);
            }
            alertError(data.frameControlErrorMessage);
            return;
        }
        if (isNotNull(data.frameControlBackMessage)) {
            if (data.frameControlIsBack === "2") {
                showToast(data.frameControlBackMessage);
            } else {
                alertMessage(data.frameControlBackMessage);
            }
        }
        for (let item in data) {
            if (item === "frameControlIsError" || item === "frameControlErrorMessage"
                || item === "frameControlIsBack" || item === "frameControlBackMessage"
                || item === "frameControlOption" || item === "frameControlSubmitFunction") {
                continue;
            } else {
                form[item] = data[item];
            }
        }
        delete data["frameControlIsError"];
        delete data["frameControlErrorMessage"];
        delete data["frameControlIsBack"];
        delete data["frameControlBackMessage"];
        delete data["frameControlOption"];
        delete data["frameControlSubmitFunction"];
        if (typeof succFun == 'function') {
            succFun(data);
        }
    };
    let requestStr = JSON.stringify(form);
    let headers = {
        "Content-Type": "application/json;charset=utf-8"
    };
    let token = getStore({name: 'token'});
    if (token && token.token) {
        headers["Authorization"] = token.tokenHead + token.token;
    }
    let loginInfo=getStore({name: 'LoginInfo'});
    if (loginInfo) {
        headers["LoginInfo"] = JSON.stringify(loginInfo);
    }

    headers["Service-Type"] = "Microservices";
    let config = {
        headers: headers
    }

    let failFunc=function (error) {
        hiddenWaiting();
        if (typeof failFun == 'function') {
            failFun(error);
        }
    }

    request.post(url, requestStr, config)
        .then(successFun)
        .catch(function (error) {
            alertError(error.toString());
            hiddenWaiting();
            // store.dispatch('LogOut').then(() => router.push({ path: '/login' }));
            failFunc(error);
            return;
        })
}

function submitTargetFileForm({url, form, actionFunName, files, succFun, failFun}) {
    if (url == null || typeof (url) == 'undefined') {
        alert("submitTargetForm  url参数不能为空");
        return;
    }

    if (form == null || typeof (form) == 'undefined') {
        return;
    }

    if (actionFunName == null || typeof (actionFunName) == 'undefined') {
        alert("submitTargetForm  actionFunName参数不能为空");
        return;
    }

    url = formatURL(url);

    if (url.indexOf("?") >= 0) {
        url = url + "&frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    } else {
        url = url + "?frameControlSubmitFunction=" + encodeURIComponent(actionFunName) + "&winTemp=" + Math.random() * (10000);
    }
    showWaiting();

    let successFun = function (data) {
        hiddenWaiting();
        //解密data
        if (typeof (data) == "object") {
            data = data;
        } else {
            var deBeforeDate = new Date();
            data = decryptAES(data);
            data = JSON.parse(data);
            // console.log("解密时间："+(new Date()-deBeforeDate));
        }

        if (data.code) {
            if (data.code == "200") {
                data = data.data;
            } else {
                if (typeof failFun == 'function') {
                    failFun(data.msg);
                }
                alertError(data.msg);
                return;
            }
        }

        if (data.frameControlIsError === "1") {
            if (typeof failFun == 'function') {
                failFun(data.frameControlErrorMessage);
            }
            alertError(data.frameControlErrorMessage);
            return;
        }

        if (data.frameControlBackMessage != "" && data.frameControlBackMessage != null) {
            if (data.frameControlIsBack === "2") {
                showToast(data.frameControlBackMessage);
            } else {
                alertMessage(data.frameControlBackMessage);
            }
        }
        for (let item in data) {
            if (item === "frameControlIsError" || item === "frameControlErrorMessage" || item === "frameControlIsBack" || item === "frameControlBackMessage") {
                continue;
            } else {
                form[item] = data[item];
            }
        }

        delete form["frameControlIsError"];
        delete form["frameControlErrorMessage"];
        delete form["frameControlIsBack"];
        delete form["frameControlBackMessage"];

        if (typeof succFun == 'function') {
            succFun(data);
        }
    };


    let requestStr = JSON.stringify(form);

    let config = {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }
    //加密
    // if (frameCrypto == 'true') {
    //     headers["cryptoFlag"] = "true";
    //     requestStr = encryptForm(requestStr, frameCryptoKey);
    // }

    const formData = new FormData();
    Object.keys(form).forEach((key) => {
        formData.append(key, form[key]);
    });

    if (files instanceof Array) {
        for (let i = 0; i < files.length; i++) {
            formData.append('file', files[i].raw);
        }
    } else {
        formData.append('file', files);
    }

    let failFunc=function (data){
        if (typeof failFun == 'function') {
            failFun(error);
        }
    }

    request.post(url, formData, config)
        .then(successFun)
        .catch(function (error) {
            hiddenWaiting();
            alertError(error.toString());
            failFunc(error);
            return;
        })
}

/*分页查询*/
function pageQuery(options, succFun, failFun) {

    if (options == null || typeof (options) == "undefined") {
        alertError("pageQuery 错误，参数options不可为空。");
        return;
    } else {
        if (isNull(options.queryNo)) {
            alertError("pageQuery 错误，参数queryNo不可为空。");
            return;
        }
        if (isNull(options.queryWindow)) {
            options.queryWindow = "1";
        }
        if (isNull(options.pageSize)) {
            options.pageSize = 30;
        }
        if (isNull(options.pageIndex)) {
            alertError("pageQuery 错误，参数pageIndex不可为空。");
            return;
        }
        if (isNull(options.whereCondition)) {
            options.whereCondition = "";
        } else {
            options.whereCondition = encryptAES(options.whereCondition);
        }
        if (isNull(options.orderCondition)) {
            options.orderCondition = "";
        } else {
            options.orderCondition = encryptAES(options.orderCondition);
        }
        if (isNull(options.parameters)) {
            options.parameters = null;
        }
    }
    let url = "/wechat/common/pagegrid.action";
    submitTargetForm({url: url, form: options, actionFunName: "query", succFun: succFun, failFun: failFun});
}


/*从缓存中实时读取数据字典*/
function loadDicItemSync(dicId) {
    if (isNull(dicId)) {
        alertError("loadDicItem 错误，参数dicId不可为空。");
        return;
    }
    return getStore({name: "dicId:" + dicId});
}

/*读数据字典*/
function loadDicItem(options, succFun, failFun) {
    if (options == null || typeof (options) == "undefined") {
        alertError("loadDicItem 错误，参数options不可为空。");
        return;
    } else {
        if (isNull(options.dicId)) {
            alertError("loadDicItem 错误，参数dicId不可为空。");
            return;
        }
        if (isNull(options.dicListGroup)) {
            options.dicListGroup = "";
        }
        if (isNull(options.language)) {
            options.language = "";
        }
        if (isNull(options.cache)) {
            options.cache = true;
        }
        if (options.cache != true) {
            options.cache = false;
        }
    }

    if (options.cache) {
        let value = loadDicItemSync(options.dicId);
        if (isNotNull(value) && isArray(value)) {
            if (typeof (succFun) == "function") {
                succFun(value);
            }
            return;
        }
    }
    let url = "/wechat/common/dictionary.action";
    let backFun = function (data) {
        if (options.dicListGroup === "") {
            if (isNotNull(data) && data.length > 0) {
                setStore({name: "dicId:" + options.dicId, content: data});
            }
        }
        if (typeof (succFun) == "function") {
            succFun(data);
        }
    };
    submitTargetForm({url: url, form: options, actionFunName: "loadDicItem", succFun: backFun, failFun: failFun});
}

/*查询数据字典*/
function selectDicItem(dicId, key) {
    if (isNull(dicId) || isNull(key)) {
        return "";
    }
    let tempDic = loadDicItemSync(dicId);
    if (isNotNull(tempDic)) {
        for (let id in tempDic) {
            let item = tempDic[id];
            if (item.code === key) {
                return item.name;
            }
        }
        return "";
    } else {
        return "";
    }
}

function formatURL(url) {
    let baseUrl = constData.baseUrl;
    if (ELECTRON_FLAG) {
        baseUrl = constData.electronBaseUrl;
    }

    if (url.indexOf("http:") == 0 || url.indexOf("https:") == 0) {

    } else if (url.indexOf("//") == 0) {
        url = url.substring(1);
    } else {
        if (url.indexOf("/") == 0) {
            url = baseUrl + url;
        } else {
            url = baseUrl + "/" + url;
        }
    }
    return url;
}

/**
 * 导出查询组件所选择内容
 */
function exportFileByWidget({widget, expType, fileType, compartlCompart}) {
    if (widget == null || typeof (widget) == "undefined") {
        alertError("widget 入参不能为空！");
        return;
    }
    if (fileType != "txt") {
        fileType = "excel";
    }
    if (expType != "config") {
        expType = "all";
    }
    var sql = widget.getCheckSQL();
    //var sql = widget.getQuerySQL();
    if (expType == "config") {
        var callbackFun = function (params) {
            if (params != null && params != "" && typeof (params) != "undefined") {
                var form1 = {
                    queryNo: params.queryNo,
                    queryWindow: params.queryWindow,
                    sysQueryList: params.sysQueryList,
                    sql: sql,
                    fileType: fileType,
                    compart: compart,
                    lCompart: lCompart
                };
                var callBackFun = function () {
                    var expFileName = form1.expFileName;
                    if (expFileName != null && expFileName != "") {
                        var url = root + "/frame/systemmanagement/querymanagement/querylist.action?frameControlSubmitFunction=download&expFileName=" + encodeURIComponent(expFileName) + "&winTemp=" + Math.random() * (10000);
                        window.location.href = url;
                    }
                };
                this.submitTargetForm({
                    url: "/frame/systemmanagement/querymanagement/export/exportcfg.action",
                    form: form1,
                    actionFunName: "exportFile",
                    succFun: callBackFun,
                    failFun: null
                });
            }
        };
        //$scope.frameCommonSelect.exportCfg({queryNo: widget.getQueryNo(), queryWindow: widget.getQueryWindow(), callBackFun: callbackFun});
        // this.openDialog({
        //     title: "导出配置",
        //     view: "frame/systemmanagement/querymanagement/export/exportcfg.action?frameControlSubmitFunction=init&queryWindow=" + encodeURIComponent(widget.getQueryWindow()) + "&queryNo=" + encodeURIComponent(widget.getQueryNo()),
        //     width: "600px",
        //     height: "600px",
        //     callBackFun: callbackFun
        // });

    } else {
        var form2 = {
            queryNo: widget.getQueryNo(),
            queryWindow: widget.getQueryWindow(),
            sql: sql,
            fileType: fileType
        };
        var callBackFun = function () {
            var expFileName = form2.expFileName;
            if (isNotNull(expFileName)) {
                var url = constData.baseUrl + "/frame/systemmanagement/querymanagement/querylist.action?frameControlSubmitFunction=download&expFileName=" + encodeURIComponent(expFileName) + "&winTemp=" + Math.random() * (10000);
                // window.open(url);
                window.location.href = url;
            }
        };
        this.submitTargetForm({
            url: "/frame/systemmanagement/querymanagement/export/exportcfg.action",
            form: form2,
            actionFunName: "exportFile",
            succFun: callBackFun,
            failFun: null
        });
    }
}

function randomString(len) {
    len = len || 32;
    var $chars = 'QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (var i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function callSysFun({funName, params, callBackFun}) {
    let tempFunName = "callFun" + funName + randomString(16);
    let callBack = function (data) {
        if (typeof (callBackFun) == "function") {
            if (data.code) {
                if (data.code == "200") {
                    data = data.data;
                    callBackFun(data);
                } else {
                    alertMessage(data.msg);
                    return;
                }
            }
        }
    }
    eval("window." + tempFunName + "=callBack");
    if (window.callSysFunc) {
        window.callSysFunc({
            funName: funName,
            params: params,
            callbackFun: tempFunName
        });
    }
}

//格式化Money
function formatMoney(number, isnull = true, decimals = 2, decPoint = '.', thousandsSep = ',') {
    isnull = (typeof isnull === 'undefined') ? true : isnull
    if (isNull(number) && isnull) {
        return '';
    }
    number = (number + '').replace(/[^0-9+-Ee.]/g, '')
    let n = !isFinite(+number) ? 0 : +number
    let prec = !isFinite(+decimals) ? 0 : Math.abs(decimals)
    let sep = (typeof thousandsSep === 'undefined') ? ',' : thousandsSep
    let dec = (typeof decPoint === 'undefined') ? '.' : decPoint
    let s = ''
    let toFixedFix = function (n, prec) {
        let k = Math.pow(10, prec);
        return '' + accDiv(Math.round(accMul(n , k)) , k);
    }
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.')
    let re = /(-?\d+)(\d{3})/
    if(isNotNull(sep)){
        while (re.test(s[0])) {
            s[0] = s[0].replace(re, '$1' + sep + '$2')
        }
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || ''
        s[1] += new Array(prec - s[1].length + 1).join('0')
    }
    return s.join(dec)
}

// 两个浮点数求和
function accAdd(num1, num2) {
    var r1, r2, m;
    try {
        r1 = num1.toString().split('.')[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = num2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    // return (num1*m+num2*m)/m;
    return Math.round(num1 * m + num2 * m) / m;
}

// 两个浮点数相减
function accSub(num1, num2) {
    var r1, r2, m;
    try {
        r1 = num1.toString().split('.')[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = num2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    var n = (r1 >= r2) ? r1 : r2;
    return (Math.round(num1 * m - num2 * m) / m).toFixed(n);
}

// 两数相除
function accDiv(num1, num2) {
    var t1, t2, r1, r2;
    try {
        t1 = num1.toString().split('.')[1].length;
    } catch (e) {
        t1 = 0;
    }
    try {
        t2 = num2.toString().split(".")[1].length;
    } catch (e) {
        t2 = 0;
    }
    r1 = Number(num1.toString().replace(".", ""));
    r2 = Number(num2.toString().replace(".", ""));
    return (r1 / r2) * Math.pow(10, t2 - t1);
}

// 两数相乘
function accMul(num1, num2) {
    var m = 0,
        s1 = num1.toString(),
        s2 = num2.toString();
    try {
        m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
        m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}

export default {
    isNull: isNull,
    isNotNull: isNotNull,
    isArray: isArray,
    isListEmpty: isListEmpty,
    isFunction: isFunction,
    extend: extend,
    showToast: showToast,
    showToastError: showToastError,
    alertMessage: alertMessage,
    alertError: alertError,
    confirm: confirm,
    showWaiting: showWaiting,
    hiddenWaiting: hiddenWaiting,
    uuid: uuid,
    encryptAES: encryptAES,
    submitTargetForm: submitTargetForm,
    submitTargetFormSync: submitTargetFormSync,
    submitTargetFileForm: submitTargetFileForm,
    loadDicItemSync: loadDicItemSync,
    loadDicItem: loadDicItem,
    selectDicItem: selectDicItem,
    pageQuery: pageQuery,
    exportFileByWidget: exportFileByWidget,
    callSysFun: callSysFun,
    formatMoney: formatMoney,
    accAdd: accAdd,
    accSub: accSub,
    accDiv: accDiv,
    accMul: accMul,
};


export var BaseCtrl = {
    inject: ["index"],

    methods: {
        isNull: isNull,
        isNotNull: isNotNull,
        isArray: isArray,
        isListEmpty: isListEmpty,
        isFunction: isFunction,
        extend: extend,
        showToast: showToast,
        showToastError: showToastError,
        alertMessage: alertMessage,
        alertError: alertError,
        confirm: confirm,
        showWaiting: showWaiting,
        hiddenWaiting: hiddenWaiting,
        uuid: uuid,
        encryptAES: encryptAES,
        submitTargetForm: submitTargetForm,
        submitTargetFormSync: submitTargetFormSync,
        submitTargetFileForm: submitTargetFileForm,
        loadDicItemSync: loadDicItemSync,
        loadDicItem: loadDicItem,
        selectDicItem: selectDicItem,
        pageQuery: pageQuery,
        exportFileByWidget: exportFileByWidget,
        callSysFun: callSysFun,
        formatMoney: formatMoney,
        accAdd: accAdd,
        accSub: accSub,
        accDiv: accDiv,
        accMul: accMul,
        /*打开MainTag*/
        openMainTag: function ({id, title, view, reloadFlag, iframeFlag, params}) {

            if (isNull(reloadFlag)) {
                reloadFlag = true;
            } else {
                if (reloadFlag == 1 || reloadFlag == '1' || reloadFlag || reloadFlag == 'y') {
                    reloadFlag = true;
                } else {
                    reloadFlag = false;
                }
            }

            if (isNull(title)) {
                alertError("");
                return;
            }
            if (isNull(view)) {
                alertError("");
                return;
            }
            if (isNull(id)) {
                id = uuid();
            }
            if (id.indexOf("/") < 0) {
                id = "/" + id;
            }

            let root = "";
            let url = view;
            if (url.indexOf("http://") == 0) {
                iframeFlag = true;
                root = "http://";
                url = url.substring(7);
            } else if (url.indexOf("https://") == 0) {
                iframeFlag = true;
                root = "https://";
                url = url.substring(8);
            } else {
                iframeFlag = false;
            }
            url = url.replace(/\/\//g, "/");
            if (url.indexOf("//") == 0) {
                url = url.substring(2);
            } else if (url.indexOf("@/") == 0) {
                url = url.substring(2);
            } else if (url.indexOf("/") == 0) {
                url = url.substring(1);
            }
            url = root + url;
            let router = null;
            if (iframeFlag) {
                router = [{
                    children: [],
                    path: id,
                    icon: "icon-caidan",
                    label: title,
                    meta: {
                        i18n: title,
                        keepAlive: reloadFlag
                    },
                    component: "",
                    parentId: null
                }];
            } else {
                router = [{
                    children: [],
                    path: id,
                    icon: "icon-caidan",
                    label: title,
                    meta: {
                        i18n: title,
                        keepAlive: reloadFlag
                    },
                    component: url,
                    parentId: null
                }];
            }

            this.$router.$avueRouter.formatRoutes(router, true);

            this.$router.push({
                path: id,
                query: params
            });
        },
        /*打开菜单*/
        openMenu: function ({menuNo, params}) {
            //查询路径
            this.$router.push({
                path: menuNo,
                query: params
            });
        },

        /*打开Window*/
        openWindow: function (params) {
            this.openDialog(params);
        },

        // /*打开Dialog*/
        // openDialog: function (params) {
        //     let edialog = this.index.$refs['edialog'];
        //     edialog.showDialog(params);
        // },

        // /*打开Dialog*/,目前支持打开两层
        openDialog: function (params) {
            let edialog1 = this.index.$refs['edialog1'];
            let edialog2 = this.index.$refs['edialog2'];
            if (edialog1.visible === false) {
                edialog1.showDialog(params);
                return;
            } else if (edialog2.visible === false) {
                edialog2.showDialog(params);
                return;
            } else {
                this.openDialog(params);
                return;
            }
            // edialog.showDialog(params);
        },

        // 打开窗口,kendo窗口
        // openWindow :function(params){
        //     var height = params.height
        //     var width = params.width
        //     var title = params.title
        //     var view = params.view
        //     var callBackFun = params.callBackFun
        //     var windowParams = params.params
        //     var modalFlag = params.modalFlag
        //     var tempWindowId = "win_" + Math.ceil(Math.random() * 100000000)
        //     var tempParam = "view" + Math.ceil(Math.random() * 100000000)
        //     view = formatURL(view)
        //     if (view.indexOf("?") >= 0) {
        //         view = view + "&winTemp=" + Math.random() * (10000);
        //     } else {
        //         view = view + "?winTemp=" + Math.random() * (10000);
        //     }
        //     view = view + "#" + tempWindowId;
        //     console.log("view=====",view)
        //
        //     // var $div = $("<div  role='window' class=\"wait-main " + tempWindowId + "  \"  style=\"height: 100%; width: 100%; background-color:#f5f5f5; overflow:hidden;  box-sizing:inherit; -webkit-box-sizing:inherit;   position: relative; \"  >  <div class=\"wait-div\" style=\" position: absolute; display:  none; text-align:center; top: 0; right: 0; bottom: 0;  left: 0; z-index: 2999; width:100%;height: 100%;box-sizing: border-box;   \" > <i class=\"fa fa-3x fa-sun-o fa-spin  text-primary\" style=\" position: absolute;left:50% ;top:50%\"></i></div> <div style=\"height: 100%; width: 100%;  overflow: auto;\"  class=\"mainTabContent  \"     ><div  style=\"height: 100%; width: 100%;\"  data-ng-include=\"" + tempParam + "\"   data-autoscroll='true'  /></div></div>");
        //     var $div = $("<div  role='window'  class=\"wait-main " + tempWindowId + " \"  style=\"height: 100%; width: 100%; background-color:#f5f5f5; overflow:hidden;  box-sizing:inherit; -webkit-box-sizing:inherit;   position: relative; \"  >  <div class=\"wait-div\" style=\" position: absolute; display:  none; text-align:center; top: 0; right: 0; bottom: 0;  left: 0; z-index: 2999; width:100%;height: 100%;box-sizing: border-box;   \" > <i class=\"fa fa-3x fa-sun-o fa-spin  text-primary\" style=\" position: absolute;left:50% ;top:50%\"></i></div> <div style=\"height: 100%; width: 100%;  overflow: hiden;\"  class=\"mainTabContent  \"     ><iframe style=\"width:100%; height:100%;border:0px;\" src=\"" + view + "\"></iframe></div>")
        //     $div.kendoWindow({
        //         actions: ["Maximize", "Close"],
        //         animation: {
        //             open: {
        //                 //effects: "fade:in"
        //                 effects: "slideIn:down fadeIn"
        //             },
        //             close: {
        //                 effects: "slide:up fadeOut"
        //             }
        //         },
        //         height: height ? height : "600px",
        //         title: title ? title : "",
        //         close: function (e) {
        //             if (this.windowClose) {
        //             } else {
        //                 if (typeof (this.callBackFun) == "function") {
        //                     this.callBackFun();
        //                 }
        //             }
        //             this.destroy();
        //         },
        //         modal: modalFlag,
        //         width: width ? width : "1000px"
        //     });
        //     $div.data("windowParams", windowParams);
        //     var window = $div.data("kendoWindow");
        //     window.center();
        //     window.open();
        //     if (typeof (callBackFun) == 'function') {
        //         window.callBackFun = callBackFun;
        //     }
        // },


        /*抽出slideReveal，目前支持两层抽出。第一层已抽出后默认自动第二次抽出；第二层已抽出后，默认打开弹窗*/
        slideReveal: function (params) {
            let edrawer1 = this.index.$refs['edrawer1'];
            let edrawer2 = this.index.$refs['edrawer2'];
            if (edrawer1.visible === false) {
                edrawer1.showDrawer(params);
                return;
            } else if (edrawer2.visible === false) {
                edrawer2.showDrawer(params);
                return;
            } else {
                this.openDialog(params);
                return;
            }
        },

        /*关闭Dialog*/
        close: function (params) {
            try {
                let componentType = this.$parent.$parent.componentType;
                if (componentType === 'EfDrawer') {
                    this.$parent.$parent.close(params);
                    return;
                }
                if (componentType === 'EfDialog') {
                    this.$parent.$parent.close(params);
                    return;
                }
            } catch (e) {
                return;
            }
        },

        /*打开ShowLoading*/
        openShowLoading: function (params) {
            let eshowloading = this.index.$refs['eshowloading'];
            eshowloading.openEfShowLoading(params);
        },

        /*关闭ShowLoading*/
        closeShowLoading: function (params) {
            let eshowloading = this.index.$refs['eshowloading'];
            eshowloading.closeEfShowLoading(params);
        },
    }
};
