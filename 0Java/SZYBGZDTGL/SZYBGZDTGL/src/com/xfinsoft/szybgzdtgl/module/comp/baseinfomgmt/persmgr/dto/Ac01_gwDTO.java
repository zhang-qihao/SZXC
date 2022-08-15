package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto;

import com.eframework.module.comp.base.IDTO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_gwDO;

public class Ac01_gwDTO extends Ac01_gwDO implements IDTO {

    private String gwid;
    private String ada137;  //实体对象变更类型

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
}
