angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                page: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    };

    $scope.loadNextPage = function (currentPageNumber) {
        $scope.loadProducts(currentPageNumber + 1);
    };

    $scope.loadPreviousPage = function (currentPageNumber) {
        $scope.loadProducts(currentPageNumber - 1);
    };

    $scope.deleteProduct = function (productId, currentPageNumber) {
        $http({
            url: contextPath + 'products/delete',
            method: 'GET',
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.loadProducts(currentPageNumber);
        });
    };

    $scope.loadProducts(); //при старте загружаем первую страницу

});