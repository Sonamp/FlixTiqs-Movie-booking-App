var flixtiqsApp = angular.module('flixtiqsApp', []);

flixtiqsApp.controller('movieSearchController', function($scope, $http) {
	
	$scope.movies = [];
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}

	$scope.searchMovie = function(nowPlaying, comingSoon) {
		//remove current results
		$scope.movies = [];
		$scope.theaters = [];
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
		$scope.messageText="";
		$scope.showPlayingMovieinTheater = false;
		$scope.showTheaterPlayingMovie = false;
		$scope.showTheater = false;
		$scope.playingMovies = [];
		$scope.comingMovies = [];
			
		$scope.comingSoon = comingSoon;
		$scope.nowPlaying =  nowPlaying;
		//search
		var date = "";
		if($scope.searchDate)
			date = formatDate($scope.searchDate);
		$http.get('/flixtiqsSite/rest/movies',		
			{params: {
				name:$scope.searchMovieName, 
				date:date}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Found "+data.length;
				var currentDate = new Date();
				for(var i=0; i< data.length; i++) {
					var releaseDate = new Date(Date.parse(data[i].movie.releasedDate));
					if(releaseDate > currentDate)
						$scope.comingMovies.push(data[i]);
					else
						$scope.playingMovies.push(data[i]);
				}
				//$scope.movies = data;
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
		$scope.showTheaterPlayingMovie = false;
		$scope.showPlayingMovieinTheater = false;
		$scope.comingSoon = false;
		$scope.nowPlaying =  false;
		$scope.showTheater = true;
		//search
		$http.get('/flixtiqsSite/rest/theaters',		
			{params: {
				name:$scope.searchName,
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
	
	$scope.searchTheaterforMovie = function(id, name) {
		//remove current results
		$scope.movieTheaters = [];
		$scope.theaterMovies = [];
		$scope.messageText="";
		$scope.showTheaterPlayingMovie = true;
		$scope.movieName = name;
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
	
	$scope.searchMoviesforTheater = function(id, name) {
		//remove current results
		$scope.theaterMovies = [];
		$scope.movieTheaters = [];
		$scope.messageText="";
		$scope.showPlayingMovieinTheater = true;
		$scope.theaterName = name;
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
	
	
});

flixtiqsApp.controller("showBookController", function($scope, $routeParams, $http) {
	var showId = $routeParams.showId;
	$scope.messageText = "";
	$http.get('/flixtiqsSite/rest/movieshow/'+showId)
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="";
				$scope.show = data.movieShow;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});	
	
	$scope.purchageTicket = function(){
		$scope.messageText = "";
		var seatsAvail = $scope.show.seatsAvailable - $scope.ticket;
		$http.put('/flixtiqsSite/rest/movieshow',		
				{movieShow:{showId:$scope.show.showId, price:$scope.show.price, showTime:$scope.show.showTime, movieId:$scope.show.movieId, theaterId:$scope.show.theaterId, seatsAvailable:seatsAvail, isdeleted: $scope.show.isdeleted}})
				.success(function(data, status) {
					$scope.httpStatus = status;
					$scope.httpData = data;
					$scope.errorStatus=false;	
					$scope.messageText="You have booked "+ $scope.ticket + " ticket/s for Movie " + data.movieShow.movie.name + " at show time " + data.movieShow.showTime;
					$scope.show = data.movieShow;
				})
				.error(function(data, status) {
					$scope.httpStatus = status;				
					$scope.httpData = data;
					$scope.errorStatus=true;
					$scope.messageText=data.error.code+ " "+ data.error.message;
				});		
		};
});