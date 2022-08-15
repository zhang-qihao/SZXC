package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.impl;

import com.eframework.module.comp.base.BaseBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IStaffInfoBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Gw01DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffInfoBO extends BaseBO implements IStaffInfoBO {
    @Override
    public List<Gw01DTO> getPosts(String ac01id) {

        String sql = "select GW01.GW001, GW01.GW003, GW01.GW002, GW01.KS001, GW01.AAE036, GW01.CAE249, GW01.CAE251, GW01.AAE100, GW01.AAZ002, GW01.AAE013" +
                "from GW01, GW10 where GW01.GW001 = GW10.GW001";
        sql += "and GW10.AC01ID = '" + ac01id + "'";

        List<Gw01DTO> list = this.findSimpleObjectsBySql(sql, Gw01DTO.class);
        System.out.println("Return Posts:" + list.size());
        return list;
    }
}
