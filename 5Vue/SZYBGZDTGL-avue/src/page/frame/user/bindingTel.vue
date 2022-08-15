<template>
  <el-container style="height: 100%">
    <el-header>
      <div class="header-left">
      </div>
      <div class="header-right">
          <el-button v-prevent-re-click icon="el-icon-folder-checked" size="mini" type="primary">保存</el-button>
      </div>
    </el-header>
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <el-form ref="form" :model="form" label-position="right" label-width="60px">
          <el-row>
            <el-col :span="24">
              <ef-tel label="手机号" v-model="form.tel" :maxlength=11 :minlength=11 name="tel"
                       validationMessage="请输入正确的手机号" require="true"
                       placeholder="手机号"/>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="display: flex;align-items: flex-end">
            <el-col :span="16">
              <ef-text label="验证码" v-model="form.yzm" :maxlength=20 :minlength=5 name="yzm"
                       validationMessage="请输入验证码" require="true"
                       placeholder="验证码"/>
            </el-col>
            <el-col :span="8">
              <el-button v-prevent-re-click style="width: 100%" size="mini" type="primary">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'

export default {
  name: "changeTel",
  mixins: [BaseCtrl],
  data() {
    return {
      form: {
        password1:'',
        yzm:''
      }
    }
  },
  created() {

  },
  mounted() {

  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>


</style>