<template>
  <el-form-item  :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-radio-group v-model="compValue">
      <el-radio  v-for="item in optionLists"
                 :key="listKey ? item[listKey] : item.code"
                 :label="listKey ? item[listKey] : item.code"
                 @change="change"
                 :disabled="compReadonly">
        {{ listValue ? item[listValue] : item.name }}
      </el-radio>
    </el-radio-group>
  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';
import store from "../../store";
export default {
  name: "EfRadio",
  mixins: [BaseFieldTag],         //继承基础BaseFieldTag
  props: {
    dictionaryNo: { // 数据字典编号。
      type: String,
      default: null,
    },
    dictionaryGroup: {  //分组
      type: String,
      default: null,
    },
    list: {  //分组
      type: [String, Array],
      default: null,
    },
    listKey: {  //列中内容字段
      type: String,
      default: 'code',
    },
    listValue: {  //列中标题字段
      type: String,
      default: "name",
    },
    queryNo: {  //列中标题字段
      type: String,
      default: null,
    },
  },
  data() {
    return {
    };
  },
  watch: {
    value: {//深度监听，可监听到对象、数组的变化
      immediate: true,
      handler(val) {
        this.compValue=val;
      },
      // deep: true //true 深度监听
    },
  },
  computed:{
    formStyle(){
      return this.$store.state.common.formStyle
    },
  },
  created(){
    this.getOptions();
  },
  methods: {
    /**
     * 选择框change事件，将所选值和所选值所在的对象回传给父组件
     * @val {String} 当前选中值
     **/
    change(val) {
      this.$emit('compChange', val);    // 更新 model
      this.$emit('onChange', val);
    },

    /**
     * 获取选择项数据
     */
    getOptions() {
      if(this.dictionaryNo){
        //this.$eframe.loadDicItem( {dicId:this.dictionaryNo},(data)=>{ this.optionLists = data;  } );
        let form={
          dicId:this.dictionaryNo,
          dicListGroup:'',  //字典分组
          language:'', //语种
          catchFlag:this.compCatch, //是否使用缓存，默认true
        };
        store.dispatch('LoadDicItem',form).then((data)=>{ this.optionLists = data;  }).catch((msg) => {
          this.$eframe.alertError(msg);
        });
        return;
      }
      if(this.list) {
        if (this.$eframe.isArray(this.list)) { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = this.list;
        } else if (typeof (this.list) === "string") { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = JSON.parse(this.list);
        }
        return;
      }
    },
  }
}
</script>

<style scoped>

</style>