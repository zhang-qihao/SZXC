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
        <iframe src="/static/frame/example/showpageexample/showpage7.html" frameborder="0" style="width: 100%;height: 400px"></iframe>
      </el-dialog>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: hidden">
      <div class="main-content" style="height: 100%;">
        <base-block v-show="selectTab == 0" class="out-block" rounded ref="exampleBlock1">
          <div class="header-title" slot="title">
            <span><i class="el-icon-user-solid"></i>我是标题</span>
          </div>
          <!--          没有header的block添加下面的div-->
          <!--          <div class="without-header"></div>-->
          <el-form @submit.native.prevent ref="form3" :model="form" label-width="80px" label-position="right">
            <el-row :gutter="20">
              <el-col :span="8">
                <ef-show :show-type="showType" label="姓名" name="name" v-model="name"/>
              </el-col>
              <el-col :span="8">
                <ef-show :show-type="showType" label="性别" name="sex" v-model="sex"/>
              </el-col>
              <el-col :span="8">
                <ef-show :show-type="showType" label="年龄" name="age" v-model="age"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="16">
                <ef-show :show-type="showType" label="电话号码" name="tel" v-model="tel"/>
              </el-col>
              <el-col :span="8">
                <ef-show :show-type="showType" label="籍贯" name="place1" v-model="place1"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="24">
                <ef-show :show-type="showType" label="联系地址" name="place2" v-model="place2"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <ef-show :show-type="showType" label="婚姻状况" name="marry" v-model="marry"/>
              </el-col>
              <el-col :span="8">
                <ef-show :show-type="showType" label="名族" name="mz" v-model="mz"/>
              </el-col>
              <el-col :span="8">
                <ef-show :show-type="showType" label="健康状况" name="health" v-model="health"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <ef-show :show-type="showType" label="籍贯" name="place1" v-model="place1"/>
              </el-col>
              <el-col :span="16">
                <ef-show :show-type="showType" label="电话号码" name="tel" v-model="tel"/>
              </el-col>
            </el-row>
          </el-form>
        </base-block>

        <div v-show="selectTab == 1" style="height: 100%">
          <ef-page-grid ref="grid1" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:100%;"/>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
export default {
  name: "showpage7",
  mixins: [BaseCtrl],
  data() {
    return {
      selectTab:0,
      dialogTableVisible:false,
      showType:['type2','textRight'],
      visable:false,
      name:'张三',
      sex:'男',
      age:'18',
      tel:'18896960909',
      place1:'江苏苏州',
      place2:'江苏省苏州市相城区港龙路',
      mz:'汉',
      marry:'已婚',
      health:'健康',
      form: {
        user1: '',
        user2: '',
        telephone1: '',
        mail1: '',
        password2: '',
        date1: '',
        month1: '',
        time1: '',
        datetime1: '',
        number1: '',
        money1: '',
        switch1: false,
        textarea: ''
      },
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

  },
  methods: {
    changeSelectTab(num){
      this.selectTab = num
    },
    testOnChange(data) {
      console.log("testOnChange::::" + data);
    },

    testOnInput(data) {
      console.log("testOnInput::" + data);
    },
  }
}
</script>

<style lang="scss" scoped>

</style>