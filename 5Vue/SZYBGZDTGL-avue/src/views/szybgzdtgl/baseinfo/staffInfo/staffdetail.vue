<template>
  <el-container style="height: 100%;height: 600px">
    <!--    页头-->
    <el-header>
      <div class="header-left">
        <div class="header-title" slot="title" style="color: #0eb1a1">{{ title1 }}</div>
      </div>
      <div class="header-right"/>
    </el-header>

    <!--    主视图-->
    <el-main style="height: calc(100% - 37px);overflow: auto;">
      <div class="main-content">
        <!--        人员基础信息块-->
        <base-block class="out-block" rounded ref="exampleBlock2">
          <div class="header-title" slot="title">人员基础信息</div>

          <el-form ref="form" :model="form" label-position="left" label-width="auto">
            <!--            工作照div-->
            <div id="div_picture" style="text-align: center;display:block">
              <img width="100" height="120" style="margin-left: 5px;" id="docid"
                   src="/util/screen/gerenzhaopian.png"/><br>
              <span><span style="color: red">*</span>工作照</span>
            </div><!--工作照结束-->

            <!--            基础信息div-->
            <div id="div_basic_info">
              <!--              第一行-->
              <el-row :gutter="24">
                <!--                第一行第一列-->
                <el-col :span="8">
                  <ef-text label="人员姓名" v-model="form.aac003" name="aac003" :require="false" readonly="true"/>
                </el-col>
                <!--                第一行第二列-->
                <el-col :span="8">
                  <ef-text label="性别" name="aac004" v-model="form.aac004" :require="false" readonly="true"/>
                </el-col>
              </el-row>

              <!--              第二行-->
              <el-row :gutter="24">
                <!--                第二行第一列-->
                <el-col :span="8">
                  <ef-text label="证件号码" name="aac147" v-model="form.aac147" :require="false" readonly="true"/>
                </el-col>
                <!--                第二行第二列-->
                <el-col :span="8">
                  <ef-text label="人员性质" name="aac039" v-model="form.aac039" :require="false" readonly="true"/>
                </el-col>
              </el-row>

              <!--              第三行-->
              <el-row :gutter="24">
                <!--                第三行第一列-->
                <el-col :span="8">
                  <ef-text label="出生日期" name="aac006" v-model="form.aac006" format="yyyyMMdd" :require="false"
                           readonly="true"/>
                </el-col>
                <!--                第三行第二列-->
                <el-col :span="8">
                  <ef-text label="政治面貌" name="aac029" v-model="form.aac029" :require="false" readonly="true"/>
                </el-col>
              </el-row>
            </div><!--基础信息结束-->
          </el-form>
        </base-block><!--人员基础信息块结束-->


        <!--        人员职务信息块-->
        <base-block class="out-block" style="margin-top: 15px" rounded ref="exampleBlock2">
          <!--          职务信息-->
          <div class="header-title" slot="title">人员职务信息</div>
          <el-form ref="form" :model="form" label-position="left" label-width="auto">

            <!--            第一行-->
            <el-row :gutter="24">
              <!--              第一行第一列-->
              <el-col :span="8">
                <ef-text label="职务" name="aac049" v-model="form.aac049" :require="false" readonly="true"/>
              </el-col>
              <!--              第一行第二列-->
              <el-col :span="8">
                <ef-text label="任该职务时间" name="aac051" v-model="form.aac051" format="yyyyMMdd" :require="false"
                         readonly="true"/>
              </el-col>
              <!--              第一行第三列-->
              <el-col :span="8">
                <ef-text label="职级" name="aac059" v-model="form.aac059" :require="false" readonly="true"/>
              </el-col>
            </el-row>

            <!--            第二行-->
            <el-row :gutter="24">
              <!--              第二行第一列-->
              <el-col :span="8">
                <ef-text label="任该职级时间" name="aac061" v-model="form.aac061" format="yyyyMMdd" :require="false"
                         readonly="true"/>
              </el-col>
              <!--              第二行第二列-->
              <el-col :span="8">
                <ef-text label="所属科室" name="ks001" v-model="form.ks001" :require="false" readonly="true"/>
              </el-col>
              <!--              第二行第三列-->
              <el-col :span="8">
                <ef-text label="任岗时间" name="gwdate" v-model="form.gwdate" format="yyyyMMdd" :require="false"
                         readonly="true"/>
              </el-col>
            </el-row>

            <!--            第三行岗位表格-->
            <el-row :gutter="24">
              <el-table></el-table>
            </el-row>

          </el-form>
        </base-block><!--人员职务信息块结束-->
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe';
import {cardid} from "/src/util/validate"; //身份证校验组件
import infoDic from "./infoDic"
import {
  mapGetters
} from "vuex";
import constData from "../../../../const";

export default {
  name: "persadd",
  mixins: [BaseCtrl],
  computed: {
    ...mapGetters(["userInfo",]),
  },
  data() {
    const validateID = (rule, value, callback) => {
      if (cardid(value)[0]) {
        callback(new Error(cardid(value)[1]));
      } else {
        this.form.aac006 = value.substr(6, 8);
        callback();
      }
      //  callback(new Error("请输入4位数的验证码"));
      //alert(1);
    };
    return {
      compAccept: 'jpg,png',
      compValueList: [],
      title1: '人员综合信息详情',    //弹窗标题
      onrequire: false,

      form: {
        ac01id: '',   // 用户人员id
        base64: '',   // 上传文件字符串
        aac003: '',   // 人员姓名
        aac004: '',   // 性别
        aac147: '',   // 证件号码
        aac039: '',   // 人员性质
        aac006: '',   // 出生日期
        aac029: '',   // 政治面貌
        aac049: '',   // 职务
        aac051: '',   // 任该职务时间
        aac059: '',   // 职级
        aac061: '',   // 任该职级时间
        ks001: '',    // 所属科室
        gwdate: '',   // 任岗时间
      }
    };

  },
  created() {
    this.loadData()
    this.loadPosts()
    this.loadDocument()
  },
  mounted() {
  },
  methods: {

    loadData() {
      //获取主页面参数
      let params = this.$attrs.params.dataItem;
      if (params) {
        if (params.get('AC01ID')) {
          this.form.ac01id = params.get('AC01ID');
        }
        if (params.get('姓名')) {
          this.form.aac003 = params.get('姓名');
        }
        if (params.get('性别')) {
          this.form.aac004 = infoDic.dic_AAC004[params.get('性别')];
        }
        if (params.get('证件号码')) {
          this.form.aac147 = params.get('证件号码');
        }
        if (params.get('人员性质')) {
          this.form.aac039 = infoDic.dic_AAC039[params.get('人员性质')];
        }
        if (params.get('出生日期')) {
          this.form.aac006 = params.get('出生日期').split(' ')[0];
        }
        if (params.get('政治面貌')) {
          this.form.aac029 = infoDic.dic_AAC029[params.get('政治面貌')];
        }
        if (params.get('职务')) {
          this.form.aac049 = infoDic.dic_AAC049[params.get('职务')];
        }
        if (params.get('任职务时间')) {
          this.form.aac051 = params.get('任职务时间').split(' ')[0];
        }
        if (params.get('职级')) {
          this.form.aac059 = infoDic.dic_AAC059[params.get('职级')];
        }
        if (params.get('任职级时间')) {
          this.form.aac061 = params.get('任职级时间').split(' ')[0];
        }
        if (params.get('所属科室')) {
          this.form.ks001 = params.get('所属科室');
        }
        if (params.get('任岗时间')) {
          this.form.gwdate = params.get('任岗时间').split(' ')[0];
        }

      }
    },

    loadPosts() {
      let form2 = {
        ac01id: this.form.ac01id
      }
      console.log('start request')
      let callbackFun = function(date) {
        console.log('success')
      }
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/staffinfo/staffindex.action',
        form: form2,
        actionFunName: "getPosts",
        succFun: callbackFun,
        failFun: null
      });
    },

    //加载照片
    loadDocument() {
      let that = this;
      let form2 = {
        ac01id: this.form.ac01id
      }
      let callbackFun = function (data) {
        //that.form = data
        /*if(isNotNull(data.registertime)){
          that.form.registertime = data.registertime.substring(0,10)
        }*/
        // that.form.fixmedins_name = "苏州市阳澄湖生态休闲旅游度假区阳澄湖畔花苑社区卫生服务站"
        if (isNotNull(data.base64)) {
          that.form.base64 = data.base64;
          $("#docid").attr("src", "data:image/jpeg;base64," + data.base64);
        }
      }
      this.$eframe.submitTargetForm({
        url: '/szybgzdtgl/baseinfo/pers/persinfomgt.action',
        form: form2,
        actionFunName: "loadDocument",
        succFun: callbackFun,
        failFun: null
      });
    },
  }
}
</script>

<style lang="scss" scoped>


</style>