angular.module('market-front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http ({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes=function (startPage,endPage){
        let arr = [];
        for(let i = startPage; i<endPage+1; i++){
            arr.push(i);
        }
        return arr;
    }

    $scope.goToEditProductPage = function (productId){
        $location.path('/edit_product/' + productId);
    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + 'api/v1/products/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
            });
    };

    // //Запрос на бэкэнд добавления товара в корзину
    // $scope.fillOrder = function (id) {
    //     $http.get(contextPath + 'api/v1/products/order/' + id)
    //         .then(function (response) {
    //             $scope.orderPage = response.data;
    //             $location.path('/order/' + $scope.orderPage);
    //         });
    // };


    $scope.goToOrderPage=function (productId){
        $location.path('/order/'+ productId);
    }


    $scope.loadProducts();


});