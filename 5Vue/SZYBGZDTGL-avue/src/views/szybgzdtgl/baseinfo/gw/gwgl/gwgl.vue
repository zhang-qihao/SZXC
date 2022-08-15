<template>
  <el-container>
    <!--    页头-->
    <el-header>
      <!--      页头左侧-->
      <div id="head_left">
        <el-button-group>
          <el-button icon="el-icon-plus" size="mini" type="success" @click="addGW">添加岗位</el-button><!--添加岗位按钮-->
        </el-button-group>
      </div><!--页头左侧结束-->
      <!--      页头右侧-->
      <div id="head_right">
        <el-form ref="form" :model="form" label-width="auto" style="margin: 0 5px">
          <ef-text label="岗位查询：" :maxlength=20 :minlength=5 placeholder="请输入岗位名称"/>
        </el-form>
        <el-button-group style="margin: 0 5px">
          <el-button icon="el-icon-search" type="info" plain size="mini" @click=""/><!--搜索按钮-->
          <el-button icon="el-icon-refresh" type="info" plain size="mini" @click=""/><!--刷新按钮-->
        </el-button-group>
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
                <ef-page-grid ref="grid1" queryNo="Query_GWGL" pageSize="20" queryWindow="1" style="height:500px;"/>
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
  name: "gwgl",

  methods: {
    addGW() {
      this.openDialog({
        view: "views/szybgzdtgl/baseinfo/gw/gwadd",
        width: 800,
        params: {}
      });
    },

    doQuery() {
      let that = this;
      let whereCondition = "1=1";

      if(that.form.aac003!=null){
        whereCondition += " and (GW003 like '%" + that.form.gw003+ "%' or aac147 like '%" + that.form.aac003 + "%' )";

      }
      let queryGrid = this.$refs['grid1'].getWidget();
      queryGrid.selectAll=function(e,grid){
        console.log("selectAll");
      };
      queryGrid.selectRow=function(e,dataItem,grid,row){
        console.log("selectRole"+dataItem.QUERYNO);
      };
      //扩展Grid函数
      queryGrid.onSelect = function (dataItem,grid,row){
        let ac01id=dataItem.get("AC01ID");
        let ckc005=dataItem.get("CKC005");
        if(ckc005=='01'){
          //that.alertMessage("人员已离岗,无需修改");
          that.addPers('del',ac01id);
        }else{
          that.addPers('mod',ac01id);
        }


      };
      queryGrid.doRefresh({
        whereCondition: whereCondition,
        parameters: {aac003: that.form.aac003
        }

      });

    },
  }
}
</script>

<style scoped>

</style>