<template>
<div>
  <basic-container>
    <div class="title">可编辑表</div>
    <div class="subTitle">测试可编辑表222</div>
    <ef-grid-editor  ref="grid1" queryNo="Frame_Query_002"  queryWindow="1"  style="height:300px;" catch="false"/>
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

 //  import $ from 'jquery';
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
              this.$refs['grid1'].setInitFun((queryGrid)=>{
                 queryGrid.selectAll=function(e,grid){
                    console.log("selectAll");
                 };
                 queryGrid.selectRow=function(e,dataItem,grid,row){
                    console.log("selectRole"+dataItem.QUERYNO);
                 };
                 //扩展Grid函数
                 queryGrid.onSelect = function (dataItem,grid,row){
                    let queryNo=dataItem.get("QUERYNO");
                    alert(queryNo);
                 };
                 queryGrid.doRefresh({  });
              });
           });
        },
       mounted()  {
          //初始化操作或写在此地
        },
        methods: {
           test1() {
              let queryGrid = this.$refs['grid1'].getWidget();
              queryGrid.doRefresh({  });

           }
        }
    }
</script>

<style scoped>

</style>
