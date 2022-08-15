
function UserLoginCtrl($scope, $localStorage, $window,$element){

    BaseCtrl.call(this,$scope, $localStorage, $window,$element);
    $scope.init = function(){
        kendo.culture("zh-CN");
        if( $scope.form.frameControlOption=='logined'){
            $scope.pageForm4 = {};
            //加载用户权限平台
            var callbackFun = function () {
                $scope.form.sysProgramDOList = [];
                $scope.form.sysProgramDOList = $scope.form.sysProgramDOList.concat($scope.pageForm4.sysProgramDOList);
                $scope.form.menuContextDTOS = [];
                $scope.form.menuContextDTOS = $scope.form.menuContextDTOS.concat($scope.pageForm4.menuContextDTOS);
            };
            $scope.submitTargetForm("/webcxcy/show/webSitePageProgram.action", $scope.pageForm4, "loadWebSitePageProgram", callbackFun, null);
        }
    };

    //登录
    $scope.login= function( ) {
        //$scope.form.extUserIdList=[];
        //$scope.form.extUserIdList[0] = 'U005';

        $scope.loginForm= {

            frameControlOption:"",
            loginType:"",
            password:$scope.form.password,
            userId:$scope.form.userId
            //loginType:"ExtUser",
            // loginType:"",
            //extUserIdList: $scope.form.extUserIdList
        };
        var logincallbackFun = function(){
            window.location.href= root+"/index.action";
        };
        $scope.submitTargetForm("/login/login.action", $scope.loginForm, "login", logincallbackFun , null);
    };

    //校验
    var initValidationLogin = function(){

    };
}
// Forms Pickers and More Controller
angular.module('app').controller('UserLoginCtrl', ['$scope', '$localStorage', '$window','$element',UserLoginCtrl ]);