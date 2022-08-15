package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Ada2DO  implements Serializable {
    @Id
    @Column(name = "ADA137", length = 32, nullable = false)
    private String ada137;//	varchar2(32)			实体对象类型

    @Column(name = "ADA182", length = 10, nullable = false)
    private String ada182;//	varchar2(10)			角色属性指标

    @Column(name = "ADA181", length = 1000, nullable = false)
    private String ada181;//	varchar2(1000)			角色属性含义

    @Column(name = "ADA183", length = 3, nullable = false)
    private String ada183;//	varchar2(3)			角色属性数据类型(1-varchar，2-number)

    @Column(name = "ADA184", length = 6, nullable = false)
    private Long ada184;//	number(6)			角色属性数据长度

    @Column(name = "ADA185", length = 3, nullable = false)
    private String ada185;//	varchar2(3)			角色属性是否可空(1-是，0-否)

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

    public String getAda183() {
        return ada183;
    }

    public void setAda183(String ada183) {
        this.ada183 = ada183;
    }

    public Long getAda184() {
        return ada184;
    }

    public void setAda184(Long ada184) {
        this.ada184 = ada184;
    }

    public String getAda185() {
        return ada185;
    }

    public void setAda185(String ada185) {
        this.ada185 = ada185;
    }
}
