package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.pers;

import com.eframework.web.mvc.controller.BaseForm;

public class LoadDocumentFileForm extends BaseForm {
    byte[] img;
    String base64;
    String fj100;
    String ac01id;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getFj100() {
        return fj100;
    }

    public void setFj100(String fj100) {
        this.fj100 = fj100;
    }

    public String getAc01id() {
        return ac01id;
    }

    public void setAc01id(String ac01id) {
        this.ac01id = ac01id;
    }
}
