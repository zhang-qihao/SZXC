<template>
  <el-container style="height: 100%">
    <el-header
        style="height: 37px;background-color: #fff;display: flex;align-items: center;justify-content: space-between;box-shadow: 0 2px 5px rgba(156,156,156,0.35);">
      <div style="flex: 1">
                  <span style="color: #409eff;font-weight: bold;font-size: 14px">
                    <i class="el-icon-s-home"></i>
                    我是标题!!!!
                  </span>
        <el-button-group style="margin-left: 5px">
          <el-button icon="el-icon-plus" size="mini" type="primary">新增用户</el-button>
        </el-button-group>
      </div>
      <div style="flex: 1;display: flex;align-items: center;justify-content: flex-end">
        <el-input style="width: 250px;margin-right: 5px" placeholder="请输入"></el-input>
        <el-button-group>
          <el-button icon="el-icon-search" size="mini" type="primary"></el-button>
          <el-button icon="el-icon-refresh" size="mini" type="primary"></el-button>
          <el-popover
              style="background: #f0f2f5;"
              placement="bottom"
              title="更多筛选"
              width="1200"
              trigger="click">
            <!--      更多筛选-->
            <div>
              <el-form ref="form" :model="form" label-position="top" label-width="auto">
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"/>
                  </el-col>
                </el-row>
                <div style="width: 100%;height: 1px;border: dashed 1px #ededed"></div>
                <el-row :gutter="20">
                  <el-col :span="6">
                    <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                             validationMessage="请输入用户1.validationMessage" :isRequirded="true"
                             placeholder="user1"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                  <el-col :span="6">
                    <ef-select
                        label="单项选择"
                        name="test"
                        v-model="form.test"
                        :require="true"
                        :emptyOption="true"
                        dictionaryNo="D_Frame_UserType"
                    />
                  </el-col>
                </el-row>
                <div style="display: flex;width: 100%;justify-content: flex-end">
                  <el-button icon="el-icon-s-open" size="mini" type="primary">筛选</el-button>
                  <el-button icon="el-icon-refresh" size="mini" type="primary">重置</el-button>
                </div>
              </el-form>
            </div>
            <el-button slot="reference" icon="el-icon-s-open" size="mini" type="primary">更多筛选</el-button>
          </el-popover>
        </el-button-group>
      </div>
    </el-header>
    <el-main style="height: calc(100% - 37px);">
      <basic-container style="padding: 15px 15px 20px;height: 100%">
        <ef-page-grid ref="grid1" queryNo="Frame_Query_002" pageSize="20" queryWindow="1" style="height:100%;"/>
      </basic-container>
    </el-main>

  </el-container>
</template>

<script>
export default {
  name: "rolelist"
}
</script>

<style scoped>

</style>