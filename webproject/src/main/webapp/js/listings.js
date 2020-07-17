function getListings() {
  let listingsStub = [
    {
      shopName: "McDonald's",
      postalCode: "123456",
      description:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
      category: "Food",
      deliveryFee: "20",
      moneyLeftToMinimumOrder: "100",
    },
    {
      shopName: "Lalalala",
      postalCode: "455444",
      description:
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
      category: "Clothes",
      deliveryFee: "20",
      moneyLeftToMinimumOrder: "100",
    },
  ];

  let cardsContainer = document.getElementById("listings-card-container");
  let childHtmlString = "";

  for (let listing of listingsStub) {
    childHtmlString += `<div class="shadow-sm p-3 mb-5 bg-white rounded listing-card">
      <div class="row form-group">
        <div class="col-md-6 mb-3 mb-md-0">
          <span class="card-heading shop-name">${listing.shopName}</span>
          <br />
          <span class="card-field">Postal Code: </span>
          <span class="card-value postal-code-value">
            ${listing.postalCode}
          </span>
          <br />
          <span class="card-description">
            ${listing.description}
          </span>
        </div>
        <div class="col-md-6 text-right">
          <span class="card-field">Category: </span>
          <span class="card-value category-value">${listing.category}</span>
          <br />
          <span class="card-field">Delivery fee: </span>
          <span class="card-value">$${listing.deliveryFee}</span>
          <br />
          <span class="card-field">$ left to minimum order: </span>
          <span class="card-value">$${listing.moneyLeftToMinimumOrder}</span>
        </div>
      </div>
    </div>`;
  }
  cardsContainer.innerHTML = childHtmlString;
}

function getSearchResults() {
  // Declare variables

  let input = document.getElementById("searchBox");
  let filter = input.value.toUpperCase();
  let cardsContainer = document.getElementById("listings-card-container");
  let cards = cardsContainer.getElementsByClassName("listing-card");

  // Loop through all table rows, and hide those who don't match the search query
  for (let i = 0; i < cards.length; i++) {
    shopName = cards[i]
      .getElementsByClassName("shop-name")[0]
      .innerHTML.toUpperCase();
    category = cards[i]
      .getElementsByClassName("category-value")[0]
      .innerHTML.toUpperCase();
    cardDescription = cards[i]
      .getElementsByClassName("card-description")[0]
      .innerHTML.toUpperCase();
    postalCode = cards[i]
      .getElementsByClassName("postal-code-value")[0]
      .innerHTML.toUpperCase();

    isFilterInCard =
      shopName.indexOf(filter) > -1 ||
      category.indexOf(filter) > -1 ||
      cardDescription.indexOf(filter) > -1 ||
      postalCode.indexOf(filter) > -1;

    if (isFilterInCard) {
      cards[i].style.display = "";
    } else {
      cards[i].style.display = "none";
    }
  }
}

function getRooms() {
  document.getElementById("listings-card-container").innerHTML = '';
  firebase.database().ref("rooms/").once('value', function(snapshot) {
    snapshot.forEach(function(child) { 
      var snap = child.val();
      document.getElementById("listings-card-container").innerHTML += `
    <a href="./room.html?${child.key}">
      <div class="shadow-sm p-3 mb-5 bg-white rounded listing-card">
        <div class="row form-group">
          <div class="col-md-6 mb-3 mb-md-0">
            <span class="card-heading shop-name">${snap.title}</span>
          </div>
        </div>
      </div>
    </a>`;
    });
  });
}
