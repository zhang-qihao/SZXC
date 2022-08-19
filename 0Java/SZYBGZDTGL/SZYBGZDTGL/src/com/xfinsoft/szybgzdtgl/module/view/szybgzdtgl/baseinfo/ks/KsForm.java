package com.xfinsoft.szybgzdtgl.module.view.szybgzdtgl.baseinfo.ks;

import com.xfinsoft.szybgzdtgl.module.comp.baseinfomgmt.persmgr.dto.Ks_gwDTO;

import java.util.List;

public class KsForm {

    private String ks001; // 科室id

    private String gw001; // 岗位id

    private String gw003; // 岗位名称

    private String gw002; // 岗位职责

    private String ac01id; // 人员id

    private String aac003; // 工作人员名称

    private String aac004; // 人员性别

    private String aac039; // 人员性质

    private String aac029; // 政治面貌

    private List<Ks_gwDTO> data_list; // 数据列表

    public List<Ks_gwDTO> getData_list() {
        return data_list;
    }

    public void setData_list(List<Ks_gwDTO> data_list) {
        this.data_list = data_list;
    }

    public String getKs001() {
        return ks001;
    }

    public void setKs001(String ks001) {
        this.ks001 = ks001;
    }

    public String getGw001() {
        return gw001;
    }

    public void setGw001(String gw001) {
        this.gw001 = gw001;
    }

    public String getGw003() {
        return gw003;
    }

    public void setGw003(String gw003) {
        this.gw003 = gw003;
    }

    public String getGw002() {
        return gw002;
    }

    public void setGw002(String gw002) {
        this.gw002 = gw002;
    }

    public String getAc01id() {
        return ac01id;
    }

    public void setAc01id(String ac01id) {
        this.ac01id = ac01id;
    }

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003;
    }

    public String getAac004() {
        return aac004;
    }

    public void setAac004(String aac004) {
        this.aac004 = aac004;
    }

    public String getAac039() {
        return aac039;
    }

    public void setAac039(String aac039) {
        this.aac039 = aac039;
    }

    public String getAac029() {
        return aac029;
    }

    public void setAac029(String aac029) {
        this.aac029 = aac029;
    }
}
