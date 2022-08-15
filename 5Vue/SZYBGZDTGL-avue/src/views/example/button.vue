<template>
  <div>
    <basic-container>
      <div class="title">按钮</div>
      <div class="subTitle">普通按钮</div>
      <div style="margin-bottom: 20px"  id="aaa">
        <el-button type="primary" @click="aaa">主要按钮</el-button>
        <el-button type="success">成功按钮</el-button>
        <el-button type="warning">警告按钮</el-button>
        <el-button type="danger">危险按钮</el-button>
      </div>
      <div class="subTitle">边框按钮</div>
      <div style="margin-bottom: 20px">
        <el-button type="primary" plain>主要按钮</el-button>
        <el-button type="success" plain>成功按钮</el-button>
        <el-button type="warning" plain>警告按钮</el-button>
        <el-button type="danger" plain>危险按钮</el-button>
      </div>
      <div class="subTitle">带图标按钮</div>
      <div style="margin-bottom: 20px">
        <el-button type="primary" icon="el-icon-share" plain
        >主要按钮</el-button
        >
        <el-button type="success" plain
        >成功按钮<i class="el-icon-upload el-icon--right"></i
        ></el-button>
        <el-button type="warning" icon="el-icon-share">警告按钮</el-button>
        <el-button type="danger"
        >危险按钮<i class="el-icon-upload el-icon--right"></i
        ></el-button>
      </div>
      <div class="subTitle">图标按钮</div>
      <div style="margin-bottom: 20px">
        <el-button
            class="iconBtn"
            type="primary"
            icon="el-icon-edit"
        ></el-button>
        <el-button
            class="iconBtn"
            type="success"
            icon="el-icon-search"
        ></el-button>
        <el-button
            class="iconBtn"
            type="warning"
            icon="el-icon-share"
        ></el-button>
        <el-button
            class="iconBtn"
            type="danger"
            icon="el-icon-delete"
        ></el-button>
      </div>
      <div class="subTitle">文字按钮</div>
      <div>
        <el-button class="textBtn primary" type="text">文字按钮</el-button>
        <el-button class="textBtn success" type="text">文字按钮</el-button>
        <el-button class="textBtn warning" type="text">文字按钮</el-button>
        <el-button class="textBtn danger" type="text">文字按钮</el-button>
      </div>

    </basic-container>

  </div>
</template>

<script type="text/javascript">
  //import $ from 'jquery';
  import  {BaseCtrl}  from '/src/util/eframe'
  export default {
    mixins: [BaseCtrl],         //继承基础BaseCtrl
    name: "components",
    data() {
      return {
        form:{}
      };
    },
    components: {},
    created() {},
    methods: {
      aaa() {
        alert("11111");
        alert($("#aaa"));

      }
    },
  };

</script>

<style lang="scss">

</style>
