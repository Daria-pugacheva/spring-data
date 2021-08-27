angular.module('market-front').controller('orderController',
    function ($scope, $http, $location,$routeParams) {
    const contextPath = 'http://localhost:8189/market/';

        $scope.fillOrder = function () {
            $http.get(contextPath + 'api/v1/products/order/' + $routeParams.productId)
                .then(function (response) {
                    $scope.orderPage = response.data;
                });
        };


});