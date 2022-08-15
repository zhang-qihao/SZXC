<template>
  <div>
    <basic-container>
      <div id="echarts1" :style="{width: '400px', height: '400px'}"></div>
      <div id="echarts2" :style="{width: '400px', height: '400px'}"></div>
      <div id="echarts3" :style="{width: '400px', height: '400px'}"></div>
    </basic-container>

  </div>
</template>

<script type="text/javascript">

 // import $ from 'jquery';
  import  {BaseCtrl}  from '/src/util/eframe'

export default {
  mixins: [BaseCtrl],         //继承基础BaseCtrl
  name: "components",
  data() {
    return {
      option:{},
      form:{}
    };
  },
  components: {},
  mounted() {
    this.drawEcharts();
  },
  created() {
  },
  methods: {
    aaa() {
      alert("11111");
      alert($("#aaa"));
    },
    drawEcharts(){
      let myChart = this.$echart.init(document.getElementById("echarts1"))
      let myChart2 = this.$echart.init(document.getElementById("echarts2"))
      //option参数详情见官网
      //https://echarts.apache.org/examples/zh/index.html
       this.option  = {
        title: {
          text: '某站点用户访问来源',
          subtext: '纯属虚构',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
        },
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: '50%',
            data: [
              {value: 1048, name: '搜索引擎'},
              {value: 735, name: '直接访问'},
              {value: 580, name: '邮件营销'},
              {value: 484, name: '联盟广告'},
              {value: 300, name: '视频广告'}
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      if(this.option!=null||this.option!=''){
        myChart.setOption(this.option)
        myChart2.setOption(this.option)
      }
    }
  },
};
</script>

<style lang="scss">

</style>
