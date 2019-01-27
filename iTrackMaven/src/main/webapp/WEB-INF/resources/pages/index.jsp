<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <!-- Required meta tags always come first -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta http-equiv="x-ua-compatible" content="ie=edge">
	    <title>iTrack implementation</title>
	    <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/static/style.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	</head>
	
		<body ng-app="mainApp" class="body">
	
			    
			  
			    <nav class="navbar navbar-default navbar-fixed-top" data-spy="affix" data-offset-top="197">
			      <a href="#/home"><img src="resources/images/logo.png" class="navbar-brand"></a><a href="#/home"><h1 class="navbar-header"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IBS Software</h1></a>
			      <div class="col-lg-6 col-md-offset-2">
			
			      <ul class="nav navbar-nav">
										<li class="active item">
			                <a href="#/home"><span class="glyphicon glyphicon-home"></span></a>
			              </li>
			              <li>
			                <a href="#/services" class="item">Services</a>
			              </li>
			              <li>
			                <a href="#/contact_us" class="item">Contact Us</a>
			              </li>
			              <li>
			                <a href="#/about_us" class="item">About Us</a>
			              </li>
			              <li>
			                <a href="#/career" class="item" ng-show="status">Career</a>
			              </li>
			              <li ng-show="status">
			                <a href="#/emp" class="item"><span class="glyphicon glyphicon-log-in"></span> Login</a>
			              </li>
			              <li ng-show="!status">
			                <a href="#/logout" class="item"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
			              </li>
			              <li ng-show="!status">
			                <a href="#/userView" class="item">Profile</a>
			              </li>		
					</ul>
				</div>
			    </nav>
			
				<div class="col-lg-12 content" ng-view>
				
				</div>
				

			<div class="fluid-container">
   				 <div class="footer text-center col-md-12"><p><br><br><br><br><br><br>&copy;2017 IBS Software</p></div>
  			</div>
			
			<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
			<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.min.js"></script>
			
			<script src="resources/static/main.js"></script>
			
			<script type="text/javascript">
			$(document).ready(function() {
  			$(".nav a").on("click", function(){
    		$(".nav").find(".active").removeClass("active");
     		$(this).parent().addClass("active");
  			});
			});
			</script>
		</body>	
		
</html>		