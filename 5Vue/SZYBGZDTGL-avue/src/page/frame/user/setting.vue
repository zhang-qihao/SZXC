<template>
  <div class="user-setting" style="height: 100%;">
    <basic-container style="height: 100%;">
      <el-tabs v-model="activeName" tab-position="left"
               stretch
               style="height: 100%;">


<!--        个性化设置-->
        <el-tab-pane label="个性化设置" name="first"  style="height: 100%;">
          <div class="user-setting__main">
            <div class="user-setting__title">主题设置</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-s-cooperation"></i>
                  主题设置
                </p>
                <p class="subtitle">更改当前主题</p>
                <el-radio-group
                    style="width: 100%"
                    v-model="text"
                                class="list">
                  <el-row :span="24">
                    <el-col v-for="(item,index) in list"
                            :key="index"
                            :md="4"
                            :xs="4"
                            :sm="4">
                      <el-radio :label="item.value">{{item.name}}</el-radio>
                      <span style="display: inline-block;height: 10px;width: 10px;" :style="colorList[index][0]"></span>
                      <span style="display: inline-block;height: 10px;width: 10px;" :style="colorList[index][1]"></span>
                    </el-col>
                  </el-row>
                </el-radio-group>
              </div>
            </div>

<!--            <div class="user-setting__item">-->
<!--              <div class="user-setting__meta">-->
<!--                <p class="title">-->
<!--                  <i class="el-icon-magic-stick"></i>-->
<!--                  主题颜色-->
<!--                </p>-->
<!--                <p class="subtitle">更改当前主题颜色</p>-->
<!--              </div>-->
<!--              <div class="user-setting__menu" style="cursor: pointer;">-->
<!--                <el-color-picker size="mini"-->
<!--                                 class="theme-picker"-->
<!--                                 popper-class="theme-picker-dropdown"-->
<!--                                 v-model="themeVal"></el-color-picker>-->
<!--              </div>-->
<!--            </div>-->
          </div>
          <div class="user-setting__main">
            <div class="user-setting__title">语言设置</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="fa fa-language el-dropdown-selfdefine"></i>
                  语言选择
                </p>
                <p class="subtitle">选择当前语言</p>
              </div>
              <div class="user-setting__menu" style="cursor: pointer;">
<!--                <el-switch @change="handleSetLanguage" v-model="value" active-text="中文" inactive-text="英文">-->
<!--                </el-switch>-->
                <el-dropdown trigger="click"
                             @command="handleSetLanguage">
<!--                  <span>语言选择</span>-->
                  <i class="el-icon-arrow-down" style="color: #409eff;font-size: 20px"></i>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item :disabled="language==='zh'"
                                      command="zh">中文</el-dropdown-item>
                    <el-dropdown-item :disabled="language==='en'"
                                      command="en">English</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </div>
          </div>
          <div class="user-setting__main">
            <div class="user-setting__title">页面布局</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-s-promotion"></i>
                  导航标签
                </p>
                <p class="subtitle">导航标签</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['tag']">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-menu"></i>
                  菜单折叠
                </p>
                <p class="subtitle">菜单折叠</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['collapse']">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-search"></i>
                  菜单搜索
                </p>
                <p class="subtitle">菜单搜索</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['search']">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-full-screen"></i>
                  屏幕全屏
                </p>
                <p class="subtitle">屏幕全屏</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['fullscren']">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-s-cooperation"></i>
                  主题选择
                </p>
                <p class="subtitle">主题选择</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['theme']">
                </el-switch>
              </div>
            </div>
<!--            <div class="user-setting__item">-->
<!--              <div class="user-setting__meta">-->
<!--                <p class="title">-->
<!--                  <i class="el-icon-magic-stick"></i>-->
<!--                  主题颜色-->
<!--                </p>-->
<!--                <p class="subtitle">主题颜色</p>-->
<!--              </div>-->
<!--              <div class="user-setting__menu">-->
<!--                <el-switch v-model="setting['color']">-->
<!--                </el-switch>-->
<!--              </div>-->
<!--            </div>-->
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-s-grid"></i>
                  顶部菜单
                </p>
                <p class="subtitle">顶部菜单</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['menu']">
                </el-switch>
              </div>
            </div>
          </div>
          <div class="user-setting__main">
            <div class="user-setting__title">功能调试</div>
<!--            <div class="user-setting__item">-->
<!--              <div class="user-setting__meta">-->
<!--                <p class="title">-->
<!--                  <i class="el-icon-document"></i>-->
<!--                  意见反馈-->
<!--                </p>-->
<!--                <p class="subtitle">意见反馈</p>-->
<!--              </div>-->
<!--              <div class="user-setting__menu">-->
<!--                <el-switch v-model="setting['screenshot']">-->
<!--                </el-switch>-->
<!--              </div>-->
<!--            </div>-->
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-bell"></i>
                  日志调试
                </p>
                <p class="subtitle">日志调试</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['debug']">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-lock"></i>
                  屏幕锁定
                </p>
                <p class="subtitle">屏幕锁定</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="setting['lock']">
                </el-switch>
              </div>
            </div>
          </div>
          <div class="user-setting__main">
            <div class="user-setting__title">意见反馈</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-chat-dot-round"></i>
                  意见反馈
                </p>
                <p class="subtitle">意见反馈</p>
              </div>
              <!--              新增-->
              <div class="user-setting__menu">
                <el-switch v-model="setting['screenshot']">
                </el-switch>
              </div>
            </div>
          </div>

        </el-tab-pane>

        <el-tab-pane label="安全设置" name="second"  style="height: 100%;">
          <div class="user-setting__main">
            <div class="user-setting__title">安全设置</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-user"></i>
                  账号安全
                </p>
                <p class="subtitle">更改当前账号密码</p>
              </div>
              <div class="user-setting__menu" @click="changePassword">
                <el-button v-prevent-re-click size="small">修改</el-button>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-mobile-phone"></i>
                  绑定手机
                </p>
                <p class="subtitle">设置您的绑定手机号码</p>
              </div>
              <div class="user-setting__menu" @click="bindingTel">
                <el-button v-prevent-re-click size="small">修改</el-button>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-message"></i>
                  绑定邮箱
                </p>
                <p class="subtitle">设置您的绑定邮箱</p>
              </div>
              <div class="user-setting__menu" @click="bindingEmail">
                <el-button v-prevent-re-click size="small">修改</el-button>
              </div>
            </div>
          </div>

        </el-tab-pane>
        <el-tab-pane label="通知设置" name="third"  style="height: 100%;">
          <div class="user-setting__main">
            <div class="user-setting__title">通知设置</div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-document"></i>
                  系统消息
                </p>
                <p class="subtitle">系统性的通知或者更新消息</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="value">
                </el-switch>
              </div>
            </div>
            <div class="user-setting__item">
              <div class="user-setting__meta">
                <p class="title">
                  <i class="el-icon-user"></i>
                  帐号消息
                </p>
                <p class="subtitle">帐号变更的通知消息</p>
              </div>
              <div class="user-setting__menu">
                <el-switch v-model="value">
                </el-switch>
              </div>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </basic-container>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
// import {setTheme} from "@/util/util";
import color from "@/mixins/color";
import {BaseCtrl} from '/src/util/eframe'
export default {
  mixins: [color(),BaseCtrl],
  props: [],
  data () {
    return {
      value: true,
      chalk: "",
      form: {
        img: '',
        text: ''
      },
      text:'',
      colorList:[
        [{background:'#20222a'},{background: '#125bde'}],
        [{background:'#398fd7'},{background: '#42c09c'}],
        [{background:'#fff',boxShadow:'0 0 2px #888'},{background: '#c94d3c'}],
        [{background:'#fff',boxShadow:'0 0 2px #888'},{background: '#409eff'}],
        [{background:'#FFD700'},{background: '#20222a'}],
      ],
      list: [
        {
          name: "默认主题",
          value: "default"
        },
        {
          name: "绿色主题",
          value: "theme-beautiful"
        },
        {
          name: "红色主题",
          value: "theme-cool"
        },
        {
          name: "简约主题",
          value: "theme-iview"
        },
        // {
        //   name: "白色主题",
        //   value: "theme-white"
        // },
        // {
        //   name: "黑色主题",
        //   value: "theme-dark"
        // },
        // {
        //   name: "hey主题",
        //   value: "theme-hey"
        // },
        // {
        //   name: "炫彩主题",
        //   value: "theme-star"
        // },
        {
          name: "尊贵主题",
          value: "theme-vip"
        },
        // {
        //   name: "智能工厂主题",
        //   value: "theme-bule"
        // },


        // {
        //   name: "d2主题",
        //   value: "theme-d2"
        // },
        // {
        //   name: "renren主题",
        //   value: "theme-renren"
        // },

      ]
    }
  },
  mounted () {
    this.text = this.themeName;
    if (!this.text) {
      this.text = "";
    }
  },
  watch: {
    text: function (val) {
      this.$store.commit("SET_THEME_NAME", val);
      // setTheme(val);
    },
  },
  computed: {
    ...mapGetters(["isHorizontal", 'setting']),
    ...mapGetters(["language", "tag"]),
    flag () {
      return this.validatenull(this.form.img);
    },
    activeName:{
      get:function(){
        return this.$route.query.activeNum
      },
      set:function(){

      }
    }
  },
  methods:{
    handleSetLanguage (lang) {
      // let lang = 'zh'
      // if(this.value == true){
      //   lang = 'zh'
      // }else if(this.value == false){
      //   lang = 'en'
      // }
      this.$i18n.locale = lang;
      this.$store.commit("SET_LANGUAGE", lang);
      let tag = this.tag;
      let title = this.$router.$avueRouter.generateTitle(
          tag.label,
          (tag.meta || {}).i18n
      );
      //根据当前的标签也获取label的值动态设置浏览器标题
      this.$router.$avueRouter.setTitle(title);
    },
    changePassword(){
      let that = this
      let backFun = function (back) {
        console.log(back)
      };
      that.openDialog({
        title: "更改当前账号密码",
        view: "page/frame/user/changePassword",
        width: 400,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },
    bindingTel(){
      let that = this
      let backFun = function (back) {
        console.log(back)
      };
      that.openDialog({
        title: "绑定手机",
        view: "page/frame/user/bindingTel",
        width: 400,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },
    bindingEmail(){
      let that = this
      let backFun = function (back) {
        console.log(back)
      };
      that.openDialog({
        title: "绑定邮箱",
        view: "page/frame/user/bindingEmail",
        width: 400,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    }
  }
}
</script>

<style lang="scss">
.screenshot__img {
  min-height: 200px;
  margin-top: 50px;
  padding: 10px 20px;
  box-sizing: border-box;
  border: 1px solid #eee;
  width: 100%;
}
.screenshot__menu {
  text-align: center;
}

</style>
