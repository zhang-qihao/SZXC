<template>
  <div >
    <el-form-item :class="placeholderClass" :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth"  inline-message="true">
      <span slot="label">{{ label }}</span>
      <el-date-picker
          :size="formStyle[0].size"
          :format="compFormat"
          :value-format="compFormat"
          v-model="compValue"
          :type="compType"
          :disabled="compReadonly"
          align="right"
          :placeholder="compPlaceholder"
          :picker-options="compOptions"
          @change="change">
      </el-date-picker>
    </el-form-item>
  </div>
</template>
<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfDatetime",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    format: { // 日期格式
      type: String,
      default: "yyyy-MM-dd HH:mm:ss",
    },
    options: { // pickerOption。
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
      pickerOptions: {},
      compType: "datetime",
      compFormat: "yyyy-MM-dd HH:mm:ss", //录入框显示样式
      compValueFormat: "yyyy-MM-dd HH:mm:ss", //输出样式 （是否需要区分）
      compOptions:null,  // 扩展选项
      compPlaceholder: null,
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
    //picker-options
    if(this.options!=null){
      this.compOptions=this.options;
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
      this.compType = 'datetime';
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