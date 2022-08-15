<template>
<div >777

  <el-button @click="test8">关闭close</el-button>
  <div class="content">
    <div class="block">
      <div class="title">通用API</div>
      <div class="subTitle">通用API</div>

      <el-button @click="test6">测试抽出框slideReveal(self)</el-button>

      <el-button @click="test7">测试抽出框slideReveal(dialog)</el-button>

      <el-button @click="test8">关闭close</el-button>

    </div>
  </div>



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
      // eframe.showWaiting
      test6() {
        let backFun = function (back) {
          alert("5.vue:::" + back);
        };
        this.slideReveal({
          title: "我的标题22222222222",
          view: "/example/subdialog",
          width: 400,
          params: {text: "这是参数222"},
          callBackFun: backFun
        });
      },

      // eframe.showWaiting
      test7() {
        let backFun = function (back) {
          alert("5.vue:::" + back);
        };
        this.slideReveal({
          title: "我的标题22222222222",
          view: "/example/button",
          width: 400,
          params: {text: "这是参数222"},
          callBackFun: backFun
        });
      },

      //关闭close
      test8() {
        this.close({a: 111, b: 222});
      },
    }
  }
</script>

<style scoped>

</style>
