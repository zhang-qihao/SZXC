package com.xfinsoft.szybgzdtgl.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static String DATABASE_USERNAME = "zjgyth";          //数据库用户
    public static String FRAME_USERNAME = "eframe";          //数据库用户
    public static String DATABASE_USERNAME_ZJGCSI = "zjgcsi";          //数据库用户
    public static String DATABASE_USERNAME_YB = "YB";   //医保用户
    public static String PROGRAMNO = "ZJGYTH";          //系统编号


    public static String AAB301_ZJG = "320582";                 //行政区划代码（张家港）
    public static String AAA027_ZJG = "320582";                 //统筹区编码（张家港）
    public static String CAC205_ZJG = "320582";                 //户籍所属区/县（市）（张家港）
    public static String STORE_PROCEDURE_SCHEME = "ssjmyl";     //到龄补缴用户
    public static String TAX_DATABASE_USERNAME = "dspt";    //地税平台用户

    //面试官短信推送信息
    public static String INTERVIEWER_PLATFORMID = "8280819b6f3ae478016f3b0cf6e30002";       //平台ID
    public static String INTERVIEWER_SENDFLAG_0 = "0"; //发送标志：0：实时发送，
    public static String INTERVIEWER_SENDFLAG_1 = "1"; //发送标志：1：定时发送 (暂未实现)
    public static String INTERVIEWER_SENDDINGTIME_0 = "0";//发送时段:0：任何时间
    public static String INTERVIEWER_SENDDINGTIME_1 = "1";//发送时段:1：工作时间
    public static String INTERVIEWER_SENDTYPE_0 = "0";//发送类型:0：短信息
    public static String INTERVIEWER_SENDTYPE_1 = "1";//发送类型:1：公众号
    public static String INTERVIEWER_SENDTYPE_2 = "2";//发送类型:2：邮件
    public static String INTERVIEWER_MSTYPE_0 = "0";//消息类型:0：模板发送
    public static String INTERVIEWER_MSTYPE_1 = "1";//消息类型:1：文本发送

    //PDF服务器
    public static final String PDF_IP = "172.168.6.106";
    public static final Integer PDF_PORT = 8001;
    public static final String PDF_BUSSINESSNO = "PDF100";
    public static final String PDF_TIMEOUT = "60";


    public static String COMM_YES = "1";     //通用常量，1 有效/勾选/是
    public static String COMM_NO = "0";     //通用常量   0 无效/未选/否
    public static String LOCALFLAG_1 = "1";    //本地
    public static String LOCALFLAG_2 = "2";    //外地

    //-----------------------接口交易号常量-----------------------------
    public static String BUSINESSNO_LEVYHANDLE = "LevyHandle";   //财务处理（共享平台）
    public static String BUSINESSNO_FAILURE = "Failure";   //参保登记数据失败重发（发税务）

    //-----------------------接口方法常量-----------------------------
    public static String METHOD_PREPARESETTLEMENT = "prepareSettlement"; //财务预结算（共享平台）
    public static String METHOD_SUMMARYCONFIRM = "summaryConfirm"; //财务确认（共享平台）


    //-----------------------地税信息发送字段-----------------------------
    public static String YWLXBM = "";                       //业务类型编码
    public static String YWXTBM = "3205823000";             //业务系统编码
    public static String XH = "CBXX00301";                       //业务类型编码

    public static String SBJBJGH = "23205820001";           //社保经办机构号
    public static String XTBM_SBUUID = "3205823000";       //系统编码—SBUUID
    public static String CBRYTSLB = "00000000003205820001";                    //人员参保特殊类别
    public static String HXBZ = "N";                         //回写标志

    public static String AAA321_1 = "1";                  //数据传输类型 新增
    public static String AAA321_2 = "2";                  //数据传输类型 变更
    public static String AAA321_3 = "3";                  //数据传输类型 删除
    public static String AAA321_4 = "4";                  //数据传输类型 终止
    public static String AAA321_5 = "5";                  //数据传输类型 延期
    public static String AAA321_6 = "6";                  //数据传输类型 撤销
    public static String AAA321_9 = "9";                  //数据传输类型 差异反馈

    public static String AAB065_1 = "1";                  //特殊单位类别代码 特困企业
    public static String AAB065_2 = "2";                  //特殊单位类别代码 个体工商户(新增)
    public static String AAB065_9 = "9";                  //特殊单位类别代码 其他

    public static String AAC008_0 = "0";                    //人员参保状态 未参保
    public static String AAC008_2 = "2";                    //人员参保状态 中断
    public static String AAC008_3 = "3";                    //人员参保状态 中断 -- 发税务
    public static String AAC008_1 = "1";                    //人员参保状态 正常参保
    public static String AAC008_4 = "4";                    //人员参保状态 终止参保

    public static String AAC031_0 = "0";                    //人员缴费状态 未缴费
    public static String AAC031_1 = "1";                    //人员缴费状态 参保缴费
    public static String AAC031_2 = "2";                    //人员缴费状态 中断缴费
    public static String AAC031_3 = "3";                    //人员缴费状态 终止缴费

    public static String CAE265_0 = "0";                    //最终关系所在标志 否
    public static String CAE265_1 = "1";                    //最终关系所在标志 是

    /*public static  String AAB051_0 = "0";                  //单位参保缴费状态 未缴*/
    /*public static  String AAB051_1 = "1";                  //单位参保缴费状态 已缴*/

    /**
     * 未审核
     */
    public static String JCA1_CJC024_0 = "0";
    /**
     * 审核通过
     */
    public static String JCA1_CJC024_1 = "1";
    /**
     * 审核不通过
     */
    public static String JCA1_CJC024_2 = "2";

    /**
     * 导入数据处理状态 ：初始
     */
    public static String LCA1_CJC024_0="0";

    /**
     * 导入数据处理状态 ：比对成功
     */
    public static String LCA1_CJC024_1="1";

    /**
     * 导入数据处理状态 ：比对失败
     */
    public static String LCA1_CJC024_2="2";

    /**
     * 缴纳医保比对标志 0-未比对
     */
    public static String CJC024_0 = "0";
    /**
     * 缴纳医保比对标志 1-比对成功
     */
    public static String CJC024_1 = "1";
    /**
     * 缴纳医保比对标志 2-比对失败
     */
    public static String CJC024_2 = "2";
    /**
     * 结算标志 0-未结算
     */
    public static String CJC025_0 = "0";
    /**
     * 结算标志 1-已结算
     */
    public static String CJC025_1 = "1";


    //------------------------------------------------------------------
    /**
     * 二维码扫码
     */
    //扫码类型
    public static final String SCANTYPE_LOGIN_1 = "1";                    //登陆扫码
    public static final String SCANTYPE_CHECK_2 = "2";                    //审核扫码

    //签名标识
    public static final String SIGNTYPE_NOTSCAN_0 = "0";                    //未扫
    public static final String SIGNTYPE_SCAN_1 = "1";                    //已扫

    //扫码结果
    public static final String SIGNRESULT_WAIT_0 = "0";                    //等待
    public static final String SIGNRESULT_SUCCESS_1 = "1";                    //成功
    public static final String SIGNRESULT_FAIL_2 = "2";                    //失败

    /***
     * 年度缴费申报,到龄补缴申报删除
     *
     */
    public static final String SCZLX_0001 = "0001";             //0001-正常缴
    public static final String SCZLX_0002 = "0002";             //0002-补缴


    /***
     * 年龄校验
     */
    public final static String CHECKAGE_0 = "0";    //正常
    public final static String CHECKAGE_1 = "1";    //超龄
    public final static String CHECKAGE_2 = "2";    //到龄
    public final static String CHECKAGE_3 = "3";    //未成年


    /**
     * 单位参保类别
     */
    public final static String CAB085_110 = "110";          //城镇职工参保单位
    //    public final static String CAB085_210 = "210";          //居民养老单位
//    public final static String CAB085_310 = "310";          //居民医疗单位
    public final static String CAB085_610 = "610";          //村社区
    public final static String CAB085_410 = "410";          //建筑工伤单位
    public final static String CAB085_510 = "510";          //征土工养老单位


    /**
     * 单位参保状态
     */
    public final static String AAB051_1 = "1";              //单位参保状态 -- 已参保
    public final static String AAB051_0 = "0";              //单位参保状态 -- 未参保
    public final static String AAB051_2 = "2";              //单位参保状态 -- 中断参保
    public final static String AAB051_3 = "3";              //单位参保状态 -- 终止参保


    /***
     * 单位参保批量结算标志
     */
    public final static String AAE158_0001 = "0001";            //批量结算
    public final static String AAE158_0000 = "0000";            //不批量结算


    /**
     * 险种读取方式
     */
    public final static String LOADTYPE_1 = "1";                   //根据单位ID查询险种
    public final static String LOADTYPE_2 = "2";                   //根据人员ID查询险种（显示所有险种 参加的勾选）
    public final static String LOADTYPE_3 = "3";                   //根据人员ID和单位ID查询外籍人员险种（只显示单位的险种）
    public final static String LOADTYPE_4 = "4";                   //根据参保身份查询险种（外籍人员只显示单位的险种）
    public final static String LOADTYPE_5 = "5";                   //查询全部险种（不勾选）
    public final static String LOADTYPE_6 = "6";                   //根据单位角色进行险种的勾选

    /**
     * 老系统存过调用
     */
    public final static String PROCEDURE_MODIFY_1 = "1";               //修改
    public final static String PROCEDURE_ADD_0 = "0";               //新增
    public final static String PROCEDURE_DELETE_2 = "2";               //删除

    /**
     * 老系统存过调用
     */
    public final static String INTERRUPT_1 = "1";               //中断
    public final static String RECOVERY_2 = "2";               //恢复


    /**
     * 审核存过调用
     */
    public final static String CHECK_1 = "1";               //审核取消

    public final static String CHECK_0 = "0";               //审核通过

    /**
     * 中断、恢复的业务标志
     */
    public final static String BUSINESSFLAG_1 = "1";           //城保
    public final static String BUSINESSFLAG_2 = "2";           //居保


    /**
     * 组织机构代码长度
     */
    public final static String AAB003LENGTH_9 = "9";               //组织机构代码长度


    /**
     * 居民养老审核
     */
    public final static String AAE016_0 = "0";               //未审核
    public final static String AAE016_1 = "1";               //审核通过
    public final static String AAE016_2 = "2";               //审核未通过
    public final static String AAE016_3 = "3";               //回退
    public final static String AAE016_9 = "9";               //失效

    //居民养老首次参加工作日期默认时间
    public final static String AAC007_PENSION = "1900/01/01";

    /**
     * 审核业务代码
     */
    public final static String CHECKTYPE_1 = "1";               //审核
    public final static String CHECKTYPE_2 = "2";               //回退
    public final static String CHECKTYPE_3 = "3";               //删除


    /**
     * 单位编号最大长度
     */
    public final static int AAB999_10 = 8;

    /**
     * 人员编号最大长度
     */
    public final static int AAC999_10 = 10;

    /**
     * 精准扶贫业务日期长度
     */
    public final static int AAE002_10 = 10;

    /**
     * 默认参保终止日期
     */
    public final static String AAE042_999912 = "9999/12";
    public final static String AAE042_99991231 = "9999/12/31";


    /**
     * 当事人标识符
     */
    public final static String AAA028_1 = "1";              //单位
    public final static String AAA028_2 = "2";              //人员

    /**
     * 当事人标类别
     */
    public final static String CAD149_CORP = "单位";         //单位
    public final static String CAD149_PERSON = "人员";       //人员

    /**
     * 有效标志
     */
    public final static String AAE100_1 = "1";               //有效
    public final static String AAE100_0 = "0";               //无效

    /**
     * 角色属性是否可空
     */
    public final static String CAD185_0 = "0";              //不可为空
    public final static String CAD185_1 = "1";              //可为空

    /**
     * 是否为主合同
     */
    public final static String CAD022_0 = "0";              //否
    public final static String CAD022_1 = "1";              //是

    /**
     * 参数类别代码 lhjfdc-灵活就业缴费基数 jmyljfdc-居民养老缴费档次 jmybjfbz-居民医保缴费标准
     * deylfybz-大额医疗费用补助 gtgshjfgz-城镇个体工商户缴费工资
     * jgjfsx-机关缴费上限 jgjfxx-机关缴费下限
     * qyjfsx-企业缴费上限 qyjfxx-企业缴费下限
     */
    public final static String AAA001_LHJFDC = "lhjfdc";              //灵活就业缴费基数
    public final static String AAA001_JMYLJFDC = "jmyljfdc";          //居民养老缴费基数
    public final static String AAA001_JMYBJFBZ = "jmybjfbz";          //居民医保缴费标准
    public final static String AAA001_DEYLFYBZ = "deylfybz";          //大额医疗费用补助
    public final static String AAA001_GTGSHJFGZ = "gtgshjfgz";        //城镇个体工商户缴费工资
    public final static String AAA001_JGJFSX = "jgjfsx";               //机关缴费上限
    public final static String AAA001_JGJFXX = "jgjfxx";               //机关缴费下限
    public final static String AAA001_QYJFSX = "qyjfsx";               //企业缴费上限
    public final static String AAA001_QYJFXX = "qyjfxx";               //企业缴费下限
    public final static String AAA001_DJBJJSXX = "djbjjsxx";               //企业缴费下限
    public final static String AAA001_QYLXJM = "qylxjm";               //企业类型减免系数
    public final static String AAA001_GTGSHJFDC = "gtgshjfdc";         //城镇个体工商户档次
    public final static String AAA001_FQRZBJSX = "fqrzbjsx";               //非全日制补缴上限
    public final static String AAA001_FQRZBJXX = "fqrzbjxx";               //非全日制补缴下限
    public final static String AAA001_FQRZGSBJJS = "fqrzgsbjjs";        //非全日制补缴下限


    public final static String AAA001_KNJMYLZJBZ = "knjmylzjbz";           //困难居民养老镇级补助比例
    public final static String AAA001_KNJMYLSJBZ = "knjmylsjbz";           //困难居民养老市级补助比例
    public final static String AAA001_JMYLSJBZ = "jmylsjbz";           //居民养老市级补助比例
    public final static String AAA001_JMYLZJBZ = "jmylzjbz";           //居民养老镇级补助比例

    public final static String AAA001_SYBXSP = "sybxsp";        //稳岗返还-2019年度失业保险金平均水平
    public final static String AAA001_JZZSQ = "jzzsq";          //集中征收期
    public final static String AAA001_YBJFJE = "ybjfje";        //居民医疗缴费金额

    public final static String AAA001_YCXJJJ = "ycxjjj";        //人员特殊退费一次性救济金


    /***
     * 养老缴费年度
     */
    public final static int START_MONTH = 9;                            //社保养老开始缴费年度
    public final static int END_MONTH = 11;                             //社保养老终止缴费年度

    /***
     * 养老缴费档次
     */
    public final static String JMYLJFDC_AAA105_1 = "1";                 //一档
    public final static String JMYLJFDC_AAA105_2 = "2";                 //二档
    public final static String JMYLJFDC_AAA105_3 = "3";                 //三档
    public final static String JMYLJFDC_AAA105_4 = "4";                 //四档

    public final static String CHECKPACKAGE_ALL = "y|~~|0|~~|";         //checkbox全选
    public final static String CHECKPACKAGE_NONE = "n|~~|0|~~|";        //checkbox不选

    /**
     * 是否精准扶贫人员
     */
    public final static String ISSUPPORTPOOR_0 = "0";                   //否
    public final static String ISSUPPORTPOOR_1 = "1";                   //是


    /**
     * ckeckbox勾选
     */
    public final static String CHECKBOX_1 = "1";            //勾选

    public final static String CHECKBOX_0 = "0";            //不勾选

    /**
     * 人员参保身份变更标志
     */
    public final static String CAE762_0 = "0";              //不变

    public final static String CAE762_1 = "1";              //增加

    public final static String CAE762_2 = "2";              //减少


    public final static String CAE211_2 = "2";                //基数类型 缴费基数
    /**
     * 特殊经历类型数据字典id
     */
    public final static String DICID_CAC400 = "CAC400";     //特殊经历类型
    public final static String DICID_CAC400_PENSION = "CAC400_PENSION";     //居民养老特殊身份

    /**
     * 特殊经历类型   0001-普通 0002-离休 0003-二乙 0004-50年代退职 0005-建国前老工人 0006-一般供养
     * 0007-离休配偶 0008-民政代管 0009-条线人员 0010-征土工保养 0011-农保人员 0012-老年补贴人员
     * 0013-省最低养老金人员 0014-军转干 0015-原工商业者 0016-高级专业技术职称 0017-享受公务员医疗补助
     * 0018-省机关养老发放人员 0019-1-4级工伤 0020-后延  0021-女干部   0022-临时老账户  0000-服刑
     * 0023-精准扶贫
     **/
    public final static String CAC400_0000 = "0000";              // 0000-服刑

    public final static String CAC400_0001 = "0001";              // 0001-普通
    public final static String CAC400_0002 = "0002";              // 0002-离休
    public final static String CAC400_0003 = "0003";              // 0003-二乙
    public final static String CAC400_0004 = "0004";              // 0004-50年代退职
    public final static String CAC400_0005 = "0005";              // 0005-建国前老工人
    public final static String CAC400_0006 = "0006";              // 0006-一般供养
    public final static String CAC400_0007 = "0007";              // 0007-离休配偶
    public final static String CAC400_0008 = "0008";              // 0008-民政代管
    public final static String CAC400_0009 = "0009";              // 0009-条线人员
    public final static String CAC400_0010 = "0010";              // 0010-征土工保养
    public final static String CAC400_0011 = "0011";              // 0011-农保人员
    public final static String CAC400_0012 = "0012";              // 0012-老年补贴人员
    public final static String CAC400_0013 = "0013";              // 0013-省最低养老金人员
    public final static String CAC400_0014 = "0014";              // 0014-军转干
    public final static String CAC400_0015 = "0015";              // 0015-原工商业者
    public final static String CAC400_0016 = "0016";              // 0016-高级专业技术职称
    public final static String CAC400_0017 = "0017";              // 0017-享受公务员医疗补助
    public final static String CAC400_0018 = "0018";              // 0018-省机关养老发放人员
    public final static String CAC400_0019 = "0019";              // 0019-1-4级工伤
    public final static String CAC400_0020 = "0020";              // 0020-后延
    public final static String CAC400_0021 = "0021";              // 0021-女干部
    public final static String CAC400_0022 = "0022";              // 0022-临时老账户
    public final static String CAC400_0023 = "0023";              // 0023-精准扶贫


    /**
     * 本地code
     */
    public final static String BAB001_320582 = "320582";  //张家港市


    /****
     * 是否为新参保
     */
    public final static String CAE550_1 = "1";              //是否为新参保 -是

    public final static String CAE550_0 = "0";              //是否为新参保 -否

    /****
     * 到龄标志
     */
    public final static String CAC340_0 = "0";              // -否

    public final static String CAC340_1 = "1";              // -是


    /***
     * 居民医保是否免缴
     */
    public final static String CAC319_0 = "0";                //否
    public final static String CAC319_1 = "1";                //是

    /***
     * 是否申报
     */
    public final static String DIC_ISDECLAR_1 = "1";                //是
    public final static String DIC_ISDECLAR_0 = "0";                //否


    /**
     * 个体工商户缴费工资
     */
    public final static String AAB019_3523 = "3523";

    /**
     * 退休年龄
     */
    public static String AGE_RET_F = "55";                    //女退休年龄
    public static String AGE_RET_M = "60";                    //男退休年龄
    public static String AGE_RET = "16";                      //参保最低年龄限制
    public static int AGE_RET_F_NGB = 55;                //女干部退休年龄
    /**
     * 特殊业务修改姓名年龄限制
     */
    public static int AGE_22 = 22;                      //特殊业务修改姓名年龄限制

    /**
     * 居民养老申报年度
     */
    public static Integer MINMONTH_9 = 9;                     //最小申报月份
    public static Integer MAXMONTH_11 = 11;                   //最大申报月份


    /***
     * 性别
     */
    public static final String AAC004_1 = "1";                      //男
    public static final String AAC004_2 = "2";                      //女
    public static final String AAC004_3 = "3";                      //未说明


    /***
     * 日期类型
     */
    public static final String FORMAT_DATE = "yyyy/MM/dd";
    public static final String FORMAT_MONTH = "yyyy/MM";
    public static final String FORMAT_MON = "yyyyMM";
    public static final String FORMAT_MM = "MM";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_DD = "dd";


    /***
     *存过成功标志 0-成功 1-失败
     */
    public static final String RESULT_SUCCESS = "0";
    public static final String RESULT_FAILURE = "-1";
    public static final String RESULT_SUCCESS_OLD = "1";// 老程序 1 代表返回正确
    /***
     * 接口成功标志
     */
    public static final String POST_SUCCESS_0 = "0";    //成功
    public static final String POST_SUCCESS_1 = "-1";    //失败

    public static final String GRBJ_SEGSEP = "~";       //分隔符
    public static final String GRBJ_FLDSEP = "%";       //分隔符
    public static final String GRBJ_ZL = "1.0";         //折率
    public static final String GRBJ_BJLX = "0001";      //补缴类型 0001-补缴 0002-补基数
//    public static final String GRBJ_FLDSEP = "|~~|";

    //现有功能业务编码
    public static final String SERVICENO_13 = "13";                    //共享平台接口(父级)
    public static final String SERVICENO_03 = "03";                    //缴费申报与核定(父级)
    public static final String SERVICENO_02 = "02";                    //参保信息管理(父级)
    public static final String SERVICENO_01 = "01";                    //基本信息管理(父级)
    public static final String SERVICENO_07 = "07";                    //待遇管理及支付(父级)

    public static final String SERVICENO_0101010001 = "0101010001";    //单位基础信息登记
    public static final String SERVICENAME_0101010001 = "单位基础信息登记";
    public static final String SERVICENO_0101020001 = "0101020001";    //单位基础信息变更
    public static final String SERVICENAME_0101020001 = "单位基础信息变更";
    public static final String SERVICENO_0101030001 = "0101030001";    //单位基础信息查询
    public static final String SERVICENAME_0101030001 = "单位基础信息查询";
    public static final String SERVICENO_0101030002 = "0101030002";    //人员基础信息变更查询
    public static final String SERVICENAME_0101030002 = "人员基础信息变更查询";
    public static final String SERVICENO_0102010001 = "0102010001";    //人员基础信息登记
    public static final String SERVICENAME_0102010001 = "人员基础信息登记";
    public static final String SERVICENO_0102020001 = "0102020001";    //人员基础信息变更
    public static final String SERVICENAME_0102020001 = "人员基础信息变更";
    public static final String SERVICENO_0102030001 = "0102030001";    //人员基础信息查询
    public static final String SERVICENAME_0102030001 = "人员基础信息查询";
    public static final String SERVICENO_0102030002 = "0102030002";    //人员基础信息变更查询
    public static final String SERVICENAME_0102030002 = "人员基础信息变更查询";
    public static final String SERVICENO_0102090001 = "0102090001";    //特殊身份维护
    public static final String SERVICENAME_0102090001 = "特殊身份维护";
    public static final String SERVICENO_0201010001 = "0201010001";    //单位参保信息登记
    public static final String SERVICENAME_0201010001 = "单位参保信息登记";
    public static final String SERVICENO_0201020001 = "0201020001";    //单位参保信息变更
    public static final String SERVICENAME_0201020001 = "单位参保信息变更";
    public static final String SERVICENO_0201030001 = "0201030001";    //单位参保登记统计查询
    public static final String SERVICENAME_0201030001 = "单位参保登记统计查询";
    public static final String SERVICENO_0201030002 = "0201030002";    //单位参保信息变更查询
    public static final String SERVICENAME_0201030002 = "单位参保信息变更查询";
    public static final String SERVICENO_0302090002 = "0302090002";    //单位补缴删除
    public static final String SERVICENAME_0302090002 = "单位补缴删除";
    public static final String SERVICENO_0302090003 = "0302090003";    //单位结算删除
    public static final String SERVICENAME_0302090003 = "单位结算删除";
    public static final String SERVICENO_0202010001 = "0202010001";    //城镇人员参保登记
    public static final String SERVICENAME_0202010001 = "城镇人员参保登记";
    public static final String SERVICENO_0202020001 = "0202020001";    //职工参保信息变更
    public static final String SERVICENAME_0202020001 = "职工参保信息变更";
    public static final String SERVICENO_0202020002 = "0202020002";    //灵活就业参保变更
    public static final String SERVICENAME_0202020002 = "灵活就业参保变更";
    public static final String SERVICENO_0202010002 = "0202010002";    //居民养老参保登记
    public static final String SERVICENAME_0202010002 = "居民养老参保登记";
    public static final String SERVICENO_0202120001 = "0202120001";    //居民养老人员审核
    public static final String SERVICENAME_0202120001 = "居民养老人员审核";
    public static final String SERVICENO_0202010003 = "0202010003";    //居民医保参保登记
    public static final String SERVICENAME_0202010003 = "居民医保参保登记";
    public static final String SERVICENO_0202050001 = "0202050001";    //人员中断
    public static final String SERVICENAME_0202050001 = "人员中断";
    public static final String SERVICENO_0202030001 = "0202030001";    //人员参保登记统计查询
    public static final String SERVICENAME_0202030001 = "人员参保登记统计查询";
    public static final String SERVICENO_0202030002 = "0202030002";    //人员中断统计查询
    public static final String SERVICENAME_0202030002 = "人员中断统计查询";
    public static final String SERVICENO_0202030003 = "0202030003";    //人员参保信息变更查询
    public static final String SERVICENAME_0202030003 = "人员参保信息变更查询";
    public static final String SERVICENO_0202030004 = "0202030004";    //人员状态查询
    public static final String SERVICENAME_0202030004 = "人员状态查询";
    public static final String SERVICENO_0202030005 = "0202030005";    //多编号查询
    public static final String SERVICENAME_0202030005 = "多编号查询";
    public static final String SERVICENO_0202060001 = "0202060001";    //人员恢复
    public static final String SERVICENAME_0202060001 = "人员恢复";
    public static final String SERVICENO_0202070001 = "0202070001";    //人员转移
    public static final String SERVICENAME_0202070001 = "人员转移";
    public static final String SERVICENO_0302110001 = "0302110001";    //居民养老年度缴费申报
    public static final String SERVICENAME_0302110001 = "居民养老年度缴费申报";
    public static final String SERVICENO_0302110002 = "0302110002";    //居民医疗年度缴费申报
    public static final String SERVICENAME_0302110002 = "居民医疗年度缴费申报";
    public static final String SERVICENO_0302040001 = "0302040001";    //居民医保补缴
    public static final String SERVICENAME_0302040001 = "居民医保补缴";
    public static final String SERVICENO_0302040002 = "0302040002";    //居民养老到龄退休补缴
    public static final String SERVICENAME_0302040002 = "居民养老到龄退休补缴";
    public static final String SERVICENO_0302040003 = "0302040003";    //灵活就业补缴
    public static final String SERVICENAME_0302040003 = "灵活就业补缴";
    public static final String SERVICENO_0302030001 = "0302030001";    //到龄补缴申报查询
    public static final String SERVICENAME_0302030001 = "到龄补缴申报查询";
    public static final String SERVICENO_0302030002 = "0302030002";    //灵活就业补缴查询
    public static final String SERVICENAME_0302030002 = "灵活就业补缴查询";
    public static final String SERVICENO_1302030001 = "1302030001";    //灵活就业参保登记信息查询
    public static final String SERVICENAME_1302030001 = "灵活就业参保登记信息查询";
    public static final String SERVICENO_1302030002 = "1302030002";    //城乡居民参保登记信息查询
    public static final String SERVICENAME_1302030002 = "城乡居民参保登记信息查询";
    public static final String SERVICENO_1302030003 = "1302030003";    //灵活就业缴费明细信息查询
    public static final String SERVICENAME_1302030003 = "灵活就业缴费明细信息查询";
    public static final String SERVICENO_1302030004 = "1302030004";    //城乡居民缴费明细信息查询
    public static final String SERVICENAME_1302030004 = "城乡居民缴费明细信息查询";
    public static final String SERVICENO_1302030005 = "1302030005";    //特殊缴费信息查询
    public static final String SERVICENAME_1302030005 = "特殊缴费信息查询";
    public static final String SERVICENO_1302030006 = "1302030006";    //社保费对账信息查询
    public static final String SERVICENAME_1302030006 = "社保费对账信息查询";
    public static final String SERVICENO_1302030007 = "1302030007";    //单户缴费状态查询
    public static final String SERVICENAME_1302030007 = "单户缴费状态查询";
    public static final String SERVICENO_0101090001 = "0101090001";//单位角色管理
    public static final String SERVICENAME_0101090001 = "单位角色管理";
    public static final String SERVICENO_0102090002 = "0102090002";//居民养老特殊身份维护
    public static final String SERVICENAME_0102090002 = "居民养老特殊身份维护";
    public static final String SERVICENO_0701010001 = "0701010001";//档案扫描认定
    public static final String SERVICENAME_0701010001 = "档案扫描认定";
    public static final String SERVICENO_0701030001 = "0701030001";//档案扫描认定查询
    public static final String SERVICENAME_0701030001 = "档案扫描认定查询";
    public static final String SERVICENO_0701120001 = "0701120001";//职工养老退休初审
    public static final String SERVICENAME_0701120001 = "职工养老退休初审";
    public static final String SERVICENO_0701120002 = "0701120002";//退休初审材料移交
    public static final String SERVICENAME_0701120002 = "退休初审材料移交";
    public static final String SERVICENO_0701120003 = "0701120003";//退休初审材料接收
    public static final String SERVICENAME_0701120003 = "退休初审材料接收";
    public static final String SERVICENO_0601130001 = "0601130001";//职工养老退休待遇初核
    public static final String SERVICENAME_0601130001 = "职工养老退休待遇初核";
    public static final String SERVICENO_0701120004 = "0701120004";//邮寄管理
    public static final String SERVICENAME_0701120004 = "邮寄管理";

    public static final String SERVICENO_0303080001 = "0303080001";//税务回传数据预处理
    public static final String SERVICENAME_0303080001 = "税务回传数据预处理";
    public static final String SERVICENO_0803080001 = "0803080001";//财务确认
    public static final String SERVICENAME_0803080001 = "财务确认";
    public static final String SERVICENO_0303080002 = "0303080002";//结算确认
    public static final String SERVICENAME_0303080002 = "结算确认";
    public static final String SERVICENO_0803080002 = "0803080002";//财务汇总计账确认
    public static final String SERVICENAME_0803080002 = "财务汇总计账确认";
    public static final String SERVICENO_0202090001 = "0202090001";//终结号启用
    public static final String SERVICENAME_0202090001 = "终结号启用";
    public static final String SERVICENO_0202090002 = "0202090002";//居民养老终结号启用
    public static final String SERVICENAME_0202090002 = "居民养老终结号启用";
    public static final String SERVICENO_0302110004 = "0302110004";//居民医疗年度缴费申报
    public static final String SERVICENAME_0302110004 = "居民医疗年度缴费申报";
    public static final String SERVICENO_0302110005 = "0302110005";//居民医疗申报确认
    public static final String SERVICENAME_0302110005 = "居民医疗申报确认";
    public static final String SERVICENO_0302110007 = "0302110007";//居民医疗免缴申报
    public static final String SERVICENAME_0302110007 = "居民医疗免缴申报";
    public static final String SERVICENO_0302110006 = "0302110006";//居民医疗年度缴费确认
    public static final String SERVICENAME_0302110006 = "居民医疗年度缴费确认";
    public static final String SERVICENO_0302110008 = "0302110008";//居民医疗年度缴费解款
    public static final String SERVICENAME_0302110008 = "居民医疗年度缴费解款";
    public static final String SERVICENO_302150001 = "302150001";//医保征缴退费
    public static final String SERVICENAME_302150001 = "医保征缴退费";
    public static final String SERVICENO_302150007 = "302150007";//医保征缴退费作废
    public static final String SERVICENAME_302150007 = "医保征缴退费作废";
    public static final String SERVICENO_302150002 = "302150002";//医保征缴退费审核
    public static final String SERVICENAME_302150002 = "医保征缴退费审核";
    public static final String SERVICENO_302150003 = "302150003";//医保征缴退费结算
    public static final String SERVICENAME_302150003 = "医保征缴退费结算";
    public static final String SERVICENO_302150004 = "302150004";//医保征缴退费查询
    public static final String SERVICENAME_302150004 = "医保征缴退费查询";
    public static final String SERVICENO_302150005 = "302150005";//医保征缴免缴汇总
    public static final String SERVICENAME_302150005 = "医保征缴免缴汇总";
    public static final String SERVICENO_302150006 = "302150006";//医保征缴统计
    public static final String SERVICENAME_302150006 = "医保征缴统计";
    public static final String SERVICENO_0301120001 = "0301120001";//稳岗初审登记
    public static final String SERVICENAME_0301120001 = "稳岗初审登记";
    public static final String SERVICENO_0301120002 = "0301120002";//稳岗预审登记
    public static final String SERVICENAME_0301120002 = "稳岗预审登记";
    public static final String SERVICENO_0301120003 = "0301120003";//稳岗复审登记
    public static final String SERVICENAME_0301120003 = "稳岗复审登记";
    public static final String SERVICENO_0301120004 = "0301120004";//稳岗单位公示
    public static final String SERVICENAME_0301120004 = "稳岗单位公示";
    public static final String SERVICENO_0301080001 = "0301080001";//稳岗业务结算
    public static final String SERVICENAME_0301080001 = "稳岗业务结算";
    public static final String SERVICENO_0301120005 = "0301120005";//稳岗业务删除
    public static final String SERVICENAME_0301120005 = "稳岗业务删除";
    public static final String SERVICENO_0301120006 = "0301120006";//稳岗业务确认
    public static final String SERVICENAME_0301120006 = "稳岗业务确认";
    public static final String SERVICENO_0201030003 = "0201030003";//裁员率监控
    public static final String SERVICENAME_0201030003 = "裁员率监控";
    public static final String SERVICENO_9702090002 = "9702090002";      //信息维护任务管理
    public static final String SERVICENAME_9702090002 = "信息维护任务管理";
    public static final String SERVICENO_9799090001 = "9799090001";      //面试任务管理
    public static final String SERVICENAME_9799090001 = "面试任务管理";
    public static final String SERVICENO_9799030001 = "9799030001";      //面试任务状态查询
    public static final String SERVICENAME_9799030001 = "面试任务管理";
    public static final String SERVICENO_9702090003 = "9702090003";      //面试任务提醒发送
    public static final String SERVICENAME_9702090003 = "面试任务提醒发送";
    public static final String SERVICENO_9702090004 = "9702090004";      //面试官任务状态维护
    public static final String SERVICENAME_9702090004 = "面试官任务状态维护";
    public static final String SERVICENO_9702030001 = "9702030001";      //面试官任务统计
    public static final String SERVICENAME_9702030001 = "面试官任务统计";
    public static final String SERVICENO_9799030002 = "9799030002";      //面试任务统计
    public static final String SERVICENAME_9799030002 = "面试任务统计";
    public static final String SERVICENO_9798010001 = "9798010001";      //面试官维护任务短信
    public static final String SERVICENAME_9798010001 = "面试官维护任务短信";
    public static final String SERVICENO_9798010002 = "9798010002";      //面试官任务短信
    public static final String SERVICENAME_9798010002 = "面试官任务短信";
    public static final String SERVICENO_9798010003 = "9798010003";      //面试官提醒短信
    public static final String SERVICENAME_9798010003 = "面试官提醒短信";
    public static final String SERVICENO_9702090001 = "9702090001";      //面试考官信息维护
    public static final String SERVICENAME_9702090001 = "面试考官信息维护";
    public static final String SERVICENO_0202030010 = "0202030010";      //接收函打印
    public static final String SERVICENAME_0202030010 = "接收函打印";
    public static final String SERVICENO_0202070002 = "0202070002";      //失业保险关系（待遇）转出
    public static final String SERVICENAME_0202070002 = "失业保险关系（待遇）转出";
    public static final String SERVICENO_0202070003 = "0202070003";      //失业保险关系（待遇）转入
    public static final String SERVICENAME_0202070003 = "失业保险关系（待遇）转入";
    public static final String SERVICENO_0301090001 = "0301090001";      //失败数据调整
    public static final String SERVICENAME_0301090001 = "失败数据调整";
    public static final String SERVICENO_0301080002 = "0301080002";      //失败数据重发结算
    public static final String SERVICENAME_0301080002 = "失败数据重发结算";
    public static final String SERVICENO_0301090002 = "0301090002";//企业规模维护
    public static final String SERVICENAME_0301090002 = "企业规模维护";
    public static final String SERVICENO_0301090003 = "0301090003";//缓缴企业维护
    public static final String SERVICENAME_0301090003 = "缓缴企业维护";
    public static final String SERVICENO_0301090004 = "0301090004";
    public static final String SERVICENAME_0301090004 = "阶段性免缴";//阶段性免缴

    public static final String SERVICENO_0302040005 = "0302040005";      //退伍军人补缴
    public static final String SERVICENAME_0302040005 = "退伍军人补缴";
    public static final String SERVICENO_0302040006 = "0302040006";      //退伍军人补缴名单导入
    public static final String SERVICENAME_0302040006 = "退伍军人补缴名单导入";
    public static final String SERVICENO_0302040007 = "0302040007";      //退伍军人外地参保信息录入
    public static final String SERVICENAME_0302040007 = "退伍军人外地参保信息录入";
    public static final String SERVICENO_0302040008 = "0302040008";      //退伍军人补缴匹配
    public static final String SERVICENAME_0302040008 = "退伍军人补缴匹配";
    public static final String SERVICENO_0302040009 = "0302040009";      //退伍军人补缴审核
    public static final String SERVICENAME_0302040009 = "退伍军人补缴审核";
    public static final String SERVICENO_0302040010 = "0302040010";      //退伍军人补缴删除
    public static final String SERVICENAME_0302040010 = "退伍军人补缴删除";
    public static final String SERVICENO_0302040011 = "0302040011";      //退伍军人欠缴记录删除
    public static final String SERVICENAME_0302040011 = "退伍军人欠缴记录删除";
    public static final String SERVICENO_0302040012 = "0302040012";      //退伍军人补缴计算
    public static final String SERVICENAME_0302040012 = "退伍军人补缴计算";

    public static final String SERVICENO_0302040004 = "0302040004";//单位补缴
    public static final String SERVICENAME_0302040004 = "单位补缴 ";
    public static final String SERVICENO_0302080001 = "0302080001";//补缴结算
    public static final String SERVICENAME_0302080001 = "补缴结算";

    public static final String SERVICENO_0302010001 = "0302010001";//退回失业金申请
    public static final String SERVICENAME_0302010001 = "退回失业金申请";//
    public static final String SERVICENO_0302120001 = "0302120001";//退回失业金确认
    public static final String SERVICENAME_0302120001 = "退回失业金确认";//
    public static final String SERVICENO_0302130001 = "0302130001";//退回失业金复核
    public static final String SERVICENAME_0302130001 = "退回失业金复核";//
    public static final String SERVICENO_0302090006 = "0302090006";//退回失业金作废
    public static final String SERVICENAME_0302090006 = "退回失业金作废";//

    public static final String SERVICENO_0301150002 = "0301150002";//减免退费结算
    public static final String SERVICENAME_0301150002 = "减免退费结算";//
    /* 一次性吸纳就业补贴*/
    public static final String SERVICENO_0301120009 = "0301120009";//名单公示（共用）
    public static final String SERVICENAME_0301120009 = "名单公示（共用）";//
    public static final String SERVICENO_0301120007 = "0301120007";//名单复审（企业）
    public static final String SERVICENAME_0301120007 = "名单复审（企业）";
    public static final String SERVICENO_0301120008 = "0301120008";//名单复审（重点企业）
    public static final String SERVICENAME_0301120008 = "名单复审（重点企业）";
    public static final String SERVICENO_0301080003 = "0301080003";
    public static final String SERVICENAME_0301080003 = "补贴结算（企业）";
    public static final String SERVICENO_0301080004 = "0301080004";
    public static final String SERVICENAME_0301080004 = "补贴结算（重点企业）";
    /*退役军人医疗补缴*/
    public static final String SERVICENO_0302040013 = "0302040013";
    public static final String SERVICENAME_0302040013 = "退役军人医疗补缴名单导入";
    public static final String SERVICENO_0302040014 = "0302040014";
    public static final String SERVICENAME_0302040014 = "退役军人医疗补缴";
    public static final String SERVICENO_0302040015 = "0302040015";
    public static final String SERVICENAME_0302040015 = "退役军人医疗补缴删除";
    //退休人员死亡待遇审核
    public static final String SERVICENO_062120001 = "062120001";
    public static final String SERVICENAME_062120001 = "企业退休人员死亡待遇初审";
    public static final String SERVICENO_062120003 = "062120003";
    public static final String SERVICENAME_062120003 = "企业退休人员死亡待遇审核";
    //退管慰问
    public static final String SERVICENO_2102090001 = "2102090001";
    public static final String SERVICENAME_2102090001 = "账户信息维护";
    public static final String SERVICENO_2102090002 = "2102090002";
    public static final String SERVICENAME_2102090002 = "死亡待遇领取人信息录入";
    public static final String SERVICENO_2102090003 = "2102090003";
    public static final String SERVICENAME_2102090003 = "亡故慰问名单生成";
    public static final String SERVICENO_2102120001 = "2102120001";
    public static final String SERVICENAME_2102120001 = "亡故慰问名单审核";
    public static final String SERVICENO_2102090004 = "2102090004";
    public static final String SERVICENAME_2102090004 = "重病慰问名单生成";
    public static final String SERVICENO_2102120002 = "2102120002";
    public static final String SERVICENAME_2102120002 = "重病慰问名单审核";
    public static final String SERVICENO_2102090005 = "2102090005";
    public static final String SERVICENAME_2102090005 = "冬送温暖数据导入";
    public static final String SERVICENO_2102120003 = "2102120003";
    public static final String SERVICENAME_2102120003 = "冬送温暖名单审核";
    public static final String SERVICENO_2102090006 = "2102090006";
    public static final String SERVICENAME_2102090006 = "重阳慰问名单生成";
    public static final String SERVICENO_2102120004 = "2102120004";
    public static final String SERVICENAME_2102120004 = "重阳慰问名单审核";
    public static final String SERVICENO_2102090007 = "2102090007";
    public static final String SERVICENAME_2102090007 = "异地体检名单确认";
    public static final String SERVICENO_2102120005 = "2102120005";
    public static final String SERVICENAME_2102120005 = "异地体检名单审核";
    public static final String SERVICENO_2102080001 = "2102080001";
    public static final String SERVICENAME_2102080001 = "业务结算";
    public static final String SERVICENO_2102090008 = "2102090008";
    public static final String SERVICENAME_2102090008 = "发放失败调整";
    public static final String SERVICENO_2102080002 = "2102080002";
    public static final String SERVICENAME_2102080002 = "重发结算";
    //特殊退费
    public static final String SERVICENO_0302150011 = "0302150011";
    public static final String SERVICENAME_0302150011 = "特殊退费名单导入";
    public static final String SERVICENO_0302150012 = "0302150012";
    public static final String SERVICENAME_0302150012 = "特殊退费";

    //企业职工退休待遇审批
    public static final String SERVICENO_0602110001 = "0602110001";
    public static final String SERVICENAME_0602110001 = "企业养老待遇申请";
    public static final String SERVICENO_0602120001 = "0602120001";
    public static final String SERVICENAME_0602120001 = "企业养老待遇审核";
    public static final String SERVICENO_0602130001 = "0602130001";
    public static final String SERVICENAME_0602130001 = "企业养老待遇审核查询";

    //基本养老保险转入审核
    public static final String SERVICENO_0602120002 = "0602120002";
    public static final String SERVICENAME_0602120002 = "基本养老保险转入审核";

    //一次性伤残补助金申报审核
    public static final String SERVICENO_0603120004 = "0603120004";
    public static final String SERVICENAME_0603120004 = "一次性伤残补助金申报审核";

    //医保关系转入
    public static final String SERVICENO_0202070004 = "0202070004";
    public static final String SERVICENAME_0202070004 = "医保关系转入";

    //技能提升补贴
    public static final String SERVICENO_0602120003 = "0602120003";
    public static final String SERVICENAME_0602120003 = "技能提升补贴审核";

    //建筑工伤参保
    public static final String SERVICENO_0603120005 = "0603120005";
    public static final String SERVICENAME_0603120005 = "建筑工伤参保审核";

    //操作类型 add
    public static final String OPERATETYPE_ADD = "ADD";//新增
    public static final String OPERATETYPE_MODIFTY = "MODIFTY ";//修改
    public static final String OPERATETYPE_CANCEL = "CANCEL ";//作废

    //共享平台sbjh_cb_rycbdj_cbmx 缴费类别
    public static final String JFLB_0 = "0";//城乡居民
    public static final String JFLB_1 = "1";//灵活就业
    public static final String JFLB_2 = "2";//特殊缴费

    //补缴 账户的实现标志
    public static final String REALIZEFLAG_0000 = "0000";//未入账
    public static final String REALIZEFLAG_0001 = "0001";//入账
    public static final String REALIZEFLAG_0002 = "0002";//实现

    //业务主体类别
    public static final String TARGETTYPE_PER = "1";//个人
    public static final String TARGETTYPE_CORP = "2";//单位

    //实现标志
    public static final String DIC_REALIZEFLAG_0001 = "0001";//入账
    public static final String DIC_REALIZEFLAG_0002 = "0002";//已实现
    public static final String DIC_REALIZEFLAG_0000 = "0000";//未入账

    /**
     * 缴费人类型
     */
    public static final String JFRLX_CXJM = "0";    //城乡居民
    public static final String JFRLX_LHJY = "1";    //灵活就业
    public static final String JFRLX_ZG = "2";      //职工
    public static final String JFRLX_DW = "3";      //单位
    public static final String JFRLX_JAXM = "4";    //建安项目
    public static final String JFRLX_DWXNH = "5";   //单位虚拟户

    /***
     * 业务办理渠道
     */
    public static final String CAE219_000 = "000";  //张家港一体化
    public static final String CAE219_001 = "001";  //核心业务系统（小房子）
    public static final String CAE219_002 = "002";  //工伤管理信息系统
    public static final String CAE219_003 = "003";  //张家港征地管理系统
    public static final String CAE219_004 = "004";  //网上申报
    public static final String CAE219_005 = "005";  //政务平台
    public static final String CAE219_007 = "007";  //微信
    public static final String CAE219_008 = "008";  //张家港医保独立化

    /**
     * 001-人社导入
     */
    public static final String CAE219_006 = "006";

    /***
     * 业务类型
     */
    public static final String AAA121_0001 = "0001"; //人员新增
    public static final String AAA121_0002 = "0002"; //人员中断
    public static final String AAA121_0003 = "0003"; //人员恢复
    public static final String AAA121_0004 = "0004"; //企业遗属新增
    public static final String AAA121_0005 = "0005"; //工伤供属新增
    public static final String AAA121_0006 = "0006"; //人员查询
    public static final String AAA121_0007 = "0007"; //人员更新


    /**
     * 税务端缴费状态代码
     */
    public static final String SWDJFZT_1 = "01";    //未申报
    public static final String SWDJFZT_2 = "02";    //已申报
    public static final String SWDJFZT_3 = "03";    //已缴费
    public static final String SWDJFZT_4 = "04";    //已入库
    public static final String SWDJFZT_9 = "09";    //无此户信息

    /**
     * 特殊缴费类型
     */
    public static final String TSJFLX_10 = "10";    //正常应缴
    public static final String TSJFLX_20 = "20";    //退收
    public static final String TSJFLX_31 = "31";    //中断补收
    public static final String TSJFLX_32 = "32";    //一次性补收
    public static final String TSJFLX_33 = "33";    //退休补收
    public static final String TSJFLX_34 = "34";    //缴费基数调整补收
    public static final String TSJFLX_35 = "35";    //缴费比例调整补收
    public static final String TSJFLX_40 = "40";    //一次性缴费

    /**
     * 缴费类型
     */
    public static final String JFLX_00 = "00";    //正常
    public static final String JFLX_01 = "01";    //政策性补缴
    public static final String JFLX_02 = "02";    //缴费基数差额补缴

    /**
     * 灵活就业人员各险种缴费比例
     */
    public static final String JFBL_QYYL = "qyyljfbl";                  //企业养老缴费比例
    public static final String JFBL_YLBX = "ylbxjfbl";                  //医疗保险缴费比例
    public static final String JFBL_BCYLBX = "bcylbxjfbl";              //补充医疗保险缴费比例
    public static final String JFBL_DEBZ = "debzjfje";                  //大额医疗费用补助缴费金额

    public static final String AAB165_0 = "0";                          //缴费标识
    public static final String SFYXBZEJF = "0";                         //是否允许不足额缴费


    //记录类型 1-人员类别 2-经历
    public static final String DAC010_1 = "1";                          //人员类别
    public static final String DAC010_2 = "2";                          //经历
    public static final String DAC010_3 = "3";                          //政府代缴费人员

    //分组显示 1-特殊人员显示
    public static final String PERTYPESHOWTYPE_1 = "1";                  //经历

    /**
     * 税务端缴费状态代码
     */
    public static final String SWDJFZTDM_03 = "03";                     //已缴费
    public static final String SWDJFZTDM_04 = "04";                     //已入库

    /**
     * 缴费模式
     */
    public static final String DKE010_0 = "0";                            //自行缴纳
    public static final String DKE010_1 = "1";                            //经办代收
    public static final String DKE010_2 = "2";                            //免缴
    public static final String DKE010_3 = "3";                            //免缴大学生

    //灵活就业单位
    public static final String LHJY_30000899 = "30000899";
    public static final String LHJY_10003737 = "10003737";
    public static final String LHJY_30000002 = "30000002";                //无效单位
    public static final String LHJY_35046833 = "35046833";                //无效单位


    //银行代码
    public static final String AAE008_103 = "中国农业银行";                        //中国农业银行
    public static final String AAE008_314 = "农村商业银行";                        //农村商业银行
    //银行id
    public static final String AAE008_GSYH = "102";                        //中国工商银行

    //解款标志
    public static final String DAE010_0 = "0";                //未确认解款
    public static final String DAE010_1 = "1";                //已确认解款


    //工单类型
    public static final String WORKORDER_COMP_ADD = "1";                //单位信息新增


    //工单环节状态 0-初始 1-待办 2-完成
    public static final String WORKORDER_TASK_STATUS_INITIAL = "0";                //初始
    public static final String WORKORDER_TASK_STATUS_TODO = "1";                //待办
    public static final String WORKORDER_TASK_STATUS_DONE = "2";                //完成

    //角色
    public static final String ROLE_ZCB = "R_ZCB";                    //镇城保
    public static final String ROLE_ZYL = "R_ZYL";                    //镇居民养老
    public static final String ROLE_ZYB = "R_ZYB";                    //镇居民医保
    public static final String ROLE_CYL = "R_CYL";                    //村居民养老
    public static final String ROLE_CYB = "R_CYB";                    //村居民医保
    public static final String ROLE_ZXCB = "R_ZXCB";                  //中心城保
    public static final String ROLE_ZXYL = "R_ZXYL";                  //中心居民养老
    public static final String ROLE_ZXYB = "R_ZXYB";                  //中心居民医保

    //用户组
    public static final String GROUP_ZCB = "GR001";                    //镇城保    20211021后  这个用户组成为了医保中心组
    public static final String GROUP_ZYL = "GR002";                    //镇居民养老
    public static final String GROUP_ZYB = "GR003";                    //镇居民医保
    public static final String GROUP_CYL = "GR004";                    //村居民养老
    public static final String GROUP_CYB = "GR005";                    //村居民医保
    public static final String GROUP_ZXCB = "GR006";                    //中心城保
    public static final String GROUP_ZXYL = "GR007";                    //中心居民养老
    public static final String GROUP_ZXYB = "GR008";                    //中心居民医保
    public static final String GROUP_ZXCXZ = "GR009";                    //中心查询组
    public static final String GROUP_ZXJCK = "GR010";                    //中心稽查科
    public static final String GROUP_ZXJBK = "GR011";                    //中心机保科
    public static final String GROUP_ZXCWK = "GR012";                    //中心财务科
    public static final String GROUP_ZXZYK = "GR013";                    //中心转移科
    public static final String GROUP_TSYW = "GR099";                    //特殊业务（目前是改名，需下放）
    public static final String GROUP_DARD_RG = "GR801";                    //档案认定-人管
    public static final String GROUP_DARD_YL = "GR802";                    //档案认定-养老保险科
    public static final String GROUP_DARD_JC = "GR803";                    //档案认定-中心稽查科
    public static final String GROUP_ZJHBG = "GR998";        //证件号变更组
    public static final String GROUP_CENTER = "GR030";                      //中心确认组
    public static final String GROUP_VILLAGE = "GR031";                     //乡镇确认组
    //居民医疗申报确认标记
    public static final String DKE011_0 = "0";//未确认
    public static final String DKE011_1 = "1";//已确认

    //一体化系统代码类别
    public static final String AAA100_YTH_SW_ZHEN = "YTH_SW_ZHEN";  //镇
    public static final String AAA100_YTH_SW_CUN = "YTH_SW_CUN";  //村
    public static final String AA99_CQJM = "320582110";//城区居民镇税务代码
    public static final String AA99_320582110191 = "320582110191";//税务杨舍镇-城区居民

    //精准扶贫标志
    public static final String JZFP_1 = "1"; //精准扶贫人员

    //人员参保类别
    public static final String INSUTYPE_0 = "0";    //城保人员
    public static final String INSUTYPE_1 = "1";    //养老人员
    public static final String INSUTYPE_2 = "2";    //医保人员

    //失业类型
    public static final String AJC091_6 = "6";//就业转失业
    public static final String AJC091_2 = "2";//工作调转


    // 居民医疗征缴退费审核
    public static final String TFZT_2 = "2"; //退费审核通过
    public static final String TFZT_3 = "3"; //退费审核退回（不通过）

    //数据处理状态
    public static final String DKC030_N1 = "-1";//数据错误
    public static final String DKC030_0 = "0";//初始
    public static final String DKC030_1 = "1";//待申报
    public static final String DKC030_2 = "2";//已申报
    public static final String DKC030_3 = "3";//已确认
    public static final String DKC030_4 = "4";//已参保(非当前渠道参保)
    public static final String DKC030_5 = "5";//免缴发送税务


    //---------------字段默认值----------------------------
    public static String CAC095_320000 = "320000";     //江苏省
    public static String CAC096_320500 = "320500";     //苏州市
    public static String AAC161_CHN = "CHN";     //中国
    public static String AAC161_TWN = "TWN";     //台湾
    public static String AAC161_HKG = "HKG";     //香港
    public static String AAC161_MAC = "MAC";     //澳门

    public static String AAC005_01 = "01";     //汉族
    public static String AAE321_00000 = "00000";     //手机号码
    public static String AAE007_215600 = "215600";//邮政编码

    //失业原因
    public static final String AJC093_20 = "20";//与企业解除或终止劳动关系的

    public static final String AAE019_2019 = "270";//居民医疗2019缴费金额

    public static final String AAE019_1300 = "350";//居民医疗2020缴费金额-非就业居民
    public static final String AAE019_1100 = "350";//居民医疗2020缴费金额-学生少儿
    public static final String AAE019_1000 = "350";//居民医疗2020缴费金额-大学生

    //退工原因
    public static final String AAE163_02 = "02";   //其他终止情形

    //职业（工种）
    public static final String ACA111_3000000 = "3000000";    //办事人员

    //国家职业资格等级
    public static final String AAC015_A = "A";    //无要求

    //就业类别
    public static final String ACC032_1 = "1";    //失业转就业
    public static final String ACC032_2 = "2";    //初次就业
    public static final String ACC032_3 = "3";    //其他就业


    //就业渠道
    public static final String ACC039_5 = "5";    //人员流动

    //用工形式
    public static final String DAC013_1 = "1"; //全日制用工

    //就业形式
    public static final String ADC111_02 = "02"; //单位就业

    /**
     * 医保
     */
    public final static String AAE376_270 = "270";          //医保缴费


    //终结号启用来源
    public static final String AAE191_0 = "0";//城保
    public static final String AAE191_1 = "1";//居民养老
    public static final String AAE191_2 = "2";//医保

    //免缴组（拼在镇后面）
    public static final String DKC012_500001 = "500001";

    //组编号（权限）
    public static final String USER_GROUP_GR004 = "GR004";//村级居民养老
    public static final String USER_GROUP_GR002 = "GR002";//镇级居民养老
    public static final String USER_GROUP_GR007 = "GR007";//中心居保业务组

    //-----------------------获取账号方法常量-----------------------------
    public static final String ACCOUNT_BUSINESSNO_BK001 = "BK001";
    public static final String ACCOUNT_BID = "314";

    /**
     * 用于Util中dateAdd
     */
    public static final String ADDTYPE_DAY = "1";//天
    public static final String ADDTYPE_MON = "2";//月
    public static final String ADDTYPE_YEAR = "3";//年

    //养老通知单打印标记
    public static final String PRINTFLAG_BOTH = "both";//天
    public static final String PRINTFLAG_DECLARE = "declare";//月
    public static final String PRINTFLAG_REPAY = "repay";//年

    /**
     * 稳岗返还
     */

    //申报类别
    public static final String DJB001_1 = "1";//1-企业稳岗返还
    public static final String DJB001_2 = "2";//2-困难企业稳岗返还
    public static final String DJB001_3 = "3";//3-应急企业稳岗返还
    public static final String DJB001_4 = "4";//4-提标发放单位

    // 公示状态
    public static final String DJB047_0 = "0";//0-未公示
    public static final String DJB047_1 = "1";//1-公示中
    public static final String DJB047_2 = "2";//2-公示通过
    public static final String DJB047_3 = "3";//3-公示不通过

    // 复审状态
    public static final String DJB041_0 = "0";//0-未复审
    public static final String DJB041_1 = "1";// 1-复审通过
    public static final String DJB041_2 = "2";// 2-复审不通过

    //申报删除业务ID
    public static final String BUINESSID_1 = "1";//删除申报信息
    public static final String BUINESSID_2 = "2";//删除补缴信息
    public static final String BUINESSID_3 = "3";//删除申报和补缴信息

    //初审校验成功标志
    public static final String CHECKFLAG_1 = "1";
    public static final String CHECKFLAG_2 = "2";

    //联合预审状态
    public static final String DJB040_0 = "0";//未审核
    public static final String DJB040_1 = "1";//审核通过
    public static final String DJB040_2 = "2";//审核未通过

    //预审结果
    public static final String DJB052_0 = "0";//未审核
    public static final String DJB052_1 = "1";//审核通过
    public static final String DJB052_2 = "2";//审核未通过

    //恢复标记
    public static final String DJB051_0 = "0";//初始化
    public static final String DJB051_1 = "1";//恢复
    public static final String DJB051_2 = "2";//失效

    // 是否注销
    public static final String DJB060_0 = "0";//否
    public static final String DJB060_1 = "1";//是

    //上年度是否享受
    public static final String DJB020_0 = "0";//否
    public static final String DJB020_1 = "1";//是

    //登记类型
    public static final String DJB101_1 = "1";//普通单位
    public static final String DJB101_2 = "2";//派遣单位
    public static final String DJB101_3 = "3";//应急单位

    //数据来源
    public static final String DJB120_0 = "0";//一体化
    public static final String DJB120_1 = "1";//网上申报

    //人员退费 退费类型
    public static final String REFUNDTYPE_1 = "1";//全额
    public static final String REFUNDTYPE_2 = "2";//个账

    //人员退费 是否本地
    public static final String REFUNDTYPE_ISLOCAL_10 = "10";//是
    public static final String REFUNDTYPE_ISLOCAL_20 = "20";//不是

    //退失业金 退取处理状态
    public static final String DAE020_0 = "0";//未退取
    public static final String DAE020_1 = "1";//待复核
    public static final String DAE020_2 = "2";//待确认(复核通过)
    public static final String DAE020_3 = "3";//已退取
    public static final String DAE020_4 = "4";//复核不通过

    //人员退费 keytype
    public static final String KEYTYPE_030215000701 = "030215000701";//退费人
    public static final String KEYTYPE_030215000702 = "030215000702";//代办人

    //组织应付计划支付标志
    public static final String AAE117_10 = "10";//等待申请拨付
    public static final String AAE117_20 = "20";//申请拨付
    public static final String AAE117_30 = "30";//拨付在途
    public static final String AAE117_31 = "31";//申请拨付失败
    public static final String AAE117_40 = "40";//实付成功
    public static final String AAE117_41 = "41";//实付失败

    /* 失业保险转移*/
    //打印标志
    public static final String DAE012_0 = "0";//未打印
    public static final String DAE012_1 = "1";//已打印

    //险种类型
    public static final String AAE140_210 = "210";//失业保险

    // 转移方向
    public static final String AAC070_1 = "1";//转入
    public static final String AAC070_2 = "2";//转出

    //转移状态
    public static final String CJC073_1 = "1";//已初审
    public static final String CJC073_2 = "2";//已复核
    public static final String CJC073_3 = "3";//财务确认
    public static final String CJC073_9 = "9";//已撤销


    //面试任务推送短信内容

    /*
        诚信平台
     */
    // 办件状态
    public static final String DEE007_1 = "1";// 预登记
    public static final String DEE007_2 = "2";// 业务受理
    public static final String DEE007_3 = "3";// 已派单

    //时间间隔(一天)
    public static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    //时间间隔(12小时)
    public static final long PERIOD_HALFDAY = 12 * 60 * 60 * 1000;

    //时间间隔(一小时)
    public static final long PERIOD_HOUR = 60 * 60 * 1000;
    //时间间隔(一刻钟)
    public static final long PERIOD_QUARTERHOUR = 15 * 60 * 1000;//15分钟
    //时间间隔(一分钟)
    public static final long PERIOD_MINUTE = 60 * 1000;//1分钟
    //时间间隔(10秒)
    public static final long PERIOD_10 = 10 * 1000;//10秒
    //时间间隔(5秒)
    public static final long PERIOD_5 = 5 * 1000;//5秒
    //时间间隔(1秒)
    public static final long PERIOD_1 = 1 * 1000;//1秒

    //线程数
    public static final int THREAD_COUNT = 5;

    /**
     * 企业缓缴
     */
    //数据来源
    public static final String DBE206_1 = "1";//单个新增
    public static final String DBE206_2 = "2";//批量

    //申请状态
    public static final String DBE227_1 = "1";//审核通过
    public static final String DBE227_2 = "2";//审核退回
    public static final String DBE227_3 = "3";//已受理

    //是否正常缴费
    public static final String DBE229_1 = "1";//是
    public static final String DBE229_2 = "2";//否

    //企业规模确认
    public static final String DBE047_0 = "0";//未确认
    public static final String DBE047_1 = "1";//已确认

    //企业规模数据来源
    public static final String DBE043_1 = "1";//导入
    public static final String DBE043_2 = "2";//初始化
    public static final String DBE043_3 = "3";//单位新增

    //企业规模
    public static final String DBE040_01 = "01";//中小微企业
    public static final String DBE040_04 = "04";//灵活就业

    public static final String CAB005_BIG = "稳岗大";

    //应急稳岗名单导入的单位行业类型
    public static final String[] CONTIGENCYROSTERLIST = {"0651", // 批发业
            "0652", // 零售业
            "0753", // 铁路运输业
            "0754", // 道路运输业
            "0755", // 水上运输业
            "0756", // 航空运输业
            "0757", // 管道运输业
            "0758", // 装卸搬运和运输代理业
            "0760", // 邮政业
            "0861", // 住宿业
            "0862", // 餐饮业
            "1887", // 文化艺术业
            "1888", // 体育
            "1889", // 娱乐业
    };

    //应急稳岗名单企业规模大小
    public static final String DJB013_1 = "1";//大
    public static final String DJB013_2 = "2";//中
    public static final String DJB013_3 = "3";//小
    public static final String DJB013_4 = "4";//微

    //应急稳岗名单匹配状态
    public static final String DJB089_0 = "0";//匹配失败
    public static final String DJB089_1 = "1";//匹配成功
    public static final String DJB089_2 = "2";//已调整

    //应急稳岗名单匹配状态
    public static final String DJB086_0 = "0";//初始化
    public static final String DJB086_1 = "1";//处理成功
    public static final String DJB086_2 = "2";//处理失败
    public static final String DJB086_3 = "3";//处理中

    /* 减免退费登记*/
    // 结算标志
    public static final String CAE084_0 = "0";//未结算
    public static final String CAE084_1 = "1";//已结算
    public static final String CAE084_2 = "2";//结算失败

    //居民医疗集中征收期
    public static final String AAA104_0 = "0";//前台不可维护
    public static final String AAA104_1 = "1";//前台可维护

    public static final String AAB999_ST = "36014498";//视同单位编号

    public static final String CORPDISPOSABLEABSORPTION_MONEY = "118";//一次性吸纳重点单位人员补贴金额

    //一次性吸纳结算状态
    public static final String DJB206_0 = "0";//待结算
    public static final String DJB206_1 = "1";//已结算

    //一次性吸纳业务类型
    public static final String DJB201_1 = "1";//企业
    public static final String DJB201_2 = "2";//重点企业

    //失业补助金金额
    public static final String UMEMP_MONEY_990 = "990";//
    public static final String UMEMP_MONEY_300 = "300";//

    public static List<Map<String, String>> SPECIALCORPS = new ArrayList<>();//特殊单位

    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aab999", "36020704");
        map.put("aab019", "10");//原来是个体户但是校验按企业的来
        map.put("addAac066", "98");//在原来的基础上添加新的参保身份
        SPECIALCORPS.add(map);
    }


    public static final String AC01_SPECIALPERSONNEL_TYPE_01 = "01";//居民养老

    public static final String AE03_AAE077_1 = "1";//托收账户
    public static final String AE03_AAE077_2 = "2";//支付账户
    public static final String AE03_AAE077_3 = "3";//直接支付账户

    public static final String ZJG_CITIZEN_CARD_ACCOUNT_OPENING_BANK = "张家港农村商业银行"; //张家港市民卡开户行

    //Http状态码
    public static final String HTTP_STATUS_CODE_200 = "200";

    public static final int ROLE_0 = 0;                    //无角色
    public static final int ROLE_1 = 1;                    //中心角色
    public static final int ROLE_2 = 2;                    //镇角色
    public static final int ROLE_3 = 3;                    //村角色
    public static final String DIC_CAC400_TOWN = "DIC_CAC400_TOWN"; //镇角色可操作的特殊身份数据字典ID
    public static final String DIC_CAC400_VILLAGE = "DIC_CAC400_VILLAGE"; //镇角色可操作的特殊身份数据字典ID

    /**
     * 政务办件常量
     */
    public static final String AREA_NO = "320582000000";//行政区划代码
    public static final String AREA_NAME = "张家港市";//行政区划名称
    public static final String ORG_ID_RS = "11320582551232000Y";//部门编码 统一信用代码
    public static final String ORG_NAME_RS = "人力资源和社会保障局";//部门名称
    public static final String ORG_ID_YB = "11320582MB1502432H";//部门编码 统一信用代码
    public static final String ORG_NAME_YB = "张家港市医疗保障局";//部门名称
    public static final String COMM_FALSE = "false";//通用 false
    public static final String APPGUID_ZW = "eff1a87d-74fe-4cfc-bb48-c30b61e2c130";//政务
    public static final String APPGUID_ZJG = "6156d330-6184-4ef4-8d4c-9995f2f7fb76";//张家港

    /*企业退休待遇审核*/
    public static final String IC40_AAE016_1 = "1";// 审核通过
    public static final String IC40_AAE016_2 = "2";// 审核不通过
    public static final String IC40_AAE016_0 = "0";// 未审核

    public static final String IC40_DIC041_1 = "1";// 企业养老
    public static final String IC40_DIC041_2 = "2";// 灵活就业

    public static final String IC40_DIC042_0 = "0";// 网上申报
    public static final String IC40_DIC042_1 = "1";// 电子社保卡APP

    /*事项对应查询*/
    public static final String FE22_DFC232_0 = "0";// 人社
    public static final String FE22_DFC232_1 = "1";// 医保
    public static final String FE22_DFC233_0 = "0";// 受办分离
    public static final String FE22_DFC233_1 = "1";// 受办独立

    /*医保关系*/
    public static final String AC03_AAC019_1 = "1";// 审核通过
    public static final String AC03_AAC019_2 = "2";// 审核不通过
    public static final String AC03_AAC019_0 = "0";// 未审核

    public static final String FE23_DFC217_S_XZSPJ = "S_XZSPJ";// 第三方proNo——政务
    public static final String FE23_DFC217_S_SPT = "S_SPT";//第三方proNo——省平台
    /*养老转入审核*/
    public static final String AEC2_AAE016_1 = "1";// 审核通过
    public static final String AEC2_AAE016_2 = "2";// 审核不通过

    public static final String JBD1_CJB009_0 = "0";//初始
    public static final String JBD1_CJB009_1 = "1";//审核通过
    public static final String JBD1_CJB009_2 = "2";//审核不通过

    // 标志
    public static final String BZ_0 = "0";//0-征缴
    public static final String BZ_1 = "1";//1-支付

    //个人医保个人帐户收支流水记录-操作员
    public static final String YB_CZY = "YB";

    //清卡类型  01-死亡  02-退休弃医 03-医保关系转移 04-区外转移 05-终结
    public static final String DKC070_01 = "01";
    public static final String DKC070_02 = "02";
    public static final String DKC070_03 = "03";
    public static final String DKC070_04 = "04";
    public static final String DKC070_05 = "05";

    // 单位性质
    public static final String AAB019_10 = "10";// 企业
    public static final String AAB019_21 = "21";// 全额事业
    public static final String AAB019_22 = "22";// 差额事业
    public static final String AAB019_23 = "23";// 自收自支
    public static final String AAB019_30 = "30";// 机关
    public static final String AAB019_40 = "40";// 社会团体
    public static final String AAB019_50 = "50";// 民办非企业单位
    public static final String AAB019_60 = "60";// 城镇个体工商户
    public static final String AAB019_91 = "91";// 代理挂靠
    public static final String AAB019_92 = "92";// 建筑工伤单位
    public static final String AAB019_99 = "99";// 其他
    public static final String AAB019_71 = "71";// 行政村
    public static final String AAB019_72 = "72";// 居委会
    public static final String AAB019_73 = "73";// 征土工养老单位

    // 20210818 临时添加需要删除 测试页 add by shenli
    //统一征缴业务平台支付--正式环境
    public static final String TYZJ_URL="http://essc.zjgsbzx.com:8802/testzjpay/JsonService.servlet";
    public static final String SERVICE_RS001="RS001";  // 灵活就业人员判断
    public static final String SERVICE_RS002="RS002";  //  查询缴费档次
    public static final String SERVICE_RS004="RS004";  //  人员银行账号校验交易
    public static final String SERVICE_RS005="RS005";  //   灵活就业参保确认交易
    public static final String SERVICE_RS006="RS006";  //人员已缴费查询
    public static final String SERVICE_RS007="RS007";  //人员缴费明细查询
    public static final String SERVICE_RS008="RS008";  //查询待缴记录
    public static final String SERVICE_RS009="RS009";  //在线支付服务
    public static final String SERVICE_RS010="RS010";  //获取当前灵活就业参保信息
    public static final String SERVICE_RS011="RS011";  //修改缴费档次
    public static final String SERVICE_RS012="RS012";  //修改缴费方式
    public static final String SERVICE_RS013="RS013";  //获取代缴金额
    public static final String SERVICE_RS014="RS014";  //代缴
    public static final String SERVICE_RS015="RS015";  //灵活就业缴费中断交易
    public static final String SERVICE_RS016="RS016";  //查询人员当月待缴
    public static final String SERVICE_RS017="RS017";  //查询人员在途缴费数据
    public static final String SERVICE_DSJ001="DSJ001";//人员户籍地判断

    /**
     * 一体化接口
     */
    public static final String ZJGYTH_PERSONADD = "PERSONADD";      //人员新增基本信息
    public static final String ZJGYTH_PORSION = "PERSINSU";      //人员新增参保信息
    public static final String ZJGYTH_PERSINSUINTERRUPT = "PERSINSUINTERRUPT";      //人员中断
    public static final String ZJGYTH_PERSINSURECORD = "PERSINSURECORD";      //人员恢复
    public static final String ZJGYTH_CORPLOAD = "CORPLOAD";      //单位基本信息查询
    public static final String ZJGYTH_PERSONLOAD = "PERSONLOAD";      //查询人员基础登记信息
    public static final String ZJGYTH_PERSONUPDATE = "PERSONUPDATE";      //更新人员基础登记信息
    public static final String ZJGYTH_PERSONLNSULOAD = "PERSONINSULOAD";      //人员参保信息查询
    public static final String ZJGYTH_FIRSTCHECKREGISTER = "FIRSTCHECKREGISTER";      //稳岗返还 初审登记
    public static final String ZJGYTH_CORPFIRSTCHECKREGISTERUEXLOAD = "CORPFIRSTCHECKREGISTERUEXLOAD";      //派遣单位稳岗返还已登记的初审信息加载
    public static final String ZJGYTH_CORPFIRSTCHECKREGISTERUEXCHECK = "CORPFIRSTCHECKREGISTERUEXCHECK";      //派遣单位稳岗返还初审校验
    public static final String ZJGYTH_CORPFIRSTCHECKREGISTERUEXSAVE = "CORPFIRSTCHECKREGISTERUEXSAVE";      //派遣单位稳岗返还初审信息保存
    public static final String ZJGYTH_CORPJB80LOAD = "CORPJB80LOAD"; // 加载Jb80
    public static final String ZJGYTH_PARTTIMEEMPINJURY = "ParttimeEmpInjury"; // 非全日制工伤保险
    public static final String ZJGYTH_BENEFITSSERVICE = "BenefitsService";      //待遇相关
    /**
     * 一体化业务类型
     */
    public static final String AAA121_PRESONADD = "001";      //人员新增
    public static final String AAA121_PERSINSUINTERRUPT = "002";      //人员中断
    public static final String AAA121_PERSINSURECORD = "003";      //人员恢复
    public static final String AAA121_PERSONLOAD = "006";      //人员查询基础信息
    public static final String AAA121_PERSONUPDATE = "007";      //人员更新
    /**
     * 一体化业务办理渠道
     */
    public static final String CAE219_WSSB = "004";      //网上申报
    public static final String CAE219_YTH = "YTH";      //一体化
    /**
     * service服务错误标志
     * FALSE  -1   错误
     * TRUE   0    正确
     */
    public static final String SERVICE_ERROR_FALSE="-1";
    public static final String SERVICE_ERROR_TRUE="0";

    public static final String SZJGYTH_URL = "http://172.16.0.228:9090/ybythtest/JsonService";//"http://172.168.0.150:7003/zjgyth/JsonService";//http://172.168.6.211:8081/syszjgyth/JsonService.servlet
    /**
     * 经办人-一体化系统
     */
    public static final String ZJGYTH_CZY = "YTH";     //经办人

    // RS001
    /**表DW_DWXX.DWDWBH
     * '10003737';  --农商行挂靠单位
     * '30000899';  --农行挂靠单位
     * 30000002    --灵活就业中断人员单位
     * 35046833		--手机自主缴费单位
     * 10000676      --人才中心代理挂靠
     */
    public static final String DW_CENTER_ZRC  = "10000676";
    public static final String DW_BANK_ZRC = "10003737";
    public static final String DW_BANK_ABC = "30000899";
    public static final String DW_BREAK = "30000002";
    public static final String DW_PERSON_APP = "35046833";
    /**
     * 人员年龄
     * 18 人员成年年龄
     * 60 男性退休年龄
     * 50 女性退休年龄
     */
    public static final int BASIC_AGE=18;
    public static final int MAN_MAX_AGE=60;
    public static final int WOMAN_MAX_AGE=50;
    /**
     * 通用标志
     * TRUE_FLAG    1
     * FALSE_FLAG   0
     */
    public static final String TRUE_FLAG="1";
    public static final String FALSE_FLAG="0";
    /**
     * 人员性别
     * 1-男
     * 2-女
     */
    public static final String SEX_MAN="1";//男
    public static final String SEX_WOMAN="2";//女

    /**
     * 人员属性
     * 0000：登记未参保；0001:在职；
     * 0002：离退；0003：失业；
     * 0004：死亡；0005：终结；
     * 0006：其它
     */
    public static final String USER_SX_WCB="0000";
    public static final String USER_SX_ZZ="0001";
    public static final String USER_SX_LT="0002";
    public static final String USER_SX_SY="0003";
    public static final String USER_SX_SW="00004";
    public static final String USER_SX_ZJ="00005";
    public static final String USER_SX_QT="00006";

    /**
     * 中文数字
     */
    public static final String[] CHINA_NUM = { "零", "一", "二", "三", "四", "五", "六", "七", "八","九","十","十一","十二"};
    /**征缴险种
     * 取前7位，第一位养老、第三位生育、第四位医保、第七位大病医疗  1表示选择 2表示未选择
     * 20210830 去除第一位养老
     */
    public static final int[] USER_JF_XZ={4,7};
    public static final String USER_ZJ_XZ="00010010";
    // shenli end

    //单位补缴
    public static final String DWBJ_DWXZ = "0060";      //单位性质-城镇个体工商户
    public static final String JFDC = "DIC_JFDC";   //缴费档次

    /**
     * 年度机关缴费基数上下限
     */
    public static final int JGJFJS_LOWER_2020 = 3800;     //下限
    public static final int JGJFJS_UPPER_2020 = 20586;     //上限
    /**
     * 年度缴费基数上下限
     */
    public static final int JFJS_LOWER_2020 = 3800;     //下限
    public static final int JFJS_UPPER_2020 = 20586;     //上限

    // begin 人社数据字典
    // 单位性质
    public static final String SSB_AAB019_33 = "33";// 基层法律服务所
    public static final String SSB_AAB019_32 = "32";// 公证处
    public static final String SSB_AAB019_39 = "39";// 其他（司法行政）
    public static final String SSB_AAB019_41 = "41";// 外国在华文化中心
    public static final String SSB_AAB019_51 = "51";// 社会团体
    public static final String SSB_AAB019_52 = "52";// 民办非企业单位
    public static final String SSB_AAB019_53 = "53";// 基金会
    public static final String SSB_AAB019_61 = "61";// 外国旅游部门常驻代表机构
    public static final String SSB_AAB019_62 = "62";// 港澳台地区旅游部门常驻内地（大陆）代表机构
    public static final String SSB_AAB019_71 = "71";// 宗教活动场所
    public static final String SSB_AAB019_72 = "72";// 宗教院校
    public static final String SSB_AAB019_81 = "81";// 基层工会
    public static final String SSB_AAB019_89 = "89";// 其他（工会）
    public static final String SSB_AAB019_91 = "91";// 企业
    public static final String SSB_AAB019_93 = "93";// 农民专业合作社
    public static final String SSB_AAB019_A1 = "A1";// 军队事业单位
    public static final String SSB_AAB019_N1 = "N1";// 组级集体经济组织
    public static final String SSB_AAB019_N2 = "N2";// 村级集体经济组织
    public static final String SSB_AAB019_N3 = "N3";// 乡镇级集体经济组织
    public static final String SSB_AAB019_Y1 = "Y1";// 其他
    public static final String SSB_AAB019_13 = "13";// 编办直接管理机构编制的群众团体
    public static final String SSB_AAB019_31 = "31";// 律师执业机构
    public static final String SSB_AAB019_35 = "35";// 仲裁委员会
    public static final String SSB_AAB019_49 = "49";// 其他（文化）
    public static final String SSB_AAB019_59 = "59";// 其他（民政）
    public static final String SSB_AAB019_69 = "69";// 其他（旅游）
    public static final String SSB_AAB019_79 = "79";// 其他（宗教）
    public static final String SSB_AAB019_92 = "92";// 个体工商户
    public static final String SSB_AAB019_A9 = "A9";// 其他（中央军委改革和编制办公室）
    public static final String SSB_AAB019_N9 = "N9";// 其他（农业）
    public static final String SSB_AAB019_29 = "29";// 其他（外交）
    public static final String SSB_AAB019_21 = "21";// 外国常驻新闻机构
    public static final String SSB_AAB019_19 = "19";// 其他（机构编制）
    public static final String SSB_AAB019_12 = "12";// 事业单位
    public static final String SSB_AAB019_11 = "11";// 机关
    public static final String SSB_AAB019_34 = "34";// 司法鉴定机构

    // 单位性质（机关事业）
    public static final String SSB_BAE976_30 = "30";// 机关
    public static final String SSB_BAE976_52 = "52";// 公益一类事业单位
    public static final String SSB_BAE976_51 = "51";// 参照公务员法管理的事业单位
    public static final String SSB_BAE976_54 = "54";// 生产经营类事业单位
    public static final String SSB_BAE976_53 = "53";// 公益二类事业单位
    public static final String SSB_BAE976_70 = "70";// 社会团体
    public static final String SSB_BAE976_71 = "71";// 军队建制单位
    public static final String SSB_BAE976_99 = "99";// 其他单位
    public static final String SSB_BAE976_55 = "55";// 行政执法类事业单位
    public static final String SSB_BAE976_60 = "60";// 尚未分类事业单位

    // 征收方式
    public static final String SSB_AAB033_1 = "1";// 银行托收
    public static final String SSB_AAB033_2 = "2";// 税务代征
    public static final String SSB_AAB033_3 = "3";// 经办机构自收
    public static final String SSB_AAB033_4 = "4";// 银行代扣
    public static final String SSB_AAB033_5 = "5";// 第三方支付平台

    // 单位变更类型
    public static final String SSB_AAB100_10 = "10";// 单位转制
    public static final String SSB_AAB100_99 = "99";// 其他
    public static final String SSB_AAB100_92 = "92";// 社团转制
    public static final String SSB_AAB100_42 = "42";// 基础资料修改
    public static final String SSB_AAB100_41 = "41";// 基础资料登记
    public static final String SSB_AAB100_09 = "09";// 单位注销（含撤销、吊销）
    public static final String SSB_AAB100_01 = "01";// 单位登记
    public static final String SSB_AAB100_02 = "02";// 单位中断缴费
    public static final String SSB_AAB100_03 = "03";// 单位恢复缴费
    public static final String SSB_AAB100_04 = "04";// 单位合并
    public static final String SSB_AAB100_05 = "05";// 单位分立
    public static final String SSB_AAB100_06 = "06";// 单位破产
    public static final String SSB_AAB100_07 = "07";// 单位成建制转出
    public static final String SSB_AAB100_08 = "08";// 单位成建制转入
    // end 人社数据字典

    // 死亡服刑人员录入begin ************************************
    public static final String DSC059_1 = "1";//死亡人员
    public static final String DSC059_2 = "2";//服刑人员
    public static final String DSC059_3 = "3";//交通事故人员
    public static final String DSC059_4 = "4";//留滞人员
    public static final String DSC059_5 = "5";//刑满释放人员
    public static final String DSC059_6 = "6";//开除公职

    // 乡镇审核标志
    public static final String DSC055_0 = "0";//未审核
    public static final String DSC055_1 = "1";//审核通过
    public static final String DSC055_2 = "2";//审核不通过

    // 中心确认标记
    public static final String DSC056_0 = "0";//未审核
    public static final String DSC056_1 = "1";//审核通过
    public static final String DSC056_2 = "2";//审核不通过

    //匹配标志
    public static final String DSC054_0="0"; //未匹配
    public static final String DSC054_1="1";//匹配一致
    public static final String DSC054_2="2";//匹配不一致

    //所属乡镇 对应组织机构
    public static final String ORGID_ORG32058291 = "ORG32058291";//1、保税区  【新增】
    public static final String ORGID_ORG32058201 = "ORG32058201";//2、经济技术开发区（杨舍镇）
    public static final String ORGID_ORG32058204 = "ORG32058204";//3、冶金工业园（锦丰镇）
    public static final String ORGID_ORG32058202 = "ORG32058202";//4、高新技术产业开发区（塘桥镇）
    public static final String ORGID_ORG32058205 = "ORG32058205";//5、乐余镇
    public static final String ORGID_ORG32058206 = "ORG32058206";//6、凤凰镇
    public static final String ORGID_ORG32058207 = "ORG32058207";//7、南丰镇
    public static final String ORGID_ORG32058208 = "ORG32058208";//8、大新镇
    public static final String ORGID_ORG32058209 = "ORG32058209";//9、常阴沙现代农业示范园区
    public static final String ORGID_ORG32058215 = "ORG32058215";//10、双山香山旅游度假区
    public static final String ORGID_ORG32058203 = "ORG32058203";//11、金港街道
    public static final String ORGID_ORG32058292 = "ORG32058292";//12、后塍街道 【新增】
    public static final String ORGID_ORG32058293 = "ORG32058293";//13、德积街道 【新增】
    // 死亡服刑人员录入 end *************************************

    // 便民服务站字典 begin
    // 渠道号
    public final static String CHANNELNO_001 = "001";//中心便民服务站
    public final static String CHANNELNO_002 = "002";//农商行便民服务站
    public final static String CHANNELNO_003 = "003";//其他


    //审批标志
    public final static String KBA004_0 = "0";//未审核
    public final static String KBA004_1 = "1";//审核通过
    public final static String KBA004_2 = "2";//审核不通过
    // 便民服务站字典 end



}
