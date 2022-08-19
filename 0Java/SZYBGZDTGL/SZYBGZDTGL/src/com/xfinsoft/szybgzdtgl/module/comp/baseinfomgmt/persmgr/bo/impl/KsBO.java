package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.impl;

import com.eframework.module.comp.base.BaseBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IKsBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KsBO")
public class KsBO extends BaseBO implements IKsBO {
    @Override
    public List<Ks_gwDTO> getData(Ks_gwDTO dto) {
        String sql = "select " +
                "a.gw003, " +
                "a.gw002, " +
                "c.aac003, " +
                "c.aac004, " +
                "c.aac039, " +
                "c.aac029 " +
                "from GW01 a, GW10 b, AC01_GW c " +
                "where " +
                "a.GW001 = b.GW001 " +
                "and b.Ac01id = c.AC01ID " +
                "where 1 = 1 ";

        if (dto.getKs001() != null && !dto.getKs001().equals("")) {
            sql += "and a.ks001 = '" + dto.getKs001() + "'";
        }

        List<Ks_gwDTO> list = this.findSimpleObjectsBySql(sql, Ks_gwDTO.class);
        return list;
    }
}
