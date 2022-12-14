package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.pers;

import com.alibaba.fastjson.JSONObject;
import com.eframework.core.exception.exception.BusinessException;
import com.eframework.datamodel.dictionary.data.DictionaryListDO;
import com.eframework.datamodel.user.SysUserService;
import com.eframework.datamodel.user.data.SysUserSsoDO;
import com.eframework.module.comp.user.bs.IUserBS;
import com.eframework.module.comp.user.bs.UserBS;
import com.eframework.module.comp.user.dto.UserDTO;
import com.eframework.util.bean.BeanHelper;
import com.eframework.util.dao.DaoHelper;
import com.eframework.util.json.JSONHelper;
import com.eframework.util.string.StringHelper;
import com.eframework.web.mvc.controller.BaseForm;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.Ac01_fj;
import com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.Ac01_gw;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.bs.IPersBS;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_fjDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01_gwDTO;
import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ac01gwDTO;
import com.xfinsoft.szybgzdtgl.util.ImageUtil;
import com.xfinsoft.szybgzdtgl.util.PinyinUtil;
import com.xfinsoft.szybgzdtgl.util.Util;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;
import sun.security.util.Length;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/szybgzdtgl/baseinfo/pers")
public class PersController {

    @Autowired
    @Qualifier("PersBS")
    private IPersBS persBS;

    @Autowired
    @Qualifier("UserBS")
    private IUserBS userBS;

    //???????????????????????????????????????
    @RequestMapping(value = "persinfomgt", params = "frameControlSubmitFunction=init", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView initPersinfomgt(PersForm persForm) throws Exception {

        Map<String, Object> formMap = persForm.toMap();
        ModelAndView modelAndView = new ModelAndView("/szybgzdtgl/baseinfo/pers/persinfomgt", formMap);
        return modelAndView;
    }

    //?????????????????????
    @RequestMapping(value = "persinfomgt", params = "frameControlSubmitFunction=loadPersData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PersForm loadPersData(@RequestBody PersForm persForm) throws Exception {
        /*Kct5DTO kct5dto = new Kct5DTO();
        BeanHelper.copyProperties(kct5dto, flexibleEmployPerInsuForm);
        flexibleEmployPerInsuBS.loadKct5Info(kct5dto);
        BeanHelper.copyProperties(flexibleEmployPerInsuForm, kct5dto);
        return flexibleEmployPerInsuForm;*/
        Ac01_gwDTO ac01 = new Ac01_gwDTO();
        BeanHelper.copyProperties(ac01, persForm);
        persBS.loadPersData(ac01);
        BeanHelper.copyProperties(persForm, ac01);
        return persForm;
    }

    //??????????????????
    @ResponseBody
    @RequestMapping(value = "persinfomgt", params = "frameControlSubmitFunction=loadDocument", method = {RequestMethod.POST, RequestMethod.GET})
    public LoadDocumentFileForm loadDocument1(@RequestBody LoadDocumentFileForm loadDocumentForm) throws BusinessException, IOException, SQLException {
        String sql = "select fj013 as img from ac01_fj where ac01id='" + loadDocumentForm.getAc01id() + "'";
        Blob object = persBS.test(sql);
        if(null!=object){
            int len = (int) object.length(); //blob???????????????????????????blob,??????rs.getBob(0);
            byte[] image = object.getBytes(1, len);
//        OutputStream out=response.getOutputStream();
//        out.write(image);
//        this.sendResponseString(response, image, "image/gif");
//        out.flush();
            loadDocumentForm.setImg(image);
            String imgBase64 = Base64.encodeBase64String(image);
            System.out.println(imgBase64);
            //loadDocumentForm.setBase64("data:image/jpeg;base64," + imgBase64);
            loadDocumentForm.setBase64(imgBase64);
        }
        return loadDocumentForm;
    }

    //????????????
    @RequestMapping(
            value = {"persinfomgt"},
            params = {"frameControlSubmitFunction=uploadfile"},
            method = {RequestMethod.POST, RequestMethod.GET}
    )
    @ResponseBody
    public PersForm uploadfile(PersForm form) throws Exception {
        /*System.out.println("?????????as");
        if(form.getFile().size()>0){
            System.out.println("?????????3");
        }*/

        if (form.getDocument() != null && !form.getDocument().isEmpty()) {
            //System.out.println("?????????4");
            byte[] bytes = FileCopyUtils.copyToByteArray(form.getDocument().getInputStream());
//            System.out.println("------"+form.getFile().getContentType());
            String type = ImageUtil.getPicType(form.getDocument().getInputStream());
            if (StringHelper.isEmpty(type) || ImageUtil.TYPE_UNKNOWN.equals(type)) {
                throw new BusinessException("?????????????????????????????????");
            }
            //?????????????????????
            //String base64 = org.apache.axis.encoding.Base64.encode(bytes);
            String imgBase64 = Base64.encodeBase64String(bytes);
            //System.out.println(imgBase64);
            //form.setBase64("data:image/jpeg;base64," + imgBase64);
            form.setBase64(imgBase64);
        }
        //System.out.println("?????????as2");
        return form;
    }

    //?????????????????????
    @ResponseBody
    @RequestMapping(value = "persinfomgt", params = "frameControlSubmitFunction=getPinyin", method = {RequestMethod.POST, RequestMethod.GET})
    public PersForm getPinyin(@RequestBody PersForm form) throws Exception {
        if(StringHelper.isNotEmpty(form.getAac003())){
            String userid = PinyinUtil.getPinyin(form.getAac003(),"");
            //???????????????????????????
            int i = 0;
            while (true){
                i+=1;
                UserDTO user = userBS.loadUserById(userid);
                if(null!=user){
                    //???????????????????????????+i
                    userid=userid+i;
                }else{
                    //??????????????????
                    break;
                }
            }
            form.setUserid(userid);

        }
        return form;
    }

    //????????????????????????
    @ResponseBody
    @RequestMapping(value = "persinfomgt", params = "frameControlSubmitFunction=savePers", method = {RequestMethod.POST, RequestMethod.GET})
    public PersForm savePersinfo(@RequestBody PersForm form) throws Exception {
        if ("add".equals(form.getType())) {
            //????????????
            Ac01_gwDTO dto = new Ac01_gwDTO();
            BeanHelper.copyProperties(dto, form);
            dto.setAc01id(DaoHelper.getNextSequence("SEQ_AC01ID"));//?????? id

            //???????????????????????????USERID
            /*String userid = PinyinUtil.getPinyin(form.getAac003(),"");
            dto.setUserid(userid);*/

            dto.setCkc005("00");    //????????????
            //??????????????????????????????????????????
            List<Ac01_gwDTO> ac01s = persBS.queryAc01gwList(dto);
            if(ac01s.size()>0){
                throw new BusinessException("??????????????????????????????????????????");
            }

            //????????????????????????????????????
            String userid = form.getUserid();
            if(StringHelper.isEmpty(userid)){
                userid = PinyinUtil.getPinyin(form.getAac003(),"");
            }
            //???????????????????????????
            int i = 0;
            while (true){
                i+=1;
                UserDTO user = userBS.loadUserById(userid);
                if(null!=user){
                    //???????????????????????????+i
                    userid=userid+i;
                }else{
                    //??????????????????
                    break;
                }
            }
            dto.setUserid(userid);

            dto.setAae036(new Date());
            dto.setCae249(Util.getUserId());
            dto.setCae251(Util.getUserName());
            //????????????????????????
            dto.setAaz002(DaoHelper.getNextSequence("SEQ_AAZ002"));
            dto.setNumid(persBS.getNumid("AC01_GW"));

            persBS.saveAc01gw(dto);

            //??????????????????
            Ac01gwDTO ac01gwDTO = new Ac01gwDTO();
            BeanHelper.copyProperties(ac01gwDTO, dto);
            ac01gwDTO.setGwid(dto.getAc01id());
            ac01gwDTO.setAda137("AC01_GW");   //??????????????????
            persBS.saveTableLog(Ac01gwDTO.class,ac01gwDTO);


            //??????????????????
            Ac01_fjDTO fjDTO = new Ac01_fjDTO();
            fjDTO.setAc01id(dto.getAc01id());//?????? id
            fjDTO.setFj002("RYZP"); //??????
            fjDTO.setFj100(dto.getAc01id());
            fjDTO.setAae036(new Date());
            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println(form.getBase64());
            byte[] fj013 = null;
            try {
                //fj013 = StringHelper.isNotEmpty(form.getBase64())?decoder.decodeBuffer(form.getBase64().split("1")[1]):null;
                fj013 = StringHelper.isNotEmpty(form.getBase64())?Base64.decodeBase64(form.getBase64()):null;
            } catch (Exception e) {
                throw new BusinessException("Base64???????????????"+e.getMessage());
            }
            fjDTO.setFj013(fj013);
            persBS.saveAc01fj(fjDTO);

            //????????????????????????,???????????????????????????????????????
            if("8".equals(dto.getAac049())){    //?????????
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(dto.getUserid());
                userDTO.setFontsizePreference("small");
                userDTO.setIsAdmin("0");    //????????????
                userDTO.setIsCancel("0");   //????????????
                userDTO.setIsDebug("0");
                userDTO.setIsUserSql("0");
                userDTO.setLanguage("0");//??????????????????
                userDTO.setPassword( form.getAac147().substring(form.getAac147().length()-6) );//????????????????????????6???,md5??????
                userDTO.setProgramNo("SZYBGZDTGL");//????????????
                userDTO.setUserName(form.getAac003());//????????????
                userBS.saveUser(userDTO);
                //??????????????????????????????

            }


            BeanHelper.copyProperties(form, dto);


        } else if ("mod".equals(form.getType())) {
            //????????????
            Ac01_gwDTO dto = new Ac01_gwDTO();
            BeanHelper.copyProperties(dto, form);
            //dto.setAc01id(Long.valueOf(DaoHelper.getNextSequence("SEQ_AC01ID")));//?????? id

            Ac01_gwDTO predto = new Ac01_gwDTO();   //?????????ac01??????
            predto.setAc01id(dto.getAc01id());
            persBS.loadPersData(predto);

            dto.setAaz002(DaoHelper.getNextSequence("SEQ_AAZ002"));
            //????????????????????????
            persBS.saveAc01gw(dto);

            //??????????????????
            Ac01gwDTO ac01gwDTO = new Ac01gwDTO();
            BeanHelper.copyProperties(ac01gwDTO, dto);
            ac01gwDTO.setGwid(dto.getAc01id());
            ac01gwDTO.setAda137("AC01_GW");   //??????????????????
            persBS.saveTableLog(Ac01gwDTO.class,ac01gwDTO);


            //??????????????????

            Ac01_fjDTO fjDTO = new Ac01_fjDTO();
            fjDTO.setAc01id(dto.getAc01id().toString());// id
            fjDTO.setFj002("RYZP"); //??????
            fjDTO.setFj100(dto.getAc01id().toString());
            fjDTO.setAae036(new Date());
            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println(form.getBase64());
            byte[] fj013 = null;
            try {
                //fj013 = StringHelper.isNotEmpty(form.getBase64())?decoder.decodeBuffer(form.getBase64().split("1")[1]):null;
                fj013 = StringHelper.isNotEmpty(form.getBase64())?Base64.decodeBase64(form.getBase64()):null;
            } catch (Exception e) {
                throw new BusinessException("Base64???????????????"+e.getMessage());
            }
            fjDTO.setFj013(fj013);
            persBS.saveAc01fj(fjDTO);

            //??????????????????????????????
            if(!dto.getAac049().equals(predto.getAac049())){
                //?????????????????????????????????????????????

            }
            BeanHelper.copyProperties(form, dto);

        } else if ("del".equals(form.getType())) {
            //??????

            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????


            //1????????????????????????????????????????????????
            Ac01_gwDTO dto = new Ac01_gwDTO();
            dto.setAc01id(form.getAc01id());

            persBS.loadPersData(dto);
            dto.setCkc005("01");
            dto.setAaz002(DaoHelper.getNextSequence("SEQ_AAZ002"));
            persBS.saveAc01gw(dto);


            //??????????????????
            Ac01gwDTO ac01gwDTO = new Ac01gwDTO();
            BeanHelper.copyProperties(ac01gwDTO, dto);
            ac01gwDTO.setGwid(dto.getAc01id());
            ac01gwDTO.setAda137("AC01_GW");   //??????????????????
            persBS.saveTableLog(Ac01gwDTO.class,ac01gwDTO);



            /*String updatesql  =" update ac01_gw set ckc005='01' where ac01id="+form.getAc01id();
            System.out.println("???????????????"+updatesql);
            persBS.executeSql(updatesql);*/




            //2???????????????????????????
            String deletesql  =" update sys_user_service set iscancel='1' where userid='"+form.getUserid()+"'";
            System.out.println("???????????????"+deletesql);
            persBS.executeSql(deletesql);

            BeanHelper.copyProperties(form, dto);

        }


        form.setFrameControlBackMessage(BaseForm.MSG_TYPE_ALERT, "???????????????");
        return form;
    }

}
