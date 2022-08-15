package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada1DO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada2DO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ADA2")
@Table(name = "ADA2")
public class Ada2  extends Ada2DO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}