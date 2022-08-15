<template>
  <el-form-item :label="label" :prop="name" :label-width="compLabelWidth" :rules="rules" inline-message="true">
    <span :style="appStyle">{{compValue}}</span>
  </el-form-item>
</template>

<script>
import {BaseFieldTag} from "./BaseFieldTag";
import store from "../../store";

export default {
  name: "EfShowValue",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    dictionaryNo: { // 数据字典编号。
      type: String,
      default: null,
    },
    list: {  //分组
      type: [String, Array],
      default: null,
    },
    listKey: {  //列中内容字段
      type: String,
      default: 'code',
    },
    listValue: {  //列中标题字段
      type: String,
      default: "name",
    },
    queryNo: {  //列中标题字段
      type: String,
      default: null,
    },
    multiple: { // 是否多选，默认false，非必传。
      type: [String, Boolean],
      default: false,
    },
  },

  data() {
    return {};
  },
  computed:{
    appStyle(){
      return this.$store.state.common.appStyle
    }
  },
  watch: {
    value() {
      this.getOptions();
    }
  },
  created() {
    this.getOptions();
  },
  methods: {
    /**
     * 获取下拉框的选择项数据
     */
    getOptions() {
      if (this.dictionaryNo) {
        //this.$eframe.loadDicItem( {dicId:this.dictionaryNo},(data)=>{ this.optionLists = data;  } );
        let form = {
          dicId: this.dictionaryNo,
          dicListGroup: '',  //字典分组
          language: '', //语种
          catchFlag: this.compCatch, //是否使用缓存，默认true
        };
        store.dispatch('LoadDicItem', form)
            .then((data) => {
              this.optionLists = data;
              this.compValue=this.findValue(this.optionLists)
            })
            .catch((msg) => {
              this.$eframe.alertError(msg);
            });
        return;
      }
      if (this.list) {
        if (this.$eframe.isArray(this.list)) { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = this.list;
          this.compValue=this.findValue(this.optionLists)
        } else if (typeof (this.list) === "string") { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = JSON.parse(this.list);
          this.compValue=this.findValue(this.optionLists)
        }
        return;
      }
    },
    findValue(list) {
      for (let k in list){
        // if (list[k].[this.listKey]===this.value){
        //   return list[k].[this.listValue];
        // }
      }
      return this.value;
    }
  },
}
</script>

<style scoped>

</style>