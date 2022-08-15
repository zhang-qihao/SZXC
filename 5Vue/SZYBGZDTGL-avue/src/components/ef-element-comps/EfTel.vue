<template>
  <el-form-item :class="placeholderClass" :label-width="compLabelWidth" :label="label" :prop="name" :rules="rules" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-input
        :size="formStyle[0].size"
            v-model="compValue"
            :placeholder="placeholder"
            @input="input"
            @change="change"
            :disabled="compReadonly" />
  </el-form-item>
</template>

<script>
  import {BaseFieldTag} from './BaseFieldTag';

  export default {
    name: "EfTel",
    mixins: [BaseFieldTag],         //继承基础BaseFieldTag
    props:{
      placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
        type: String,
        default: "请录入手机号",
      },
    },
    data(){
      return {
        componentType:'EfTel',
        //设置默认手机
        compPattern : /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
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
	watch:{
		value(){
			this.compValue = this.value;
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