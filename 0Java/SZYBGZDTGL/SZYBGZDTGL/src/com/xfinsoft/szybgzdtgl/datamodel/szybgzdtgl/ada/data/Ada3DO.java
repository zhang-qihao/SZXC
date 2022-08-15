package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Ada3DO   implements Serializable {
    @Id
    @Column(name = "ADA003", length = 32, nullable = false)
    private String ada003;//	varchar2(32)			对象角色变更事件id

    @Column(name = "ADA137", length = 32, nullable = false)
    private String ada137;//	varchar2(32)			实体对象变更类型

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AAE035", length = 10, nullable = true)
    private Date aae035;//	date	y		变更日期

    @Column(name = "AAE013", length = 100)
    private String aae013;//	varchar2(100)	y		备注

    @Column(name = "AAZ002", length = 32)
    private String aaz002;//	varchar2(32)	y		日志id

    public String getAda003() {
        return ada003;
    }

    public void setAda003(String ada003) {
        this.ada003 = ada003;
    }

    public String getAda137() {
        return ada137;
    }

    public void setAda137(String ada137) {
        this.ada137 = ada137;
    }

    public Date getAae035() {
        return aae035;
    }

    public void setAae035(Date aae035) {
        this.aae035 = aae035;
    }

    public String getAae013() {
        return aae013;
    }

    public void setAae013(String aae013) {
        this.aae013 = aae013;
    }

    public String getAaz002() {
        return aaz002;
    }

    public void setAaz002(String aaz002) {
        this.aaz002 = aaz002;
    }
}
