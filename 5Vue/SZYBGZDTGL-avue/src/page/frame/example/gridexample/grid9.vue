<template>
  <el-container style="height: 100%">
    <el-header>
      <div class="header-left">
        <div class="header-left-tabbar">
          <div class="header-left-tab" @click="changeSelectTab(0)" :class="{'header-left-tab-active':selectTab == 0}">
            西药中成药
          </div>
          <div class="header-left-tab" @click="changeSelectTab(1)" :class="{'header-left-tab-active':selectTab == 1}">
            中药饮片
          </div>
        </div>

      </div>
      <div class="header-right">
        <el-button v-prevent-re-click icon="el-icon-plus" size="mini" type="primary" @click="dialogTableVisible = true">显示源码</el-button>
        <el-popover class="exp-info" style="margin-left: 100px" placement="bottom-start" width="250"
                    trigger="hover">
          <p>红色字体表示该药品已在国家库作废</p>
          <div slot="reference">
            <div class="exp-info-content"><i class="el-icon-question"
                                             style="margin-right:5px;"></i>操作说明
            </div>
          </div>
        </el-popover>
      </div>
      <!--      显示源码-->
      <el-dialog title="源码展示" :visible.sync="dialogTableVisible" :modal-append-to-body="false">
        <iframe src="/static/frame/example/gridexample/grid9.html" frameborder="0"
                style="width: 100%;height: 400px"></iframe>
      </el-dialog>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content" style="height: 100%;">
        <div style="height: 100%;" v-show="selectTab == 0">
          <base-block style="height: 100%;box-shadow: 0 2px 10px rgb(0 0 0 / 20%);" rounded>
            <div class="header-title" slot="title">
              <el-button v-prevent-re-click icon="el-icon-upload2" size="mini" type="primary" @click="doExport">导出
              </el-button>
            </div>
            <template slot="options">
              <div class="options-content">
                <el-form @submit.native.prevent :model="wsForm" label-position="right" label-width="82px">
                  <ef-text label="编码" v-model="wsForm.med_list_codg" name="med_list_codg"
                           placeholder="请输入机构编码/市码/省码/国码/自定义码" style="width: 350px"/>
                  <ef-text label-width="60px" label="药品名称" v-model="wsForm.drug_name" name="drug_name"
                           placeholder="药品注册名/商品名/通用名"/>
                </el-form>
                <el-button v-prevent-re-click icon="el-icon-search" size="mini" type="primary"
                           @click="searchInvalidMed()" style="margin-left:6px;">国家作废西药查询
                </el-button>
                <el-button-group style="margin: 0 5px">
                  <el-button v-prevent-re-click icon="el-icon-search" type="info" plain size="mini"
                             @click="loadMedList1"></el-button>
                  <el-button v-prevent-re-click icon="el-icon-refresh" type="info" plain size="mini"
                             @click="resetMedList1"></el-button>
                  <el-popover
                      placement="bottom"
                      popper-class="popover-content"
                      width="800"
                      v-model="visable1"
                      trigger="click">
                    <!--      更多筛选-->
                    <div>
                      <el-form @submit.native.prevent :model="wsForm" label-position="right" label-width="82px">
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="助记码" v-model="wsForm.pinyin" name="pinyin" placeholder="请输入拼音码\五笔码"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-text label="生产企业名称" v-model="wsForm.prodentp_name" name="prodentp_name"
                                     placeholder="请输入生产企业名称"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-select label="医保属性" name="type" v-model="wsForm.type"
                                       dictionaryNo="DIC_MEDICAL_INSURANCE" placeholder="请选择医保属性"/>
                          </el-col>

                          <el-col :span="12">
                            <ef-select label="目录类别" name="test" v-model="wsForm.list_type" placeholder="请选择目录类别"
                                       dictionaryNo="DIC_LIST_TYPE" dictionaryGroup="1"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="药品规格" v-model="wsForm.spec" name="spec" placeholder="请输入药品规格"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-select label="药品剂型" name="dosformCode" v-model="wsForm.dosformCode"
                                       dictionaryNo="DIC_DRUG_DOSFORM" filterable="true" placeholder="请选择药品剂型"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-select label="有效标志" name="valid" v-model="wsForm.valid"
                                       dictionaryNo="DIC_VALI_FLAG" filterable="true" placeholder="有效标志"/>
                          </el-col>
                          <el-col :span="12">
                            <div class="more-button">
                              <el-button v-prevent-re-click icon="el-icon-s-open" size="mini" type="primary"
                                         @click="loadMedList1">筛选
                              </el-button>
                              <el-button v-prevent-re-click icon="el-icon-refresh" size="mini" type="primary" plain
                                         @click="resetMedList1">
                                重置
                              </el-button>
                            </div>
                          </el-col>
                        </el-row>
                      </el-form>
                    </div>
                    <el-button v-prevent-re-click slot="reference" icon="el-icon-s-open" size="mini" type="info" plain>
                      更多筛选
                    </el-button>
                  </el-popover>
                </el-button-group>
              </div>
            </template>
            <div style="height: 100%">
              <ef-page-grid ref="medListCrspMgrGrid" queryNo="Q_MedListCrspMgr_005" pageSize="20" queryWindow="1"
                            style="height: 100%"/>
            </div>
          </base-block>
        </div>
        <div style="height: 100%;" v-show="selectTab == 1">
          <base-block style="height: 100%;box-shadow: 0 2px 10px rgb(0 0 0 / 20%);" rounded>
            <div class="header-title" slot="title">
              <el-button-group>
                <el-button v-prevent-re-click icon="el-icon-upload2" size="mini" type="primary" @click="doExport">导出
                </el-button>
              </el-button-group>
            </div>
            <template slot="options">
              <div class="options-content">
                <el-form @submit.native.prevent :model="tcForm" label-position="right" label-width="82px">
                  <ef-text label="编码" v-model="tcForm.med_list_codg" name="med_list_codg"
                           placeholder="请输入机构编码/市码/省码/国码/自定义码" style="width: 350px"/>
                  <ef-text style="width: 200px" label-width="60px" label="药品名称" v-model="tcForm.drug_name"
                           name="drug_name" placeholder="中草药名称/药材名称"/>
                </el-form>
                <el-button v-prevent-re-click icon="el-icon-search" size="mini" type="primary"
                           @click="searchInvalidMed()" style="margin-left:6px;">国家作废中药查询
                </el-button>
                <el-button-group style="margin: 0 5px">
                  <el-button v-prevent-re-click icon="el-icon-search" type="info" plain size="mini"
                             @click="loadMedList2"></el-button>
                  <el-button v-prevent-re-click icon="el-icon-refresh" type="info" plain size="mini"
                             @click="resetMedList2"></el-button>
                  <el-popover
                      placement="bottom"
                      popper-class="popover-content"
                      width="800"
                      v-model="visable2"
                      trigger="click">
                    <!--      更多筛选-->
                    <div>
                      <el-form @submit.native.prevent :model="wsForm" label-position="right" label-width="82px">
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="中草药年份" :numberFlag="true" v-model="tcForm.tcmherb_year" name="tcmherb_year"
                                     placeholder="请输入中草药年份"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-select label="单复方标志" name="cpnd_flag" v-model="tcForm.cpnd_flag"
                                       dictionaryNo="DIC_TCMDRUG_USED_WAY" placeholder="请选择单复方标志"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-select label="医保属性" name="type" v-model="tcForm.type"
                                       dictionaryNo="DIC_MEDICAL_INSURANCE"
                                       placeholder="请选择医保属性"/>
                          </el-col>

                          <el-col :span="12">
                            <ef-text label="功效分类" v-model="tcForm.ecy_type" name="ecy_type" placeholder="请输入功效分类"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="功能主治" v-model="tcForm.efcc_atd" name="efcc_atd" placeholder="请输入功能主治"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-text label="生产企业名称" v-model="tcForm.prodentp_name" name="prodentp_name"
                                     placeholder="请输入生产企业名称"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="产地" v-model="tcForm.exct_cont" name="exct_cont" placeholder="请输入产地"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-select label="药品剂型" name="dosformCode" v-model="tcForm.dosformCode"
                                       dictionaryNo="DIC_DRUG_DOSFORM"
                                       filterable="true" @onChange="tcDosformChange" placeholder="请选择药品剂型"/>
                          </el-col>
                        </el-row>
                        <el-row :gutter="20">
                          <el-col :span="12">
                            <ef-text label="规格" v-model="tcForm.spec" name="spec" placeholder="请输入规格"/>
                          </el-col>
                          <el-col :span="12">
                            <ef-select label="有效标志" name="valid2" v-model="tcForm.valid"
                                       dictionaryNo="DIC_VALI_FLAG"
                                       filterable="true" placeholder="有效标志"/>
                          </el-col>
                        </el-row>
                        <div class="more-button">
                          <el-button v-prevent-re-click icon="el-icon-s-open" size="mini" type="primary"
                                     @click="loadMedList2">筛选
                          </el-button>
                          <el-button v-prevent-re-click icon="el-icon-refresh" size="mini" type="primary" plain
                                     @click="resetMedList2">重置
                          </el-button>
                        </div>
                      </el-form>
                    </div>
                    <el-button v-prevent-re-click slot="reference" icon="el-icon-s-open" size="mini" type="info" plain>
                      更多筛选
                    </el-button>
                  </el-popover>
                </el-button-group>
              </div>
            </template>
            <div style="height: 100%">
              <ef-page-grid ref="medListCrspMgr103Grid" queryNo="Q_MedListCrspMgr103_006" pageSize="20"
                            queryWindow="1" style="height: 100%"/>
            </div>
          </base-block>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
import {mapGetters} from "vuex";
import constData from "../../../../const";

export default {
  name: "grid9",
  mixins: [BaseCtrl],
  data() {
    return {
      dialogTableVisible:false,
      visable1: false,
      visable2: false,
      selectTab: 0,
      fixmedins_id: '',
      //中西药查询
      wsForm: {
        med_list_codg: '',//医疗目录编号
        fixmedins_hilist_id: '',//自定义编码
        drug_name: '',//药品名
        pinyin: '',//助记码
        prodentp_name: '',//生产企业名称
        type: '',//医保属性
        spec: '',//药品规格
        list_type: '',//目录类别
        dosformCode: '', //药品剂型
        chk_stas: '', //审核状态
        valid: '1', //有效标志
      },
      //中药饮片查询
      tcForm: {
        dosform: '',
        prodentp_name: '',
        exct_cont: '',
        spec: '',
        med_list_codg: '',//医疗目录编号
        fixmedins_hilist_id: '',//自定义编码
        drug_name: '',//药品名称
        type: '',//医保属性
        // pinyin: '',//拼音助记码
        // wubi: '',//五笔助记码
        tcmherb_year: '',//中草药年份
        cpnd_flag: '',//单复方标志
        ecy_type: '',//功效分类
        efcc_atd: '',//功能主治
        chk_stas: '', //审核状态
        valid: '1', //有效标志
      },
      accept: "xls,xlsx",
    }
  },
  beforeRouteLeave(to, from, next) {
    this.visable1 = false
    this.visable2 = false
    next()
  },
  created() {
    let that = this;
    this.loadDicItem({dicId: "DIC_DRUG_DOSFORM"});//药品剂型字典
    this.fixmedins_id = that.userInfo.fixmedins_id;
    this.$nextTick(() => {
      //中西药
      this.$refs['medListCrspMgrGrid'].setInitFun((grid) => {//扩展Grid函数
        let options = grid.getOptions();

        options.dataBound = function () {
          let row = grid.dataSource.data();
          for (let i = 0; i < row.length; i++) {
            let uid = row[i].uid;
            let invalid = row[i].INVALID;
            if (invalid == "1") {
              $("tbody").find("tr[data-uid=" + uid + "]").css("color", "red")
            }
          }
        };

        let columns = options.columns;
        columns.unshift({
          title: "操作",
          width: 62,
          locked:true,
          command: [
            {
              className: "btn btn-xs btn-default",
              name: "btn01",
              template: "<a class='k-button k-grid-btn01'>修改</a>",
              visible: function (dataItem) {
                if (dataItem.get("VALI_FLAG") == '1') {
                  return true;
                }
              },
              click: function (e) {
                let tr = $(e.target).closest("tr");
                let rowData = grid.dataItem(tr);
                if (that.isNull(rowData.get("CITY_CODE"))) {
                  that.openMedlistcrspmgrowndetail(rowData);
                } else {
                  that.openMedlistcrspmgrdetail(rowData);
                }
              }
            },
            {
              className: "btn btn-xs btn-default",
              name: "btn02",
              template: "<a class='k-button k-grid-btn02'>查看</a>",
              visible: function (dataItem) {
                if (dataItem.get("VALI_FLAG") == '0') {
                  return true;
                }
              },
              click: function (e) {
                let tr = $(e.target).closest("tr");
                let rowData = grid.dataItem(tr);
                if (that.isNull(rowData.get("CITY_CODE"))) {
                  that.openMedlistcrspmgrowndetail(rowData);
                } else {
                  that.openMedlistcrspmgrdetail(rowData);
                }
              }
            }
          ]
        });
        grid.setOptions(options);
        that.loadMedList1();
      });

      //中药饮片
      this.$refs['medListCrspMgr103Grid'].setInitFun((grid) => {//扩展Grid函数
        let options = grid.getOptions();

        options.dataBound = function () {
          let row = grid.dataSource.data();
          for (let i = 0; i < row.length; i++) {
            let uid = row[i].uid;
            let invalid = row[i].INVALID;
            if (invalid == "1") {
              $("tbody").find("tr[data-uid=" + uid + "]").css("color", "red")
            }
          }
        };

        let columns = options.columns;
        columns.unshift({
          title: "操作",
          width: 62,
          command: [
            {
              className: "btn btn-xs btn-default",
              name: "btn01",
              template: "<a class='k-button k-grid-btn01'>修改</a>",
              visible: function (dataItem) {
                if (dataItem.get("VALI_FLAG") == '1') {
                  return true;
                }
              },
              click: function (e) {
                let tr = $(e.target).closest("tr");
                let rowData = grid.dataItem(tr);
                if (that.isNull(rowData.get("CITY_CODE"))) {
                  that.openMedlistcrspmgrowndetail103(rowData);
                } else {
                  that.openMedlistcrspmgrdetail103(rowData);
                }
              }
            },
            {
              className: "btn btn-xs btn-default",
              name: "btn02",
              template: "<a class='k-button k-grid-btn02'>查看</a>",
              visible: function (dataItem) {
                if (dataItem.get("VALI_FLAG") == '0') {
                  return true;
                }
              },
              click: function (e) {
                let tr = $(e.target).closest("tr");
                let rowData = grid.dataItem(tr);
                if (that.isNull(rowData.get("CITY_CODE"))) {
                  that.openMedlistcrspmgrowndetail103(rowData);
                } else {
                  that.openMedlistcrspmgrdetail103(rowData);
                }
              }
            }
          ]
        });
        grid.setOptions(options);
        that.loadMedList2();
      });
    });
  },
  computed: {
    ...mapGetters(["userInfo"])
  },
  mounted() {
  },
  methods: {
    //查找国家库中作废的对照
    searchInvalidMed() {
      let whereCondition = " invalid='1' ";
      let grid = "medListCrspMgrGrid";
      if (this.selectTab == 1) {
        this.resetMedList2();
        grid = "medListCrspMgr103Grid";
      } else {
        this.resetMedList1();
      }
      this.$refs[grid].getWidget().doRefresh({
        whereCondition: whereCondition,
        parameters: {fixmedins_id: this.fixmedins_id}
      });
    },

    //药品剂型改变时
    tcDosformChange(val) {
      if (val) {
        //取剂型名称
        this.tcForm.dosform = this.selectDicItem('DIC_DRUG_DOSFORM', val);
      } else {
        this.tcForm.dosform = "";
      }
    },

    changeSelectTab(num) {
      //清空搜索条件
      this.wsForm.dosformCode = "";
      this.selectTab = num;
    },
    //打开自费西药页面
    openMedlistcrspmgrowndetail(item) {
      let that = this;
      let backFun = function (data) {
        that.$refs['medListCrspMgrGrid'].getWidget().doRefresh(); //刷新Grid
      }
      that.slideReveal({
        title: "自费药详情",
        view: "/views/ehis/medlistmgmt/medlistcrspmgr/medlistcrspmgrowndetail",
        width: 1000,
        params: {
          medins_medlist_id: item.get("MEDINS_MEDLIST_ID"),//药品内码
          list_type: item.get("LIST_TYPE")//目录类别
        },
        callBackFun: backFun
      });

    },
    //打开医保西药页面
    openMedlistcrspmgrdetail(item) {
      let that = this;
      let flag = false;
      let invalid = item.get("INVALID");
      if (invalid == "1") {//医保无效——本机构作废
        flag = true;
      }
      let backFun = function (data) {
        that.$refs['medListCrspMgrGrid'].getWidget().doRefresh(); //刷新Grid
      }
      that.slideReveal({
        title: "医保药详情",
        view: "/views/ehis/medlistmgmt/medlistcrspmgr/medlistcrspmgrdetail",
        width: 1000,
        params: {
          med_list_codg: item.get("MED_LIST_CODG"),//医疗目录编码
          list_type: item.get("LIST_TYPE"),
          medins_medlist_id: item.get("MEDINS_MEDLIST_ID"),//药品内码
          vali_flag: item.get("VALI_FLAG"),//药品无效
          city_code: item.get("CITY_CODE"),//市码
          prov_code: item.get("PROV_CODE"),//省码
          flag: flag
        },
        callBackFun: backFun
      });
    },

    //打开自费中药饮片页面
    openMedlistcrspmgrowndetail103(item) {
      let that = this;
      let medins_medlist_id = item.get("MEDINS_MEDLIST_ID");//药品内码
      let backFun = function (data) {
        that.$refs['medListCrspMgr103Grid'].getWidget().doRefresh(); //刷新Grid
      }
      that.slideReveal({
        title: "自费药详情",
        view: "/views/ehis/medlistmgmt/medlistcrspmgr/medlistcrspmgrowndetail103",
        width: 1000,
        params: {medins_medlist_id: medins_medlist_id},
        callBackFun: backFun
      });
    },
    //打开医保中药饮片页面
    openMedlistcrspmgrdetail103(item) {
      let that = this;
      let flag = false;
      let invalid = item.get("INVALID");
      if (invalid == "1") {//医保无效——本机构作废
        flag = true;
      }
      let backFun = function (data) {
        that.$refs['medListCrspMgr103Grid'].getWidget().doRefresh(); //刷新Grid
      }
      console.log(item);
      that.slideReveal({
        title: "医保药详情",
        view: "/views/ehis/medlistmgmt/medlistcrspmgr/medlistcrspmgrdetail103",
        width: 1000,
        params: {
          med_list_codg: item.get("MED_LIST_CODG"),//医疗目录编码
          medins_medlist_id: item.get("MEDINS_MEDLIST_ID"),//药品内码
          vali_flag: item.get("VALI_FLAG"),//药品无效
          city_code: item.get("CITY_CODE"),//市码
          prov_code: item.get("PROV_CODE"),//省码
          flag: flag
        },
        callBackFun: backFun
      });
    },
    loadMedList1() {
      let whereCondition = "1=1";
      if (this.wsForm.med_list_codg) {
        whereCondition += " and (lower(med_list_codg) like lower('%" + this.wsForm.med_list_codg +
            "%') or lower(fixmedins_hilist_id) like lower('%" + this.wsForm.med_list_codg +
            "%') or lower(city_code) like lower('%" + this.wsForm.med_list_codg +
            "%') or lower(fix_asit_code) like lower('%" + this.wsForm.med_list_codg +
            "%') or lower(prov_code) like lower('%" + this.wsForm.med_list_codg + "%'))";
      }
      if (this.wsForm.commodity_name) {
        whereCondition += "and commodity_name like '%" + this.wsForm.commodity_name + "%'";
      }
      if (this.wsForm.drug_name) {
        whereCondition += "and (fixmedins_hilist_name like '%" + this.wsForm.drug_name + "%'" +
            " OR drug_genname like '%" + this.wsForm.drug_name + "%'" +
            " OR commodity_name like '%" + this.wsForm.drug_name + "%')";
      }
      if (this.wsForm.pinyin) {
        whereCondition += " and (lower(pinyin) like lower('%" + this.wsForm.pinyin + "%')" +
            " or lower(wubi) like lower('%" + this.wsForm.pinyin + "%'))";
      }
      if (this.wsForm.prodentp_name) {
        whereCondition += " and prodentp_name like '%" + this.wsForm.prodentp_name + "%'";
      }
      if (this.wsForm.type && this.wsForm.type !== '2') {
        whereCondition += " and type = '" + this.wsForm.type + "'";
      }
      if (this.wsForm.spec) {
        whereCondition += " and spec like '%" + this.wsForm.spec + "%'";
      }
      if (this.wsForm.list_type) {
        whereCondition += " and list_type = '" + this.wsForm.list_type + "'";
      }
      if (this.wsForm.dosformCode) {
        let drug_dosform = this.selectDicItem('DIC_DRUG_DOSFORM', this.wsForm.dosformCode);
        if (this.isNotNull(drug_dosform)) {
          whereCondition += " and dosform = '" + drug_dosform + "'";
        }
      }
      if (this.wsForm.chk_stas) {
        whereCondition += " and CHK_STAS = '" + this.wsForm.chk_stas + "'";
      }
      if (this.wsForm.valid) {
        whereCondition += " and VALI_FLAG = '" + this.wsForm.valid + "'";
      }
      this.$refs['medListCrspMgrGrid'].getWidget().doRefresh({
        whereCondition: whereCondition,
        parameters: {fixmedins_id: this.fixmedins_id}
      });
    },
    resetMedList1() {
      this.wsForm.med_list_codg = '';
      this.wsForm.fixmedins_hilist_id = '';
      this.wsForm.pinyin = '';
      this.wsForm.drug_name = '';
      this.wsForm.prodentp_name = '';
      this.wsForm.type = '';
      this.wsForm.spec = '';
      this.wsForm.list_type = '';
      this.wsForm.dosformCode = '';
      this.wsForm.chk_stas = '';
      this.wsForm.valid = '1';
    },
    loadMedList2() {
      let whereCondition = "1=1";
      if (this.tcForm.type && this.tcForm.type !== '2') {
        whereCondition += " and type = '" + this.tcForm.type + "'";
      }
      if (this.tcForm.med_list_codg) {
        whereCondition += " and (lower(med_list_codg) like lower('%" + this.tcForm.med_list_codg +
            "%') or lower(fixmedins_hilist_id) like lower('%" + this.tcForm.med_list_codg +
            "%') or lower(city_code) like lower('%" + this.tcForm.med_list_codg +
            "%') or lower(fix_asit_code) like lower('%" + this.tcForm.med_list_codg +
            "%') or lower(prov_code) like lower('%" + this.tcForm.med_list_codg + "%'))";
      }
      if (this.tcForm.prodentp_name) {
        whereCondition += " and prodentp_name like '%" + this.tcForm.prodentp_name + "%'";
      }
      if (this.tcForm.exct_cont) {
        whereCondition += " and exct_cont like '%" + this.tcForm.exct_cont + "%'";
      }
      if (this.tcForm.spec) {
        whereCondition += " and spec like '%" + this.tcForm.spec + "%'";
      }
      if (this.tcForm.dosform) {
        whereCondition += " and dosform = '" + this.tcForm.dosform + "'";
      }
      if (this.tcForm.drug_name) {
        whereCondition += "and (fixmedins_hilist_name like '%" + this.tcForm.drug_name + "%'" +
            " or commodity_name like '%" + this.tcForm.drug_name + "%')";
      }
      if (this.tcForm.tcmherb_year) {
        whereCondition += " and tcmherb_year = '" + this.tcForm.tcmherb_year + "'";
      }
      if (this.tcForm.cpnd_flag) {
        whereCondition += " and cpnd_flag = '" + this.tcForm.cpnd_flag + "'";
      }
      if (this.tcForm.ecy_type) {
        whereCondition += " and ecy_type like '%" + this.tcForm.ecy_type + "%'";
      }
      if (this.tcForm.efcc_atd) {
        whereCondition += " and efcc_atd like '%" + this.tcForm.efcc_atd + "%'";
      }
      if (this.tcForm.chk_stas) {
        whereCondition += " and CHK_STAS = '" + this.tcForm.chk_stas + "'";
      }
      if (this.tcForm.valid) {
        whereCondition += " and VALI_FLAG = '" + this.tcForm.valid + "'";
      }

      this.$refs['medListCrspMgr103Grid'].getWidget().doRefresh({
        whereCondition: whereCondition,
        parameters: {fixmedins_id: this.fixmedins_id}
      });
    },
    resetMedList2() {
      this.tcForm.type = '';
      this.tcForm.med_list_codg = '';
      this.tcForm.fixmedins_hilist_id = '';
      this.tcForm.drug_name = '';
      this.tcForm.tcmherb_year = '';
      this.tcForm.cpnd_flag = '';
      this.tcForm.ecy_type = '';
      this.tcForm.efcc_atd = '';
      this.tcForm.dosformCode = '';
      this.tcForm.dosform = '';
      this.tcForm.prodentp_name = '';
      this.tcForm.exct_cont = '';
      this.tcForm.spec = '';
      this.tcForm.chk_stas = '';
      this.tcForm.valid = '1';
    },

    doExport() {
      let that = this;
      let okFun = function () {
        if (that.selectTab === 0) {
          that.exportFileByWidget({
            widget: that.$refs['medListCrspMgrGrid'].getWidget(),
            expType: "all",
            fileType: "excel"
          });
        } else {
          that.exportFileByWidget({
            widget: that.$refs['medListCrspMgr103Grid'].getWidget(),
            expType: "all",
            fileType: "excel"
          });
        }
      };
      this.confirm("提示", "确定要导出查询出的药品信息吗？", okFun, null);
    },
    importFile1(file) {
      let that = this;
      let name = file.name;
      var form = {
        file: file,
      };
      var callBackFun = function (data) {
        if (data != null) {
          if (isNotNull(data.error)) {
            that.$eframe.alertMessage("导入失败：" + data.error);
            return;
          }
          if (isNotNull(data.errorCount) && data.errorCount > 0) {
            window.location.href = constData.baseUrl + "/download/" + data.excelName;
            that.$eframe.alertMessage(data.info + "详细参考：" + data.excelName);
          } else {
            that.$eframe.alertMessage("您本次成功导入" + data.successCount + "条记录");
          }
        }
        that.loadMedList1();
      };
      this.$eframe.submitTargetFileForm({
        url: "/ehis/medlistmgmt/medlist.action",
        form: form,
        actionFunName: "impMedListData",
        succFun: callBackFun,
        files: file
      });
    },
    importFile2(file) {
      let that = this;
      let name = file.name;
      var form = {
        file: file,
      };
      var callBackFun = function (data) {
        if (data != null) {
          if (isNotNull(data.error)) {
            that.$eframe.alertMessage("导入失败：" + data.error);
            return;
          }
          if (isNotNull(data.errorCount) && data.errorCount > 0) {
            window.location.href = constData.baseUrl + "/download/" + data.excelName;
            that.$eframe.alertMessage(data.info + "详细参考：" + data.excelName);
          } else {
            that.$eframe.alertMessage("您本次成功导入" + data.successCount + "条记录");
          }
        }
        that.loadMedList2();
      };
      this.$eframe.submitTargetFileForm({
        url: "/ehis/medlistmgmt/medlist.action",
        form: form,
        actionFunName: "impHerbListData",
        succFun: callBackFun,
        files: file
      });
    },
  }
}
</script>

<style lang="scss" scoped>
::v-deep .block-content {
  height: calc(100% - 37px);
}
</style>