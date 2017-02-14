
var app = angular.module("MyApp",['ui.router']);
app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider.state('home', {
		url:'/home',
		templateUrl:'views/home.html',
		controller:'HomeController'
	});
	
	$stateProvider.state('chercher', {
		url:'/chercher',
		templateUrl:'views/chercher.html',
		controller:'MyController'
	});
	
	$stateProvider.state('newProduit', {
		url:'/newProduit',
		templateUrl:'views/newProduit.html',
		controller:'newProduitController'
	});
	$stateProvider.state('DelProduit', {
		url:'/DelProduit',
		templateUrl:'views/DelProduit.html',
		controller:'DelProduitController'
	});
});

app.controller("DelProduitController", function() {
	
  });
app.controller('HomeController', function() {
	
});
app.controller('newProduitController', function($scope,$http) {
	$scope.produit={designation:"",prix:0.0,quantite:0};
	$scope.mode=0;
	$scope.saveProduit= function (){
		$http.post('http://localhost:8080/produits', $scope.produit)
		.success(function (data){
			
			$scope.produit=data;
			
			$scope.mode=1;
		})
		.error(function (err){
			console.log(err);
		})
		
	}
	$scope.newProd=function(){
		$scope.produit={designation:"",prix:0.0,quantite:0};
		$scope.mode=0;
	}
	
});


app.controller("MyController",['$scope', '$http', function($scope, $http){
	$scope.pageProduits={};
	$scope.motCle="";
    $scope.pageCourante=0;
    $scope.size=4;
    $scope.pages=[];
	$scope.chercherProduit=function (){
	
	 $http.get("http://localhost:8080/chercheProduits?mc="+$scope.motCle+"&page="+$scope.pageCourante+"&size="+$scope.size )
	 .success(function (data) {

		$scope.pageProduits=data;
		console.log(data);
		$scope.pages=new Array(data.totalPages);
	})
	.error(function(err) {
	    // Handle error
	   
	    console.log(err);
	  
	  });
	}
	 
	$scope.getProduits=function () {
		 $scope.pageCourante=0;
		 $scope.chercherProduit();
	}
	
	$scope.goToPage=function (p){
     $scope.pageCourante=p;
     $scope.chercherProduit();
	}
	$scope.deletep=function (id){
		 	var produit_to_delete = $scope.produit[id];
		API.DeleteProduit ({idx : produit_to_delete.id}, function (success){
			$scope.produit.splice (idx,1);
		});
	}
	
}]);
