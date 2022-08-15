package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_fjDO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_gwDO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="AC01_FJ")
@Table(name="AC01_FJ")
public class Ac01_fj extends Ac01_fjDO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}