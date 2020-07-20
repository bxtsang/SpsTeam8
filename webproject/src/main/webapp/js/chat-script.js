var roomID;

$(document).ready(function () {
    database = firebase.database(); 
    name = "user " + Math.floor(Math.random() * Math.floor(5));
    roomID = window.location.search.substring(1);

    firebase.database().ref('messages/' + roomID).on('child_added', function(snapshot) {    
        var snap = snapshot.val();
        var html = "<li class='message' id='message-" + snapshot.key + "'>";
        html += snap.time + " " + snap.user + ": ";
        if (snap.type == "text") {
            html += snap.message;
        } else {
            html += "<a href=\"" + snap.message + "\"><img src=\"" + snap.message + "\" /></a>";
        }
        html += "</li>";
 
        document.getElementById("messages").innerHTML += html;
    });
});

function fetchBlobstoreUrl() {
  fetch('/blobstore')
      .then((response) => {
        return response.text();
      })
      .then((imageUploadUrl) => {
        const messageForm = document.querySelector('#image-form');
        messageForm.action = imageUploadUrl;
        messageForm.classList.remove('hidden');
      });
}

function minutes_with_leading_zeroes(date) { 
  return (date.getMinutes() < 10 ? '0' : '') + date.getMinutes();
}

function hours_with_leading_zeroes(date) { 
  return (date.getHours() < 10 ? '0' : '') + date.getHours();
}
