import request from '@/router/axios';
import { baseUrl } from '@/config/env';
import constData from "../const";


function formatURL(url) {

    let baseUrl = constData.baseUrl;
    if(ELECTRON_FLAG){
        baseUrl = constData.electronBaseUrl;
    }
    alert("baseUrl::::"+baseUrl);

    if (url.indexOf("http:") == 0 || url.indexOf("https:") == 0) {

    } else if (url.indexOf("//") == 0) {
        url = url.substring(1);
    } else {
        if (url.indexOf("/") == 0) {
            url = baseUrl + url;
        } else {
            url = baseUrl + "/" + url;
        }
    }
    return url;
}


export const loginByUsername = (username, password, code, redomStr) => request({
    url: formatURL( '/user/login'),
    method: 'post',
    meta: {
        isToken: false
    },
    data: {
        username,
        password,
        code,
        redomStr
    }
});

export const loginByUsername2 = (username, password, grant_type) => request({
    url: formatURL('/login/token.action?frameControlSubmitFunction=getTokenId'),
    // url: baseUrl + '/user/login',
    method: 'post',
    headers: {
        'Content-Type': "application/json;charset=utf-8"
    },
    meta: {
        isToken: false
    },
    data: {
        username,
        password,
        grant_type,
    }
})

export const getUserInfo = () => request({
    url: formatURL( '/user/getUserInfo'),
    method: 'get'
});

export const getUserInfo2 = () => request({
    url: formatURL( '/frame/systemmanagement/usermanagement/user/usermanagement.action?frameControlSubmitFunction=loadUser'),
    // url: baseUrl + '/user/getUserInfo',
    method: 'post',
    headers: {
        'Content-Type': "application/json;charset=utf-8"
    },
    data:{

    }
});

export const refeshToken = () => request({
    url: formatURL( '/user/refesh'),
    method: 'post'
});

export const refeshToken2 = (refresh,grant_type) => request({
    url: formatURL( '/login/token.action?frameControlSubmitFunction=getTokenId'),
    // url: baseUrl + '/user/login',
    method: 'post',
    headers: {
        'Content-Type': "application/json;charset=utf-8"
    },
    meta: {
        isToken: false
    },
    data: {
        refresh,
        grant_type,
    }
})


export const getMenu = (type = 0) => request({
    url: formatURL( '/user/getMenu'),
    method: 'get',
    data: {
        type
    }
});

export const getTopMenu = () => request({
    url: formatURL( '/user/getTopMenu'),
    method: 'get'
});

export const sendLogs = (list) => request({
    url: formatURL( '/user/logout'),
    method: 'post',
    data: list
});

export const logout = () => request({
    url: formatURL( '/user/logout'),
    meta: {
        isToken: false
    },
    method: 'get'
});