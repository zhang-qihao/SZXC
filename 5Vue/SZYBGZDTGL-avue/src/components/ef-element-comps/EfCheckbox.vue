<template>
  <el-form-item :label="label" :prop="name" :rules="rules" :label-width="compLabelWidth" inline-message="true">
    <span slot="label">{{ label }}</span>
    <div  v-if="compShow">
      <el-checkbox v-model="checkAll"
                   :indeterminate="isIndeterminate"
                   @change="handleCheckAllChange"
                   :v-if="compShow">全选
      </el-checkbox>
    </div>
<!--    <div style="margin: 15px 0;"></div>-->
    <el-checkbox-group  v-model="compValueList"
                       @change="handleCheckedChange" :disabled="compReadonly">
      <el-checkbox :ref="item.name" v-for="item in optionLists"
                   :label="listKey ? item[listKey] : item.code"
                   :key="listKey ? item[listKey] : item.code">{{ listValue ? item[listValue] : item.name }}
      </el-checkbox>
    </el-checkbox-group>
  </el-form-item>
</template>

<script>
import {BaseFieldTag} from './BaseFieldTag';
import store from "../../store";

export default {
  name: "EfCheckbox",
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
    checkAllShow:{
      type: [String,Boolean],
      default: false,
    },
  },
  data() {
    return {
      isIndeterminate: true,
      checkAll: true,
      compShow: false,
      compValueList: [],
    };
  },
  watch: {
    list() {
      this.getOptions();
    },
    value: {
      handler(nVal){
        this.compValueList = nVal;
      },
      immediate:true,
    },
  },
  computed:{
    formStyle(){
      return this.$store.state.common.formStyle;
    },
  },
  created() {
    if(typeof(this.checkAllShow)=='boolean'){
      this.compShow=this.checkAllShow;
    }else {
      if(this.checkAllShow=='true'){
        this.compShow=true;
      }else {
        this.compShow=false;
      }
    }
    this.getOptions();
  },
  methods: {
    handleCheckAllChange(val) {

      let that=this;
      that.compValueList=[];
      if (val) {
        //true 为全选
        if (this.$eframe.isNull(that.listKey)) {
          this.optionLists.forEach(function (item) {
            that.compValueList.push(item['code']);
          })
        }else {
          this.optionLists.forEach(function (item) {
            that.compValueList.push(item[that.listKey]);
          })
        }
      }else {
        that.compValueList=[];
      }
      // this.compValueList = val ? this.optionLists : [];
      this.isIndeterminate = false;
      this.$emit('compChange', this.compValueList);    // 更新 model
      this.$emit('onChange', this.compValueList);
    },
    handleCheckedChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.optionLists.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.optionLists.length;
      this.$emit('compChange', this.compValueList);    // 更新 model
      this.$emit('onChange', this.compValueList);

    },

    /**
     * 选择框change事件，将所选值和所选值所在的对象回传给父组件
     * @val {String} 当前选中值
     **/
    change(val) {
      let checkedCount = val.length;
      this.checkAll = checkedCount === this.optionLists.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.optionLists.length;

      this.$emit('compChange', this.compValueList);    // 更新 model
      this.$emit('onChange', this.compValueList);
    },

    /**
     * 获取选择项数据
     */
    getOptions() {
      if (this.dictionaryNo) {
        // this.$eframe.loadDicItem({dicId: this.dictionaryNo}, (data) => {
        //   this.optionLists = data;
        // });
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
      if (this.list) {
        if (this.$eframe.isArray(this.list)) { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = this.list;
        } else if (typeof (this.list) === "string") { // 如果父组件有下拉选项数据，则不进行数据请求
          this.optionLists = JSON.parse(this.list);
        }
        return;
      }
    },
    setFocus(val){
      this.$refs[val].focus()
    }
  }


}
</script>

<style scoped>

</style>