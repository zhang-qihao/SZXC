<template>
  <div v-show="efShowLoading" class="ef-show-loading">
    <div class="ef-show-loading-box">
      <i style="font-size: 30px" class="el-icon-loading"></i>
      <div style="color: #333;margin-top: 5px">正在读卡...</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EfShowLoading",
  data(){
    return{
      efShowLoading:false,
    }
  },

  computed:{

  },
  watch: {

  },
  created() {


  },
  methods: {
    openEfShowLoading(val){
      this.efShowLoading = true
    },
    closeEfShowLoading(val){
      this.efShowLoading = false
    }
  }
}
</script>

<style scoped>
.ef-show-loading{
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0,0,0,0);
  z-index:9999;
  display: flex;
  align-items: center;
  justify-content: center;
}
.ef-show-loading-box{
  /*margin: 15vh auto 0;*/
  width: 277px;
  height: 167px;
  /*box-shadow: 0 1px 3px rgb(0 0 0 / 30%);*/
  box-shadow: 0px 0px 9px 0px rgba(217, 216, 216, 0.79);
  box-sizing: border-box;
  background: #fff;
  border-radius: 20px;
  /*padding: 15px;*/
  overflow: hidden;
  background-image:url("./img/sbk.png") ;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
</style>
