<template>
  <div style="width: 100%;height: 100%">
    <IndexT1 v-if="website.mainPageFrameType == 'T1'"/>
    <IndexT2 v-if="website.mainPageFrameType == 'T2'"/>
  </div>

</template>

<script>
import IndexT1 from "./index-T1"
import IndexT2 from "./index-T2"
import {mapGetters} from "vuex";
import yb from "/src/util/yb";
import eframe from "/src/util/eframe";
import admin from "/src/util/admin";
import {validatenull} from "/src/util/validate";
import {calcDate} from "/src/util/date.js";
import {getStore} from "/src/util/store.js";
import servpara from "/src/util/servpara";


export default {
  components: {
    IndexT1,
    IndexT2
  },
  name: "index",
  computed: mapGetters(["website", "userInfo"]),
  data() {
    return {
      //刷新token锁
      refreshLock: false,
      //刷新token的时间
      refreshTime: ""
    };
  },
  created() {
    //实时检测刷新token
    this.refreshToken();
    //缓存常用字典
    this.loadDic();
    if (this.userInfo.fix_onln_open_flag === "1" && this.userInfo.fix_flag === "1"){
      this.ybSignIn();//签到
      if (this.website.isYB) {
        this.initYB();
      }
    }
  },
  mounted() {
    this.init();
  },

  methods: {
    //初始化医保
    initYB() {
      setTimeout(() => {
        yb.callInit();
      }, 5000);
    },
    //签到
    ybSignIn(){
      let succFun = function (data) {

      };
      let failFun = function (data) {

      };
      this.$eframe.submitTargetForm({
        url: '/userloginmgmt/signIn.action',
        form: {},
        actionFunName: "signIn",
        succFun: succFun,
        failFun: failFun,
      });
    },
    // 屏幕检测
    init() {
      this.$store.commit("SET_SCREEN", admin.getScreen());
      window.onresize = () => {
        setTimeout(() => {
          this.$store.commit("SET_SCREEN", admin.getScreen());
        }, 0);
      };
    },

    // 10分钟检测一次token
    refreshToken() {
      this.refreshTime = setInterval(() => {
        const token = getStore({
          name: "token",
          debug: true
        }) || {};
        const date = calcDate(token.datetime, new Date().getTime());
        if (validatenull(date)) return;
        if (date.seconds >= this.website.tokenTime && !this.refreshLock) {
          this.refreshLock = true;
          this.$store.dispatch("RefeshToken")
              .then(() => {
                this.refreshLock = false;
              })
              .catch(() => {
                this.refreshLock = false;
              });
        }
      },  this.website.refreshTime); //毫秒
    },
    //缓存常用字典
    loadDic() {
      // eframe.loadDicItem({dicId: "DIC_FLAG"});//是否
      // eframe.loadDicItem({dicId: "DIC_GEND"});//性别
      // eframe.loadDicItem({dicId: "DIC_PSN_TYPE"});//人员类别
      // eframe.loadDicItem({dicId: "DIC_INSUTYPE"});//险种类型
      // eframe.loadDicItem({dicId: "DIC_INSU_ADMDVS_SZ"});//参保所属医保区划
      // eframe.loadDicItem({dicId: "DIC_FEE_TYPE"});//就诊类别
      // eframe.loadDicItem({dicId: "DIC_SETL_TYPE"});//收费类别
      // eframe.loadDicItem({dicId: "DIC_MED_TYPE"});//医疗类别
      // //对账
      // eframe.loadDicItem({dicId: "DIC_STMT_RSLT"});//对账结果
      // eframe.loadDicItem({dicId: "DIC_STMT_CLRTYPE_BD"});//本地清算类别
      // servpara.getConfigArea();
    }
  }
};
</script>