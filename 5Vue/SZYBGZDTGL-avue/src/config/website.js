/**
 * 全局配置文件
 */
export default {
  title: "岗位职责管理系统",
  // title: "兴财云诊所系统",
  logo: "S",
  key: 'SZYBGZDTGL',//配置主键,目前用于存储,系统号
  indexTitle: '',
  lockPage: '/lock',
  tokenTime: 30000,//token过期时间(秒) 8*60  30000
  refreshTime:3600000,//3600s*1000(毫秒)  每小时刷新一次 3600000
  Authorization: 'Authorization',
  loginMethod: 'LoginByUsername',//LoginByGateway,LoginByUsername
  refreshTokenMethod:'RefeshToken',//RefeshTokenByGateway,RefeshToken
  //http的status默认放行不才用统一处理的,
  statusWhiteList: [400],
  //配置首页不可关闭
  isFirstPage: false,
  //是否走正式医保接口
  isYB: false,
  //是否测试环境
  isTest:true,
  mainPageFrameType:'T1',   //主页面框架类型：T1 简单菜单模式  T2 板块模式（一级菜单为版块）  默认 T1
  styleList:{
    themeName:'default',
    //主题选择theme-beautiful
    //默认主题      default
    //vip尊贵主题   theme-vip
    //iview主题     theme-iview
    //cool主题      theme-cool
    //beautiful主题  theme-beautiful
    showType:[],
    // 默认:['type1','textRight']
    // textJustify:每个字之间有间隔
    //textLeft:靠左
    //textRight:靠右
    //type1:默认,无边框,无背景颜色
    //type2:有边框,有背景颜色
  },
  // 系统配置
  setting: {
    sidebar: 'vertical',//控制左侧菜单栏,不要使用horizontal(水平),影响样式
    tag: true,//控制导航栏按钮
    debug: false,//控制错误日志按钮
    collapse: true,//控制左边菜单栏展开收起按钮
    search: true,//控制搜索框
    lock: true,//控制锁屏按钮
    screenshot: false,//控制截屏按钮
    fullscren: true,//控制全屏按钮
    theme: true,//控制主题按钮
    menu: true,//控制菜单按钮
    // color: true,//控制颜色按钮,已经移到个性化设置页面,已经不用
  },
  fistPage: {
    label: "首页",
    value: "/wel/index",
    params: {},
    query: {},
    meta: {
      i18n: 'dashboard'
    },
    group: [],
    close: false,
    saveFlag:false,
    topId:'',
  },
  //配置菜单的属性
  menu: {
    iconDefault: 'fa fa-list-ul',
    props: {
      label: 'label',
      path: 'path',
      icon: 'icon',
      children: 'children'
    }
  }
}