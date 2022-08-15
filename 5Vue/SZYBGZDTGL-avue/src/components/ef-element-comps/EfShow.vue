
/*****************************
* 组件：ef_show   显示组件
******************************/
<template>
  <el-form-item class="show-type" :class="showType" :label="label" :prop="name" :label-width="compLabelWidth" :rules="rules" inline-message="true">
    <div v-if="type == 'text' && isNotNull(compValue)" class="text-box" style="user-select: text">{{compValue}}</div>
    <div v-if="type == 'text' && isNull(compValue)" class="text-null-box" style="user-select: text">{{compValue}}</div>
<!--    <el-input :size="formStyle[0].size" v-model="compValue" :placeholder="placeholder" @input="input"-->
<!--              @changerecord="changerecord" :disabled="compReadonly" :maxlength="compMaxLength" :minlength="compMinLength">-->
<!--      <div slot="append" v-if="buttonFlag">-->
<!--        <slot></slot>-->
<!--      </div>-->
<!--    </el-input>-->
    <el-input-number
        v-if="type == 'money'"
        :controls="false"
        v-model="compValue"
        :size="formStyle[0].size"
        :precision="compDecimals"
        :disabled="true"
    >

    </el-input-number>
  </el-form-item>
</template>

<script>
import {
  BaseFieldTag
} from './BaseFieldTag';
import website from '../../config/website'
import {BaseCtrl} from '@/util/eframe.js'
import store from "@/store";
export default {
  name: "EfShow",
  mixins: [BaseFieldTag,BaseCtrl], //继承基础BaseFieldTag
  props: {
    placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
      type: String,
      default: "",
    },
    maxlength: {
      require: false,
      type: [String, Number],
      default: null
    },
    minlength: {
      require: false,
      type: [String, Number],
      default: null
    },
    buttonFlag:{
      require:false,
      type:Boolean,
      default: false,
    },

    /*****************************
     * ef_show 标签显示样式
     * 默认:['type1','textRight']
     * 目前支持的数值：
     *     textLeft:靠左
     *     textRight:靠右
     *     type1:默认,无边框,无背景颜色
     *     type2:有边框,有背景颜色
     ******************************/
    showType:{
      require:false,
      type:Array,
      default(){
        return ['type1','textRight'] || website.styleList.showType
      }
    },


    decimals: {
      require: false,
      type: [String, Number],
      default: 2
    },

    /*****************************
     * ef_show 标签 按照书籍字典翻译code值到name
     * 默认:null
     ******************************/
    dictionaryNo: { // 数据字典编号。
      type: String,
      default: null,
    },

    type:{
      require:false,
      type:String,
      default:'text'
    },
    listKey: {  //列中内容字段
      type: String,
      default: 'code',
    },
    listValue: {  //列中标题字段
      type: String,
      default: "name",
    },
  },
  data() {
    return {
      componentType: 'EfText',
      compMaxLength: null,
      compMinLength: null,
      compDecimals:2,
      optionLists:[]
    };
  },
  computed:{
    formStyle(){
      return this.$store.state.common.formStyle
    },
  },
  watch: {
    value() {
      this.compValue = this.value;
      this.getOptions()
    }
  },
  created() {
    if (typeof(this.maxlength) === "string") {
      this.compMaxLength = Number(this.maxlength);
    } else {
      this.compMaxLength = this.maxlength;
    }
    if (typeof(this.minlength) === "string") {
      this.compMinLength = Number(this.minlength);
    } else {
      this.compMinLength = this.minlength;
    }
    if(typeof (this.decimals)==='number'){
      this.compDecimals=this.decimals;
    }else {
      if(this.$eframe.isNull(this.decimals)){
        this.compDecimals=2;
      }else {
        this.compDecimals=parseInt(this.decimals);
      }
    }
    this.getOptions()
  },
  methods: {
    /**
     * 获取下拉框的选择项数据
     */
    getOptions() {
      if (this.dictionaryNo) {
        //this.$eframe.loadDicItem( {dicId:this.dictionaryNo},(data)=>{ this.optionLists = data;  } );
        let form = {
          dicId: this.dictionaryNo,
          dicListGroup: '',  //字典分组
          language: '', //语种
          catchFlag: true, //是否使用缓存，默认true
        };
        store.dispatch('LoadDicItem', form)
            .then((data) => {
              this.optionLists = data;
              this.compValue = this.findValue(this.optionLists)
            })
            .catch((msg) => {
              this.$eframe.alertError(msg);
            });
        return;
      }
    },
    findValue(list) {
      for (let k in list){
        if (list[k][this.listKey]===this.value){
          return list[k][this.listValue];
        }
      }
      return this.value;
    },
    input(val) {
      this.$emit('compChange', val);
      this.$emit('onInput', val);
    },
  }
}
</script>

<style scoped lang="scss">
::v-deep .el-input-number.is-without-controls .el-input__inner{
  text-align: right;
}
</style>
