process.env.VUE_APP_VERSION = require('./package.json').version
// 基础路径 注意发布之前要先修改这里
// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
module.exports = {
  devServer: {
    port: 8084,
    proxy: {
      '/web': {
        // target: 'http://172.16.10.14:8095',
        // target: 'http://192.168.31.79:8802',
        target: 'http://127.0.0.1:8080',
        changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
        ws: true,
        pathRewrite: {
          '^/web': '/web'
        }
      },
    }
  },
  lintOnSave: true,
  productionSourceMap: false,
  // configureWebpack: config => {
  //     if (process.env.NODE_ENV === 'production') {
  //         return {
  //             plugins: [
  //                 new BundleAnalyzerPlugin()
  //             ]
  //         }
  //     }
  // },
  chainWebpack: (config) => {
    //忽略的打包文件
    config.externals({
      'vue': 'Vue',
      'vue-router': 'VueRouter',
      'vuex': 'Vuex',
      'axios': 'axios',
      'element-ui': 'ELEMENT',
    })
    const entry = config.entry('app')
    entry
      .add('babel-polyfill')
      .end()
    entry
      .add('classlist-polyfill')
      .end()
    entry
      .add('@/mock')
      .end()
  },
  css: {
    extract: { ignoreOrder: true }
  }
}