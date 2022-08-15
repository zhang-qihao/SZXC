package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Ks01DO  implements Serializable {
    @Id
    @Column(name = "KS001", length = 32, nullable = false)
    private String ks001;//	varchar2(32)	y		科室表id

    @Column(name = "KS003", length = 200)
    private String ks003;//	varchar2(200)	y		科室名称

    @Column(name = "ks009", length = 32)
    private String ks009;//	varchar2(32)	y		科室分管主任id

    @Column(name = "ks010", length = 50)
    private String ks010;//	varchar2(50)	y		科室分管主任姓名

    @Column(name = "ks019", length = 32)
    private String ks019;//	varchar2(32)	y		科室负责人id

    @Column(name = "ks020", length = 50)
    private String ks020;//	varchar2(50)	y		科室负责人姓名

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AAE036", length = 10, nullable = true)
    private Date aae036;//	date	y		创建时间

    @Column(name = "CAE249", length = 50)
    private String cae249;//	varchar2(50)	y		创建人id

    @Column(name = "CAE251", length = 100)
    private String cae251;//	varchar2(100)	y		创建人名称

    @Column(name = "CKC005", length = 2)
    private String ckc005;//	varchar2(2)	y		状态（00-已启用，01-已停用）

    @Column(name = "NUMID", length = 20)
    private String numid;//	varchar2(20)	y		序号

    @Column(name = "AAZ002", length = 200)
    private String aaz002;//	varchar2(32)	y		日志id

    @Column(name = "KS002", length = 1000)
    private String ks002;//	varchar2(1000)	y		科室职责

    public String getKs001() {
        return ks001;
    }

    public void setKs001(String ks001) {
        this.ks001 = ks001;
    }

    public String getKs003() {
        return ks003;
    }

    public void setKs003(String ks003) {
        this.ks003 = ks003;
    }

    public String getKs009() {
        return ks009;
    }

    public void setKs009(String ks009) {
        this.ks009 = ks009;
    }

    public String getKs010() {
        return ks010;
    }

    public void setKs010(String ks010) {
        this.ks010 = ks010;
    }

    public String getKs019() {
        return ks019;
    }

    public void setKs019(String ks019) {
        this.ks019 = ks019;
    }

    public String getKs020() {
        return ks020;
    }

    public void setKs020(String ks020) {
        this.ks020 = ks020;
    }

    public Date getAae036() {
        return aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getCae249() {
        return cae249;
    }

    public void setCae249(String cae249) {
        this.cae249 = cae249;
    }

    public String getCae251() {
        return cae251;
    }

    public void setCae251(String cae251) {
        this.cae251 = cae251;
    }

    public String getCkc005() {
        return ckc005;
    }

    public void setCkc005(String ckc005) {
        this.ckc005 = ckc005;
    }

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }

    public String getAaz002() {
        return aaz002;
    }

    public void setAaz002(String aaz002) {
        this.aaz002 = aaz002;
    }

    public String getKs002() {
        return ks002;
    }

    public void setKs002(String ks002) {
        this.ks002 = ks002;
    }
}
