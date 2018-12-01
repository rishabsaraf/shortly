var Shortly = angular.module('Shortly', []).controller('ShortlyController', ['$scope', '$http', '$timeout', function ($scope, $http, $timeout) {
    $scope.hasError = false;
    $scope.error = "";
    $scope.shortUrl = "";
    $scope.requestUrl = "https://";
    $scope.copyButtonText = "Copy";
    $scope.isCopied = false;

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

    $scope.copyToClipboard = function (elementId) {
        document.getElementById(elementId).select();
        document.execCommand("copy");
        $scope.copyButtonText = "Copied";
        $scope.isCopied = true;
        $timeout(function () {
            $scope.copyButtonText = "Copy";
            $scope.isCopied = false;
        }, 1200);
    };

}]);