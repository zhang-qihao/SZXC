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
              <ef-mail label="邮箱" v-model="form.mail" name="mail" placeholder="邮箱" require="true"
                       validationMessage="请输入正确的邮箱"
                       :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"/>
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
  name: "changeEmail",
  mixins: [BaseCtrl],
  data() {
    return {
      form: {
        mail:'',
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