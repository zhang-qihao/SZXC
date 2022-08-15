<template>
  <el-container style="height: 100%;" v-cloak>
    <el-header>
      <div class="header-left">
        <div class="header-left-tabbar">
          <div class="header-left-tab" @click="changeSelectTab(0)" :class="{'header-left-tab-active':selectTab == 0}">用户维护</div>
          <div class="header-left-tab" @click="changeSelectTab(1)" :class="{'header-left-tab-active':selectTab == 1}">用户权限</div>
        </div>
      </div>
      <div class="header-right">
        <el-form  ref="form" :model="form" label-width="auto">
          <ef-text :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
        </el-form>
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-search" type="info" plain size="mini"></el-button>
          <el-button icon="el-icon-refresh" type="info" plain size="mini"></el-button>
          <el-popover
              style="background: #f0f2f5;"
              placement="bottom"
              title="更多筛选"
              width="1200"
              trigger="click">
            <!--      更多筛选-->
            <div>
              <el-form ref="form" :model="form" label-position="top" label-width="auto">
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                </el-row>
                <div class="border-line"></div>
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                </el-row>
                <div class="more-button">
                  <el-button icon="el-icon-s-open" size="mini" type="success">筛选</el-button>
                  <el-button icon="el-icon-refresh" size="mini" type="primary">重置</el-button>
                </div>
              </el-form>
            </div>
            <el-button slot="reference" icon="el-icon-s-open" size="mini" type="info" plain>更多筛选</el-button>
          </el-popover>
        </el-button-group>
      </div>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: hidden">
      <div class="main-content" style="height: 100%;">
        <ef-page-grid v-show="selectTab == 0" ref="grid1" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:100%;"/>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
export default {
  name: "grid4",
  mixins: [BaseCtrl],
  data() {
    return {
      dialogTableVisible: false,
      selectTab:0,
      form: {
        user1: '',
        user2: '',
      }
    }
  },
  created() {
    this.$nextTick(()=>{
      this.$refs['grid1'].setInitFun((queryGrid)=>{
        queryGrid.selectAll=function(e,grid){
          console.log("selectAll");
        };
        queryGrid.selectRow=function(e,dataItem,grid,row){
          console.log("selectRole"+dataItem.QUERYNO);
        };
        //扩展Grid函数
        queryGrid.onSelect = function (dataItem,grid,row){
          let queryNo=dataItem.get("QUERYNO");
          alert(queryNo);
        };
        queryGrid.doRefresh({  });
      });
    });
  },
  mounted()  {
    //初始化操作或写在此地
    // let queryGrid = this.$refs['grid1'].getWidget();
    // queryGrid.selectAll=function(e,grid){
    //    console.log("selectAll");
    // };
    // queryGrid.selectRow=function(e,dataItem,grid,row){
    //    console.log("selectRole"+dataItem.QUERYNO);
    // };
    // //扩展Grid函数
    // queryGrid.onSelect = function (dataItem,grid,row){
    //    let queryNo=dataItem.get("QUERYNO");
    //    alert(queryNo);
    // };
    // queryGrid.doRefresh({  });
  },
  methods: {
      changeSelectTab(num){
        this.selectTab = num
      }
  }
}
</script>

<style lang="scss" scoped>



</style>