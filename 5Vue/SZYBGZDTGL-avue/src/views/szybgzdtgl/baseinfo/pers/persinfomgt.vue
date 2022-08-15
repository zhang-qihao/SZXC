<template>
  <el-container style="height: 100%">
    <el-header>
      <div class="header-left">
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-plus" size="mini" type="primary" @click="addPers('add','')">新增人员</el-button>
          <el-button  size="mini" type="primary" @click="addPers('add','')">人员排序</el-button>
        </el-button-group>
      </div>
      <div class="header-right">
        <el-form ref="form" :model="form" label-width="auto" style="margin: 0 5px" >
            <ef-text label="人员查询：" v-model="form.aac003" :maxlength=20 :minlength=5 name="aac003" placeholder="请输入姓名、证件号码"/>
            <ef-select
              label="状态："
              name="ckc005"
              v-model="form.ckc005"
              :require="false"
              :emptyOption="true"
              :list="[{code:'00',name:'正常'},{code:'01',name:'离岗'}]">
          </ef-select>
        </el-form>
        <el-button-group style="margin: 0 5px">
          <el-button icon="el-icon-search" type="info" plain size="mini"  @click="doQuery"></el-button>
          <el-button icon="el-icon-refresh" type="info" plain size="mini" @click="loadData('exampleBlock2')"></el-button>
<!--          <el-button icon="el-icon-s-open" type="info" plain size="mini" @click="moreSelection = !moreSelection">更多筛选</el-button>-->
        </el-button-group>
        <div class="moreSelection" v-show="moreSelection">
          <div class="moreSelection-title">更多筛选</div>
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
      </div>
    </el-header>


    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <base-block class="out-block" style="margin-top: 1px" rounded ref="exampleBlock2" btn-option-fullscreen btn-option-content>
          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row>
              <el-col :span="24">
                <ef-page-grid ref="grid1"  queryNo="Query_001" pageSize="20" queryWindow="1" style="height:500px;"/>
              </el-col>
            </el-row>
          </el-form>
        </base-block>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
import {mapGetters} from "vuex";

export default {
  name: "persinfomgt",
  mixins: [BaseCtrl],
  data() {
    return {
      moreSelection:false,
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
      }
    }
  },
  computed: {
    ...mapGetters([
      "userInfo",
    ]),
  },
  created() {
    /*this.$nextTick(()=>{
      this.$refs['grid1'].setInitFun((queryGrid)=>{
        queryGrid.selectAll=function(e,grid){
          console.log("selectAll");
        };
        queryGrid.selectRow=function(e,dataItem,grid,row){
          console.log("selectRole"+dataItem.QUERYNO);
        };
        //扩展Grid函数
        queryGrid.onSelect = function (dataItem,grid,row){

          let ac01id=dataItem.get("AC01ID");
          this.addPers('mod',ac01id);


        };
        queryGrid.doRefresh({  });
      });
    });*/
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
    //人员新增，弹出新增页面 this.openDialog
    addPers(type,id) {
      var that = this;
      var backFun = function (back) {
        that.doQuery();
      };
      var title1 = "新增人员信息";
      if(type == 'add'){
        title1 = "新增人员信息";
      }else if(type == 'mod'){
        title1 = "人员信息修改";
      }
      this.openDialog({
        view: "views/szybgzdtgl/baseinfo/pers/persadd",
        width: 800,
        params: {type: type,ac01id:id,title1:title1},
        callBackFun: backFun
      });
    },
    //列表查询
    doQuery() {
      let that = this;
      let userId=this.userInfo.userId;
      //var userName=this.userInfo.userName;
      let whereCondition = "1=1";
      if(userId!=null){
        whereCondition += " and cae249 = '"+userId+"' ";
      }

      if(that.form.aac003!=null){
        whereCondition += " and (aac003 like '%" + that.form.aac003+ "%' or aac147 like '%" + that.form.aac003 + "%' )";

      }
      if(that.form.ckc005!=null&&that.form.ckc005!=""){
        whereCondition += " and ckc005 ='" + that.form.ckc005+ "' ";

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
    //列表刷新重置
    reFalsh(){
      let queryGrid = this.$refs['grid1'].getWidget();
      queryGrid.doRefresh({  });
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


</style>