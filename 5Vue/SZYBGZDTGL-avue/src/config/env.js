// 配置编译环境和线上环境之间的切换

// let baseUrl = '';
// let baseUrl = `http://172.16.10.14:8095/web`;
let baseUrl = `http://192.168.1.188:8080/web`;
let iconfontVersion = ['567566_pwc3oottzol'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
let codeUrl = `${baseUrl}/code`;
const env = process.env;
// if (env.NODE_ENV == 'development') {
//   baseUrl = `http://127.0.0.1:18080/web`; // 开发环境地址
// } else if (env.NODE_ENV == 'production') {
//   baseUrl = `http://127.0.0.1:18080/web`; //生产环境地址
// } else if (env.NODE_ENV == 'test') {
//   baseUrl = `http://127.0.0.1:18080/web`; //测试环境地址
// }
export {
  baseUrl,
  iconfontUrl,
  iconfontVersion,
  codeUrl,
  env
}