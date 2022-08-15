package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo;

import com.eframework.core.exception.exception.BusinessException;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_fjDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;

import java.util.List;

public interface IPersBO {

    /**
     * 保存变更日志
     * @param cla
     * @param obj
     * @throws BusinessException
     */
    public void saveTableLog(Class cla,Object obj)  throws BusinessException;

    /**
     * 查询-用户人员信息
     * @return
     */
    public List<Ac01_gwDTO> queryAc01(Ac01_gwDTO dto) throws BusinessException;

    /**
     * 执行操作语句
     * @param sql
     * @throws BusinessException
     */
    public void executeSql(String sql) throws BusinessException;

    /**
     * 保存用户人员信息
     * @throws BusinessException
     */
    public void saveAc01gw(Ac01_gwDTO dto) throws BusinessException;

    /**
     * 保存用户人员照片
     * @throws BusinessException
     */
    public void  saveAc01fj(Ac01_fjDTO fjDTO) throws BusinessException;

    /**
     * 查询用户人员信息
     * @param dto
     * @return
     * @throws BusinessException
     */
    public List<Ac01_gwDTO> queryAc01gwList(Ac01_gwDTO dto) throws BusinessException;

    /**
     * 获取业务表排序id
     * @param tablename
     * @return
     */
    public String getNumid(String tablename) throws BusinessException;
}
