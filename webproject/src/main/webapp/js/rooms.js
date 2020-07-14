window.onload = function() {
  getRooms();
}

async function getRooms() {
  var response = await fetch("https://summer20-sps-47.firebaseio.com/rooms.json");
  var data = await response.json();
  var entries = Object.entries(data);

  entries.forEach(room => {
    var roomData = room[1];

    document.querySelector("#rooms-table").innerHTML += `
    <tr>
      <td>${roomData.title}</td>
      <td><a href="./room.html?${room[0]}">join room</a></td>
    </tr>`;
  });
}
