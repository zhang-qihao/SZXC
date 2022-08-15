<template>
  <el-drawer :title="titleInner" :visible.sync="visible" :size="dialogWidth" :with-header="false"
             :before-close="handleClose" :wrapperClosable="maskClose" :modal-append-to-body="body"
             :append-to-body="body">
    <component :is="tempComponent" :params="paramsInner"/>
    <div class="back">
      <img @click="back" src="./img/back.png" alt="">
    </div>
  </el-drawer>
</template>

<script type="text/javascript">
// import Vue from 'vue'
export default {
  name: "EfDrawer",
  props: {
    show: {
      require: false,
      type: Boolean,
      default: false,
    },
    title: {
      require: false,
      type: String,
      default: "提示",
    },
    view: {
      require: false,
      type: String,
      default: "",
    },
    width: {
      require: false,
      type: [String, Number],
      default: 600,
    },

    params: {
      require: false,
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  computed: {
    dialogWidth: function () {
      let width = "50%";
      if (this.widthInner) {
        if (typeof this.widthInner == "number") {
          width = this.widthInner + "px";
        } else {
          width = this.widthInner;
        }
      }
      return width;
    },
  },
  data() {
    return {
      componentType: 'EfDrawer',
      visible: false,
      tempComponent: null,
      maskClose: false,
      body: true,
      // 新增代码
      showInner: this.show,
      titleInner: this.title,
      viewInner: this.view,
      widthInner: this.width,
      paramsInner: this.params,
    };
  },
  watch: {
    show: function (newl) {
      this.visible = newl;
      this.fullscreen = false;
    },
  },
  created() {

  },
  methods: {
    close(bakParams) {
      if (typeof (bakParams) == 'undefined') {
        bakParams = null;
      }
      this.visible = false;
      if (typeof (this.callBackFun) == 'function') {
        this.callBackFun(bakParams);
      }
    },
    getComponent() {
      if (this.viewInner) {
        this.tempComponent = (resolve) =>
            require([`@/views${this.viewInner}`], resolve);
      }
    },
    handleClose() {
      this.close(null);
    },

    showDrawer(params) {

      if (typeof (params) == 'undefined' || params == null || params == "") {
        alert("slideReveal函数错误，params不可为空");
        return;
      }
      var title = params.title;
      var view = params.view;
      var width = params.width;
      var callBackFun = params.callBackFun;
      var iframeFlag = params.iframeFlag; //打开模式   默认:angularjs 路由   iframe时view嵌入iframe中展示
      var windowParams = params.params;

      if (typeof (windowParams) == 'undefined') {
        windowParams = {};
      }

      if (iframeFlag != true) {
        iframeFlag = false;
      }

      if (typeof (view) == 'undefined' || view == null || view == "") {
        alert("slideReveal函数错误，view不可为空");
        return;
      }
      if (typeof (title) == 'undefined' || title == null || title == "") {
        title = "";
      }

      if (typeof (width) == 'undefined' || width == null) {
        width = 500;
      }


      this.visible = true;
      this.fullscreen = false;
      this.titleInner = title;
      this.viewInner = view;
      this.widthInner = width;
      this.paramsInner = windowParams;
      this.callBackFun = callBackFun;
      if (this.viewInner) {
        let path = this.viewInner;
        // path="@/page/frame/systemmanagement/usermanagement/user/usermanagement"
        // console.log(path);
        path = path.replace(/\/\//g, "/");
        if (path.indexOf('/')==0) {
          path = path.substring(1, path.length);
        } else if (path.indexOf('@/')==0) {
          path = path.substring(2, path.length);
        }
        // console.log(path);
        this.tempComponent = (resolve) => require([`@/${path}.vue`], resolve);

      }
    },
    back() {
      this.close()
    }

  },
};
</script>

<style>
header.el-drawer__header {
  height: 56px;
  border-bottom: 1px solid #ececec;
  box-sizing: border-box;
  font-size: 16px;
  padding: 20px 24px 10px;
  margin-bottom: 0;
  /* color: rgba(0, 0, 0, 0.85); */
}

.el-drawer__body {
  background: #f0f2f5;
  height: 100%;
  /*overflow: auto;*/
}

.el-dialog__close:focus,
.el-drawer__close-btn:focus {
  outline: none;
}

.back {
  position: absolute;
  width: 20px;
  height: 134px;
  top: 50%;
  left: -14px;
  margin-top: -67px;
  background: transparent;
  cursor: pointer;
  display: flex;
  align-items: center;
}

</style>
