
function sendPost(url, data, callbackFn) {
    fetch(ctx + url, {
        method: "POST",headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(data)
    }).then(res => callbackFn(res));
}

const cartRow = document.getElementsByClassName("cart-row");

for(let row of cartRow) {
    let deleteBtn = row.getElementsByClassName("delete-btn");
    deleteBtn.addEventListener("click", () =>{
        sendPost("/cart/delete", {
                productId: deleteBtn.getAttribute("data-product-id")
            },
            (res) => {
                deleteBtn.parentElement.parentElement.parentElement.remove();
            }
        )
    })


}



//
// function ajaxPost(url, data, callback) {
//     var xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
//
//     xmlDoc.open('POST', url, true);
//     xmlDoc.setRequestHeader("Content-type", "application/json");
//
//     xmlDoc.onreadystatechange = function() {
//         if (xmlDoc.readyState === 4 && xmlDoc.status === 200) {
//             callback(xmlDoc);
//         }
//     }
//
//     xmlDoc.send(data);
// }
//
// ajaxPost(ctx+"/cart", JSON.stringify(data), (data)=>{
//     console.log(data);
// })