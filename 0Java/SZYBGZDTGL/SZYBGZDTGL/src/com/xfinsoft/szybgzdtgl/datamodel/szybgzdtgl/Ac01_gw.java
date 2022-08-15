package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.IPO;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data.Ac01_gwDO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="AC01_GW")
@Table(name="AC01_GW")
public class Ac01_gw extends Ac01_gwDO implements IPO {
    @Override
    public void validatePO(int i) throws BusinessException {

    }
}
