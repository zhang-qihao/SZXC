package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_gwDO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ks01DO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="KS01")
@Table(name="KS01")
public class Ks01  extends Ks01DO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}
