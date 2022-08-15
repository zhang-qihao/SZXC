package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Gw01DTO;

import java.util.HashMap;
import java.util.List;

/**
 * 人员信息查询BO接口
 */
public interface IStaffInfoBO {
    // 获取岗位
    public List<Gw01DTO> getPosts(String ac01id);
}
