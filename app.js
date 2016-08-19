/**
 * 
 */

var app = angular.module('myApp', []);

app.controller('CartForm',function CartForm($scope,$http) {
    $scope.invoice = {
        formdata: [{}]
    };

    $scope.getDataFromServer = function(item) {
//    	alert(item.desc);
    	var dt = {
    		    desc: item.desc
    	};
    	alert(JSON.stringify(dt));
        $http({
                method : 'POST',
                url : 'Dbconnect',
                data: JSON.stringify(dt)
        }).success(function(data, status, headers, config) {
//        	alert("suc");
//        	alert(data.qty);
        	item.qty = data.qty;
        	item.price = data.price;
        }).error(function(data, status, headers, config) {
        	alert(status);
        });

};

$scope.submitForm = function() {
	var items="";
//	angular.forEach($scope.invoice.formdata, function(item) {
////        total += item.qty * item.price;
//        items = items + "{\"desc\":\"" +item.desc+"\", \"qty\":\"" +item.qty+"\", \"price\":\"" + item.price+"\"},"; 
//    });
//	items = items 
//	var finalData= "{\"cart\":\""+items+"\",\"cusname\":\""+$scope.custName+"\"}";
	
	$scope.formData = {};
	alert(JSON.stringify(($scope.invoice)));
$http({
  method  : 'POST',
  url     : 'saveForm',
  data    : $scope.invoice
 }).success(function(data, status, headers, config) {
 	alert("suc");
 }).error(function(data, status, headers, config) {
 	alert(status);
 });
};

    $scope.addItem = function() {
        $scope.invoice.formdata.push({
            qty: 1,
            desc: '',
            price: 0
        });
    },

    $scope.removeItem = function(index) {
        $scope.invoice.formdata.splice(index, 1);
    },

    $scope.total = function() {
        var total = 0;
        angular.forEach($scope.invoice.formdata, function(item) {
//        	alert(item.qty);
            total += item.qty * item.price;
        })

        return total;
    }
});


