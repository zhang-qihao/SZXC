package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada4DO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ADA4")
@Table(name = "ADA4")
public class Ada4 extends Ada4DO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}