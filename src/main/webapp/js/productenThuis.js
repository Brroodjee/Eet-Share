function populateDropdown() {
    fetch('http://localhost:4711/eet-share/login', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.user-dropdown');
            dropdowns.forEach(dropdown => {
                if (dropdown.children.length <= 1) {   // kijkt of de producten (in dit geval users nog) al in de dropdown staan, zo niet voegt hij ze toe
                    data.forEach(user => {
                        const option = document.createElement('option');
                        option.text = user.naam;
                        dropdown.add(option);
                    });
                }
            });
        })
        .catch(error => console.error('Error:', error));
}

document.getElementById("ProductInHuis__button--plaatsToevoegen").addEventListener('click', function() {
    const container = document.querySelector(".grid-container");
    const template = document.getElementById("productInHuis__template");
    const clone = template.content.cloneNode(true);
    container.appendChild(clone);

    populateDropdown();
});

document.getElementById("addRowButton").addEventListener('click', function() {
    const table = document.querySelector("#theTable");
    const tableTemplate = document.getElementById("productInHuis__Table-template");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);
});
//https://stackoverflow.com/questions/44586479/adding-new-div-on-button-click
//https://www.sitepoint.com/community/t/adding-rows-to-table-with-button-to-remove/434396/5