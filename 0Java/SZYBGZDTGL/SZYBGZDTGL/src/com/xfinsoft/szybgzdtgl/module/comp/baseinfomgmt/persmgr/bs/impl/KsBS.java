package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.impl;

import com.eframework.module.comp.base.BaseBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IKsBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IKsBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KsBS")
public class KsBS extends BaseBS implements IKsBS {

    @Autowired
    @Qualifier("KsBO")
    private IKsBO iKsBO;

    @Override
    public List<Ks_gwDTO> getData(Ks_gwDTO dto) {
        return iKsBO.getData(dto);
    }
}
