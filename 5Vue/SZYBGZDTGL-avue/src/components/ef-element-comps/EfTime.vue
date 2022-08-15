<template>
  <div>
    <el-form-item :class="placeholderClass" :label="label" :prop="name" :label-width="compLabelWidth" :rules="rules" inline-message="true">
      <span slot="label">{{ label }}</span>
      <el-time-picker
          :size="formStyle[0].size"
          :value-format="compFormat"
          v-model="compValue"
          align="right"
          :disabled="compReadonly"
          :placeholder="compPlaceholder"
          :picker-options="compOptions"
          @change="change">
      </el-time-picker>
    </el-form-item>
  </div>
</template>
<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfTime",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    format: { // 时间类型 HH:mm:ss
      type: String,
      default: null,
    },
    options: { // 扩展选项 @todo
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      compOptions: null,
      compFormat: "HH:mm:ss", //录入框显示样式
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
      this.compFormat = 'HH:mm:ss';

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