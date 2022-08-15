<template>
  <div class="avue-top">
    <div class="top-bar__left">
      <div class="avue-breadcrumb" :class="[{ 'avue-breadcrumb--active': isCollapse }]"
           v-if="setting.collapse&&!isHorizontal">
        <i style="cursor: pointer" class="fa fa-navicon" @click="setCollapse"></i>
      </div>
    </div>
    <div class="top-bar__menu">
      <div class="top-menu">
        <el-menu :default-active="activeIndex"
                 mode="horizontal"
                 text-color="#333">
          <template v-for="(item,index) in topMenuList">
            <el-menu-item :index="item.parentId+''"
                          @click.native="openHomePage(item)"
                          :key="index">
              <template slot="title">
                <div :class="{'activeMenu':tag.topId == item.parentId}">
                  <!--                  <i :class="item.icon"></i>-->
                  <i style="margin-right: 5px" class="fa fa-folder-o"></i>
                  <!--                  <i class="el-icon-collection"></i>-->
                  <span>{{ generateTitle(item) }}</span>
                </div>
              </template>
            </el-menu-item>
          </template>
        </el-menu>
      </div>
    </div>
    <el-dropdown v-if="topMenu.length > 6" placement="bottom-start" style="margin:0 20px;" trigger="click">
        <span class="el-dropdown-link menu-dropdown-link" style="cursor:pointer;font-size: 16px;line-height: 52px">
          <i class="el-icon-more"></i>
          <span style="font-size: 16px;margin-left:5px;cursor: pointer">更多</span>
        </span>
      <el-dropdown-menu class="top-bar__menu-more-menu" slot="dropdown">
        <el-dropdown-item :class="{'activeMenu':tag.topId == item.parentId}" v-for="(item,index) in topMenuMore"
                          :key="index" @click.native="openHomePage(item)">
          <!--          <i :class="item.icon"></i>-->
          <!--          <i class="el-icon-collection"></i>-->
          <i class="fa fa-folder-o"></i>
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!--    <div class="top-bar__title">-->
    <!--      <div :title="topTitle">{{ topTitle }}</div>-->
    <!--&lt;!&ndash;            <div class="top-bar__item top-bar__item&#45;&#45;show"&ndash;&gt;-->
    <!--&lt;!&ndash;                 v-if="setting.menu">&ndash;&gt;-->
    <!--&lt;!&ndash;              <top-menu ref="topMenu"></top-menu>&ndash;&gt;-->
    <!--&lt;!&ndash;            </div>&ndash;&gt;-->
    <!--      &lt;!&ndash;      <span class="top-bar__item"&ndash;&gt;-->
    <!--      &lt;!&ndash;            v-if="setting.search">&ndash;&gt;-->
    <!--      &lt;!&ndash;        <top-search></top-search>&ndash;&gt;-->
    <!--      &lt;!&ndash;      </span>&ndash;&gt;-->
    <!--    </div>-->
    <div class="top-bar__right">
      <!--      <el-tooltip v-if="setting.color"-->
      <!--                  effect="dark"-->
      <!--                  :content="$t('navbar.color')"-->
      <!--                  placement="bottom">-->
      <!--        <div class="top-bar__item">-->
      <!--          <top-color></top-color>-->
      <!--        </div>-->
      <!--      </el-tooltip>-->
      <el-tooltip effect="dark"
                  content="首页"
                  placement="bottom">
        <div class="top-bar__item">
          <span
              style="padding: 2px 4px;border: solid #edf0f2 2px;cursor: pointer;background: #edf0f2;border-radius: 3px">
            <i class="el-icon-s-home" @click="changeHomePageName"></i>
          </span>
        </div>
      </el-tooltip>
      <el-tooltip v-if="setting.debug"
                  effect="dark"
                  :content="logsFlag?$t('navbar.bug'):logsLen+$t('navbar.bugs')"
                  placement="bottom">
        <div class="top-bar__item" style="padding-top: 1px">
          <top-logs></top-logs>
        </div>
      </el-tooltip>
      <el-tooltip v-if="setting.lock"
                  effect="dark"
                  :content="$t('navbar.lock')"
                  placement="bottom">
        <div class="top-bar__item">
          <top-lock></top-lock>
        </div>
      </el-tooltip>
      <el-tooltip v-if="setting.theme"
                  effect="dark"
                  :content="$t('navbar.theme')"
                  placement="bottom">
        <div class="top-bar__item top-bar__item--show">
          <!--          <router-link :to="{path:'/info/setting',query:{activeNum:'first'}}" style="color: rgba(0,0,0,.65)">-->
          <span @click="linkToSetting"
                style="padding: 2px 4px;cursor: pointer;border: solid #edf0f2 2px;background: #edf0f2;border-radius: 3px">
              <i class="el-icon-finished"></i>
            </span>
          <!--          </router-link>-->
        </div>
      </el-tooltip>
      <!--      <el-tooltip effect="dark"-->
      <!--                  :content="$t('navbar.notice')"-->
      <!--                  placement="bottom">-->
      <!--        <div class="top-bar__item top-bar__item&#45;&#45;show">-->
      <!--          <top-notice></top-notice>-->
      <!--        </div>-->
      <!--      </el-tooltip>-->
      <!--      <el-tooltip effect="dark"-->
      <!--                  :content="$t('navbar.language')"-->
      <!--                  placement="bottom">-->
      <!--        <div class="top-bar__item top-bar__item&#45;&#45;show">-->
      <!--          <top-lang></top-lang>-->
      <!--        </div>-->
      <!--      </el-tooltip>-->
      <el-tooltip v-if="setting.fullscren"
                  effect="dark"
                  :content="isFullScren?$t('navbar.screenfullF'):$t('navbar.screenfull')"
                  placement="bottom">
        <div class="top-bar__item">
          <span
              style="padding: 2px 4px;border: solid #edf0f2 2px;cursor: pointer;background: #edf0f2;border-radius: 3px">
            <i :class="isFullScren?'fa fa-compress':'fa fa-expand'"
               @click="handleScreen"></i>
          </span>
        </div>
      </el-tooltip>
      <!--      菜单管理-->
      <!--      <el-dropdown style="margin:0 8px;" trigger="click" v-if="setting.menu">-->
      <!--        <span class="el-dropdown-link"-->
      <!--              style="background: #edf0f2;padding:3px 4px;cursor:pointer;border-radius: 3px;border: solid 2px #edf0f2;">-->
      <!--          <span style="font-size: 16px;margin-left:5px;cursor: pointer">{{ menuTitle }}</span>-->
      <!--          <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
      <!--        </span>-->
      <!--        <el-dropdown-menu slot="dropdown">-->
      <!--          <el-dropdown-item v-for="(item,index) in items" :key="index" @click.native="openMenu(item,index)" divided>-->
      <!--            <span><i :class="item.icon" style="margin-right: 10px"></i>{{ generateTitle(item) }}</span>-->
      <!--          </el-dropdown-item>-->
      <!--        </el-dropdown-menu>-->
      <!--      </el-dropdown>-->

      <!--      字体大小-->

      <el-dropdown style="margin:0 8px;" trigger="click">
        <span class="el-dropdown-link"
              style="background: #edf0f2;padding:3px 4px;cursor:pointer;border-radius: 3px;border: solid 2px #edf0f2;">
          <span style="font-size: 16px;margin-left:5px;cursor: pointer">字(<span style="font-size: 12px">{{ zi }}</span>)</span>
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="changeFormStyle(0)"
                            divided>小号
          </el-dropdown-item>
          <el-dropdown-item @click.native="changeFormStyle(1)"
                            divided>中号
          </el-dropdown-item>
          <el-dropdown-item @click.native="changeFormStyle(2)"
                            divided>大号
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <!--      <img class="top-bar__img"-->
      <!--           :src="userInfo.avatar">-->
      <img class="top-bar__img"
           src="../../../assets/top/man.png">
      <el-dropdown trigger="click">
        <span class="el-dropdown-link" style="cursor: pointer;margin-left: 3px;font-size: 16px">
          {{ userInfo.userName }}
          <!--          <span style="background: #edf0f2;padding:2px 4px;border-radius: 3px;border: solid 2px #edf0f2;">-->
            <i class="el-icon-arrow-down el-icon--right"></i>
          <!--          </span>-->
        </span>
        <el-dropdown-menu slot="dropdown">
          <!--            <router-link to="/">{{ $t('navbar.dashboard') }}</router-link>-->
          <el-dropdown-item @click.native="changeHomePageName" divided>首页</el-dropdown-item>
          <!-- TODO  个人信息-->
          <!--          <el-dropdown-item>-->
          <!--            <router-link to="/info/index">{{$t('navbar.userinfo')}}</router-link>-->
          <!--          </el-dropdown-item>-->
          <!--          <el-dropdown-item>-->
          <!--          <router-link :to="{path:'/info/setting',query:{activeNum:'second'}}">{{$t('navbar.setting')}}</router-link>-->
          <!--          </el-dropdown-item>-->
          <el-dropdown-item @click.native="updatePassword" divided>修改密码</el-dropdown-item>
          <el-dropdown-item @click.native="logout" divided>{{ $t('navbar.logOut') }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import {BaseCtrl} from '/src/util/eframe'
import {resetRouter} from '@/router/router'
import {mapGetters} from "vuex";
import {fullscreenToggel, listenfullscreen} from "@/util/util";
import topLock from "./top-lock";
// import topMenu from "./top-menu";
import topSearch from "./top-search";
// import topTheme from "./top-theme";
import topLogs from "./top-logs";
// import topColor from "./top-color";
import topNotice from './top-notice'
// import topLang from "./top-lang";
import {validatenull} from "@/util/validate";
import ViewsRouter from '@/router/views/index'

export default {
  components: {
    topLock,
    // topMenu,
    topSearch,
    // topTheme,
    topLogs,
    // topColor,
    topNotice,
    // topLang
  },
  name: "topIndexT2",
  inject: ["index"],
  data() {
    return {
      zi: '小',
      // items: [],
      menuTitle: '平台首页',
      // topTitle:'',
      activeIndex: '0',
      topMenuList: [],
      topMenuMore: []
    };
  },
  filters: {},
  created() {
    this.$store.dispatch("GetT2Menu", '').then(data => {
      if (data.length !== 0) {
        this.$router.$avueRouter.formatRoutes(data, true);
        this.getTopMenu()
      }
    })
  },
  mounted() {
    listenfullscreen(this.setScreen);
  },
  computed: {
    ...mapGetters([
      "setting",
      "userInfo",
      "isFullScren",
      "tagWel",
      "tagList",
      "isCollapse",
      "tag",
      "logsLen",
      "logsFlag",
      "isHorizontal",
      "themeName",
      "menu",
      "website",
      "topMenu",
    ]),
  },
  watch: {
    topMenu: {
      handler(newVal) {
        if (this.topMenu.length > 6) {
          this.topMenuList = newVal.slice(0, 6)
          this.topMenuMore = newVal.slice(6, this.topMenu.length)
        } else {
          this.topMenuList = newVal
        }
      },
      deep: true,
      immediate: true,
    }
  },
  methods: {
    linkToSetting() {
      this.$store.commit('SET_T2MENU ', null)
      this.$router.push({
        path: '/info/setting',
        query: {activeNum: 'first'}
      })
    },
    changeHomePageName() {
      ViewsRouter[0].children[0].component = 'page/frame/wel/index'
      ViewsRouter[0].children[0].path = '/wel/index'
      this.$router.$avueRouter.formatviewsRoutes(ViewsRouter, true);
      this.$router.push({
        path: '/wel/index',
        query: ''
      })
    },
    isNull(data) {
      return this.$eframe.isNull(data);
    },
    handleScreen() {
      fullscreenToggel();
    },
    setCollapse() {
      this.$store.commit("SET_COLLAPSE", '0');
    },
    setScreen() {
      this.$store.commit("SET_FULLSCREN");
    },
    updatePassword() {
      let that = this
      let backFun = function (back) {
        console.log(back)
      };
      let dialog = that.index.$refs['edialog1']
      dialog.showDialog({
        title: "更改当前账号密码",
        view: "@/page/frame/user/changePassword",
        width: 400,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },
    logout() {
      this.$confirm(this.$t("logoutTip"), this.$t("tip"), {
        confirmButtonText: this.$t("submitText"),
        cancelButtonText: this.$t("cancelText"),
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          ViewsRouter[0].children[0].component = 'page/frame/wel/index'
          ViewsRouter[0].children[0].path = '/wel/index'
          resetRouter();
          console.log("【top-index-T2】 1:"+"跳转到登录页面");
          this.$router.push({path: "/login"});
        });
      });
    },
    changeFormStyle(num) {
      if (num == 0) {
        this.zi = '小'
        document.body.className = 'body-mini' + ' ' + this.themeName
      } else if (num == 1) {
        this.zi = '中'
        document.body.className = 'body-small' + ' ' + this.themeName
      } else if (num == 2) {
        this.zi = '大'
        document.body.className = 'body-medium' + ' ' + this.themeName
      }
      this.$store.commit('SET_FORMSTYLE', num)
    },
    generateTitle(item) {
      return this.$router.$avueRouter.generateTitle(
          item.label,
          (item.meta || {}).i18n
      );
    },
    getTopMenu() {
      this.$store.dispatch("GetT2TopMenu").then((data) => {
        data.map((item)=>{
          if(this.userInfo.role_type == '3'){
            if(item.label == '医生工作站'){
              this.openHomePage(item)
            }
          }else{
            if(item.label == '结算管理'){
              this.openHomePage(item)
            }
          }
        })
      })
    },
    openHomePage(item = {}) {
      this.$store.commit('SET_T2MENU', item.parentId)
      //当点击顶部菜单做的事件
      if (!validatenull(item)) {
        this.$store.commit('SET_MENUID', item);
        if (isNotNull(item.path)) {
          if(item.path == '/wel/system' || item.path == '/wel/example'){
            ViewsRouter[0].children[0].component = item.component
            ViewsRouter[0].children[0].path = item.path + '/index'
            this.$router.$avueRouter.formatviewsRoutes(ViewsRouter, true);
            this.$router.push({
              path: item.path + '/index',
              query: {topId: item.parentId}
            })
          }else{
            ViewsRouter[0].children[0].component = item.component
            ViewsRouter[0].children[0].path = item.path
            this.$router.$avueRouter.formatviewsRoutes(ViewsRouter, true);
            this.$router.push({
              path: item.path,
              query: {topId: item.parentId}
            })
          }
        } else {
          ViewsRouter[0].children[0].component = 'page/frame/wel/index'
          ViewsRouter[0].children[0].path = '/wel/index'
          this.$router.$avueRouter.formatviewsRoutes(ViewsRouter, true);
          this.$router.push({
            path: '/wel/index',
            query: {topId: item.parentId}
          })
        }
      }
    },
  }
};
</script>

<style lang="scss" scoped>

</style>