<template>
  <div class="home-box" style="height: 100%;width: 100%;overflow: hidden auto;">
    <div class="home-box-top">
      <div class="top-img1"><img src="./img/top-left.png" alt=""></div>
      <div class="top-img2"><img src="./img/top-right.png" alt=""></div>
      <div class="home-box-top-title">
        <span>开启今天的工作吧,加油,<span style="color: #1f7be9">{{ userInfo.userName }}</span>~</span>
      </div>
      <div class="home-box-top-content">
        <el-row :gutter="20">
          <el-col :span="8" v-show="userInfo.fixmedins_type !== '2'">
            <div class="home-box-top-item" @click="linkTo(1)">
              <div class="home-box-top-item-left">
                <div class="home-box-top-item-left-img"><img src="./img/mzgh.png" alt=""></div>
              </div>
              <div class="home-box-top-item-right">
                <div>
                  <div class="home-box-top-item-right-title1">门诊挂号</div>
                  <div class="home-box-top-item-right-title2">MEN&nbsp;&nbsp;ZHEN&nbsp;&nbsp;GUA&nbsp;&nbsp;HAO</div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="home-box-top-item" @click="linkTo(2)">
              <div class="home-box-top-item-left">
                <div class="home-box-top-item-left-img"><img src="./img/hjsf.png" alt=""></div>
              </div>
              <div class="home-box-top-item-right">
                <div>
                  <div class="home-box-top-item-right-title1">划价收费</div>
                  <div class="home-box-top-item-right-title2">HUA&nbsp;&nbsp;JIA&nbsp;&nbsp;SHOU&nbsp;&nbsp;FEI</div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="home-box-top-item" @click="linkTo(3)">
              <div class="home-box-top-item-left">
                <div class="home-box-top-item-left-img"><img src="./img/tfgl.png" alt=""></div>
              </div>
              <div class="home-box-top-item-right">
                <div>
                  <div class="home-box-top-item-right-title1">退费管理</div>
                  <div class="home-box-top-item-right-title2">TUI&nbsp;&nbsp;FEI&nbsp;&nbsp;GUAN&nbsp;&nbsp;LI</div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8" v-show="userInfo.fixmedins_type === '2'">
            <div class="home-box-top-item" @click="linkTo(4)">
              <div class="home-box-top-item-left">
                <div class="home-box-top-item-left-img"><img src="./img/mzgh.png" alt=""></div>
              </div>
              <div class="home-box-top-item-right">
                <div>
                  <div class="home-box-top-item-right-title1">日报表</div>
                  <div class="home-box-top-item-right-title2">RI&nbsp;&nbsp;BAO&nbsp;&nbsp;BIAO</div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="home-box-table">
      <el-row :gutter="20" style="height: 100%">
        <el-col :span="12" style="height: 100%">
          <div class="home-box-table-content">
            <div class="home-box-table-content-title">
              <span class="home-box-table-content-title-flag"></span>
              <span>公告资讯</span>
              <div class="more-list" @click="loadMore()" v-show="noticeList.length>3">更多>></div>
            </div>
            <div class="home-box-table-scroll">
              <div class="notice-list" v-for="(pageDO, index) in noticeList" @click="openWindow(pageDO.WRITEID)"
                   :key="index" v-if="index<3">
                <div v-if="pageDO.WRITEID=='0'" class="notice-list-left" style="width: 100%">
                  <div class="notice-list-point"></div>
                  <div class="notice-list-text" style="max-width:100%;">
                    <a style="color: #d26a5c;" v-html="pageDO.TITLE"></a>
                  </div>
                </div>
                <div v-else class="notice-list-left">
                  <div class="notice-list-point"></div>
                  <div class="notice-list-text">
                    <a v-html="pageDO.TITLE"></a>
                  </div>
                </div>
                <div class="notice-list-time">{{ subStr(pageDO.STARTDATE, 10) }}</div>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="12" style="height: 100%">
          <div class="home-box-table-content home-box-table-content-right">
            <div class="home-box-table-content-title">
              <span class="home-box-table-content-title-flag"></span>
              <span>下载</span>
              <div class="more-list" @click="moreFile()" v-show="downloadList.length>3">更多>></div>
            </div>
            <div class="home-box-table-scroll">
              <div class="notice-list" v-for="(pageDO, index) in downloadList" :key="index" @click="download(pageDO)"
                   v-if="index<3">
                <div class="notice-list-left">
                  <div class="notice-list-point"></div>
                  <div class="notice-list-text" @click="showPageContent(pageDO)">
                    <a>{{ pageDO.PAGETITLE }}</a>
                  </div>
                  <div class="table-xiazai" v-show="isNotNull(pageDO.PAGEID)"><img src="./img/xz.png" alt=""></div>
                </div>
                <div class="notice-list-time">{{ subStr(pageDO.CREATETIME, 10) }}</div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="home-box-bottom">
      <div class="home-box-bottom-list">
        <el-row :gutter="20" style="height: 100%">
          <el-col :span="6" style="height: 100%">
            <div class="home-box-bottom-list-item">
              <div class="home-box-bottom-list-item-box">
                <div class="home-box-bottom-list-item-box-top">
                  <div class="home-box-bottom-list-item-box-top-flag">
                    <div class="home-box-bottom-list-item-box-top-title">
                      <div class="home-box-bottom-list-item-box-top-title-left">01</div>
                      <div class="home-box-bottom-list-item-box-top-title-mid"></div>
                      <div class="home-box-bottom-list-item-box-top-title-right">目录管理</div>
                    </div>
                  </div>
                </div>
                <div class="home-box-bottom-list-item-content">
                  <div class="home-box-bottom-list-item-content-text">
                    可进行药品、诊疗、材料与国家医保标准库的对照，并可对已对照的项目进行查询及维护，同时支持本机构项目库的导出。可进行药品、诊疗、材料与国家医保标准库的对照，并可对已对照的项目进行查询及维护，同时支持本机构项目库的导出。
                  </div>
                  <div class="home-box-bottom-list-item-content-btn" @click="showPDFdetail('1')">
                    <div>操作说明</div>
                    <i style="font-size: 15px;margin-left: 5px" class="fa-chevron-circle-right fa"></i>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="home-box-bottom-list-item">
              <div class="home-box-bottom-list-item-box">
                <div class="home-box-bottom-list-item-box-top">
                  <div class="home-box-bottom-list-item-box-top-flag">
                    <div class="home-box-bottom-list-item-box-top-title">
                      <div class="home-box-bottom-list-item-box-top-title-left">02</div>
                      <div class="home-box-bottom-list-item-box-top-title-mid"></div>
                      <div class="home-box-bottom-list-item-box-top-title-right">组套管理</div>
                    </div>
                  </div>
                </div>
                <div class="home-box-bottom-list-item-content">
                  <div class="home-box-bottom-list-item-content-text">可对常用的中药方剂、诊疗套餐等进行管理，方便划价结算时可快速操作。</div>
                  <div class="home-box-bottom-list-item-content-btn" @click="showPDFdetail('2')">
                    <div>操作说明</div>
                    <i style="font-size: 15px;margin-left: 5px" class="fa-chevron-circle-right fa"></i>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="home-box-bottom-list-item">
              <div class="home-box-bottom-list-item-box">
                <div class="home-box-bottom-list-item-box-top">
                  <div class="home-box-bottom-list-item-box-top-flag">
                    <div class="home-box-bottom-list-item-box-top-title">
                      <div class="home-box-bottom-list-item-box-top-title-left">03</div>
                      <div class="home-box-bottom-list-item-box-top-title-mid"></div>
                      <div class="home-box-bottom-list-item-box-top-title-right">结算管理</div>
                    </div>
                  </div>
                </div>
                <div class="home-box-bottom-list-item-content">
                  <div class="home-box-bottom-list-item-content-text">可进行门诊挂号、划价收费等机构的日常主要操作，自费患者和医保患者均可支持。同时支持退费功能。
                  </div>
                  <div class="home-box-bottom-list-item-content-btn" @click="showPDFdetail('3')">
                    <div>操作说明</div>
                    <i style="font-size: 15px;margin-left: 5px" class="fa-chevron-circle-right fa"></i>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="home-box-bottom-list-item">
              <div class="home-box-bottom-list-item-box">
                <div class="home-box-bottom-list-item-box-top">
                  <div class="home-box-bottom-list-item-box-top-flag">
                    <div class="home-box-bottom-list-item-box-top-title">
                      <div class="home-box-bottom-list-item-box-top-title-left">04</div>
                      <div class="home-box-bottom-list-item-box-top-title-mid"></div>
                      <div class="home-box-bottom-list-item-box-top-title-right">系统管理</div>
                    </div>
                  </div>
                </div>
                <div class="home-box-bottom-list-item-content">
                  <div class="home-box-bottom-list-item-content-text">
                    可对本机构的信息进行维护，包括机构的基础信息、硬件信息、登录用户信息等；对于诊所，同时可进行科室及医生信息的维护。
                  </div>
                  <div class="home-box-bottom-list-item-content-btn" @click="showPDFdetail('4')">
                    <div>操作说明</div>
                    <i style="font-size: 15px;margin-left: 5px" class="fa-chevron-circle-right fa"></i>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>

import {mapGetters} from "vuex";
import {BaseCtrl} from '/src/util/eframe'
import constData from "../../../const";
import {getStore, setStore} from '@/util/store';
import {validatenull} from "@/util/validate";
import ViewsRouter from "@/router/views";


export default {
  name: "indexchgr",
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  data() {
    return {
      noticeList: [],
      downloadList: [],
    }
  },
  computed: {
    ...mapGetters(["userInfo", "website"]),
  },
  created() {
    this.loadInvalidMedlist();

    this.loadDownloadList();
    this.loadNoticeList2();
  },
  methods: {
    moreFile() {
      this.openMainTag({
        id: "noticedownloadlistmgmt",
        title: "下载列表",
        view: "/views/ehis/sysmanage/notice/noticedownload/noticedownloadlistshow",
        reloadFlag: false,
        iframeFlag: false,
        params: {}
      });
    },
    linkTo(num) {
      let name = ''
      let src = ''
      let meta = {
        il8n: '',
        keepAlive: true
      }
      if (num == 1) {
        name = '门诊挂号'
        src = '/MU1102'
        meta.il8n = '门诊挂号'
      }
      if (num == 2) {
        name = '划价收费'
        src = '/MU1103'
        meta.il8n = '划价收费'
      }
      if (num == 3) {
        name = '退费'
        src = '/MU1104/MU110401'
        meta.il8n = '退费'
      }
      if (num == 4) {
        name = '收款日报表'
        src = '/MU1108/MU110801'
        meta.il8n = '收款日报表'
      }
      if (this.website.mainPageFrameType == 'T2') {
        this.$store.commit('SET_T2MENU', 'MU11')
        let path = this.$router.$avueRouter.getPath({
          name: name,
          src: src
        }, meta)
        this.$router.push({
          path: '/MU11' + this.$router.$avueRouter.getPath({
            name: name,
            src: src
          }, meta),
          query: {topId: 'MU11'},
        });
      } else {
        this.$router.push({
          path: '/MU11' + src,
          query: {topId: ''},
        });
      }
    },
    subStr(str, length) {
      if (this.isNotNull(str)) {
        if (str.length < length) {
          return str;
        } else {
          return str.substring(0, length)
        }
      } else {
        return "";
      }
    },
    loadMore() {
      this.openMainTag({
        id: "noticepublishlist",
        title: "公告列表",
        view: "/views/ehis/sysmanage/notice/noticepublish/noticepublishlistshow",
        reloadFlag: false,
        iframeFlag: false,
        params: {}
      });
    },
    openWindow(writeid) {
      let that = this;
      let params = {
        writeId: writeid,
      };
      // if (writeid == that.invalidNotice.WRITEID) {
      //   params.textContent = that.invalidNotice.TEXTCONTENT;
      // }
      if (isNotNull(writeid)) {
        this.openDialog({
          title: "公告详情",
          view: "/views/ehis/sysmanage/notice/noticepublish/noticepublishdetailshow",
          width: '600px',
          // height:'',
          params: params
        });
      }
    },

    loadInvalidMedlist() {
      let that = this;
      that.noticeList = [];
      let options = {
        queryNo: "Q_InvalidMedlist",
        queryWindow: "1",
        pageSize: 99,
        pageIndex: 1,
        parameters: {
          fixmedins_id: that.userInfo.fixmedins_id
        }
      };
      let succFun = function (data) {
        // let invalidMag = "";
        let sumNum = 0;
        if (!that.isListEmpty(data)) {
          for (let item of data) {
            sumNum += item.NUM;
            // if (item.NUM > 0) {
            //   invalidMag += item.NUM + "个" + item.NAME + ","
            // }
          }
        }
        if (sumNum > 0) {
          that.showInvalid = true;
          that.showNoticeNum -= 1;
          // let textContent = "您有" + invalidMag + "在国家库中已作废，请前往【三目对照】下相应模块进行处理！"
          let title = "您有" + sumNum + "个已对照项目在国家库已作废，请前往【三目对照】下相应模块进行处理！"
          let invalidNotice = {
            WRITEID: "0",
            TITLE: title,
            // TEXTCONTENT: textContent,
          };
          that.noticeList.push(invalidNotice);
        }
        that.loadNoticeList();
      }
      this.$eframe.pageQuery(options, succFun, null);
    },

    //获取通知list
    loadNoticeList() {
      let that = this;
      let options = {
        queryNo: "Q_NoticePublish_002",
        queryWindow: "1",
        pageSize: 3,
        pageIndex: 1,
        whereCondition: "1=1 AND to_char(sysdate,'yyyy-MM-dd')>=startdate",
        parameters: {}
      }
      let succFun = function (data) {
        that.noticeList = that.noticeList.concat(data);
        if (that.noticeList.length < 3) {
          for (let i = that.noticeList.length; i < 3; i++) {
            let item = {};
            that.noticeList.push(item);
          }
        }
      }
      let failFun = function (data) {

      }
      that.$eframe.pageQuery(options, succFun, null);
    },

    loadNoticeList2() {
      let that = this;
      let options = {
        queryNo: "Q_NoticePublish_002",
        queryWindow: "1",
        pageSize: 999,
        pageIndex: 1,
        whereCondition: "1=1 AND to_char(sysdate,'yyyy-MM-dd')>=startdate and to_date(startdate,'yyyy-MM-dd')>=trunc(sysdate-3) and showflag='1'",
        parameters: {}
      }
      let succFun = function (data) {
        if (data.length > 0) {
          if (isNull(getStore({name: 'showPageFlag'}))) {
            setStore({
              name: 'showPageFlag',
              content: '1'
            })
            that.openDialog({
              title: "公告详情",
              view: "/views/ehis/sysmanage/notice/noticepublish/noticepublishdetailshow-out",
              width: '600px'
            });
          }
        }
      }
      let failFun = function (data) {

      }
      that.$eframe.pageQuery(options, succFun, failFun);
    },


    loadDownloadList() {
      let that = this;
      let options = {
        queryNo: "Q_NoticeDownload_002",
        queryWindow: "1",
        pageSize: 50,
        pageIndex: 1,
        whereCondition: "1=1 AND to_char(sysdate,'yyyy-MM-dd')>=createtime",
        parameters: {}
      }
      let succFun = function (data) {
        that.downloadList = data;
        for (let i = that.downloadList.length; i < 3; i++) {
          let item = {};
          that.downloadList.push(item);
        }
      }
      let failFun = function (data) {

      }
      that.$eframe.pageQuery(options, succFun, failFun);
    },
    showPageContent(pageDO) {
      if (isNotNull(pageDO.pageid)) {
        if (pageDO.pagedoctype.toUpperCase() === 'PDF') {
          // window.open(constData.baseUrl + pageDO.classurl + pageDO.pageurl,pageDO.pageurl);
          window.open(pageDO.pageurl, pageDO.pageurl);
        } else {
          window.location.href = pageDO.pageurl;
        }
      }
    },
    //下载
    download(pageDO) {
      if (isNotNull(pageDO.PAGEID)) {
        let that = this;
        //获取信息
        let form = {
          pageid: pageDO.PAGEID
        }
        let callbackFun = function (data) {
          window.location.href = constData.baseUrl + pageDO.PAGEURL;
        }
        this.$eframe.submitTargetForm({
          url: '/ehis/sysmanage/notice/noticemgmt.action',
          form: form,
          actionFunName: "downLoadFile",
          succFun: callbackFun,
          failFun: null
        });
      }
    },
    showPDFdetail(index) {
      var obj;
      if (index == '1') {
        obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualsanmuduizhao.pdf', '_target', 'height=700,width=1200');
        setTimeout(() => {
          obj.document.title = '目录管理'
        }, 500)
      } else if (index == '2') {
        obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualzutaoguanli.pdf', '_target', 'height=700,width=1200');
        setTimeout(() => {
          obj.document.title = '组套管理'
        }, 500)
      } else if (index == '3') {
        //非定点
        if (this.userInfo.fix_flag == '0') {
          //诊所
          if (this.userInfo.fixmedins_type == '1') {
            obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualjiesuanfeidingdianzhensuo.pdf', '_target', 'height=700,width=1200');
          } else if (this.userInfo.fixmedins_type == '2') {//药店
            obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualjiesuanfeidingdianyaodian.pdf', '_target', 'height=700,width=1200');
          }
        } else {
          //诊所
          if (this.userInfo.fixmedins_type == '1') {
            obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualjiesuandingdianzhensuo.pdf', '_target', 'height=700,width=1200');
          } else if (this.userInfo.fixmedins_type == '2') {//药店
            obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualjiesuandingdianyaodian.pdf', '_target', 'height=700,width=1200');
          }
        }
        setTimeout(() => {
          obj.document.title = '结算管理'
        }, 500)
      } else if (index == '4') {
        obj = window.open(constData.baseUrl + '/resources/file/xz/' + 'OperationManualjichuxinxiweihu.pdf', '_target', 'height=700,width=1200');
        setTimeout(() => {
          obj.document.title = '系统管理'
        }, 500)
      }
    }
  },
}
</script>

<style scoped lang="scss">

::v-deep div .ql-container {
  border: none;
}

::v-deep .ql-toolbar {
  display: none;
}

.home-box {
  width: 100%;
  height: 100%;
  padding: 25px;
  background: #fff;
  overflow: hidden auto;

  .home-box-top {
    width: 100%;
    height: 190px;
    background: #eaf4fe;
    position: relative;
    padding-top: 30px;

    .top-img1 {
      width: 133px;
      height: 171px;
      position: absolute;
      bottom: 0;
      left: 0;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .top-img2 {
      width: 275px;
      height: 200px;
      position: absolute;
      bottom: 0;
      right: 0;

      img {
        width: 100%;
        height: 100%;
      }
    }

    .home-box-top-title {
      width: 100%;
      margin-left: 40px;
      font-size: 16px;
      font-weight: bold;
      color: #333;
    }

    .home-box-top-content {
      margin: 30px 40px;
      min-width: 900px;
      max-width: 1100px;

      .home-box-top-item {
        //width: 100%;
        height: 80px;
        background: #fff;
        display: flex;
        cursor: pointer;
        width: 300px;


        .home-box-top-item-left {
          width: 126px;
          height: 100%;
          background: #71b3fc;
          position: relative;
          padding-top: 25px;
          flex-shrink: 0;

          .home-box-top-item-left-img {
            width: 50px;
            height: 50px;
            margin-left: 30px;

            img {
              width: 100%;
              height: 100%;
            }
          }
        }

        .home-box-top-item-left:before {
          content: '';
          position: absolute;
          right: 0;
          bottom: 0;
          width: 0;
          height: 0;
          border-color: #fff transparent; /*上下颜色 左右颜色*/
          border-width: 80px 0 0 40px;
          border-style: solid;
        }

        .home-box-top-item-right {
          width: calc(100% - 126px);
          flex-shrink: 0;
          display: flex;
          align-items: center;
          justify-content: center;
          text-align: center;

          .home-box-top-item-right-title1 {
            font-size: 16px;
            font-weight: bold;
            color: #333;
          }

          .home-box-top-item-right-title2 {
            font-size: 12px;
            font-weight: 400;
            color: #bcbbbb;
            margin-top: 5px;
          }
        }
      }
    }
  }

  .home-box-table {
    margin-top: 20px;
    width: 100%;
    height: 187px;

    .home-box-table-content {
      width: 100%;
      height: 100%;
      background-color: #f1f8fe;
      padding: 0 40px;
      background-image: url("./img/table-left.png");
      background-size: 140px 145px;
      background-repeat: no-repeat;
      background-position: right top;

      .home-box-table-left {
        position: absolute;
        width: 140px;
        height: 145px;
        top: 0;
        right: 0;

        img {
          width: 100%;
          height: 100%;
        }
      }

      .home-box-table-content-title {
        width: 100%;
        height: 50px;
        display: flex;
        align-items: center;
        font-size: 16px;
        font-weight: bold;
        color: #479efd;
        position: relative;

        .more-list {
          position: absolute;
          right: 0;
          top: 0;
          bottom: 0;
          width: 60px;
          cursor: pointer;
          display: flex;
          align-items: center;
          justify-content: flex-end;
          font-size: 14px;
          color: #479efd;
        }

        .home-box-table-content-title-flag {
          width: 4px;
          height: 16px;
          background: #479efd;
          margin-right: 10px;
        }
      }

      .home-box-table-scroll {
        height: 102px;
        width: 100%;
        overflow: hidden auto;
        background: #fff;

        .notice-list {
          height: 34px;
          display: flex;
          align-items: center;
          justify-content: space-between;
          padding: 0 28px;
          cursor: pointer;

          .notice-list-left {
            width: calc(100% - 100px);
            flex-shrink: 0;
            display: flex;
            align-items: center;

            .notice-list-point {
              display: inline-block;
              width: 6px;
              height: 6px;
              border-radius: 100%;
              background: #d1d0d0;
              margin-right: 12px;
            }

            .notice-list-text {
              display: inline-block;
              max-width: calc(100% - 50px);
              color: #333;
              font-weight: 500;
              font-size: 14px;
              white-space: nowrap;
              text-overflow: ellipsis;
              overflow: hidden;

              a {
                color: #333;
              }
            }
          }

          .notice-list-time {
            width: 100px;
            flex-shrink: 0;
            color: #999;
            font-weight: 500;
            font-size: 14px;
            text-align: right;
          }

          .table-xiazai {
            width: 14px;
            height: 14px;
            margin-left: 12px;
            cursor: pointer;

            img {
              width: 100%;
              height: 100%;
            }
          }
        }

        .notice-list:nth-child(even) {
          background: #f6f7fb;
        }
      }


    }

    .home-box-table-content-right {
      background-image: url("./img/table-right.png");
      background-size: 100px 132px;
    }
  }

  .home-box-bottom {
    margin-top: 20px;
    width: 100%;
    background: rgba(230, 242, 254, .46);
    min-height: calc(100% - 417px);
    padding: 30px;

    .home-box-bottom-list:first-child {
      margin-top: 0;
    }

    .home-box-bottom-list {
      margin-top: 30px;
      width: 100%;
      display: flex;
      height: 390px;
      //margin-left: -30px;
      .home-box-bottom-list-item {
        //width:360px;
        //max-width: 380px;
        height: 390px;
        //margin-left: 30px;
        .home-box-bottom-list-item-box {
          width: 100%;
          height: 100%;
          background: #fff;

          .home-box-bottom-list-item-box-top {
            width: 100%;
            display: flex;
            justify-content: center;
            padding: 38px 0 28px;

            .home-box-bottom-list-item-box-top-flag {
              width: 142px;
              height: 22px;
              background: rgba(71, 158, 253, .22);
              border-radius: 11px;
              position: relative;

              .home-box-bottom-list-item-box-top-title {
                position: absolute;
                height: 38px;
                bottom: 6px;
                width: 100%;
                display: flex;
                justify-content: center;
                align-items: center;

                .home-box-bottom-list-item-box-top-title-left {
                  font-size: 27px;
                  color: #479EFD;
                  font-weight: bold;
                }

                .home-box-bottom-list-item-box-top-title-mid {
                  width: 1px;
                  background: #478efd;
                  height: 100%;
                  transform: rotate(30deg);
                  margin: 0 8px;
                }

                .home-box-bottom-list-item-box-top-title-right {
                  font-size: 16px;
                  color: #66ABFD;
                  font-weight: bold;
                }
              }
            }
          }

          .home-box-bottom-list-item-content {
            height: 270px;
            margin: 0 30px;
            background-color: #EFF7FF;
            border-radius: 20px;
            background-image: url("./img/home-bottom-bg.png");
            background-repeat: no-repeat;
            background-position: -15px 190px;

            .home-box-bottom-list-item-content-text {
              font-size: 14px;
              font-weight: 500;
              color: #333;
              line-height: 26px;
              word-break: break-all;
              padding: 40px;
              height: 175px;
              overflow: hidden;
              text-overflow: ellipsis;
              display: -webkit-box;
              -webkit-line-clamp: 5;
              -webkit-box-orient: vertical;
            }

            .home-box-bottom-list-item-content-btn {
              width: 114px;
              margin: 20px auto 0;
              height: 36px;
              background: #fff;
              border-radius: 18px;
              border: 1px solid #479EFD;
              display: flex;
              align-items: center;
              justify-content: center;
              font-size: 14px;
              font-weight: 500;
              color: #479efd;
              cursor: pointer;
            }
          }
        }
      }
    }
  }
}
</style>