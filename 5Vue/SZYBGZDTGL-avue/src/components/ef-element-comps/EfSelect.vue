<template>
	<el-form-item :class="placeholderClass" :label-width="compLabelWidth"  :label="label" :prop="name" :rules="rules" inline-message="true">
    <span slot="label">{{ label }}</span>
    <el-select
        :ref="name"
        :size="formStyle[0].size"
				v-model="compValue"
				:multiple="compMultiple"
				:disabled="compReadonly"
				:clearable="compClearable"
				:filterable="compFilterable"
				:placeholder="placeholder"
				:collapse-tags="compCollapseTags"
				style="width: 100%"
				@change="change"
				@visible-change="visibleChange">
			<el-option v-if="compEmptyOption" key="" label="" value=""/>
			<el-option
					v-for="item in optionLists"
					:key="listKey ? item[listKey] : item.code"
					:label="listValue ? item[listValue] : item.name"
					:value="listKey ? item[listKey] : item.code" />
		</el-select>
	</el-form-item>
</template>
<script type="text/javascript">
	import {BaseFieldTag} from './BaseFieldTag';
	import store from "../../store";
	export default {
		name: 'EfSelect',
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
			emptyOption: { // 是否包含空选项， 默认false，非必传。
				type: [String, Boolean],
				default: false,
			},
			//...BaseFieldTag.props,
			multiple: { // 是否多选，默认false，非必传。
				type: [String, Boolean],
				default: false,
			},
			collapseTags: { // 多选用数字替代
				type: [String, Boolean],
				default: false,
			},
			clearable: { // 是否可清空选项，默认true，非必传。
				type: [String, Boolean],
				default: true,
			},
			filterable: { // 是否可搜索， 默认false，非必传。
				type: [String, Boolean],
				default: false,
			}
		},
		data() {
			return {
				componentType:'EfSelect',
				compEmptyOption:false,
				compMultiple:false,
				compClearable:false,
				compFilterable:false,
				compValidateTrigger:'change', //校验触发事件,select为change
				optionLists: [],
				compCollapseTags:false,
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
		watch: {
			list() {
				this.getOptions();
			},
			value() {
				this.compValue = this.value;
			},
		},
		created(){
			if(typeof (this.emptyOption)=="string") {
				this.compEmptyOption = this.emptyOption === 'true';
			}else{
				this.compEmptyOption = this.emptyOption === true;
			}
			if(typeof (this.multiple)=="string") {
				this.compMultiple = this.multiple === 'true';
			}else{
				this.compMultiple = this.multiple === true;
			}
			if(typeof (this.collapseTags)=="string") {
				this.compCollapseTags = this.collapseTags === 'true';
			}else{
				this.compCollapseTags = this.collapseTags === true;
			}
			if(typeof (this.clearable)=="string") {
				this.compClearable = this.clearable === 'true';
			}else{
				this.compClearable = this.clearable === true;
			}
			if(typeof (this.filterable)=="string") {
				this.compFilterable = this.filterable === 'true';
			}else{
				this.compFilterable = this.filterable === true;
			}
			this.getOptions();
		},
		methods: {
			/**
			 * 获取下拉框的选择项数据
			 */
			getOptions() {
				if(this.dictionaryNo){
					//this.$eframe.loadDicItem( {dicId:this.dictionaryNo},(data)=>{ this.optionLists = data;  } );
					let form={
						dicId:this.dictionaryNo,
						dicListGroup:this.dictionaryGroup,  //字典分组
						language:'', //语种
						catchFlag:this.compCatch, //是否使用缓存，默认true
					};
					store.dispatch('LoadDicItem',form).then((data)=>{ this.optionLists = data; }).catch((msg) => {
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

			/**
			 * 选择框change事件，将所选值和所选值所在的对象回传给父组件
			 * @val {String} 当前选中值
			 **/
			change(val) {
				this.$emit('compChange', val);    // 更新 model
				this.$emit('onChange', val);
			},
			/**
			 * 下拉框出现/隐藏时触发
			 * @isShow {Boolean} 标识：true为下拉框展开；false为下拉框隐藏
			 **/
			visibleChange(isShow) {
				this.$emit('onVisibleChange', isShow);
			},
      getValue(){
			  var list=this.optionLists;
        for (let k in list ){
          // if (list[k].[this.listKey]===this.value){
          //   return list[k].[this.listValue];
          // }
        }
        return this.value;
      },
      setFocus(){
        this.$refs[this.name].focus()
      },
      setBlur(){
        this.$refs[this.name].blur()
      }
		},
	}
</script>
<style lang="scss" scoped>
	.el-select-dropdown__item.hover, .el-select-dropdown__item:hover{
		background: #edf0f4;
	}
</style>
