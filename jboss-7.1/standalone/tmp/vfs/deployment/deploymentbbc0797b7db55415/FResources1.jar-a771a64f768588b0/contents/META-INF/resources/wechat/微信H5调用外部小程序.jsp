<%@ page import="com.eframework.core.config.weixinconfig.WeixinConfig" %><%--
  Created by IntelliJ IDEA.
  User: xc45
  Date: 2018/7/23
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="e" uri="/eframe-tags" %>
<%
    String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>H5跳转小程序</title>

    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <%--    破解微信图片--%>
    <meta name="referrer" content="never">
    <link href="<%=root%>/weixin/css/iconfont.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=root%>/weixin/css/mui.min.css">
    <e:weixin/> <!--微信 -->
    <script>

        wx.ready(function () {

        });
        wx.error(function (res) {
            console.log(JSON.stringify(res));
        });
        var launchBtn = document.getElementById('launch-btn');
        launchBtn.addEventListener('ready', function (e) {
            console.log('开放标签 ready')
        });
        launchBtn.addEventListener('launch', function (e) {
            alert('开放标签 success')
        });
        launchBtn.addEventListener('error', function (e) {
            console.log('开放标签 fail', e.detail)
        })
    </script>
    <style>

        html,
        body {
            background: #F3F5F9;
            color: #333;
        }

        .card {
            position: relative;
            z-index: 2;
            margin-bottom: 13px;
            border-radius: 10px;
            background: #fff;
            /*padding: 0 10px;*/
            box-shadow: -1px 1px 9px 0px rgba(81, 85, 89, 0.24);
        }

        .mui-bottom p {
            font-size: 10px;
            color: #999;
        }

        .menu > div {
            text-align: center;
            margin-top: 8px;
            height: 36px;
        }

        .card-content {
            padding: 10px;
            border-bottom: 1px solid #EDEDED;
        }

        .card-content:last-child {
            border: none;
        }

        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        @-webkit-keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        @keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        wx-open-launch-weapp {
            position: absolute;bottom: 40%;left: 0;right: 0;display: flex;flex-direction: column;align-items: center;
        }
    </style>
</head>

<body>
<div id="app">
    <div>
        <div class="card mui-content" style="margin-top: 10px;">
            <div class="card-content mui-content-padded">
                <div style="border-width: 3px; border-style: solid; border-color: rgb(68, 79, 129);">
                    <div style="padding: 20px; line-height: 30px;">
                        <p>第一步：长按并识别下方的二维码打开小程序</p>
                        <div style="text-align: center">
                            <%--username:小程序原始Id   path:小程序页面--%>
                            <wx-open-launch-weapp id="launch-btn" username="gh_82fd73ae1cad" path="/pages/humanSociety/index?appId=202109220001&areaCode=320500&areaName=苏州">
                                <template>
                                    <button style="width: 200px; height: 45px; text-align: center; font-size: 17px; display: block; margin: 0 auto; padding: 8px 24px; border: none; border-radius: 4px; background-color: #07c160; color:#fff;">
                                        打开小程序
                                    </button>
                                </template>
                            </wx-open-launch-weapp>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="<%=root%>/weixin/js/mui.min.js"></script>
<script>

</script>
</html>