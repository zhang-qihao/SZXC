<template>
  <el-container style="height: 100%;height: 600px">
    <el-header>
      <div class="header-left">
        <div class="header-title" slot="title">{{ title1 }}</div>
      </div>
      <div class="header-right">
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-minus" size="mini" type="warning" v-show="onshow" @click="savePers('del')">离岗
          </el-button>
          <el-button icon="el-icon-plus" size="mini" type="primary" v-show="onshow1" @click="savePers('add')">保存</el-button>
        </el-button-group>
      </div>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <base-block class="out-block" rounded ref="exampleBlock2">
          <div class="header-title" slot="title"><i class="el-icon-user-solid"></i>人员基础信息</div>

          <el-form ref="form" :model="form" label-position="left" label-width="auto" :rules="IDRules">
            <el-row :gutter="24">
              <el-col :span="8">
<!--                <el-form-item prop="aac003" label="人员姓名">
                  <el-input size="small"
                            v-model="form.aac003"
                            auto-complete="off"
                            placeholder=""
                            @blur="blurname()">
                  </el-input>
                </el-form-item>-->
                <ef-text label="人员姓名" v-model="form.aac003" name="aac003" require="true"  :onblur="blurname()" placeholder=""/>
              </el-col>
              <el-col :span="8">
                <ef-select label="性别" name="aac004" v-model="form.aac004" :require="true" :emptyOption="false"
                           dictionaryNo="AAC004" placeholder=""
                />
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件号码：" prop="aac147">
                  <el-input v-model="form.aac147" placeholder=""></el-input>
                </el-form-item>
<!--               <ef-text label="证件号码" v-model="form.aac147"  name="aac147" placeholder=""
                         require="true"/>-->
              </el-col>

            </el-row>
            <el-row :gutter="24">
              <el-col :span="8">
                <ef-select label="人员性质" v-model="form.aac039" name="aac039" dictionaryNo="DIC_AAC039" placeholder=""
                           require="true"/>
              </el-col>
              <el-col :span="8">
                <ef-text label="出生日期" v-model="form.aac006" name="aac006" readonly="true" require="true"/>
              </el-col>
              <el-col :span="8">
                <ef-select label="政治面貌" v-model="form.aac029" name="aac029" dictionaryNo="DIC_AAC029" placeholder=""
                           require="true"/>
              </el-col>

            </el-row>

            <el-row :gutter="20">
              <el-col :span="8">

                <el-upload
                    :accept="compAccept"
                    class="upload-demo inline-block"
                    :show-file-list="false"
                    :limit="1"
                    ref="import"
                    action=""
                    :file-list="compValueList"
                    :on-preview="handlePreview"
                    :before-remove="handleRemove"
                    :auto-upload="false"
                    :on-change="onChange">
                  <el-pl style="margin-left: 19px;"><span style="color: #F56C6C;">*</span>工作照</el-pl>
                  <img width="120" height="120" style="margin-left: 5px;" id="docid"
                       src="/util/screen/gerenzhaopian.png"/>
                  <!--                <el-button v-prevent-re-click slot="trigger" size="mini" type="primary" icon="el-icon-upload2">{{ label }}</el-button>-->
                </el-upload>
              </el-col>
              <el-col :span="8">
                <ef-text label="用户名" v-model="form.userid" maxlength="50" name="userid" readonly="true"/>
              </el-col>
            </el-row>
            <ef-text label="图片字符串" v-model="form.base64" maxlength="50" name="base64" v-show="false"/>
            <ef-text label="人员状态" v-model="form.ckc005" maxlength="50" name="ckc005" v-show="false"/>
            <ef-text label="人员排序num" v-model="form.numid" maxlength="50" name="numid" v-show="false"/>
          </el-form>
        </base-block>
        <base-block class="out-block" style="margin-top: 15px" rounded ref="exampleBlock2">
          <div class="header-title" slot="title"><i class="el-icon-user-solid"></i>人员职务信息</div>

          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <el-row :gutter="24">
              <el-col :span="8">
                <ef-select label="职务" v-model="form.aac049" name="aac049" dictionaryNo="DIC_AAC049" require="true"
                           placeholder=""/>
              </el-col>
              <el-col :span="8">
                <ef-date label="任职时间" v-model="form.aac051" name="aac051" format="yyyyMMdd" require="true"/>
              </el-col>
              <el-col :span="8">
                <ef-select label="职级" v-model="form.aac059" name="aac059" dictionaryNo="DIC_AAC059" placeholder="" :onchange="selectaac059()"
                           require="true"/>
              </el-col>

            </el-row>
            <el-row :gutter="24">
              <el-col :span="8">
                <ef-date label="任该职级时间" v-model="form.aac061" name="aac061" format="yyyyMMdd" :require="onrequire"/>
              </el-col>
              <el-col :span="8">
<!--                <ef-text label="所属科室" v-model="form.ks003" name="ks003" readonly="true"/>-->
                <el-form-item label="所属科室：">
                  <el-select v-model="form.ks002">
                    <el-option
                        v-for="item in ks003list"
                        :key="item.code"
                        :label="item.name"
                        :value="item.code">
                    </el-option>
                  </el-select>
                </el-form-item>

              </el-col>

            </el-row>

          </el-form>
        </base-block>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe';
import {cardid} from "/src/util/validate"; //身份证校验组件
import {
  mapGetters
} from "vuex";
import constData from "../../../../const";

export default {
  name: "persadd",
  mixins: [BaseCtrl],
  computed: {
    ...mapGetters(["userInfo",]),
  },
  data() {
    const validateID = (rule, value, callback) => {
      if (cardid(value)[0]) {
        callback(new Error(cardid(value)[1]));
      } else {
        this.form.aac006 = value.substr(6, 8);
        callback();
      }
      //  callback(new Error("请输入4位数的验证码"));
      //alert(1);
    };
    return {
      compAccept: 'jpg,png',
      compValueList: [],
      title1: '',    //弹窗标题
      onshow: false, //离岗按钮是否显示
      onshow1: true, //保存按钮是否显示
      onrequire: false,
      //科室类型
      ks003list: [{
        code: 'kxglg',
        name: '科学管理岗'
      }],
      form: {
        ac01id: '',   //用户人员id
        base64: '',   //上传文件字符串
        aac006:'',
        aac003:'',
        userid:'',
        type:''   //增减类型
      },
      IDRules: {
        aac147: [{required: true, trigger: "blur", validator: validateID}]
      }
    };

  },
  created() {
    //获取主页面参数
    let params = this.$attrs.params;
    if (params) {
      if (params.type) {
        this.form.type = params.type;
        if (this.form.type == "mod") {
          this.onshow = true;
          this.onshow1 = true;
        }else if(this.form.type == "del"){
          this.onshow = false;
          this.onshow1 = false;
        }
      }
      if (params.title1) {
        this.title1 = params.title1;
      }
      if (params.ac01id) {
        this.form.ac01id = params.ac01id;
        //alert(this.form.ac01id);
        this.loadData(this.form.ac01id);
      }

    }

  },
  mounted() {
  },
  methods: {
    //更改职级时间
    selectaac059(){
      let that = this;
      if(that.form.aac059=='0'){
        that.onrequire = false;
      }else{
        that.onrequire = true;
      }
    },

    //更改用户名
    blurname(){
      //this.form.userid=this.form.aac003;
      //校验获取用户名编号
      let that = this;
      let pinyinform = {
        aac003 : that.form.aac003
      }
      /*let callbackFun = function (data) {
        if(isNotNull(data.userid)){
          that.form.userid=data.userid;
        }
      }
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/pers/persinfomgt.action',
        form: pinyinform,
        actionFunName: "getPinyin",
        succFun: callbackFun,
        failFun: null
      });*/

    },

    //加载用户人员信息
    loadData(ac01id) {
      let that = this;
      //获取信息
      let form1 = {
        ac01id: ac01id
      }
      let callbackFun = function (data) {
        that.form = data;
        that.form.type = "mod";
        /*if(isNotNull(data.registertime)){
          that.form.registertime = data.registertime.substring(0,10)
        }*/
        // that.form.fixmedins_name = "苏州市阳澄湖生态休闲旅游度假区阳澄湖畔花苑社区卫生服务站"
        that.loadDocument();

      }
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/pers/persinfomgt.action',
        form: form1,
        actionFunName: "loadPersData",
        succFun: callbackFun,
        failFun: null
      });
    },
    //加载照片
    loadDocument() {
      let that = this;
      let form2 = {
        ac01id: this.form.ac01id
      }
      let callbackFun = function (data) {
        //that.form = data
        /*if(isNotNull(data.registertime)){
          that.form.registertime = data.registertime.substring(0,10)
        }*/
        // that.form.fixmedins_name = "苏州市阳澄湖生态休闲旅游度假区阳澄湖畔花苑社区卫生服务站"
        if (isNotNull(data.base64)) {
          that.form.base64 = data.base64;
          $("#docid").attr("src", "data:image/jpeg;base64,"+data.base64);
        }

      }
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/pers/persinfomgt.action',
        form: form2,
        actionFunName: "loadDocument",
        succFun: callbackFun,
        failFun: null
      });


    },

    //保存用户人员信息
    savePers(type2) {
      let that = this;
      //alert(that.form.type);
      if(type2=='del'){ //离岗
        that.form.type='del';
      }else if(type2=='add' && that.form.type=='add'){  //新增
        that.form.type='add';
      }else if(type2=='add' && that.form.type=='mod'){  //修改
        that.form.type='mod';
      }else{
        this.alertError("操作失败！");
      }
      let succFun = function (data) {
        that.form = data;
        if(that.form.ckc005=='01'){//离岗，关闭页面
          that.close();
        }else{
          that.form.type='mod';
          that.onshow = true;
          that.onshow1 = true;
        }
      };
      let failFun = function (data) {

      };
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/pers/persinfomgt.action',
        form: that.form,
        actionFunName: "savePers",
        succFun: succFun,
        failFun: failFun,
      });
    },
    handleRemove(file, fileList) { //删除事件
    },
    handlePreview(file) {  //点击事件
    },
    //附件上传
    onChange(file, fileList) {
      console.log("onchange");

      let that = this;
      this.fileTemp = file.raw;
      let fileName = file.raw.name;
      let fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
      //选择文件类型

      // 判断上传文件格式
      if (this.fileTemp) {
        if (this.$eframe.isNotNull(this.compAccept)) {
          if (this.compAccept.toLowerCase().indexOf(fileType.toLowerCase()) < 0) {//
            fileList.splice(fileList.length - 1, 1);
            alert("选择文件类型错误！");
          }
        }

        this.$emit('onChange', this.fileTemp);
        this.$refs.import.uploadFiles.shift();  //文件刷新
        //上传
        /*that.form = {
          file: file,
          ac01id: '1'
        }*/

        let formfile = {
          ac01id: '1',
          document: this.fileTemp,
        };

        let callBackFun = function (data) {
          //alert("选择文件类型！");
          if (isNotNull(data.base64)) {
            $("#docid").attr("src", "data:image/jpeg;base64,"+data.base64);
            that.form.base64 = data.base64;
          }
        };

        this.$eframe.submitTargetFileForm({
          url: "/szybgzdtgl/baseinfo/pers/persinfomgt.action",
          form: formfile,
          actionFunName: "uploadfile",
          succFun: callBackFun,
          files: this.fileTemp
        });


        // if ((fileType == 'xlsx') || (fileType == 'xls')) {
        //   //回调执行上传方法
        //this.$emit('onChange', this.fileTemp);
        //this.$refs.import.uploadFiles.shift()
        // } else {
        //   this.compFileList=[];
        //   this.$message({
        //     type: 'warning',
        //     message: '附件格式错误，请删除后重新上传！'
        //   })
        // }


      } else {
        this.compFileList = [];
        this.$message({
          type: 'warning',
          message: '请上传附件！'
        })
      }

    }

  }
}
</script>

<style lang="scss" scoped>


</style>