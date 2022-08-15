<template>
  <div style="height: 100%">
    <el-button v-prevent-re-click @click="open1">老年痴呆症筛查（初筛）量表</el-button>
    <el-button v-prevent-re-click @click="open2">老年抑郁障碍筛查（初筛）量表</el-button>
    <el-button v-prevent-re-click @click="open3">抑郁症精筛-汉密尔顿抑郁量表</el-button>
    <el-button v-prevent-re-click @click="download">download</el-button>
    <el-button v-prevent-re-click @click="closeFile">closeFile</el-button>
    <div style="height: calc(100% - 40px)">
      <EfEditFile :documentInfo="documentInfo" ref="document1" @onDownloadAs="test"></EfEditFile>
    </div>
  </div>
</template>

<script>
import EfEditFile  from "/src/components/ef-onlyoffice-comps/EfEditFile"
export default {
  name: "editfile",
  components:{
    EfEditFile
  },
  data() {
    return {

      pageLoading: false,
      documentInfo: {
        url: "",
        fileType: "",
        title: "",
        isEdit: "",
        key:"",
      },
    }
  },
  created() {
    // this.init();
   },

  methods: {
    init(options){
      this.documentInfo = options;
    },

    open1(){
      let options = {
        key:"4",
        url: "http://106.14.226.146:8401/ftproot/TEMP/2021/05/1.docx",
        fileType: "docx",
        title: "老年痴呆症筛查（初筛）量表",
        isEdit: true,
      }
      this.init(options);
    },

    open2(){
      let options = {
        key:"2",
        url: "http://106.14.226.146:8401/ftproot/TEMP/2021/05/2.docx",
        fileType: "docx",
        title: "老年抑郁障碍筛查（初筛）量表",
        isEdit: true,
      }
      this.init(options);
    },
    open3(){
      let options = {
        key:"3",
        url: "http://106.14.226.146:8401/ftproot/TEMP/2021/05/3.docx",
        fileType: "docx",
        title: "抑郁症精筛-汉密尔顿抑郁量表",
        isEdit: true,
      }
      this.init(options);
    },
    download(){
      this.$refs.document1.docEditor.downloadAs();
    },
    test(url){
      console.log(url);
    },
    closeFile(){
      this.$refs.document1.docEditor.destroyEditor();
    }

  }

}
</script>

<style scoped>

</style>