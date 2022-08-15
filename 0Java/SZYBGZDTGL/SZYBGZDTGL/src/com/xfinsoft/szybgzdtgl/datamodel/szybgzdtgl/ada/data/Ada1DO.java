package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Ada1DO  implements Serializable {
    @Id
    @Column(name = "ADA001", length = 32, nullable = false)
    private String ada001;//	varchar2(32)			角色属性id

    @Column(name = "GWID", length = 32, nullable = false)
    private String gwid;//	varchar2(32)			实体类主键id

    @Column(name = "ADA137", length = 32, nullable = false)
    private String ada137;//	varchar2(32)			实体对象类型

    @Column(name = "ADA182", length = 10)
    private String ada182;//	varchar2(10)	y		角色属性指标

    @Column(name = "ADA181", length = 100)
    private String ada181;//	varchar2(100)	y		角色属性含义

    @Column(name = "ADA180", length = 1000)
    private String ada180;//	varchar2(1000)	y		角色属性值

    @Column(name = "AAZ002", length = 32)
    private String aaz002;//	varchar2(32)	y		日志id

    public String getAda001() {
        return ada001;
    }

    public void setAda001(String ada001) {
        this.ada001 = ada001;
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

    public String getAda180() {
        return ada180;
    }

    public void setAda180(String ada180) {
        this.ada180 = ada180;
    }

    public String getAaz002() {
        return aaz002;
    }

    public void setAaz002(String aaz002) {
        this.aaz002 = aaz002;
    }
}
