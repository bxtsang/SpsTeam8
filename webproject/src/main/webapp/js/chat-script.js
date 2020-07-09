var database;
var name;

$(document).ready(function () {
    //TO-DO: Use Google to get user info
    //var profile = auth2.currentUser.get().getBasicProfile();
    //var name = profile.getGivenName();
    database = firebase.database(); 
    //Using random numbers to create different names for different users.
    //Different window = different user for now
    //Will be replaced with Google ID.
    name = "user " + Math.floor(Math.random() * Math.floor(5));

    database.ref('/messages/').on('child_added', function(snapshot) {    
        var html = "<li class='message' id='message-" + snapshot.key + "'>";
        html += snapshot.val().sender + ": " + snapshot.val().message;
        html += "</li>";
 
        document.getElementById("messages").innerHTML += html;
    });
});

function sendMessage() {
    var message = document.getElementById("message").value;
 
    database.ref('messages/').push().set({
        sender: name,
        message: message
    }, function(error) {
        if (error) {
            console.log("Write failed");
        }
    });
 
    return false;
}
