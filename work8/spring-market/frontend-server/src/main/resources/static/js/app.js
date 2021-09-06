var marketApp = angular.module('marketApp', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'views/home.html',
            controller: 'HomeController'
        });
        $routeProvider.when('/products', {
            templateUrl: 'views/products.html',
            controller: 'ProductsController'
        });
        $routeProvider.when('/cart', {
            templateUrl: 'views/cart.html',
            controller: 'CartController'
        });
        $routeProvider.when('/orders', {
            templateUrl: 'views/orders.html',
            controller: 'OrdersController'
        });
        $routeProvider.otherwise({
            redirectTo: '/home'
        });
    }).run(function ($rootScope, $templateCache) {
        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            if (typeof (current) !== 'undefined') {
                $templateCache.remove(current.templateUrl);
            }
        });
    });
