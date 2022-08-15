<template>
  <div class="avue-ueditor">
    <quill-editor :style="{height:`${minRows*40+100}px`,maxHeight:`${maxRows*40+100}px`}"
                  v-model="text"
                  :disabled="disabled"
                  @change="handleChange"
                  @click.native="handleClick"
                  @dblclick.native="handleDbClick"
                  ref="myQuillEditor"
                  :options="option">
    </quill-editor>
    <transition name="fade">
      <div class="avue-ueditor__dialog"
           v-show="box">
        <div class="avue-ueditor__mask"
             @click.stop="box=false"></div>

        <div class="avue-ueditor__content">
          <p class="avue-ueditor__tip">
            <span v-if="loading">
              正在上传中，请稍后
            </span>
          </p>
          <div class="avue-ueditor__img">
            <div class="avue-ueditor__img-left">
              <p>
                <small>宽度</small>:
                <input type="text"
                       :disabled="imgFlag"
                       class="avue-ueditor__img-input"
                       v-model="img.width" />
              </p>
              <p>
                <small>高度</small>:
                <input type="text"
                       :disabled="imgFlag"
                       class="avue-ueditor__img-input"
                       v-model="img.height" />
              </p>
            </div>
            <div class="avue-ueditor__img-right">
              <img :src="img.url"
                   class="avue-ueditor__img-img"
                   ref="img"
                   :width="img.width"
                   :height="img.height"
                   alt="" />
            </div>
          </div>
          <div class="avue-ueditor__menu">
            <div class="avue-ueditor__upload"
                 v-if="!isImg">
              <button class="avue-ueditor__btn avue-ueditor__btn--plan">上 传</button>
              <input type="file"
                     id="file"
                     @change="handleUpload"
                     class="avue-ueditor__file" />
            </div>
            &nbsp;
            <button class="avue-ueditor__btn avue-ueditor__btn--plan"
                    :class="{'avue-ueditor__btn--disabled':imgFlag}"
                    @click="handleSubmit">确 定</button>
          </div>

        </div>
      </div>
    </transition>
  </div>
</template>
<script>
import quillEditor from "vue-quill-editor/src/editor";
import { getClient } from "./upload/ali";
import { getToken } from "./upload/qiniu";
import { getObjValue } from "./upload/util";
export default {
  name: "AvueUeditor",
  components: {
    quillEditor
  },
  computed: {
    imgFlag () {
      return this.img.url == '';
    },
    isImg () {
      return this.img.obj.src
    },
    urlKey () {
      return this.props.url || 'url';
    },
    props () {
      return this.options.props || this.upload.props;
    },
    oss () {
      return this.options.oss || this.upload.oss;
    },
    action () {
      return this.options.action || this.upload.action;
    },
    qiniu () {
      return this.options.qiniu || this.upload.qiniu;
    },
    ali () {
      return this.options.ali || this.upload.ali;
    },
    isQiniuOSS () {
      return this.oss === "qiniu";
    },
    isAliOSS () {
      return this.oss === "ali";
    }
  },
  props: {
    options: {
      type: Object,
      default: () => {
        return {};
      }
    },
    upload: {
      type: Object,
      default: () => {
        return {};
      }
    },
    value: {
      type: String,
      default: ""
    },
    disabled: {
      type: Boolean,
      default: false
    },
    readonly: {
      type: Boolean,
      default: false
    },
    height: {
      default: ""
    },
    minRows: {
      type: Number,
      default: 8
    },
    maxRows: {
      type: Number,
      default: 10
    }
  },
  data () {
    return {
      myQuillEditor: {},
      loading: false,
      text: undefined,
      box: false,
      img: {
        obj: {},
        url: "",
        width: 0,
        height: 0,
        calc: ""
      },
      option: {
        placeholder: "请输入内容",
        readonly: false
      }
    };
  },
  watch: {
    "img.width" (n, o) {
      if (n === 0 || o === 0) return;
      this.img.height = parseInt(n * this.img.calc);
    },
    disabled: {
      handle () {
        this.$nextTick(() => {
          this.myQuillEditor.enable(false)
        })
      }
    },
    value () {
      this.text = this.value;
    }
  },
  created () {
    this.init();
  },
  mounted () {
    this.myQuillEditor = this.$refs.myQuillEditor.quill
    this.myQuillEditor
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
  },
  methods: {
    handleSubmit () {
      if (this.imgFlag) return
      const index = this.myQuillEditor.selection.savedRange.index || this.text.length;
      if (this.isImg) {
        this.img.obj.width = this.$refs.img.width;
        this.img.obj.height = this.$refs.img.height;
      } else {
        this.myQuillEditor.insertEmbed(index, "image", this.img.url);
        this.myQuillEditor.focus();
      }
      this.clearImg();
      this.box = false;
    },
    clearImg () {
      this.img.obj = {};
      this.img.url = "";
      this.img.width = 0;
      this.img.height = 0;
    },
    handleUpload (e) {
      this.loading = true;
      const file = e.target.files[0];
      this.handleFile(file).then(() => {
        this.setImgParam();
      });
    },
    handleFile (file) {
      return new Promise(resolve => {
        const headers = { "Content-Type": "multipart/form-data" };
        let oss_config = {};
        let client;
        let param = new FormData();
        let url = this.action;
        param.append("file", file, file.name);
        if (this.isQiniuOSS) {
          oss_config = this.qiniu;
          const token = getToken(oss_config.AK, oss_config.SK, {
            scope: oss_config.scope,
            deadline: new Date().getTime() + oss_config.deadline * 3600
          });
          param.append("token", token);
          url = "http://up.qiniu.com/";
        } else if (this.isAliOSS) {
          oss_config = this.ali;
          client = getClient({
            region: oss_config.region,
            endpoint: oss_config.endpoint,
            accessKeyId: oss_config.accessKeyId,
            accessKeySecret: oss_config.accessKeySecret,
            bucket: oss_config.bucket
          });
        }
        (() => {
          if (this.isAliOSS) {
            return client.put(file.name, file);
          } else {
            return this.$httpajax.post(url, param, { headers });
          }
        })().then(res => {
          let list = {};
          if (this.isAliOSS) {
            list = res;
            this.img.url = list.url;
          } else if (this.isQiniuOSS) {
            list = res.data;
            list.key = oss_config.url + list.key;
            this.img.url = list.key;
          } else {
            list = getObjValue(res.data, this.props.res, "object");
            this.img.url = list[this.urlKey];
          }
          this.img.width = false;
          this.img.height = false;
          this.loading = false;
          resolve();
        });
      });
    },
    setImgParam () {
      const img = this.$refs.img;
      img.onload = () => {
        this.img.width = img.width;
        this.img.height = img.height;
        this.img.calc = img.height / img.width;
      };
    },
    imgHandler () {
      this.clearImg();
      this.box = true;
    },
    init () {
      this.text = this.value;
      this.option.placeholder = this.placeholder || "请输入内容";
      this.option.readonly = this.readonly;
      this.option = Object.assign(this.option, this.options, this.upload)
      this.handlePaste();
    },
    handlePaste () {
      //粘贴键
      document.addEventListener("paste", e => {
        //获取剪切板文件
        const getFile = event => {
          if (event.clipboardData || event.originalEvent) {
            var clipboardData =
              event.clipboardData || event.originalEvent.clipboardData;
            if (clipboardData.items) {
              var items = clipboardData.items,
                len = items.length,
                blob = null;
              for (var i = 0; i < len; i++) {
                if (items[i].type.indexOf("image") !== -1) {
                  blob = items[i].getAsFile();
                  return blob;
                }
              }
            }
          }
        };
        const file = getFile(e);
        if (file) {
          this.box = true;
          this.handleFile(file).then(() => {
            this.setImgParam();
          });
        }
      });
    },

    handleClick () {
      if (typeof this.click === "function")
        this.click({ value: this.text, column: this.column });
    },
    handleDbClick (e) {
      if (e.target.nodeName == 'IMG') {
        const img = e.target
        this.img.obj = e.target;
        this.img.url = img.src;
        this.img.width = img.width;
        this.img.height = img.height;
        this.img.calc = img.height / img.width;
        this.box = true;
      }
    },
    handleChange (value) {
      if (typeof this.change === "function")
        this.change({ value: value.html, column: this.column });
      this.$emit("input", value.html);
      this.$emit("change", value.html);
    }
  }
};
</script>
<style lang="scss">
@import "../../theme-chack/src/index.scss";
</style>
