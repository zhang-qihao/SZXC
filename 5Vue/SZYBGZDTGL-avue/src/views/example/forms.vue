<template>
  <div>
    <basic-container>
      <div class="title">表单</div>

      <el-form ref="form" :model="form" label-position="top">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="活动名称"  >
              <el-input v-model="form.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <!--            <el-form-item label="活动名称">-->
            <!--              <el-input v-model="form.name"></el-input>-->
            <!--            </el-form-item>-->
            <!--            <EFText lable="test1" name="test" init-value="ttt" errorMessage="不可为空！"  require=true ></EFText>-->
            {{form}}
            <ef-select
                label="测试标题11111"
                name="test"
                v-model="form.test"
                :require="true"
                :emptyOption="flag1"
                dictionaryNo="D_ACC001"
                :list="[{code:'01',name:'测试01'},{code:'02',name:'测试02'},{code:'03',name:'测试03'}]"

                @change="selectChange">
            </ef-select>

          </el-col>
          <el-col :span="6">
            <!--            <el-form-item label="活动名称">-->
            <!--              <el-input v-model="form.name"></el-input>-->
            <!--            </el-form-item>-->
            <!--            <EFText lable="test2" name="test2" init-value="ttt"  :validateTT="option" ></EFText>-->
          </el-col>
          <el-col :span="6">
            <el-form-item label="活动区域">
              <el-select v-model="form.region" placeholder="请选择活动区域">
                <el-option
                    v-for="item in regionList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="时间">
              <el-date-picker
                  v-model="form.date2"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="特殊资源">
              <el-radio-group v-model="form.resource">
                <el-radio label="线上品牌商赞助"></el-radio>
                <el-radio label="线下场地免费"></el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="活动性质">
              <el-checkbox-group v-model="form.type">
                <el-checkbox
                    label="美食/餐厅线上活动"
                    name="type"
                ></el-checkbox>
                <el-checkbox label="地推活动" name="type"></el-checkbox>
                <el-checkbox label="线下主题活动" name="type"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="即时配送">
              <el-switch v-model="form.delivery"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="时间">
              <el-time-select v-model="form.time" placeholder="选择时间">
              </el-time-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="日期">
              <el-date-picker
                  v-model="form.date2"
                  align="right"
                  type="date"
                  placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="日期">
              <el-time-picker
                  v-model="form.time2"
                  :picker-options="{
                  selectableRange: '18:30:00 - 20:30:00',
                }"
                  placeholder="任意时间点"
              >
              </el-time-picker>
            </el-form-item>
          </el-col>
          <el-col :span="18">
            <el-form-item label="活动形式">
              <el-input type="textarea" v-model="form.desc"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-button size="mini" @click="submitForm">表单校验</el-button>
          </el-col>
        </el-row>

      </el-form>
    </basic-container>

  </div>
</template>

<script type="text/javascript">
    import {BaseCtrl} from '/src/util/eframe';
    export default {
        mixins: [BaseCtrl],         //继承基础BaseCtrl
        name: "forms",
        data() {
            return {
                flag1:false,
                model: '',
                options: JSON.stringify([
                    {
                        valueKey: '01',
                        labelKey: '选项1111',
                        dis: '说明1',
                        disA: '说明2',
                    },
                    {
                        valueKey: '02',
                        labelKey: '选项2222',
                        dis: '说明3',
                        disA: '说明4',
                    }
                ]),
                option: {validate: [{min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}]},
                regionList:[],
                form: {
                    test:"2",
                    name: "",
                    region: "",
                    date1: "",
                    date2: "",
                    time: "",
                    time2: "",
                    delivery: false,
                    type: [],
                    resource: "",
                    desc: "",
                },
            };
        },
        // components: {EFText},
        created() {
            this.loadDicItem({dicId:"D_ACC001"},(data)=>{ this.regionList = data;  })
        },
        methods: {
            /**
             * 选择框选择值更改触发
             * @val {String} 当前选中值
             * @valObj {Object} 当前选中值所在的对象
             **/
            selectChange(val, valObj) {
                 alert(val);
             //   this.form.test = val;
             //   console.log("valObj=>", valObj)
            },

            submitForm(){
                this.flag1=true;
             //   this.form.test= ["01","02"];
            }

        },
    };
</script>

<style lang="scss" scoped>

</style>
