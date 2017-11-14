(function(angular){
	angular.module('myApp',[])
    .controller('myCtrl',function($http,$scope){
    	$scope.employee = [];
    	$http.get('/bigdata/users')
    	     .then(function(response){
    	    	  $scope.employee = response.data.slice(0,10);
    	    	  angular.forEach($scope.employee,function(employee){
    	    		  employee['editable'] = false;
    	    	  })
    	 })
    	 $scope.editRow = function(emp){
    	   	emp.editable = true;
    	}
    	$scope.updateRow = function(emp){
    		emp.editable = false;
    		$http.put('bigdata/update',emp)
    		     .then(function(){
    		    	 console.log("updated...");
    		     })
    	};
    	$scope.deleteRow = function(emp){
    		$http.get('bigdata/delete/'+emp.id).then(function(){
    			console.log("Data Deleted....");
    		})
    	}
    	$scope.addRow = function(){
    		$scope.employee.push({"id":null,"firstName":null,"lastName":null,"email":null,"gender":null,"editable":true});
    		console.log($scope.employee);
    	}
    	
    	$scope.insert = function(){
    		$http.post('bigdata/insert',$scope.employee[$scope.employee.length-1]).then(function(){
    			consoe.log(inserted);
    		})
    	}
    })
})(window.angular)