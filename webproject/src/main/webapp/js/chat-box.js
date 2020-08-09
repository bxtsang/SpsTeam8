var roomID = window.location.search.substr(1);
var username = null;

window.onload = function() {
    fetchBlobstoreUrl();
}

$(document).ready(function() {
    database = firebase.database();
    name = "user " + Math.floor(Math.random() * Math.floor(5));
    roomID = window.location.search.substring(1);
    firebase.database().ref('messages/' + roomID).on('child_added', function(snapshot) {
        var newMessage = snapshot.val();
        var html = "<li class='message' id='message-" + snapshot.key + "'>";
        html += "<span class='chat-user'>" + newMessage.user + "</span> <br />";
        if (newMessage.type == "text") {
            html += "<span class='chat-message'>" + newMessage.message + "</span> <br />";
        } else {
            html += "<a href=\"" + newMessage.message + "\"><img src=\"" + newMessage.message + "\" /></a> <br />";
        }

        let date = new Date(newMessage.timestamp);
        let minutes = minutes_with_leading_zeroes(date);
        let hours = hours_with_leading_zeroes(date);
        let day = date.getDate();
        let month = getMonth(date);

        html += `<span class='chat-time'>${day} ${month} ${hours}:${minutes}</span>`;
        html += "</li>";
        let messagesContainer = document.getElementById("messages");
        messagesContainer.innerHTML += html;
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
    });
});

async function sendMessage() {
    let usernamePromise = null;
    
    if (username == null) {
	    usernamePromise = getUsername();
    } else {
	    usernamePromise = Promise.resolve(username);
    }

    usernamePromise.then(username => {
        let messageBox = document.getElementById("message");
        var message = messageBox.value;
        messageBox.value = "";

        await $.ajax({
            type: 'POST',
            url: "/text-message",
            data: {'textmessage': message},
        });;
    });
    return false;
}

function getUsername() {
    return fetch('/username').then(response => response.text()).then(response => {
        username = response;
        return response;
    });
}

function fetchBlobstoreUrl() {
    fetch('/blobstore')
        .then((response) => {
            return response.text();
        })
        .then((imageUploadUrl) => {
            const messageForm = document.getElementById('image-form');
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

function getMonth(date) {
    let months = ["January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    return months[date.getMonth()];
}

function showUploadingSpinner() {    	
    //Disabling a text input field with regular JavaScript.
    document.getElementById("file-input").disabled = true;
    document.getElementsByClassName('upload-button')[0].innerHTML = '<i class="fa fa-spinner fa-spin" style="font-size:24px"></i>';
}
