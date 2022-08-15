// import Cookies from 'js-cookie'
// import website from '@/config/website'
import { getStore } from '@/util/store'

// const Authorization = website.Authorization
// var inFifteenMinutes = new Date(new Date().getTime() + website.tokenTime * 1000);

export function getToken () {
    if(getStore({ name: 'token' })){
        return JSON.stringify(getStore({ name: 'token' })) ;
    }else {
        return null;
    }
}

export function setToken (token) {
  // return Cookies.set(Authorization, token, { expires: inFifteenMinutes })
}

export function removeToken () {
  // return Cookies.remove(Authorization)
}