// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
let map, markers;
/** Adds GMaps script tag to the page. */
function loadGoogleMapsScript() {
    const script = document.createElement("script")
    script.type = "text/javascript";
    let scriptAttributeAsyc = document.createAttribute("async"); // Create a "class" attribute
    let scriptAttributeDefer = document.createAttribute("defer");
    script.setAttributeNode(scriptAttributeAsyc);
    script.setAttributeNode(scriptAttributeDefer);
    if (script.readyState) { //IE
        script.onreadystatechange = function() {
            if (script.readyState == "loaded" ||
                script.readyState == "complete") {
                script.onreadystatechange = null;
            }
        };
    } else { //Others
        script.onload = function() {};
    }
    script.src = config.GMAPS_URL + config.GMAPS_API_KEY + '&callback=initMap';
    document.getElementsByTagName("head")[0].appendChild(script);
}

function initMap() {
    const singapore = { name: 'Singapore', lat: 1.3521, lng: 103.8198 }
    map = new google.maps.Map(document.getElementById('map-container'), {
        center: { lat: singapore.lat, lng: singapore.lng },
        zoom: 12
    });

    const infoWindow = new google.maps.InfoWindow;
    
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            let pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            const marker = new google.maps.Marker({
                position: new google.maps.LatLng(pos.lat, pos.lng),
                map: map
            });
            infoWindow.setPosition(pos);
            infoWindow.setContent('Your location');
            infoWindow.open(map, marker);

            google.maps.event.addListener(marker, 'click', function() {
                infoWindow.open(map, marker);
            });

            map.setCenter(pos, marker);
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });

        initialiseRoomMarkers();
        displayRoomMarkers();
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function initialiseRoomMarkers() {
    markers = [];

    fetch('https://summer20-sps-47.firebaseio.com/rooms.json')
    .then(response => response.json())
    .then(data => Object.entries(data))
    .then(entries => {
        for (entry of entries) {
            room = entry[1];
            if (room.deliveryLocation !== undefined) {
                initialiseSingleRoomMarker(room);
            }
        }
    });
}

function initialiseSingleRoomMarker(room) {
    const fetchUrl = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + room.deliveryLocation
            + '&key=' + getApiKey();

    fetch(fetchUrl)
    .then(response => response.json())
    .then(response => {
        if (response.status === 'OK') {
            const coordinates = response.results[0].geometry.location;
            appendMarker(room, coordinates.lat, coordinates.lng);
        }
    });
}

function appendMarker(room, lat, lng) {
    const marker = new google.maps.Marker({
        position: new google.maps.LatLng(lat, lng),
        map: map,
        title: room.title
    });

    windowContent = `<div class="text-center">
            <p> ${room.title} </p>
            <p> ${room.deliveryLocation} </p>
            </div>`;

    const infoWindow = new google.maps.InfoWindow({
        content: windowContent
    });

    google.maps.event.addListener(marker, 'click', function() {
        infoWindow.open(map, marker);
    });

    markers.push(marker);
}

function displayRoomMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}
