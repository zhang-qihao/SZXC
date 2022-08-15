package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada1DO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada3DO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ADA3")
@Table(name = "ADA3")
public class Ada3  extends Ada3DO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}