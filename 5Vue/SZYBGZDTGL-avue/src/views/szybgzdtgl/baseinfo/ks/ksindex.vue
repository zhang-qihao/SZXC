<template>
  <el-container>
    <!--    页头-->
    <el-header>
      <!--      页头左侧-->
      <div class="head_left"/>
      <!--      页头右侧-->
      <div class="header-right">
        <!--        类型选择表单-->
        <el-form ref="form" :model="form" label-width="auto" style="margin: 0 5px">
          <ef-text label="科室名称："
                   v-model="form.ks003"
                   :maxlength=20
                   :minlength=5
                   name="info"
                   placeholder=""
                   style="margin-right: 20px"/>
        </el-form><!--下拉框表单结束-->
        <!--        查询刷新按钮组-->
        <el-button-group style="margin: 0 5px">
          <el-button icon="el-icon-search" type="info" plain size="mini" @click="doQuery"></el-button>
          <el-button icon="el-icon-refresh" type="info" plain size="mini"
                     @click="loadData('exampleBlock2')"></el-button>
        </el-button-group><!--按钮组结束-->
      </div><!--页头右侧结束-->
    </el-header><!--页头结束-->

    <!--    主视图内容-->
    <el-main>
      <div class="main-content">
        <base-block class="out-block" style="margin-top: 1px" ref="exampleBlock2">
          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row>
              <el-col :span="24">
                <ef-page-grid ref="grid1" queryNo="Query_change" pageSize="20" queryWindow="1"
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
import {BaseCtrl} from "@/util/eframe";

export default {
  name: "ksindex",
  mixins: [BaseCtrl],

  data() {
    return {
      form: {
        ks003: '',
        ckc005: ''
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
    //列表查询
    doQuery() {
      let that = this;
      let whereCondition = "1=1";
      let ks003 = this.form.ks003
      let ckc005 = that.form.ckc005

      if (ckc005 === null || ckc005 === "") {
        whereCondition += " and CKC005 ='" + ckc005 + "'";
      }
      if (ks003 !== null && ks003 !== "") {
        whereCondition += " and KS003 like %'" + ks003 + "'% ";
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
          ks003: that.form.ks003
        }
      });

    },
    loadData(ref) {
      this.$refs[ref].stateLoading()

      setTimeout(() => {
        this.$refs[ref].stateNormal()
      }, 2000)
    },
  }
}
</script>

<style scoped>

</style>