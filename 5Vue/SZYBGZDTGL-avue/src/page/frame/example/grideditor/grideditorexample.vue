<template>
<div>
  <el-button v-prevent-re-click @click="test15">下载</el-button>
  <a href="http://172.16.10.15:8401/ftproot/2019/09/1.xls" download="test">
    <el-button v-prevent-re-click class = "button" size="medium" @click="saveInfo"  round>保存配信息至本地</el-button>
  </a>
</div>
</template>

<script>
export default {
  name: "grideditorexample",
  methods:{
    test15() {
      let url="http://172.16.10.15:8401/ftproot/2019/09/1.xls";
      window.location.href=url;
    },
  }
}
</script>

<style scoped>

</style>