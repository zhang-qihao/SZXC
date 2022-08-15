<template>
  <div>
    <el-form-item :class="placeholderClass" :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
      <span slot="label">{{ label }}</span>
      <el-date-picker
          :size="formStyle[0].size"
          :format="compFormat"
          :value-format="compFormat"
          v-model="compValue"
          :type="compType"
          align="right"
          :disabled="compReadonly"
          :picker-options="compOptions"
          :placeholder="compPlaceholder"
          @change="change">
      </el-date-picker>
    </el-form-item>
  </div>
</template>
<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfDate",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    format: { // 日期格式
      type: String,
      default: "yyyy-MM-dd",
    },
    options: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      compOptions: null,
      compType: "date",
      compFormat: "yyyy-MM-dd", //录入框显示样式
      compPlaceholder: null,
    }
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
    this.getFormat();
    if (this.options != null) {
      this.compOptions = this.options;
    }
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
    getFormat() {
      if ("YYYY/MM/DD" === this.format.toUpperCase()) {
        this.compType = 'date';
        this.compFormat = 'yyyy/MM/dd';
      } else if ("YYYYMMDD" === (this.format.toUpperCase())) {
        this.compType = 'date';
        this.compFormat = 'yyyyMMdd';
      } else if ("YYYY-MM" === (this.format.toUpperCase())) {
        this.compType = 'month';
        this.compFormat = 'yyyy-MM';
      } else if ("YYYY/MM" === (this.format.toUpperCase())) {
        this.compType = 'month';
        this.compFormat = 'yyyy/MM';
      } else if ("YYYYMM" === (this.format.toUpperCase())) {
        this.compType = 'month';
        this.compFormat = 'yyyyMM';
      } else if ("YYYY" === (this.format.toUpperCase())) {
        this.compType = 'year';
        this.compFormat = 'yyyy';
      } else {
        //默认
        this.compType = 'date';
        this.compFormat = 'yyyy-MM-dd';
      }


      if (this.placeholder == null || this.placeholder=="请选择") {
        this.compPlaceholder = "格式：" + this.compFormat.toLowerCase();
      } else {
        this.compPlaceholder = this.placeholder;
      }
    }
  }
}
</script>

<style scoped>

</style>