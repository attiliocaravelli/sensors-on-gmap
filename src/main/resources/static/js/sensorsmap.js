var app = angular.module('mapApp', []);

app.controller('mapCtrl', function ($scope, $http) {

    var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(55, 8),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);

    $scope.markers = [];
    $scope.cities = [];
   
    var infoWindow = new google.maps.InfoWindow();

    $http.get('http://localhost:8080/sensor/json').
    success(function (data) {

            $scope.sensors = data;
            $scope.sensors.forEach(function(sensor) {
                createMarker(sensor);
            });

        
        });

    var createMarker = function(sensor) {
        var marker = new google.maps.Marker({
            map: $scope.map,
            position: new google.maps.LatLng(sensor.location.latitude, sensor.location.longitude),
            title: sensor.description

        });
        marker.content = '<div class="infoWindowContent">' + sensor.location.city + '</div>';
       
        marker.content = marker.content + '<div class="infoWindowContent">' + "Temperatures read:" + '</div>';
        
        sensor.readings.forEach(function(reading) {
        	marker.content = marker.content + '<div class="infoWindowContent">' + "Event date: " +reading.timestamp + " Temp:" + reading.temperature +"°" + reading.unit + '</div>';    
        });
        
        marker.content = marker.content + '<div class="infoWindowContent">' + "Alerts:" + '</div>';
        
        sensor.alerts.forEach(function(alert) {
        	marker.content = marker.content + '<div class="infoWindowContent">' + "Event date: " + alert.timestamp + " Descr:" + alert.description + '</div>';    
        });
   
        google.maps.event.addListener(marker, 'click', function() {
            infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
            infoWindow.open($scope.map, marker);
        });
        
        $scope.markers.push(marker);
    };

});



