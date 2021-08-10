angular.module('shopApp', []).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8181/shop';
    $scope.fillTable = function() {
        $http({
            url: contextPath + '/api/v1/product',
            method: 'GET'
//            params: 
        }).then(function(response){
            $scope.productPage = response.data;
            $scope.productList = $scope.productPage.content;
        });
    };
    $scope.fillTable();
});