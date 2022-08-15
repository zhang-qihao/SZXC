<template>
  <span style="padding: 2px 4px;border: solid #edf0f2 2px;cursor: pointer;background: #edf0f2;border-radius: 3px">
    <i class="el-icon-lock"
       @click="handleLock"></i>
    <el-dialog title="设置锁屏密码"
               :visible.sync="box"
               width="30%"
               append-to-body>
      <el-form :model="form"
               ref="form"
               label-width="100px">
        <el-form-item label="锁屏密码"
                      style="display: flex;margin-top: 20px;justify-content: center"
                      prop="passwd"
                      :rules="[{ required: true, message: '锁屏密码不能为空'}]">
          <el-input v-model="form.passwd"
                    placeholder="请输入锁屏密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button v-prevent-re-click type="primary" size="mini"
                   @click="handleSetLock">确 定</el-button>
      </span>
    </el-dialog>
  </span>
</template>

<script>
import { validatenull } from "@/util/validate";
import { mapGetters } from "vuex";
export default {
  name: "top-lock",
  data() {
    return {
      box: false,
      form: {
        passwd: ""
      }
    };
  },
  created() {},
  mounted() {},
  computed: {
    ...mapGetters(["lockPasswd"]),
  },
  props: [],
  methods: {
    handleSetLock() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$store.commit("SET_LOCK_PASSWD", this.form.passwd);
          this.handleLock();
        }
      });
    },
    handleLock() {
      if (validatenull(this.lockPasswd)) {
        this.box = true;
        return;
      }
      this.$store.commit("SET_LOCK");
      setTimeout(() => {
        this.$router.push({ path: "/lock" });
      }, 100);
    }
  },
  components: {}
};
</script>

<style lang="scss" scoped>
::v-deep.el-form-item > .el-form-item__content{
  margin-left: 0!important;
}
</style>
