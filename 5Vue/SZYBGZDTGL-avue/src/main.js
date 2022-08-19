import Vue from 'vue';
import axios from './router/axios';
import VueAxios from 'vue-axios';
import App from './App';
import router from './router/router';

import './permission'; // 权限
import './error'; // 日志
import './cache';//页面缓冲
import store from './store';
import { loadStyle } from './util/util'
import * as urls from '@/config/env';
import Element from 'element-ui';
import {
  iconfontUrl,
  iconfontVersion
} from '@/config/env';
import i18n from './lang' // Internationalization
import './styles/common.scss';
import basicBlock from './components/basic-block/main'
import basicContainer from './components/basic-container/main'
import crudCommon from '@/mixins/crud.js'
import md5 from 'js-md5';
import AvueFormDesign from '@sscfaith/avue-form-design'
import VueCodemirror from "vue-codemirror/src";

import VueQuillEditor from 'vue-quill-editor'
// import 'quill/dist/quill.core.css'
// import 'quill/dist/quill.snow.css'
// import 'quill/dist/quill.bubble.css'
import Print from 'vue-print-nb'

window.$crudCommon = crudCommon;
//代码高亮
// Vue.use(VueCodemirror)
Vue.use(Print)
Vue.use(router);
Vue.use(VueAxios, axios);
Vue.use(Element, {
  i18n: (key, value) => i18n.t(key, value)
});
Vue.use(window.AVUE, {
  i18n: (key, value) => i18n.t(key, value)
});
Vue.use(AvueFormDesign)
//富文本编辑器添加实例
Vue.use(VueQuillEditor, /* { default global options } */)
//载入eframe 开始

//载入eframe 开始
 import $ from 'jquery';
// import '@progress/kendo-ui';
// import '@progress/kendo-theme-default/dist/all.css';
import eframe from '/src/util/eframe';
import EfElementComps from '@/components/ef-element-comps'//框架组件库
import * as echarts from 'echarts';
//载入eframe 结束

Vue.use($);
Vue.use(EfElementComps);
//载入eframe 结束

//注册全局容器
Vue.component('basicContainer', basicContainer);
Vue.component('basicBlock', basicBlock);
// 加载相关url地址
Object.keys(urls).forEach(key => {
  Vue.prototype[key] = urls[key];
});
//md5加密
Vue.prototype.$md5 = md5;


// 动态加载阿里云字体库
// iconfontVersion.forEach(ele => {
//   loadStyle(iconfontUrl.replace('$key', ele));
// });

Vue.config.productionTip = false;

//载入eframe 开始
Vue.prototype.$eframe=eframe;
Vue.prototype.$echart=echarts;
//载入eframe 结束

axios.defaults.withCredentials = true;

// 防止el-button短时间内多次点击
Vue.directive('preventReClick',{
  inserted(el,binding){
    el.addEventListener('click',()=>{
      if(!el.disabled){
        el.disabled = true
        setTimeout(()=>{
          el.disabled = false
        },binding.value || 1500)
      }
    })
  }
})


new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app');

//模板类型传参
const tempType = new Vue();
Vue.prototype.$tempType = tempType;