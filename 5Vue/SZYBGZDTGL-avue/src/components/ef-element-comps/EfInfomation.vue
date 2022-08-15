<template>
  <div class="ef-infomation">
    <div v-if="infoInner" style="width: 400px;background: #fff;border-radius: 10px;max-height: 300px;position: absolute;top: 15vh;left: calc(50% - 200px)">
      <div style="height: 40px;display: flex;align-items: center;justify-content: space-between;padding: 0 15px">
        <span style="color: #333;font-size: 16px;font-weight: bold">提示</span>
        <span style="font-size: 16px;cursor: pointer;display:inline-block;width: 40px;height: 40px;line-height: 40px;text-align: right " @click="closeInfoInner">
          <i class="el-icon-close el-icon" style="font-size: 18px"></i>
        </span>
      </div>
      <div style="max-height: 260px;overflow: hidden auto">
        <div style="display: flex;padding: 15px;justify-content: center">
          <i class="el-icon-error el-icon" style="color: red;font-size: 16px;width: 16px;flex-shrink: 0"></i>
          <div style="margin-left: 15px;width: calc(100% - 38px);font-size: 14px;line-height: 18px;word-break: break-all">
            {{innerText}}
          </div>
        </div>
        <div style="padding:15px;display: flex;justify-content: flex-end">
          <el-button size="mini" type="primary" @click="closeInfoInner">确定</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EfInfomation",
  data(){
    return{
      // efInfomation:false,
      infoText:'',
      infoInner:true,
    }
  },
  props:{
    innerText:{
      type:String,
      default:''
    }
  },

  computed:{

  },
  watch: {

  },
  created() {

  },
  methods: {
    // openEfInfomation(val){
    //   this.efInfomation = true
    //   this.infoInner = true
    //   this.infoText = val
    // },
    // closeEfInfomation(val){
    //   this.efInfomation = false
    //   this.infoInner = false
    // },
    closeInfoInner(){
      this.infoInner = false
    }
  }
}
</script>

<style scoped>
.ef-infomation{
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0,0,0,.3);
  z-index:9999;
}
</style>
