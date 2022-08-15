package com.xfinsoft.szybgzdtgl.util;

import com.alibaba.fastjson.JSONObject;
import com.eframework.core.config.systemconfig.SystemConfig;
import com.eframework.core.db.dbprocedure.DBProcedureExecution;
import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.dictionary.data.DictionaryListDO;
import com.eframework.module.comp.login.LoginInfo;
import com.eframework.module.comp.login.LoginThreadLocal;
import com.eframework.module.comp.query.bs.IQueryBS;
import com.eframework.module.comp.query.dto.QueryCfgDTO;
import com.eframework.module.comp.role.dto.RoleDTO;
import com.eframework.util.dao.DaoHelper;
import com.eframework.util.spring.SpringHelper;
import com.eframework.util.string.StringHelper;
import com.xfinsoft.szybgzdtgl.constant.Constant;
import io.netty.util.internal.StringUtil;
import org.apache.commons.net.util.Base64;

import javax.transaction.UserTransaction;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public class Util {


    /**
     * 日期格式化
     * d
     *
     * @param source
     * @param sourceFormat
     * @param toFormat
     * @return
     * @throws ParseException
     */
    public static String formatDate(String source, String sourceFormat, String toFormat) throws BusinessException {
        if (StringHelper.isEmpty(source)) {
            throw new BusinessException("数据校验失败,Source为空!");
        }
        if (StringHelper.isEmpty(sourceFormat)) {
            throw new BusinessException("数据校验失败,sourceFormat为空!");
        }
        if (StringHelper.isEmpty(toFormat)) {
            throw new BusinessException("数据校验失败,toFormat为空!");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sourceFormat);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            throw new BusinessException("日期格式转换错误");
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(toFormat);
        return simpleDateFormat1.format(parse);
    }




    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse("2021-10-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
//        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) +1);
        ca.add(Calendar.MONTH,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/01");
        String month = simpleDateFormat.format(ca.getTime());
        System.out.println(month);
    }


    /**
     * 获取格式化的数据库时间
     *
     * @param format
     * @return
     * @throws BusinessException
     */
    public static String getDBDate(String format) throws BusinessException {
        if (StringHelper.isEmpty(format)) {
            throw new BusinessException("数据校验失败,日期格式为空！");
        }
        return DaoHelper.getDBDate(format);
    }


    /**
     * 获取当前登录用户
     *
     * @return
     * @throws BusinessException
     */
    public static String getUserName() throws BusinessException {
        LoginInfo loginInfo = LoginThreadLocal.getLoginInfo();//获取当前登录用户
        if (loginInfo == null) {
            return null;
        }
        if (loginInfo.getLoginUserName() != null) {
            return loginInfo.getLoginUserName();//用户名
        } else {
//            return null;
            throw new BusinessException("获取登录信息失败，请重新登录！");
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     * @throws BusinessException
     */
    public static String getUserId() throws BusinessException {
        LoginInfo loginInfo = LoginThreadLocal.getLoginInfo();//获取当前登录用户
        if (loginInfo == null) {
            return null;
        }
        if (loginInfo != null) {
            return loginInfo.getLoginUserId();//用户名
        } else {
//            return null;
            throw new BusinessException("获取登录信息失败，请重新登录！");
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     * @throws BusinessException
     */
    public static String getSysdate() throws BusinessException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }



    /**
     * 获取当前医保年度
     *
     * @return
     * @throws BusinessException
     */
    public static String getNowMedicalYear() throws BusinessException {
        int month = Integer.parseInt(getDBDate(Constant.FORMAT_MM));
        int year = Integer.parseInt(getDBDate(Constant.FORMAT_YEAR));
//        if (month < 7) {    //若当前年份大于7月份则年份加一
//            year = year - 1;
//        }
        return String.valueOf(year);
    }


    /**
     * 是否退休
     *
     * @return
     * @throws BusinessException
     */
    public static boolean isRetire(String aac006, String aac004) throws BusinessException {

        if (StringHelper.isEmpty(aac006)) {
            throw new BusinessException("数据校验失败，出生日期为空！");
        }
        if (StringHelper.isEmpty(aac004)) {
            throw new BusinessException("数据校验失败，性别为空！");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");

        int Myear = Integer.parseInt(Constant.AGE_RET_M);//男 退休年份
        int Wyear = Integer.parseInt(Constant.AGE_RET_F);//女 退休年

        Date date_bir = null;
        try {
            date_bir = sdf.parse(aac006);
        } catch (ParseException e) {
            throw new BusinessException("日期格式错误，请确认是yyyy/mm/dd格式！");
        }
        int year_now = Integer.valueOf(getDBDate(Constant.FORMAT_YEAR));    //获取年
        int month_now = Integer.valueOf(getDBDate(Constant.FORMAT_MM));   //获取月份

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_bir);//出生日期

        int year_bir = calendar.get(Calendar.YEAR);    //获取年
        int month_bir = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int age = 0;

        if (month_bir <= month_now) {
            age = year_now - year_bir;

        } else {// 当前用户还没过生日
            age = year_now - year_bir - 1;
        }

        if (age >= Myear && Constant.AAC004_1.equals(aac004)) {//男
            return true;
        }
        //女
        return age >= Wyear && Constant.AAC004_2.equals(aac004);
    }


    /**
     * 通过身份证号码获取出生日期、性别、年龄
     *
     * @param certificateNo
     * @return 'sexCode'性别 |'age'年龄 | 'birthday'返回的出生日期格式：1990/01/01
     */
    public static Map<String, String> getBirAgeSex(String certificateNo) {
        String birthday = "";
        int age = 0;
        String sexCode = "";
        Date date = new Date();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "/"
                    + certificateNo.substring(8, 10) + "/"
                    + certificateNo.substring(10, 12);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3)) % 2 == 0 ? "2" : "1";

            // 身份证上的年份(15位身份证为1980年前的)
            String uyear = "19" + certificateNo.substring(6, 8);
            // 身份证上的月份
            String uyue = certificateNo.substring(8, 10);
            // 当前年份
            String fyear = sdf.format(date).substring(0, 4);
            // 当前月份
            String fyue = sdf.format(date).substring(5, 7);
            if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) {
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
                // 当前用户还没过生日
            } else {
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
            }

            //age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "/"
                    + certificateNo.substring(10, 12) + "/"
                    + certificateNo.substring(12, 14);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "2" : "1";

            // 身份证上的年份
            String uyear = certificateNo.substring(6).substring(0, 4);
            // 身份证上的月份
            String yue = certificateNo.substring(10).substring(0, 2);
            // 当前年份
            String fyear = sdf.format(date).substring(0, 4);
            // 当前月份
            String fyue = sdf.format(date).substring(5, 7);
            // 当前月份大于用户出身的月份表示已过生日
            if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) {
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);

            } else {// 当前用户还没过生日
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) - 1;
            }

            //age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", String.valueOf(age));
        map.put("sexCode", sexCode);
        return map;
    }


    /**
     * 获取医疗补缴参保年度
     *
     * @return
     * @throws BusinessException
     */
    public static List<Integer> getMedicalPayBackYear() throws BusinessException {
        Integer year = Integer.valueOf(getDBDate(Constant.FORMAT_YEAR));
        Integer month = Integer.valueOf(getDBDate(Constant.FORMAT_MM));
        List<Integer> list = new ArrayList<Integer>();
        if (month >= 6) {     //取
            for (int i = 1; i <= 2; i++) {
                list.add(year - i);
            }
        } else {
            for (int i = 0; i <= 1; i++) {
                list.add(year - i);
            }
        }
        return list;
    }

    /**
     * 取服务器事务。
     *
     * @return UserTransaction                        XA事务
     * @throws BusinessException 系统异常
     */
    public static UserTransaction getUserTransaction() throws BusinessException {
        return SystemConfig.getConfigXML().getUserTransaction();
    }


    /**
     * 调用存过
     *
     * @param sid               数据库SID
     * @param owner             数据库用户
     * @param packageName       包名称
     * @param procedureName     过程名称
     * @param inParametersValue 传入参数List
     * @throws BusinessException SQL执行异常
     */
    public static void doProcedure(String sid, String owner, String packageName, String procedureName, List<Object> inParametersValue) throws BusinessException {
        try {
//            getUserTransaction().commit();
//            getUserTransaction().begin();

            if ("PRO_INTERFACE_LABOUR".equals(procedureName)) {
                //xr 0806 医保独立化 不要就管模块
                return;
            }

            //1）调用存过
            Map<String, Object> map = DBProcedureExecution.executeProcedure(sid, owner, packageName, procedureName, inParametersValue);

            //2）判断入参
            if (map == null || map.get("PO_RESULT") == null) {
                throw new BusinessException("存过返回参数错误");
            }

            //3）出参判断
            String result = map.get("PO_RESULT").toString();
            if (Constant.RESULT_FAILURE.equals(result)) {
                String errorMsg = "存过错误，但无报错信息！";
                if (map.get("PO_MSG") != null) {
                    errorMsg = map.get("PO_MSG").toString();
                }
                throw new BusinessException(errorMsg);
            }

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * <p>===========================================================================================</p>
     * <p>将一个参数插入到分割符字符串的某个位置</p>
     * <pre><p>            ====================begin=====================</p></pre>
     * <pre><p>            1.重新整理</p></pre>
     * <pre><p>            ====================end=======================</p></pre>
     *
     * @param str       分隔符数据包
     * @param separator 分隔符
     * @param pos       位置(0:最前面 -1:最后面 >0:插入的位置 其它:无效)
     * @param parameter 插入参数
     * @return String            String
     * <p>===============================================================================</p>
     */
    public static String insertParameter(String str, String separator, int pos,
                                         String parameter) throws BusinessException {
        String indexStr;
        String headStr = "", rearStr = "";
        int index = 0, seplen = 0;
        int ipos = 0;
        //去空
        if (parameter == null || "undefined".equals(parameter)) {
            parameter = "";
        }

        try {
            //1.无效的分隔符
            if ((separator == null) || (separator.length() == 0)) {
                throw new BusinessException("无效的数据报分隔符〖空分隔符〗！");
            }

            //2.空数据报，无法找到插入位置；
            if ((str == null || str.length() == 0) && pos != 0 && pos != -1) {
                throw new BusinessException("空数据报，参数插入位置〖不等于零或-1〗无效！");
            }

            //3.参数是否为空
            if (parameter == null) {
                throw new BusinessException("无效数据报参数〖空参数〗！");
            }

            //4.检查参数中是否含有分隔符
            if (parameter.indexOf(separator) != -1) {
                throw new BusinessException("参数中含分隔符〖" + separator + "〗！");
            }

            //5.追加在前面
            if (pos == 0) {
                if (str == null || str.length() == 0) {
                    return parameter + separator;
                } else {
                    return parameter + separator + str;
                }
            }

            seplen = separator.length();
            indexStr = str;
            index = indexStr.indexOf(separator);
            while (index != -1) {
                ipos = ipos + 1;
                headStr = indexStr.substring(0, index + seplen);
                if (ipos == pos) return headStr + parameter + separator +
                        indexStr.substring(index + seplen);
                index = indexStr.indexOf(separator, index + seplen);
            }

            rearStr = indexStr.substring(headStr.length());
            if (rearStr != null && rearStr.length() != 0) {
                ipos = ipos + 1;
            }
            if (ipos == pos) {
                return headStr + rearStr + separator + parameter + separator;
            }
            //追加在后面
            if (pos == -1) {
                if (rearStr != null && rearStr.length() != 0) {
                    return headStr + rearStr + separator + parameter +
                            separator;
                } else {
                    return headStr + parameter + separator;
                }
            }

            //出错了，没有找到插入位置
            throw new BusinessException("在数据报中没有找到参数的插入位置〖最大位置:" + ipos + ";插入位置:" + pos + "〗！");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * String日期转换Date
     *
     * @param sdate
     * @param formatter
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String sdate, String formatter) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(formatter);
            Date date = dateFormat.parse(sdate);
            return date;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 获取当前自然年
     *
     * @return
     */
    public static String getSysYear() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(date);
        return year;
    }

    public static String getSysMon() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String mon = simpleDateFormat.format(date);
        return mon;
    }

    /**
     * 获取前两年以及当前年度的时间
     *
     * @return
     */
    public static List<DictionaryListDO> getYear() {
        //获取后两年年度
        Integer year = Integer.valueOf(Util.getDBDate(Constant.FORMAT_YEAR));
        List<DictionaryListDO> list = new ArrayList<DictionaryListDO>();
        DictionaryListDO dictionaryListDO = null;
        for (int i = 0; i < 3; i++) {
            dictionaryListDO = new DictionaryListDO();
            dictionaryListDO.setName(year - i + "");
            dictionaryListDO.setCode(year - i + "");
            list.add(dictionaryListDO);

        }
        return list;

    }

    /**
     * 获取当前月份的最后一天
     *
     * @return
     * @throws BusinessException
     */
    public static Date getLastDay() throws BusinessException {
        Date toDay = new Date();
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.setTime(toDay);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    /**
     * 将字符串日期增加 n天或n个月或n年
     *
     * @param s       日期
     * @param n       增加数
     * @param format  日期格式
     * @param addtype 1-天，2-月，3-年
     * @return
     * @throws BusinessException
     */
    public static String dateAdd(String s, int n, String format, String addtype) throws BusinessException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化日期
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(s));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (Constant.ADDTYPE_DAY.equals(addtype)) {
            cd.add(Calendar.DATE, n);//n=1代表增加一个天
        } else if (Constant.ADDTYPE_MON.equals(addtype)) {
            cd.add(Calendar.MONTH, n);//n=1代表增加一个月
        } else if (Constant.ADDTYPE_YEAR.equals(addtype)) {
            cd.add(Calendar.YEAR, n);//n=1代表增加一个年
        }
        return sdf.format(cd.getTime());
    }


    /**
     * 获取2018到当前年度的列表
     *
     * @return
     */
    public static List<DictionaryListDO> getYears() {
        //获取后两年年度
        Integer year = Integer.valueOf(Util.getDBDate(Constant.FORMAT_YEAR));
        List<DictionaryListDO> list = new ArrayList<>();
        DictionaryListDO dictionaryListDO = null;
        int limit = year - 2018;
        for (int i = 0; i < limit; i++) {
            dictionaryListDO = new DictionaryListDO();
            dictionaryListDO.setName(year - i + "");
            dictionaryListDO.setCode(year - i + "");
            list.add(dictionaryListDO);

        }
        return list;

    }


    /**
     * 获取两个时间中的所有月份（包括结束和开始月份）
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws BusinessException
     */
    public static List<String> getMonthBetween(Date startTime, Date endTime, String returnFormat) throws BusinessException {
        if (startTime == null || endTime == null) {
            throw new BusinessException("获取月份失败，开始和结束时间都不能为空！");
        }
        List<String> months = new ArrayList<String>();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startTime);
        start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), 1);

        end.setTime(endTime);
        end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 2);

        Calendar curr = start;
        SimpleDateFormat sdf = new SimpleDateFormat(returnFormat);
        while (curr.before(end)) {
            months.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return months;

    }

    /**
     * 获取前一个月
     *
     * @return 返回 mm
     */
    public static String getSysProMonth() {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
//        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
        ca.add(Calendar.MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String month = simpleDateFormat.format(ca.getTime());

        return month;
    }

    /**
     * @param mon
     * @return 返回 yyyy/MM/01
     */
    public static String getSysProMonth(int mon) {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH,-mon);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/01");
        String month = simpleDateFormat.format(ca.getTime());

        return month;
    }

    /**
     * 获取前几个月
     * @param mon
     * @return 返回 yyyy/MM/dd
     */
    public static String getSysProMonthDay(int mon) {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH,-mon);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String month = simpleDateFormat.format(ca.getTime());

        return month;
    }

    /**
     * 获取提前月份的年月
     *
     * @param nyStr
     * @param format
     * @param mon
     * @return
     */
    public static String getProMonth(String nyStr, String format, int mon) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(nyStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - mon);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/01");
        String month = simpleDateFormat1.format(ca.getTime());

        return month;
    }


    /**
     * obj传换为bigDecimal （若传入的字符串为空则返回0）
     *
     * @param obj
     * @return
     * @throws BusinessException
     */
    public static BigDecimal ObjectToBigDecimal(Object obj) throws BusinessException {
        if (obj == null || obj.equals("")) {
            return new BigDecimal("0");
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            throw new BusinessException("请确认传入的对象是否为数字！");
        }
    }

    /**
     * obj传换为String （若传入的字符串为空则返回null）
     *
     * @param obj
     * @return
     * @throws BusinessException
     */
    public static String ObjectToString(Object obj) throws BusinessException {
        if (obj == null) {
            return null;
        }
        try {
            return JSONObject.toJSON(obj).toString();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 获取前一个月
     *
     * @return
     */
    public static String getSysProMonth(String format) {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String month = simpleDateFormat.format(ca.getTime());

        return month;
    }

    /**
     * 获取随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getIntRandom(int min, int max, List<Integer> excluding) {
        if (excluding != null && max <= excluding.size()) {

            boolean isIncluding = false;
            for (int i = min; i <= max; i++) {
                if (!excluding.contains(i)) {
                    isIncluding = true;
                    break;
                }
            }

            if (!isIncluding) {
                throw new BusinessException("获取随机数失败，传入的排除数包含的所有生成的数字！");
            }
        }

        //创建Random类对象
        Random random = new Random();

        //产生随机数
        while (true) {
            int num = random.nextInt(max - min + 1) + min;
            if (excluding != null && excluding.size() != 0) {
                if (!excluding.contains(num)) {
                    return num;
                }
            } else {
                return num;
            }

        }
    }


    /**
     * 获取上年度
     *
     * @return
     */
    public static String getPreYear() {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.YEAR, ca.get(Calendar.YEAR) - 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(ca.getTime());

        return year;

    }




    /**
     * 获取从开始年度到当前年度的 年度列表
     *
     * @param startYear 开始年度 (不包括开始年度)
     * @return
     */
    public static List<DictionaryListDO> getYearsBeginTo(int startYear) {
        //获取后两年年度
        Integer year = Integer.valueOf(Util.getDBDate(Constant.FORMAT_YEAR));
        List<DictionaryListDO> list = new ArrayList<>();
        DictionaryListDO dictionaryListDO = null;
        int limit = year - startYear;
        for (int i = -1; i < limit; i++) {
            dictionaryListDO = new DictionaryListDO();
            dictionaryListDO.setName(year - i + "");
            dictionaryListDO.setCode(year - i + "");
            list.add(dictionaryListDO);

        }
        return list;

    }


    public static DictionaryListDO getDiccode(String dicId, String name) {
        String sql = "select * from eframe.sys_dictionary_list a where a.dicid='" + dicId + "' and name='" + name + "'";
        return DaoHelper.findSimpleObjectBySql(sql, DictionaryListDO.class);
    }

    public static DictionaryListDO getDictionaryList(String dicId, String code) {
        String sql = "select * from eframe.sys_dictionary_list a where a.dicid='" + dicId + "' and code='" + code + "'";
        return DaoHelper.findSimpleObjectBySql(sql, DictionaryListDO.class);
    }

    /**
     * 获取开始到结束月份不存在月份List
     * 目前主要用于退伍军人获取未缴月份
     *
     * @param startmon yyyy/mm
     * @param endmon   yyyy/mm
     * @param havemons yyyy/mm
     * @return
     */
    public static List<String> getDifferDate(String startmon, String endmon, List<String> havemons) {
        if (StringHelper.isEmpty(startmon)) {
            throw new BusinessException("数据校验失败,startmon为空!");
        }
        if (StringHelper.isEmpty(endmon)) {
            throw new BusinessException("数据校验失败,endmon为空!");
        }
        List<String> result = new ArrayList<String>();
        try {
            Date d1 = new SimpleDateFormat("yyyy/MM").parse(startmon);//定义起始日期
            Date d2 = new SimpleDateFormat("yyyy/MM").parse(endmon);//定义结束日期
            Calendar dd = Calendar.getInstance();//定义日期实例
            dd.setTime(d1);//设置日期起始时间
            while (dd.getTime().before(d2)) {//判断是否到结束日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
                String str = sdf.format(dd.getTime());
                result.add(str);
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }
            result.add(endmon.substring(0, 7));
            //输出日期结果
            // 差集 去除已有月份
            result.removeAll(havemons);
        } catch (Exception e) {
            System.out.println("获取月份差集异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 计算两个时间相隔的月份
     *
     * @Param: [beginTime, endTime, format]
     * @Return: int
     * @Author: ZSW
     * @Date: 2020/4/21 9:45
     */
    public static int getMonth(String beginTime, String endTime, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d1 = sdf.parse(beginTime);
        Date d2 = sdf.parse(endTime);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int a = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + 1;
        return a;
    }

    public static void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss:SSS");
        System.out.println(simpleDateFormat.format(new Date()));
    }


    /**
     * 校验传入的月份是否为连贯的月份
     *
     * @param months
     * @return
     */
    public static boolean checkIsContinuationMonth(List<String> months, String format) throws ParseException {
        if (months == null || months.size() == 0) {
            return false;
        }
        //取出最大和最小的月份
        Collections.sort(months);//升序
        String minMonth = months.get(0);
        String maxMonth = months.get(months.size() - 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        List<String> monthBetween = getMonthBetween(simpleDateFormat.parse(minMonth), simpleDateFormat.parse(maxMonth), format);

        return months.size() == monthBetween.size();
    }

    /**
     * 根据参数进行sql查询
     *
     * @param queryNo        查询号
     * @param params         参数
     * @param whereCondition 查询条件
     * @param checkPackage   界面勾选
     * @return
     */
    public static List<Map> loadSql(String queryNo, List<Map<String, String>> params, String whereCondition, String checkPackage) {
        IQueryBS queryBS = (IQueryBS) SpringHelper.getBean("QueryBS");
        QueryCfgDTO queryCfgDTO = queryBS.loadQueryCfg(queryNo);
        StringBuilder querySql = new StringBuilder(queryCfgDTO.getQuerySql());//取通用sql中的sql语句
        for (Map<String, String> map : params) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String mapKey = entry.getKey(); //查询中的param名，例'〈aac999〉'
                String mapValue = entry.getValue();//对应的param值
                String param = "〈" + mapKey + "〉";

                //将sql中的params字段替换成对应的值
                querySql = new StringBuilder(querySql.toString().replaceAll(param, mapValue));

            }
        }
        //拼接 whereCondition
        querySql = new StringBuilder("select * from (" + querySql + ") where " + whereCondition);
        //判断 checkPackage 勾选情况
        //全选'y|~~|2|~~|15|~~|16|~~|' y表示全选|~~|表示不勾选的有2条|~~|具体取消勾选的selectedkey
        //单个勾选 "n|~~|2|~~|15|~~|16|~~|" n表示非全选|~~|勾选的2条|~~|具体勾选的selectedkey
        //未勾选'"n|~~|0|~~|"' 表示未勾选数据
        if (StringHelper.isNotEmpty(checkPackage)
                && !checkPackage.equals("n|~~|0|~~|")) {
            if (checkPackage.indexOf("y") > 0) {
                //全选
                querySql = new StringBuilder("select * from (" + querySql + ") where SELECTEDKEY not in ( ");
            } else if (checkPackage.indexOf("n") > 0) {
                //未全选
                querySql = new StringBuilder("select * from (" + querySql + ") where SELECTEDKEY  in ( ");
            }
            List<String> list = StringHelper.splitString(checkPackage, "|~~|");
            for (int i = 0; i < list.size(); i++) {

                if (StringHelper.isEmpty(list.get(i))) {
                    continue;
                }

                if (i > 1) { //从第三个开始
                    querySql.append("'");
                    querySql.append(list.get(i)).append("'");
                }
                if (i != list.size() - 1) {
                    querySql.append(",");
                }

            }
            querySql.append(")");
            return DaoHelper.findBySql(querySql.toString());
        }
        return DaoHelper.findBySql(querySql.toString());
    }

    /***
     * 根据出生日期获取年龄 （精确到年）
     * @param birthday
     * @return
     */
    public static Integer getAgeByBirthDayAndYear(String birthday) {
        if (StringHelper.isEmpty(birthday))
            throw new BusinessException("出生日期不能为空！");
        Integer substring = Integer.parseInt(birthday.substring(0, 4));//出生年度
        Integer yyyy = Integer.parseInt(Util.getDBDate("yyyy"));
        return yyyy - substring;
    }



    /**
     * 输入出生日期获取年龄一共多少月
     *
     * @param aac006
     * @return
     */
    public static int getAgeMonth(String aac006) {

        if (StringHelper.isEmpty(aac006)) {
            throw new BusinessException("年龄校验失败，出生日期为空！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date_bir = null;
        try {
            date_bir = sdf.parse(aac006);
        } catch (ParseException e) {
            throw new BusinessException("日期格式错误，请确认是yyyy/mm/dd格式！");
        }
        Calendar calendar = Calendar.getInstance();
        int year_now = Integer.parseInt(Util.getDBDate(Constant.FORMAT_YEAR));    //获取年
        int month_now = Integer.parseInt(Util.getDBDate(Constant.FORMAT_MM));     //获取月份


        calendar.setTime(date_bir);//出生日期


        int year_bir = calendar.get(Calendar.YEAR);    //获取年
        int month_bir = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int age = year_now - year_bir;
        int totalmouth = 0;
        if (month_now < month_bir) {  //未到出生的月份
            age -= 1;
            totalmouth = age * 12 + 12 - month_bir + month_now;
        } else {
            totalmouth = age * 12 + month_now - month_bir;
        }
        return totalmouth;
    }

    /**
     * 科学数字转String
     * 一般用于excel导入
     *
     * @param input
     * @return
     */
    public static String ScientificFiguresToString(String input) {
        Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)[Ee]{1}[\\+-]?[0-9]*");
        DecimalFormat ds = new DecimalFormat("0");
        if (pattern.matcher(input).matches()) {
            return ds.format(Double.parseDouble(input)).trim();
        }
        return input;
    }

    /**
     * 校验人员姓名
     *
     * @param name
     * @return
     * @throws BusinessException
     */
    public static boolean checkPersonName(String name) throws BusinessException {
        if (StringHelper.isEmpty(name)) {
            throw new BusinessException("传入的姓名参数为空！");
        }
        String pattern = "[\\u4E00-\\u9FA5][\\u4E00-\\u9FA5|·]*[\\u4E00-\\u9FA5]";
        return Pattern.matches(pattern, name);
    }

    /**
     * 校验手机号码
     *
     * @param num
     * @return
     * @throws BusinessException
     */
    public static boolean checkPhoneNum(String num) throws BusinessException {
        if (StringHelper.isEmpty(num)) {
            throw new BusinessException("传入的姓名参数为空！");
        }
        String pattern = "(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\\d{8})";
        return Pattern.matches(pattern, num);
    }

    /**
     * 根据人员出身日期和退休年龄获取退休时间
     *
     * @param birthday      yyyy/MM/dd 或者 yyyy-MM-dd
     * @param retirementAge
     * @return yyyy/MM
     * @throws BusinessException
     */
    public static String getRetireDateTime(String birthday, int retirementAge) throws BusinessException {
        if (StringHelper.isEmpty(birthday)) throw new BusinessException("人员的出生日期为空！");
        if (birthday.trim().length() < 4) throw new BusinessException("出生日期错误！");
        int year = Integer.parseInt(birthday.substring(0, 4)) + retirementAge;
        return year + birthday.substring(5);
    }

    /**
     * Date转LocalDate
     *
     * @param date
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获取指定日期下个月的第一天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String getFirstDayOfNextMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期上个月的第一天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String getFirstDayOfLastMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, -1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期上个月的最后一天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String getLastDayOfLastMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date lastDateOfPrevMonth = new Date();
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            //设置为指定日期
            c.setTime(date);
            //指定日期月份减去一
            c.add(Calendar.MONTH, -1);
            //指定日期月份减去一后的 最大天数
            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
            //获取最终的时间
            lastDateOfPrevMonth = c.getTime();

        } catch (ParseException e) {

        }
        return sdf.format(lastDateOfPrevMonth);
    }

    /**
     * 将制定地址文件转换成Base64 String字符串
     *
     * @param urlPath
     * @return
     * @throws BusinessException
     */
    public static String fielToBase64(String urlPath) throws BusinessException {
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            //设置超时
            httpURLConnection.setConnectTimeout(1000 * 30);
            //设置请求方式，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");    // 加上这句话解决问题
            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            // 控制台打印文件大小
//            System.out.println(urlPath+"下载的文件大小为:" + fileLength / 1024 + "KB");
            //在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(httpURLConnection.getInputStream());
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            //创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            //apache公司的API
            return Base64.encodeBase64String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bin.close();
                //关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 医保独立化有效险种
     */
    public static String ValidAAE140s = "'907','310','330','510'";

    /**
     * 校验字符串长度
     *
     * @param length  校验长度
     * @param str     校验字符串
     * @param strName 字符串中文名
     * @throws BusinessException
     */
    public static void checkStringLength(int length, String str, String strName) throws BusinessException {

        if (StringUtil.isNullOrEmpty(str)) {
            return;
        }

        if (str.length() > length) {
            throw new BusinessException(strName + "长度必须小于" + length);
        }
    }

    /**
     * 校验 null 字符串
     * @param inStr
     * @return
     */
    public static String checkNullStr(String inStr){
        if (!StringUtil.isNullOrEmpty(inStr)){

            if ("null".equals(inStr.trim())){
                return "";
            }else {
                return inStr;
            }
        }
        return "";
    }
}
