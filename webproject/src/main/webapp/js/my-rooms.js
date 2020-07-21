window.onload = function() {
  getMyRooms();
}

async function joinRoom(roomId) {
  var formData = new FormData();
  formData.append("roomId", roomId);

  var response = await fetch('/join', {
    method: 'POST',
    headers: {
      'Accept': 'application/json'
    },
    body: formData
  });

  if (response.status == 200) {
    window.alert("Joined room!")
  }

  window.location.reload();
}

function toChat(roomId) {
  window.location.href = `/roomChat.html?${roomId}`;
}

async function getMyRooms() {
  var response = await fetch("/myRooms");
  var responseJson = await response.json();
  var entries = Object.entries(responseJson)

  let childHtmlString = "";

  entries.forEach(room => {
    var roomData = room[1];

    childHtmlString += `<div class="shadow-sm p-3 mb-5 bg-white rounded listing-card">
      <div class="row form-group">
        <div class="col-md-6 mb-3 mb-md-0">
          <span class="card-heading title"><a href="./roomChat.html?${room[0]}>${roomData.title}</a></span>
          <br />
          <span class="card-field">Postal Code: </span>
          <span class="card-value postal-code-value">
            ${roomData.deliveryLocation}
          </span>
          <br />
          <span class="card-description">
            ${roomData.description}
          </span>
        </div>
        <div class="col-md-6 text-right">
          <span class="card-field">Category: </span>
          <span class="card-value category-value">${roomData.category}</span>
          <br />
          <span class="card-field">Delivery fee: </span>
          <span class="card-value">$${roomData.deliveryFee}</span>
          <br />
          <span class="card-field">$ current orders value: </span>
          <span class="card-value">$${roomData.ordersValue}</span>
          <br />
        </div>
      </div>
    </div>`;
  })

  document.getElementById("listings-card-container").innerHTML = childHtmlString;
}
