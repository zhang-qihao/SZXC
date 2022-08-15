import Layout from '@/page/index/'


export default [{
    path: '/wel',
    component: 'page/index/index',
    redirect: '/wel/index',
    children: [
        {
            path: '/wel/index',
            name: '首页',
            meta: {
                i18n: 'dashboard'
            },
            component: 'page/frame/wel/index',
            children: []
        },
        // -----test 路由-------
        {
            path: '/wel/stock-in',
            name: '库销存',
            meta: {
                i18n: 'stock-in-out',
                isTab: true
            },
            component: 'views/ehis/stock/stockin/list',
            children: []
        },
        // ------ test 路由
        {
            path: '/wel/stock-out',
            name: '入库管理报表',
            meta: {
                i18n: 'stockInReport',
                isTab: true
            },
            component: 'views/ehis/stock/stockout/list',
            children: []
        },
        {
            path: '/wel/stock-out-report',
            name: '出库单报表',
            meta: {
                i18n: 'stockOutReport',
                isTab: true
            },
            component: 'views/ehis/stock/report/stock-out',
            children: []
        },
        {
            path: '/wel/stock-sales',
            name: '每日销售查询',
            meta: {
                i18n: 'stockSales',
                isTab: true
            },
            component: 'views/ehis/stock/report/sales',
            children: []
        },
        {
            path: '/wel/stock-medicine',
            name: '药品流水',
            meta: {
                i18n: 'stockMedicine',
                isTab: true
            },
            component: 'views/ehis/stock/report/medicine-serial',
            children: []
        },
        //
        // -------end-------
        // {
        //     path: 'dashboard',
        //     name: '控制台',
        //     meta: {
        //         i18n: 'dashboard',
        //         menu: false,
        //     },
        //     component: 'page/frame/wel/dashboard',
        //     children: []
        // },
        // {
        //     path: 'system',
        //     name: '系统管理',
        //     meta: {
        //         i18n: 'systemindex'
        //     },
        //     component: 'page/frame/wel/systemindex',
        //     children: []
        // }, {
        //     path: 'example',
        //     name: '开发用例',
        //     meta: {
        //         i18n: 'exampleindex'
        //     },
        //     component: 'page/frame/wel/exampleindex',
        //     children: []
        // },
    ]
}, {
    path: '/form-detail',
    component: 'page/index/index',
    children: [{
        path: '/form-detail/index',
        name: '详情页',
        meta: {
            i18n: 'detail'
        },
        component: 'page/frame/util/form-detail',
        children: []
    }]
},
    {
        path: '/info',
        component: 'page/index/index',
        redirect: '/info/index',
        children: [{
            path: '/info/index',
            name: '个人信息',
            meta: {
                i18n: 'info'
            },
            component: 'page/frame/user/info',
            children: []
        }, {
            path: '/info/setting',
            name: '个人设置',
            meta: {
                i18n: 'setting'
            },
            component: 'page/frame/user/setting',
            children: []
        }, {
            path: '/info/test',
            name: '个人信息22',
            meta: {
                i18n: ''
            },
            component: 'page/frame/systemmanagement/usermanagement/user/usermanagement',
            children: []
        },
        ]
    }, {
        path: '/query',
        name: '参数',
        component: 'page/index/index',
        meta: {
            i18n: 'params'
        },
        children: [{
            path: '/query:params',
            component: 'page/frame/util/params',
            children: []
        }]
    },
    {
        path: '/temp',
        name: 'Temp',
        component: 'page/index/index',
        meta: {
            i18n: 'params'
        },
        children: []
    },

]