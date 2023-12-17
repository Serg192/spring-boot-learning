const editAddressForm = document.getElementById("edit-address-form");

editAddressForm.addEventListener("submit", (event) => {
    if(!isDeliveryAddressSpecified()) {
        alert("Будь ласка укажіть спосіб доставки(хоча б один)");
        event.preventDefault();
    }
});

function isDeliveryAddressSpecified(){
    const address = document.getElementById("addressStr").value.trim();
    const pointNP = document.getElementById("pointNP").value.trim();
    const pointUkrPost = document.getElementById("pointUkrPost").value.trim();

    return address.length > 0 ||
           pointNP.length > 0 ||
           pointUkrPost.length > 0;
}

