var app = angular.module('app', ['ngRoute','flixtiqsApp', 'adminApp']);

app.config(['$routeProvider',function($routeProvider) {
	$routeProvider
		.when('/',
			{
			templateUrl:'browse.html',
			controller: 'movieSearchController'			
			})
		.when('/shows/:showId',
		{
			templateUrl:'bookticket.html',
			controller: 'showBookController'			
		})	
		.when('/admin',
		{
			templateUrl:'admin.html',
			controller: 'adminController'			
		})	
		.otherwise({
				redirectTo: '/'
			});
}]);