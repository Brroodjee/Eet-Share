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

document.getElementById("addRowButton").addEventListener('click', function() {
    const table = document.querySelector("#theTable");
    const tableTemplate = document.getElementById("productInHuis__Table-template");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    populateDropdown()
});
//https://stackoverflow.com/questions/44586479/adding-new-div-on-button-click
//https://www.sitepoint.com/community/t/adding-rows-to-table-with-button-to-remove/434396/5