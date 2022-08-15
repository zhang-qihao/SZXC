//判断空

function isNull(data){
    return (data === "" || data === undefined || data === null) ? true : false;
}

//判断非空
function isNotNull(data){
    return !isNull(data);
}

//格式化URL
function formatURL(url) {
    if (url.indexOf("http:") == 0 || url.indexOf("https:") == 0) {

    }else if (url.indexOf("//") == 0 ) {
        url=url.substring(1);
    } else {
        if (url.indexOf("/") == 0) {
            url = root + url;
        } else {
            url = root + "/" + url;
        }
    }
    return url;
}


//
function encryptRSA(data){
    if(isNull(data)){
        return "";
    }
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey( pubKey);
    var encrypted = encrypt.encrypt(data);
    return encrypted;
}



function encryptAES(data){
    if(isNull(data)){
        return "";
    }
    return myAesUtil.encrypt(M_SALT, M_IV, M_PASSPHRASE, data);
}

function decryptAES(data){
    if(isNull(data)){
        return "";
    }
    return myAesUtil.decrypt(M_SALT, M_IV, M_PASSPHRASE, data);
}

function submitAjax(params){
    var type = params.type;
    var url = params.url;
    var contentType = params.contentType;
    var headers = params.headers;
    var data = params.data;
    var dataType = params.dataType;
    var success = params.success;
    var error = params.error;
    if(isNull(type)){
        type="POST"
    }

    if(!isNotNull(contentType)){
        contentType="application/json; charset=utf-8"
    }
    if(url == null){
        alert("请求路径不可为空！");
        return;
    }

    if(isNotNull(data)){
        data=encryptAES(data);
    }else {
        data=null;
    }
    if(!isNotNull(headers)){
        headers={"Content-Type":"application/json; charset=utf-8"};
    }

    if(!isNotNull(dataType)){
        dataType="text";
    }

    var suc=function (result,status,headers){
        if(result.indexOf("script") > 0){

        }
        if(typeof (result)==="object" || isNull(result)  ){
            data=result;
        }else {
            data = decryptAES(result);
            data = JSON.parse(data);
        }
        success(data,status,headers);
    }
    var err=function (result){
        console.log(result);
        console.log("error!!!");
    }
    $.ajax({
        type:type,
        url:url,
        headers:headers,
        contentType: contentType,
        data:data,
        dataType:dataType,
        xhrFields: {
            withCredentials: true // 这里设置了withCredentials
        },
        crossDomain: true,
        success:suc,
        error:err,
    })

}

//扩展
function uuid(){
    var len=32;//32长度
    var radix=16;//16进制
    var chars='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid=[],i;
    radix=radix||chars.length;if(len){
        for(i=0;i<len;i++)uuid[i]=chars[0|Math.random()*radix];
    }else{
        var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]='-';
        uuid[14]='4';
        for(i=0;i<36;i++){
            if(!uuid[i]){
                r=0|Math.random()*16;
                uuid[i]=chars[(i==19)?(r&0x3)|0x8:r];
            }
        }
    }
    return uuid.join('');
}






var eRootWindow=null;

function doExternalFun(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10){
    if(eRootWindow!=null&&typeof(eRootWindow)!="undefined"){
        try{
            return   eRootWindow.external.doExternalFun(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10).toString();
        }catch(e){
            alert("函数doExternalFun无法在IE环境下调用。 ");
            return "";
        }
    }
}


//iframe加载页面
function hrefIframe(targetFrame,url){

    if (typeof (targetFrame) == "undefined" || targetFrame==null|| targetFrame =="") {
        return;
    }

    if(url!=null&&url!=""&&typeof(url)!="undefined"){
        url = formatURL(url);
        if(url.indexOf("?")>=0){
            url= url+"&winTemp="+Math.random()*(1000);
        }else{
            url= url+"?winTemp="+Math.random()*(1000);
        }

        if( targetFrame.location!=null && typeof(targetFrame.location)!="undefined"){
            targetFrame.location.href=url;
        }else{
            targetFrame.src=url;
        }

    }else{
        return;
    }
}






/**
 * 自适应Grid高度
 *
 * @param
 * @return
 * @exception
 */
function resizeGrid(gridElement) {
    var  dataArea = gridElement.find(".k-grid-content"),
        gridHeight = gridElement.innerHeight(),
        otherElements = gridElement.children().not(".k-grid-content"),
        otherElementsHeight = 0;
    otherElements.each(function(){
        otherElementsHeight += $(this).outerHeight();
    });
    dataArea.height(gridHeight - otherElementsHeight);
}



////////////////////////1.扩展kendo grid///////////////////////////////////////////////
/**
 * 设置查询参数  queryNo 查询号
 *               queryWindow 窗口号
 *               parameters  参数JSON
 *               whereCondition 查询条件（不包含where字段）
 *               authCondition 权限条件（不包含where字段）
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.setQueryParams = function ( paramsJson ) {
    if(typeof(paramsJson)!="object"||paramsJson==null){
        return;
    }
    //if(typeof(paramsJson.queryNo)!="undefined"){
    //    this.dataSource.transport.options.queryParams.queryNo = paramsJson.queryNo;
    //}
    //if(typeof(paramsJson.queryWindow)!="undefined"){
    //    this.dataSource.transport.options.queryParams.queryWindow = paramsJson.queryWindow;
    //}
    //if(typeof(paramsJson.parameters)=="object"){
    //    this.dataSource.transport.options.queryParams.parameters = paramsJson.parameters;
    //}
    //if(typeof(paramsJson.whereCondition)!="undefined"){
    //    this.dataSource.transport.options.queryParams.whereCondition = paramsJson.whereCondition;
    //}

    if(typeof(paramsJson.queryNo)!="undefined"){
        this.dataSource.transport.queryParams.queryNo = paramsJson.queryNo;
    }
    if(typeof(paramsJson.queryWindow)!="undefined"){
        this.dataSource.transport.queryParams.queryWindow = paramsJson.queryWindow;
    }
    if(typeof(paramsJson.parameters)=="object"){
        this.dataSource.transport.queryParams.parameters = paramsJson.parameters;
    }
    if(typeof(paramsJson.whereParams)=="object"){
        this.dataSource.transport.queryParams.whereParams = paramsJson.whereParams;
    }
    if(typeof(paramsJson.orderParams)=="object"){
        this.dataSource.transport.queryParams.orderParams = paramsJson.orderParams;
    }
    if(typeof(paramsJson.whereCondition)!="undefined"){
        this.dataSource.transport.queryParams.whereCondition = encryptAES(paramsJson.whereCondition);
    }
    if(typeof(paramsJson.authCondition)!="undefined"){
        this.dataSource.transport.queryParams.authCondition = encryptAES(paramsJson.authCondition);
    }
    if(typeof(paramsJson.connectId)!="undefined"){
        this.dataSource.transport.queryParams.connectId = paramsJson.connectId;
    }



};



/**
 * 获取查询号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getQueryNo= function (   ) {
    return  this.dataSource.transport.queryParams.queryNo;
}


/**
 * 获取窗口号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getQueryWindow = function (   ) {
 return  this.dataSource.transport.queryParams.queryWindow;
}


//check结构
//kendo.ui.Grid.prototype.selectedItems  =
//{
//    all:"n",
//    itemsPkg:"|~~|",
//    selectItem:function(key,checked ){
//        var split = "|~~|";
//        var hasFlag = (this.itemsPkg.indexOf(split+key+split)>-1);
//        if(this.all=="y"){
//            if(!checked){
//                if(!hasFlag){
//                    this.itemsPkg=this.itemsPkg+key+split;
//                }
//            }else{
//                this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
//            }
//        }else{
//            if(checked){
//                if(!hasFlag){
//                    this.itemsPkg=this.itemsPkg+key+split;
//                }
//            }else{
//                this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
//            }
//        }
//    },
//    getCheckPackage: function( ) {
//        var split = "|~~|";
//        var checkpackage = this.itemsPkg.split(split);
//        if (checkpackage.length <= 2) {
//            return this.all + split + "0" + split;
//        } else {
//            return this.all + split + (checkpackage.length - 2) + this.itemsPkg;
//        }
//    }
//
//};


kendo.ui.Grid.prototype.initSelectedItems = function ( ) {

    var selectedItems = this.getSelectedItems();
    selectedItems.all="n";
    selectedItems.itemsPkg = "|~~|";
    //清除全选框
    var  $checkboxAll = this.thead.find(".headerCheckboxAll");
    if($checkboxAll.length>0){
        $checkboxAll.removeAttr("checked");
    }
};

kendo.ui.Grid.prototype.getSelectedItems = function ( ) {
    var that=this;
    if(that.selectedItems==null||typeof (that.selectedItems) == "undefined"){
        if(that.selectedItems==null){
            that.selectedItems={
                all:"n",
                itemsPkg:"|~~|",
                selectItem:function(key,checked ){
                    var split = "|~~|";
                    var hasFlag = (this.itemsPkg.indexOf(split+key+split)>-1);
                    if(that.all=="y"){
                        if(!checked){
                            if(!hasFlag){
                                this.itemsPkg=this.itemsPkg+key+split;
                            }
                        }else{
                            this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
                        }
                    }else{
                        if(checked){
                            if(!hasFlag){
                                this.itemsPkg=this.itemsPkg+key+split;
                            }
                        }else{
                            this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
                        }
                    }
                },
                getCheckPackage: function( ) {
                    var split = "|~~|";
                    var checkpackage = that.itemsPkg.split(split);
                    if (checkpackage.length <= 2) {
                        return that.all + split + "0" + split;
                    } else {
                        return that.all + split + (checkpackage.length - 2) + that.itemsPkg;
                    }
                }
            };
        }
    }

    return that.selectedItems;
};



/**
 * 刷新
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.doRefresh = function (paramsJson) {
    this.initSelectedItems();
    if(typeof(paramsJson)=="object"&&paramsJson!=null){
      this.setQueryParams(paramsJson);
        if(typeof(paramsJson.queryNo)!="undefined"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.queryWindow)!="undefined"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.parameters)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.whereParams)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.orderParams)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.whereCondition)!="undefined"){
            this.dataSource.page(1);
        }else{
            this.dataSource.read();
        }
    }else {
        this.dataSource.read();
    }

};

/**
 * 获得当前数据JSON
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.serialize = function ( ) {

};

/**
 * 取查询语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getQuerySQL = function ( ) {
    return this.dataSource.reader.querySQL;
};

/**
 * 取统计语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getStatSQL = function ( ) {
    return this.dataSource.reader.statSQL;
};



/**
 * 取选中记录SQL
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getCheckSQL = function( ) {
    var checkAll = this.hasCheckBox();
    if(!checkAll){
        return this.getQuerySQL();
    }
    var checkSql;
    var split = "|~~|";
    var temp=this.getSelectedItems().itemsPkg.substring(4, this.getSelectedItems().itemsPkg.length)
    var checkpackage=temp.split(split);
    checkpackage.pop(true);
    if(this.getSelectedItems().all=='y'){
        if(checkpackage.length<1){
            checkSql=this.getQuerySQL();
        }else{
            checkSql=this.getQuerySQL()+" WHERE SELECTEDKEY NOT IN ('"+checkpackage.join("','")+"')";
        }
        return checkSql;
    }else{
        if(checkpackage.length<1){
            checkSql=this.getQuerySQL()+" WHERE 1=2";
        }else{
            checkSql=this.getQuerySQL()+" WHERE SELECTEDKEY IN ('"+checkpackage.join("','")+"')";
        }
        return checkSql;
    }
};

/**
 * 取选中记录包
 * 备注:（格式：y/n|~~|包条数|~~|选择主键1|~~|选择主键2|~~|选择主键3|~~|......|~~|选择主键n|~~|)
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.getCheckPackage = function( ) {
    return this.getSelectedItems().getCheckPackage();

};

/**
 * 是否存在hasCheckBox框
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.hasCheckBox = function(){
    var gridOptions = this.getOptions();
    return gridOptions.checkAll;
};

/**
 * 翻页触发事件  false不进行翻页，true进行翻页
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.Grid.prototype.setPageFun = function ({funName}) {
    if(typeof (funName)=="function"){
        var page=function (e){
            var check=funName(e);
            if(check){

            }else {
                e.preventDefault();
            }
        };
        var gridOptions = this.getOptions();
        gridOptions.page=page;
        this.setOptions(gridOptions);
    }else {
        console.log("funName不是方法！");
    }
};



////////////////////////2.扩展kendo grid///////////////////////////////////////////////
/**
 * 设置查询参数  queryNo 查询号
 *               queryWindow 窗口号
 *               parameters  参数JSON
 *               whereCondition 查询条件（不包含where字段）
 *               authCondition 权限条件（不包含where字段）
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.setQueryParams = function ( paramsJson ) {
    if(typeof(paramsJson)!="object"||paramsJson==null){
        return;
    }
    //if(this.options.showType=="1"){//级联打钩
    //    if (typeof(paramsJson.queryNo) != "undefined") {
    //        this.dataSource.options.queryParams.queryNo = paramsJson.queryNo;
    //    }
    //    if (typeof(paramsJson.queryWindow) != "undefined") {
    //        this.dataSource.options.queryParams.queryWindow = paramsJson.queryWindow;
    //    }
    //    if (typeof(paramsJson.parameters) == "object") {
    //        this.dataSource.options.queryParams.parameters = paramsJson.parameters;
    //    }
    //    if (typeof(paramsJson.whereCondition) != "undefined") {
    //        this.dataSource.options.queryParams.whereCondition = paramsJson.whereCondition;
    //    }
    //}else {
    //    if (typeof(paramsJson.queryNo) != "undefined") {
    //        this.dataSource.transport.options.queryParams.queryNo = paramsJson.queryNo;
    //    }
    //    if (typeof(paramsJson.queryWindow) != "undefined") {
    //        this.dataSource.transport.options.queryParams.queryWindow = paramsJson.queryWindow;
    //    }
    //    if (typeof(paramsJson.parameters) == "object") {
    //        this.dataSource.transport.options.queryParams.parameters = paramsJson.parameters;
    //    }
    //    if (typeof(paramsJson.whereCondition) != "undefined") {
    //        this.dataSource.transport.options.queryParams.whereCondition = paramsJson.whereCondition;
    //    }
    //}

    if (typeof(paramsJson.queryNo) != "undefined") {
        this.dataSource.transport.queryParams.queryNo = paramsJson.queryNo;
    }
    if (typeof(paramsJson.queryWindow) != "undefined") {
        this.dataSource.transport.queryParams.queryWindow = paramsJson.queryWindow;
    }
    if (typeof(paramsJson.rootId) != "undefined") {
        this.dataSource.transport.queryParams.rootId = paramsJson.rootId;
    }
    if (typeof(paramsJson.parameters) == "object") {
        this.dataSource.transport.queryParams.parameters = paramsJson.parameters;
    }
    if(typeof(paramsJson.whereParams)=="object"){
        this.dataSource.transport.queryParams.whereParams = paramsJson.whereParams;
    }
    if(typeof(paramsJson.orderParams)=="object"){
        this.dataSource.transport.queryParams.orderParams = paramsJson.orderParams;
    }
    if (typeof(paramsJson.whereCondition) != "undefined") {
        this.dataSource.transport.queryParams.whereCondition =  encryptAES(paramsJson.whereCondition);
    }
    if(typeof(paramsJson.authCondition)!="undefined"){
        this.dataSource.transport.queryParams.authCondition = encryptAES(paramsJson.authCondition);
    }
    if(typeof(paramsJson.connectId)!="undefined"){
        this.dataSource.transport.queryParams.connectId = paramsJson.connectId;
    }
};

/**
 * 获取查询号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getQueryNo= function (   ) {
    return  this.dataSource.transport.queryParams.queryNo;
};


/**
 * 获取窗口号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getQueryWindow = function (   ) {
    return  this.dataSource.transport.queryParams.queryWindow;
};





/**
 * 刷新
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.doRefresh = function (paramsJson) {

    if(typeof(paramsJson)=="object"&&paramsJson!=null){
        this.setQueryParams(paramsJson);
    }
    this.dataSource.read();
};

/**
 * 取查询语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getQuerySQL = function ( ) {
    return this.dataSource.reader.querySQL;
};

/**
 * 取统计语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getStatSQL = function ( ) {
    return this.dataSource.reader.statSQL;
};



// function that gathers IDs of checked nodes
//checkType   1 包含半选状态  2 不包含半选状态
function checkedNodeIds(nodes, checkedNodes, checkType) {
    if(checkType!="1"){
        checkType = "0";
    }
    for (var i = 0; i < nodes.length; i++) {

        if(checkType=="1"){
            if (nodes[i].checked) {
                checkedNodes.push(nodes[i].SELECTEDKEY);
            }
            if (nodes[i].hasChildren) {
                checkedNodeIds(nodes[i].children.view(), checkedNodes , checkType);
            }
        }else{
            if (nodes[i].checked){
                checkedNodes.push(nodes[i].SELECTEDKEY);
                if (nodes[i].hasChildren) {
                    checkedNodeIds(nodes[i].children.view(), checkedNodes, checkType);
                }
            }
            if( !nodes[i].checked) {
                if (nodes[i].hasChildren) {
                    var j=checkedNodes.length;
                    checkedNodeIds(nodes[i].children.view(), checkedNodes , checkType);
                    if (j < checkedNodes.length) {
                        checkedNodes.push(nodes[i].SELECTEDKEY);
                    }
                }
            }
        }
        // if (nodes[i].hasChildren) {
        //     checkedNodeIds(nodes[i].children.view(), checkedNodes , checkType);
        // }
    }
}


/**
 * 取选中字段list
 *
 * @param checkType   1 包含半选状态  2 不包含半选状态
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getCheckArr =  function ( checkType ){
    if(checkType!="1"){
        checkType = "0";
    }
    var checkedNodes = [];
    checkedNodeIds(this.dataSource.view(), checkedNodes , checkType);

    return checkedNodes;
};


/**
 * 取选中字段package
 *
 * @param checkType   1 包含半选状态  2 不包含半选状态
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getCheckPackage =  function ( checkType ){
    if(checkType!="1"){
        checkType = "0";
    }
    var returnArr= this.getCheckArr( checkType );
    return returnArr.join(",");
};


/**
 * 取选中记录SQL
 *
 * @param checkType   1 包含半选状态  2 不包含半选状态
 * @return
 * @exception
 */
kendo.ui.TreeView.prototype.getCheckSQL = function( checkType ) {
    if(checkType!="1"){
        checkType = "0";
    }
    var checkedNodes = this.getCheckArr(checkType);
    var checkSQL="select * from ("+ this.getQuerySQL()+")t where ";
    if (checkedNodes.length > 0) {
        checkSQL=checkSQL+ " SELECTEDKEY in ("
        for(i=0;i<checkedNodes.length;i++){
            if(i==checkedNodes.length-1){
                checkSQL=checkSQL+"'"+checkedNodes[i]+"'"
            }else{
                checkSQL=checkSQL+"'"+checkedNodes[i]+"',"
            }
        }
        checkSQL=checkSQL+")";
    } else {
        checkSQL  = checkSQL+" 1=2 ";
    }
    return checkSQL;
};

////////////////////////2.扩展kendo TreeList///////////////////////////////////////////////
/**
 * 设置查询参数  queryNo 查询号
 *               queryWindow 窗口号
 *               parameters  参数JSON
 *               whereCondition 查询条件（不包含where字段）
 *               authCondition 权限条件（不包含where字段）
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.setQueryParams = function ( paramsJson ) {
    if(typeof(paramsJson)!="object"||paramsJson==null){
        return;
    }
    //if(typeof(paramsJson.queryNo)!="undefined"){
    //    this.dataSource.transport.options.queryParams.queryNo = paramsJson.queryNo;
    //}
    //if(typeof(paramsJson.queryWindow)!="undefined"){
    //    this.dataSource.transport.options.queryParams.queryWindow = paramsJson.queryWindow;
    //}
    //if(typeof(paramsJson.parameters)=="object"){
    //    this.dataSource.transport.options.queryParams.parameters = paramsJson.parameters;
    //}
    //if(typeof(paramsJson.whereCondition)!="undefined"){
    //    this.dataSource.transport.options.queryParams.whereCondition = paramsJson.whereCondition;
    //}

    if(typeof(paramsJson.queryNo)!="undefined"){
        this.dataSource.transport.queryParams.queryNo = paramsJson.queryNo;
    }
    if(typeof(paramsJson.queryWindow)!="undefined"){
        this.dataSource.transport.queryParams.queryWindow = paramsJson.queryWindow;
    }
    if(typeof(paramsJson.parameters)=="object"){
        this.dataSource.transport.queryParams.parameters = paramsJson.parameters;
    }
    if(typeof(paramsJson.whereParams)=="object"){
        this.dataSource.transport.queryParams.whereParams = paramsJson.whereParams;
    }
    if(typeof(paramsJson.orderParams)=="object"){
        this.dataSource.transport.queryParams.orderParams = paramsJson.orderParams;
    }
    if(typeof(paramsJson.whereCondition)!="undefined"){
        this.dataSource.transport.queryParams.whereCondition = encryptAES(paramsJson.whereCondition);
    }
    if(typeof(paramsJson.authCondition)!="undefined"){
        this.dataSource.transport.queryParams.authCondition = encryptAES(paramsJson.authCondition);
    }

    if(typeof(paramsJson.connectId)!="undefined"){
        this.dataSource.transport.queryParams.connectId = paramsJson.connectId;
    }



};


/**
 * 获取查询号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getQueryNo= function (   ) {
    return  this.dataSource.transport.queryParams.queryNo;
};


/**
 * 获取窗口号
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getQueryWindow = function (   ) {
    return  this.dataSource.transport.queryParams.queryWindow;
};

//check结构
//kendo.ui.Grid.prototype.selectedItems  =
//{
//    all:"n",
//    itemsPkg:"|~~|",
//    selectItem:function(key,checked ){
//        var split = "|~~|";
//        var hasFlag = (this.itemsPkg.indexOf(split+key+split)>-1);
//        if(this.all=="y"){
//            if(!checked){
//                if(!hasFlag){
//                    this.itemsPkg=this.itemsPkg+key+split;
//                }
//            }else{
//                this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
//            }
//        }else{
//            if(checked){
//                if(!hasFlag){
//                    this.itemsPkg=this.itemsPkg+key+split;
//                }
//            }else{
//                this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
//            }
//        }
//    },
//    getCheckPackage: function( ) {
//        var split = "|~~|";
//        var checkpackage = this.itemsPkg.split(split);
//        if (checkpackage.length <= 2) {
//            return this.all + split + "0" + split;
//        } else {
//            return this.all + split + (checkpackage.length - 2) + this.itemsPkg;
//        }
//    }
//
//};


kendo.ui.TreeList.prototype.initSelectedItems = function ( ) {
    var selectedItems = this.getSelectedItems();
    selectedItems.all="n";
    selectedItems.itemsPkg = "|~~|";
    //清除全选框
    var  $checkboxAll = this.thead.find(".headerCheckboxAll");
    if($checkboxAll.length>0){
        $checkboxAll.removeAttr("checked");
    }
};

kendo.ui.TreeList.prototype.getSelectedItems = function ( ) {
    if(this.selectedItems==null||typeof (this.selectedItems) == "undefined"){
        if(this.selectedItems==null){
            this.selectedItems={
                all:"n",
                itemsPkg:"|~~|",
                selectItem:function(key,checked ){
                    var split = "|~~|";
                    var hasFlag = (this.itemsPkg.indexOf(split+key+split)>-1);
                    if(this.all=="y"){
                        if(!checked){
                            if(!hasFlag){
                                this.itemsPkg=this.itemsPkg+key+split;
                            }
                        }else{
                            this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
                        }
                    }else{
                        if(checked){
                            if(!hasFlag){
                                this.itemsPkg=this.itemsPkg+key+split;
                            }
                        }else{
                            this.itemsPkg=this.itemsPkg.replace(split+key+split,split);
                        }
                    }
                },
                getCheckPackage: function( ) {
                    var split = "|~~|";
                    var checkpackage = this.itemsPkg.split(split);
                    if (checkpackage.length <= 2) {
                        return this.all + split + "0" + split;
                    } else {
                        return this.all + split + (checkpackage.length - 2) + this.itemsPkg;
                    }
                }
            };
        }
    }
    return this.selectedItems;
};

/**
 * 刷新
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.doRefresh = function (paramsJson) {
    this.initSelectedItems();
    if(typeof(paramsJson)=="object"&&paramsJson!=null){
        this.setQueryParams(paramsJson);

        if(typeof(paramsJson.queryNo)!="undefined"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.queryWindow)!="undefined"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.parameters)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.whereParams)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.orderParams)=="object"){
            this.dataSource.page(1);
        }
        else if(typeof(paramsJson.whereCondition)!="undefined"){

            this.dataSource.page(1);
        }else{
            this.dataSource.read();
        }

    }else {
        this.dataSource.read();
    }
};

/**
 * 获得当前数据JSON
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.serialize = function ( ) {

};

/**
 * 取查询语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getQuerySQL = function ( ) {
    return this.dataSource.reader.querySQL;
};

/**
 * 取统计语句
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getStatSQL = function ( ) {
    return this.dataSource.reader.statSQL;
};



/**
 * 取选中记录SQL
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getCheckSQL = function() {
    var checkAll = this.hasCheckBox();
    if(!checkAll){
        return this.getQuerySQL();
    }

    var checkSql="";
    var split = "|~~|";
    var temp=this.getSelectedItems().itemsPkg.substring(4, this.getSelectedItems().itemsPkg.length)
    var checkpackage=temp.split(split);
    checkpackage.pop(true);
    if(this.getSelectedItems().all=='y'){
        if(checkpackage.length<1){
            checkSql=this.getQuerySQL();
        }else{
            checkSql=this.getQuerySQL()+" WHERE SELECTEDKEY NOT IN ('"+checkpackage.join("','")+"')";
        }
        return checkSql;
    }else{
        if(checkpackage.length<1){
            checkSql=this.getQuerySQL()+" WHERE 1=2";
        }else{
            checkSql=this.getQuerySQL()+" WHERE SELECTEDKEY IN ('"+checkpackage.join("','")+"')";
        }
        return checkSql;
    }
};

/**
 * 取选中字段list
 *
 * @param checkType   1 包含半选状态  2 不包含半选状态
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getCheckArr =  function ( checkType ){
    if(checkType!="1"){
        checkType = "0";
    }
    var checkedNodes = [];
    var view=this.dataSource.view();
    checkedNodeIds(view, checkedNodes , checkType);

    return checkedNodes;
};

/**
 * 取选中字段package
 *
 * @param checkType   1 包含半选状态  2 不包含半选状态
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.getCheckPackage = function ( checkType ){
    if(checkType!="1"){
        checkType = "0";
    }
    var returnArr= this.getCheckArr( checkType );
    return returnArr.join(",");
};

/**
 * 是否存在hasCheckBox框
 *
 * @param
 * @return
 * @exception
 */
kendo.ui.TreeList.prototype.hasCheckBox = function(){
    var gridOptions = this.options;
    return gridOptions.checkAll;
};




