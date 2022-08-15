package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.impl;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.module.comp.base.BaseBS;
import com.eframework.util.bean.BeanHelper;
import com.eframework.util.dao.DaoHelper;
import com.eframework.util.string.StringHelper;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IPersBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IPersBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_fjDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Service("PersBS")
public class PersBS  extends BaseBS implements IPersBS {
    @Autowired
    @Qualifier("PersBO")
    private IPersBO iPersBO;


    /**
     * 保存变更日志
     * @param cla
     * @param obj
     * @throws BusinessException
     */
    public void saveTableLog(Class cla,Object obj)  throws BusinessException{
        iPersBO.saveTableLog(cla,obj);
    }

    /**
     * 查询人员基础信息
     * @param ac01_gwDTO
     */
    public void loadPersData(Ac01_gwDTO ac01_gwDTO) throws BusinessException{
        List<Ac01_gwDTO> list = iPersBO.queryAc01(ac01_gwDTO);
        if(list.size()>0){
            BeanHelper.copyProperties(ac01_gwDTO, list.get(0));
        }
    }

    /**
     * 查询图片
     * @param sql
     * @return
     * @throws BusinessException
     */
    public Blob test(String sql) throws BusinessException {
        Map objBySql = DaoHelper.findObjBySql(sql);
        if(null!=objBySql){
            Blob jo = (Blob) objBySql.get("IMG");
            return jo;
        }
        return null;
    }

    /**
     * 执行操作语句
     * @param sql
     * @throws BusinessException
     */
    public void executeSql(String sql) throws BusinessException{
        if(StringHelper.isEmpty(sql)){
            throw new BusinessException("操作失败!");
        }
        iPersBO.executeSql(sql);
    }


    /**
     * 保存用户人员信息
     * @throws BusinessException
     */
    public void saveAc01gw(Ac01_gwDTO dto) throws BusinessException{
        iPersBO.saveAc01gw(dto);
    }


    /**
     * 保存用户人员照片
     * @throws BusinessException
     */
    public void  saveAc01fj(Ac01_fjDTO fjDTO) throws BusinessException{
        iPersBO.saveAc01fj(fjDTO);
    }

    /**
     * 查询用户人员信息
     * @param dto
     * @return
     * @throws BusinessException
     */
    public List<Ac01_gwDTO> queryAc01gwList(Ac01_gwDTO dto) throws BusinessException{
        return  iPersBO.queryAc01gwList(dto);
    }

    /**
     * 获取业务表排序id
     * @param tablename
     * @return
     */
    public String getNumid(String tablename) throws BusinessException{
        return  iPersBO.getNumid(tablename);
    }

}
