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
    name: "EfPageGrid",
    mixins: [BaseQueryTag],         //继承基础BaseFieldTag
    props:{

    },
    data(){
      return {
      };
    },
    mounted() {

        // let url="/frame/component/pagegrid/pagegrid.action";
        // let form={
        //     widgetUid:this.id,
        //     queryNo:this.compQueryNo,  //查询号
        //     queryWindow:this.compQueryWindow, //窗口号
        //     showType:this.compShowType, // 0不显示check框   1 显示check框， 默认为：1
        //     pageSize:this.compPageSize, //每页显示条数
        // };
        // let actionFunName="config";
        // let that = this;
        // let callbackFun = function (data) {
        //     if(data.options) {
        //         var vueConstData = constData;
        //         console.log(data.options);
        //         let options = eval("("+data.options+")");
        //         console.log(options);
        //         let element = $("#" + that.id).kendoGrid(options);
        //         that.widget = element.data('kendoGrid');
        //     }
        // };
        // eframe.submitTargetForm(url,form,actionFunName,callbackFun);

        let form={
            widgetUid:this.id,
            queryNo:this.compQueryNo,  //查询号
            connectId:this.compConnectId,//数据库链接
            queryWindow:this.compQueryWindow, //窗口号
            showType:this.compShowType, // 0不显示check框   1 显示check框， 默认为：1
            pageSize:this.compPageSize, //每页显示条数
            catchFlag:this.compCatch, //是否使用缓存，默认true
            editable:this.compEditable, //是否可编辑，默认false
        };
        store.dispatch('GetPageGrid',form).then((data) => {
            if(data&&data.options){
                var vueConstData = constData;
                let options = eval("("+data.options+")");
                //console.log(options);
                let element = $("#" + this.id).kendoGrid(options);
                this.widget = element.data('kendoGrid');
            }else{
                this.$eframe.alertError("EfPageGrid错误，未取到查询配置！");
            }
        }).catch((msg) => {
            this.$eframe.alertError(msg);
        });

    },
  }
</script>

<style scoped>

</style>