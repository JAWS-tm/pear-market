
document.getElementsByName("newQuantity").forEach((element)=>{
    element.addEventListener("change", ()=>{
        element.nextElementSibling.classList.add("isVisible");
    });
})


