<template>
  <div style="margin:15px 15px 0;">
    <div class="menu-home-content-bg">
      <el-row>
        <el-col :span="16" :offset="4" style="min-width: 800px">
          <el-row :gutter="20">
            <el-col :span="16">
              <div style="height: 300px;width: 100%;background: #fff;box-shadow: 0 0 18px 0 rgba(122 128 136 0.3)">
                <div style="width: 100%;padding: 15px 0">
                  <div
                      style="height: 40px;line-height: 40px;border-left: solid 8px rgba(64, 158, 255, 1);background: #eff5fd;padding-left: 20px;color: rgba(64, 158, 255, 1);font-size: 16px;font-weight: bold">
                    通知公告
                  </div>
                </div>
                <div style="height: calc(100% - 70px);padding:0 15px 15px;width: 100%">
                  <div style="height: 100%;overflow: hidden auto;width: 100%;font-size: 14px">
                    <div class="notice-list"
                         style="width: 100%;display: flex;height: 40px;align-items: center;justify-content: space-between;padding:0 15px">
                      <div style="width: calc(100% - 100px);">
                        <span>1、通知，苏州云诊所(简易HIS)正式上线了！</span>
                      </div>
                      <div style="color: #999;width: 100px;flex-shrink: 0">2021-09-30</div>
                    </div>
                    <div class="notice-list"
                         style="width: 100%;display: flex;height: 40px;align-items: center;justify-content: space-between;padding:0 15px">
                      <div style="width: calc(100% - 100px);">
                        <a :href="baseUrl+'/resources/file/云诊所系统操作手册.docx'">
                          <span style="width: 100%;">2、系统操作手册下载</span>
                        </a>
                      </div>
                      <div style="color: #999;width: 100px;flex-shrink: 0">2021-09-30</div>
                    </div>
                    <div class="notice-list" v-for="(item,index) in 3"
                         style="width: 100%;display: flex;height: 40px;align-items: center;justify-content: space-between;padding:0 15px">
                      <div style="width: calc(100% - 100px);">
<!--                        <el-link style="width: 100%" href="https://baidu.com" target="_blank" :underline="false">百度-->
<!--                        </el-link>-->
                      </div>
                      <div style="color: #999;width: 100px;flex-shrink: 0"></div>
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <el-card
                  style="height: 90px;width: 100%;background:#ee9c50;cursor: pointer;box-shadow: 0 0 18px 0 rgba(122 128 136 0.3)">
                <div
                    style="width: 80px;height: 100%;display: flex;align-items: center;justify-content: center;color: #fff;font-size: 30px">
                  <i class="fa fa-hospital-o"></i>
                </div>
                <div class="box-right">
                  <div style="color: #fff;font-size: 16px;font-weight: bold;">门诊挂号</div>
                  <div style="border-top: solid 1px #fff;color: #fff;margin-top: 5px">
                    <div style="margin-top: 5px">
                      <span>今日挂号数:</span>
                      <span style="margin-left: 5px">{{ form.now_optnosum }}</span>
                    </div>
                    <div style="margin-top:5px;">
                      <span>昨日挂号数:</span>
                      <span style="margin-left: 5px">{{ form.yest_optnosum }}</span>
                    </div>
                  </div>
                </div>
              </el-card>
              <el-card
                  style="height: 90px;width: 100%;margin-top: 15px;background:#8e84b9;cursor: pointer;box-shadow: 0 0 18px 0 rgba(122 128 136 0.3)">
                <div
                    style="width: 80px;height: 100%;display: flex;align-items: center;justify-content: center;color: #fff;font-size: 30px">
                  <i class="el-icon-money"></i>
                </div>
                <div class="box-right">
                  <div style="color: #fff;font-size: 16px;font-weight: bold;">划价收费</div>
                  <div style="border-top: solid 1px #fff;color: #fff;margin-top: 5px">
                    <div style="margin-top: 5px">
                      <span>今日收费金额:</span>
                      <span style="margin-left: 5px">{{ form.now_setlsum }}元</span>
                    </div>
                    <div style="margin-top:5px;">
                      <span>昨日收费金额:</span>
                      <span style="margin-left: 5px">{{ form.yest_setlsum }}元</span>
                    </div>
                  </div>
                </div>
              </el-card>
              <el-card
                  style="height: 90px;width: 100%;margin-top: 15px;background:#a8d151;cursor: pointer;box-shadow: 0 0 18px 0 rgba(122 128 136 0.3)">
                <div
                    style="width: 80px;height: 100%;display: flex;align-items: center;justify-content: center;color: #fff;font-size: 30px">
                  <i class="fa fa-money"></i>
                </div>
                <div class="box-right">
                  <div style="color: #fff;font-size: 16px;font-weight: bold;">退费</div>
                  <div style="border-top: solid 1px #fff;color: #fff;margin-top: 5px">
                    <div style="margin-top: 5px">
                      <span>今日退费金额:</span>
                      <span style="margin-left: 5px">{{ form.now_refdsum }}元</span>
                    </div>
                    <div style="margin-top:5px;">
                      <span>昨日退费金额:</span>
                      <span style="margin-left: 5px">{{ form.yest_refdsum }}元</span>
                    </div>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <!--        功能菜单-->
          <div style="margin: 15px 0">
            <div class="menu-home-header">
              <div class="menu-home-title">
                <span style="margin-left: 20px"><i class="el-icon-collection" style="margin-right: 5px"></i>功能菜单</span>
              </div>
              <div class="menu-home-header-right">
                <span @click="changeSelect" style="cursor:pointer;">
                  <img v-if="allFlag != true" src="./img/down.png" alt="" width="14">
                  <img v-if="allFlag == true" src="./img/up.png" alt="" width="14">
                </span>
              </div>
            </div>
            <div class="menu-home-box" v-if="allFlag">
              <el-row>
                <el-col :span="6" v-show="check('/MU01/MU0101/MU010101')">
                  <div @click="linkTo('/MU01/MU0101/MU010101')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-set-up"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      药品对照
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="药品对照。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU01/MU0102/MU010201')">
                  <div @click="linkTo('/MU01/MU0102/MU010201')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-set-up"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      诊疗对照
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="诊疗对照。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU01/MU0103/MU010301')">
                  <div @click="linkTo('/MU01/MU0103/MU010301')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="el-icon-set-up"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      材料对照
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="材料对照。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU02/MU0204')&&isFix">
                  <div @click="linkTo('/MU02/MU0204')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="fa fa-address-card-o"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      参保信息查询
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="参保信息查询。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU02/MU0202')">
                  <div @click="linkTo('/MU02/MU0202')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-c-scale-to-original"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      门诊挂号
                      <span @click.stop="() => {return false}">
                        <el-tooltip placement="bottom" content="门诊挂号。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU02/MU0201')">
                  <div @click="linkTo('/MU02/MU0201')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-money"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      划价收费
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="划价收费。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU02/MU0203')">
                  <div @click="linkTo('/MU02/MU0203')"
                       class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="fa fa-money"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      退费
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="退费。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU04/MU0401')">
                  <div @click="linkTo('/MU04/MU0401')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-edit-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      机构维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="机构维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU04/MU0402')">
                  <div @click="linkTo('/MU04/MU0402')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-edit-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      本机构信息维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="本机构信息维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU04/MU0403')">
                  <div @click="linkTo('/MU04/MU0403')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-edit-outline"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      科室维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="科室维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU04/MU0404')">
                  <div @click="linkTo('/MU04/MU0404')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="fa fa-user-md"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      医生维护
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="医生维护。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU06/MU0601')">
                  <div @click="linkTo('/MU06/MU0601')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="green-bg">
                      <span><i class="fa fa-search"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      交易记录查询
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="交易记录查询。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU06/MU0602')">
                  <div @click="linkTo('/MU06/MU0602')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="blue-bg">
                      <span><i class="el-icon-pie-chart"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      门诊收款日报表
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="门诊收款日报表。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" v-show="check('/MU07/MU0701')">
                  <div @click="linkTo('/MU07/MU0701')" class="menu-home-box-img menu-home-box-pointer">
                    <div class="info-bg">
                      <span><i class="el-icon-tickets"></i></span>
                    </div>
                    <div class="menu-bg-title">
                      发票管理
                      <span @click.stop="() => {return false}">
                        <el-tooltip
                            placement="bottom"
                            content="发票管理。">
                          <span><i style="color: #f7b75d" class="el-icon-question"></i></span>
                        </el-tooltip>
                      </span>
                    </div>
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
import {mapGetters} from "vuex";
import {BaseCtrl} from '/src/util/eframe'
import constData from '@/const/index'

export default {
  name: "exampleindex",
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  data() {
    return {
      isFix:true,
      baseUrl: "",
      allFlag: true,
      form: {
        fixmedins_id: '',
        now_optnosum: '',		//今日挂号数
        yest_optnosum: '',		//昨日挂号数

        now_setlsum: '',		//今日收费金额
        yest_setlsum: '',		//昨日收费金额

        now_refdsum: '',		//今日退费金额
        yest_refdsum: '',		//昨日退费金额
      }
    }
  },
  computed: {
    ...mapGetters(["userInfo", "menuAll"])
  },
  created() {
    this.form.fixmedins_id = this.userInfo.fixmedins_id;
    this.baseUrl = constData.electronBaseUrl;
    if (this.userInfo.fix_onln_open_flag === "1" && this.userInfo.fix_flag === "1") {
      this.isFix = true;
    } else {
      this.isFix = false;
    }
    this.loadFeeInfo();
  },
  methods: {
    changeSelect() {
      this.allFlag = !this.allFlag
    },
    linkTo(path) {
      this.$router.push({
        path: path,
      })
    },

    check(path) {
      let menu = JSON.stringify(this.menuAll);
      if (menu.indexOf('\"' + path + '\"') > 0) {
        return true;
      } else {
        return false;
      }
    },

    loadFeeInfo(){
      let that = this;
      if(this.isNotNull(this.form.fixmedins_id)){
        let form = {
          fixmedins_id: this.form.fixmedins_id
        };
        let callbackFun = function (data) {
          if (that.$eframe.isNotNull(data)) {
            that.form = data;
          }
        };
        this.$eframe.submitTargetForm({
          url: '/ehis/homepagemgmt/homepagemgmt.action',
          form: form,
          actionFunName: "loadHomePage",
          succFun: callbackFun,
          failFun: null
        });
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

div > .notice-list:nth-child(odd) {
  background: #f2f2f2;
}

::v-deep .notice-list span {
  display: inline-block;
  width: 100%;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

::v-deep .notice-list .el-link {
  justify-content: flex-start;
}

::v-deep .el-card__body {
  padding: 0;
  height: 100%;
  display: flex;
}

.box-right {
  height: 100%;
  width: calc(100% - 80px);
  font-size: 12px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-right: 10px;
}
</style>