/**
 * 全站http配置
 *
 * axios参数说明
 * isSerialize是否开启form表单提交
 * isToken是否需要token
 */
import axios from 'axios'
import store from '@/store/';
import router from '@/router/router'
import { serialize } from '@/util/util'
import { getToken } from '@/util/auth'
import { Message } from 'element-ui'
import website from '@/config/website';
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'
import {getStore} from "../util/store";
import eframe from "../util/eframe"; // progress bar style
axios.defaults.timeout = 1000000;
//返回其他状态吗
axios.defaults.validateStatus = function (status) {
  return status >= 200 && status <= 500; // 默认的
};
//跨域请求，允许保存cookie
axios.defaults.withCredentials = true;
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
// axios.defaults.headers.post['Content-Type'] = 'multipart/form-data'
// NProgress Configuration
NProgress.configure({
  showSpinner: false
});

//HTTPrequest拦截
axios.interceptors.request.use(config => {
  if(eframe.isNotNull(config.data) && typeof (config.data)!='object'){
    config.data=encryptAES(config.data);
  }
  NProgress.start() // start progress bar
  const meta = (config.meta || {});
  const isToken = meta.isToken === false;

  const token=getStore({name:'token'});

  // if (getToken() && !isToken) {
  //   config.headers[website.Authorization] = 'Bearer ' + getToken().data.token // 让每个请求携带token--['Authorization']为自定义key 请根据实际情况自行修改
  // }

  if (token && token.token && !isToken) {
    config.headers[website.Authorization] = 'Bearer ' + token.token// 让每个请求携带token--['Authorization']为自定义key 请根据实际情况自行修改
  }

  //headers中配置serialize为true开启序列化
  if (config.method === 'post' && meta.isSerialize === true) {
    config.data = serialize(config.data);
  }

  return config
}, error => {

  return Promise.reject(error)
});
//HTTPresponse拦截
axios.interceptors.response.use(res => {
  console.log("interceptors.response");
  NProgress.done();
  const status = Number(res.status) || 200;
  const statusWhiteList = website.statusWhiteList || [];
  const message = res.data.message || res.data || '未知错误';

  //如果是401则跳转到登录页面
  if (status === 401) {
    console.log("【axios】 1:"+"401则跳转到登录页面");
    store.dispatch('FedLogOut').then(() => router.push({ path: '/login' }));
    return Promise.reject(new Error(message));
  }
  if (status === 500) {
    console.log(message);
  }

  return res.data;
}, error => {
  console.log("error");
  NProgress.done();
  return Promise.reject(new Error(error));
})

export default axios;