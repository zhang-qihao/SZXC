<template>
    <div :id="id"   ></div>
</template>

<script>
  import {BaseQueryTag} from './BaseQueryTag';
  import eframe from "../../util/eframe";
  import constData from '../../const/index';
  import store from "../../store";
  //import $ from "jquery";

  export default {
    name: "EfTree",
    mixins: [BaseQueryTag],         //继承基础BaseFieldTag
    props:{

    },
    data(){
      return {
      };
    },
    mounted() {

        let form={
            widgetUid:this.id,
            queryNo:this.compQueryNo,  //查询号
            connectId:this.compConnectId,//数据库链接
            queryWindow:this.compQueryWindow, //窗口号
            showType:this.compShowType, // 0不显示check框   1 显示check框， 默认为：1
            catchFlag:this.compCatch, //是否使用缓存，默认true
        };
        store.dispatch('GetTree',form).then((data) => {
            if(data&&data.options){
                var vueConstData = constData;
                let options = eval("("+data.options+")");
                //console.log(options);
                let element = $("#" + this.id).kendoTreeView(options);
                this.widget = element.data('kendoTreeView');
            }else{
                this.$eframe.alertError("EfTree错误，未取到查询配置！");
            }
        }).catch((msg) => {
            this.$eframe.alertError(msg);
        });

    },
  }
</script>

<style scoped>

</style>