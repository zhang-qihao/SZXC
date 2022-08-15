<template>
  <div class="avue-tags"
       v-if="setting.tag"
       @click="contextmenuFlag=false">
    <!-- tag盒子 -->
    <div v-if="contextmenuFlag"
         class="avue-tags__contentmenu"
         :style="{left:contentmenuX+'px',top:contentmenuY+'px'}">
      <div class="item"
           @click="closeOthersTags">{{$t('tagsView.closeOthers')}}</div>
      <div class="item"
           @click="closeAllTags">{{$t('tagsView.closeAll')}}</div>
    </div>
    <div class="avue-tags__box"
         :class="{'avue-tags__box--close':!website.isFirstPage}">
      <el-tabs v-model="active"
               type="card"
               :closable="tagLen!==1"
               @tab-click="openTag"
               @contextmenu.prevent.native="handleContextmenu"
               @edit="menuTag">
        <el-tab-pane :key="item.value"
                     v-for="(item,index) in tagList"
                     :label="generateTitle(item)"
                     :name="item.value">
          <span slot="label">
            <span v-if="index != 0">{{generateTitle(item)}}</span>
            <i v-if="index == 0" class="el-icon-s-home" style="color: #77787b;font-size: 14px;margin-right: 5px"></i>
            <!-- 取消菜单刷新 -->
<!--            <i class="el-icon-refresh" :class="{'turn':refresh}" @click="handleRefresh" v-if="active==item.value"></i>-->
          </span>
        </el-tab-pane>
      </el-tabs>
<!--      修改-->
<!--      <el-dropdown class="avue-tags__menu">-->
<!--        <el-button type="primary"-->
<!--                   style="height: 24px"-->
<!--                   size="mini">-->
<!--          {{$t('tagsView.menu')}}-->
<!--          <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
<!--        </el-button>-->
<!--        <el-dropdown-menu slot="dropdown">-->
<!--          <el-dropdown-item @click.native="$parent.isSearch=true">{{$t('tagsView.search')}}</el-dropdown-item>-->
<!--          <el-dropdown-item @click.native="closeOthersTags">{{$t('tagsView.closeOthers')}}</el-dropdown-item>-->
<!--          <el-dropdown-item @click.native="closeAllTags">{{$t('tagsView.closeAll')}}</el-dropdown-item>-->
<!--        </el-dropdown-menu>-->
<!--      </el-dropdown>-->
    </div>

  </div>
</template>
<script>
import { mapGetters } from "vuex";
import {BaseCtrl} from '/src/util/eframe'
export default {
  name: "tags",
  mixins: [BaseCtrl],
  data () {
    return {
      refresh: false,
      active: "",
      contentmenuX: "",
      contentmenuY: "",
      contextmenuFlag: false
    };
  },
  created () { },
  mounted () {
    this.setActive();
  },
  watch: {
    tag () {
      this.setActive();
    },
    contextmenuFlag () {
      window.addEventListener("mousedown", this.watchContextmenu);
    },
  },
  computed: {
    ...mapGetters(["tagWel", "tagList", "tag", "website", "setting"]),
    tagLen () {
      return this.tagList.length || 0;
    }
  },
  methods: {
    handleRefresh () {
      this.refresh = true;
      this.$store.commit('SET_IS_REFRESH', false);
      setTimeout(() => {
        this.$store.commit('SET_IS_REFRESH', true);
      }, 100)
      setTimeout(() => {
        this.refresh = false;
      }, 1000)
    },
    generateTitle (item) {
      return this.$router.$avueRouter.generateTitle(
        item.label,
        (item.meta || {}).i18n
      );
    },
    watchContextmenu (event) {
      if (!this.$el.contains(event.target) || event.button !== 0) {
        this.contextmenuFlag = false;
      }
      window.removeEventListener("mousedown", this.watchContextmenu);
    },
    handleContextmenu (event) {
      // let target = event.target;
      // 解决 https://github.com/d2-projects/d2-admin/issues/54
      // let flag = false;
      // if (target.className.indexOf("el-tabs__item") > -1) flag = true;
      // else if (target.parentNode.className.indexOf("el-tabs__item") > -1) {
      //   target = target.parentNode;
      //   flag = true;
      // }
      // if (flag) {
      // console.log(target)
        event.preventDefault();
        event.stopPropagation();
        this.contentmenuX = event.clientX;
        this.contentmenuY = event.clientY;
        this.contextmenuFlag = true;
      // }
    },
    //激活当前选项
    setActive () {
      this.active = this.tag.value;
    },
    menuTag (value, action) {
      if (action === "remove") {
        let openTag; // 最终要打开的页面
        let { tag, key } = this.findTag(value);

        if (tag.value === this.tag.value) {
          openTag = this.tagList[key === 0 ? key : key - 1]; //如果关闭本标签让前推一个
        } else {
          openTag = this.tag;
          this.openTag(tag);
        }
        if(tag.saveFlag){
          let okFun = ()=>{
            this.$store.commit("DEL_TAG", tag);
            this.openTag(openTag);
          }
          let cancleFun = ()=>{
            this.openTag(tag)
          }
          this.confirm('提示','数据有变动,尚未保存,是否关闭',okFun,cancleFun)
        }else{
          this.$store.commit("DEL_TAG", tag);
          this.openTag(openTag);
        }
      }
    },
    openTag (item) {
      let tag;
      if (item.name) {
        tag = this.findTag(item.name).tag;
      } else {
        tag = item;
      }
      if(this.website.mainPageFrameType == 'T2'){
          this.$store.commit('SET_T2MENU',tag.topId)
      }
      //tag切换接诊页面,不刷新页面
      if(tag.query.drvisit_flag){
        tag.query.drvisit_flag = false
      }
      this.$router.push({
        path: this.$router.$avueRouter.getPath({
          name: tag.label,
          src: tag.value
        }, tag.meta),
        query: tag.query
      });
    },
    findTag (value) {
      let tag, key;
      this.tagList.map((item, index) => {
        if (item.value === value) {
          tag = item;
          key = index;
        }
      });
      return { tag: tag, key: key };
    },
    // 因需清除每个keep-alive页面的缓存，需一个一个的激活tag到前台做删除
    activeTag (tagList) {
      tagList.forEach(item => {
        this.openTag(item);
        this.$store.commit("DEL_TAG", item);
      });
    },
    closeOthersTags () {
      this.contextmenuFlag = false;
      let openTag = this.tag;
      let tagList = this.tagList.filter(item =>
        item.value !== this.tag.value && !this.website.isFirstPage && item.value !== this.tagWel.value
      );
      this.activeTag(tagList)
      this.$nextTick(()=>{
        this.openTag(openTag);
      })
    },
    closeAllTags () {
      this.contextmenuFlag = false;
	  let tagList = this.tagList.filter(item =>
	    !this.website.isFirstPage && item.value != this.tagWel.value
	  );
	  this.activeTag(tagList)
	  this.$nextTick(()=>{
      this.openTag(this.tagWel);
    })
    }
  }
};
</script>


