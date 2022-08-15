<template>
  <el-container style="height: 100%;" v-cloak>
    <el-header>
      <div class="header-left">
        <div class="header-left-tabbar">
          <div class="header-left-tab" @click="changeSelectTab(0)" :class="{'header-left-tab-active':selectTab == 0}">用户维护</div>
          <div class="header-left-tab" @click="changeSelectTab(1)" :class="{'header-left-tab-active':selectTab == 1}">用户权限</div>
        </div>
        <el-button-group style="margin-left: 5px">
          <el-button v-prevent-re-click icon="el-icon-plus" size="mini" type="primary" @click="dialogTableVisible = true">显示源码</el-button>
        </el-button-group>
      </div>
      <div class="header-right">

      </div>
      <!--      显示源码-->
      <el-dialog title="源码展示" :visible.sync="dialogTableVisible" :modal-append-to-body="false">
        <iframe src="/static/frame/example/gridexample/grid3.html" frameborder="0" style="width: 100%;height: 400px"></iframe>
      </el-dialog>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: hidden">
      <div class="main-content" style="height: 100%;">
        <base-block style="height: 100%" round class="out-block" v-show="selectTab == 0">
          <div class="header-title" slot="title">
            <el-button-group style="margin: 0 5px">
              <el-button v-prevent-re-click icon="el-icon-plus" type="primary" size="mini">新增用户</el-button>
              <el-button v-prevent-re-click icon="el-icon-plus" type="primary" size="mini">新增机构</el-button>
            </el-button-group>
          </div>
          <template slot="options">
            <div class="options-content">
              <el-form @submit.native.prevent ref="form4" :model="form" label-width="0">
                <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
              </el-form>
              <el-button-group style="margin: 0 5px">
                <el-button v-prevent-re-click icon="el-icon-search" type="info" plain size="mini"></el-button>
                <el-button v-prevent-re-click icon="el-icon-refresh" type="info" plain size="mini"
                           @click="loadData('exampleBlock2')"></el-button>
              </el-button-group>
            </div>
          </template>
          <ef-page-grid ref="grid1" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:100%;"/>
        </base-block>
        <base-block style="height: 100%" round class="out-block" v-show="selectTab == 1">
          <div class="header-title" slot="title">
            <el-button-group style="margin: 0 5px">
              <el-button v-prevent-re-click icon="el-icon-delete" type="danger" size="mini">删除</el-button>
              <el-button v-prevent-re-click icon="el-icon-folder-checked" type="primary" size="mini">保存</el-button>
            </el-button-group>
          </div>
          <template slot="options">
            <div class="options-content">
              <el-form @submit.native.prevent ref="form4" :model="form" label-width="0">
                <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
              </el-form>
              <el-button-group style="margin: 0 5px">
                <el-button v-prevent-re-click icon="el-icon-search" type="info" plain size="mini"></el-button>
                <el-button v-prevent-re-click icon="el-icon-refresh" type="info" plain size="mini" @click="loadData('exampleBlock2')"></el-button>
                <el-popover
                    popper-class="popover-content"
                    placement="bottom"
                    width="400"
                    v-model="visable"
                    trigger="click">
                  <!--      更多筛选-->
                  <div>
                    <el-form @submit.native.prevent ref="form2" :model="form" label-position="top" label-width="90px">
                      <el-row :gutter="20">
                        <el-col :span="24">
                          <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                                   validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                                   placeholder="user1"/>
                        </el-col>
                      </el-row>
                      <el-row :gutter="20">
                        <el-col :span="24">
                          <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
                        </el-col>
                      </el-row>
                      <el-row :gutter="20">
                        <el-col :span="24">
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
                      <el-row :gutter="20">
                        <el-col :span="24">
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
                        <el-button v-prevent-re-click icon="el-icon-s-open" size="mini" type="primary">筛选</el-button>
                        <el-button v-prevent-re-click icon="el-icon-refresh" size="mini" type="primary" plain>重置</el-button>
                      </div>
                    </el-form>
                  </div>
                  <el-button v-prevent-re-click slot="reference" icon="el-icon-s-open" size="mini" type="info" plain>更多筛选</el-button>
                </el-popover>
              </el-button-group>
            </div>
          </template>
          <ef-page-grid ref="grid2" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:100%;"/>
        </base-block>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
export default {
  name: "grid3",
  mixins: [BaseCtrl],
  data() {
    return {
      visable:false,
      dialogTableVisible: false,
      selectTab:0,
      form: {
        user1: '',
        user2: '',
      }
    }
  },
  beforeRouteLeave(to,from,next){
    this.visable = false
    next()
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
      this.$refs['grid2'].setInitFun((queryGrid)=>{
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

  },
  methods: {
    changeSelectTab(num){
      this.selectTab = num
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

<style lang="scss" scoped>
::v-deep .block .block-content{
  height: calc(100% - 37px);
}
</style>