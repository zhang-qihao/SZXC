package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.impl;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IPersBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.impl.StaffInfoBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IStaffInfoBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Gw01DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

public class StaffInfoBS implements IStaffInfoBS {

    @Autowired
    @Qualifier("StaffInfoBO")
    private StaffInfoBO staffInfoBO;

    @Override
    public List<Gw01DTO> getPosts(String ac01id) {
        return staffInfoBO.getPosts(ac01id);
    }
}
