
import request from '@/router/axios';
import { baseUrl } from '@/config/env';
import constData from "../../const";

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

export const list = (data) => {
  return request({
    url: formatURL( '/crud/list'),
    method: 'get',
    meta: {
      isSerialize: true
    },
    params: data
  })
}
export const del = (id) => request.delete(formatURL( '/crud/delete'), {
  params: {
    id
  }
})
export const add = (data) => request({
  url: formatURL( '/crud/add'),
  method: 'post',
  meta: {
    isSerialize: true
  },
  data: data
})
export const update = (id, data) => request({
  url: formatURL( '/crud/update'),
  method: 'put',
  meta: {
    isSerialize: true
  },
  data: data
})



