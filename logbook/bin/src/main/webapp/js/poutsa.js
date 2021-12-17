var logged_user;

function readFile() {
    if (this.files && this.files[0]) {
        var FR = new FileReader();

        FR.addEventListener("load", function(e) {
            document.getElementById("base64").value = e.target.result;
        });
        FR.readAsDataURL(this.files[0]);
    }

}

function add_listener_input() {
    document.getElementById("input").addEventListener("change", readFile);
}

function showusers() {
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open('GET', 'showUsers');
    //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function popup(id) {
    var data = 'id=' + id;
    
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open("POST", "postdetails", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function userprofile(name) {
    var data = 'username=' + name;
    
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open("POST", "userProfile", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function delete_post() {
	var data = 'username=' + logged_user;
    
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open("POST", "deletePost", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function post() {
	//alert('ekei');
	var data = 'username=' + logged_user + '&desc=' + document.getElementById("desc").value + "&lat=" +
    document.getElementById("lat").value + '&lon=' + document.getElementById("lon").value + '&image_url=' + 
    document.getElementById("image_url").value + '&base64=' + document.getElementById("base64").value;
    
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	if (xhr.responseText=="Empty fields") {
        		document.getElementById("desc").style.borderColor = "red";
            	document.getElementById("lat").style.borderColor = "red";
            	document.getElementById("lon").style.color = "red";
                document.getElementById("ekei").innerHTML = xhr.responseText;
        	}
        	else {
        	   document.getElementById("content").innerHTML = xhr.responseText;
            }
        }
    };
    xhr.open("POST", "uploadPost", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

/*function getLonLat() {
    navigator.geolocation.getCurrentPosition(fillLonLat);
}

function fillLonLat(position){
    var lat=position.coords.latitude;
    var lon=position.coords.longitude;
    document.getElementById("lon").value=lon;
    document.getElementById("lat").value=lat;
}*/


function getLonLat() {
	if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
    	  document.getElementById('lat').value=position.coords.latitude;
    	  document.getElementById('lon').value=position.coords.longitude;
       });
	}else {
	   alert("Sorry, your browser does not support HTML5 geolocation.");
	}
}

function createpost() {
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open('GET', 'createPost');
    //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function editprofile() {
    var data = 'username=' + logged_user;
    
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open("POST", "editprofile", true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function home() {
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	//alert(xhr.responseText);
        	document.getElementById("content").innerHTML = xhr.responseText;
            }
    };
    xhr.open('GET', 'home');
    //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function signup() {
	 var sign_in_request = new XMLHttpRequest();
	    sign_in_request.onreadystatechange = function() {
	        if (sign_in_request.readyState === 4 && sign_in_request.status === 200) {
	            document.getElementById("content").innerHTML = sign_in_request.responseText;
	        }
	    };
	    sign_in_request.open("GET", "servlet", true);
	    //check_p.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	    sign_in_request.send();
}


function signin() {
	//alert('ekei');
    //document.getElementById("skata").innerHTML = "";
    var username = password = document.getElementById("username").value;
    var password = document.getElementById("password").value;

   // var data = new URLSearchParams();
   // data.append("username", username);
   // data.append("password", password);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText=="Wrong username/password") {
            	document.getElementById("username").style.borderColor = "red";
            	document.getElementById("password").style.borderColor = "red";
            	document.getElementById("message").style.color = "red";
                document.getElementById("message").innerHTML = xhr.responseText;
               }
            else {
            	logged_user = username;
            	document.getElementById("content").innerHTML = xhr.responseText;
            	document.getElementById('username').style.borderColor = "#ccc";
            	document.getElementById("password").style.borderColor = "#ccc";
            }
        }
        if (xhr.readyState === 4 && xhr.status !== 200) {
            alert('An error occurred...');
        }
    };
    xhr.open('GET', 'checksignin?username=' + username + '&password=' + password);
    //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function signout() {
	var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("content").innerHTML = xhr.responseText;
        }
    };
    xhr.open('GET', 'logout');
    //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}