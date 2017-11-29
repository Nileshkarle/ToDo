var toDo = angular.module('toDo');

toDo.controller('loginController', function($scope, loginService,$location){
	$scope.loginUser = function(){
		var a=loginService.loginUser($scope.user,$scope.error);
			a.then(function(response) {
				console.log(response.data.responseMessage);
				localStorage.setItem('token',response.data.responseMessage);
			
				$location.path("/home");
			},function(response){
				$scope.error=response.data.responseMessage;
			});
	}
});