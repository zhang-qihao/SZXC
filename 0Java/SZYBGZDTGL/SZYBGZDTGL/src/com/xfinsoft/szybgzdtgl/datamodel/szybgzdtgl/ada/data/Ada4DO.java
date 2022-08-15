package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@MappedSuperclass
public class Ada4DO  implements Serializable {
    @Id
    @Column(name = "ADA013", length = 32, nullable = false)
    private String ada013;//	varchar2(32)			对象角色变更明细id

    @Column(name = "ADA003", length = 32, nullable = false)
    private String ada003;//	varchar2(32)			对象角色变更事件id

    @Column(name = "GWID", length = 32, nullable = false)
    private String gwid;//	varchar2(32)			实体类主键id

    @Column(name = "ADA137", length = 32, nullable = false)
    private String ada137;//	varchar2(32)			实体类对象id

    @Column(name = "ADA182", length = 10, nullable = false)
    private String ada182;//	varchar2(10)			角色属性指标

    @Column(name = "ADA181", length = 100)
    private String ada181;//	varchar2(100)	y		角色属性含义

    @Column(name = "ADA127", length = 1000)
    private String ada127;//	varchar2(1000)	y		原值

    @Column(name = "ADA152", length = 1000)
    private String ada152;//	varchar2(1000)	y		新值

    @Column(name = "AAZ002", length = 32)
    private String aaz002;//	varchar2(32)	y		日志id

    public String getAda013() {
        return ada013;
    }

    public void setAda013(String ada013) {
        this.ada013 = ada013;
    }

    public String getAda003() {
        return ada003;
    }

    public void setAda003(String ada003) {
        this.ada003 = ada003;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getAda137() {
        return ada137;
    }

    public void setAda137(String ada137) {
        this.ada137 = ada137;
    }

    public String getAda182() {
        return ada182;
    }

    public void setAda182(String ada182) {
        this.ada182 = ada182;
    }

    public String getAda181() {
        return ada181;
    }

    public void setAda181(String ada181) {
        this.ada181 = ada181;
    }

    public String getAda127() {
        return ada127;
    }

    public void setAda127(String ada127) {
        this.ada127 = ada127;
    }

    public String getAda152() {
        return ada152;
    }

    public void setAda152(String ada152) {
        this.ada152 = ada152;
    }

    public String getAaz002() {
        return aaz002;
    }

    public void setAaz002(String aaz002) {
        this.aaz002 = aaz002;
    }
}