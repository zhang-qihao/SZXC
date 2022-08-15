import request from '@/router/axios';
import { baseUrl } from '@/config/env';
import constData from "../const";
import eframe from "@/util/eframe";


function formatURL(url) {

    let baseUrl = constData.baseUrl;
    if(ELECTRON_FLAG){
        baseUrl = constData.electronBaseUrl;
    }
    // alert("baseUrl::::"+baseUrl);

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

function formatData (data) {
  if(data && typeof (data)!='object'){
    data = decryptAES(data);
    data = JSON.parse(data);
  }
  return data
}

// ehis
// stock in
export const stockPutIn = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/putin/stockPutIn.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

export const stockinfoUpload = async (actionFunName, form = {}) => {
  // return new Promise((resolve, reject) => {
  //   let errorFun = function (error) {
  //     reject(error)
  //   };
  //   let backFun = function (data) {
  //     resolve(data)
  //   }
  //   eframe.submitTargetForm({
  //       url: '/ehis/stock/stockinfo.action',
  //       form,
  //       actionFunName,
  //       succFun: backFun,
  //       failFun: errorFun
  //   });
  // })

  let data = await request({
    url: formatURL(`/ehis/stock/stockinfo.action?frameControlSubmitFunction=${actionFunName}`),
    data: form,
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    meta: {
      isSerialize: false
    }
  })
  return formatData(data)
}


// get Stok Put
export const getStockPutIn = async (type, opt) => {
  let data = await request({
    url: formatURL(`/ehis/putin/stockPutIn.action?frameControlSubmitFunction=${type}&stock_put_in_id=${opt.stock_put_in_id}&pageIndex=${opt.pageIndex}&pageSize=${opt.pageSize}`),
    method: 'get'
  })

  return formatData(data)
};

export const getStockPutInExport = async (type, stock_put_in_id) => {
  let data = await request({
    url: formatURL(`/ehis/putin/stockPutIn.action?frameControlSubmitFunction=${type}&stock_put_in_id=${stock_put_in_id}`),
    method: 'get'
  })

  return formatData(data)
};

// get Stock Info
export const getStockinfo = async (type, condition) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockinfo.action?frameControlSubmitFunction=${type}${condition}`),
    method: 'get'
  })

  return formatData(data)
};

// stock out
export const stockPutOut = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/putout/stockPutOut.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

// get Stok Put
export const getStockPutOut = async (type, stockPutOunId) => {
  let data = await request({
    url: formatURL(`/ehis/putout/stockPutOut.action?frameControlSubmitFunction=${type}&stock_put_out_id=${stockPutOunId}`),
    method: 'get'
  })

  return formatData(data)
};

// get Stock Info
export const getStockOutInfo = async (type, stockPutInId) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockinfo.action?frameControlSubmitFunction=${type}&stock_put_in_id=${stockPutInId}`),
    method: 'get'
  })
  return formatData(data)
};

// stock
export const stockStockInfo = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/stock/stockinfo.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

// stock info
export const stockPutInfo = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/stock/stockinfo.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

// stockInventory
export const stockInventory = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/stock/stockInventory.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    })
  })
}

// get stockInventory
export const getStockInventory = async (type, opt) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockInventory.action?frameControlSubmitFunction=${type}&stock_inventory_id=${opt.stock_inventory_id}&pageIndex=${opt.pageIndex}&pageSize=${opt.pageSize}`),
    method: 'get'
  })
  return formatData(data)
}

// 出库确认
export const getStockinfoOut = async (type, condition) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockinfo.action?frameControlSubmitFunction=${type}${condition}`),
    method: 'get'
  })
  return formatData(data)
};

// 盘点确认
export const getStockinfoInv = async (type, condition) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockinfo.action?frameControlSubmitFunction=${type}${condition}`),
    method: 'get'
  })
  return formatData(data)
};

// 导出 blob
export const excelDownload = async (expFileName) => {
  let data = await request({
    url: formatURL(`/ehis/stock/excel.action?frameControlSubmitFunction=download&expFileName=${expFileName}`),
    method: 'get',
    responseType: 'blob'
  })

  return formatData(data)
}

// stock out
export const stockReport = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/stock/report.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

// stock param
export const stockParam = (actionFunName, form = {}) => {
  return new Promise((resolve, reject) => {
    let errorFun = function (error) {
      reject(error)
    };
    let backFun = function (data) {
      resolve(data)
    };
    eframe.submitTargetForm({
        url: '/ehis/stock/stockParam.action',
        form,
        actionFunName,
        succFun: backFun,
        failFun: errorFun
    });
  })
}

// get param
export const getStockParam = async (type, code) => {
  let data = await request({
    url: formatURL(`/ehis/stock/stockParam.action?frameControlSubmitFunction=${type}&param_type=${code}`),
    method: 'get'
  })

  return formatData(data)
}
