var database = firebase.database();

$(document).ready(function () {
    writeUserData('1', 'Ava Lovelace', 'ava@lovelace.com');
    writeUserData('2', 'Brett Yang', 'brett@yang.com');
    readUserData('1');
});

function writeUserData(userId, name, email) {
    //TO-DO: Retrieve userID using 
    //googleUser.getAuthResponse().id_token;
    firebase.database().ref('users/' + userId).set({
        username: name,
        email: email
    }, function(error) {
        if (error) {
            console.log("Write failed");
        } else {
            console.log("Write succeeded");
        }
    });
}

function readUserData(userId) {
    firebase.database().ref('/users/' + userId).once('value').then(function(snapshot) {
        console.log(snapshot.val().username);
    });
}
