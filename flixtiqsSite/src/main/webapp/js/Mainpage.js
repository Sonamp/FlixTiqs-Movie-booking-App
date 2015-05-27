var flixtiqsApp = angular.module('flixtiqsApp', []);

flixtiqsApp.controller('movieSearchController', function($scope, $http) {
	
	$scope.movies = [];

	$scope.searchMovie = function() {
		//remove current results
		$scope.movies = [];
		$scope.theaters = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
		$scope.messageText="";
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		//search
		$http.get('/flixtiqsSite/rest/movies',		
			{params: {
				name:$scope.searchMovieName, 
				date:$scope.searchDate}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Found "+data.length;
				$scope.movies = data;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.searchTheater = function() {
		//remove current results
		$scope.theaters = [];
		$scope.movies = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
		$scope.messageText="";
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		//search
		$http.get('/flixtiqsSite/rest/theaters',		
			{params: {
				city:$scope.searchCity, 
				state:$scope.searchState,
				zip:$scope.searchZip}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Found "+data.length;
				$scope.theaters = data;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.searchTheaterforMovie = function(id) {
		//remove current results
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.messageText="";
		
		//search
		$http.get('/flixtiqsSite/rest/theaters',		
			{params: {
				movie:id}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Found "+data.length;
				$scope.movieTheaters = data;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.searchMoviesforTheater = function(id) {
		//remove current results
		$scope.theaterMovies = [];
		$scope.movieTheaters = [];
		$scope.messageText="";
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		//search
		$http.get('/flixtiqsSite/rest/movieshow',		
			{params: {
				theater:id}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				var moviesData = [];				
				for(var j=0; j<data.length; j++){
					var addflag = false;
					var show = data[j].movieShow;
					for(var i=0; i<moviesData.length; i++){
						if(show.movie.movieId == moviesData[i].movie.movieId){
							moviesData[i].show.push({showId : show.showId, showTime: show.showTime, price: show.price})
							addflag = true;
						}	
					}	
					if(!addflag){
						moviesData.push({movie : show.movie, show : [{ showId :  show.showId, showTime: show.showTime, price: show.price}]});
					}
				}	
				$scope.messageText="Found "+moviesData.length;
				$scope.theaterMovies = moviesData;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.addMovieForm = function(){
		$scope.showAddMovieForm = true;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.theaters = [];
		$scope.movies = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
	};
	
	$scope.addMovie = function() {
		//remove current results
		$scope.messageText="";
		$scope.showAddMovieForm = false;		
		//search
		$http.post('/flixtiqsSite/rest/movies',		
			{movie: {name:$scope.addMovieName,releasedDate:$scope.addDate,length: $scope.addLength,rating: $scope.addRating}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;				
				$scope.messageText="Created new movie with ID "+data.movie.movieId;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.addTheaterForm = function(){
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = true;
		$scope.showAddMovietoTheaterForm = false;
		$scope.theaters = [];
		$scope.movies = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
	};
	
	$scope.addTheater = function() {
		//remove current results
		$scope.messageText="";
		$scope.showAddTheaterForm = false;
		//search
		$http.post('/flixtiqsSite/rest/theaters',		
			{theater: {name:$scope.addTheaterName,city:$scope.addCity,state: $scope.addState,zipcode: $scope.addZip}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;				
				$scope.messageText="Created new theater with ID "+data.theater.theaterId;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.addMovietoTheaterForm = function(){
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = true;
		$scope.theaters = [];
		$scope.movies = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
	};
});