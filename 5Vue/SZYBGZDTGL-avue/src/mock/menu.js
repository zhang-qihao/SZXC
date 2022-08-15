import Mock from 'mockjs'
const top = [{
  label: "首页",
  path: "/wel/index",
  icon: 'el-icon-document',
  meta: {
    i18n: 'dashboard',
  },
  parentId: 0
}, {
  label: "官网",
  icon: 'el-icon-document',
  meta: {
    menu: false,
    i18n: 'website',
  },
  path: "https://avuejs.com",
  parentId: 1
},
{
  label: "测试",
  icon: 'el-icon-document',
  path: "/test/index",
  meta: {
    i18n: 'test',
  },
  parentId: 2
},
{
  label: "更多",
  icon: 'el-icon-document',
  path: "/wel/dashboard",
  meta: {
    menu: false,
    i18n: 'more',
  },
  parentId: 3
}];
const first = [{
    label: "官网",
    path: 'https://avuejs.com',
    icon: 'icon-caidan',
    meta: {
      target: '_blank',
      i18n: 'website',
    },
    children: []
  },
  {
    label: "开发用例",
    path: '/example',
    icon: 'icon-caidan',
    children: [{
      label: "通用API",
      path: 'commonapi',
      component: 'views/example/commonapi',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "按钮",
      path: 'button',
      component: 'views/example/button',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "表单",
      path: 'form',
      component: 'views/example/forms',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "表单校验",
      path: 'forms2',
      component: 'views/example/forms2',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "表单3",
      path: 'forms3',
      component: 'views/example/forms3',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "echarts图标",
      path: 'echarts',
      component: 'views/example/echarts',
      icon: 'icon-caidan',
      children: []
    }, {
      label: "Kendo表格",
      path: 'kendogrid',
      component: 'views/example/kendogrid',
      icon: 'icon-caidan',
      children: []
    },
    {
      label: "Kendo表格2",
      path: 'kendogrid2',
      component: 'views/example/kendogrid2',
      icon: 'icon-caidan',
      children: []
    },
    {
      label: "Kendo树",
      path: 'kendotree',
      component: 'views/example/kendotree',
      icon: 'icon-caidan',
      children: []
    },
      {
        label: "Kendo树状表格",
        path: 'kendotreegrid',
        component: 'views/example/kendotreegrid',
        icon: 'icon-caidan',
        children: []
      },
      {
        label: "Kendo可编辑表",
        path: 'kendogrideditor',
        component: 'views/example/kendogrideditor',
        icon: 'icon-caidan',
        children: []
      }



    ]
  },

  {
  label: "缓冲",
  path: '/cache',
  component: 'views/util/cache',
  icon: 'icon-caidan',
  meta: {
    i18n: 'cache',
    keepAlive: true
  },
  children: []
}, {
  label: "参数",
  path: '/params',
  component: 'views/util/params',
  meta: {
    i18n: 'params',
  },
}, {
  label: "标签",
  path: '/tags',
  component: 'views/util/tags',
  icon: 'icon-caidan',
  meta: {
    i18n: 'tags',
  },
  children: []
}, {
  label: "存储",
  path: '/store',
  component: 'views/util/store',
  icon: 'icon-caidan',
  meta: {
    i18n: 'store',
  },
  children: []
}, {
  label: "全局函数",
  path: 'https://avuejs.com/doc/api?test1=1&test2=2',
  icon: 'icon-caidan',
  meta: {
    i18n: 'api',
  }
}, {
  label: "日志监控",
  path: '/logs',
  component: 'views/util/logs',
  icon: 'icon-caidan',
  meta: {
    i18n: 'logs',
  },
  children: []
}, {
  label: "通用模板",
  path: '/crud',
  component: 'views/util/crud',
  icon: 'icon-caidan',
  meta: {
    i18n: 'crud',
  },
  children: []
}, {
  label: "表格",
  path: '/table',
  component: 'views/util/table',
  icon: 'icon-caidan',
  meta: {
    i18n: 'table',
  },
  children: []
}, {
  label: "表单",
  path: '/form',
  component: 'views/util/form',
  icon: 'icon-caidan',
  meta: {
    i18n: 'form'
  },
  children: []
}, {
  label: "权限",
  path: '/permission',
  component: 'views/util/permission',
  icon: 'icon-caidan',
  meta: {
    i18n: 'permission',
  },
  children: []
}, {
  label: "返回顶部",
  path: '/top',
  component: 'views/util/top',
  icon: 'icon-caidan',
  meta: {
    i18n: 'top',
  },
  children: []
}, {
  label: "表格表单",
  path: '/crud-form',
  component: 'views/util/crud-form',
  icon: 'icon-caidan',
  meta: {
    i18n: 'crudForm',
  },
  children: []
}, {
  label: "图钉111",
  path: '/affix',
  component: 'views/util/affix',
  icon: 'icon-caidan',
  // meta: {
  //   i18n: 'affix',
  // },
  children: []
}, {
  label: "异常页",
  path: '/error',
  meta: {
    i18n: 'error',
  },
  icon: 'icon-caidan',
  children: [{
    label: "error403",
    path: 'error',
    component: 'components/error-page/403',
    icon: 'icon-caidan',
    children: []
  }, {
    label: "error404",
    path: '404',
    component: 'components/error-page/404',
    icon: 'icon-caidan',
    children: []
  }, {
    label: "error500",
    path: '500',
    component: 'components/error-page/500',
    icon: 'icon-caidan',
    children: []
  }]
}];
const second = [];
const third = [{
  label: "测试页面",
  path: '/test',
  component: 'views/test',
  icon: 'icon-caidan',
  meta: {
    i18n: 'test',
  },
  children: []
}];
export default ({ mock }) => {
  if (!mock) return;
  let menu = [first, second, third];
  Mock.mock('/user/getMenu', 'get', (res) => {
    let body = JSON.parse(res.body);
    return {
      data: menu[body.type] || []
    };
  });
  Mock.mock('/user/getTopMenu', 'get', () => {
    return {
      data: top
    };
  })

}