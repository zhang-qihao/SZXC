<template>
  <el-form-item :class="placeholderClass" :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
    <span slot="label">{{ label }}</span>

    <div v-if="  compDecimals ==null"  >
      <el-input-number v-model="compNum"
                       :ref="name"
                       :size="formStyle[0].size"
                       :placeholder="placeholder"
                       controls-position="right"
                       @change="change"
                       @blur="blur"
                       :disabled="compReadonly"
                       :min="compMin"
                       :max="compMax">
      </el-input-number>
    </div>

    <div v-if="  compDecimals !=null"  >
      <el-input-number v-model="compNum"
                       :ref="name"
                       :size="formStyle[0].size"
                       :placeholder="placeholder"
                       controls-position="right"
                       @change="change"
                       @blur="blur"
                       :precision="compDecimals"
                       :disabled="compReadonly"
                       :min="compMin"
                       :max="compMax">
      </el-input-number>
    </div>

  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfNumber",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    value:{
      type: [String,Number],
    },
    max: {
      require: false,
      type: [String, Number],
      default: null
    },
    min: {
      require: false,
      type: [String, Number],
      default: null
    },
    decimals: {
      require: false,
      type: [String, Number],
      default: null
    },
  },
  watch: {
    value: {//深度监听，可监听到对象、数组的变化
      immediate: true,
      handler(val) {
        this.compNum=val;
      },
      // deep: true //true 深度监听
    }
  },
  data() {
    return {
      compNum:0,
      compMax: Infinity,
      compMin: -Infinity,
      compDecimals:2,
    };
  },
  computed:{
    formStyle(){
      return this.$store.state.common.formStyle
    },
    placeholderClass(){
      return this.$store.state.common.formStyle[0].size
    }
  },
  created() {
    if(typeof (this.value)==='number'){
      this.compNum=this.value;
    }else if(this.$eframe.isNotNull(this.value)){
      this.compNum=Number(this.value);
    }
    if(typeof (this.max)==='number'){
      this.compMax=this.max;
    }else {
      if(this.$eframe.isNull(this.max)){
        this.compMax=Infinity;
      }else {
        this.compMax=Number(this.max);
      }
    }

    if(typeof (this.min)==='number'){
      this.compMin=this.min;
    }else {
      if(this.$eframe.isNull(this.min)){
        this.compMin=-Infinity;
      }else {
        this.compMin=Number(this.min);
      }
    }

    if(typeof (this.decimals)==='number'){
      this.compDecimals=this.decimals;
    }else {
      if(this.$eframe.isNull(this.decimals)){
        this.compDecimals=2;
      }else {
        if(this.decimals=="null"){
          this.compDecimals=null;
        }else {
          this.compDecimals = parseInt(this.decimals);
        }
      }
    }

  },
  methods: {
    /**
     * 日期选择框change事件，将所选值和所选值所在的对象回传给父组件
     * @val {String} 当前选中值
     **/
    change(currentValue, oldValue) {
      this.$emit('compChange', currentValue);    // 更新 model
      this.$emit('onChange', currentValue);
    },
    setFocus(){
      this.$refs[this.name].focus();
      this.$refs[this.name].select()
    },
    blur(val) {
      this.$emit('compBlur', val);
    },
    setBlur() {
      this.$refs[this.name].blur()
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-input-number .el-input__inner{
  text-align: right;
}
</style>