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
        <iframe src="/static/frame/example/formexample/form10.html" frameborder="0" style="width: 100%;height: 400px"></iframe>
      </el-dialog>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: hidden">
      <div class="main-content" style="height: 100%;">
        <base-block v-show="selectTab == 0" class="out-block" rounded ref="exampleBlock1">
          <div class="header-title" slot="title">
            <el-button-group style="margin: 0 5px">
              <el-button v-prevent-re-click icon="el-icon-refresh" type="primary" size="mini">重置</el-button>
              <el-button v-prevent-re-click icon="el-icon-folder-checked" type="primary" size="mini">保存</el-button>
            </el-button-group>
          </div>
          <!--          没有header的block添加下面的div-->
          <!--          <div class="without-header"></div>-->
          <el-form @submit.native.prevent ref="form3" :model="form" label-position="top" label-width="90px">
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
                <ef-number :pattern="/^[.\n]+$/" require="true" :decimals="0" label="数字1" v-model="form.number1"
                           name="number1"/>
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
        <base-block v-show="selectTab == 1" class="out-block" rounded ref="exampleBlock1">
          <div class="header-title" slot="title">
            <el-button-group style="margin-left: 5px">
              <el-button v-prevent-re-click type="danger" icon="el-icon-delete" size="mini">删除</el-button>
              <el-button v-prevent-re-click type="primary" icon="el-icon-folder-checked" size="mini">保存</el-button>
            </el-button-group>
          </div>
          <!--          没有header的block添加下面的div-->
          <!--          <div class="without-header"></div>-->
          <el-form @submit.native.prevent ref="form3" :model="form" label-position="top" label-width="90px">
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
                <ef-number :pattern="/^[.\n]+$/" require="true" :decimals="0" label="数字1" v-model="form.number1"
                           name="number1"/>
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
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
export default {
  name: "form10",
  mixins: [BaseCtrl],
  data() {
    return {
      selectTab:0,
      dialogTableVisible:false,
      form: {
        user1: '',
        user2: '',
      }
    }
  },
  created() {

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