document.getElementById("ProductInHuis__button--plaatsToevoegen").onclick = function() {
    const container = document.querySelector(".grid-container");
    const template = document.getElementById("productInHuis__template");
    const clone = template.content.cloneNode(true);
    container.appendChild(clone);
} //https://stackoverflow.com/questions/44586479/adding-new-div-on-button-click