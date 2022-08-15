<template>
  <div>
    <basic-container>
      <div class="title">表单校验</div>
      <el-form ref="form" :model="form" label-position="top" label-width="auto">
        <el-row>
          <el-col>
            <el-button-group>
              <el-button type="primary" size="mini" @click="openSlidebar">弹出侧边</el-button>
            </el-button-group>
          </el-col>
        </el-row>
<!--        测试-->
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-text :require="type" label="用户1" name="user2" v-model="form.user2" :button-flag="true">
              <el-button @click="buttonTip" size="mini" icon="el-icon-search"></el-button>
            </ef-text>
          </el-col>
          <el-col :span="6">
            <ef-text label="用户1" type="text" v-model="form.user1" :button-flag="true">
              <el-button @click="buttonTip" size="mini" icon="el-icon-search"></el-button>
            </ef-text>
          </el-col>
          <el-col :span="6">
            <ef-text label="用户1" type="text" v-model="form.user1" :button-flag="true">
              <el-button @click="buttonTip" size="mini" icon="el-icon-search"></el-button>
            </ef-text>
          </el-col>
          <el-col :span="6">
            <ef-text label="用户1" type="text" v-model="form.user1" :button-flag="true">
              <el-button @click="buttonTip" size="mini" icon="el-icon-search"></el-button>
            </ef-text>
          </el-col>
        </el-row>
        <!--        测试结束-->

        <el-row :gutter="20">
          <el-col :span="6">

            <ef-text label="用户1" v-model="form.user1" :maxlength=20 :minlength=5 name="user1"
                     validationMessage="请输入用户1.validationMessage" require="true" @onChange="changeUser1"
                     placeholder="user1"/>
          </el-col>
          <el-col :span="6">
            <ef-text label="用户2222222" v-model="form.user2" maxlength="5" name="user2" require="true"/>
          </el-col>
          <el-col :span="6">
            <EfText label="用户13" v-model="form.user3" name="user3" placeholder="user3" :readonly="true"/>
          </el-col>
          <el-col :span="6">
            <EfText label="用户14" v-model="form.user4" name="user4" placeholder="user4"/>
          </el-col>
        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            user1: {{ form.user1 }}
          </el-col>
          <el-col :span="6">
            user2: {{ form.user2 }}
          </el-col>
          <el-col :span="6">
            user3: {{ form.user3 }}
          </el-col>
          <el-col :span="6">
            user4: {{ form.user4 }}
          </el-col>
        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-tel label="移动电话1111：" v-model="form.telephone1" name="telephone1" :require="true"/>
          </el-col>
          <el-col :span="6">
            <ef-tel label="移动电话2：" v-model="form.telephone2" name="telephone2" placeholder="请输入移动电话2"/>
          </el-col>
          <el-col :span="6">
            <ef-tel label="移动电话3：" v-model="form.telephone3" name="telephone3" :readonly="true"/>
          </el-col>
        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            telephone1: {{ form.telephone1 }}
          </el-col>
          <el-col :span="6">
            telephone2: {{ form.telephone2 }}
          </el-col>
          <el-col :span="6">
            telephone3: {{ form.telephone3 }}
          </el-col>
        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px">-->
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-mail label="邮箱1：" v-model="form.mail1" name="mail1" placeholder="请输入邮箱1" require="true"
                     :pattern="/^(|[-]?(0|[1-9]\d*|(0|([1-9]\d*))){1,1})$/"
                     @onChange="testOnChange" @onInput="testOnInput"/>
          </el-col>
          <el-col :span="6">
            <ef-mail label="邮箱2：" v-model="form.mail2" name="mail2" require="true"/>
          </el-col>
          <el-col :span="6">
            <ef-mail label="邮箱3：" v-model="form.mail3" name="mail3" placeholder="请输入邮箱3" readonly="true"/>
          </el-col>
        </el-row>

        <!--            <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <!--          </el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            mail1: {{ form.mail1 }}
          </el-col>
          <el-col :span="6">
            mail2: {{ form.mail2 }}
          </el-col>
          <el-col :span="6">
            mail3: {{ form.mail3 }}
          </el-col>
        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-button size="mini" @click="testInput">测试input</el-button>
          </el-col>
          <el-col :span="6">
            <el-button size="mini" @click="testInput2">测试input2</el-button>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <ef-textarea :rows="3" label="测试textarea" v-model="form.textarea" maxlength="50" name="textarea"
                         require="true"/>
          </el-col>
        </el-row>

        <el-row :gutter="20">
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
            选择: {{ form.test }}
          </el-col>
        </el-row>

        <!--          <el-row :gutter="20" style="padding: 10px"></el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            <el-button size="mini" @click="testInput">测试input</el-button>
          </el-col>
          <el-col :span="6">
            <el-button size="mini" @click="testInput2">测试input2</el-button>
          </el-col>
          <el-col :span="12">
            {{ form }}
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="6">
            <ef-tel label="移动电话1111：" v-model="form.telephone1" name="telephone1" :require="true"/>
          </el-col>
          <el-col :span="6">
            <ef-tel label="移动电话2：" v-model="form.telephone2" name="telephone2" placeholder="请输入移动电话2"/>
          </el-col>
          <el-col :span="6">
            <ef-tel label="移动电话3：" v-model="form.telephone3" name="telephone3" :readonly="true"/>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="padding: 10px">
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            telephone1: {{ form.telephone1 }}
          </el-col>
          <el-col :span="6">
            telephone2: {{ form.telephone2 }}
          </el-col>
          <el-col :span="6">
            telephone3: {{ form.telephone3 }}
          </el-col>
        </el-row>
        <!--date-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">密码</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-password label="密码1：" v-model="form.password1" name="password1" :require="true"/>
          </el-col>
          <el-col :span="6">
            <ef-password label="密码2：" v-model="form.password2" name="password2" placeholder="请输入密码"/>
          </el-col>

        </el-row>
        <!--          <el-row :gutter="20" style="padding: 10px">-->
        <!--          </el-row>-->
        <el-row :gutter="20">
          <el-col :span="6">
            password1: {{ form.password1 }}
          </el-col>
          <el-col :span="6">
            password2: {{ form.password2 }}
          </el-col>
        </el-row>
        <!--date-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">日期选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-date label="日期选择1：" v-model="form.date1" name="date1" format="yyyy-MM-dd"/>
          </el-col>
          <el-col :span="6">
            <ef-date label="日期选择2：" v-model="form.date2" name="date2" format="yyyy/MM/dd"/>
          </el-col>
          <el-col :span="6">
            <ef-date label="日期选择3：" v-model="form.date3" name="date3" format="yyyy/MM/dd" readonly="true"/>
          </el-col>
          <el-col :span="6">
            <ef-date label="日期选择3(禁用当天之前的日期)：" label-width="200px" v-model="form.date4" name="date4"
                     format="YYYY/MM/DD" :options="disableOptions"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            date1: {{ form.date1 }}
          </el-col>
          <el-col :span="6">
            date2: {{ form.date2 }}
          </el-col>
          <el-col :span="6">
            date3(只读): {{ form.date3 }}
          </el-col>
          <el-col :span="6">
            date4: {{ form.date4 }}
          </el-col>
        </el-row>
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">月份选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-date label="月份选择1：" v-model="form.month1" name="month1" format="yyyy-MM"/>
          </el-col>
          <el-col :span="6">
            <ef-date label="月份选择2：" v-model="form.month2" name="month2" format="yyyy/MM"/>
          </el-col>

          <el-col :span="6">
            <ef-date label="年份选择1：" v-model="form.year1" name="year1" format="yyyy"/>
          </el-col>
          <el-col :span="6">
            <ef-date label="年份选择2：" v-model="form.year2" name="year2" format="yyyy"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            month1: {{ form.month1 }}
          </el-col>
          <el-col :span="6">
            month2: {{ form.month2 }}
          </el-col>
          <el-col :span="6">
            year1: {{ form.year1 }}
          </el-col>
          <el-col :span="6">
            year2: {{ form.year2 }}
          </el-col>
        </el-row>
        <!--time-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">时间选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-time label="时间选择1：" v-model="form.time1" name="time1" format="HH时mm分ss秒"/>
          </el-col>
          <el-col :span="6">
            <ef-time label="时间选择2：" v-model="form.time2" name="time2"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            time1: {{ form.time1 }}
          </el-col>
          <el-col :span="6">
            time2: {{ form.time2 }}
          </el-col>
        </el-row>
        <!--datetime-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">日期时间选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-datetime label="日期时间选择1：" v-model="form.datetime1" name="datetime1" require="true"
                         format="yyyyMMdd HH:mm:ss"/>
          </el-col>
          <el-col :span="6">
            <ef-datetime label="日期时间选择2：" v-model="form.datetime2" name="datetime2"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            datetime1: {{ form.datetime1 }}
          </el-col>
          <el-col :span="6">
            datetime2: {{ form.datetime2 }}
          </el-col>
        </el-row>
        <!--date range-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">日期范围选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-date-range label="日期范围选择1：" v-model="form.daterange1" name="daterange1" :options="pickerOptions"/>
          </el-col>
          <el-col :span="6">
            <ef-date-range label="日期范围选择2：" v-model="form.daterange2" name="daterange2" format="yyyy/MM/dd HH:mm:ss"/>
          </el-col>
          <el-col :span="6">
            <ef-date-range label="月份范围选择1：" v-model="form.monthrange1" name="monthrange1" format="yyyy/MM"/>
          </el-col>
          <el-col :span="6">
            <ef-date-range label="月份范围选择2：" v-model="form.monthrange2" name="monthrange2" format="yyyy-MM"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            日期范围选择1(返回为Object): {{ form.daterange1 }}
          </el-col>
          <el-col :span="6">
            日期范围选择2(返回为Object): {{ form.daterange2 }}
          </el-col>
          <el-col :span="6">
            月份范围选择1(返回为Object): {{ form.monthrange1 }}
          </el-col>
          <el-col :span="6">
            月份范围选择2(返回为Object): {{ form.monthrange2 }}
          </el-col>
        </el-row>
        <!--date time range-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">日期时间范围选择</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-datetime-range label="时间范围选择1：" v-model="form.datetiemrange1" name="datetiemrange1"/>
          </el-col>
          <el-col :span="6">
            <ef-datetime-range require="true" label="时间范围选择2：" v-model="form.datetiemrange2" name="datetiemrange2"
                               format="yyyy/MM/dd HH:mm"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            时间范围选择1(返回为Object): {{ form.datetiemrange1 }}
          </el-col>
          <el-col :span="6">
            时间范围选择2(返回为Object): {{ form.datetiemrange2 }}
          </el-col>
        </el-row>
        <!--date time range-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">数字</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-number label="数字1：" v-model="form.number1" name="number1"/>
          </el-col>
          <el-col :span="6">
            <ef-number require="true" label="数字2：" max="5" v-model="form.number2" name="number2"/>
          </el-col>
          <el-col :span="6">
            <ef-number require="true" label="数字3：" max="15" v-model="form.number3" decimals="4" name="number3"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            数字1: {{ form.number1 }}
          </el-col>
          <el-col :span="6">
            数字2: {{ form.number2 }}
          </el-col>
          <el-col :span="6">
            数字3: {{ form.number3 }}
          </el-col>
        </el-row>
        <!--date time range-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">金额</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-money label="金额1：" v-model="form.money1" name="money1"/>
          </el-col>
          <el-col :span="6">
            <ef-money require="true" label="金额2：" max="5" v-model="form.money2" name="money2"/>
          </el-col>
          <el-col :span="6">
            <ef-money require="true" label="金额3：" max="15" v-model="form.money3" label-width="100px" decimals="4"
                      name="money3"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            金额1: {{ form.money1 }}
          </el-col>
          <el-col :span="6">
            金额2: {{ form.money2 }}
          </el-col>
          <el-col :span="6">
            金额3: {{ form.money3 }}
          </el-col>
        </el-row>
        <!--switch-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">开关</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-switch label="开关1：" v-model="form.switch1" name="switch1"/>
          </el-col>
          <el-col :span="6">
            <ef-switch label="开关2：" v-model="form.switch2" name="switch2" rightLabel="开启"/>
          </el-col>
          <el-col :span="6">
            <ef-switch v-model="form.switch3" name="switch3" field-value="1" left-label="未开启"/>
          </el-col>
          <el-col :span="6">
            <ef-switch label="开关4：" v-model="form.switch4" name="switch4" field-value="y" rightLabel="开启"
                       leftLabel="未开启"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            开关1: {{ form.switch1 }}
          </el-col>
          <el-col :span="6">
            开关2: {{ form.switch2 }}
          </el-col>
          <el-col :span="6">
            开关3: {{ form.switch3 }}
          </el-col>
          <el-col :span="6">
            开关4: {{ form.switch4 }}
          </el-col>
        </el-row>
        <!--radio-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">单选</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-radio label="单选1：" v-model="form.radio1" name="radio1" :list="radioList"/>
          </el-col>

        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            单选1: {{ form.radio1 }}
          </el-col>
        </el-row>
        <!--check box-->
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">多选选</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <ef-checkbox label="多选1：" v-model="form.checkbox1" name="checkbox1" :list="checkboxList"/>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            多选1: {{ form.checkbox1 }}
          </el-col>
        </el-row>
        <div style="font-size: 16px;font-weight: bold;padding: 15px 0;color: #398fd7">block</div>
        <el-row :gutter="20">
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle">
              <p>
                Simple block..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" header-bg>
              <p>
                With header background..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" bordered>
              <p>
                Bordered block..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" bordered header-bg>
              <p>
                Bordered block with header background..
              </p>
            </base-block>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" rounded>
              <p>
                Simple block..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" rounded header-bg>
              <p>
                With header background..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" rounded bordered>
              <p>
                Bordered block..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" rounded bordered header-bg>
              <p>
                Bordered block with header background..
              </p>
            </base-block>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" tag="a" href="javascript:void(0)">
              <p>
                Default opacity hover effect..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" tag="a" href="javascript:void(0)" link-rotate>
              <p>
                Rotate hover effect..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" tag="a" href="javascript:void(0)" link-pop>
              <p>
                Pop hover effect..
              </p>
            </base-block>
          </el-col>
          <el-col :span="6">
            <base-block title="Title" subtitle="Subtitle" tag="a" href="javascript:void(0)" link-shadow>
              <p>
                Shadow hover effect..
              </p>
            </base-block>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <base-block rounded title="Title" subtitle="Subtitle" ref="exampleBlock1" btn-option-fullscreen
                        btn-option-pinned btn-option-content btn-option-close>
              <template #options>
                <button type="button" class="btn-block-option" @click="loadData('exampleBlock1')">
                  <i class="si si-refresh"></i>
                </button>
              </template>
              <p>
                Dolor posuere proin blandit accumsan senectus netus nullam curae, ornare laoreet adipiscing luctus mauris
                adipiscing pretium eget fermentum, tristique lobortis est ut metus lobortis tortor tincidunt himenaeos
                habitant quis dictumst proin odio sagittis purus mi, nec taciti vestibulum quis in sit varius lorem sit
                metus mi.
              </p>
            </base-block>
          </el-col>
          <el-col :span="12">
            <base-block rounded title="Title" subtitle="Subtitle" ref="exampleBlock2" header-rtl btn-option-fullscreen
                        btn-option-pinned btn-option-content btn-option-close>
              <template #options>
                <button type="button" class="btn-block-option" @click="loadData('exampleBlock2')">
                  <i class="si si-refresh"></i>
                </button>
              </template>
              <p>
                Dolor posuere proin blandit accumsan senectus netus nullam curae, ornare laoreet adipiscing luctus mauris
                adipiscing pretium eget fermentum, tristique lobortis est ut metus lobortis tortor tincidunt himenaeos
                habitant quis dictumst proin odio sagittis purus mi, nec taciti vestibulum quis in sit varius lorem sit
                metus mi.
              </p>
            </base-block>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <base-block rounded title="Title" subtitle="Subtitle">
              <template slot="options">
                <div style="display: flex;align-items: center;">
                  <el-input placeholder="请输入..."></el-input>
                  <el-button size="mini" type="primary">Edit</el-button>
                </div>
              </template>
              <p>
                Dolor posuere proin blandit accumsan senectus netus nullam curae, ornare laoreet adipiscing luctus mauris
                adipiscing pretium eget fermentum, tristique lobortis est ut metus lobortis tortor tincidunt himenaeos
                habitant quis dictumst proin odio sagittis purus mi, nec taciti vestibulum quis in sit varius lorem sit
                metus mi.
              </p>
            </base-block>
          </el-col>
          <el-col :span="12">
            <base-block rounded title="Title" subtitle="Subtitle" header-rtl>
              <template slot="options">
                <div style="display: flex;align-items: center;">
                  <el-input placeholder="请输入..."></el-input>
                  <el-button size="mini" type="primary">Edit</el-button>
                </div>
              </template>
              <p>
                Dolor posuere proin blandit accumsan senectus netus nullam curae, ornare laoreet adipiscing luctus mauris
                adipiscing pretium eget fermentum, tristique lobortis est ut metus lobortis tortor tincidunt himenaeos
                habitant quis dictumst proin odio sagittis purus mi, nec taciti vestibulum quis in sit varius lorem sit
                metus mi.
              </p>
            </base-block>
          </el-col>
        </el-row>
      </el-form>
    </basic-container>
  </div>
</template>

<script>
import {BaseCtrl} from '/src/util/eframe'

export default {
  name: "forms3",
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  data() {
    return {
      type:false,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() + 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() + 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() + 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      disableOptions: {
        disabledDate(date) {
          let zero = new Date().setHours(0, 0, 0, 0);
          if (date.getTime() < zero) {
            return true;
          }
          return false;
        }
      },
      radioList: [
        {code: "3", name: "选项1"},
        {code: "5", name: "选项2"},
        {code: "7", name: "选项3"},
      ],
      checkboxList: [
        {code: "A", name: "选项A"},
        {code: "B", name: "选项B"},
        {code: "C", name: "选项C"},
      ],
      form: {
        user1: "11",
        user2: "",
        user3: "U002",
        user4: "",
        telephone1: "",
        telephone2: "",
        telephone3: "",
        mail1: "",
        mail2: "",
        mail3: "yrdy@qq.com",
        multiSelect1: ['上海'],
        date1: "",
        date2: "2021/04/01",
        date3: "2021/03/01",
        time2: "",
        number1: "11.336",
        switch3: "1",
      },
    }
  },
  methods: {
    buttonTip(){
      console.log('111')
      this.type = true
      console.log(this.type)
    },
    testInput() {
      let callback = function (boolean, object) {
        console.log(boolean);
        console.log(object);
      };
      this.$refs.form.validate(callback);
    },
    testInput2() {
      this.form.mail2 = "test@qq.com";
    },
    changeUser1(data) {
      this.$eframe.alertMessage("user1 changed:" + data);
    },


    testOnChange(data) {
      console.log("testOnChange::::" + data);
    },

    testOnInput(data) {
      console.log("testOnInput::" + data);
    },
    changeFormStyle(num) {
      // window.localStorage.setItem('flag',num)
      this.$store.commit('SET_FORMSTYLE', num)
    },
    openSlidebar() {
      var that = this;
      var backFun = function (back) {

      };
      this.slideReveal({
        title: "我的标题111",
        view: "/views/example/test-form",
        width: 800,
        params: {text: "这是参数111"},
        callBackFun: backFun
      });
    },
    loadData(ref) {
      // Set the block to loading state
      this.$refs[ref].stateLoading()

      // .. here you could load your data

      // Set a timeout for demo purposes
      setTimeout(() => {
        // Set the block back to normal state
        this.$refs[ref].stateNormal()
      }, 2000)
    }
  },
}
</script>

<style lang="scss">

</style>