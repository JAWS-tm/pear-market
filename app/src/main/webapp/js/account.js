
document.getElementsByName("newQuantity").forEach((element)=>{
    element.addEventListener("change", ()=>{
        element.nextElementSibling.classList.add("isVisible");
    });
})



const toggleAdminButtons = document.getElementsByClassName("toggle-admin");

for (const btn of toggleAdminButtons) {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        // noinspection JSUnusedLocalSymbols
        sendPost("/account",
            {
                action: "toggleAdmin",
                userId: btn.parentElement.getAttribute("user-id")
            },
            (data) => {
                const isAdmin = !(btn.getAttribute("is-admin") === 'true');
                btn.innerHTML = isAdmin ? "Supprimer admin" : "Mettre admin";
                btn.setAttribute("is-admin", String(isAdmin));
            },
            (data) => {

            })
    })
}

const toggleBlockedBtns = document.getElementsByClassName("toggle-blocked");

for (const btn of toggleBlockedBtns) {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        // noinspection JSUnusedLocalSymbols
        sendPost("/account",
            {
                action: "toggleBlocked",
                userId: btn.parentElement.getAttribute("user-id")
            },
            (data) => {
                const isBlocked = !(btn.getAttribute("is-blocked") === 'true');
                btn.innerHTML = isBlocked ? "Débloquer" : "Bloquer";
                btn.setAttribute("is-blocked", String(isBlocked));

                console.log("il est bloqué : " + isBlocked);
            },
            (data) => {

            })
    })
}

const selectStates = document.getElementsByClassName("select-state");

for (const select of selectStates) {
    select.lastSelected = select.value;
    // noinspection JSUnusedLocalSymbols
    select.addEventListener("change", (e) => {
        // noinspection JSUnusedLocalSymbols
        sendPost("/account",
            {
                action: "changeOrderState",
                userId: select.getAttribute("order-id"),
                state: select.value
            },
            (data) => {
                select.classList.remove("value-"+select.lastSelected);
                select.classList.add("value-"+select.value);

                select.lastSelected = select.value;
            },
            (data) => {
                select.value = select.lastSelected;
            })
    })
}