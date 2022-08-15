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
        <div class="card" >
          <div class="card-title">
            <i class="el-icon-s-custom"></i>
            <span class="img-title">用户信息</span>
          </div>
          <div class="card-content">
            <el-form ref="form" :model="form" label-position="top" label-width="auto">
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="用户ID" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                           require="true" placeholder="user1" readonly="true"/>
                </el-col>
                <el-col :span="8">
                  <ef-text label="用户名称" v-model="form.user2" :maxlength=20 :minlength=5 name="user2"
                           require="true" placeholder="user2" />
                </el-col>
                <el-col :span="8">
                  <ef-text label="上级用户ID" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                   placeholder="挂接到上级用户ID"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select
                      label="语种"
                      name="test"
                      v-model="form.test"
                      :emptyOption="true"
                      dictionaryNo="D_Frame_UserType"
                  />
                </el-col>
                <el-col :span="8">
                  <ef-password label="密码2" v-model="form.password2" name="password2" placeholder="请输入密码"/>
                </el-col>
                <el-col :span="8">
                  <ef-number label="显示顺序" v-model="form.number1" name="number1" require="true"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-select
                      label="用户类型"
                      name="test"
                      v-model="form.test"
                      :emptyOption="true"
                      dictionaryNo="D_Frame_UserType"
                  />
                </el-col>
                <el-col :span="8">
                  <ef-date label="用户有效期" v-model="form.date1" name="date1" format="yyyy-MM-dd"/>
                </el-col>
                <el-col :span="8">
                  <ef-switch label="注销" v-model="form.switch1" name="switch1"/>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <ef-text label="备注" v-model="form.user3" :maxlength=20 :minlength=5 name="user2"
                           require="true" placeholder="用户职务与其他说明信息" />
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
                    <ef-text label="考勤钟ID" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                             placeholder="考勤系统对照ID"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="CA" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                             placeholder="CA系统对照ID"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="PIN码" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"/>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <ef-text label="用户绑定IP" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                             placeholder="例如:192.168.100.126"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="用户绑定网卡" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                             placeholder="例如:00-25-64-76-BD-40"/>
                  </el-col>
                  <el-col :span="8">
                    <ef-text label="三方系统扩展信息" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                             placeholder="系统号1~~参数包1~~|~~|系统号2~~参数包2"/>
                  </el-col>
                </el-row>
              </div>
            </el-form>
          </div>
          <div class="card-title card-title-flex">
            <div>虚拟用户</div>
            <div class="title-right">
              <el-form ref="form"  :model="form" label-width="auto">
                <ef-switch class="form-switch" v-model="form.switch3" name="switch3" field-value="1"/>
              </el-form>
              <div>是否虚拟用户</div>
            </div>
          </div>
          <div class="card-content">
            <div v-if="form.switch3 == '1'">
              <el-form ref="form" :model="form" label-position="top" label-width="auto">
                <div class="input-content">
                  <el-row>
                    <el-col :span="24">
                      <ef-textarea :rows="3" label="虚拟用户Sql" v-model="form.textarea" maxlength="50" name="textarea"
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
                  <ef-tel label="电话[座机]" v-model="form.telephone1" name="telephone1" placeholder="办公电话"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="电话[手机]" v-model="form.telephone1" name="telephone1" placeholder="行动电话"/>
                </el-col>
                <el-col :span="8">
                  <ef-tel label="电话[内线]" v-model="form.telephone1" name="telephone1" placeholder="内线电话"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="传真" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                           placeholder="传真"/>
                </el-col>
                <el-col :span="16">
                  <ef-text label="电子邮箱" v-model="form.user3" name="user3" placeholder="例如:mail@163.com"/>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <ef-text label="邮政编码" v-model="form.user3" :maxlength=20 :minlength=5 name="user3"
                           placeholder="例如:215000"/>
                </el-col>
                <el-col :span="16">
                  <ef-text label="详细地址" v-model="form.user3" name="user3"/>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
        <div class="card" >
          <div class="card-title card-title-flex">
            <div>
              <i class="el-icon-s-custom"></i>
              <span class="img-title">我的部门</span>
            </div>
            <div class="title-right">
              <el-form ref="form"  :model="form" label-width="auto" style="display: flex">
                  <el-button icon="el-icon-plus" size="mini" type="primary">添加</el-button>
              </el-form>
            </div>
          </div>
          <div class="card-content">

          </div>

        </div>
      </div>
      <div class="content-inner" v-if="selectTab == 1">
        <div class="card">
          <div class="card-title card-title-flex">
            <div>
              <i class="el-icon-s-custom"></i>
              <span class="img-title">我的用户组</span>
            </div>
            <div class="title-right">
              <el-form ref="form"  :model="form" label-width="auto" style="display: flex">
                <ef-text placeholder="请输入用户组编号、用户组名称进行查询" style="margin-right: 5px;width: 250px"></ef-text>
                <el-button-group>
                  <el-button icon="el-icon-search" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-refresh" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-plus" size="mini" type="primary">添加</el-button>
                  <el-button icon="el-icon-minus" size="mini" type="primary">移除</el-button>
                </el-button-group>
              </el-form>
            </div>
          </div>
          <div class="card-content" style="height: 246px">
            kendo表格
          </div>
        </div>
        <div class="card">
          <div class="card-title card-title-flex">
            <div>
              <i class="el-icon-s-custom"></i>
              <span class="img-title">我的角色</span>
            </div>
            <div class="title-right">
              <el-form ref="form"  :model="form" label-width="auto" style="display: flex">
                <ef-text placeholder="请输入用户组编号、用户组名称进行查询" style="margin-right: 5px;width: 250px"></ef-text>
                <el-button-group>
                  <el-button icon="el-icon-search" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-refresh" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-plus" size="mini" type="primary">添加</el-button>
                  <el-button icon="el-icon-minus" size="mini" type="primary">移除</el-button>
                </el-button-group>
              </el-form>
            </div>
          </div>
          <div class="card-content" style="height: 246px">
            kendo表格
          </div>
        </div>
        <div class="card">
          <div class="card-title card-title-flex">
            <div>
              <i class="el-icon-s-custom"></i>
              <span class="img-title">我的系统</span>
            </div>
            <div class="title-right">
              <el-form ref="form"  :model="form" label-width="auto" style="display: flex">
                <ef-text placeholder="请输入用户组编号、用户组名称进行查询" style="margin-right: 5px;width: 250px"></ef-text>
                <el-button-group>
                  <el-button icon="el-icon-search" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-refresh" size="mini" type="default"></el-button>
                  <el-button icon="el-icon-plus" size="mini" type="primary">添加</el-button>
                  <el-button icon="el-icon-minus" size="mini" type="primary">移除</el-button>
                </el-button-group>
              </el-form>
            </div>
          </div>
          <div class="card-content" style="height: 246px">
            kendo表格
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/javascript">
import './css/formStyle.css'
import  {BaseCtrl}  from '/src/util/eframe'
export default {
  name: "test-slidebar",
  mixins: [BaseCtrl],
  props: {
    params: {
      default: ''
    },
  },

  data() {
    return {
      selectTab:0,
      form: {
        user1: "11",
        user2: "管理员",
        user3: "",
        user4: "",
        telephone1: "",
        telephone2: "",
        telephone3: "",
        mail1: "",
        mail2: "",
        mail3: "yrdy@qq.com",
        multiSelect1: ['上海'],
        date1: "",
        date2: "2021/04/01",
        date3: "2021/03/01",
        time2: "",
        number1: "11.336",
        switch3: "1",
      },

    }
  },
  created() {

  },
  computed: {},
  methods: {
    changeSelectTab(num){
      this.selectTab = num
    }
  }
}

</script>

<style scoped>



</style>

