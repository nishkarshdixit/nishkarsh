var mainApp = angular.module("mainApp", ['ngRoute']).directive('fileInput',['$parse',function($parse){
	return{
		restrict:'A',
		link:function(scope,elm,attrs){
			elm.bind('change',function(){
				$parse(attrs.fileInput).assign(scope,elm[0].files)
				scope.$apply()
			})
		}
	}
}]);

mainApp.config(function($routeProvider) {
	$routeProvider
		.when('/home', {
			templateUrl: 'resources/pages/Homepage1.html',
		})
		.when('/', {
			templateUrl: 'resources/pages/Homepage1.html',
		})
		.when('/about_us', {
			templateUrl: 'resources/pages/about_us1.html',
		})
		.when('/contact_us', {
			templateUrl: 'resources/pages/contact_us1.html',
		})
		.when('/cand_login', {
			templateUrl: 'resources/pages/cand_Login1.html',
		})
		.when('/career', {
			templateUrl: 'resources/pages/career1.html',
		})
		.when('/register', {
			templateUrl: 'resources/pages/NewReg.html',
			controller:'fileUpload'
		})
		.when('/emp', {
			templateUrl: 'resources/pages/emp_Login1.html',
		});
});
	
//Registration Controller

	mainApp.controller('fileUpload',function($scope,$http){
		
	$scope.filesChanged=function(elm){
		$scope.files=elm.files
		$scope.$apply();
	}
	
	$scope.add=function(){
		var fd=new FormData()
		angular.forEach($scope.files,function(file){
			fd.append('file',file)
			console.log(fd)
		})
		$http({
			url:'candidateRegistration',
			method: 'POST',
			data: fd,
			transformRequest:angular.identity,
			headers: {'Content-Type':undefined}
		}).success(function(d){
			console.log(d)
		})
	}
		
	});
		

//Directive
	
	

