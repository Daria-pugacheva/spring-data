(function () {
    angular
        .module('market-front', ['ngRoute'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            // .when('/order/:productId', { //в итоге я пересмотрела логику и это не надо, НО вот с такими двумя when для разных вариантов обращения к странице с корзиной оба варианта функционируют.
            //     templateUrl: 'order/order.html',
            //     controller: 'orderController'
            // })
            .when('/order', {
                templateUrl: 'order/order.html',
                controller: 'orderController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http) {
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http) {
    const contextPath = 'http://localhost:8189/market';

});
