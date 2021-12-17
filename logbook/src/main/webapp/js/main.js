"use strict";

var map,markers;

function checkLocation() {
    var country = document.getElementById("countries");
    var city = document.getElementById("city");
    var address = document.getElementById("address");
    document.getElementById("error").innerHTML="";

    if(country.value!="" && city.value!="" && address.value!=""){
        var request = new XMLHttpRequest();
        var coordinates = "country="+country.value+"&city="+city.value+"&street="+address.value+"&format=json";
        var url = "https://nominatim.openstreetmap.org/search";

        request.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var response = JSON.parse(this.responseText);
                if(response==''){
                    document.getElementById("error").innerHTML="The location "+country.value+", "
                    +city.value+", "+address.value+" does not exist.";
                    country.style.border = "medium solid red";
                    city.style.border = "medium solid red";
                    address.style.border = "medium solid red";
                }
                else{
                  document.getElementById("error").innerHTML="The location  "+country.value+", "
                  +city.value+", "+address.value+" exists.";
                    document.getElementById("showMap").style.display="inline";
                    country.style.border = "medium solid green";
                    city.style.border = "medium solid green";
                    address.style.border = "medium solid green";
                }
            }
        };
        request.open("GET", url+"?"+coordinates, true);
        request.send();
    }
}

window.onload = function() {
    map= new OpenLayers.Map("map");
    map.addLayer(new OpenLayers.Layer.OSM());
    markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);
    document.getElementById("map").style.height="0px";
    if (navigator.geolocation) {
          document.getElementById("autocomplete").style.display="inline";
    }
}

function mark(lon,lat){
    document.getElementById("map").style.height="auto";

    var lonLat = new OpenLayers.LonLat(lon,lat)
          .transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
          );

    markers.addMarker(new OpenLayers.Marker(lonLat));
    var zoom=15;
    map.setCenter (lonLat, zoom);
}

//lon,lat,country,town,addr
function showOnMap(lon1,lat1){
    var country = document.getElementById("country");
    var city = document.getElementById("city");
    var address = document.getElementById("address");

    var request = new XMLHttpRequest();
    var coordinates = "country="+country.value+"&city="+city.value+"&street="+address.value+"&format=json";
    var url = "https://nominatim.openstreetmap.org/search";
    document.getElementById("map").style.display="box";
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            if(response!=''){
                var lat=response[0].lat;
                var lon=response[0].lon;
                document.getElementById("map").style.height="auto";
                markers.clearMarkers();
                mark(lon,lat);
            }
        }
    };
    request.open("GET", url+"?"+coordinates, true);
    request.send();
}

function fillFields(lo,la){
	var lat=la;
    var lon=lo;
    //document.getElementById("skr").value=lat;
    //document.getElementById('lat').value=lat;
	//document.getElementById('lon').value=lon;
   // var city = document.getElementById("city");
    //var address = document.getElementById("address");
    document.getElementById("error").innerHTML="";
    var request = new XMLHttpRequest();
    var coordinates = "lat="+lat+"&lon="+lon+"&format=json";
    var url = "https://nominatim.openstreetmap.org/reverse";

    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            if(response!=''){
            	//document.getElementById("skr").value=response.address.country;
            	document.getElementById("country").value=response.address.country;
            	document.getElementById("city").value=response.address.city;
		if(typeof response.address.neighbourhood !== 'undefined')
			    document.getElementById("address").value=response.address.neighbourhood;
		else
		     address.value=response.address.road;
                document.getElementById("showMap").style.display="inline";
            }
        }
    };
    request.open("GET", url+"?"+coordinates, true);
    request.send();
}

function showCamera(askisi){
    document.getElementById("photo").style.display="inline";
    document.getElementById("no").checked=false;
    faceRec.init(askisi);
}

function hideCamera(){
    document.getElementById("photo").style.display="none";
    document.getElementById("yes").checked=false;
    document.getElementById("no").checked=true;
    try{
        _streamCopy.stop(); // if this method doesn't exist, the catch will be executed.
    }catch(e){
       _streamCopy.getVideoTracks()[0].stop(); // then stop the first video track of the stream
    }
}

var _streamCopy;

var state2 = {
    progress: 0,
};
/*  face recognition that is based on faceplusplus service */
var faceRec = (function () {

  // Object that holds anything related with the facetPlusPlus REST API Service
  var faceAPI = {
    apiKey: 'l2jNgKbk1HXSR4vMzNygHXx2g8c_xT9c',
    apiSecret: '2T6XdZt4EYw-I7OhmZ6g1wtECl81e_Ip',
    app: 'hy359',
    // Detect
    // https://console.faceplusplus.com/documents/5679127
    detect: 'https://api-us.faceplusplus.com/facepp/v3/detect',  // POST
    // Set User ID
    // https://console.faceplusplus.com/documents/6329500
    setuserId: 'https://api-us.faceplusplus.com/facepp/v3/face/setuserid', // POST
    // Get User ID
    // https://console.faceplusplus.com/documents/6329496
    getDetail: 'https://api-us.faceplusplus.com/facepp/v3/face/getdetail', // POST
    // addFace
    // https://console.faceplusplus.com/documents/6329371
    addFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/addface', // POST
    // Search
    // https://console.faceplusplus.com/documents/5681455
    search: 'https://api-us.faceplusplus.com/facepp/v3/search', // POST
    // Create set of faces
    // https://console.faceplusplus.com/documents/6329329
    create: 'https://api-us.faceplusplus.com/facepp/v3/faceset/create', // POST
    // update
    // https://console.faceplusplus.com/documents/6329383
    update: 'https://api-us.faceplusplus.com/facepp/v3/faceset/update', // POST
    // removeface
    // https://console.faceplusplus.com/documents/6329376
    removeFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/removeface', // POST
  };

  // Object that holds anything related with the state of our app
  // Currently it only holds if the snap button has been pressed
  var state = {
      photoSnapped: false,
  };

  // function that returns a binary representation of the canvas
  function getImageAsBlobFromCanvas(canvas) {
      // function that converts the dataURL to a binary blob object
      function dataURLtoBlob(dataUrl) {
          // Decode the dataURL
          var binary = atob(dataUrl.split(',')[1]);

          // Create 8-bit unsigned array
          var array = [];
          for (var i = 0; i < binary.length; i++) {
              array.push(binary.charCodeAt(i));
          }

          // Return our Blob object
          return new Blob([new Uint8Array(array)], {
              type: 'image/jpg',
          });
      }

      var fullQuality = canvas.toDataURL('image/jpeg', 1.0);
      return dataURLtoBlob(fullQuality);
  }

  // function that returns a base64 representation of the canvas
  function getImageAsBase64FromCanvas(canvas) {
    // return only the base64 image not the header as reported in issue #2
    return canvas.toDataURL('image/jpeg', 1.0).split(',')[1];

  }

  // Function called when we upload an image for search
  function uploadImage4search() {
      if (state.photoSnapped) {
          var canvas = document.getElementById('canvas');
          var image = getImageAsBlobFromCanvas(canvas);

          var data = new FormData();
          data.append('api_key', faceAPI.apiKey);
          data.append('api_secret', faceAPI.apiSecret);
          data.append('image_file', image);
          data.append('outer_id', faceAPI.app);

          ajaxRequest('POST', faceAPI.search, data);
          hideCamera();
      } else {
          alert('No image has been taken!');
      }
  }

  // Function called when we upload an image for detection of face
  function uploadImage4detect() {
      if (state.photoSnapped) {
          var canvas = document.getElementById('canvas');
          var image = getImageAsBlobFromCanvas(canvas);

          var data = new FormData();
          data.append('api_key', faceAPI.apiKey);
          data.append('api_secret', faceAPI.apiSecret);
          data.append('image_file', image);

          ajaxRequest('POST', faceAPI.detect, data);
          hideCamera();
      } else {
          alert('No image has been taken!');
      }
  }

  // Function for initializing things (event handlers, etc...)
  function init(askisi) {
      // Grab elements, create settings, etc.
      var canvas = document.getElementById('canvas');
      var context = canvas.getContext('2d');
      var video = document.getElementById('video');
      var mediaConfig = {
          video: true,
      };
      var errBack = function (e) {
          console.log('An error has occurred!', e);
      };

      // Put video listeners into place
      if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
          navigator.mediaDevices.getUserMedia(mediaConfig).then(function (stream) {
              video.srcObject = stream;
              video.onloadedmetadata = function (e) {
                  video.play();
              };
              _streamCopy=stream;
          });
      }

      // Trigger photo take
      document.getElementById('snap').addEventListener('click', function () {
          context.drawImage(video, 0, 0, 640, 480);
          state.photoSnapped = true; // photo has been taken
      });

      // Trigger when upload button is pressed
      if(askisi==1){ //sthn 1 kanoume apload gia na antistoixistei se face token
          document.getElementById('upload').addEventListener('click', uploadImage4detect);
      }
      else if(askisi==2){  //sth 2 kanoume search to username pou antistoixei sto proswpo
          document.getElementById('upload').addEventListener('click', uploadImage4search);
          state2.progress=3;
      }

  }

  // ============================================

  // !!!!!!!!!!! ================ TODO  ADD YOUR CODE HERE  ====================
  // From here on there is code that should not be given....

  // You have to implement the ajaxRequest function!!!!
  function ajaxRequest(method, url, data){
      var ajaxreq = new XMLHttpRequest();
      ajaxreq.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
              var response = JSON.parse(this.responseText);
              manageResponse(response);
          }
      };
      ajaxreq.open(method, url, true);
      ajaxreq.send(data);
  }

  function manageResponse(jsonResponse){
      if(state2.progress==0){
          var id=document.getElementById("username").value;
          if(id=='' || jsonResponse.faces.length<=0) return;
          var token=jsonResponse.faces[0].face_token;

          var data = new FormData();
          data.append('api_key', faceAPI.apiKey);
          data.append('api_secret', faceAPI.apiSecret);
          data.append('face_token', token);
          data.append('user_id', id);
          state2.progress++;
          ajaxRequest('POST', faceAPI.setuserId, data);
      }
      else if(state2.progress==1){
          var token=jsonResponse.face_token;
          var data = new FormData();
          data.append('api_key', faceAPI.apiKey);
          data.append('api_secret', faceAPI.apiSecret);
          data.append('face_tokens', token);
          data.append('outer_id', faceAPI.app);
          state2.progress++;
          ajaxRequest('POST', faceAPI.addFace, data);
      }
      else if(state2.progress==2){
          var status=jsonResponse.failure_detail;
          var message=document.getElementById("status");
          if(status.length<=0){
              message.style.color="green";
              message.innerHTML="Successfully associated username with photo!";
          }
          else{
            message.style.color="red";
            message.innerHTML="Could not associate username with photo!";
          }
          state2.progress=0;
      }
      else{
          var username=document.getElementById("username");
          var results=jsonResponse.results;
          if(results!=undefined){
              var id=results[0].user_id;
              var conf=results[0].confidence;
              for (var i = 0; i < results.length; i++) {
                  if(results[i].confidence>conf){
                      id=results[i].user_id;
                      conf=results[i].confidence;
                  }
              }
              username.value=id;
          }
          else{
              alert("Could not associate username with photo!");
          }
      }
  }

  // !!!!!!!!!!! =========== END OF TODO  ===============================

 // Public API of function for facet recognition
 // You might need to add here other methods based on your implementation


  return {
      init: init,
  };

})();
