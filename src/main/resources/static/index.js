angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
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


    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + 'api/v1/products/' + productId)
                .then(function (response) {
            $scope.loadProducts(currentPageIndex);
        });
    };

    //в этой реализации у метода только удачный исход, т.к. нет варианта ввода некорректной категории (ее нет)
    $scope.createNewProduct=function (){
        $http.post(contextPath + 'api/v1/products',$scope.new_product)
            .then(function successCallback (response){
                $scope.loadProducts(currentPageIndex);
                $scope.new_product=null;
            });
    }

    $scope.updateProduct=function (){
        $http.put(contextPath + 'api/v1/products', $scope.updated_product)
            .then(function successCallback (response){
                console.log($scope.updated_product);
                $scope.loadProducts(currentPageIndex);
                $scope.updated_product=null;
            },function failureCallback (response) {
                alert(response.data.message);
            });
    }

// //вариант обновления через реквестпарамы, но тоже не работает
//     $scope.updateProduct=function (){
//          $http({
//             url: contextPath + 'api/v1/products',
//             method: 'PUT',
//             params: {
//                 id: $scope.updated_product.id,
//                 title: $scope.updated_product.title,
//                 price: $scope.updated_product.price
//             }
//         }).then(function successCallback (response){
//                 console.log($scope.updated_product);
//                 $scope.loadProducts(currentPageIndex);
//                 $scope.updated_product=null;
//             });
//     }

    $scope.loadProducts(); //при старте загружаем первую страницу

});

//АРХИВ старых домашек
// //вариант метода удаления,когда не было реализации REST
// $scope.deleteProduct = function (productId, currentPageNumber) {
//     $http({
//         url: contextPath + 'products/delete',
//         method: 'GET',
//         params: {
//             id: productId
//         }
//     }).then(function (response) {
//         $scope.loadProducts(currentPageNumber);
//     });
// };