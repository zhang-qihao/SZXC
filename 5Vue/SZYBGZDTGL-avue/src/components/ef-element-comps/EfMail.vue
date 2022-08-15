<template>
  <el-form-item :class="placeholderClass" :label-width="compLabelWidth" :label="label" :prop="name" :rules="rules" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-input
        :size="formStyle[0].size"
        v-model="compValue"
        :placeholder="placeholder"
        @input="input"
        @change="change"
        :disabled="compReadonly"/>
  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';

export default {
  name: "EfMail",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
      type: String,
      default: "格式:mail@mail.com",
    },
  },
  data() {
    return {
      componentType: 'EfMail',
      //设置默认的邮箱校验方式
      compPattern: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/,
    };
  },
  watch: {
    value: {//深度监听，可监听到对象、数组的变化
      immediate: true,
      handler(val) {
        this.compValue = val;
      },
      // deep: true //true 深度监听
    }
  },
  computed: {
    formStyle() {
      return this.$store.state.common.formStyle
    },
    placeholderClass() {
      return this.$store.state.common.formStyle[0].size
    }
  },
  methods: {
    input(val) {
      this.$emit('compChange', val);
      this.$emit('onInput', val);
    }
  }
}
</script>

<style scoped>

</style>