var roomID = 'room' + Math.floor(Math.random() * Math.floor(50));

$(document).ready(function() {
    document.querySelector('#chatbutton').addEventListener('click', function(){
        openChatRoom(roomID);
    });
    console.log(roomID);
});

function openChatRoom(roomID) {
    var opened = window.open("");
    opened.document.write('<iframe width="100%" height="100%" frameborder="0"'
    + 'src="../chat.html?param=' 
    + roomID + '"></iframe>');
}
