<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "app",
  data() {
    return {
      width:null
    };
  },
  created() {
    document.body.className = this.themeName
    // 全局添加屏幕宽度监听
    window.addEventListener('resize',this.getWidth)
    this.getWidth()
    this.preEnter()
  },
  destroyed() {
    // 全局移除屏幕宽度监听
    window.removeEventListener('resize',this.getWidth)
  },
  computed: {
    ...mapGetters([
      "themeName"
    ]),
  },
  methods: {
    // 获取屏幕宽度
    getWidth(){
      this.width = document.body.clientWidth
      if(this.width < 1680){
        this.$store.commit("SET_COLLAPSE",'1');
      }
      if(this.width >= 1680){
        this.$store.commit("SET_COLLAPSE",'2');
      }
    },
    preEnter() {
      document.onkeydown = function (e) {
        if (e.keyCode == 13) {
          e.preventDefault()
          e.stopPropagation()
        }
      }
    }
  },
};
</script>
<style lang="scss">
#app {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>