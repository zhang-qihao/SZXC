<template>
  <div>
    <basic-container>
      <div class="title">表单校验</div>
      <el-form ref="form" :model="form" :rules="rules" label-position="top">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="测试：" prop="mail">
              <el-input v-model="form.mail" placeholder="测试"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户编号：" prop="userId" :rules="[ { required: true, message: '请输入用户编号', trigger: 'blur' }]">
              <el-input v-model="form.userId" placeholder="用户编号，唯一"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户名称1：" prop="userName"
                          :rules="[ { required: true, message: '请输入用户名称', trigger: 'blur' }]">
              <el-input v-model="form.userName" placeholder="用户名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="上级用户ID：">
              <el-input v-model="form.supUserId" placeholder="挂到上级用户编号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="语种：" placeholder="请选择默认语种">
              <el-select v-model="form.language">
                <el-option
                    v-for="item in languageOptions"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="密码：" prop="password"
                          :rules="[ { required: false, message: '请输入用户密码', trigger: 'blur' }, {  min: 8, max: 20, message: '长度在 8 到 20 个字符', trigger: 'blur'}]">
              <el-input type="password" v-model="form.password"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="显示顺序：">
              <!--              <el-input-number v-model="form.userOrder" controls-position="right" :min="0" ></el-input-number>-->
              <el-input type="number" v-model="form.userOrder" :min="0"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户类型：">
              <el-select v-model="form.isAdmin">
                <el-option
                    v-for="item in isAdminOptions"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="用户有效期：">
              <el-date-picker
                  v-model="form.cancelDate"
                  align="left"
                  type="date"
                  placeholder="选择日期"
                  :picker-options="pickerOptions"
                  value-format="yyyy-MM-dd" prop="mail">
              </el-date-picker>
              <!--              <el-input v-model="form.cancelDate" ></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="注销：">
              <el-switch
                  v-model="form.isCancel">
              </el-switch>
              <!--              <el-input v-model="form.isCancel" ></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注：" prop="desc">
              <el-input type="textarea" v-model="form.post" maxlength="500"
                        show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-button size="mini" @click="submitForm">表单校验</el-button>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            {{ form }}
          </el-col>
        </el-row>
      </el-form>

      <el-form ref="form2" :model="form2" :rules="rules2" label-position="top" enctype="multipart/form-data">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="手机：" prop="tel">
              <el-input v-model="form2.tel" placeholder="手机号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <ef-upload v-model="form2.efFiles" name="efFiles" limit="4" list-type="card" :accept="accept" placeholder="文件类型支持.png .jpg"></ef-upload>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-button @click="checkFile">checkFile</el-button>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-button @click="submitFileForm">submitFileForm</el-button>
          </el-col>
        </el-row>
      </el-form>

    </basic-container>

  </div>
</template>

<script type="text/javascript">
// import '/src/styles/ext-element.scss'
import {BaseCtrl} from '/src/util/eframe'
import EfUpload from "../../components/ef-element-comps/EfUpload";


export default {
  components: {EfUpload},
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  name: "forms",

  data() {
    return {
      accept:["png","txt"],
      user: "",
      //日期快捷选择器
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        },
        shortcuts: [{
          text: '一周后',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }, {
          text: '一个月后',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() + 3600 * 1000 * 24 * 28);
            picker.$emit('pick', date);
          }
        }]
      },
      //语言选项
      languageOptions: [{
        code: '1',
        name: '中文（默认）'
      }, {
        code: '2',
        name: '英文'
      }],
      //用户类型选项
      isAdminOptions: [{
        code: '1',
        name: '超级管理员'
      }, {
        code: '2',
        name: '普通用户'
      }, {
        code: '3',
        name: '服务用户'
      },],
      //表单
      form: {
        test: "",
        userId: "",
        userName: "",
        supUserId: "",
        language: "",
        password: "",
        userOrder: 0,
        isAdmin: "1",
        cancelDate: "",
        isCancel: "",
        post: "",
      },
      rules: {
        mail: [{required: true, message: '请输入', trigger: 'blur'}],
        phone: [{min: 8, max: 20, message: '长度在 8 到 20 个字符', trigger: 'blur'}]
      },
      form2: {
        tel: "",
        efFiles: [],
      },
      rules2: {},
    };
  },
  // components: {EFText},
  created() {

  },
  methods: {

    submitForm() {
      let that = this;
      //校验返回方法
      let callbackfun = function (boolean, object) {
        alert("表单校验回调：" + boolean + ":::" + object);
        console.log(object)
        console.log(that.form);
      };
      this.$refs["form"].validate(callbackfun);
    },
    checkFile() {
      console.log("checkFile:");
      for (let i = 0; i < this.form2.efFiles.length; i++) {
        console.log(this.form2.efFiles[i].raw);
      }
    },
    submitFileForm() {
      let fileForm = {
        saveType: "1",
        documentName: "1234",
        keyNo: "1",
        keyType: "3",
        keyName: "角色",
        rootId: "all",
        frameControlOption: "new",
      }

      var callbackFun = function () {

      };
      this.$eframe.submitTargetFileForm({
        url: "/frame/systemmanagement/documentmanagement/documentmanagement.action",
        form: fileForm,
        actionFunName: "saveMultiDocuments",
        files: this.form2.efFiles,
        succFun: callbackFun,
        failFun: null
      });

    }
  },
};
</script>

<style lang="scss" scoped>

</style>
