var toDo = angular.module('toDo');

toDo.controller('registerController', function($scope, registerService, $location) {
			
		$scope.registerUser = function() {
				
				var httpRegisterUser = registerService.registerUser($scope.user,$scope.errorMessage);
				httpRegisterUser.then(function(response) {
					$location.path("/login");
				}, function(response) {
					$scope.errorMessage=response.data.responseMessage;
				});
			}
		});
/*toDo.controller('registerController', function($scope, registrationServices, $location) {
	$scope.user = {};

	 $scope.registerUser = function() {
		console.log("inside register function")
		registrationServices.registerUser2($scope.user);
	}
	 $scope.redirectToSignIn = function() {
			//$location.path('/loginPage');
		}
});*/

