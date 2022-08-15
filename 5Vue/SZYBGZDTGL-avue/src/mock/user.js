//模拟接口测试mockjs，未使用


import Mock from 'mockjs'
export default ({ mock }) => {
    if (!mock) return;
    // 用户登录
    // Mock.mock('/user/login', 'post', {
    //     data: new Date().getTime() + ''
    // });
    Mock.mock('/user/login', 'post', {
        code:200,
        msg:"",
        success:true,
        data: {
            expiresIn: 3600,
            refreshToken: "3db572d66c354e3990486af3cea9240e",
            token: "8280819d79499b9a017949a5174c0009",
            tokenHead: "Bearer ",
        },
    });

    //用户退出
    Mock.mock('/user/logout', 'get', {
        data: true,
    });
    //刷新token
    Mock.mock('/user/refesh', 'post', {
        code:200,
        msg:"",
        success:true,
        data: {
            expiresIn: 3600,
            refreshToken: "be80942eaea245eea69f1795192fcf46",
            token: "8280819d7949e595017949e9947f0001",
            tokenHead: "Bearer ",
        },
    });
    //获取用户信息
    Mock.mock('/user/getUserInfo', 'get', {
        data: {
            userInfo: {
                username: 'admin',
                name: 'avue',
                avatar: 'https://gitee.com/uploads/61/632261_smallweigit.jpg',
            },
            roles: 'admin',
            permission: [
                'sys_crud_btn_add',
                'sys_crud_btn_export',
                'sys_menu_btn_add',
                'sys_menu_btn_edit',
                'sys_menu_btn_del',
                'sys_role_btn1',
                'sys_role_btn2',
                'sys_role_btn3',
                'sys_role_btn4',
                'sys_role_btn5',
                'sys_role_btn6',
            ], //权限级别
        }
    });

    //获取表格数据
    Mock.mock('/user/getTable', 'get', () => {
        let list = []
        for (let i = 0; i < 5; i++) {
            list.push(Mock.mock({
                id: '@increment',
                name: Mock.mock('@cname'),
                username: Mock.mock('@last'),
                type: [0, 2],
                checkbox: [0, 1],
                'number|0-100': 0,
                datetime: 1532932422071,
                'sex|0-1': 0,
                moreselect: [0, 1],
                "grade": 0,
                address: Mock.mock('@cparagraph(1, 3)'),
                check: [1, 3, 4]
            }))
        }
        return {
            data: {
                total: 11,
                pageSize: 10,
                tableData: list
            }
        }
    })
}