var adminApp = angular.module("adminApp", []);

adminApp.controller("adminController", function($scope, $http) {
	
	$scope.categories = ["Comedy", "Thriller", "Action", "Adventure", "Sci/Fi", "Family", "Drama", "Suspence"];
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}
	
	$scope.addMovieForm = function(){
		$scope.showAddMovieForm = true;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.showUpdateMovieForm = false;
		$scope.showUpdateTheaterForm = false;
		$scope.showUpdateShowtoTheaterForm = false;
		$scope.messageText="";
	};
	
	$scope.addMovie = function() {
		//remove current results
		$scope.messageText="";
		//$scope.showAddMovieForm = false;		
		//search
		var date = formatDate($scope.addDate);
		$http.post('/flixtiqsSite/rest/movies',		
			{movie: {name:$scope.addMovieName,releasedDate:date,length: $scope.addLength,rating: $scope.addRating, category:$scope.addCat}})
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
		$scope.showUpdateMovieForm = false;
		$scope.showUpdateTheaterForm = false;
		$scope.showAddTheaterForm = true;
		$scope.showAddMovietoTheaterForm = false;
		$scope.showUpdateShowtoTheaterForm = false;
		$scope.messageText="";
	};
	
	$scope.addTheater = function() {
		//remove current results
		$scope.messageText="";
		//$scope.showAddTheaterForm = false;
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
		$scope.showUpdateMovieForm = false;
		$scope.showUpdateTheaterForm = false;
		$scope.showUpdateShowtoTheaterForm = false;
		$scope.showAddMovietoTheaterForm = true;
		$scope.messageText="";
		$http.get('/flixtiqsSite/rest/movies',{})
				.success(function(data, status) {
					$scope.httpStatus = status;
					$scope.httpData = data;
					$scope.errorStatus=false;
					$scope.movies = [];
					for(var i=0; i< data.length; i++){
						$scope.movies.push({name:data[i].movie.name, id:data[i].movie.movieId});
					}					
				})
				.error(function(data, status) {
					$scope.httpStatus = status;				
					$scope.httpData = data;
					$scope.errorStatus=true;
					$scope.messageText=data.error.code+ " "+ data.error.message;
				});		
		$http.get('/flixtiqsSite/rest/theaters',{})
		.success(function(data, status) {
			$scope.httpStatus = status;
			$scope.httpData = data;
			$scope.errorStatus=false;
			$scope.theaters = [];
			for(var i=0; i< data.length; i++){
				$scope.theaters.push({name:data[i].theater.name, id:data[i].theater.theaterId});
			}					
		})
		.error(function(data, status) {
			$scope.httpStatus = status;				
			$scope.httpData = data;
			$scope.errorStatus=true;
			$scope.messageText=data.error.code+ " "+ data.error.message;
		});		
	};
	
	$scope.addMovietoTheater = function(){
		$scope.messageText="";		
		//$scope.showAddMovietoTheaterForm = false;
		//search
		$http.post('/flixtiqsSite/rest/movieshow',		
			{movieShow:{price:$scope.addPrice, showTime:$scope.addShowTime, movieId:$scope.selectedMovie.id, theaterId:$scope.selectedTheater.id, seatsAvailable:$scope.addSeats}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;				
				$scope.messageText="Created new movie show with ID "+data.movieShow.showId;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});	
	};
	
	$scope.updateMovieForm = function(){
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.showUpdateTheaterForm = false;
		$scope.showUpdateShowtoTheaterForm = false;
		$scope.showUpdateMovieForm = true;
		$scope.messageText="";
		$scope.upMovies = [];
		$http.get('/flixtiqsSite/rest/movies',{})
		.success(function(data, status) {
			$scope.httpStatus = status;
			$scope.httpData = data;
			$scope.errorStatus=false;			
			for(var i=0; i< data.length; i++){
				$scope.upMovies.push({name:data[i].movie.name, id:data[i].movie.movieId, date:new Date(data[i].movie.releasedDate), rating:data[i].movie.rating, length:data[i].movie.length, category:data[i].movie.category});
			}					
		})
		.error(function(data, status) {
			$scope.httpStatus = status;				
			$scope.httpData = data;
			$scope.errorStatus=true;
			$scope.messageText=data.error.code+ " "+ data.error.message;
		});		
	};
	
	$scope.updateMovie = function(isDeleted) {
		//remove current results
		$scope.messageText="";	
		var date = formatDate($scope.selectedUpMovie.date);
		//search
		$http.put('/flixtiqsSite/rest/movies',		
			{movie: {movieId:$scope.selectedUpMovie.id, name:$scope.selectedUpMovie.name, releasedDate:date, length: $scope.selectedUpMovie.length,rating: $scope.selectedUpMovie.rating, category:$scope.selectedUpMovie.category, isdeleted:isDeleted}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;					
				if(isDeleted)
					$scope.messageText="Deleted movie with ID "+$scope.selectedUpMovie.id;
				else
					$scope.messageText="updated movie with ID "+$scope.selectedUpMovie.id;
				$scope.selectedUpMovie = null;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.updateTheaterForm = function(){
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.showUpdateMovieForm = false;
		$scope.showUpdateShowtoTheaterForm = false;
		$scope.showUpdateTheaterForm = true;
		$scope.messageText="";
		$http.get('/flixtiqsSite/rest/theaters',{})
		.success(function(data, status) {
			$scope.httpStatus = status;
			$scope.httpData = data;
			$scope.errorStatus=false;
			$scope.theaters = [];
			for(var i=0; i< data.length; i++){
				$scope.theaters.push({name:data[i].theater.name, id:data[i].theater.theaterId, city:data[i].theater.city, state:data[i].theater.state, zip:data[i].theater.zipcode});
			}					
		})
		.error(function(data, status) {
			$scope.httpStatus = status;				
			$scope.httpData = data;
			$scope.errorStatus=true;
			$scope.messageText=data.error.code+ " "+ data.error.message;
		});		
	};
	
	$scope.updateTheater = function(isDeleted) {
		//remove current results
		$scope.messageText="";	
		//$scope.showUpdateTheaterForm = false;
		//search
		$http.put('/flixtiqsSite/rest/theaters',		
			{theater: {theaterId:$scope.selectedUpTheater.id, name:$scope.selectedUpTheater.name, city:$scope.selectedUpTheater.city, state: $scope.selectedUpTheater.state,zipcode: $scope.selectedUpTheater.zip, isdeleted:isDeleted}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;	
				if(isDeleted)
					$scope.messageText="Deleted theater with ID "+data.theater.theaterId;
				else
					$scope.messageText="updated theater with ID "+data.theater.theaterId;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
	
	$scope.updateMovieShowForm = function(){
		$scope.showAddMovieForm = false;
		$scope.showAddTheaterForm = false;
		$scope.showUpdateMovieForm = false;
		$scope.showUpdateTheaterForm = false;
		$scope.showAddMovietoTheaterForm = false;
		$scope.showUpdateShowtoTheaterForm = true;
		$scope.shows = []
		$scope.messageText="";
		$http.get('/flixtiqsSite/rest/movies',{})
				.success(function(data, status) {
					$scope.httpStatus = status;
					$scope.httpData = data;
					$scope.errorStatus=false;
					$scope.movies = [];
					for(var i=0; i< data.length; i++){
						$scope.movies.push({name:data[i].movie.name, id:data[i].movie.movieId});
					}					
				})
				.error(function(data, status) {
					$scope.httpStatus = status;				
					$scope.httpData = data;
					$scope.errorStatus=true;
					$scope.messageText=data.error.code+ " "+ data.error.message;
				});		
		$http.get('/flixtiqsSite/rest/theaters',{})
		.success(function(data, status) {
			$scope.httpStatus = status;
			$scope.httpData = data;
			$scope.errorStatus=false;
			$scope.theaters = [];
			for(var i=0; i< data.length; i++){
				$scope.theaters.push({name:data[i].theater.name, id:data[i].theater.theaterId});
			}					
		})
		.error(function(data, status) {
			$scope.httpStatus = status;				
			$scope.httpData = data;
			$scope.errorStatus=true;
			$scope.messageText=data.error.code+ " "+ data.error.message;
		});		
	};
	
	$scope.srchShowInTheater = function(){
		$scope.messageText="";
		$http.get('/flixtiqsSite/rest/movieshow',{
			params: {theater:$scope.srchTheater.id, movie:$scope.srchMovie.id}})
		.success(function(data, status) {
			$scope.httpStatus = status;
			$scope.httpData = data;
			$scope.errorStatus=false;
			$scope.shows = data;
			$scope.messageText="Found" + data.length;
		})
		.error(function(data, status) {
			$scope.httpStatus = status;				
			$scope.httpData = data;
			$scope.errorStatus=true;
			$scope.messageText=data.error.code+ " "+ data.error.message;
		});		
	};
	
	$scope.editShow = function(movieShow, isDeleted){
		$http.put('/flixtiqsSite/rest/movieshow',		
				{movieShow:{showId:movieShow.showId, price:movieShow.price, showTime:movieShow.showTime, movieId:$scope.srchMovie.id, theaterId:$scope.srchTheater.id, seatsAvailable:movieShow.seatsAvailable, isdeleted: isDeleted}})
				.success(function(data, status) {
					$scope.httpStatus = status;
					$scope.httpData = data;
					$scope.errorStatus=false;	
					if(isDeleted)
						$scope.messageText="Deleted show with ID "+data.movieShow.showId;
					else
						$scope.messageText="updated show with ID "+data.movieShow.showId;
					$scope.srchShowInTheater();
				})
				.error(function(data, status) {
					$scope.httpStatus = status;				
					$scope.httpData = data;
					$scope.errorStatus=true;
					$scope.messageText=data.error.code+ " "+ data.error.message;
				});		
	};
});