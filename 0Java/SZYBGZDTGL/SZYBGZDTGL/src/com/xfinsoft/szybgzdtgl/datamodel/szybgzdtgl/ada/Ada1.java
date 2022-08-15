package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.data.Ada1DO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_gwDO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ADA1")
@Table(name = "ADA1")
public class Ada1 extends Ada1DO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}
