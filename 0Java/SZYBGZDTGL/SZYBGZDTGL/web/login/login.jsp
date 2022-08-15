<%@page contentType="text/html;charset=UTF-8" language="java"  import="com.eframework.core.config.systemconfig.SystemConfig,com.eframework.core.xml.config.systemconfig.SystemConfigXML" %>
<%@taglib prefix="e" uri="/eframe-tags"%>
<%
    String root = request.getContextPath();
    String jsVersion= SystemConfig.getConfigXML().getJsVersion();
%>
<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus" data-ng-app="app" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus" data-ng-app="app" lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>用户登录</title>

    <meta name="description" content="OneUI - Admin Dashboard Template &amp; UI Framework created by pixelcave and published on Themeforest">
    <meta name="author" content="pixelcave">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Icons -->
    <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
    <link rel="shortcut icon" href="<%=root%>/assets/img/favicons/favicon.png">

    <link rel="icon" type="image/png" href="<%=root%>/assets/img/favicons/favicon-16x16.png" sizes="16x16">
    <link rel="icon" type="image/png" href="<%=root%>/assets/img/favicons/favicon-32x32.png" sizes="32x32">
    <link rel="icon" type="image/png" href="<%=root%>/assets/img/favicons/favicon-96x96.png" sizes="96x96">
    <link rel="icon" type="image/png" href="<%=root%>/assets/img/favicons/favicon-160x160.png" sizes="160x160">
    <link rel="icon" type="image/png" href="<%=root%>/assets/img/favicons/favicon-192x192.png" sizes="192x192">

    <link rel="apple-touch-icon" sizes="57x57" href="<%=root%>/assets/img/favicons/apple-touch-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="<%=root%>/assets/img/favicons/apple-touch-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=root%>/assets/img/favicons/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="<%=root%>/assets/img/favicons/apple-touch-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=root%>/assets/img/favicons/apple-touch-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="<%=root%>/assets/img/favicons/apple-touch-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="<%=root%>/assets/img/favicons/apple-touch-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="<%=root%>/assets/img/favicons/apple-touch-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="<%=root%>/assets/img/favicons/apple-touch-icon-180x180.png">
    <!-- END Icons -->

    <!-- Stylesheets -->
    <!-- Page JS Plugins CSS -->
    <link rel="stylesheet" href="<%=root%>/assets/js/plugins/magnific-popup/magnific-popup.min.css?jsVersion=<%=jsVersion%>">


    <!-- Bootstrap and OneUI CSS framework -->

    <link rel="stylesheet" href="<%=root%>/assets/css/bootstrap.min.css?jsVersion=<%=jsVersion%>">
    <link rel="stylesheet" id="css-main" href="<%=root%>/assets/css/oneui.css?jsVersion=<%=jsVersion%>">

    <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
    <!-- <link rel="stylesheet" id="css-theme" href="<%=root%>/assets/css/themes/flat.min.css"> -->

    <link rel="stylesheet" href="<%=root%>/assets/js/plugins/slick/slick.min.css?jsVersion=<%=jsVersion%>">
    <link rel="stylesheet" href="<%=root%>/assets/js/plugins/slick/slick-theme.min.css?jsVersion=<%=jsVersion%>">
    <link href="<%=root%>/assets/css/kendo/kendo.common.min.css?jsVersion=<%=jsVersion%>" rel="stylesheet">
    <link href="<%=root%>/assets/css/kendo/kendo.rtl.min.css?jsVersion=<%=jsVersion%>" rel="stylesheet">
    <link href="<%=root%>/assets/css/kendo/kendo.silver.min.css?jsVersion=<%=jsVersion%>" rel="stylesheet">
    <link href="<%=root%>/assets/css/kendo/kendo.silver.mobile.min.css?jsVersion=<%=jsVersion%>" rel="stylesheet">

    <link rel="stylesheet"   href="<%=root%>/frame/resource/css/ext-kendo.css?jsVersion=<%=jsVersion%>">

    <script >
        var root = "<%=root%>";
        var frameControlUserId="";
        var frameCrypto="true";
        //格式化URL
        function formatURL(url) {
            if (url.indexOf("http:") == 0 || url.indexOf("https:") == 0) {

            }else if (url.indexOf("//") == 0 ) {
                url=str.substring(1);
            } else {
                if (url.indexOf("/") == 0) {
                    url = root + url;
                } else {
                    url = root + "/" + url;
                }
            }
            return url;
        }
    </script>
    <!-- END Stylesheets -->
</head>
<style>
    html,body{
        width:100%;
        height: 100%;
        text-align: center;
        font-size:12px;
        color:#464646;
        font-family:Arial, Helvetica, sans-serif;
        background-color: #ffffff;
    }

    input {
        /*-webkit-appearance: textfield;*/
        background-color: white;
        /*-webkit-rtl-ordering: logical;*/
        /*user-select: text;*/
        /*cursor: auto;*/
        padding: 1px;
        border: 1px solid #5d99c1bd;
        border-width: 1px;
        border-style: solid;
        border-color: deepskyblue;
        /*border-image: initial;*/
    }
   .myinput{
       padding-left:12px;
       margin:0 auto;
       margin-bottom:30px;
       width:310px;
       height:40px;
       border:1px solid #E1E1E1;
       border-radius:5px;
       color:#000000;
       font-size: 16px;
       background: white; !important;
   }
    input[type="checkbox"] {
        margin: 3px 3px 3px 4px;
        -webkit-appearance: checkbox;
        box-sizing: border-box;
    }
   .rem-password input[type='checkbox']{
       width: 12px;
       height: 12px;
       -webkit-appearance:none;
       border: 1px solid #297cfa;
       border-radius: 2px;
       outline: none;
       position: absolute;
       top: 1px;
       left: 0px;
   }
   .rem-password input[type=checkbox]:checked{
       background: url("../login/img/checkbox_icon.png")no-repeat center;
       background-size: 12px;
   }

</style>
<body  data-ng-controller="UserLoginCtrl" >
<e:mainPage>
<e:hidden name="frameControlOption"/>
<!-- Lock Screen Content -->
<div style="width: 100%;height:100%;position: relative;">
    <%--背景图--%>
    <img src="<%=root%>/login/img/loginbg.png" style="width:100%; height: 100%;"/>

    <%--  登录标题为图片形式  样式--%>

    <%--<div style="position: absolute;top:0;width:100%;height:16%;background: #FFF;color: #0584fa;font-size: 48px;font-weight: bold;">--%>
        <%--<img src="<%=root%>/login/img/title.png" style="height:100%;"/>--%>
    <%--</div>--%>

    <%--登录标题为 文字形式 --%>
    <div style="position: absolute;top: 80px;right: 0;left: 0;">
        <img src="<%=root%>/login/img/logo.png" alt="" style="width: 80px;position: absolute;top: -10px;">
        <span style="color: #FFFFFF;font-size: 46px;font-family: FZZZHONGJW--GB1-0;margin-left: 90px;font-weight: bold;letter-spacing: 4px">张家港人社一体化系统</span>
    </div>

    <%--输入信息框--%>
    <div style="position: absolute;right:18%;top:26%;width:400px;background: #FFF;border: 10px solid #d0dff6;border-radius: 20px;" >
        <div style="text-align:center !important; font-size:28px;color:#2F8CFF;height: 96px;line-height: 96px;">登&nbsp;&nbsp;录</div>
        <div><input ng-model="form.userId"  placeholder="请输入账号/用户名" type="text" class="myinput"></div>
        <div><input ng-model="form.password" placeholder="请输入密码" type="password"  class="myinput"></div>
       <div style="color: #2f8cff;font-size: 15px;margin: -20px auto 24px;width: 310px;">
           <div class="rem-password" style="text-align:left;position: relative;">
                <input type="checkbox">
                 <span style="margin-left: 22px;">记住密码</span>
           </div>
<%--           <div style="text-align:right;color: #2f8cff;font-size: 15px;margin: -20px auto 24px;width: 310px;">
               忘记密码?
           </div>--%>
       </div>

        <div ng-click="login()" style="cursor: pointer;margin:0 auto;border-radius:10px;text-align:center;width:310px;height:40px;line-height: 40px;background-color:#2F8CFF;font-size:20px;border-radius:8px;color:#fff">
            登&nbsp;&nbsp;录
        </div>
        <%--没有账号 去注册--%>
<%--        <div style="text-align: center;font-size: 14px;margin: 10px auto 30px;">
            <span style="color: #333333;">没有账号？</span><span style="color: #2F8CFF;">去注册</span><img src="../login/img/goregister.png" alt="" style="width: 14px;margin-left: 6px;">
        </div>--%>
        <%--温馨提示--%>
        <div style="width: 310px;margin: 0 auto 30px; display: -webkit-box;font-size: 15px;">
            <div style="width: 90px;color: #FDB405;"><%--温馨提示：--%></div>
            <%--<div style="-webkit-box-flex: 1;text-align: left;color: #999999;">
                <div>1.这里是温馨提示的内容</div>
                <div>2.这里是温馨提示的内容</div>
            </div>--%>
        </div>
    </div>
    <div style="position: absolute;bottom: 0;width: 100%;height:60px;line-height:60px;background:#FFF;color: #2a66d4;font-size: 16px;">
        建议使用1920*1080分辨率  Google浏览器  <%--版权所有  苏州市财政投资评审中心--%>
    </div>
</div>


<!-- END Lock Screen Content -->




<!-- Apps Modal -->
<!-- Opens from the button in the header -->
<div class="modal fade" id="apps-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-sm modal-dialog modal-dialog-top">
        <div class="modal-content">
            <!-- Apps Block -->
            <div class="block block-themed block-transparent">
                <div class="block-header bg-primary-dark">
                    <ul class="block-options">
                        <li>
                            <button data-dismiss="modal" type="button"><i class="si si-close"></i></button>
                        </li>
                    </ul>
                    <h3 class="block-title">Apps</h3>
                </div>
                <div class="block-content">
                    <div class="row text-center">
                        <div class="col-xs-6">
                            <a class="block block-rounded" href="base_pages_dashboard.html">
                                <div class="block-content text-white bg-default">
                                    <i class="si si-speedometer fa-2x"></i>
                                    <div class="font-w600 push-15-t push-15">Backend</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a class="block block-rounded" href="frontend_home.html">
                                <div class="block-content text-white bg-modern">
                                    <i class="si si-rocket fa-2x"></i>
                                    <div class="font-w600 push-15-t push-15">Frontend</div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Apps Block -->
        </div>
    </div>
</div>
<!-- END Apps Modal -->

<!-- Pop Out Modal -->
<div class="modal  fade" id="modal-popout" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout">
        <div class="modal-content">
            <div class="block block-themed animated fadeIn">
                <div class="block-content block-content-full block-content-narrow">
                    <form class="js-validation-login form-horizontal push-30-t push-50" action="base_pages_dashboard.html" method="post" novalidate="novalidate">
                        <div class="form-group" style="display: none;">
                            <div class="col-xs-12">
                                <e:number name="aaaa" label="abc"/>
                            </div>
                        </div>
                    </form>
                    <!-- END Login Form -->
                </div>
            </div>


        </div>
    </div>
</div>
<!-- END Pop Out Modal -->

<!-- OneUI Core JS: jQuery, Bootstrap, slimScroll, scrollLock, Appear, CountTo, Placeholder, Cookie and App.js -->
<script src="<%=root%>/assets/js/core/jquery.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/bootstrap.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/jquery.slimscroll.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/jquery.scrollLock.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/jquery.appear.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/jquery.countTo.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/jquery.placeholder.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/js.cookie.min.js?jsVersion=<%=jsVersion%>"></script>





<script src="<%=root%>/frame/resource/js/plugins/slidereveal/slidereveal.js?jsVersion=<%=jsVersion%>"></script> <!-- 左侧划出窗口 -->
<script src="<%=root%>/frame/resource/js/plugins/bootstrap-notify/bootstrap-notify.js?jsVersion=<%=jsVersion%>"></script>    <!-- 错误提示 -->
<script src="<%=root%>/frame/resource/js/plugins/screenfull/screenfull.js?jsVersion=<%=jsVersion%>"></script>   <!-- 全屏 -->

<!-- OneUI Core JS: AngularJS -->
<script src="<%=root%>/assets/js/core/angular.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/frame/resource/js/plugins/fileupload/ng-file-upload.min.js?jsVersion=<%=jsVersion%>"></script>  <!-- 文件上传 -->
<script src="<%=root%>/assets/js/core/angular-ui-router.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/plugins/ui-bootstrap-tpls/ui-bootstrap-tpls-2.3.1.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/angular-scroll.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/ocLazyLoad.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/ngStorage.min.js?jsVersion=<%=jsVersion%>"></script>



<!-- OneUI Core JS: Kendo -->
<script src="<%=root%>/assets/js/core/kendo.all.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/frame/resource/js/plugins/resizable/Resizable.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/core/kendo.poptextbox.js?jsVersion=<%=jsVersion%>"></script>

<!-- Page JS Plugins -->
<script src="<%=root%>/assets/js/plugins/magnific-popup/magnific-popup.min.js?jsVersion=<%=jsVersion%>"></script>

<script src="<%=root%>/assets/js/cache/syscache.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/app.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/factorys.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/assets/js/directives.js?jsVersion=<%=jsVersion%>"></script>


<script src="<%=root%>/assets/js/plugins/slick/slick.min.js?jsVersion=<%=jsVersion%>"></script>
<script src="<%=root%>/login/login.js?jsVersion=<%=jsVersion%>"></script>
</e:mainPage>
</body>
</html>