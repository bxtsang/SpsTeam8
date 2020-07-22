var roomID = window.location.search.substr(1);
window.onload = function() {
    console.log('MEAP MEAP')
    roomID = window.location.search.substr(1);
    getRoomDetails(roomID);
    getAllOrders();
    getHeaderLinks();
}

function getAllOrders() {
    console.log('HEREEEE')
    let allOrderItems = [{
            productName: "Chicken burger",
            quantity: 2,
            perUnitPrice: 10,
        },
        {
            productName: "Fish burger",
            quantity: 1,
            perUnitPrice: 15,
        },
    ];
    let myRoomDetails = {
        shopName: "McDonald's",
        postalCode: "123456",
        description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        category: "Food",
        deliveryFee: 20,
        minimumOrder: 100,
        noOfPeopleInRoom: 3,
    };
    let allOrdersContainer = document.getElementById("all-orders-container");
    console.log('AllOrdersContainer: ', allOrdersContainer)
    if (allOrderItems.length <= 0) {
        allOrdersContainer.innerHTML = "Add an item now!";
        return;
    }
    let myOrderString = `
        <table class="table">
        <thead class="thead-light">
        <tr>
        <th scope="col">#</th>
        <th scope="col">Product</th>
        <th scope="col">Quantity</th>
        <th scope="col">$ / Quantity</th>
        <th scope="col">Total</th>
        <th scope="col"></th>
        </tr>
        </thead>
        <tbody>`;
    let total = 0;
    for (let i = 0; i < allOrderItems.length; i++) {
        productTotal = allOrderItems[i].quantity * allOrderItems[i].perUnitPrice;
        total += productTotal;
        myOrderString += `
    <tr>
    <form action="/myOrder" method="delete">
    <th scope="row">${i + 1}</th>
    <td>${allOrderItems[i].productName}</td>
    <td>${allOrderItems[i].quantity}</td>
    <td>${allOrderItems[i].perUnitPrice}</td>
    <td>${productTotal}</td>
    </form>
    </tr>`;
    }
    myDeliveryFee = (
        myRoomDetails.deliveryFee
    ).toFixed(2);
    total += parseFloat(myDeliveryFee);
    myOrderString += `</tbody></table>`;
    myOrderString += `
    <div class="col-12 text-center">
    <hr />  
    <span class = "my-order-delivery-fee-header">Delivery fee: </span>
    <span class = "my-order-delivery-fee-value">$${myDeliveryFee}</span>
    <br />
    <hr />
    <span class = "my-order-grand-total-header">Grand Total: </span>
    <span class = "my-order-grand-total-value">$${total}</span>
    </div>`;
    allOrdersContainer.innerHTML = myOrderString;
}

function getHeaderLinks() {
    document.getElementById('chat-link').href = '/roomChat.html?' + roomID;
    document.getElementById('my-order-link').href = '/roomMyOrder.html?' + roomID;
}
