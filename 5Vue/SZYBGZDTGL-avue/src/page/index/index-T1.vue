<template>
  <div class="avue-contail"
       :class="{'avue--collapse':isCollapse,}">
    <screenshot v-if="setting.screenshot"></screenshot>
    <!--    <setting></setting>-->
    <div class="avue-layout"
         :class="{'avue-layout--horizontal':isHorizontal}">
      <div class="avue-sidebar">
        <!-- 左侧导航栏 -->
        <logo/>
        <sidebar/>
      </div>
      <div class="avue-main">
        <!-- 顶部导航栏 -->
        <top ref="top"/>
        <!-- 顶部标签卡 -->
        <tags/>
        <transition name="fade-scale">
          <search class="avue-view"
                  v-show="isSearch"></search>
        </transition>
        <!-- 主体视图层 -->
        <div style="height:calc(100% - 80px);overflow-y:auto;overflow-x:hidden;position: relative"
             id="avue-view"
             v-show="!isSearch">
          <keep-alive>
            <router-view class="avue-view"
                         v-if="$route.meta.keepAlive && isRefresh"/>
          </keep-alive>
          <router-view class="avue-view"
                       v-if="!$route.meta.keepAlive && isRefresh"/>
        </div>
        <!--        <div class="avue-footer">-->
        <!--          <p class="copyright">© 2018-2021 Avue designed by smallwei</p>-->
        <!--        </div>-->
      </div>
    </div>
    <ef-dialog ref="edialog1"/>
    <ef-dialog ref="edialog2"/>
    <ef-drawer ref="edrawer1"/>
    <ef-drawer ref="edrawer2"/>
    <ef-show-loading ref="eshowloading"/>
  </div>
</template>

<script>

import {mapGetters} from "vuex";
import tags from "./tags";
import screenshot from './screenshot';
import search from "./search";
import logo from "./logo";
import top from "./top/";
import sidebar from "./sidebar/";
import {validatenull} from "@/util/validate";
import EfDialog from "@/components/ef-element-comps/EfDialog";
import EfDrawer from "@/components/ef-element-comps/EfDrawer";


export default {
  components: {
    top,
    logo,
    tags,
    search,
    sidebar,
    // setting,
    screenshot,
    EfDialog,
    EfDrawer,
  },
  name: "indexT1",
  provide() {
    return {
      index: this
    };
  },
  data() {
    return {
      //搜索控制
      isSearch: false,
    };
  },
  created() {

  },
  mounted() {

  },
  computed: mapGetters(["isHorizontal", "setting", "isRefresh", "isLock", "isCollapse", "website", "menu", "userInfo"]),
  props: [],
  methods: {
    //打开菜单
    openMenu(item = {}) {
      this.$store.dispatch("GetMenu", item.parentId).then(data => {
        if (data.length !== 0) {
          this.$router.$avueRouter.formatRoutes(data, true);
        }
        //当点击顶部菜单做的事件
        if (!validatenull(item)) {
          let itemActive = {},
              childItemActive = 0;
          //vue-router路由
          if (item.path) {
            itemActive = item;
          } else {
            if (this.menu[childItemActive].length == 0) {
              itemActive = this.menu[childItemActive];
            } else {
              itemActive = this.menu[childItemActive].children[childItemActive];
            }
          }
          this.$store.commit('SET_MENUID', item);
          this.$router.push({
            path: this.$router.$avueRouter.getPath({
              name: itemActive.label,
              src: itemActive.path
            }, itemActive.meta)
          });
        }
      });
    },

  }
};
</script>