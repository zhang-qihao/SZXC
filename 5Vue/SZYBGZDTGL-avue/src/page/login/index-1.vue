<template>
  <div class="login-box">
    <div class="login-title">
      {{ topTitle }}
    </div>
    <div class="login-content">
      <div class="login-content-title">欢迎登录</div>
      <el-form class="login-form"
               status-icon
               :rules="loginRules"
               ref="loginForm"
               :model="loginForm"
               label-width="0">
        <el-form-item prop="username">
          <el-input size="small"
                    v-model="loginForm.username"
                    auto-complete="off"
                    placeholder="请输入您的用户名"
                    @blur="checkUserName()">
          </el-input><!--@keyup.enter.native="handleLogin"-->
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="small"
                    :type="passwordType"
                    v-model="loginForm.password"
                    auto-complete="off"
                    placeholder="请输入您的密码">
            <i class="el-icon-view el-input__icon"
               slot="suffix"
               @click="showPassword"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-row :span="24">
            <el-col :span="16">
              <el-input size="small"
                        :maxlength="code.len"
                        v-model="loginForm.code"
                        auto-complete="off"
                        placeholder="请输入验证码">
              </el-input>
            </el-col>
            <el-col :span="5">
              <div class="login-code">
            <span class="login-code-img"
                  v-if="code.type == 'text'">{{ code.value }}</span>
              </div>
            </el-col>
            <el-col :span="3" style="display: flex;align-items: center;justify-content: center">
              <i style="font-size: 25px;cursor: pointer;color: #bbb" @click="refreshCode" class="el-icon-refresh"></i>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item prop="fixmedins_id" v-show="isAdmin">
          <el-select v-model="loginForm.fixmedins_id"
                     placeholder="请选择机构"
                     @change="changeFixmedins()" filterable>
            <el-option
                v-for="item in fixmedinsList"
                :key="item.code"
                :label="item.name"
                :value="item.code">
            </el-option>
          </el-select>
        </el-form-item>
        <div class="login-submit">
          <el-button
              v-prevent-re-click
              type="primary"
              size="small"
              @click.native.prevent="handleLogin"
          >安全登录
          </el-button>
        </div>
        <div v-show="isNull(loginForm.fixmedins_id) && !isAdmin" style="width: 350px;display: flex;margin:10px auto 0">
          <div
              style="width:70%;color: #ffffff;text-align: left;font-weight: 400;display: inline-block;font-size:14px;cursor: pointer;text-decoration:underline"
              @click="showInfo">
            本电脑还未入驻平台？点此注册
          </div>
          <div
              style="width:30%;color: #ffffff;text-align: right;font-weight: 400;display: inline-block;font-size:14px;cursor: pointer;text-decoration:underline"
              @click="linkAnQuan">
            安全码申请

          </div>
        </div>

        <div v-show="!isNull(loginForm.fixmedins_id) && !isAdmin">
          <div
              style="margin-top: 10px;width: 100%;justify-content: space-between;color: #ffffff;font-weight: 400;text-align: center;font-size:14px;cursor: pointer;text-decoration:underline"
              @click="linkAnQuan">
            安全码申请
          </div>
        </div>

        <!--        <div class="login-tool" style="text-align: center;font-size:16px" @click="showInfo">-->
        <!--          本电脑还未注入平台？点此注册-->
        <!--        </div>-->
      </el-form>
    </div>
    <el-dialog class="register-dialog" :visible.sync="dialogVisible" :close-on-click-modal="false" width="450px"
               :before-close="handleClose" center>
      <div slot="title">
        <div>本电脑使用医保收费系统申请</div>
        <div style="text-align: center">定点医疗机构</div>
      </div>
      <el-container style="height: 100%">
        <el-main style="height: 100%;overflow: auto;">
          <div class="main-content">
            <base-block rounded>

              <div v-show="!registerflag&&(status=='0'||status=='3')"
                   style="margin: 0 -30px 15px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 60px">
                <div style="display: flex;justify-content: space-between">
                  <div style="flex-shrink: 0;">审核状态：{{ statusjson[status] }}</div>
                  <div style="flex-shrink: 0;" v-show="status!='0'">审核时间：{{ substring(examinetime, 0, 10) }}</div>
                </div>
                <div style="word-break: break-all" v-show="status=='3'">原因：{{ remark }}</div>
              </div>

              <div v-show="registerflag&&status=='2'"
                   style="margin: 0 -30px 15px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 60px">
                <div style="display: flex;justify-content: space-between">
                  <div style="flex-shrink: 0;">审核状态：{{ statusjson[status] }}</div>
                  <div style="flex-shrink: 0;" v-show="status!='0'">审核时间：{{ substring(examinetime, 0, 10) }}</div>
                </div>
              </div>

              <el-form ref="registerform" :model="registerform" label-position="right" label-width="100px"
                       v-show="registerform.fix_flag=='1'">
                <el-row>
                  <el-col :span="24" style="margin-bottom: 5px">
                    <ef-text label="定点机构编码" name="fixmedins_code" v-model="registerform.fixmedins_code" require="true"
                             :readonly="registerflag" :maxlength=12 :minlength=12 :checkFlag="true"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-show="!registerflag"
                         style="margin-left: 100px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      请填写12位国家医药机构编码（提示：第1位是字母，后11位是数字）
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="margin-bottom:5px">
                    <ef-text label="机构名称" name="fixmedins_name" v-model="registerform.fixmedins_name" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-show="!registerflag"
                         style="margin-left: 100px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      请填写准确的机构名称全称
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="申请人" name="username" v-model="registerform.username" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="联系电话" name="tel" v-model="registerform.tel" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="margin-bottom: 5px">
                    <ef-text label="本电脑IP地址" name="ip" v-model="registerform.ip" readonly="true"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-show="!registerflag"
                         style="margin-left: 100px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      本机IP自动获取，不可更改
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="margin-bottom: 5px">
                    <ef-text label="本电脑MAC地址" name="mac" v-model="registerform.mac" readonly="true"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-show="!registerflag"
                         style="margin-left: 100px;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      本机MAC地址自动获取，不可更改
                    </div>
                  </el-col>
                </el-row>
                <div v-show="!registerflag" class="more-button" style="justify-content: center;margin-top: 20px">
                  <el-button v-prevent-re-click style="width: 100px;" icon="el-icon-folder-checked" size="mini" type="primary"
                             @click="register()">提交
                  </el-button>
                </div>
              </el-form>

              <el-form @submit.native.prevent ref="registerform1" :model="registerform1" label-position="right"
                       label-width="100px" v-show="registerform.fix_flag=='0'">
                <el-row>
                  <el-col :span="24">
                    <ef-text label="社会信用编码" name="uscc" v-model="registerform1.uscc" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="机构名称" name="fixmedins_name" v-model="registerform1.fixmedins_name" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="申请人" name="username" v-model="registerform1.username" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="联系电话" name="tel" v-model="registerform1.tel" require="true"
                             :readonly="registerflag"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="本电脑IP地址" name="ip" v-model="registerform1.ip" readonly="true"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-text label="本电脑MAC地址" name="mac" v-model="registerform1.mac" readonly="true"/>
                  </el-col>
                </el-row>
                <div v-show="!registerflag" class="more-button" style="justify-content: center;margin-top: 20px">
                  <el-button v-prevent-re-click style="width: 100px;" icon="el-icon-folder-checked" size="mini" type="primary"
                             @click="register()">提交
                  </el-button>
                </div>
              </el-form>
            </base-block>
          </div>
        </el-main>
      </el-container>
    </el-dialog>

    <el-dialog class="register-dialog" :visible.sync="cainfoVisible" :close-on-click-modal="false" width="620px"
               :before-close="handleCainfoClose" center>
      <div slot="title">
        <div style="font-size: 20px">机构安全码绑定</div>
      </div>
      <el-container style="height: 100%">
        <el-main style="height: 100%;overflow: auto;">
          <div class="main-content">
            <base-block rounded>
              <el-form ref="caform" :model="caform" label-position="right" label-width="50px">
                <el-row>
                  <el-col :span="24" style="margin-bottom: 5px">
                    <div v-show="tipflag1" style="text-align: center;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      定点机构尚未绑定安全码，请立即绑定，否则将无法正常登录并使用系统。&nbsp;&nbsp;&nbsp;&nbsp;
                      <span style="font-size:14px;text-decoration:underline" @click="linkAnQuan">
                        安全码申请
                      </span>
                    </div>
                    <div v-show="tipflag2"
                         style="text-align: center;background: #fffce7;color: #fdae17;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      您的原机构安全码（原安全码信息如下框内所示），现无法校验通过，请确认机构安全码是否有变更并重新进行绑定，否则将无法正常登录并使用系统。
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24" style="margin-bottom: 5px">
                    <ef-textarea label="安全码" name="cainfo" v-model="caform.cainfo" require="true" rows="5"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-show :show-type="showType" label="AK" name="api_access_key" v-model="caform.api_access_key"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <ef-show :show-type="showType" label="SK" name="api_secret_key" v-model="caform.api_secret_key"/>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-show="errorflag1"
                         style="text-align: center;background: #fff;color: #ec0b12;font-weight: 500;line-height: 18px;word-break: break-all;padding: 8px 17px">
                      <i class="el-icon-warning" style="margin-right:5px;"></i>机构安全码校验未通过，请修改并确保安全码的正确性，并再次提交绑定
                    </div>
                  </el-col>
                </el-row>
                <div class="more-button" style="justify-content: center;margin-top: 20px">
                  <el-button v-prevent-re-click style="width: 100px;" icon="el-icon-folder-checked" size="mini" type="primary"
                             @click="confirm()">确认绑定
                  </el-button>
                </div>
              </el-form>
            </base-block>
          </div>
        </el-main>
      </el-container>
    </el-dialog>
  </div>
</template>
<script>
import {randomLenNum} from "@/util/util";
import {mapGetters} from "vuex";
import '@/styles/login.scss'
import {isMobile, isPhone} from "../../util/validate";
import {removeStore, setStore} from "../../util/store";
import ViewsRouter from '@/router/views/index'
import {resetRouter} from "@/router/router";
export default {
  name: "loginIndex",
  data() {
    const validateCode = (rule, value, callback) => {
      if (this.code.value != value) {
        this.loginForm.code = "";
        this.refreshCode();
        callback(new Error("请输入正确的验证码"));
      } else {
        callback();
      }
    };
    return {
      showType: ['type2', 'textRight'],
      topTitle: '',
      dialogVisible: false,
      cainfoVisible: false,
      reIp: "",//注册提示IP
      reMac: "",//注册提示Mac
      loginForm: {
        username: "",
        password: "",
        code: "",
        redomStr: "",
        ipAddr: "",
        macAddr: "",
        fixmedins_id: "",
        fixmedins_name: "",
      },
      code: {
        src: "",
        value: "",
        len: 4,
        type: "text"
      },

      loginRules: {
        username: [
          {required: true, message: "请输入用户名", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"},
          // {min: 6, message: "密码长度最少为6位", trigger: "blur"}
        ],
        code: [
          {required: true, message: "请输入验证码", trigger: "blur"},
          {min: 4, max: 4, message: "验证码长度为4位", trigger: "blur"},
          {required: true, trigger: "blur", validator: validateCode}
        ]
      },
      statusjson: {
        '0': '审核中',
        '2': '审核通过',
        '3': '审核未通过'
      },

      passwordType: "password",
      registerflag: false,
      registerform: {
        registerid: '',
        fixmedins_code: '',
        uscc: '',
        fixmedins_name: '',
        fix_flag: '1',
        username: '',
        tel: '',
        ip: '',
        mac: '',
      },
      registerform1: {
        registerid: '',
        fixmedins_code: '',
        uscc: '',
        fixmedins_name: '',
        fix_flag: '1',
        username: '',
        tel: '',
        ip: '',
        mac: '',
      },
      caform: {
        cainfo: '',
        api_access_key: '',
        api_secret_key: ''
      },
      tipflag1: false,
      tipflag2: false,
      errorflag1: false,
      status: '',
      remark: '',
      examinetime: '',
      isregister: false,
      path: '',
      isAdmin: false,//是否管理员-可选机构
      fixmedinsList: [],//机构字典
    };
  },
  created() {
    removeStore({name: 'LoginInfo'})
    this.refreshCode();
    //获取IP地址、MAC地址
    this.getLocalIP();
  },
  mounted() {
  },
  computed: {
    ...mapGetters(["tagWel", "website", "userInfo",])
  },
  props: [],
  methods: {
    linkAnQuan() {
      window.open("http://10.58.10.3:32001/#/signup", 'anquanregister', 'height=700,width=1200')
      // window.location.href = "http://10.58.10.3:32001/#/signup"
    },

    register() {
      let that = this;
      if (that.registerform.fix_flag == '1') { //定点
        that.$refs['registerform'].validate((valid) => {
          if (valid) {
            if (that.registerform.fixmedins_code.trim().length < 12) {
              that.$eframe.alertError("请填写12位国家医药机构编码（提示：第1位是字母，后11位是数字）");
              return;
            }
            if (!(isMobile(that.registerform.tel) || isPhone(that.registerform.tel))) {
              that.$eframe.alertError("请输入正确的联系电话，方便工作人员联系");
              return;
            }
            that.registerform1.uscc = ''
            that.save();
          } else {
            that.$eframe.alertError("表单填写不完整");
            return false;
          }
        });
      } else {
        that.$refs['registerform1'].validate((valid) => {
          if (valid) {
            if (!(isMobile(that.registerform1.tel) || isPhone(that.registerform1.tel))) {
              that.$eframe.alertError("请输入正确的联系电话，方便工作人员联系");
              return;
            }
            that.registerform1.fixmedins_code = ''
            that.registerform = that.registerform1;
            that.save();
          } else {
            that.$eframe.alertError("表单填写不完整");
            return false;
          }
        });
      }
    },
    save() {
      let that = this;
      let loc_form = {
        registerid: that.registerform.registerid,
        fixmedins_code: that.registerform.fixmedins_code,
        uscc: that.registerform.uscc,
        fixmedins_name: that.registerform.fixmedins_name,
        fix_flag: that.registerform.fix_flag,
        username: that.registerform.username,
        tel: that.registerform.tel,
        ip: that.registerform.ip,
        mac: that.registerform.mac,
        examinestatus: '0'
      };
      let succFun = function (data) {
        that.$eframe.alertMessage("电脑入驻平台申请成功，请等待审核~~~审核通过后即可使用账号密码进行登录");
        that.dialogVisible = false
      };
      let failFun = function (data) {

      };
      this.$eframe.submitTargetForm({
        url: '/userloginmgmt/login.action',
        form: loc_form,
        actionFunName: "saveRegister",
        succFun: succFun,
        failFun: failFun,
      });
    },
    substring(data, start, end) {
      if (this.$eframe.isNotNull(data)) {
        return data.substring(start, end)
      }
    },
    loadRegisterInfo() {
      let that = this
      let loc_form = {
        ip: that.registerform.ip,
        mac: that.registerform.mac
      }
      //判断审核状态，0-待审核，2-审核通过，3-审核不通过
      let succFun = function (data) {
        if (data.registerid != null) {
          that.registerform.fixmedins_code = data.fixmedins_code
          that.registerform.uscc = data.uscc
          that.registerform.fixmedins_name = data.fixmedins_name
          that.registerform.fix_flag = data.fix_flag
          that.registerform.username = data.username
          that.registerform.tel = data.tel
          that.registerform.ip = data.ip
          that.registerform.mac = data.mac
          that.examinestatus = data.examinestatus
          if (that.examinestatus == '0') {
            that.registerflag = false
            that.registerform.registerid = data.registerid
          } else if (that.examinestatus == '2') {
            that.registerflag = true
          } else if (that.examinestatus == '3') {
            that.registerflag = false
          }
          // that.registerflag = true
          that.status = data.examinestatus
          that.remark = data.remark
          that.examinetime = data.examinetime
          that.registerform1 = that.registerform
        }
      };
      let failFun = function (data) {

      };
      this.$eframe.submitTargetForm({
        url: '/userloginmgmt/login.action',
        form: loc_form,
        actionFunName: "loadRegister",
        succFun: succFun,
        failFun: failFun,
      });
    },

    showInfo() {
      this.dialogVisible = true;
      this.loadRegisterInfo();
    },
    handleClose() {
      this.dialogVisible = false
    },
    // 获取IP地址
    getLocalIP() {
      let that = this;
      let callBackFun = function (data) {
        console.log("IP：" + JSON.stringify(data));

        let list = [];
        for (let devName in data) {
          let iface = data[devName];
          for (let i = 0; i < iface.length; i++) {
            let alias = iface[i];
            if (alias.family === 'IPv4' && alias.address !== '127.0.0.1' && !alias.internal) {
              //TODO 获取第一条IP地址
              if (that.isNull(that.registerform.ip)) {

                that.registerform.ip = alias.address;//IP
                that.registerform.mac = alias.mac;//MAC
                that.registerform1 = that.registerform
              }

              let item = {ip_addr: alias.address, mac_adrr: alias.mac};
              list.push(item);
              // if (that.getFixmedinsByIp()) { //获取机构
              //   break;
              // }
            }
          }
        }
        that.getFixmedinOfMacs(list);
        // if(that.isNull(that.topTitle)){
        //   that.topTitle='简易HIS平台';
        // }
      }
      this.$eframe.callSysFun({
        funName: 'getLocalAddress',
        params: {},
        callBackFun: callBackFun,
      });
    },
    isNull(data) {
      return this.$eframe.isNull(data);
    },
    //根据IP+MAC获取机构信息
    getFixmedinsByIp() {
      let that = this;
      let localForm = {
        ipAddr: this.loginForm.ipAddr,
        macAddr: this.loginForm.macAddr,
      };
      let succFun = function (data) {
        if (data != null && !that.isNull(data.fixmedins_id)) {
          that.topTitle = data.fixmedins_name;//机构名称
          that.loginForm.fixmedins_id = data.fixmedins_id;//机构ID
          that.loginForm.fixmedins_name = data.fixmedins_name;//机构名称
          // if(data.fixmedins_type==="2"){
          //   that.website.title="云药店";
          // }else{
          //   that.website.title="云诊所";
          // }


          // if(isNull(that.loginForm.fixmedins_id)){
          // if(that.loginForm.fixmedins_id){
          //   that.loadRegisterInfo();  //获取申请信息
          // }
          return true;
        } else {
          return false;
        }
      };
      let failFun = function (data) {
        return false;
      };
      this.$eframe.submitTargetForm({
        url: '/userloginmgmt/login.action',
        form: localForm,
        actionFunName: "getFixmedinsByIP",
        succFun: succFun,
        failFun: failFun,
      });
    },
    //根据IP+MAC获取机构信息
    getFixmedinOfMacs(list) {
      let that = this;
      let exeVersion = '';
      if (window.getExeVersion) {//取版本号
        exeVersion = window.getExeVersion();
      }
      let localForm = {
        fixmedinsPcBDOList: list,
        exeVersion:exeVersion,
      };
      let succFun = function (data) {
        if (data != null && that.$eframe.isNotNull(data.fixmedins_id)) {
          that.topTitle = data.fixmedins_name;//机构名称
          that.loginForm.fixmedins_id = data.fixmedins_id;//机构ID
          that.loginForm.fixmedins_code = data.fixmedins_code;//机构编码
          that.loginForm.fixmedins_name = data.fixmedins_name;//机构名称
          that.loginForm.fix_blng_admdvs = data.fix_blng_admdvs;
          that.loginForm.ipAddr = data.ip_addr;//IP
          that.loginForm.macAddr = data.mac_adrr;//MAC
          that.caform.cainfo = data.cainfo;//安全码
          let loginInfo = {
            fixmedins_id: data.fixmedins_id,//机构ID
            ipAddr: data.ip_addr,//IP
            macAddr: data.mac_adrr,//MAC
          }
          setStore({name: 'LoginInfo', content: loginInfo})

          //判断安全码
          if (data.fix_flag == '1' && that.isNull(data.cainfo)) {
            that.cainfoVisible = true;
            that.tipflag1 = true;
            that.errorflag1 = false;
            that.caform.api_access_key = data.api_access_key;
            that.caform.api_secret_key = data.api_secret_key;
          }
        } else { //没有机构
          let loginInfo = {
            ipAddr: that.registerform.ip,//IP
            macAddr: that.registerform.mac,//MAC
          }
          setStore({name: 'LoginInfo', content: loginInfo})
        }
      };
      let failFun = function (data) {
      };
      this.$eframe.submitTargetForm({
        url: '/userloginmgmt/login.action',
        form: localForm,
        actionFunName: "getFixmedinOfMacs",
        succFun: succFun,
        failFun: failFun,
      });
    },

    refreshCode() {
      this.loginForm.redomStr = randomLenNum(this.code.len, true);
      this.code.type == "text" ? (this.code.value = randomLenNum(this.code.len)) : (this.code.src = `${this.codeUrl}/${this.loginForm.redomStr}`);
      // this.loginForm.code = this.code.value;
    },
    showPassword() {
      this.passwordType == "" ? (this.passwordType = "password") : (this.passwordType = "");
    },
    handleLogin() {
      let that = this;
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          // if (that.isAdmin && that.isNull(that.loginForm.fixmedins_id)) {
          //   that.$eframe.alertError("管理员账号，请选择本次登录机构。");
          //   return;
          // }
          // if (that.isNull(that.loginForm.fixmedins_id) && that.loginForm.username != 'U001' && that.loginForm.username != 'U002') {
          //   that.$eframe.alertError("本设备未入驻平台，请点击下方按钮进行注册。");
          //   return;
          // }
          console.log("test");
          that.$store.dispatch("LoginByUsername", that.loginForm).then((data) => {
            that.path = that.tagWel.value;
            that.$router.$avueRouter.formatviewsRoutes([...ViewsRouter],true)

            that.$router.push({
              path: '/wel/index',
              query:{
                topId:that.tagWel.topId
              }
            });
          });
        }
      });
    },
    handleCainfoClose() {
      this.cainfoVisible = false;
    },
    //提交安全码
    confirm() {
      let that = this;
      that.$refs['caform'].validate((valid) => {
        if (valid) {
          if (that.caform.cainfo.length < 100) {
            that.$eframe.alertError("安全码不正确");
            return;
          }
          let temp = {
            fixmedins_id: that.loginForm.fixmedins_id,
            fixmedins_code: that.loginForm.fixmedins_code,
            fixmedins_name: that.loginForm.fixmedins_name,
            ip_addr: that.loginForm.ipAddr,
            mac_adrr: that.loginForm.macAddr,
            fix_blng_admdvs: that.loginForm.fix_blng_admdvs,
            uact: that.loginForm.username,
            cainfo: that.caform.cainfo.trim(),
            api_access_key: that.caform.api_access_key,
            api_secret_key: that.caform.api_secret_key,
          };

          let callbackFun = function (data) {
            that.cainfoVisible = false;
            that.loginForm.cainfo = data.cainfo;
          };
          this.$eframe.submitTargetForm({
            url: '/userloginmgmt/login.action',
            form: temp,
            actionFunName: "checkCaInfo",
            succFun: callbackFun,
            failFun: null
          });
        } else {
          that.$eframe.alertError("表单填写不完整");
          return false;
        }
      });
    },

    checkUserName() {
      let that = this;
      if (that.isNull(that.topTitle) && that.loginForm.username === 'Admin_2022.') {//固定账号可选择机构
        if (that.$eframe.isListEmpty(that.fixmedinsList)) {
          //获取机构LIST
          let succFun = function (data) {
            that.fixmedinsList = data;
          };
          let failFun = function (data) {
          };
          this.$eframe.submitTargetForm({
            url: '/userloginmgmt/login.action',
            form: {},
            actionFunName: "loadFixmedinsDic",
            succFun: succFun,
            failFun: failFun,
          });
          that.isAdmin = true;
        }
      } else {
        that.isAdmin = false;
      }
    },
    //机构改变
    changeFixmedins() {
      if (this.loginForm.fixmedins_id) {
        for (let i=0,len=this.fixmedinsList.length;i<len;i++) {
          let item = this.fixmedinsList[i];
          if (item.code === this.loginForm.fixmedins_id) {
            this.loginForm.fixmedins_name = item.name;
            this.loginForm.macAddr = item.exp;
            if (this.isNull(item.exp1)) {
              this.loginForm.ipAddr = this.registerform.ip;
            } else {
              this.loginForm.ipAddr = item.exp1;
            }
            let loginInfo = {
              fixmedins_id: this.loginForm.fixmedins_id,//机构ID
              ipAddr: this.loginForm.ipAddr,//IP
              macAddr: this.loginForm.macAddr,//MAC
            }
            setStore({name: 'LoginInfo', content: loginInfo})
            break;
          }
        }
      } else {
        this.loginForm.fixmedins_name = "";
        this.loginForm.macAddr = "";
        this.loginForm.ipAddr = "";
        removeStore({name: 'LoginInfo'});
      }
    }
  }
};
</script>

<style lang="scss" scoped>
::v-deep .register-dialog .el-dialog__header {
  height: 66px;
  padding: 10px 24px;
}

::v-deep .el-select {
  height: 100%;

  .el-input {
    height: 100%;

    .el-input__inner {
      height: 100%;
      font-size: 16px;
    }
  }
}

</style>