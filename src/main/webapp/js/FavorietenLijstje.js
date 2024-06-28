const token = window.sessionStorage.getItem("myJWT");

document.addEventListener("DOMContentLoaded", () => {
    const responseMessageHuishouden = document.querySelector(".responseMessage");
    const progressBarHuishouden = document.querySelector(".progressBar");

    initializeEventListeners();

    document.getElementById('saveButton').addEventListener('click', function(event) {
        event.preventDefault();
        const values = getValues();
        console.log(values);
        postValues(values);

        console.log("sluit na 5 sec")
        responseMessageHuishouden.innerText = "Boodschappenlijst opgeslagen!";
        responseMessageHuishouden.style.color = "green" //https://www.w3schools.com/jsref/prop_style_color.asp
        progressBarHuishouden.style.width = "100%";
        setTimeout(() => {
            location.reload()
        }, 5000);
    });

    getFavorietenlijstje();
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
    fetch('/eet-share/producten/vleeswaren', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            populateDropdowns('.lijst__dropdown-vleeswaren', data);
        })
        .catch(error => console.error('Error:', error));
}

function ZuivelDropdown() {
    console.log("ZuivelDropdown() function called");
    fetch('/eet-share/producten/zuivel', {
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
    fetch('/eet-share/producten/groente', {
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
    fetch('/eet-share/producten/dranken', {
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
    fetch('http://localhost:4711/eet-share/producten/koek', {
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
    fetch('/eet-share/producten/overige', {
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
                option.value = product.productNaam;
                dropdown.add(option);
            });
        }
    });
}

function getValues() {
    const values = [];
    const categories = ["vleeswaren", "zuivel", "groente_en_fruit", "drank", "koek_en_snoep", "overige"];

    categories.forEach(category => {
        const rows = document.querySelectorAll(`.lijst__table-${category} tr`);
        rows.forEach(row => {
            const dropdown = row.querySelector(`.lijst__dropdown-${category}`);

            if (dropdown && dropdown.value !== 'Selecteer product') {
                values.push({
                    product: dropdown.value,
                });
            }
        });
    });

    console.log(values);
    return values;
}

function postValues(values) {
    values.forEach(({ product }) => {
        const bodyData = { productNaam: product };

        fetch("/eet-share/favorietenlijstje", {
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
    });
}

function getFavorietenlijstje() {
    console.log("getFavorietenlijstje() called");
    fetch('/eet-share/favorietenlijstje/mijn', {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const producten = data.producten;
            producten.forEach(product => {
                console.log('Product:', product);
                const category = product.category;

                let tableSelector;
                let templateSelector;

                switch (category) {
                    case 'vleeswaren':
                        tableSelector = '.lijst__table-vleeswaren';
                        templateSelector = '.lijst__table-template-vleeswaren-GET';
                        break;
                    case 'groente_en_fruit':
                        tableSelector = '.lijst__table-groente_en_fruit';
                        templateSelector = '.lijst__table-template-groente-GET';
                        break;
                    case 'dranken':
                        tableSelector = '.lijst__table-drank';
                        templateSelector = '.lijst__table-template-dranken-GET';
                        break;
                    case 'zuivel':
                        tableSelector = '.lijst__table-zuivel';
                        templateSelector = '.lijst__table-template-zuivel-GET';
                        break;
                    case 'koek_en_snoep':
                        tableSelector = '.lijst__table-koek_en_snoep';
                        templateSelector = '.lijst__table-template-koek-GET';
                        break;
                    case 'overige':
                        tableSelector = '.lijst__table-overige';
                        templateSelector = '.lijst__table-template-overige-GET';
                        break;
                    default:
                        console.log(`Onbekende categorie voor product: ${product.productNaam}`);
                        return;
                }

                const table = document.querySelector(tableSelector);
                const template = document.querySelector(templateSelector);

                if (!template) {
                    console.error(`Template niet gevonden voor selector: ${templateSelector}`);
                    return;
                }

                const newRow = document.importNode(template.content, true);
                const dropdown = newRow.querySelector('select');

                const option = document.createElement('option');
                option.value = product.productNaam;
                option.text = product.productNaam;
                dropdown.add(option);
                dropdown.value = product.productNaam;

                console.log(product.productNaam);

                table.appendChild(newRow);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}