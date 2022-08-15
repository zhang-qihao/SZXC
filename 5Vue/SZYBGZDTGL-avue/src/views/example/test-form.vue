<template>
  <el-container style="height: 100%">
    <el-header>
      <div class="header-left">
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-plus" size="mini" type="primary" @click="dialogTableVisible = true">显示源码</el-button>
          <el-button icon="el-icon-folder-checked" size="mini" type="success">保存</el-button>
          <el-button icon="el-icon-delete" size="mini" type="danger">删除</el-button>
          <el-button size="mini" type="primary">复制权限</el-button>
        </el-button-group>
      </div>
      <div class="header-right">
        <el-form  ref="form" :model="form" label-width="auto">
          <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
        </el-form>
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-plus" size="mini" type="primary">添加</el-button>
        </el-button-group>
      </div>
      <!--      显示源码-->
      <el-dialog title="源码展示" :visible.sync="dialogTableVisible" :modal-append-to-body="false">
        <iframe src="/static/frame/example/formexample/form1.html" frameborder="0"
                style="width: 100%;height: 400px"></iframe>
      </el-dialog>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <base-block class="out-block" rounded ref="exampleBlock2" btn-option-fullscreen btn-option-content>
          <div class="header-title" slot="title"><i class="el-icon-user-solid"></i>单位信息</div>
          <template slot="options">
            <div class="options-content">
              <el-form  ref="form" :model="form" label-width="auto">
                <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
              </el-form>
              <el-button-group style="margin: 0 5px">
                <el-button icon="el-icon-search" type="info" plain size="mini"></el-button>
                <el-button icon="el-icon-refresh" type="info" plain size="mini" @click="loadData('exampleBlock2')"></el-button>
              </el-button-group>
            </div>
          </template>
          <el-form ref="form" :model="form" label-position="top" label-width="auto">
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                         validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                         placeholder="user1"/>
              </el-col>
              <el-col :span="6">
                <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
              </el-col>
              <el-col :span="6">
                <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                         :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                         @onChange="testOnChange" @onInput="testOnInput"/>
              </el-col>
              <el-col :span="6">
                <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-date label="日期选择1" v-model="form.date1" name="date1" format="yyyy-MM-dd"/>
              </el-col>
              <el-col :span="6">
                <ef-date label="月份选择1" v-model="form.month1" name="month1" format="yyyy-MM"/>
              </el-col>
              <el-col :span="6">
                <ef-time label="时间选择1" v-model="form.time1" name="time1" format="HH时mm分ss秒"/>
              </el-col>
              <el-col :span="6">
                <ef-datetime label="日期时间选择1" v-model="form.datetime1" name="datetime1" require="true"
                             format="yyyyMMdd HH:mm:ss"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-number label="数字1" v-model="form.number1" name="number1"/>
              </el-col>
              <el-col :span="6">
                <ef-money label="金额1" v-model="form.money1" name="money1"/>
              </el-col>
              <el-col :span="6">
                <ef-switch label="开关1" v-model="form.switch1" name="switch1"/>
              </el-col>
              <el-col :span="6">
                <ef-datetime label="日期时间选择1" v-model="form.datetime1" name="datetime1" require="true"
                             format="yyyyMMdd HH:mm:ss"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <ef-textarea :rows="3" label="测试textarea" v-model="form.textarea" maxlength="50" name="textarea"
                             require="true"/>
              </el-col>
            </el-row>
          </el-form>
        </base-block>
        <base-block class="out-block" style="margin-top: 15px" rounded ref="exampleBlock2" btn-option-fullscreen btn-option-content>
          <div class="header-title" slot="title"><i class="el-icon-user-solid"></i>基础信息</div>
          <template slot="options">
            <div class="options-content">
              <el-form  ref="form" :model="form" label-width="auto">
                <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
              </el-form>
              <el-button-group style="margin: 0 5px">
                <el-button icon="el-icon-search" type="info" plain size="mini"></el-button>
                <el-button icon="el-icon-refresh" type="info" plain size="mini" @click="loadData('exampleBlock2')"></el-button>
              </el-button-group>
            </div>
          </template>
          <el-form ref="form" :model="form" label-position="top" label-width="auto">
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                         validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                         placeholder="user1"/>
              </el-col>
              <el-col :span="6">
                <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
              </el-col>
              <el-col :span="6">
                <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                         :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                         @onChange="testOnChange" @onInput="testOnInput"/>
              </el-col>
              <el-col :span="6">
                <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                         validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                         placeholder="user1"/>
              </el-col>
              <el-col :span="6">
                <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
              </el-col>
              <el-col :span="6">
                <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                         :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                         @onChange="testOnChange" @onInput="testOnInput"/>
              </el-col>
              <el-col :span="6">
                <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
              </el-col>
            </el-row>
          </el-form>
          <base-block rounded ref="exampleBlock2">
            <div class="header-title" slot="title">联系方式</div>
            <template slot="options">
              <div class="options-content">
                <el-form ref="form" :model="form" label-width="auto">
                  <ef-switch class="form-switch" v-model="form.switch1" name="switch1" field-value="1"/>
                </el-form>
                <div style="margin-left: 5px;font-size: 14px">是否虚拟用户</div>
              </div>
            </template>
            <div v-if="form.switch1 == true">
              <el-form class="inner-block-form" ref="form" :model="form" label-position="top" label-width="auto">
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                             :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                             @onChange="testOnChange" @onInput="testOnInput"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                             :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                             @onChange="testOnChange" @onInput="testOnInput"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </base-block>
          <base-block rounded ref="exampleBlock2">
            <div class="header-title" slot="title">用户标识</div>
            <el-form class="inner-block-form" ref="form" :model="form"
                     label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="6">
                  <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                           validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                           placeholder="user1"/>
                </el-col>
                <el-col :span="6">
                  <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
                </el-col>
                <el-col :span="6">
                  <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                           :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                           @onChange="testOnChange" @onInput="testOnInput"/>
                </el-col>
                <el-col :span="6">
                  <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="6">
                  <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                           validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                           placeholder="user1"/>
                </el-col>
                <el-col :span="6">
                  <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
                </el-col>
                <el-col :span="6">
                  <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                           :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                           @onChange="testOnChange" @onInput="testOnInput"/>
                </el-col>
                <el-col :span="6">
                  <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
                </el-col>
              </el-row>
            </el-form>
          </base-block>
        </base-block>
        <base-block class="out-block" style="margin-top: 15px" rounded ref="exampleBlock2" btn-option-fullscreen btn-option-content>
          <div class="header-title" slot="title"><i class="el-icon-user-solid"></i>查询信息</div>
          <template slot="options">
            <div class="options-content">
              <el-form ref="form" :model="form" label-width="auto">
                <ef-text v-model="form.user1" :maxlength=20 :minlength=5 name="user1" placeholder="user1"/>
              </el-form>
              <el-button-group style="margin: 0 5px">
                <el-button icon="el-icon-search" type="info" plain size="mini"></el-button>
                <el-button icon="el-icon-refresh" type="info" plain size="mini" @click="loadData('exampleBlock2')"></el-button>
                <el-button icon="el-icon-s-open" type="info" plain size="mini" @click="moreSelection = !moreSelection">更多筛选</el-button>
              </el-button-group>
            </div>
          </template>
          <!--          更多筛选-->
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
          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row :gutter="20">
              <el-col :span="6">
                <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                         validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                         placeholder="user1"/>
              </el-col>
              <el-col :span="6">
                <ef-tel label="移动电话1111" v-model="form.telephone1" name="telephone1" :require="true"/>
              </el-col>
              <el-col :span="6">
                <ef-mail label="邮箱1" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                         :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                         @onChange="testOnChange" @onInput="testOnInput"/>
              </el-col>
              <el-col :span="6">
                <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <ef-page-grid ref="grid1" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:300px;"/>
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

export default {
  name: "form1",
  mixins: [BaseCtrl],
  data() {
    return {
      dialogTableVisible: false,
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