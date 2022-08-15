<template>
  <div>
<!--    <indexdr v-if="activeName==='dr'"></indexdr>-->
    <indexchgr ></indexchgr>
  </div>
</template>

<script>

import {mapGetters} from "vuex";
import {BaseCtrl} from '/src/util/eframe'
import indexchgr from "./index-chgr";


export default {
  name: "welindex",
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  components: {
    //indexdr,
    indexchgr,
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  data() {
    return {
      activeName: "",
    };
  },
  created() {
    if (this.userInfo.role_type === '3') {
      this.activeName = "dr";
    } else {
      this.activeName = "chgr";
    }
  },
  methods: {},
}
</script>

<style scoped lang="scss">

</style>