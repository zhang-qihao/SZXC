package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.pers;

import com.eframework.web.mvc.controller.BaseForm;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class PersForm  extends BaseForm {
    /*
    Ac01_gw 指标信息
    */
    private String ac01id;//	number(16)	y		人员信息表id
    private String aac003;//	varchar2(100)	y		姓名
    private String aac004;//	varchar2(4)	y		性别
    private String aac147;//	varchar2(50)	y		证件号码
    private String aac006;//	varchar2(8)	y		出生日期
    private String aac029;//	varchar2(20)	y		政治面貌
    private String aac039;//	varchar2(20)	y		人员性质
    private String aac049;//	varchar2(20)	y		职务编码
    private String aac050;//	varchar2(50)	y		职务名称
    private String aac051;//	varchar2(30)	y		任该职务时间
    private String aac059;//	varchar2(20)	y		职级
    private String  aac061;//	varchar2(30)	y		任该职级时间
    private String ks002;//	varchar2(20)	y		科室编码
    private String ks003;//	varchar2(100)	y		科室名称
    private String userid;//	varchar2(32)	y		用户名
    private Date aae036;//	date	y		创建时间
    private String aac060;//	varchar2(30)	y		职级名称
    private String ckc005;//	varchar2(2)	y		状态（00-正常，01-离岗）
    private String numid;//人员排序序号

    /*
    附件显示
     */
    //private MultipartFile file; //文件
    List<MultipartFile> file;
    MultipartFile document;
    private String base64;  //文件BASE64字符串

    private String type;    //操作类型

    public String getAc01id() {
        return ac01id;
    }

    public void setAc01id(String ac01id) {
        this.ac01id = ac01id;
    }

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003;
    }

    public String getAac004() {
        return aac004;
    }

    public void setAac004(String aac004) {
        this.aac004 = aac004;
    }

    public String getAac147() {
        return aac147;
    }

    public void setAac147(String aac147) {
        this.aac147 = aac147;
    }

    public String getAac006() {
        return aac006;
    }

    public void setAac006(String aac006) {
        this.aac006 = aac006;
    }

    public String getAac029() {
        return aac029;
    }

    public void setAac029(String aac029) {
        this.aac029 = aac029;
    }

    public String getAac039() {
        return aac039;
    }

    public void setAac039(String aac039) {
        this.aac039 = aac039;
    }

    public String getAac049() {
        return aac049;
    }

    public void setAac049(String aac049) {
        this.aac049 = aac049;
    }

    public String getAac050() {
        return aac050;
    }

    public void setAac050(String aac050) {
        this.aac050 = aac050;
    }

    public String getAac051() {
        return aac051;
    }

    public void setAac051(String aac051) {
        this.aac051 = aac051;
    }

    public String getAac059() {
        return aac059;
    }

    public void setAac059(String aac059) {
        this.aac059 = aac059;
    }

    public String getAac061() {
        return aac061;
    }

    public void setAac061(String aac061) {
        this.aac061 = aac061;
    }

    public String getKs002() {
        return ks002;
    }

    public void setKs002(String ks002) {
        this.ks002 = ks002;
    }

    public String getKs003() {
        return ks003;
    }

    public void setKs003(String ks003) {
        this.ks003 = ks003;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getAae036() {
        return aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getAac060() {
        return aac060;
    }

    public void setAac060(String aac060) {
        this.aac060 = aac060;
    }

    public String getCkc005() {
        return ckc005;
    }

    public void setCkc005(String ckc005) {
        this.ckc005 = ckc005;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }
}
