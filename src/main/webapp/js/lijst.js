function VleeswarenDropdown() {
    console.log("VleeswarenDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/vleeswaren', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-vleeswaren');
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

document.querySelector(".lijst__addRowButton-vleeswaren").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-vleeswaren");
    const tableTemplate = document.querySelector(".lijst__table-template-vleeswaren");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    VleeswarenDropdown()
});