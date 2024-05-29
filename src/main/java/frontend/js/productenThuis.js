document.getElementById("ProductInHuis__button--plaatsToevoegen").onclick = function() {
    var container = document.getElementById("productInHuis__container");
    var template = document.getElementById("productInHuis__template");
    var clone = template.content.cloneNode(true);
    container.appendChild(clone);
} //https://stackoverflow.com/questions/44586479/adding-new-div-on-button-click