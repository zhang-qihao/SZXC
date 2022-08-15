package com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.impl;

import com.eframework.core.exception.exception.BusinessException;
import com.eframework.module.comp.base.BaseBO;
import com.eframework.util.bean.BeanHelper;
import com.eframework.util.dao.DaoHelper;
import com.eframework.util.string.StringHelper;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.Ac01_fj;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.Ac01_gw;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.Ada1;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.Ada2;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.Ada3;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.ada.Ada4;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bo.IPersBO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_fjDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Repository("PersBO")
public class PersBO extends BaseBO implements IPersBO {

    /**
     * 保存变更日志
     * @param cla
     * @param obj
     * @throws BusinessException
     */
    public void saveTableLog(Class cla,Object obj)  throws BusinessException{
        String gwid = null;
        String ada137 = null;
        String aaz002 = null;
        //获取对象主键、表名
        try {
             gwid = (String) PropertyUtils.getProperty(obj,"gwid"); //对象主键id
             ada137 = (String) PropertyUtils.getProperty(obj,"ada137"); //表名
            aaz002 = (String) PropertyUtils.getProperty(obj,"aaz002"); //日志id
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /*
         * 保存 ADA3 对象变更事件表
         */
        Ada3 ada3 = new Ada3();
        ada3.setAda003(DaoHelper.getNextSequence("SEQ_ADA003"));   //变更事件id
        ada3.setAda137(ada137); //变更对象类型
        ada3.setAae035(new Date()); //变更日期
        ada3.setAae013(null);   //备注
        ada3.setAaz002(aaz002);    //日志id
        this.save(ada3);

        /*
         *  保存 ADA4   变更明细
         */
        List<Ada4> ada4List = new ArrayList<Ada4>();
        //1、获取标对象指标信息 ADA2
        String ada2sql = "select * from Ada2 where ADA137 = '"+ada137+"'";
        List<Ada2> ada2List = this.findSimpleObjectsBySql(ada2sql,Ada2.class);
        if(ada2List.size()<1){
            throw  new BusinessException("该对象指标类型无法获取相应指标！");
        }
        //2、获取对象指标记录表中的所有记录 ADA1
        String ada1sql = "SELECT t1.* FROM ada1 t1 WHERE t1.gwid = '"+gwid+"' and ada137='"+ada137+"'" ;
        List<Ada1> ada1List = this.findSimpleObjectsBySql(ada1sql,Ada1.class);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < ada1List.size(); i++) {
            Ada1 ada1 = ada1List.get(i);
            map.put(ada1.getAda182().toLowerCase(), ada1.getAda180());
        }
        //3、遍历角色指标
        for(int i=0;i<ada2List.size();i++){
            //定义局部变量
            Ada2 ada2 = ada2List.get(i);
            //定义变更明细
            Ada4 ada4 = new Ada4();

            //设置 原值 ADA127
            if(ada1List.size()<1){
                ada4.setAda127(null);   // 原值 为 空
            }else{
                ada4.setAda127(map.get(ada2.getAda182().toLowerCase()));
            }

            //设置 新值 ADA152
            String fieldname = ada2.getAda182().toLowerCase();
            System.out.println(fieldname);//指标名称
            try {
                //通过指标反射获取值
                Field fld = cla.getDeclaredField(fieldname.trim());
                fld.setAccessible(true);  //设置属性，能够正常的访问私有属性
                if(fld.get(obj)!=null){
                    //判断一下 值 的长度
                    if(fld.get(obj).toString().getBytes().length>ada2.getAda184()){
                        throw new BusinessException(ada2.getAda181()+"的长度('"+String.valueOf(fld.get(obj.toString().getBytes().length))+"')超出字段长度"+String.valueOf(ada2.getAda184()));
                    }
                    ada4.setAda152(fld.get(obj).toString()); // 新值
                }else{
                    ada4.setAda152(null);
                }
            } catch (Exception e) {
                ada4.setAda152(null);
                e.printStackTrace();
            }

            //比较 新值、旧值，如果不一致，保存变更明细
            if( ( StringHelper.isEmpty(ada4.getAda127()) && StringHelper.isNotEmpty(ada4.getAda152()) )      //如果 旧值为空，新值不为空
                    ||( StringHelper.isNotEmpty(ada4.getAda127()) && StringHelper.isEmpty(ada4.getAda152()) )//或者 旧值不为空，新值为空
                    ||( StringHelper.isNotEmpty(ada4.getAda127()) && StringHelper.isNotEmpty(ada4.getAda152()) && (!ada4.getAda127().equals(ada4.getAda152())) )  //或者 旧值不为空，新值不为空，且 俩者不等
                    ){
                ada4.setAda013(DaoHelper.getNextSequence("SEQ_ADA013"));    //变更明细id
                ada4.setAda003(ada3.getAda003());   //变更事件id
                ada4.setGwid(gwid); //变更对象主键id
                ada4.setAda137(ada137); //变更对象类型
                ada4.setAda182(ada2.getAda182());   //对象角色指标
                ada4.setAda181(ada2.getAda181());   //对象角色指标属性含义
                ada4.setAaz002(aaz002); //日志id

                ada4List.add(ada4);

            }

        }

        //4、保存变更明细
        if(ada4List.size()>0){
            this.save(ada4List);
        }

        /*
         * 保存 ADA1  对象指标表
         */
        //1、定义 MAP  对象指标
        Map<String, Ada1> mapada1 = new HashMap<String, Ada1>();
        for (int i = 0; i < ada1List.size(); i++) {
            Ada1 ada1 = ada1List.get(i);
            mapada1.put(ada1.getAda182().toLowerCase(), ada1);
        }
        //2、遍历循环 变更明细记录 ADA4LIST
        for(Ada4 ada4 : ada4List){
            Ada1 ada1 = new Ada1();
            //如果 系统中 的指标项 存在变更记录中的指标项，则需要对该指标项 新增保存
            if(map.containsKey(ada4.getAda182().toLowerCase())){
                BeanHelper.copyProperties(ada1, mapada1.get(ada4.getAda182().toLowerCase()));
                ada1.setAda181(ada4.getAda181());//指标属性含义
                ada1.setAda180(StringHelper.isNotEmpty(ada4.getAda152())?ada4.getAda152():"");//指标 值
                ada1.setAaz002(aaz002);
                this.update(ada1);
            }
            // 如果 系统中 的指标项 不存在变更记录中的指标项，则需要对该指标项新增保存
            else{
                ada1.setAda001(DaoHelper.getNextSequence("SEQ_ADA001"));   //对象指标记录id
                ada1.setGwid(gwid); //实体对象id
                ada1.setAda137(ada137); //实体对象类型
                ada1.setAda182(ada4.getAda182());   //对象指标
                ada1.setAda181(ada4.getAda181());   //对象指标含义
                ada1.setAda180(StringHelper.isNotEmpty(ada4.getAda152())?ada4.getAda152():"");//指标 值
                ada1.setAaz002(aaz002); //日志id
                this.save(ada1);
            }
        }
    }

    /**
     * 查询-用户人员信息
     * @return
     */
    public List<Ac01_gwDTO> queryAc01(Ac01_gwDTO dto) throws BusinessException {
        String sql = "select ac01id,\n" +
                "       aac003,\n" +
                "       aac004,\n" +
                "       aac147,\n" +
                "       aac006,\n" +
                "       aac029,\n" +
                "       aac039,\n" +
                "       aac049,\n" +
                "       aac050,\n" +
                "       aac051,\n" +
                "       aac059,\n" +
                "       aac060,\n" +
                "       aac061,\n" +
                "       ks002,\n" +
                "       ks003,\n" +
                "       userid,\n" +
                "       aae036,\n" +
                "       ckc005\n" +
                "  from ac01_gw\n" +
                " where 1 = 1 \n";
        if (dto.getAc01id()!=null&&!"".equals(dto.getAc01id())){
            sql += " and ac01id="+dto.getAc01id();
        }

        if(StringHelper.isNotEmpty(dto.getAac147())){
            sql += " and aac147='"+dto.getAac147()+"'";
        }
        //状态
        if(StringHelper.isNotEmpty(dto.getCkc005())){
            sql += " and ckc005 ='"+dto.getCkc005()+"'";
        }

        System.out.println("用户人员信息查询：" + sql);
        List<Ac01_gwDTO> list = this.findSimpleObjectsBySql(sql, Ac01_gwDTO.class);
        return list;
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
        this.execute(sql);
        this.flush();
    }

    /**
     * 保存用户人员信息
     * @throws BusinessException
     */
    public void saveAc01gw(Ac01_gwDTO dto) throws BusinessException{
        Ac01_gw ac01 = new Ac01_gw();
        BeanHelper.copyProperties(ac01, dto);
        this.saveOrUpdate(ac01);
        this.flush();
        BeanHelper.copyProperties(dto, ac01);
    }

    /**
     * 保存用户人员照片
     * @throws BusinessException
     */
    public void  saveAc01fj(Ac01_fjDTO fjDTO) throws BusinessException{
        Ac01_fj ac01_fj = new Ac01_fj();
        BeanHelper.copyProperties(ac01_fj, fjDTO);
        this.saveOrUpdate(ac01_fj);
        this.flush();
        BeanHelper.copyProperties(fjDTO, ac01_fj);
    }

    /**
     * 查询用户人员信息
     * @param dto
     * @return
     * @throws BusinessException
     */
    public List<Ac01_gwDTO> queryAc01gwList(Ac01_gwDTO dto) throws BusinessException{
        String sql = "select * from ac01_gw where 1=1 ";
        //身份证号
        if(StringHelper.isNotEmpty(dto.getAac147())){
            sql += " and aac147='"+dto.getAac147()+"'";
        }
        //状态
        if(StringHelper.isNotEmpty(dto.getCkc005())){
           sql += " and ckc005 ='"+dto.getCkc005()+"'";
        }

        System.out.println("用户人员信息查询：" + sql);
        List<Ac01_gwDTO> list = this.findSimpleObjectsBySql(sql, Ac01_gwDTO.class);
        return list;
    }

    /**
     * 获取业务表排序id
     * @param tablename
     * @return
     */
    public String getNumid(String tablename) throws BusinessException{
        String sql = "";
        String numid="0";
        if("AC01_GW".equals(tablename)){
            sql = " select to_char(nvl(to_number(max(numid))+1,1)) as numid from ac01_gw where ckc005='00' ";
            Ac01_gwDTO dto = this.findSimpleObjectBySql(sql,Ac01_gwDTO.class);
            numid = dto.getNumid();
        }
        return numid;
    }

}
