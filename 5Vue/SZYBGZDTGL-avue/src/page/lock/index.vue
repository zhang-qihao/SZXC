<template>
  <div class="lock-container">
    <div class="lock-form animated bounceInDown">
      <div class="animated"
           :class="{'shake':passwdError,'bounceOut':pass}">
        <h3 class="title">{{userInfo.username}}</h3>
        <el-input placeholder="请输入登录密码"
                  type="password"
                  class="input-with-select animated"
                  v-model="passwd"
                  @keyup.enter.native="handleLogin">
          <el-button-group slot="append">
            <el-button v-prevent-re-click type="info" plain icon="el-icon-unlock" @click="handleLogin"></el-button>
            <el-button v-prevent-re-click type="info" plain icon="fa fa-sign-out" @click="handleLogout"></el-button>
          </el-button-group>
        </el-input>
      </div>

    </div>
  </div>
</template>
<script>
import { mapGetters, mapState } from "vuex";
export default {
  name: "lock",
  data() {
    return {
      passwd: "",
      passwdError: false,
      pass: false
    };
  },
  created() {},
  mounted() {},
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    }),
    ...mapGetters(["tag", "lockPasswd"])
  },
  props: [],
  methods: {
    handleLogout() {
      this.$confirm("是否退出系统, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          console.log("【lock-index】 1:"+"跳转到登录页面");
          this.$router.push({ path: "/login" });
        });
      });
    },
    handleLogin() {
      if (this.passwd != this.lockPasswd) {
        this.passwd = "";
        this.$message({
          message: "解锁密码错误,请重新输入",
          type: "error"
        });
        this.passwdError = true;
        setTimeout(() => {
          this.passwdError = false;
        }, 1000);
        return;
      }
      this.pass = true;
      setTimeout(() => {
        this.$store.commit("CLEAR_LOCK");
        this.$router.push({
          path: this.$router.$avueRouter.getPath({ src: this.tag.value })
        });
      }, 1000);
    }
  },
  components: {}
};
</script>

<style lang="scss">
.lock-container {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  background-image: url("~@/assets/login/login-bg.png");
  background-size: cover;
  background-repeat: no-repeat;
  .title {
    margin-bottom: 8px;
    color: #333;
  }
}
//.lock-container::before {
//  z-index: -999;
//  content: "";
//  position: absolute;
//  left: 0;
//  top: 0;
//  width: 100%;
//  height: 100%;
//  background-image: url("/img/bg/login.png");
//  background-size: cover;
//}
.lock-form {
  width: 300px;
  div > .el-input-group--append{
    //height: 24px;
    display: flex;
    align-items: center;
  }
  div .el-input{
    //height: 24px;
  }
  div .el-button{
    //height: 24px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 ;
    flex: 1;
    flex-shrink: 0;
    border: solid 1px #dcdfe6;
  }
  div .el-button:first-child{
    border-left: none;
    border-right: none;
    border-top-left-radius: unset;
    border-bottom-left-radius: unset;
  }
  div > .el-input-group__append, .el-input-group__prepend{
    width: 100px;
    padding: 0;
    //height: 100%;
    border: none;
  }
  div > .el-button-group{
    //height: 24px;
    width: 100%;
    display: flex;
  }
  div .el-input-group__append .el-button, .el-input-group__append .el-select, .el-input-group__prepend .el-button, .el-input-group__prepend .el-select{
    margin: 0;
  }
}


</style>