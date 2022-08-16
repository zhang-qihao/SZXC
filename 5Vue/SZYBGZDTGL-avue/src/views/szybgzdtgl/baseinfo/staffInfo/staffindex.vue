<template>
  <el-container style="height: 100%">
    <!--    第一行页头-->
    <el-header>
      <div class="header-left">
        <!--        第一行页头表单-->
        <el-form ref="form" :model="form" label-width="auto" style="margin: 0 5px;">
          <ef-text label="人员查询："
                   v-model="form.info"
                   :maxlength=20
                   :minlength=5
                   name="info"
                   placeholder="输入姓名、证件号码"
                   style="margin-right: 20px"/>
          <ef-select
              label="人员性质："
              placeholder=""
              v-model="form.quality"
              :require="false"
              :emptyOption="false"
              :list="[
                  {code:'1',name:'公务员及参公'},
                  {code:'2',name:'公益性岗位'},
                  {code:'3',name:'第三方（商保）'},
                  {code:'4',name:'第三方（其他）'}
              ]"
              style="margin-right: 20px"/>
          <ef-select
              label="政治面貌："
              placeholder=""
              v-model="form.mm"
              :require="false"
              :emptyOption="false"
              :list="[
                  {code: '1', name: '中共党员'},
                  {code: '2', name: '中共预备党员'},
                  {code: '3', name: '共青团员'},
                  {code: '4', name: '民主党派人士'},
                  {code: '5', name: '群众'}
              ]"
              style="margin-right: 20px"/>
          <ef-select
              label="所属科室："
              placeholder=""
              v-model="form.ks"
              :require="false"
              :emptyOption="false"
              :list="[

              ]"
              style="margin-right: 20px"/>
          <ef-date-range
              label="出生日期："
              :require="false"
              :emptyOption="false"
              v-model="form.dateRange"
              style="max-width: 300px"/>
        </el-form><!--第一行表单结束-->
      </div><!--左表头结束-->
      <!--      右表头-->
      <div class="header-right"/>
    </el-header><!--第一行页头结束-->
    <!--    第二行页头-->
    <el-header>
      <!--      左表头-->
      <div class="header-left">
        <!--        第二行页头表单-->
        <el-form ref="form" :model="form" label-width="auto" style="margin: 0 5px;">
          <ef-select
              label="职务："
              placeholder=""
              v-model="form.zw"
              :require="false"
              :emptyOption="false"
              :list="[
                  {code: '1', name: '主任'},
                  {code: '2', name: '副主任'},
                  {code: '3', name: '科长'},
                  {code: '4', name: '副科长（主持工作）'},
                  {code: '5', name: '副科长'},
                  {code: '6', name: '科员'},
                  {code: '7', name: '公益性岗位人员'},
                  {code: '8', name: '第三方人员'}
              ]"
              style="margin-right: 20px"/>
          <ef-select
              label="职级："
              placeholder=""
              v-model="form.zj"
              :require="false"
              :emptyOption="false"
              :list="[
                  {code: '0', name: '无'},
                  {code: '1', name: '四级调研员'},
                  {code: '2', name: '一级主任科员'},
                  {code: '3', name: '二级主任科员'},
                  {code: '4', name: '三级主任科员'},
                  {code: '5', name: '四级主任科员'},
                  {code: '6', name: '一级科员'},
              ]"/>
        </el-form><!--第二行表单结束-->
      </div><!--左表头结束-->
      <!--      右表头-->
      <div class="header-left">
        <!--        第二行按钮组-->
        <el-button-group>
          <el-button icon="el-icon-search" type="info" plain size="mini" @click="doQuery"></el-button>
          <el-button icon="el-icon-refresh" type="info" plain size="mini"
                     @click="loadData('exampleBlock2')"></el-button>
        </el-button-group><!--第二行按钮组结束-->
      </div>
    </el-header><!--第二行页头结束-->

    <!--    主视图内容-->
    <el-main>
      <div class="main-content">
        <base-block class="out-block" style="margin-top: 1px" rounded ref="exampleBlock2" btn-option-fullscreen
                    btn-option-content>
          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row>
              <el-col :span="24">
                <ef-page-grid ref="grid1" queryNo="Query_RYZH" pageSize="20" queryWindow="1"
                              style="margin-top: 25px"/>
              </el-col>
            </el-row>
          </el-form>
        </base-block>
      </div>
    </el-main><!--主视图内容结束-->

  </el-container>
</template>

<script>
// 导入人员信息字典
import {BaseCtrl} from '/src/util/eframe'

export default {
  name: "staffindex",
  mixins: [BaseCtrl],
  data() {
    return {
      form: {
        info: '',
        quality: '',
        mm: '',
        ks: '',
        zw: '',
        zj: '',
        dateRange: null
      }
    }
  },

  mounted() {
    this.$nextTick(() => {
      this.$refs['grid1'].setInitFun((queryGrid) => {
        this.doQuery()
      });
    });
  },

  methods: {

    infoDetail(dataItem) {
      let that = this;
      var backFun = function (back) {
        that.doQuery();
      };
      this.openDialog({
        view: "views/szybgzdtgl/baseinfo/staffInfo/staffdetail",
        width: 800,
        params: {dataItem: dataItem},
        callBackFun: backFun
      });
    },

    doQuery() {
      let that = this;
      let whereCondition = "1=1";

      if (that.form.info !== null && that.form.info !== "") {
        whereCondition += " and (姓名 like '%" + that.form.info + "%' or 证件号码 like '%" + that.form.info + "%' )";
      }
      if (that.form.quality !== null && that.form.quality !== "") {
        whereCondition += " and 人员性质 ='" + that.form.quality + "' ";
      }
      if (that.form.mm !== null && that.form.mm !== "") {
        whereCondition += " and 政治面貌 ='" + that.form.mm + "' ";
      }
      if (that.form.ks !== null && that.form.ks !== "") {
        whereCondition += " and 所属科室 ='" + that.form.ks + "' ";
      }
      if (that.form.zw !== null && that.form.zw !== "") {
        whereCondition += " and 职务 ='" + that.form.zw + "' ";
      }
      if (that.form.zj !== null && that.form.zj !== "") {
        whereCondition += " and 职级 ='" + that.form.zj + "' ";
      }
      if (that.form.dateRange !== null) {
        let startdate = that.form.dateRange[0]
        let enddate = that.form.dateRange[1]
        whereCondition += " and 出生日期 between " +
            "to_date('" + startdate + "', 'yyyy-MM-dd') and to_date('" + enddate + "', 'yyyy-MM-dd') "
      }

      let queryGrid = this.$refs['grid1'].getWidget();
      queryGrid.selectAll = function (e, grid) {
        console.log("selectAll");
      };
      queryGrid.selectRow = function (e, dataItem, grid, row) {
        console.log("selectRole" + dataItem.QUERYNO);
      };
      queryGrid.onPersSelect = function (dataItem) {
        that.infoDetail(dataItem)
      };
      queryGrid.doRefresh({
        whereCondition: whereCondition,
        parameters: {
          info: that.form.info
        }
      });


    },

    loadData(ref) {
      // Set the block to loading state
      this.$refs[ref].stateLoading()

      // .. here you could load your data

      // Set a timeout for demo purposes
      setTimeout(() => {
        // Set the block back to normal state
        this.$refs[ref].stateNormal()
      }, 2000)
    },
  }
}
</script>

<style scoped>

</style>