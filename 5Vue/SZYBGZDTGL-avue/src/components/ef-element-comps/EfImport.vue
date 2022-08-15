<template>
    <el-upload
        :accept="compAccept"
        class="upload-demo inline-block"
        :show-file-list="false"
        :limit="2"
        ref="import"
        action=""
        :file-list="compValueList"
        :auto-upload="false"
        :on-change="onChange">
      <el-button v-prevent-re-click slot="trigger" size="mini" type="primary" icon="el-icon-upload2">{{ label }}</el-button>
    </el-upload>
</template>

<script>
import {
  BaseFieldTag
} from './BaseFieldTag';
export default {
  name: "EfImport",
  mixins: [BaseFieldTag], //继承基础BaseFieldTag
  props: {
    accept:{//接收文件类型 extension
      type:[String,Array],
      default:"",
    },
    placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
      type: String,
      default: "",
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
    this.handleAccept();
  },
  methods: {
    handleAccept(){
      //处理文件类型
      let arr=[];
      if(typeof (this.accept) === "string"){
        //String类型 以，作为分隔符 拆分数组
        if(isNotNull(this.accept)){
          arr=this.accept.split(",");
        }

      }else if(this.$eframe.isArray(this.accept)){
        arr=this.accept;
      }else {
        arr=[];
      }
      for(let i=0;i<arr.length;i++){
        this.compAccept+="."+arr[i]+","
      }
    },
    onChange(file, fileList){
      console.log("onchange")
      this.fileTemp = file.raw
      let fileName = file.raw.name
      let fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
      //选择文件类型

      // 判断上传文件格式
      if (this.fileTemp) {
        if(this.$eframe.isNotNull(this.compAccept)){
          if(this.compAccept.toLowerCase().indexOf(fileType.toLowerCase())<0){//
            fileList.splice(fileList.length-1, 1);
            alert("选择文件类型错误！");
          }
        }

        // if ((fileType == 'xlsx') || (fileType == 'xls')) {
        //   //回调执行上传方法
          this.$emit('onChange', this.fileTemp);
          this.$refs.import.uploadFiles.shift()
        // } else {
        //   this.compFileList=[];
        //   this.$message({
        //     type: 'warning',
        //     message: '附件格式错误，请删除后重新上传！'
        //   })
        // }

      } else {
        this.compFileList=[];
        this.$message({
          type: 'warning',
          message: '请上传附件！'
        })
      }

    }
  }
}
</script>

<style scoped lang="scss">
.inline-block{
  display: inline-block;
  float: left;
}
</style>