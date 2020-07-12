var userID;
var database;

$(document).ready(function() {
    userID = 'user123';
    database = firebase.database(); 

    document.querySelector('#newRoomButton').addEventListener('click', function(){
        makeNewChatRoom(userID);
    });

    database.ref('users/' + userID + '/rooms/').on('child_added', function(snapshot) {    
        var snap = snapshot.val();
        var html = "<li class='room' id='room-" + snapshot.key + "'>";
        html += snap.ID;
        html += "</li>";
 
        document.getElementById("roomsList").innerHTML += html;
    });
});

async function makeNewChatRoom(userID) {
    var numberOfRooms;

    numberOfRooms = await(database.ref('users/' + userID + '/').once('value').then(function(snapshot) {    
        return ((snapshot.val() && snapshot.val().chatNumber) || 0);
    }));

    var roomID = 'room' + numberOfRooms;

    numberOfRooms += 1;
    console.log(numberOfRooms);

    database.ref('users/' + userID + '/').set({
        chatNumber: numberOfRooms
    });

    database.ref('users/' + userID + '/rooms/').push({
        ID: roomID
    });
}
