<template>
<div>
  <basic-container>
    <div class="title">树</div>
    <div class="subTitle">测试树</div>
    <ef-tree  ref="tree1" queryNo="Frame_Menu_001"   queryWindow="1"   style="height:300px;"/>
    <div style="margin-bottom: 20px" >
      <el-button type="primary" @click="test1">刷新查询</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </div>
  </basic-container>

</div>
</template>

<script type="text/javascript">

    import  {BaseCtrl}  from '/src/util/eframe'
    export default {
        mixins: [BaseCtrl],         //继承基础BaseCtrl
        name: "",
        data() {
            return {}
        },
        components: {},
        created() {
           this.$nextTick(()=>{
              this.$refs['tree1'].setInitFun((tree)=>{

                 tree.onSelect = function (dataItem,tree) {
                    let menuId = dataItem.get("MENUID");
                     alert(menuId);
                 };
                 tree.doRefresh({ parameters:{ programNo:"SYSP"}});

              });
           });
        },
       mounted()  {
            //初始化操作或写在此地
        },
        methods: {
           test1() {

              let tree = this.$refs['tree1'].getWidget();


           }

        }
    }
</script>

<style scoped>

</style>
