<template>
  <el-form-item :label="label" :label-width="compLabelWidth" :prop="name" :rules="rules" inline-message="true">
    <el-upload
        v-model="compValue"
        :accept="compAccept"
        class="upload-demo"
        :limit="compLimit"
        :multiple="true"
        :drag="compDrag"
        ref="upload"
        action="#"
        :on-preview="handlePreview"
        :before-remove="handleRemove"
        :file-list="compValueList"
        :list-type="compListType"
        :on-exceed="handleOnExceed"
        :auto-upload="false"
        :on-change="onChange">
      <el-button v-prevent-re-click  slot="trigger" size="small" type="primary">选取文件</el-button>
<!--      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>-->
      <div slot="tip" class="el-upload__tip">{{placeholder}}</div>
    </el-upload>
  </el-form-item>
</template>

<script>
import {
  BaseFieldTag
} from './BaseFieldTag';
export default {
  name: "EfUpload",
  mixins: [BaseFieldTag], //继承基础BaseFieldTag
  props: {
    placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
      type: String,
      default: "",
    },
    limit:{//文件数量限制
      type: [String,Number],
      default: 1,
    },
    accept:{//接收文件类型 extension
      type:[String,Array],
      default:[],
    },
    listType:{
      type:String,
      default:"text",
    },
  },

  data() {
    return {
      compValueList:[],
      compLimit:1,//默认单文件上传
      compDrag:false,//是否支持拖拽
      compMultiple:false,//默认单文件删除
      compFlag:false,
      compAccept:"",
      compListType:"text",//显示样式 文字:text 图片卡:picture-card
    };
  },
  computed: {
    formStyle() {
      return this.$store.state.common.formStyle
    },
    placeholderClass() {
      return this.$store.state.common.formStyle[0].size
    }
  },
  watch: {

  },
  created() {
    //显示样式
    if(this.listType.toLowerCase()=='card' || this.listType.toLowerCase()=='picture-card'){
      this.compListType="picture-card";
    }else {
      this.compListType="text"
    }


    //文件数量限制
    if(typeof (this.limit) === "string"){
      this.compLimit= Number(this.limit) ;
    }else {
      this.compLimit= this.limit;
    }
    if(this.compLimit>1){
      this.compMultiple=true;
    }

    //选择文件类型
    this.handleAccept();

  },
  methods: {
    handleAccept(){
      //处理文件类型
      let arr=[];
      if(typeof (this.accept) === "string"){
        //String类型 以，作为分隔符 拆分数组
        arr=this.accept.split(",");
      }else if(this.$eframe.isArray(this.accept)){
        arr=this.accept;
      }else {
        arr=[];
      }
      for(let i=0;i<arr.length;i++){
        this.compAccept+="."+arr[i]+","
      }
    },

    handleRemove(file, fileList) { //删除事件

    },

    handlePreview(file) {  //点击事件

    },

    handleOnExceed(file, fileList){
      alert("超出上传附件数量！");
    },
    onChange(file, fileList){
      this.compValueList=fileList;
      let testFile = file.name.substring(file.name.lastIndexOf('.') + 1).toLowerCase()

      //处理后缀不相符的文件
      if(this.$eframe.isNotNull(this.compAccept)){
        if(this.compAccept.toLowerCase().indexOf(testFile.toLowerCase())<0){//
          fileList.splice(fileList.length-1, 1);
          alert("选择文件类型错误！");
        }
      }

      if(this.compValueList.length>=this.limit){
        this.compFlag=true;
      }else {
        this.compFlag=false;
      }
      this.$emit('compChange', this.compValueList);    // 更新 model
      this.$emit('onChange', this.compValueList);
    }
  }
}
</script>

<style scoped>

</style>