var roomID = window.location.search.substr(1);
window.onload = function() {
    roomID = window.location.search.substr(1);
    getRoomDetails(roomID);
    getMyOrder();
    getHeaderLinks();
}

async function getMyOrder() {
    let orderResponse = await fetch(`/myOrder?roomId=${roomID}`);
    let myOrders = await orderResponse.json();

    let roomResponse = await fetch(`https://summer20-sps-47.firebaseio.com/rooms/${roomID}.json`)
    let myRoomDetails = await roomResponse.json();

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

    let myOrderContainer = document.getElementById("my-order-container");
    if (myOrders.orders == null) {
        myOrderContainer.innerHTML = "Add an item now!";
    } else {
        let myOrderItems = Object.values(myOrders.orders);
        for (var i = 0; i < myOrderItems.length; i++) {
            let orderItem = myOrderItems[i];
            productTotal = orderItem.quantity * orderItem.unitPrice;
            total += productTotal;
            myOrderString += `
        <tr>
        <th scope="row">${i + 1}</th>
        <td>${orderItem.product}</td>
        <td>${orderItem.quantity}</td>
        <td>${orderItem.unitPrice}</td>
        <td>${productTotal}</td>
        <td>
        <button onclick="deleteOrder('${orderItem.orderId}')" class="btn my-order-delete-btn">
        <i class="fa fa-times" aria-hidden="true"></i>
        </button>
        </td>
        </tr>`;
        }
    }
    myDeliveryFee = (
        myRoomDetails.deliveryFee / myRoomDetails.users.length
    ).toFixed(2);

    myOrderString += getNewProductForm();
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
    myOrderContainer.innerHTML = myOrderString;
}

function getNewProductForm() {
    return `<tr>
    <th scope="row">
    </th>
    <td>
    <input type="string" class="form-control" id="newProductName" required />
    </td>
    <td>
    <input type="number" class="form-control" id="newProductQuantity" required />
    </td>
    <td>
    <input type="number" class="form-control" id="newProductUnitPrice" required />
    </td>
    <td><button onclick="addOrder()" class="btn btn-add" value="Add">Add</button>
    </td>
    <td></td>
    </tr>`;
}

async function addOrder() {
    let product = document.querySelector("#newProductName").value;
    let quantity = document.querySelector("#newProductQuantity").value;
    let unitPrice = document.querySelector("#newProductUnitPrice").value;

    let response = await $.ajax({
        type: 'POST',
        url: "/order",
        data: {
            'roomId': roomID,
            'product': product,
            'quantity': quantity,
            'unitPrice': unitPrice
        },
    });
  
    if (response.status == 200) {
      window.alert("Your order is added!")
    }
  
    window.location.reload();
}

async function deleteOrder(orderID) {
    let response = await $.ajax({
        type: 'DELETE',
        url: "/myOrder",
        data: {
            'orderId': orderID
        },
    });
  
    if (response.status == 200) {
      window.alert("Your order has been deleted!")
    }
  
    window.location.reload();
}

function getHeaderLinks() {
    document.getElementById('chat-link').href = '/roomChat.html?' + roomID;
    document.getElementById('all-orders-link').href = '/roomAllOrders.html?' + roomID;
}
