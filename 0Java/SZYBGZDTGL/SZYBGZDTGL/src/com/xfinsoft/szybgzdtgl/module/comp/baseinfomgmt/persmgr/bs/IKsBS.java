package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;

import java.util.List;

public interface IKsBS {
    public List<Ks_gwDTO> getData(Ks_gwDTO dto);
}
