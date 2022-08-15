<template>
  <div class="avue-logo" id="loginLogo">
    <transition name="fade">
      <span v-if="isCollapse"
            class="avue-logo_subtitle"
            key="0">
        <div class="logo-img" style="height: 100%;display: flex;align-items: center;justify-content: center;">
            <i class="iconfont icon-yun" style="font-size: 30px;font-weight: 400"></i>
          <!--          <img src="./img/logo.png">-->
        </div>
      </span>
    </transition>
    <transition-group name="fade">
      <template v-if="!isCollapse">
        <span class="avue-logo_title" key="1">
          <div class="logo-img" style="height: 100%;display: flex;align-items: center;justify-content: center;margin-right:5px">
            <i class="iconfont icon-yun" style="font-size: 30px;font-weight: 400"></i>
            <!--          <img src="./img/logo.png">-->
        </div>
          <div>{{ title }}
            <span style="font-size: 8px;font-family:'微软雅黑';">{{ exeVersion }}</span>
          </div>
        </span>
      </template>
    </transition-group>
    <!--    <el-dialog title="密码修改"-->
    <!--        :visible.sync="dialogVisible"-->
    <!--        width="460px"-->
    <!--        :before-close="handleClose" style="line-height: 20px;">-->
    <!--      <changerecord-pwd @changeFlag='changeFlag' :dataForm="form"></changerecord-pwd>-->
    <!--    </el-dialog>-->
  </div>
</template>

<script>
import {mapGetters} from "vuex";
// import {MessageBox} from 'element-ui';
import changePwd from "../../page/frame/user/changePassword";
import website from "../../config/website";

export default {
  name: "logo",
  components: {
    changePwd,
  },
  data() {
    return {
      // dialogVisible: false,
      title: "",
      exeVersion: "",
      form: {}
    };
  },
  created() {
    let that = this;
    //TODO 系统名称
    that.title = website.title;
    if (that.title.endsWith("系统")) {
      that.title = that.title.substring(0, that.title.length - 2);
    }
    if (window.getExeVersion) {//取版本号
      that.exeVersion = window.getExeVersion();
    }
    // if (this.userInfo.fixmedins_type === "2") {
    //   that.title = "云药店";
    // } else {
    //   that.title = "云诊所";
    // }
    // if (this.userInfo.init_pwd_flag === '1') {
    //   that.dialogVisible = true;
    // }

  },
  mounted() {
  },
  computed: {
    ...mapGetters(["website", "tagWel", "isCollapse", "userInfo"])
  },
  methods: {
    // changeFlag() {
    //   this.dialogVisible = false;
    // },
    handleClose(done) {

    }
  }
};
</script>

<style lang="scss">
#loginLogo .el-dialog__headerbtn .el-dialog__close {
  color: white;
}

.fade-leave-active {
  transition: opacity 0.2s;
}

.fade-enter-active {
  transition: opacity 2.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.avue-logo {
  height: 64px;
  line-height: 64px;
  background-color: #20222a;
  font-size: 20px;
  overflow: hidden;
  box-sizing: border-box;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);
  color: rgba(255, 255, 255, 0.8);

  &_title {
    display: block;
    text-align: center;
    font-weight: 300;
    font-size: 20px;
  }

  &_subtitle {
    display: block;
    text-align: center;
    font-size: 18px;
    font-weight: bold;
    color: #fff;
  }
}
</style>