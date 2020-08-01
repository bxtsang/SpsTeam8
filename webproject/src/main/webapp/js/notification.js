$(document).ready(function() {
    firebase.database().ref('rooms').orderByChild('timestamp').startAt(Date.now()).on('child_added', function(snapshot) {
        var snap = snapshot.val();
        notify(snap.title);
    });
});

function notify(roomName) {
    if (!("Notification" in window)) {
        alert("This browser does not support desktop notification");
    } else if (Notification.permission === "granted") {
        notification = new Notification("A new room " + roomName + " has opened!");
    } else if (Notification.permission !== "denied") {
        Notification.requestPermission().then(function (permission) {
            if (permission === "granted") {
                notification = new Notification("A new room " + roomName + " has opened!");
            }
        });
    }
}
