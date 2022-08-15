<template>
  <el-form-item :class="placeholderClass" :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
    <span slot="label">{{ label }}</span>

    <div v-if="  compDecimals ==null"  >
      <el-input-number
              :size="formStyle[0].size"
              v-model="compValue"
              :placeholder="placeholder"
              @input="input"
              @change="change"
              :disabled="compReadonly"
              :max="compMax"
              :min="compMin"
              :ref="name"
              :controls="false"
      >
      </el-input-number>
    </div>

    <div v-if="  compDecimals !=null"  >
      <el-input-number
              :size="formStyle[0].size"
              v-model="compValue"
              :placeholder="placeholder"
              @input="input"
              @change="change"
              :disabled="compReadonly"
              :precision="compDecimals"
              :max="compMax"
              :min="compMin"
              :ref="name"
              :controls="false"
      >
      </el-input-number>
    </div>





  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';
export default {
  name: "EfMoney",
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
      default: 2
    },
  },
  data() {
    return {
      compNum:0,
      compMax: Infinity,
      compMin: -Infinity,
      compDecimals:2,
    };
  },
  watch: {
    value: {//深度监听，可监听到对象、数组的变化
      immediate: true,
      handler(val) {
        this.compValue=val;
      },
      // deep: true //true 深度监听
    }
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
    }else {
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
    input(val) {
        this.$emit('compChange', val);
        this.$emit('onInput', val);
    },
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
    setBlur(){
      this.$refs[this.name].blur()
    },
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-input-number.is-without-controls .el-input__inner{
  text-align: right;
}
/*input::-webkit-outer-spin-button,*/
/*input::-webkit-inner-spin-button {*/
/*  margin-left: 5px;*/
/*}*/
input[type=number]{
  -moz-appearance: textfield;
}

</style>