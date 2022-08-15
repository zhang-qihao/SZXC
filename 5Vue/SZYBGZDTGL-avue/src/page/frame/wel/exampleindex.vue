<template>
  <div>
    <div class="menu-home-content-bg">
      <el-row>
        <el-col :span="16" :offset="4" style="min-width: 800px">
          <!--        ef标签-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-collection" style="margin-right: 5px"></i>ef标签</span>
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
                  <div @click="linkTo('/wel/example/EXP01/EXP0101')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="fa fa-tag"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      标签示例(ef-tags)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="标签使用示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP01/EXP0102')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-notebook-2"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      分页查询(PageGrid)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="分页查询示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP01/EXP0103')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-edit"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      可编辑查询(GridEditor)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="可编辑查询示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP01/EXP0104')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-search"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      树状查询(Tree)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="树状查询示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP01/EXP0105')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="fa fa-list"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      树状列表(TreeList)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="树状列表示例。">
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
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        界面用例-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i style="margin-right:5px"
                                                   class="el-icon-copy-document"></i>界面用例</span>
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
                  <div @click="linkTo('/wel/example/EXP02/EXP0201/EXP020101')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-tickets"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      表单界面
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="表单界面模板示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP02/EXP0202/EXP020201')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-c-scale-to-original"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      展示界面
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="展示界面模板示例。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP02/EXP0203/EXP020301')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="fa fa-search"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      查询界面
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="查询界面模板示例。">
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
          <!--        JavaScript-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
              <span style="margin-left: 20px">
                <i class="el-icon-edit-outline" style="margin-right: 5px"></i>
                JavaScript
              </span>
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
                  <div @click="linkTo('/wel/example/EXP03')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-edit-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      JavaScript
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="js使用示例。">
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
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        权限示例-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-finished" style="margin-right: 5px"></i>权限示例</span>
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
                  <div @click="linkTo('/wel/example/EXP04')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-finished"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      权限示例
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="模块功能权限示例,接口方法权限示例。">
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
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        图表(Echarts)-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-pie-chart" style="margin-right: 5px"></i>图表</span>
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
                  <div @click="linkTo('/wel/example/EXP05')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-pie-chart"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      图表(Echarts)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="图表使用示例。">
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
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        地图组件(GIS)-->
          <div style="margin-top: 15px">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-map-location"
                                                   style="margin-right: 5px"></i>地图组件</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(5)" style="cursor:pointer;">
                  <img v-if="selected[5] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[5] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[5] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/EXP06')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-map-location"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      地图组件(GIS)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="地图组件示例。">
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
                <el-col :span="6">
                  <div class="menu-home-box-img">

                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
          <!--        文档编辑(Onlyoffice)-->
          <div style="margin: 15px 0">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-s-cooperation"
                                                   style="margin-right: 5px"></i>文档编辑</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect(6)" style="cursor:pointer;">
                  <img v-if="selected[6] != true" src="./img/down.png" alt="" width="14">
                  <img v-if="selected[6] == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="selected[6] == true">
              <el-row>
                <el-col :span="6">
                  <div @click="linkTo('/wel/example/FILE04/FILE0401')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-s-cooperation"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      文档编辑(Onlyoffice)
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="文档编辑示例。">
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
  name: "exampleindex",
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
    this.selected = new Array(7).fill(true)
  },
  methods: {
    changeSelect(num) {
      this.selected.splice(num, 1, !this.selected[num])
    },
    linkTo(path) {
      if(this.website.mainPageFrameType == 'T2'){
        this.$router.push({
          path:path,
          query:{topId:'example'}
        })
      }else{
        let regPath = path.replace('/wel/example','')
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