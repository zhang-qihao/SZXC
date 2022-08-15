//医保JS
import eframe from '/src/util/eframe';
import website from '../config/website';
import store from "/src/store/index";

const ip = "10.58.70.177"; //医保测试http://10.58.70.177:8086/CSB
// const ip = "10.61.152.33"; //医保正式http://10.61.152.33:8086/CSB
const port = "8086";
const ec_url = "http://10.72.42.210:10081/test/localcfc/api/hsecfc/localQrCodeQuery";   //测试
// const ec_url = "http://10.58.33.207:10086/localcfc/api/hsecfc/localQrCodeQuery";  //正式

//医保初始化
export function callInit() {
    try {
        eframe.showWaiting();
        let userInfo = store.state.user.userInfo;
        let form = {
            ip: ip, //服务端IP地址
            port: port,	//服务端端口
            card_passtype: "1",	//社保卡验证密码方式
            ec_url: ec_url,	//电子凭证中台URL
            api_name: userInfo.api_name,	//CSB的_api_name
            api_version: userInfo.api_version,	//CSB的api_version
            access_key: userInfo.api_access_key,	//CSB的api_access_key
            secretkey: userInfo.api_secret_key,	//CSB的secretKey
            org_id: userInfo.fixmedins_code,	//定点编号
            area_code: userInfo.fix_blng_admdvs,	//定点所属行政区划代码
            ext: "",	//JSON对象字符串
        };
        console.log("医保初始化开始--" + JSON.stringify(form));
        let callBackFun = function (data) {
            eframe.hiddenWaiting();
            console.log("医保初始化结束：" + JSON.stringify(data));
            if (data.result !== 0) {
                eframe.alertError("医保初始化失败：" + JSON.stringify(data.pErrMsg));
            } else {
                eframe.showToast("医保初始化成功");
            }
        };
        eframe.callSysFun({
            funName: 'init',
            params: form,
            callBackFun: callBackFun,
        });
    } catch (e) {
        eframe.alertError("医保初始化失败：" + e.message);
    } finally {
        eframe.hiddenWaiting();
    }

}

//读卡
export function readCardInfo(params, succFun, failFun) {
    console.log("读卡开始-------");
    try {
        let callBackFun = function (data) {
            console.log("读卡结束：" + JSON.stringify(data));
            if (data.result !== 0) {
                eframe.alertError("医保卡读卡失败：" + data.pCardInfo);
                if (typeof failFun == 'function') {
                    failFun(data);
                }
            } else {
                let info = data.pCardInfo.split("|");
                let psnInfo = {
                    si_card_issue_area: info[0],//发卡地区行政区划代码
                    card_sn: info[1],//社会保障卡卡号
                    cardId: info[2],//卡号
                    si_card_no: info[3],//卡识别码
                    psn_name: info[4],//姓名
                    resetInfo: info[5],//卡复位信息
                    version: info[6],//规范版本
                    beginDate: info[7],//发卡日期
                    endDate: info[8],//卡有效期
                    devCode: info[9],//终端机编号
                    devNo: info[10],//终端设备号
                    pBusiCardInfo: data.pBusiCardInfo,//业务卡串
                    mdtrt_cert_type: "03",
                }
                psnInfo.mdtrt_cert_no = psnInfo.cardId + '|' + psnInfo.pBusiCardInfo;//就诊凭证编号
                psnInfo.certno = psnInfo.card_sn;//卡号-证件号
                psnInfo.psn_cert_type = "90";//证件类型
                psnInfo.fixmedins_id = params.fixmedins_id;//机构ID
                let funName = "readCardInfo";
                if (eframe.isNotNull(params.isSave) && params.isSave) {
                    funName = "readCardAndSave";
                }
                eframe.submitTargetForm({
                    url: '/ehis/settlementmgmt/psnmgmt/psnInfo.action',
                    form: psnInfo,
                    actionFunName: funName,
                    succFun: succFun,
                    failFun: failFun
                });
            }
        };
        if (website.isYB) {
            eframe.callSysFun({
                funName: 'readCardBas',
                params: {},
                callBackFun: callBackFun,
            });
        } else {
            // TODO 模拟
            let data = {
                result: 0,
                pCardInfo: "320500|410305199311122044|183760642|320500D1560000…DCC75B|2.00|20201019||320500902398||JSE165319722|",
                pBusiCardInfo: "twCSljjR1u2h6rS3gRDe3D+G9rHAf+A77BOAAJlY54uYHlwKHN…PSS+/l2gi31AxWdqALHxNPM821OouBoCoeeQJrSKr0uF42kc="
            }
            callBackFun(data);
        }
    } catch (e) {
        eframe.alertError("医保卡读卡失败：" + e.message);
        if (typeof failFun == 'function') {
            failFun();
        }
    } finally {

    }

}

//电子社保卡
export function ecCardInfo(params, succFun, failFun) {
    console.log("电子社保卡扫码开始-------")
    try {
        let callBackFun = function (data) {
            console.log("电子社保卡扫码结束：" + JSON.stringify(data));
            if (data.result !== 0 || JSON.parse(data.pOutData).code != 0) {
                eframe.alertError("电子社保卡解码失败：" + JSON.stringify(data.pOutData));
                if (typeof failFun == 'function') {
                    failFun(data);
                }
            } else {
                let info = JSON.parse(data.pOutData).data;
                let psnInfo = {
                    card_sn: info.si_no,//社会保障卡卡号(社会保障号码)
                    si_card_no: info.si_card_no,//卡识别码(人员识别号)
                    si_card_issue_area: info.si_card_issue_area,//发卡地区行政区划代码
                    psn_name: info.name,//姓名
                    gender: info.gender,//性别
                    cardId: info.id_no,//卡号（证件号码）
                    psn_cert_type: info.id_type,//证件类型
                    mdtrt_cert_type: "04", //就诊凭证类型
                    ecToken: info.ecCardToken,//令牌
                }
                psnInfo.mdtrt_cert_no = psnInfo.cardId + "|" + psnInfo.ecToken;//就诊凭证编号
                psnInfo.certno = psnInfo.cardId;//卡号-证件号
                psnInfo.fixmedins_id = params.fixmedins_id;//机构ID

                let funName = "readCardInfo";
                if (eframe.isNotNull(params.isSave) && params.isSave) {
                    funName = "readCardAndSave";
                }
                eframe.submitTargetForm({
                    url: '/ehis/settlementmgmt/psnmgmt/psnInfo.action',
                    form: psnInfo,
                    actionFunName: funName,
                    succFun: succFun,
                    failFun: failFun
                });
            }
        };
        if (website.isYB) {
            eframe.callSysFun({
                funName: 'ecCardQuery',
                params: {},
                callBackFun: callBackFun,
            });
        } else {
            //TODO 模拟
            let data = {
                result: 0,
                pOutData: {
                    "code": 0,
                    "message": "",
                    "data": {
                        "si_no": "320981199804163722",
                        "si_card_no": "JSJ048625595",
                        "si_card_issue_area": "320900",
                        "name": "臧莉",
                        "gender": "2",
                        "id_type": "01",
                        "id_no": "320981199804163722",
                        "ecCardToken": "o/2NdFs1ir+7WzI+2z9iNva526mq/7TQW2ipQ2HPVF2GZMg1Fc+zLDqStmIdW/JOLXP/UltfvrU4FK//MxEDVTjhRKkBi9EsWlD9KZucYuge3J8yOA/fJR1mjiXrg5Rl/PRVpLSH9U2G2S3FHbJAlQ=="
                    }
                },
            }
            callBackFun(data);
        }
    } catch (e) {
        eframe.alertError("电子社保卡扫码失败：" + e.message);
        if (typeof failFun == 'function') {
            failFun();
        }
    } finally {

    }
}

//电子医保凭证
export function ecInfo(params, succFun, failFun) {
    try {
        let form = {
            orgId: params.fixmedins_code,//机构ID
            businessType: params.businessType,//用码业务类型
            operatorId: params.psn_code,//收款员编号
            operatorName: params.psn_name,//收款员姓名
            officeId: params.caty,//医保科室编号
            officeName: params.dept_name,//科室名称
        };
        console.log("电子医保凭证扫码开始--" + JSON.stringify(form))
        let callBackFun = function (data) {
            console.log("电子医保凭证扫码结束：" + JSON.stringify(data));
            if (data.result !== 0 || data.pOutData.code !== 0) {
                eframe.alertError("电子医保凭证解码失败：" + JSON.stringify(data.pOutData.message));
                if (typeof failFun == 'function') {
                    failFun(data);
                }
            } else {
                let info = data.pOutData.data;
                let psnInfo = {
                    psn_name: info.userName,//姓名
                    psn_cert_type: info.idType,//证件类型
                    ecToken: info.ecToken,//令牌
                    insuOrg: info.insuOrg,//参保地区编码
                    certno: info.idNo,//身份证号-证件号码
                    ecIndexNo: info.ecIndexNo,//电子凭证索引号
                    gender: info.gender,//性别
                    brdy: info.birthday,//出生日期
                    nationality: info.nationality,//国籍
                    email: info.email,//邮箱
                    mdtrt_cert_type: "01",
                    mdtrt_cert_no: info.ecToken, //电子凭证令牌
                }
                psnInfo.fixmedins_id = params.fixmedins_id;//机构ID

                let funName = "readCardInfo";
                if (eframe.isNotNull(params.isSave) && params.isSave) {
                    funName = "readCardAndSave";
                }
                eframe.submitTargetForm({
                    url: '/ehis/settlementmgmt/psnmgmt/psnInfo.action',
                    form: psnInfo,
                    actionFunName: funName,
                    succFun: succFun,
                    failFun: failFun
                });
            }
        };
        if (website.isYB) {
            eframe.callSysFun({
                funName: 'ecQuery',
                params: {
                    orgId: params.fixmedins_code,//机构ID
                    businessType: params.businessType,//用码业务类型
                    operatorId: params.userId,//收款员编号
                    operatorName: params.userName,//收款员姓名
                    officeId: '',//医保科室编号
                    officeName: '',//科室名称
                },
                callBackFun: callBackFun,
            });
        } else {
            //TODO 模拟
            let data = {
                "result": 0,
                "pOutData": {
                    "code": 0,
                    "data": {
                        "authNo": null,
                        "birthday": null,
                        "chnlId": null,
                        "ecIndexNo": "6C03F0CDD8C35F17D336F01D2EFAEF5A",
                        "ecQrCode": null,
                        "ecToken": "320000ecnqssaor2nj7a2a480a0000a4cef57c",
                        "email": null,
                        "gender": null,
                        "idNo": "37030519870924532X",
                        "idType": "01",
                        "insuOrg": "320500",
                        "nationality": null,
                        "userName": "王娜娜"
                    },
                    "message": "交易成功",
                    "orgId": ""
                }
            }
            callBackFun(data);
        }
    } catch (e) {
        eframe.alertError("电子医保凭证扫码失败：" + e.message);
        if (typeof failFun == 'function') {
            failFun();
        }
    } finally {

    }

}


//根据身份证获取参保信息
export function idCardTest(params, succFun, failFun) {
    console.log("根据身份证获取参保信息开始-------")
    try {
        console.log(JSON.stringify(params));
        if (eframe.isNull(params.idNo)) {
            eframe.alertError("身份证不能为空");
            failFun();
        } else {
            let psnInfo = {
                idNo: params.idNo,//身份证号
                mdtrt_cert_type: "02",
                mdtrt_cert_no: params.idNo,//就诊凭证编号-身份证号码
                fixmedins_id: params.fixmedins_id,//机构ID
            }
            let funName = "readCardInfo";
            if (eframe.isNotNull(params.isSave) && params.isSave) {
                funName = "readCardAndSave";
            }
            eframe.submitTargetForm({
                url: '/ehis/settlementmgmt/psnmgmt/psnInfo.action',
                form: psnInfo,
                actionFunName: funName,
                succFun: succFun,
                failFun: failFun
            });
        }
    } catch (e) {
        eframe.alertError("根据身份证获取参保信息失败：" + e.message);
        if (typeof failFun == 'function') {
            failFun();
        }
    } finally {

    }
}

export default {
    callInit: callInit,   //医保初始化
    readCardInfo: readCardInfo,//读卡
    ecCardInfo: ecCardInfo,//电子社保卡
    ecInfo: ecInfo,//电子医保凭证
    idCardTest: idCardTest,//身份证获取参保信息
}