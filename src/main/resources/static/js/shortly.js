var Shortly = angular.module('Shortly', []).controller('ShortlyController', ['$scope', '$http', '$window', '$log', function ($scope, $http, $window, $log) {

    $scope.hasError = false;
    $scope.error = "";
    $scope.shortUrl = "";
    $scope.requestUrl = "https://";
    $scope.isHome = false;

    $scope.initHome = function () {
        if (!!$window.location.pathname && $window.location.pathname.trim() != "/") {
            $scope.isHome = false;
            $http({method: "GET", url: "/rest" + $window.location.pathname}).then(function (response) {
                $scope.hasError = response.data.hasError;
                $scope.error = response.data.errorMessage;
                if (!$scope.error && response.data.data && response.data.data.url) {
                    $window.location.href = response.data.data.url;
                }
            }, function (response) {
                $scope.hasError = true;
                $scope.error = 'Something went wrong!';
            });
        } else {
            $scope.isHome = true;
        }
    };

    $scope.shorten = function (requestUrl) {
        var requestData = {
            'url': requestUrl
        };
        $scope.shortUrl = "";
        $scope.hasError = false;
        $scope.error = "";
        $http({method: "POST", url: "/rest/save", data: requestData}).then(function (response) {
            $scope.hasError = response.data.hasError;
            $scope.error = response.data.errorMessage;
            if (!$scope.error && response.data.data && response.data.data.tiny_url) {
                $scope.shortUrl = response.data.data.tiny_url;
            }
        }, function (response) {
            $scope.hasError = true;
            $scope.error = 'Something went wrong!';
        });
    };
}]);