var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.maxHeight){
            content.style.maxHeight = null;
        } else {
            content.style.maxHeight = content.scrollHeight + "px";
        }
    });
}

function populateDropdown() {
    console.log("populateDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.product-dropdown');
            dropdowns.forEach(dropdown => {
                if (dropdown.children.length <= 1) {   // kijkt of de producten al in de dropdown staan, zo niet voegt hij ze toe
                    data.forEach(product => {
                        const option = document.createElement('option');
                        option.text = product.productNaam;
                        dropdown.add(option);
                    });
                }
            });
        })
        .catch(error => console.error('Error:', error));
}

document.querySelector(".addRowButton").addEventListener('click', function() {
    const table = document.querySelector(".theTable");
    const tableTemplate = document.getElementById("productInHuis__Table-template");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    populateDropdown()
});
//https://stackoverflow.com/questions/44586479/adding-new-div-on-button-click
//https://www.sitepoint.com/community/t/adding-rows-to-table-with-button-to-remove/434396/5