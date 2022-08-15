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
          <!--        类型选择下拉框-->
          <ef-select
              @change="doQuery"
              label="变动类型："
              placeholder="请选择类型"
              v-model="form.changeType"
              :require="false"
              :emptyOption="false"
              :list="[
                  {code:'00',name:'人员信息变动'},
                  {code:'01',name:'岗位信息变动'},
                  {code:'02',name:'科室信息变动'}
              ]">
          </ef-select><!--下拉框结束-->
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
        <base-block class="out-block" style="margin-top: 1px" rounded ref="exampleBlock2" btn-option-fullscreen
                    btn-option-content>
          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row>
              <el-col :span="24">
                <ef-page-grid ref="grid1" queryNo="Query_change" pageSize="20" queryWindow="1" style="height:500px;"/>
              </el-col>
            </el-row>
          </el-form>
        </base-block>
      </div>
    </el-main><!--主视图内容结束-->
  </el-container>
</template>

<script>
export default {
  name: "changeindex",

  data() {
    return {
      form: {
        changeType: ''
      }
    }
  },

  methods: {
    //列表查询
    doQuery() {
      let that = this;
      let whereCondition = "1=1";
      let changeT = this.form.changeType

      if (that.form.changeType !== null && that.form.changeType !== "") {
        whereCondition += " and selectedkey ='" + changeT + "' ";

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
          '变动类型': that.form.changeType
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