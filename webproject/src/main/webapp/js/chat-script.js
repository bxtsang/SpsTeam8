$(document).ready(function () {
    database = firebase.database(); 
    name = "user " + Math.floor(Math.random() * Math.floor(5));
    var param = window.location.search.substring(1);
    var array = param.split('=');
    roomID = array[1];

    firebase.database().ref('messages/' + roomID + '/').on('child_added', function(snapshot) {    
        var snap = snapshot.val();
        var html = "<li class='message' id='message-" + snapshot.key + "'>";
        html += snap.time + " ";
        html += snap.user + ": " + snap.message;
        html += "</li>";
 
        document.getElementById("messages").innerHTML += html;
    });
});

function sendMessage() {
    var message = document.getElementById("message").value;
    var date = new Date();
    var time = date.getHours() + ":" + date.getMinutes();

    firebase.database().ref('messages/' + roomID + '/').push().set({
        user: name,
        message: message,
        time: time
    }, function(error) {
        if (error) {
            console.log("Write failed");
        }
    });
 
    return false;
}
