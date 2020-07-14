function getRoomDetails() {
  let roomDetails = {
    shopName: "McDonald's",
    postalCode: "123456",
    deliveryAddress: "1 Sorby Adams Drive",
    description:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
    category: "Food",
    deliveryFee: "20",
    moneyLeftToMinimumOrder: "100",
  };

  let roomDetailsContainer = document.getElementById("room-details-container");

  let roomDetailsString = `
    <div>
      <span class="room-details-shopName">${roomDetails.shopName}</span>
      <br />
      <br />

      <span class="room-details-header">Delivering To:</span>
      <br />
      <span class="room-details-value">${roomDetails.deliveryAddress}</span>
      <br />
      <span class="room-details-value">${roomDetails.postalCode}</span>
      <br />
      <br />

      <span class="room-details-header">Delivery fee: </span>
      <br />
      <span class="room-details-value">$${roomDetails.deliveryFee}</span>
      <br/>

      <span class="room-details-header">$ left to minimum order: </span>
      <br />
      <span class="room-details-value">$${roomDetails.moneyLeftToMinimumOrder}</span>

    </div>
  `;

  roomDetailsContainer.innerHTML = roomDetailsString;
}

function getMyOrder() {
  let myOrderItems = [
    {
      productName: "Chicken burger",
      quantity: 2,
      perUnitPrice: 10,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
    },
    {
      productName: "Fish burger",
      quantity: 1,
      perUnitPrice: 15,
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
    description:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
    category: "Food",
    deliveryFee: "20",
    minimumOrder: "100",
    noOfPeopleInRoom: 3,
  };

  let myOrderContainer = document.getElementById("my-order-container");

  if (myOrderItems.length <= 0) {
    myOrderContainer.innerHTML = "Add an item now!";
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
        </tr>
      </thead>
    <tbody>`;

  let total = 0;
  for (let i = 0; i < myOrderItems.length; i++) {
    productTotal = myOrderItems[i].quantity * myOrderItems[i].perUnitPrice;
    myOrderString += `
      <tr>
        <th scope="row">${i + 1}</th>
        <td>${myOrderItems[i].productName}</td>
        <td>${myOrderItems[i].quantity}</td>
        <td>${myOrderItems[i].perUnitPrice}</td>
        <td>${productTotal}</td>
      </tr>`;
    total += productTotal;
  }

  myOrderString += getNewProductForm();

  myOrderString += `</tbody></table>`;
  myOrderString += `
  <div class="col-12 text-center">
    <hr />  
    <span class = "my-order-delivery-fee-header">Delivery fee: </span>
    <span class = "my-order-delivery-fee-value">$${(
      myRoomDetails.deliveryFee / myRoomDetails.noOfPeopleInRoom
    ).toFixed(2)}</span>
    <br />
    <hr />
    <span class = "my-order-grand-total-header">Grand Total: </span>
    <span class = "my-order-grand-total-value">$${total}</span>
  </div>`;
  myOrderContainer.innerHTML = myOrderString;
}

function getNewProductForm() {
  return `<form action="/myOrder" method="post">
    <tr>
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
      <td><input type="submit" class="btn btn-add" value="Add" />
      </td>
    </tr>
  </form>`;
}
