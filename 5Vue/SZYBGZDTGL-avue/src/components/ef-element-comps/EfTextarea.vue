<template>
    <el-form-item :class="placeholderClass" :label-width="compLabelWidth" :label="label" :prop="name" :rules="rules" inline-message="true">
      <span slot="label">{{ label }}</span>
      <el-input
          :ref="name"
          :style="appStyle"
                type="textarea"
                v-model="compValue"
                :placeholder="placeholder"
                @input="input"
                @change="change"
                :disabled="compReadonly"
                :maxlength="compMaxLength"
                :minlength="compMinLength"
                :rows="compRows"
                :autosize="autosize"
          @focus="focus" @blur="blur"
                show-word-limit
        />
    </el-form-item>
</template>

<script>
    import {BaseFieldTag} from './BaseFieldTag';

    export default {
        name: "EfTextarea",
        mixins: [BaseFieldTag],         //继承基础BaseFieldTag
        props:{
            placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
                type: String,
                default: "",
            },
            maxlength:{
                require:false,
                type: [String,Number],
                default:null
            },
            minlength:{
                require:false,
                type: [String,Number],
                default:null
            },
            rows:{
                require:false,
                type: [String,Number],
                default:2
            },
            autosize:{
              require:false,
              type:Boolean,
              default:false
            },
        },
        data(){
            return {
                componentType:'EfTextarea',
                compMaxLength:null,
                compMinLength:null,
                compRows:2,
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
        },
        appStyle(){
          return this.$store.state.common.appStyle
        }
      },
        created() {

            if (typeof (this.maxlength) === "string") {
                this.compMaxLength = Number(this.maxlength) ;
            } else {
                this.compMaxLength = this.maxlength;
            }
            if (typeof (this.minlength) === "string") {
                this.compMinLength = Number(this.minlength) ;
            } else {
                this.compMinLength = this.minlength;
            }
            if (typeof (this.rows) === "string") {
                this.compRows = Number(this.rows) ;
            } else {
                this.compRows = this.rows;
            }
        },
        methods:{
            input(val){
                this.$emit('compChange', val);
                this.$emit('onInput', val);
            },
          setFocus(){
            this.$refs[this.name].focus()
            this.$refs[this.name].select()
          },
          setBlur(){
            this.$refs[this.name].blur()
          },
          focus(val) {
            this.$emit('compFocus', val);
          },
          blur(val) {
            this.$emit('compBlur', val);
          },
        }
    }
</script>

<style scoped>

</style>