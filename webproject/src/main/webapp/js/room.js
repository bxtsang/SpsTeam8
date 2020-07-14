window.onload = function() {
  getRoom();
}

var roomId = window.location.search.substr(1)

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
