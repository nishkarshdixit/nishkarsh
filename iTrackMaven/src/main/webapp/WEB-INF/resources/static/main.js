var mainApp = angular.module("mainApp", ['ngRoute']).run(function($rootScope,$http){
	
	$rootScope.data={};											//All the global variable are declared
	$rootScope.message={};
	$rootScope.status=true;
	$rootScope.requestId;
	$rootScope.messagehm='';
	$rootScope.messagerb='';
	
	 $http({													//Fetching the session from the spring controller
     	url:'getSession',
     	method:'POST',
     }).success(function(data,status,headers,config){
     	$rootScope.data=data;
     	console.log($rootScope.data);
     	if($rootScope.data!='')
     		{
     			$rootScope.status=false;
     		}
     })
	
});

//****************************************Rote Providing with Authentication*************************

mainApp.config(function($routeProvider) {										//Providing route to the application
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
		.when('/career', {
			templateUrl: 'resources/pages/career1.html',
		}).when('/services', {
			templateUrl: 'resources/pages/Services.html',
		})
		.when('/userView',{
			resolve:{
				"check":function($location,$rootScope){											//Authentication
					if($rootScope.data.designation=="HM")
						{
							$location.path("/hmView");
						}
					else if($rootScope.data.designation=="ADMIN")
						{
							$location.path("/adminView")
						}
					else if($rootScope.data.designation=="RM" || $rootScope.data.designation=="BFM")
						{
							$location.path("/Rm_Bfm")
						}
					else if($rootScope.data.designation=="RCM")
						{
							$location.path("/rcmView")
						}
					else if ($rootScope.data.designation=="REC")
						{
							$location.path("/recView");
						}
					else
						{
							$location.path("/home")
						}
				}
			}
		})
		
		.when('/rcmView', {
			templateUrl: 'resources/pages/RcmView.html',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({															//Resolve to check the authentication
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="RCM")
							{
								
							}
						else if($rootScope.data.designation!="RCM" && $rootScope.data.designation!="")
							{
								$location.path("/home");
															
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			},
			controller: 'rcmController'
		})
		
		.when('/adminView', {
			templateUrl: 'resources/pages/adminPage.html',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="ADMIN")
							{
								
							}
						else if($rootScope.data.designation!="ADMIN" && $rootScope.data.designation!="")
							{
								$location.path("/home");
								//prompt("You are not authenticated for this");
							}
						else
							{
								$location.path("/emp");
								//prompt("Please Login first");
							}
					     })
					
				}
			}
		})
		.when('/selected',{
			templateUrl: 'resources/pages/SelectedCandidatesView.html',
			controller: 'recruiterController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="REC"  || $rootScope.data.designation=="HM")
							{
								
							}
						else if($rootScope.data.designation!="REC" || $rootScope.data.designation=="HM"  && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/logout', {
			templateUrl:'resources/pages/Homepage1.html',
			controller: 'logoutController'
		})
		.when('/raiseRequest', {
			templateUrl: 'resources/pages/request_requisition.html',
			controller:'hiringManagerController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="HM")
							{
								
							}
						else if($rootScope.data.designation!="HM" && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/recView', {
			templateUrl: 'resources/pages/RecruiterView.html',
			controller:'recruiterController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="REC")
							{
								
							}
						else if($rootScope.data.designation!="REC" && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/shortlistCandidates',{
			templateUrl:'resources/pages/ShortlistCandidatesView.html',
			controller:'recruiterController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="REC")
							{
								
							}
						else if($rootScope.data.designation!="REC" && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/confirmStatus',{
			templateUrl:'resources/pages/CandidateStatusView.html',
			controller: 'updateStatusController',
			resolve:{
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="REC")
							{
								
							}
						else if($rootScope.data.designation!="REC" && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/Rm_Bfm', {
			templateUrl: 'resources/pages/RmBfmView.html',
			controller:'rmBfmController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="RM" || $rootScope.data.designation=="BFM")
							{
								
							}
						else if($rootScope.data.designation!="RM" || $rootScope.data.designation!="BFM" && $rootScope.data.designation=="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			}
		})
		.when('/career', {
			templateUrl: 'resources/pages/career1.html',
			resolve: {
				"check":function($location,$rootScope){
					if($rootScope.status)
						{
							
						}
					else
						{
							$location.path("/home");
							
						}
				}
			}
		})
		
		.when('/register', {
			templateUrl: 'resources/pages/NewReg.html',
			controller:'fileUpload',
			resolve: {
				"check":function($location,$rootScope){
					if($rootScope.status)
						{
							
						}
					else
						{
							$location.path("/home");
							
						}
				}
			}
				
		})
		.when('/emp', {
			templateUrl: 'resources/pages/emp_Login1.html',
			controller:'employeeController'	,
			resolve: {
				"check":function($location,$rootScope){
					if($rootScope.status)
						{
							
						}
					else
						{
							$location.path("/home");
							
						}
				}
			}
		})
		.when('/errorPage',{
			templateUrl: 'resources/pages/ErrorPage.html'
		})
		.when('/hmView', {
			templateUrl: 'resources/pages/HmView.html',
			controller:'hiringManagerController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="HM")
							{
								
							}
						else if($rootScope.data.designation!="HM" && $rootScope.data.designation!="")
							{
								$location.path("/home");
								
							}
						else
							{
								$location.path("/emp");
								
							}
					     })
					
				}
			},
			
			
		})
		.when('/requestStatus', {
			templateUrl: 'resources/pages/RequestStatus.html',
			controller:'hiringManagerController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="HM")
							{
								
							}
						else if($rootScope.data.designation!="HM" && $rootScope.data.designation!="")
							{
								$location.path("/home");
							
							}
						else
							{
								$location.path("/emp");
							
							}
					     })
					
				}
			},
			
			
		})
		.when('/addEmployee', {
			templateUrl: 'resources/pages/AddEmployee.html',
			controller:'employeeController',
			resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="ADMIN")
							{
								
							}
						else if($rootScope.data.designation!="ADMIN" && $rootScope.data.designation!="")
							{
								$location.path("/home");
							
							}
						else
							{
								$location.path("/emp");
							
							}
					     })
					
				}
			}
		})
		.when('/addProject', {
		templateUrl: 'resources/pages/AddProject.html',
		controller:'employeeController',
		resolve: {
			"check":function($location,$rootScope,$http){
				
				 $http({
				     	url:'getSession',
				     	method:'POST',
				     }).success(function(data,status,headers,config){
				     	$rootScope.data=data;
				     	console.log($rootScope.data);
				     	if($rootScope.data!="")
				     		{
				     			$rootScope.status=false;
				     		}
				     	if($rootScope.data.designation=="ADMIN")
						{
							
						}
					else if($rootScope.data.designation!="ADMIN" && $rootScope.data.designation!="")
						{
							$location.path("/home");
							
						}
					else
						{
							$location.path("/emp");
							
						}
				     })
				
			}
		}
		})
		.when('/viewEmployees', {
			templateUrl: 'resources/pages/EmployeeList.html',
	        controller:'employeeController'	,
	        resolve: {
				"check":function($location,$rootScope,$http){
					
					 $http({
					     	url:'getSession',
					     	method:'POST',
					     }).success(function(data,status,headers,config){
					     	$rootScope.data=data;
					     	console.log($rootScope.data);
					     	if($rootScope.data!="")
					     		{
					     			$rootScope.status=false;
					     		}
					     	if($rootScope.data.designation=="ADMIN")
							{
								
							}
						else if($rootScope.data.designation!="ADMIN" && $rootScope.data.designation!="")
							{
								$location.path("/home");
							
							}
						else
							{
								$location.path("/emp");
							
							}
					     })
					
				}
			}
		}).otherwise({
			templateUrl:'resources/pages/Homepage1.html'
		});
		
});
	
//********************************************Registration Controller************************************************

	
	mainApp.controller('fileUpload',function($scope,$http,$location,$rootScope){
		
			  $scope.regObject={};
			  $scope.message='';
              $scope.checkType=false;
              $scope.check=function(){																//File upload and authentication
                     var format=document.getElementById('file').files[0].type;
                     var size=document.getElementById('file').files[0].size;
                     console.log(format);
                     console.log(size);
                     if(format=="application/vnd.openxmlformats-officedocument.wordprocessingml.document" || format=="application/pdf" || format=="application/msword" && size<=1048576)
                           {
                                  $scope.checkType=true;
                                  $scope.uploadCheck=false;
                           }
                     else
                     {
                           $scope.uploadCheck=true;
                           alert("Please upload .doc/pdf only and less than 1 mb");
                     }
              }
              
              $scope.add = function(){
                     $scope.check();
                     if($scope.checkType)
                           {
                       var f = document.getElementById('file').files[0],
                           r = new FileReader();
                       r.onloadend = function(e){
                         var data = e.target.result;
                         $scope.regObject.cv=data;
                         
                       }
                       r.readAsDataURL(f);									//Converting file as binary stream to be sent(Encoded 64)
                     }
              
              else
                     {
                           return false;
                     }
              }
                                                              
              $scope.submit=function(regObject)
              {
                    
                    $http({														//Form submission
                    	url:'candidateRegistration',
                    	method:'POST',
                    	data:$scope.regObject,
                    	headers:{'Content-Type':'application/json'}
                    }).success(function(data,status,headers,config){
                    	console.log(data);
                    	if(data.candidateId==0)
                    		{
                    			$scope.message="Email id already existing";
                    			console.log($scope.message);
                    		}
                    	else if(data=="")
                    		{
                    			$location.path("/errorPage");
                    		}
                    	else
                    		{
                    			$location.path("/home");
                    			alert("Registered Successfully!!!")
                    		}
                    });
              }   
              });
		

//****************************************************************Employee Controller*****************************************************
	
	mainApp.controller('employeeController',function($scope,$http,$location,$rootScope){
		
		$scope.empObject = {};
		$scope.projObject={};
		$scope.loginObject={};
		$scope.message='';
		$scope.viewEmployees={};
		$scope.dateFlag=false;
		

		 $scope.submit=function($empObject)
         {
               // var object=$scope.regObject;						//Adding new employee
			 console.log($scope.empObject)
               $http({
               	url:'employeeDescriptor',
               	method:'POST',
               	data: $scope.empObject,
               	headers:{'Content-Type':'application/json'}
               }).success(function(data,status,headers,config){
               		$location.path("/adminView");
               		if(data="")
               			{
               			$scope.messageEmployee='Enter valid unique ID';
               			}
               		else
               			{
               		alert("Saved Successfully"); }
               }).error(function(error,status){
            	   console.log(status);
            	  $location.path("/errorPage");
               });
               
             
         }

		
		$scope.login=function(loginObject)
		{
			$http({														//Employee Login data to spring
				url:'employeeLogin',
				method:'POST',
				data: $scope.loginObject,
				headers:{'Content-Type':'application/json'}
			}).success(function(data,status,headers,config){
				$rootScope.data=data;
				console.log('Login data');
				console.log($rootScope.data);
				if($rootScope.data.employeeId=="0")
					{
						$scope.message="Please Enter valid Employee Id and password";
						console.log('Inside if');
					}
				else if($rootScope.data.designation=="HM")					//Resolving the view according to the designation of the employee
					{
						//$rootScope.data=data;
						//console.log($rootScope.data);
						$rootScope.status=false;
						$location.path("/hmView");
					}
				else if($rootScope.data.designation=="ADMIN")
					{
						//$rootScope.data=data;
						//console.log($rootScope.data);
						$rootScope.status=false;
						$location.path("/adminView");
					}
				else if($rootScope.data.designation=="RM" || $rootScope.data.designation=="BFM")
				{
					$rootScope.status=false;
					$location.path("/Rm_Bfm");
				}
				else if($rootScope.data.designation=="RCM")
					{
						$rootScope.status=false;
						$location.path("/rcmView");
					}
				else if($rootScope.data.designation=="REC")
					{
						$rootScope.status=false;
						$location.path("/recView");
					}
			});
		}
		
		$scope.checkDate=function(x)
		{
			var date=new Date();
       	 
       	 
       	 if(date-x<=0)
       		 {
       		 	$scope.dateFlag=true;
       		 }
       	 else{$scope.dateFlag=false;}
		}
		
	    $scope.submitProject=function($projObject)
        {															//Adding new cost center/project
	    	$scope.message='';
       	 console.log($scope.projObject);
       	 $http({
       		 url:'projectDescriptor',
       		 method:'POST',
       		 data: $scope.projObject,
       		 headers: {'Content-Type':'application/json'}
       	 }).success(function(data,status,headers,config){
       		alert("Successfully saved");
       		$location.path("/adminView");
       	 }).error(function(error,status){
       			$location.path("/errorPage");
       		})
       	
        }
		
	    
	    $http({
			url:'viewEmployees',
			method:'POST'
		}).success(function(data){
		$scope.viewEmployees = data;
		console.log(data);
		if(data==undefined)
			{
		console.log('Receiving list of Employees');
		console.log($scope.viewEmployees);}
		});
	    
		
		
		
	});
	
	
//**************************************************************hiringManager Controller*****************************************************
	
	mainApp.controller('hiringManagerController',function($scope,$http,$rootScope,$location){
		
		
		 $scope.requestObject={};
         $scope.checkType=false;
         $scope.ccList={};
         $scope.pendingRequest={};
         $scope.pendingRequest.hmId=$rootScope.data.employeeId;
         $scope.listRequests={};
         $scope.dateFlag=false;
         $scope.count=0;
         $scope.statusRequests={};
         $rootScope.messagehm='';
         
         $scope.checkDate=function(){
        	 var date=new Date();
        	 var inDate=$scope.requestObject.deadline;
        	 
        	 if(date-inDate>=0)
        		 {
        		 	$scope.dateFlag=true;
        		 }
        	 else{$scope.dateFlag=false;}
         }
        
         
         $http({											//Fetching cost center list to provide to hiring manager to raise request
        	 url:'getCostCenter',
        	 method:'POST'
         }).success(function(data,headers,status,config){
        	 console.log(data);
        	 $scope.ccList=data;
        	// console.log($scope.cclist);
         }).error(function(error,status){
        	 $location.path("/errorPage");
         });
         
         $http({
        	 url:'viewStatus',
        	 method:'POST',
        	 data: $scope.pendingRequest,
        	 headers:{'Content-Type':'application/json'}
         }).success(function(data){
        	 $scope.statusRequests=data;
        	 console.log($scope.statusRequests);
         }).error(function(error,status){
        	 $location.path("/errorPage");
         });
         
         $http({
        	 url:'viewRequests',
        	 method:'POST',
        	 data: $scope.pendingRequest,
        	 headers:{'Content-Type':'application/json'}
         }).success(function(data){
        	 //$location.path("/hmView");
        	 
        	 if(data==undefined)
        		 {
        		 	$scope.flag=false; console.log('Inside if');
        		 }
        	 else{
        		 $scope.listRequests=data;
        		 console.log('Request for the hm');
        		 console.log($scope.listRequests);
        		 $scope.count=$scope.listRequests.length;
        		 $scope.flag=true;
        	 }
         }).error(function(error,status){
        	 $location.path('/errorPage');
         });
         
         $scope.check=function(){																//File upload authentication
                var format=document.getElementById('file').files[0].type;
                var size=document.getElementById('file').files[0].size;
                console.log(format);
                console.log(size);
                if(format=="application/vnd.openxmlformats-officedocument.wordprocessingml.document" || format=="application/pdf" || format=="application/msword" && size<=1048576)
                      {
                             $scope.checkType=true;
                             $scope.uploadCheck=false;
                      }
                else
                {
                      $scope.uploadCheck=true;
                      alert("Please upload .doc/pdf only and less than 1 mb");
                }
         }
         
         $scope.add = function(){
                $scope.check();
                if($scope.checkType)
                      {
                  var f = document.getElementById('file').files[0],
                      r = new FileReader();
                  r.onloadend = function(e){
                    var data = e.target.result;
                    $scope.requestObject.jobDescription=data;
                    
                  }
                  r.readAsDataURL(f);
                }
         
         else
                {
                      return false;
                }
         }
                                                         
         $scope.submitRequest=function($requestObject)
         {
               // var object=$scope.regObject;											//Raising the request accordingly
        	 $requestObject.hmId=$rootScope.data.employeeId;
        	 console.log($requestObject);
               $http({
               	url:'raiseRequest',
               	method:'POST',
               	data:$scope.requestObject,
               	headers:{'Content-Type':'application/json'}
               }).success(function(data,status,headers,config){
               		if(data.costCenter==0)
               			{
               				$location.path("/hmview");
               				$rootScope.messagehm="Some Problem occured";
               			}
               		else if(data=="")
               			{
               				$location.path("/errorPage");
               			}
               		else
               			{
               				$location.path("/hmView");
               				$rootScope.messagehm="Request Raised Successfully";
               				alert("Request Raised Successfully");
               			}
               })
               
             
         }
         
         
         
        
	});

	
//***************************************************************Logout Controller****************************************************
	
	mainApp.controller('logoutController',function($scope,$rootScope,$http,$location){
		
		console.log("Logout COntroller called");
		$http({
			
			url:'employeeLogout',								//Sending request to spring controller to logout the session/invalidate the session
			method:'POST',
		}).success(function(data,status,headers,config){
			$rootScope.data={};
			$rootScope.status=true;
			$location.path("/home");
			console.log("Success");
		})
	});
	
	
	//*******************************************RM/BFM Controller*************************************************
	
	mainApp.controller('rmBfmController',function($scope,$rootScope,$http,$location,$route){
		
		console.log("Inside Rm/Bfm controller");
		
		$scope.statusObject={};
		$scope.basicFetch={};
		$scope.basicFetch.designation=$rootScope.data.designation;
		$scope.basicFetch.id=$rootScope.data.employeeId;
		$scope.requests={};
		$scope.spliceObject={};
		$scope.sendObject={};
		$scope.messageApprove='';
		
			$http({										//fetching the requests according to the employee id
				url:'getAllRequest',
				method:'POST',
				data: $scope.basicFetch,
				headers: {'Content-Type':'application/json'}
			}).success(function(data,config,headers,status){
				console.log($scope.basicFetch);
				$scope.requests=data;
				console.log($scope.requests);
			}).error(function(error,status){
				$location.path("/errorPage");
			})
		
				$scope.submitAccept=function()
				{
					if($scope.spliceObject.data==undefined)
						{
							
						}
					else{
					$scope.sendObject.requestId=$scope.spliceObject.data.requestId;
					$scope.sendObject.status="A";								//post on accepting the request
					$scope.sendObject.designation=$rootScope.data.designation;
					$scope.sendObject.id=$rootScope.data.employeeId;
					
					console.log("Accept the request and data send");
					console.log($scope.statusObject);
					$http({
						url:'saveRequest',
						method:'POST',
						data: $scope.sendObject,
						headers: {'Content-Type':'application/json'}
					}).success(function(data){
						$scope.requests.splice($scope.requests.indexOf($scope.spliceObject.data),1);
						$route.reload();
						alert('Approved Successfully');
					}).error(function(error,status){
						$location.path("/errorPage");
					})
					}
				}
				
				$scope.submitReject=function($statusObject)
				{
					if($scope.spliceObject.data==undefined)
						{}																		//post rejecting the request
					else{
					$scope.statusObject.requestId=$scope.spliceObject.data.requestId;
					$scope.statusObject.status="R";
					$scope.statusObject.designation=$rootScope.data.designation;
					$scope.statusObject.id=$rootScope.data.employeeId;
					console.log("Reject send data");
					console.log($scope.statusObject);
					$http({
						url:'saveRequest',
						method:'POST',
						data: $scope.statusObject,
						headers: {'Content-Type':'application/json'}
					}).success(function(data){
						$scope.requests.splice($scope.requests.indexOf($scope.spliceObject.data),1);
						alert("rejected Successfully");
					}).error(function(error,status){
						alert("Some problem occured");
					}).then(function(){
						//$route.reload();
					})
					}
				}
				
				$scope.viewJD=function(x)
				{
					window.open(x);
				}
				
				
		
	});
	
	//***********************************************************RCM Controller********************************************************
	
	mainApp.controller('rcmController',function($scope,$http,$rootScope,$location,$route){
		
		$scope.basicFetch={};
		$scope.basicFetch.id=$rootScope.data.employeeId;
		$scope.requests={};
		$scope.assignObject={};
		$scope.recruiters={};
		$scope.spliceObject={};
		
		$http({												//Fetching All the requests
			url:'getRcmRequest',
			method:'POST'
		}).success(function(data){
			$scope.requests=data;
			console.log('Receiving RCM requests');
			console.log($scope.requests);
		}).error(function(error,status){
			$location.path("/errorPage");
		});
		
		$http({												//Fetching all the recruiters from database
			url:'getRecruiter',
			method:'POST'
		}).success(function(data){
			$scope.recruiters=data;
			console.log('Receiving recruiter list');
			console.log($scope.requests);
		}).error(function(error,status){
			$location.path("/errorPage");
		});
		
		
		$scope.assignRec=function()							//Assigning the recruiter particular request
		{
			if($scope.spliceObject.data==undefined)
				{}
			else
				{
			$scope.assignObject.irequestId=$scope.spliceObject.data.requestId;
			console.log('Sending data to assign recruiter');
			console.log($scope.assignObject);
			$http({
				url:'saveRecruiter',
				method:'POST',
				data: $scope.assignObject,
				headers: {'Content-Type':'application/json'}
			}).success(function(data){
				$scope.requests.splice($scope.requests.indexOf($scope.spliceObject.data),1);
				alert("Assigned successfully");
			}).error(function(error,status,data){
				console.log(data);
				$location.path("/errorPage");
			});
				}
		}
		

		$scope.jdDownload=function(x)
		{
			window.open(x);
		}
		
		
		
	});
	
	
	
//*********************************************************RECRUITER CONTROLLER**********************************************
	
	mainApp.controller('recruiterController',function($scope,$http,$rootScope,$location,$route){
		
		
		$scope.requests={};
		$scope.basicFetch={};
		$scope.basicFetch.id=$rootScope.data.employeeId;
		$scope.candidateList={};
		$scope.arr=[];
		$scope.candidatesList=[];
		$scope.selectedRequest={};
		$scope.requestAssignObject={};
		$scope.selectedCandidates={};
		$scope.countSelected=0;
		$scope.countAvailable=0;
		
		//$scope.candidatesList.requestId={};

		$http({												//Fetching All the requests
			url:'getRecruiterRequests',
			method:'POST',
			data: $scope.basicFetch,
			headers: {'Content-Type':'application/json'}
		}).success(function(data){
			$scope.requests=data;
			console.log('Receiving Recruiter requests');
			console.log($scope.requests);
		}).error(function(status,error){
			$location.path("/errorPage");
		});
		
		
		
		$http({												//Fetching all the avaialable candidates
			url:'getCandidates',
			method:'POST'
		}).success(function(data){
			$scope.candidateList=data;
			console.log('All Candidate listing');
			console.log($scope.candidateList);
			$scope.countAvailable=$scope.candidateList.length;
		}).error(function(error,status){
			$location.path("/errorPage");
		});
		
		
		$scope.viewCV=function(x)
		{
			window.open(x);
		}
		
		$scope.getJD=function(x)
		{
			window.open(x);
		}
		
		$scope.add=function(index)
		{
			var counter=$scope.arr.indexOf(index);
			if(counter==-1)
				{
					$scope.arr.push(index);
				}
			else if(counter!=-1)
				{
					$scope.arr.splice(counter,1);
				}
			
		
		}
		
		
		$scope.addItem=function()								//Adding candidates id using checkboxes 
		{
		
			if($rootScope.requestId==undefined)
			{
				//alert("Please select any request Id");
				$location.path("/userView");
			}
			else{
				if($scope.arr.length>0)
					{
			console.log('current index in the array before adding the objects');
			for (var p=0; p<$scope.arr.length;p++) {
		        for (var q=p+1;q<$scope.arr.length;q++) {
		            var tmp=0;
		            if ($scope.arr[p] > $scope.arr[q]) {
		                tmp = $scope.arr[p];
		                $scope.arr[p] = $scope.arr[q];
		                $scope.arr[q] = tmp;
		            }
		        }
		    }
			
			console.log($scope.arr);
			for(var i=0;i<$scope.arr.length;i++)
				{										//Making list of the candidate and requests to be shortlisted
		
					$scope.candidatesList.push({candidateId:$scope.candidateList[$scope.arr[i]].candidateId,requestId:$rootScope.requestId});
				}
			$scope.arr=[];
			console.log('Candidate list shortlisted with checkbox');
			
			console.log($scope.candidatesList);
			$http({
				url:'shortlistCandidates',
				method:'POST',
				data: $scope.candidatesList,
				headers: {'Content-Type':'application/json'}
			}).success(function(data){
				$location.path("/userView");
				
			}).error(function(status,error){
				$location.path("/errorPage");
			});
					}
				else
					{
						alert("Please Select some candidates");
					}
				
					}
		}
		
		$scope.submitRequest=function($requestAssignObject)						//Passing the request id to shortlist the candidates accordingly
		{
			
			$rootScope.requestId=$scope.requestAssignObject.requestId;
			console.log('submitting request');
			console.log($rootScope.requestId);
			
				
		}
		
		
		$scope.submitShortlistRequest=function($requestAssignObject)		//Passing the request id to update status of candidates
		{
			
			$rootScope.requestId=$scope.requestAssignObject.requestId;
			console.log('submitting request');
			console.log($rootScope.requestId);
				
		}
		
		
		$http({																	//Fetching the selected Candidates
			url:'selectedCandidates',
			method:'POST'
		}).success(function(data){
			$scope.selectedCandidates=data;
			console.log('Receiving list of selected candidates');
			console.log($scope.selectedCandidates);
			$scope.countSelected=$scope.selectedCandidates.length;
		}).error(function(error,status){
			$location.path("/errorPage");
		});
		
		$scope.resolveRequest=function()
		{
			if($scope.requestAssignObject.requestId==undefined)
				{}
			else{
			$http({									//Resolving Request in Request Status Table
			url:'resolveRequest',
			method:'POST',
			data: $scope.requestAssignObject,
			headers: {'Content-Type':'application/json'}
		}).success(function(data){
			$route.reload();
		}).error(function(error,status){
			$location.path("/errorPage");
		});
			}
		}
	});
	
	//***************************************************Update Candidate Status Controller*****************************************
	
	mainApp.controller('updateStatusController',function($scope,$http,$rootScope,$location,$route){
		$scope.spliceObject={};
		if($rootScope.requestId==undefined)
		{
			alert("Please select any request Id");
			$location.path("/userView");
		}
		else
			{
		$scope.candidateList={};
		$scope.basicFetch=[];
		$scope.basicFetch.push({requestId:$rootScope.requestId});
		console.log('Inside the update status of candidate list controller and sending the request id');
		console.log($scope.basicFetch);
		$scope.updateObject={};
		$scope.accept={};
		
		$http({												//Fetching the shortlisted candidates for particular request id
			url:'shortlistedCandidates',
			method:'POST',
			data: $scope.basicFetch,
			headers: {'Content-Type':'application/json'}
		}).success(function(data){
			$scope.candidateList=data;
			console.log(data);
		}).error(function(status,error){
			$location.path("/errorPage");
		});
		
		$scope.updateStatus=function($updateObject)
		{	if($scope.spliceObject.data==undefined)							//Update status of candidate
			{
				alert("Select any candidate first");
			}
		else{
			$scope.accept.candidateId=$scope.spliceObject.data.candidateId;
			$scope.accept.status=$scope.updateObject.status;
			console.log($scope.accept);
			console.log($scope.spliceObject.data)
			console.log('Sending object to update the candidate');
			$http({
				url:'updateCandidates',
				method:'POST',
				data: $scope.accept,
				headers: {'Content-Type':'application/json'}
			}).success(function(data){
			
				
				$route.reload();
					
			}).error(function(status,error){
				alert("Some problem occured");
			});
		}
			
		}
		
		$scope.rejectStatus=function($updateObject)
		{																	//Updating rejection of candidate
			if($scope.spliceObject.data==undefined)
				{alert("Select any candidate first");}
			else
				{
			console.log('Sending Object to update the rejection of candidate with remark');
			console.log($scope.updateObject);
			$scope.updateObject.candidateId=$scope.spliceObject.data.candidateId;
			$http({
				url:'updateCandidates',
				method:'POST',
				data: $scope.updateObject,
				headers: {'Content-Type':'application/json'}
			}).success(function(data){
				$scope.candidateList.splice($scope.candidateList.indexOf($scope.spliceObject.data),1);
				$scope.updateObject={};
				//$route.reload();
			}).error(function(status,error){
				alert("Some problem occured");
			});
				}
		}
			}
	});
	

	

