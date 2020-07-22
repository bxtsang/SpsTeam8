async function getRoomDetails(roomID) {
    var response = await fetch(`https://summer20-sps-47.firebaseio.com/rooms/${roomID}.json`);
    var roomDetails = await response.json();
    let roomDetailsContainer = document.getElementById("room-details-container");
    let roomDetailsString = `
    <div>
    <span class="room-details-shopName">${roomDetails.title}</span>
    <br />
    <br />
    
    <span class="room-details-header">Delivering To:</span>
    <br />
    <span class="room-details-value">${roomDetails.deliveryLocation}</span>
    <br />
    <br />
    
    <span class="room-details-header">Delivery fee: </span>
    <br />
    <span class="room-details-value">$${roomDetails.deliveryFee}</span>
    <br/>
    
    <span class="room-details-header">$ left to minimum order: </span>
    <br />
    <span class="room-details-value">$${
        roomDetails.minPrice - roomDetails.total
    }</span>
    <br />
    <br />
    </div>
    
    <div class="room-details-exit">
    <form action="/closeRoom" method="post" >
    <button type="submit" class="btn btn-danger" />Delete & Exit Room</button>
    <input type="hidden" name="roomId" value="${roomID}">
    </form>
    </div>`;

    roomDetailsContainer.innerHTML = roomDetailsString;
}
