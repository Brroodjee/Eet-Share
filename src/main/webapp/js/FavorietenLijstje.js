const token = window.sessionStorage.getItem("myJWT");

document.addEventListener("DOMContentLoaded", () => {
    initializeEventListeners();
});

function initializeEventListeners() {
    const vleeswarenButton = document.querySelector(".lijst__addRowButton-vleeswaren");
    vleeswarenButton.addEventListener('click', function() {
        addRow(".lijst__table-vleeswaren", ".lijst__table-template-vleeswaren", VleeswarenDropdown);
    });

    const zuivelButton = document.querySelector(".lijst__addRowButton-zuivel");
    zuivelButton.addEventListener('click', function() {
        addRow(".lijst__table-zuivel", ".lijst__table-template-zuivel", ZuivelDropdown);
    });

    const groenteButton = document.querySelector(".lijst__addRowButton-groente");
    groenteButton.addEventListener('click', function() {
        addRow(".lijst__table-groente_en_fruit", ".lijst__table-template-groente", GroenteDropdown);
    });

    const drankenButton = document.querySelector(".lijst__addRowButton-dranken");
    drankenButton.addEventListener('click', function() {
        addRow(".lijst__table-drank", ".lijst__table-template-dranken", DrankenDropdown);
    });

    const koekButton = document.querySelector(".lijst__addRowButton-koek");
    koekButton.addEventListener('click', function() {
        addRow(".lijst__table-koek_en_snoep", ".lijst__table-template-koek", KoekDropdown);
    });

    const overigeButton = document.querySelector(".lijst__addRowButton-overige");
    overigeButton.addEventListener('click', function() {
        addRow(".lijst__table-overige", ".lijst__table-template-overige", OverigeDropdown);
    });

    document.querySelectorAll(".lijst__form").forEach(form => {
        form.addEventListener("submit", function(event) {
            event.preventDefault();
        });
    });
}

function addRow(tableSelector, templateSelector, dropdownFunction) {
    const table = document.querySelector(tableSelector);
    const tableTemplate = document.querySelector(templateSelector);
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    dropdownFunction();
}

function VleeswarenDropdown() {
    console.log("VleeswarenDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/vleeswaren', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-vleeswaren', data))
        .catch(error => console.error('Error:', error));
}

function ZuivelDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/zuivel', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-zuivel', data))
        .catch(error => console.error('Error:', error));
}

function GroenteDropdown() {
    console.log("GroenteDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/groente', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-groente_en_fruit', data))
        .catch(error => console.error('Error:', error));
}

function DrankenDropdown() {
    console.log("DrankenDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/dranken', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-drank', data))
        .catch(error => console.error('Error:', error));
}

function KoekDropdown() {
    console.log("KoekDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/koek', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-koek_en_snoep', data))
        .catch(error => console.error('Error:', error));
}

function OverigeDropdown() {
    console.log("OverigeDropdown() function called");
    fetch('https://tests-1718633149689.azurewebsites.net/eet-share/producten/overige', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => populateDropdowns('.lijst__dropdown-overige', data))
        .catch(error => console.error('Error:', error));
}

function populateDropdowns(selector, data) {
    const dropdowns = document.querySelectorAll(selector);
    dropdowns.forEach(dropdown => {
        if (dropdown.children.length <= 1) {
            data.forEach(product => {
                const option = document.createElement('option');
                option.text = product.productNaam;
                option.value = product.productNaam; // Set the value to product name
                dropdown.add(option);
            });
        }
    });
}

function getValues() {
    const vleeswaren = Array.from(document.querySelectorAll(".lijst__dropdown-vleeswaren")).map(select => select.value);
    const zuivel = Array.from(document.querySelectorAll(".lijst__dropdown-zuivel")).map(select => select.value);
    const groenteEnFruit = Array.from(document.querySelectorAll(".lijst__dropdown-groente_en_fruit")).map(select => select.value);
    const dranken = Array.from(document.querySelectorAll(".lijst__dropdown-drank")).map(select => select.value);
    const koekEnSnoep = Array.from(document.querySelectorAll(".lijst__dropdown-koek_en_snoep")).map(select => select.value);
    const overige = Array.from(document.querySelectorAll(".lijst__dropdown-overige")).map(select => select.value);

    const values = {
        vleeswaren: vleeswaren.filter(value => value !== 'Selecteer product'),
        zuivel: zuivel.filter(value => value !== 'Selecteer product'),
        groenteEnFruit: groenteEnFruit.filter(value => value !== 'Selecteer product'),
        dranken: dranken.filter(value => value !== 'Selecteer product'),
        koekEnSnoep: koekEnSnoep.filter(value => value !== 'Selecteer product'),
        overige: overige.filter(value => value !== 'Selecteer product')
    };

    console.log(values);

    return values;
}

document.getElementById('getValuesButton').addEventListener('click', function(event) {
    event.preventDefault();
    const values = getValues();
    console.log(values);

    // Post all the values
    for (const [category, products] of Object.entries(values)) {
        products.forEach(product => {
            if (product !== 'Selecteer product') {
                const bodyData = { productNaam: product };

                fetch("https://tests-1718633149689.azurewebsites.net/eet-share/favorietenlijstje", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify(bodyData)
                })
                    .then((res) => {
                        if (!res.ok) {
                            throw new Error(`HTTP error! status: ${res.status}`);
                        }
                        return res.json();
                    })
                    .then((data) => {
                        console.log(`Product ${product} toegevoegd aan lijstje`, data);
                    })
                    .catch((error) => {
                        console.error(`Fout bij het toevoegen van product ${product}: `, error);
                    });
            }
        });
    }
});