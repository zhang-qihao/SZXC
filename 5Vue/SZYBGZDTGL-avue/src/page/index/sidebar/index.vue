<template>
  <el-scrollbar class="avue-menu"
                v-if="reload">
    <div v-if="validatenull(menu)&&!isHorizontal"
         class="avue-sidebar--tip">{{$t('menuTip')}}</div>
    <el-menu unique-opened
             :default-active="nowTagValue"
             :mode="setting.sidebar"
             :collapse="isCollapse">
      <sidebar-item :menu="menu"
                    :screen="screen"
                    first
                    :props="website.menu.props"
                    :collapse="isCollapse"></sidebar-item>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from "vuex";
import sidebarItem from "./sidebarItem";
export default {
  name: "sidebar",
  components: { sidebarItem },
  inject: ["index"],
  data () {
    return {
      reload: true
    };
  },
  created () {
    if(this.website.mainPageFrameType == 'T1'){
      this.index.openMenu()
    }
  },
  watch: {
    isHorizontal () {
      this.reload = false;
      this.$nextTick(() => {
        this.reload = true;
      })
    }
  },
  computed: {
    ...mapGetters(["isHorizontal", "setting", "website", "menu", "tag", "isCollapse", "screen", "menuId"]),
    nowTagValue: function () {
      return this.$router.$avueRouter.getValue(this.$route);
    },
  },
  methods: {}
};
</script>
<style lang="scss">

</style>

