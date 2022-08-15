<template>
  <div class="content-box" v-cloak>
    <div class="slider-content">
      <div class="nav-bar">
        <div class="nav-bar-inner">
          <div class="pull-left">
            <el-button-group>
              <el-button type="success" size="mini" icon="el-icon-folder-add">保存</el-button>
              <el-button type="primary" size="mini" icon="el-icon-printer">打印</el-button>
              <el-button type="primary" size="mini" icon="el-icon-refresh">刷新</el-button>
            </el-button-group>
          </div>
          <div class="pull-right selectTab">
            <div class="tabName" @click="changeSelectTab(0)" :class="{'active':selectTab == 0}">用户维护</div>
            <div class="tabName" @click="changeSelectTab(1)" :class="{'active':selectTab == 1}">用户权限</div>
          </div>
        </div>
        <div style="height: 37px"></div>
      </div>
      <div class="content-inner" v-if="selectTab == 0">
        <div class="card">
          <div class="card-title">
            <i class="el-icon-s-custom"></i>
            <span class="img-title">用户信息</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="用户ID" v-model="form.userId" name="userId"
                           require="true" placeholder="用户ID"/>
                </el-col>
                <el-col :span="8">
                  <ef-text label="用户名称" v-model="form.userName" :maxlength=20 :minlength=5 name="userName"
                           require="true" placeholder="用户名称"/>
                </el-col>
                <el-col :span="8">
                  <ef-mail label="上级用户ID" v-model="form.supUserId" name="supUserId"
                           placeholder="挂接到上级用户ID"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select label="语种" name="language" v-model="form.language" :list="languageList"/>
                </el-col>
                <el-col :span="8">
                  <ef-password label="密码" v-model="form.password" name="password" placeholder="请输入密码" require="true"/>
                </el-col>
                <el-col :span="8">
                  <ef-number label="显示顺序" v-model="form.userOrder" name="userOrder" require="true" decimals="0"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select
                      label="用户类型"
                      name="isAdmin"
                      v-model="form.isAdmin"
                      :emptyOption="true"
                      dictionaryNo="D_Frame_UserType"
                  />
                </el-col>
                <el-col :span="8">
                  <ef-date label="用户有效期" v-model="form.cancelDate" name="cancelDate" format="yyyy-MM-dd"/>
                </el-col>
                <el-col :span="8">
                  <ef-switch label="注销" v-model="form.isCancel" name="switch1"/>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <ef-textarea label="备注" v-model="form.post" :maxlength=500 name="post"
                           placeholder="用户职务与其他说明信息"/>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <div class="card-title">
            <span>用户标识</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <div class="input-content">
                <el-row :gutter="20">
                  <el-col :span="8">
                    <ef-text label="考勤钟ID" v-model="form.checkId" name="user3"
                             placeholder="考勤系统对照ID"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="CA" v-model="form.ca" name="ca"
                             placeholder="CA系统对照ID"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="PIN码" v-model="form.pin" name="pin"/>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <ef-text label="用户绑定IP" v-model="form.ipAddr" name="ipAddr"
                             placeholder="例如:192.168.100.126"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="用户绑定网卡" v-model="form.macAddr" name="macAddr"
                             placeholder="例如:00-25-64-76-BD-40"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="三方系统扩展信息" v-model="form.userInfos" name="userInfos"
                             placeholder="系统号1~~参数包1~~|~~|系统号2~~参数包2"/>
                  </el-col>
                </el-row>
              </div>
            </el-form>
          </div>
          <div class="card-title card-title-flex">
            <div>虚拟用户</div>
            <div class="title-right">
              <el-form ref="form" :model="form">
                <ef-switch v-model="form.isUserSql" name="isUserSql" field-value="1"/>
              </el-form>
              <div>是否虚拟用户</div>
            </div>
          </div>
          <div class="card-content">
            <div v-if="form.isUserSql == '1'">
              <el-form ref="form" :model="form" label-position="top" label-width="auto">
                <div class="input-content">
                  <el-row>
                    <el-col :span="24">
                      <ef-textarea :rows="3" label="虚拟用户Sql" v-model="form.userSql" maxlength="500" name="userSql"
                                   placeholder="虚拟用户Sql"/>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-title">
            <i class="el-icon-phone"></i>
            <span class="img-title">联系方式</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-tel label="电话[座机]" v-model="form.tel" name="tel" placeholder="办公电话"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="电话[手机]" v-model="form.tel1" name="tel1" placeholder="行动电话"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="电话[内线]" v-model="form.tel2" name="tel2" placeholder="内线电话"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="传真" v-model="form.fax" :maxlength=20 :minlength=5 name="fax"
                           placeholder="传真"/>
                </el-col>
                <el-col :span="16">
                  <ef-text label="电子邮箱" v-model="form.mailAddress" name="mailAddress" placeholder="例如:mail@163.com"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="邮政编码" v-model="form.postmark" :maxlength=20 :minlength=5 name="postmark"
                           placeholder="例如:215000"/>
                </el-col>
                <el-col :span="16">
                  <ef-text label="详细地址" v-model="form.address" name="address"/>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
        <div class="card">
          <div class="card-title">
            <i class="el-icon-s-fold"></i>
            <span class="img-title">扩展信息</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select label="字体偏好" v-model="form.fontsizePreference" name="fontsizePreference"
                             :list="fontsizePreferenceList" placeholder="字体偏好"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展2" v-model="form.userId2" name="userId2"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展3" v-model="form.userId3" name="userId3"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-tel label="用户扩展4" v-model="form.userId4" name="userId4"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展5" v-model="form.userId5" name="userId5"/>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
        <div class="card">
          <div class="card-title">
            <i class="el-icon-office-building"></i>
            <span class="img-title">我的部门</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select label="字体偏好" v-model="form.fontsizePreference" name="fontsizePreference"
                             :list="fontsizePreferenceList" placeholder="字体偏好"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展2" v-model="form.userId2" name="userId2"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展3" v-model="form.userId3" name="userId3"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-tel label="用户扩展4" v-model="form.userId4" name="userId4"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="用户扩展5" v-model="form.userId5" name="userId5"/>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>

      </div>
      <div class="content-inner" v-if="selectTab == 1">
        <div class="card">
          <div class="card-title">
            <i class="el-icon-s-custom"></i>
            <span class="img-title">我的用户组</span>
          </div>
          <div class="card-content">
            <ef-page-grid  ref="groupGrid" queryNo="Frame_UserGroup_003" pageSize="20" queryWindow="1"  style="height:230px;" />
          </div>
        </div>
        <div class="card">
          <div class="card-title">
            <i class="el-icon-s-custom"></i>
            <span class="img-title">我的角色</span>
          </div>
          <div class="card-content">
            <ef-page-grid  ref="roleAssignGrid" queryNo="Frame_Role_003" pageSize="20" queryWindow="1"  style="height:230px;" />
          </div>
        </div>
        <div class="card">
          <div class="card-title">
            <i class="el-icon-s-custom"></i>
            <span class="img-title">我的系统</span>
          </div>
          <div class="card-content">
            <ef-page-grid  ref="programAssignGrid" queryNo="Frame_Program_002" pageSize="20" queryWindow="1"  style="height:230px;" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
import '@/views/example/css/formStyle.css'
export default {
  name: "usermanagement",
  mixins: [BaseCtrl],
  props: {
    params: {
      default: ''
    },
  },
  data() {
    return {
      languageList: [{'code': '0', 'name': '中文（默认）'}, {'code': '1', 'name': '英文'}],
      fontsizePreferenceList: [{'code': 'small', 'name': '小号字体(12号)'}, {
        'code': 'middle',
        'name': '中号字体(14号)'
      }, {'code': 'large', 'name': '大号字体(16号)'}],
      selectTab: 0,
      form: {
        frameControlOption:"new",
        language:"0",
        isAdmin:"1",
        userName:"",
      },
    }
  },
  created() {
    console.log("user::");

    if(this.params){
      if(this.params.userId){
        this.form.userId=this.params.userId;
        this.getUser(this.form.userId);
      }
      if(this.params.frameControlOption){
        this.form.frameControlOption=this.params.frameControlOption;
      }
    }else  if(typeof (this.$route.query.params)!='undefined'){
      let params=this.$route.query.params;
      if(params.userId){
        this.form.userId=params.userId;
        this.getUser(this.form.userId);
      }
      if(params.frameControlOption){
        this.form.frameControlOption=params.frameControlOption;
      }


    }
  },
  watch:{
    form:{//深度监听，可监听到对象、数组的变化
      handler(val, oldVal){
        console.log("form");
        console.log(val);
      },
      deep:true //true 深度监听
    },
  },

  computed: {},
  mounted() {
    console.log("mounted::")
  },
  methods: {
    //Tab切换
    changeSelectTab(num) {
      let that=this;
      this.selectTab = num
      if(num=="1"){
        that.loadProgramAssignGrid();
      }
    },

    //获取用户
    getUser(userId){
      let that=this;
      let url = '/frame/systemmanagement/usermanagement/user/usermanagement.action';
      let actionFunName = "loadUser";
      let callbackFun = function (data) {
        console.log("获取用户成功：：");
        that.setUserInfo(data)
        // console.log(that.form);
      };
      let errorFun = function (data) {
        alert("获取用户失败！");
      };
      this.$eframe.submitTargetForm({
        url:url,
        form:this.form,
        actionFunName:actionFunName,
        succFun:callbackFun,
        failFun:errorFun
      });
    },
    setUserInfo(userInfo){
      this.form=this.deepClone(userInfo);
    },
    loadGroupGrid(){

    },
    loadRoleAssignGrid(){

    },
    loadProgramAssignGrid(){
      let userId=this.form.userId;
      this.$nextTick(() => {
        this.$refs['programAssignGrid'].setInitFun((queryGrid) => {
          let programOptions=queryGrid.getOptions();
          var programColumns  = programOptions.columns;
          programColumns.push(
              {   title: "配置",
                width:180,
                command: [{
				  name: "btn01",
				  template: "<a class='k-button k-grid-btn01'>菜单设置</a>",
                  click: function(e) {
                    console.log(e);
                  }
                },
                  {
					name: "btn02",
					template: "<a class='k-button k-grid-btn02'>接口设置</a>",
                    click: function(e) {

                    }
                  },
                ]
              }
          );
          queryGrid.setOptions(programOptions);
          queryGrid.doRefresh({  parameters:{ userId:userId } });
        });
      });
    },
  },

}
</script>

<style scoped>

</style>