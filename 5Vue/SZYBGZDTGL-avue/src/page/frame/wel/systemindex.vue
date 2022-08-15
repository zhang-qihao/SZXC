<template>
  <div>
    <div class="menu-home-content-bg">
      <el-row>
        <el-col :span="16" :offset="4" style="min-width: 800px">
          <!--        用户管理-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-user" style="margin-right: 5px"></i>用户管理</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(0)" style="cursor:pointer;">
                  <img v-if="selected[0] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[0] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[0] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS01/SYS0101')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-user"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      用户管理
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="用户维护,用户权限。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS01/SYS0102')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-user"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      用户组维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="用户组维护,用户组权限。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS01/SYS0103')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-house"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      组织机构维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="组织机构维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        权限管理-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-folder-checked"
                                                   style="margin-right: 5px"></i>权限管理</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(1)" style="cursor:pointer;">
                  <img v-if="selected[1] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[1] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[1] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS02/SYS0201')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-files"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      系统维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip placement="bottom">
                          <div style="line-height: 20px" slot="content">平台说明,系统维护,<br/>系统下菜单维护,系统下模块维护,<br/>系统下模块下功能点维护,<br/>系统下接口维护,系统下接口下方法维护。</div>
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS02/SYS0202')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-view"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      角色维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="角色维护,角色权限。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div
                      @click="linkTo('/wel/system/SYS02/SYS0203')"
                      class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-edit-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      代办维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="委派人维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        数据管理-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-receiving" style="margin-right: 5px"></i>数据管理</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(2)" style="cursor:pointer;">
                  <img v-if="selected[2] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[2] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[2] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0301')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-search"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      查询配置中心
                      <span @click.stop="() => {return false}">
                        <el-tooltip placement="bottom">
                          <div style="line-height: 20px" slot="content">目录维护,新增查询,<br/>查询导出,Excel导入。</div>
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0302')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-guide"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      目录维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="目录维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0303')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-collection-tag"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      数据字典
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="数据字典维护,数据字典明细配置。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0304')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-coin"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      数据集
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="数据坐标维护,数据集维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0305')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-box"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      元数据
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="目录维护,新增元数据。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div
                      @click="linkTo('/wel/system/SYS03/SYS0306')"
                      class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-location-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      地理信息(GIS)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="点维护,路劲维护,区域维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS03/SYS0307')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-download"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      导入配置中心
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="目录维护,新增导入配置。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        流程管理-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-guide" style="margin-right: 5px"></i>流程管理</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(3)" style="cursor:pointer;">
                  <img v-if="selected[3] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[3] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[3] == true">
              <el-row>
                <el-col :span="6">
                  <div
                      @click="linkTo('/wel/system/SYS04/SYS0401')"
                      class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-folder-checked"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      流程设置
                      <span @click.stop="() => {return false}">
                        <el-tooltip placement="bottom" content="目录维护,角色单选,角色多选,新增审批模块。">
                          <div style="line-height: 20px" slot="content">目录维护,角色单选,<br/>角色多选,新增审批模块。</div>
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div
                      @click="linkTo('/wel/system/SYS04/SYS0402')"
                      class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-edit"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      流程绘制
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="目录维护,新增抽象流程,流程测试。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        日志服务-->
          <div style="margin: 15px 0">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-odometer" style="margin-right: 5px"></i>日志服务</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(4)" style="cursor:pointer;">
                  <img v-if="selected[4] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[4] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[4] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS05/SYS0501')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-connection"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      接口日志
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="接口日志管理。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS05/SYS0502')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-guide"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      菜单日志
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="菜单日志管理。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/system/SYS05/SYS0503')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-search"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      查询日志
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="查询日志管理。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'
import {mapGetters} from "vuex";

export default {
  name: "systemindex",
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  computed: {
    ...mapGetters(["website",]),
  },
  data() {
    return {
      selected: null,
    }
  },
  created() {
    this.selected = new Array(5).fill(true)
  },
  methods: {
    changeSelect(num) {
      this.selected.splice(num, 1, !this.selected[num])
    },
    linkTo(path) {
      if(this.website.mainPageFrameType == 'T2'){
        this.$router.push({
          path:path,
          query:{topId:'system'}
        })
      }else{
        let regPath = path.replace('/wel/system','')
        this.$router.push({
          path:regPath,
          query:{}
        })
      }
    }
  },
}
</script>

<style scoped>
.menu-home-content-bg {
  background-image: url("./img/content-bg.png");
  background-size: 1700px 730px;
}

.el-col {
  margin-bottom: 0;
}

/*.menu-home-header {*/
/*  width: 100%;*/
/*  height: 30px;*/
/*  display: flex;*/
/*  align-items: center;*/
/*  justify-content: space-between;*/
/*  border-bottom: solid 3px rgba(64, 158, 255, .5);*/
/*}*/

/*.menu-home-title {*/
/*  width: 160px;*/
/*  height: 100%;*/
/*  display: flex;*/
/*  align-items: center;*/
/*  background: rgba(64, 158, 255, .5);*/
/*  color: #fff;*/
/*  padding-right: 27px;*/
/*  position: relative;*/
/*  font-size: 16px;*/
/*  font-weight: bold;*/
/*}*/

/*.menu-home-title:before {*/
/*  content: '';*/
/*  position: absolute;*/
/*  right: 0;*/
/*  top: 0;*/
/*  width: 0;*/
/*  height: 0;*/
/*  border-color: transparent #f0f2f5; !*上下颜色 左右颜色*!*/
/*  border-width: 0 27px 27px 0;*/
/*  border-style: solid;*/
/*}*/

/*.menu-home-header-right {*/
/*  flex: 1;*/
/*  display: flex;*/
/*  align-items: center;*/
/*  justify-content: flex-end;*/
/*}*/

/*.menu-home-box-img {*/
/*  width: 100%;*/
/*  height: 80px;*/
/*  display: flex;*/
/*  align-items: center;*/
/*  padding: 0 30px;*/
/*  border-right: solid 1px #ededed;*/
/*  border-bottom: solid 1px #ededed;*/
/*  box-sizing: border-box;*/
/*}*/

/*.menu-home-box-pointer {*/
/*  cursor: pointer;*/
/*}*/

/*.menu-home-box {*/
/*  box-shadow: 0 0 18px 0 rgba(122, 128, 136, .3);*/
/*  background: #fff;*/
/*}*/

/*.menu-bg-title {*/
/*  flex: 1;*/
/*  font-size: 15px;*/
/*  font-weight: bold;*/
/*  color: #333;*/
/*  word-break: break-word;*/
/*  margin-left: 20px;*/
/*}*/

.blue-bg, .info-bg, .green-bg {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 4px;
}

.blue-bg, .info-bg, .green-bg > span > i {
  font-size: 16px;
  color: #fff;
}

.blue-bg {
  background: linear-gradient(-21deg, #0392E5, #64DFFE);
}

.info-bg {
  background: linear-gradient(-21deg, #858FCA, #BAC1FB);
}

.green-bg {
  background: linear-gradient(-21deg, #3AAD7B, #87F4C6);
}
</style>