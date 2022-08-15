<template>
  <div class="login-container"
       @keyup.enter.native="handleLogin">
    <top-color v-show="false"></top-color>
    <div class="login-weaper animated bounceInDown">
      <div class="login-left">
        <div class="login-time">
          {{ time }}
        </div>
        <!--        <img class="img"-->
        <!--             src="https://avuejs.com/images/logo.png"-->
        <!--             alt="">-->
        <!--        <p class="title">{{ $t('login.info') }}</p>-->
        <div class="title-box">
          <div class="title">欢迎登录</div>
          <div class="title">岗位职责管理系统</div>
        </div>
      </div>
      <div class="login-border">
        <div class="login-header">
          <a class="login-tab" :class="{'login-tab-active':activeName == 'user'}" href="#" @click.stop="activeName='user'">
            <div >
              账号登录
            </div>
          </a>
<!--          <a class="login-tab" :class="{'login-tab-active':activeName == 'code'}" href="#" @click.stop="activeName='code'">
            <div >
              手机号登录
            </div>
          </a>-->
        </div>
        <div class="login-main">
          <!--          <h4 class="login-title">-->
          <!--            {{ $t('login.title') }}{{ website.title }}-->
          <!--            <top-lang></top-lang>-->
          <!--          </h4>-->
          <userLogin v-if="activeName==='user'"></userLogin>
          <codeLogin v-else-if="activeName==='code'"></codeLogin>
          <thirdLogin v-else-if="activeName==='third'"></thirdLogin>
          <faceLogin v-else-if="activeName==='face'"></faceLogin>
          <!--          <div class="login-menu">-->
          <!--            <a href="#"-->
          <!--               @click.stop="activeName='user'">{{ $t('login.userLogin') }}</a>-->
          <!--            <a href="#"-->
          <!--               @click.stop="activeName='code'">{{ $t('login.phoneLogin') }}</a>-->
          <!--            <a href="#"-->
          <!--               @click.stop="activeName='face'">{{ $t('login.faceLogin') }}</a>-->
          <!--            <a href="#"-->
          <!--               @click.stop="activeName='third'">{{ $t('login.thirdLogin') }}</a>-->
          <!--          </div>-->
          <!--          <div class="login-tool">-->
          <!--            <span><a href="">忘记密码?</a></span>-->
          <!--            <span>还没有账号?&nbsp;<span><a href="">去注册</a></span></span>-->
          <!--          </div>-->
        </div>
        <div class="login-suggestion">建议使用1920*1080分辨率 Google浏览器 <a href="https://www.google.cn/chrome/" target="_blank">去下载&nbsp;>></a></div>
      </div>
    </div>
    <div class="login-footer"> 版权所有©兴财科技&nbsp;&nbsp;2021-2031</div>
  </div>
</template>
<script>
import userLogin from "./userlogin";
import codeLogin from "./codelogin";
import thirdLogin from "./thirdlogin";
import faceLogin from "./facelogin";
import {mapGetters} from "vuex";
import {dateFormat} from "@/util/date";
import {validatenull} from "@/util/validate";
import topLang from "@/page/index/top/top-lang";
import topColor from "@/page/index/top/top-color";
import { Base64 } from 'js-base64';
import {
  setToken,
  removeToken
} from '@/util/auth';
export default {
  name: "login",
  components: {
    userLogin,
    codeLogin,
    thirdLogin,
    faceLogin,
    topLang,
    topColor
  },
  data() {
    return {
      time: "",
      activeName: "user",
    };
  },
  watch: {
    $route() {

      const params = this.$route.query;
      this.socialForm.state = params.state;
      this.socialForm.code = params.code;
      if (!validatenull(this.socialForm.state)) {
        const loading = this.$loading({
          lock: true,
          text: `${
              this.socialForm.state === "WX" ? "微信" : "QQ"
          }登录中,请稍后。。。`,
          spinner: "el-icon-loading"
        });
        setTimeout(() => {
          loading.close();
        }, 2000);
      }
    }
  },
  created() {
    this.getTime();
    setInterval(() => {
      this.getTime();
    }, 1000);
  },
  mounted() {
  },
  computed: {
    ...mapGetters(["website","tagWel"])
  },
  props: [],
  methods: {
    getTime() {
      this.time = dateFormat(new Date());
    },
    getQueryVariable(variable) {
      var query = window.location.search.substring(1);
      var vars = query.split("&");
      for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
          return pair[1];
        }
      }
      return (false);
    },
  }
};
</script>

<style lang="scss">
@import "@/styles/login.scss";
</style>