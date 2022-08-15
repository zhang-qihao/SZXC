<template>
  <el-form-item :class="placeholderClass" :label-width="compLabelWidth" :label="label" :prop="name" :rules="rules" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-input
        :size="formStyle[0].size"
            v-model="compValue"
            :placeholder="placeholder"
            @input="input"
            @change="change"
            :disabled="compReadonly"
            show-password
    />
  </el-form-item>
</template>

<script>
  import {BaseFieldTag} from './BaseFieldTag';

  export default {
    name: "EfPassword",
    mixins: [BaseFieldTag],         //继承基础BaseFieldTag
    props:{
      placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
        type: String,
        default: "请录入密码",
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
    data(){
      return {
        componentType:'EfPassword',
        //设置默认密码校验  1.密码必须由字母、数字或特殊字符组成，区分大小写 2.密码长度为6-18位
        // compPattern : /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$/,
        compPattern : /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$|(?=.*[~!@#$%^&*])[\da-zA-Z~!@#$%^&*]{6,18}$/,
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
    methods:{
      input(val){
        this.$emit('compChange', val);
        this.$emit('onInput', val);
      }
    }
  }
</script>

<style scoped>

</style>