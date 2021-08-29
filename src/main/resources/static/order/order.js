angular.module('market-front').controller('orderController',
    function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

          $scope.loadOrderPage = function (){
            $http.get(contextPath + 'api/v1/products/orderInfo')
                .then(function (response) {
                    console.log("Получен от сервера список товаров в заказе")
                    $scope.orderPage = response.data;
                });
        };

        $scope.loadOrderPage();

});