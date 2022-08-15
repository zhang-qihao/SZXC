<template>
  <div>
    <el-form-item :class="placeholderClass" :label="label" :label-width="compLabelWidth" :prop="name" :rules="rules" inline-message="true">
      <span slot="label">{{ label }}</span>
      <el-date-picker
          :size="formStyle[0].size"
          :format="compFormat"
          :value-format="compFormat"
          v-model="compValue"
          align="right"
          :type="compType"
          :disabled="compReadonly"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :picker-options="compOptions"
          @change="change">
      </el-date-picker>
    </el-form-item>
  </div>
</template>
<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfDatetimeRange",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    format: { // 时间类型
      type: String,
      default: "yyyy-MM-dd HH:mm:ss",
    },
    options: { // 扩展选项
      type: Object,
      default: null,
    },
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
  data() {
    return {
      compOptions: null,
      compFormat: "yyyy-MM-dd HH:mm:ss", //录入框显示样式
    }
  },
  created() {
    this.getFormat();
    if(this.options!=null){
      this.compOptions=this.options;
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
  methods: {
    /**
     * 日期选择框change事件，将所选值和所选值所在的对象回传给父组件
     * @val {String} 当前选中值
     **/
    change(val) {
      console.log("时间:" + typeof (val) + " " + val);
      this.$emit('compChange', val);    // 更新 model
      this.$emit('onChange', val);
    },
    getFormat() {
      if ("YYY-MM-DD HH:MM" === this.format.toUpperCase()) {
        this.compFormat = 'yyyy-MM-dd hh:mm';
      } else if ("YYYY/MM/DD HH:MM:SS" === (this.format.toUpperCase())) {
        this.compFormat = 'yyyy/MM/dd hh:mm:ss';
      } else if ("YYYY/MM/DD HH:MM" === (this.format.toUpperCase())) {
        this.compFormat = 'yyyy/MM/dd hh:mm';
      } else if ("YYYYMMDD HH:MM:SS" === (this.format.toUpperCase())) {
        this.compFormat = 'yyyyMMdd hh:mm:ss';
      } else if ("YYYYMMDD HH:MM:SS" === (this.format.toUpperCase())) {
        this.compFormat = 'yyyyMMdd hh:mm:ss';
      } else if ("YYYYMMDD HH:MM" === (this.format.toUpperCase())) {
        this.compFormat = 'yyyyMMdd hh:mm';
      } else {
        //默认
        this.compFormat = 'yyyy-MM-dd hh:mm:ss';
      }
      this.compType = 'datetimerange';
    }
  }
}
</script>

<style scoped>

</style>