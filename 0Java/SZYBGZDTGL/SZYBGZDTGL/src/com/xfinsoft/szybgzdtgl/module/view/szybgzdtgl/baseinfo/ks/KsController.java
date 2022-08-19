package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.ks;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IKsBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.impl.KsBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/szybgzdtgl/baseinfo/ks")
public class KsController {

    @Autowired
    @Qualifier("KsBS")
    private IKsBS iKsBS;

    @RequestMapping(value = "getPrintData", params = "frameControlSubmitFunction=getData",
            method = {RequestMethod.POST, RequestMethod.GET})
    public KsForm getData(@RequestBody KsForm ksForm) {

        Ks_gwDTO dto = new Ks_gwDTO();
        dto.setKs001(ksForm.getKs001());

        KsForm data = new KsForm();
        data.setData_list(iKsBS.getData(dto));

        return data;
    }

}
