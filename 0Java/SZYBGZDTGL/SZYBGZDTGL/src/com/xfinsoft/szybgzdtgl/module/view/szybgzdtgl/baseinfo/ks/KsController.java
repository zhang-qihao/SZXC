package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.ks;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IKsBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/szybgzdtgl/baseinfo/ks")
public class KsController {

    @Autowired
    @Qualifier("KsBS")
    private IKsBS iKsBS;

    @RequestMapping(value = "getPrintData", params = "frameControlSubmitFunction=getData", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Ks_gwDTO> getData(KsForm ksForm) {

        System.out.println("success");
        Ks_gwDTO dto = new Ks_gwDTO();
        dto.setKs001(ksForm.getKs001());
        return iKsBS.getData(dto);
    }

}
