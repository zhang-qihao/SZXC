package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;

import java.util.List;

public interface IKsBO {
    public List<Ks_gwDTO> getData(Ks_gwDTO dto);
}
