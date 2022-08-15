<template>
  <div style="height: 100%">
    <div id="EfEditFile" ></div>
  </div>
</template>
<script>
import {handleDocType} from "../../util/util"
import {mapGetters} from "vuex";

export default {
  props: {
    documentInfo: { // 文件
      type: [String,Object],
      default: null,
    },
  },
  data(){
    return {
      docEditor:""
    }
  },
  mounted() {
    if (this.documentInfo.url)
      this.openFile(this.documentInfo)
  },
  watch: {
    documentInfo: {
      handler: function (n, o) {
        this.openFile(n);
        this.documentType = handleDocType(n.fileType);
      },
      deep: true,
    }
  },
  computed: {
    ...mapGetters([
      "userInfo",
    ]),
  },
  methods: {
    openFile(){
      var that=this;
      if(isNull(this.documentInfo.url)){
        alert("打开文件失败,url不可为空！");
      }
      var fileType=this.documentInfo.fileType;
      var key=this.documentInfo.key;
      var title=this.documentInfo.title;
      var url=this.documentInfo.url;
      var edit=this.documentInfo.isEdit || false;
      console.log(this.userInfo);

      var userId=this.userInfo.userId;
      var userName=this.userInfo.userName;

      var documentType=handleDocType(fileType);

      //下载文件触发事件
      var onDownloadAs = function (event) {
        that.$emit("onDownloadAs",event.data);
        // return event.data;
      }
      //内容修改触发事件
      var onDocumentStateChange = function (event) {
        if (event.data) {
          console.log("The document changed");
        } else {
          console.log("Changes are collected on document editing service");
        }
      }

      let config = {
        // "type": 'embedded',
        "document": {
          "fileType": fileType,
          "key": key,
          "title": title,
          "url": url,
          permissions: {
            edit: edit, // default = true
          }
        },
        "documentType": documentType,
        "height": "100%",
        "width": "100%",
        "editorConfig": {
          "user": {
            "id": userId,
            "name": userName,
          },
          customization: {
            "autosave":false,
            "reviewDisplay":"original",
            "toolbarNoTabs":false,
            "comments": true,
          },
          lang:'zh',
          region:"zh-CN"
        },
        "events": {
          "onDownloadAs": onDownloadAs,
        },
      }

      this.docEditor = new DocsAPI.DocEditor("EfEditFile", config);
    },
  }
}
</script>

<style scoped>

</style>