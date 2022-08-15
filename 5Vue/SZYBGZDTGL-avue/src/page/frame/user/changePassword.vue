<template>
  <el-container style="height: 100%">
    <el-main style="height: 100%;overflow: auto;">
      <div class="main-content">
        <base-block rounded >
          <div style="margin-bottom: 15px" v-if="userInfo.init_pwd_flag==='1'">
            <span>您的账号密码为系统初始密码，请修改密码：</span>
          </div>
          <div style="margin: 0 calc(-15px - 1.25rem) 15px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px" >
            <i class="el-icon-warning" style="margin-right: 10px"></i>
            <span >密码长度为6-18位，必须由字母、数字或特殊字符组成，区分大小写。</span>
          </div>
        <el-form @submit.native.prevent ref="form" :model="form" label-position="right" label-width="65px">
          <el-row style="margin-bottom: 10px" v-show="userInfo.init_pwd_flag!=='1'">
            <el-col :span="24" >
              <ef-password label="原密码" v-model="form.password1"  name="password1" require="true"
                       placeholder="请输入原密码"  :pattern="/.*/" />
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 10px">
            <el-col :span="24">
              <ef-password label="新密码" v-model="form.password2" :maxlength=18 :minlength=6 name="password2"
                           require="true" placeholder="请输入6~18位新密码，包含字母、数字或特殊字符"/>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 10px">
            <el-col :span="24">
              <ef-password label="确认密码" v-model="form.password3" :maxlength=18 :minlength=6 name="password3"
                           require="true" placeholder="请再次输入密码"/>
            </el-col>
          </el-row>

          <div class="more-button" style="justify-content: center">
           <el-button v-prevent-re-click style="width: 100px" icon="el-icon-folder-checked" size="mini" type="primary" @click="savePwd()">保存</el-button>
          </div>
        </el-form>
        </base-block>

      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
import md5 from "js-md5";
import {MessageBox} from "element-ui";
import {mapGetters} from "vuex";

export default {
  name: "changePassword",
  mixins: [BaseCtrl],
  computed: {
    ...mapGetters(["userInfo"])
  },
  data() {
    return {
      form: {
        password1:'',
        password2:'',
        password3:''
      }
    }
  },
  created() {

  },
  mounted() {

  },
  methods: {

    savePwd(){
      let that = this;
      if(this.userInfo.init_pwd_flag!=='1'){ //原密码
        if(this.isNull(this.form.password1)){
          this.alertError("请输入原密码！");
          return;
        }
        if(this.userInfo.pwd!=md5(this.form.password1)){
          this.alertError("原密码不正确，请确认！");
          return;
        }
      }

      if(this.isNull(this.form.password2)){
        this.alertError("请输入新密码！");
        return;
      }
      if(this.isNull(this.form.password2)){
        this.alertError("请再次输入新密码！");
        return;
      }
      //设置默认密码校验  1.密码必须由字母、数字组成，区分大小写 2.密码长度为6-18位
      let reg = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$|(?=.*[~!@#$%^&*])[\da-zA-Z~!@#$%^&*]{6,18}$/
      if(!reg.test(this.form.password2)){
        this.alertError("密码必须由字母、数字或特殊字符组成，区分大小写，长度为6-18位，请确认！");
        return;
      }
      //判断新密码是否相同
      if(this.form.password2 != this.form.password3){
        this.alertError("两次密码不一致，请确认！");
        return;
      }
      // 不能与原密码相同
      if(md5(that.form.password2) === this.userInfo.pwd){
        this.alertError("新密码不能与原密码相同，请重新输入！");
        return;
      }
      let succFun = function (data) {
        that.close()
		that.$emit('changeFlag')
        // 保存成功出现提示在跳转到登录页面
        MessageBox.alert("密码修改成功，请重新登录！", "提示", {
          type: 'success'
        }).then(() => {
          //跳转到登录页面
          that.$store.dispatch("LogOut").then(() => {
            console.log("【changePassword】 1:"+"跳转到登录页面");
            that.$router.push({ path: "/login" });
          });
        }).catch(()=>{
          that.$store.dispatch("LogOut").then(() => {
            console.log("【changePassword】 2:"+"跳转到登录页面");
            that.$router.push({ path: "/login" });
          });
        })
      }
      let failFun = function (data) {

      }
      let localForm={
        uact_id: this.userInfo.uact_id,
        pwd:this.form.password2,
      }
      this.$eframe.submitTargetForm({
        url: '/ehis/fixmedins/useruact/userdetail.action',
        form: localForm,
        actionFunName: "updatePwd",
        succFun: succFun,
        failFun: failFun
      });
    },
  }
}
</script>

<style lang="scss" scoped>


</style>