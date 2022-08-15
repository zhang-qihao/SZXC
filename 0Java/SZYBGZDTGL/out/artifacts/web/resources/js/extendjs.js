App.factory('BusinessFactory', ["$rootScope", function ($rootScope) {
    return function ($scope, $element) {
        this.scope = $scope;
        this.element = $element;

        this.showDocument = function (params) {
            if (typeof (params) == "undefined") {
                return;
            }
            var keyNo = params.keyNo;
            var keyType = params.keyType;
            var keyName = params.keyName;
            var callBackFun = params.callBackFun;
            var element = params.rootElement;
            var editable = params.editable;
            var filter = params.filter;
            if (filter != "img" && filter != "video") {
                filter = "all";
            }

            if (editable == true) {
                var url = '/frame/systemmanagement/documentmanagement/documentaddlist.action?frameControlSubmitFunction=init&keyNo=' + encodeURIComponent(keyNo) + '&keyType=' + encodeURIComponent(keyType) + '&filter=' + encodeURIComponent(filter) + '&keyName=' + encodeURIComponent(keyName);
                $scope.tools.slideReveal({
                    view: url,
                    file: ['/frame/systemmanagement/documentmanagement/documentaddlist.js'],
                    width: '400px',
                    rootElement: element,
                    callBackFun: callBackFun
                });
            } else {
                var url = '/frame/systemmanagement/documentmanagement/documentshowlist.action?frameControlSubmitFunction=init&keyNo=' + encodeURIComponent(keyNo) + '&keyType=' + encodeURIComponent(keyType) + '&filter=' + encodeURIComponent(filter) + '&keyName=' + encodeURIComponent(keyName);
                $scope.tools.slideReveal({
                    view: url,
                    file: ['/frame/systemmanagement/documentmanagement/documentshowlist.js'],
                    width: '400px',
                    rootElement: element,
                    callBackFun: callBackFun
                });
            }
        };


    }
}]);

