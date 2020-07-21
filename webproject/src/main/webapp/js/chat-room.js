var roomID = window.location.search.substr(1);
window.onload = function() {
    roomID = window.location.search.substr(1);
    getRoomDetails(roomID);
    document.getElementById('chat-box').innerHTML = '<iframe width="100%" height="100%" frameborder="0" src="chat.html?' + roomID + '"></iframe>';
}

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
