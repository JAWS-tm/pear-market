function showError() {
    document.querySelector(".forget-coupon.error").classList.remove("invisible")
}

function removeError() {
    document.querySelector(".forget-coupon.error").classList.add("invisible")
}

function sendPost(url, paramData, successCallbackFn, failCallbackFn) {
    fetch(ctx + url, {
        method: "POST",headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(paramData)
    })
        .then(res => {
            res.text().then((data) => {
                if (res.status === 200) {
                    removeError();
                    successCallbackFn(data);
                } else {
                    showError();
                    failCallbackFn(data);
                }
            })
        })
}



const cartRow = document.getElementsByClassName("cart-row");

function removeRow(row) {
    row.remove();

    if (cartRow.length === 0){
        document.getElementById("no-products").classList.remove("invisible");
        document.getElementById("cart-product-list").classList.add("invisible");
    }
}

function updatePrice() {
    let total = 0;
    for(let row of cartRow) {
        total += parseFloat(row.querySelector(".row-total").innerHTML);
    }
    document.getElementById("total-price").innerHTML = total.toFixed(2)+"€";
}

for(let row of cartRow) {
    let deleteBtn = row.querySelector(".delete-btn");
    deleteBtn.addEventListener("click", () =>{
        sendPost("/cart/delete", {
                productId: row.getAttribute("data-product-id")
            },
            (data) => {
                removeRow(row);
                updatePrice();
            },
            (data) => {
            }
        );
    })

    let quantityInput = row.querySelector(".newQuantity");
    let quantityBtn = row.querySelector(".changeQuantity");
    quantityBtn.addEventListener("click", () => {
        sendPost("/cart/changeQuantity", {
                productId: row.getAttribute("data-product-id"),
                quantity: quantityInput.value
            }, (data) => {

                if (parseInt(quantityInput.value) === 0)
                    removeRow(row);

                row.querySelector(".applyChange-container").classList.remove("isVisible");
                quantityInput.setAttribute("last-value", quantityInput.value)

                let totalCol = row.querySelector(".row-total");
                totalCol.innerHTML = ((parseInt(totalCol.getAttribute("item-price"))) * parseInt(quantityInput.value)).toFixed(2) + "€";

                updatePrice();
            }, (data) => {
                row.querySelector(".applyChange-container").classList.remove("isVisible");
                quantityInput.value = quantityInput.getAttribute("last-value");
            }
        );
    })
}
