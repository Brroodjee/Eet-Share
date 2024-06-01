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

function ZuivelDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/zuivel', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-zuivel');
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

document.querySelector(".lijst__addRowButton-zuivel").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-zuivel");
    const tableTemplate = document.querySelector(".lijst__table-template-zuivel");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    ZuivelDropdown();
});

function GroenteDropdown() {
    console.log("GroenteDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/groente', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-groente_en_fruit');
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

document.querySelector(".lijst__addRowButton-groente").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-groente_en_fruit");
    const tableTemplate = document.querySelector(".lijst__table-template-groente");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    GroenteDropdown();
});


function DrankenDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/dranken', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-drank');
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

document.querySelector(".lijst__addRowButton-dranken").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-drank");
    const tableTemplate = document.querySelector(".lijst__table-template-dranken");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    DrankenDropdown();
});


function KoekDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/koek', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-koek_en_snoep');
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

document.querySelector(".lijst__addRowButton-koek").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-koek_en_snoep");
    const tableTemplate = document.querySelector(".lijst__table-template-koek");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    KoekDropdown();
});


function OverigeDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('http://localhost:4711/eet-share/producten/overige', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.lijst__dropdown-overige');
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

document.querySelector(".lijst__addRowButton-overige").addEventListener('click', function() {
    const table = document.querySelector(".lijst__table-overige");
    const tableTemplate = document.querySelector(".lijst__table-template-overige");
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    OverigeDropdown();
});