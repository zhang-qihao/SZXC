<template>
  <el-form-item :label="compLabel" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-switch
        v-model="compValue"
        active-color="#13ce66"
        inactive-color="#ff4949"
        @change="change"
        :active-text="rightLabel"
        :inactive-text="leftLabel"
        :active-value="compActiveValue"
        :inactive-value="compInactiveValue"
		:disabled="compReadonly">
    </el-switch>
  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';
export default {
  name: "EfSwitch",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    fieldValue: { // 设置选中值（未选中值），默认为'true'。目前支持：true/false ; 1/0 ; y/n
      type: [String,Number,Boolean],
      default: true,
    },
    rightLabel: { // 开启的lable
      type: String,
      default: null,
    },
    leftLabel: { // 未启的lable
      type: String,
      default: null,
    },
  },
  watch: {
    value: {//深度监听，可监听到对象、数组的变化
      immediate: true,
      handler(val) {
        this.compValue=val;
        this.setValueType(this.compValue);
      },
      // deep: true //true 深度监听
    }
  },
  data() {
    return {
      compLabel:" ",
      compActiveValue: true,
      compInactiveValue:false,
    }
  },
  computed:{
    formStyle(){
      return this.$store.state.common.formStyle
    },
  },
  created() {
    if(this.$eframe.isNull(this.label)){
      this.compLabel=" ";
    }else {
      this.compLabel=this.label;
    }
    this.setValueType(this.fieldValue);

  },
  methods: {
    /**
     * 日期选择框change事件，将所选值和所选值所在的对象回传给父组件
     * @val {String} 当前选中值
     **/
    change(val) {
      this.$emit('compChange', val);    // 更新 model
      this.$emit('onChange', val);
    },
    setValueType(val){
      if(typeof (val)=="number"){
        this.compActiveValue = 1;
        this.compInactiveValue = 0;
      }else if(typeof (val)=="boolean"){
        this.compActiveValue = true;
        this.compInactiveValue = false;
      }else {
        if("1"===(val )){
          this.compActiveValue = "1";
          this.compInactiveValue = "0";
        }else if("y"===(val)){
          this.compActiveValue = "y";
          this.compInactiveValue= "n";
        }else if("true"===(val)){
          this.compActiveValue = "true";
          this.compInactiveValue = "false";
        } else{
          this.compActiveValue = "1";
          this.compInactiveValue = "0";
        }
      }
    },
  }
}
</script>

<style scoped>

</style>