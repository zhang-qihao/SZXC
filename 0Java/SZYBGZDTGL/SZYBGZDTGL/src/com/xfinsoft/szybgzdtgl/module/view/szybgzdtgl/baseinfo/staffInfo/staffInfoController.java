package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.staffInfo;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IPersBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IStaffInfoBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.impl.StaffInfoBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01gwDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Gw01DTO;
import com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.pers.PersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/szybgzdtgl/baseinfo/staffinfo")
public class staffInfoController {

    private IStaffInfoBS staffInfoBS;

    //用户人员信息保存
    @ResponseBody
    @RequestMapping(value = "staffindex", params = "frameControlSubmitFunction=getPosts", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Gw01DTO> getPosts(@RequestBody PersForm form) throws Exception {
        return staffInfoBS.getPosts(form.getAc01id());
    }
}
