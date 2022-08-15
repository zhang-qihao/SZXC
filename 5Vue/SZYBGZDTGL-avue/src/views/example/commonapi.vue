<template>
<div style="width: 100%">
  <basic-container>
    <div class="title">通用API</div>
    <div class="subTitle">通用API</div>

    <el-button @click="test0">测试submitTargetForm</el-button>

    <el-button @click="api">测试api</el-button>

    <el-button @click="test2">测试alertError</el-button>

    <el-button @click="test4">测试alertMessage</el-button>

    <el-button @click="test3">测试confirm</el-button>

    <el-button @click="test5">测试showToast</el-button>

    <el-button @click="test6">测试showWaiting/hiddenWaiting</el-button>

    <el-button @click="test7">测试抽出框slideReveal</el-button>

    <el-button @click="test8">关闭close</el-button>

    <el-button @click="test9">测试Window/Dialog</el-button>
    <el-button @click="test10">测试addTag10</el-button>
    <el-button @click="test11">测试addTag11</el-button>

    <el-button @click="test13">测试openMenu1</el-button>

    <el-button @click="test14">测试openMenu2</el-button>
    <el-button @click="test15">下载</el-button>
    <a href="http://172.16.10.15:8401/ftproot/2019/09/1.xls" download="test">
      <el-button class = "button" size="medium" @click="saveInfo"  round>保存配信息至本地</el-button>
    </a>



  </basic-container>

</div>
</template>

<script type="text/javascript">

import {BaseCtrl} from '/src/util/eframe'

export default {
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  name: "",
  data() {
    return {}
  },
  components: {},
  created() {

  },
  methods: {

    //eframe.submitTargetForm
    test0() {
      let url = '/menu.action';
      let form = {};
      let actionFunName = "loadMenu";
      let callbackFun = function (data) {
        alert("获取菜单成功！");
      };
      let errorFun = function (data) {
        alert("errorFun");
      };
      this.$eframe.submitTargetForm({url:url, form:form, actionFunName:actionFunName, succFun:callbackFun,failFun:errorFun});

    },
    api() {
      let url ='/frame/systemmanagement/usermanagement/user/usermanagement.action';
      let form = {};
      let actionFunName = "loadUser";
      let callbackFun = function (data) {
        alert("获取菜单成功！");
      };
      let errorFun = function (data) {
        alert("errorFun");
      };
      this.$eframe.submitTargetForm({url:url, form:form, actionFunName:actionFunName, succFun:callbackFun,failFun:errorFun});
    },
    //eframe.alertError
    test2() {
      this.alertError("test2");
    },

    // eframe.confirm
    test3() {
      let that = this;
      let okFun = function () {
        that.$alert("okFun")
      };
      let cancelFun = function () {
        that.$alert("cancelFun")
      };
      this.confirm("", "你点击了确认框", okFun, () => {
        this.$alert("cancelFun1111111");
      });
    },

    // eframe.alertMessage
    test4() {
      this.alertMessage("test4");
    },


    // eframe.showToast
    test5() {
      this.showToast("test5");
    },

    // eframe.showWaiting
    test6() {
      this.showWaiting();
      this.hiddenWaiting();
    },


    //  this.slideReveal
    test7() {
      var that = this;
      var backFun = function (back) {

      };
      this.slideReveal({
        title: "我的标题111",
        view: "views/example/subdialog",
        width: 800,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },

    //关闭close
    test8() {
      this.close();
    },

    //  this.openDialog
    test9() {
      var that = this;
      var backFun = function (back) {

      };
      this.openDialog({
        title: "我的标题111",
        view: "/example/subdialog",
        width: 800,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },
    test10() {
      this.openMainTag({
        id: "11",
        title: "人员查询",
        view: "/page/frame/systemmanagement/usermanagement/user/usermanagement",
        reloadFlag: true,
        iframeFlag: false,
        params: {
          params: {
            userId: "MD5"
          }
        }
      });

    },
    test11() {
      this.openMainTag({
        id: "11",
        title: "人员查询",
        view: "/page/frame/systemmanagement/usermanagement/user/usermanagement",
        reloadFlag: true,
        iframeFlag: false,
        params: {
          params: {
            userId: "U001"
          }
        }
      });

    },
    test13() {
      this.openMenu({
        menuNo: "MU1005",
        params: null,
      });

    },
    test14() {
      this.openMenu({
        menuNo: "MU1006",
        params: null,
      });
    },
    test15() {
      let url="http://172.16.10.15:8401/ftproot/2019/09/1.xls";
      window.open(url);
    },
    saveInfo(){

    }
  }
}
</script>

<style scoped>

</style>
