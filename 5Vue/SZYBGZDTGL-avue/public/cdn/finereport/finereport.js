const baseUrl = "http://172.16.10.14:6080/sspsxt/decision/view/report?viewlet=ehis/";	//测试
// const baseUrl = "http://192.168.99.207:6095/webroot/decision/view/report?viewlet=ehis/"; //常熟
// const baseUrl = "http://192.168.218.61:8095/webroot/decision/view/report?viewlet=ehis/"; //苏州

/**
 * 帆软报表打印
 *
 * @param  printFile                   报表名称
 * @param  targetFrame                 空
 * @param  parm                        参数拼接（拼路径用）
 * @return
 * @exception
 */
function FineReport(printFile,targetFrame,parm){

	this.printFile="";
	this.parameters=JSON.parse("{}");
	if(typeof(printFile)=="undefined"||printFile==null){

	}else{
		this.printFile=printFile;
	}
	this.targetFrame=null;
	if(typeof(targetFrame)=="undefined"||targetFrame==null){

	}else{
		this.targetFrame=targetFrame;
	}


	var initPrintFile= function(printFile){
		if(typeof(printFile)=="undefined"||printFile==null){
			alert("initPrintFile()函数错误，参数printFile不可为空！")
			return ;
		}
		this.printFile=printFile;
	};
	this.initPrintFile=initPrintFile;

	var initTargetFrame= function(targetFrame){
		if(typeof(targetFrame)=="undefined"||targetFrame==null){
			alert("initTargetFrame()函数错误，参数targetFrame不可为空！")
			return ;
		}
		this.targetFrame=targetFrame;
	};
	this.initTargetFrame=initTargetFrame;

	var initSql= function(subRptName,sql){
		if(typeof(subRptName)=="undefined"||subRptName==null||subRptName==""){
			alert("addSql()函数错误，参数名不可为空！")
			return ;
		}
		if(typeof(sql)=="undefined"||sql==null||sql==""){
			alert("addSql()函数错误，参数sql不可为空！")
			return ;
		}
		this.parameters[subRptName]=sql;
	};
	this.initSql=initSql;


	var initParam= function(subRptName,sql){
		if(typeof(subRptName)=="undefined"||subRptName==null||subRptName==""){
			alert("addSql()函数错误，参数名不可为空！")
			return ;
		}
		if(typeof(sql)=="undefined"||sql==null||sql==""){
			alert("addSql()函数错误，参数sql不可为空！")
			return ;
		}
		this.parameters[subRptName]=sql;
	};
	this.initParam=initParam;

	var init= function(){
		var fineReportUrl = baseUrl+printFile+""+parm+"";
		if(fineReportUrl==null||fineReportUrl==""){
			alert("FineReport.init()函数错误，fineReportUrl不可为空！");
			return
		}
		if(this.parameters==null ){
			alert("FineReport.init()函数错误，parameters不可为空！");
			return;
		}

		var tempForm = document.createElement("form");
		document.body.appendChild(tempForm);
		tempForm.action = fineReportUrl;
		tempForm.method = "post";
		tempForm.style.display = "none";

		if( this.targetFrame!=''){
			if(this.targetFrame.name==null||this.targetFrame.name==""){
				this.targetFrame.name=this.targetFrame.id;
			}
			tempForm.target=this.targetFrame.name;
		}else{
			var width=777, height=750;
			var left = (screen.width/2) - width/2;
			var top = (screen.height/2) - height/2;
			var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbar=no,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
			var printWindow =view(printFile,parm);
		}

	};
	this.init=init;

}

function view(printFile,parm){
	var url = baseUrl+printFile+""+parm+"";
	var WinWidth = 1000;
	var WinHeight = 620;
	// if(top.window && top.window.process && top.window.process.type){
	// 	console.info("封装打开方式");
	// 	var ipc = top.window.nodeRequire('ipc');
	// 	var remote = top.window.nodeRequire('remote');
	// 	var browserwindow = remote.require('browser-window');
	// 	var winProps = {};
	// 	winProps.width = '800';
	// 	winProps.height = '600';
	// 	winProps.title = 'printWindow';
	// 	winProps['web-preferences'] = {'plugins':true};
	// 	winProps['node-integration'] = false;
	// 	var focusedId = browserwindow.getFocusedWindow().id;
	// 	treeWin = new browserwindow(winProps);
	// 	treeWin.loadUrl($.UrlUpdateParams(url,'focusedId',focusedId));
	// 	treeWin.on('closed',function(){
	// 		treeWin = null;
	// 		SetIsOpenDlg(0);
	// 		canClick=true;
	// 	});
	// 	ipc.on('message-departmentTree',function(args){
	// 		if(treeWin){
	// 			SetIsOpenDlg(0);
	// 			canClick=true;
	// 			treeWin.close();
	// 			treeWin = null;
	// 			var ret = args;
	// 		}
	// 	});
	// }else{
		console.info("window.open普通打开方式");
		treeWin = window.open(url, "printWindow", "scrollbars=yes,toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,width=" + WinWidth + ",height=" + WinHeight + ",left=" + (window.screen.width - WinWidth) / 2 + ",top=" + (window.screen.height - WinHeight - 100) / 2);

	// }
	return treeWin;
}

(function ($) {
	$.extend({
		Request: function (m) {
			var sValue = location.search.match(new RegExp("[\?\&]" + m + "=([^\&]*)(\&?)", "i"));
			return sValue ? sValue[1] : sValue;
		},
		UrlUpdateParams: function (url, name, value) {
			var r = url;
			if (r != null && r != 'undefined' && r != "") {
				value = encodeURIComponent(value);
				var reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
				var tmp = name + "=" + value;
				if (url.match(reg) != null) {
					r = url.replace(eval(reg), tmp);
				}
				else {
					if (url.match("[\?]")) {
						r = url + "&" + tmp;
					} else {
						r = url + "?" + tmp;
					}
				}
			}
			return r;
		}

	});
})(jQuery);