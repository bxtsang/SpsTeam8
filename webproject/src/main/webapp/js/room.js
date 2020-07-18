var roomId = window.location.search.substr(1);
window.onload = function() {
    roomID = window.location.search.substr(1);
    getRoom();
    document.getElementById('chat').innerHTML = '<iframe width="100%" height="100%" frameborder="0" src="chat.html?' + roomID + '"></iframe>';
}
async function getRoom() {
    var response = await fetch(`https://summer20-sps-47.firebaseio.com/rooms/${roomId}.json`);
    var room = await response.json();
    var roomKeys = Object.keys(room);
    roomKeys.forEach(key => {
        document.querySelector("#room-details").innerHTML += `
      <tr>
        <td>${key}</td>
        <td>${room[key]}</td>
      </tr>`;
    });
}
