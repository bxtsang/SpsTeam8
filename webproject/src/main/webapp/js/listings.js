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
