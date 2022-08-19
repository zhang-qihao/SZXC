<template>
  <el-container style="height: 100%;height: 600px">
    <!--    页头-->
    <el-header>
      <div class="header-left">
        <div class="header-title" slot="title" style="color: #0eb1a1">{{ title1 }}</div>
      </div>
      <div class="header-right"/>
    </el-header>

    <!--    主视图-->
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <!--        科室基础信息块-->
        <base-block class="out-block" rounded ref="exampleBlock2">
          <div class="header-title" slot="title">科室基础信息</div>

          <el-form ref="form" :model="form" label-position="left" label-width="auto">


            <el-row :gutter="24">
              <el-col :span="8">
                <ef-text label="科室名称" name="ks003" v-model="form.ks003" :require="false" readonly="true"/>
              </el-col>
              <el-col :span="8">
                <ef-text label="科室负责人" name="ks020" v-model="form.ks020" :require="false" readonly="true"/>
              </el-col>
              <el-col :span="8">
                <ef-text label="分管主任" name="ks010" v-model="form.ks010" :require="false" readonly="true"/>
              </el-col>
            </el-row>

            <el-row :gutter="24">
              <el-col :span="8">
                <ef-text label="科室人数" name="pernum" v-model="form.pernum" :require="false" readonly="true"/>
              </el-col>
            </el-row>

            <el-row :gutter="24">
              <el-col :span="24">
                <ef-text label="科室职责" name="ks002" v-model="form.ks002" :require="false" readonly="true"/>
              </el-col>
            </el-row>


          </el-form>
        </base-block><!--科室基础信息块-->


        <!--        科室岗位信息块-->
        <base-block class="out-block" style="margin-top: 15px" ref="exampleBlock2">
          <div class="header-title" slot="title">科室岗位信息</div>
          <div class="header-right">
            <el-button-group>
              <el-button size="mini" type="primary" @click="printTable">打印</el-button> <!--v-print="'#printMe'"-->
            </el-button-group>
          </div>
          <div id="printMe">
            <el-form ref="form" :model="form" label-position="left" label-width="auto">
              <el-form ref="form" :model="form" label-position="left" label-width="auto">
                <ef-page-grid style="margin-top: 10px" ref="grid1" queryNo="Query_KSZH_02" pageSize="5"
                              queryWindow="1"/>
              </el-form>
            </el-form>
          </div>
        </base-block><!--科室岗位信息块-->


      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe';
import printJS from 'print-js'
import {
  mapGetters
} from "vuex";
import constData from "../../../../const";

export default {
  name: "ksdetail",
  mixins: [BaseCtrl],
  computed: {
    ...mapGetters(["userInfo",]),
  },
  data() {
    const validateID = (rule, value, callback) => {
      if (cardid(value)[0]) {
        callback(new Error(cardid(value)[1]));
      } else {
        this.form.aac006 = value.substr(6, 8);
        callback();
      }
      //  callback(new Error("请输入4位数的验证码"));
      //alert(1);
    };
    return {
      compAccept: 'jpg,png',
      compValueList: [],
      title1: '科室综合信息详情',    //弹窗标题
      onrequire: false,

      form: { // 页面数据表单
        ks001: '',  // 科室id
        ks003: '',  // 科室名称
        ks020: '',  // 科室负责人
        ks010: '',  // 分管主任
        pernum: '', // 科室人数
        ks002: '',  //科室职责
      }
    };
  },

  created() {
    this.loadData()
  },

  mounted() {
    this.$nextTick(() => {
      this.$refs['grid1'].setInitFun((queryGrid) => {
        this.doQuery()
      });
    });
  },

  methods: {

    loadData() {
      //获取传递参数
      let params = this.$attrs.params.dataItem;
      if (params) {
        if (params.get('KS001')) {
          this.form.ks001 = params.get('KS001');
        }
        if (params.get('KS003')) {
          this.form.ks003 = params.get('KS003');
        }
        if (params.get('KS010')) {
          this.form.ks010 = params.get('KS010');
        }
        if (params.get('KS020')) {
          this.form.ks020 = params.get('KS020');
        }
        if (params.get('PERNUM')) {
          this.form.pernum = params.get('PERNUM');
        }
        if (params.get('KS002')) {
          this.form.ks002 = params.get('KS002');
        }
      }
    },

    doQuery() {
      let that = this;
      let whereCondition = "1=1";
      let ks001 = that.form.ks001

      if (ks001 !== null && ks001 !== "") {
        whereCondition += " and KS001 = '" + ks001 + "'";
      }

      let queryGrid = this.$refs['grid1'].getWidget();
      queryGrid.selectAll = function (e, grid) {
        console.log("selectAll");
      };
      queryGrid.selectRow = function (e, dataItem, grid, row) {
        console.log("selectRole" + dataItem.QUERYNO);
      };
      queryGrid.doRefresh({
        whereCondition: whereCondition,
        parameters: {
          ac01id: that.form.ac01id
        }
      });
    },


    printTable() {  // 打印表格

      let succFun = function (data) {
        console.log(data)
        printJS({
          printable: data,
          type: 'json',
          properties: [{
            field: 'ks003',
            displayName: '岗位名称',
            columnSize: 1
          }, {
            field: 'ks002',
            displayName: '岗位职责描述',
            columnSize: 3
          }, {
            field: 'aac003',
            displayName: '工作人员',
            columnSize: 1
          }, {
            field: 'aac004',
            displayName: '人员性别',
            columnSize: 1
          }, {
            field: 'aac039',
            displayName: '人员性质',
            columnSize: 1
          }, {
            field: 'aac029',
            displayName: '政治面貌',
            columnSize: 1
          }],
          header: this.form.ks003 + '科岗职责说明书',
          //样式设置
          gridStyle: 'border:2px; color:black',
          gridHeaderStyle: 'color:black; border:2px;',
          headerStyle: 'text-align:center;color:#000;width:100%;',
        })
      };
      let failFun = function (data) {
        alert('获取数据失败')
      };
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/module/view/szybgzdtgl/baseinfo/ks/getPrintData.action',
        form: {ks001: this.form.ks001},
        actionFunName: "getData",
        succFun: succFun,
        failFun: failFun,
      });
    }
  }
}
</script>

<style lang="scss" scoped>
</style>