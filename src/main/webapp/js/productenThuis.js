const token = window.sessionStorage.getItem("myJWT");

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

    getProductenInHuis();
});

function initializeEventListeners() {
    document.querySelector(".addRowButton-1").addEventListener('click', function() {
        addRow(".theTable-1", "#productInHuis__Table-template-1", populateDropdown);
    });

    document.querySelector(".addRowButton-2").addEventListener('click', function() {
        addRow(".theTable-2", "#productInHuis__Table-template-2", populateDropdown);
    });

    document.querySelector(".addRowButton-3").addEventListener('click', function() {
        addRow(".theTable-3", "#productInHuis__Table-template-3", populateDropdown);
    });

    document.querySelector(".addRowButton-4").addEventListener('click', function() {
        addRow(".theTable-4", "#productInHuis__Table-template-4", populateDropdown);
    });

    document.querySelector(".addRowButton-5").addEventListener('click', function() {
        addRow(".theTable-5", "#productInHuis__Table-template-5", populateDropdown);
    });

}

function addRow(tableSelector, templateSelector, dropdownFunction) {
    const table = document.querySelector(tableSelector);
    const tableTemplate = document.querySelector(templateSelector);
    const cloneTable = tableTemplate.content.cloneNode(true);
    table.appendChild(cloneTable);

    dropdownFunction();
}

function populateDropdown() {
    console.log("populateDropdown() function called");
    fetch('/eet-share/producten', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdowns = document.querySelectorAll('.product-dropdown');
            dropdowns.forEach(dropdown => {
                if (dropdown.children.length <= 1) {
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

function getValues() {
    const values = [];
    const locations = ["1", "2", "3", "4", "5"];

    locations.forEach(location => {
        const rows = document.querySelectorAll(`.theTable-${location} tr`);
        rows.forEach(row => {
            const quantityInput = row.querySelector(".productInHuis__input-number");
            const dropdown = row.querySelector(".product-dropdown");

            if (quantityInput && dropdown && dropdown.value !== 'Selecteer product') {
                values.push({
                    product: dropdown.value,
                    quantity: parseInt(quantityInput.value),
                    location: parseInt(location)
                });
            }
        });
    });

    console.log(values);
    return values;
}

function postValues(values) {
    values.forEach(({ product, quantity, location }) => {
        const bodyData = { productNaam: product, quantity, location };

        fetch("/eet-share/productenthuis/testing", {
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
                console.log(`Product ${product} met kwantiteit ${quantity} en locatie ${location} toegevoegd aan lijstje`, data);
            })
            .catch((error) => {
                console.error(`Fout bij het toevoegen van product ${product}: `, error);
            });
    });
}

function getProductenInHuis() {
    console.log("getProductenInHuis() called");
    fetch('/eet-share/productenthuis/ophalen', {
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
                console.log("product:", product);
                const products = product.product;
                console.log(products)
                const quantity = product.quantity;
                console.log(quantity);
                const location = product.location;

                let tableSelector = `.theTable-${location}`;
                let templateSelector = `#productInHuis__Table-template-${location}-GET`;

                const table = document.querySelector(tableSelector);
                const template = document.querySelector(templateSelector);

                if (!template) {
                    console.error(`Template niet gevonden voor selector: ${templateSelector}`);
                    return;
                }

                const newRow = document.importNode(template.content, true);
                newRow.querySelector('.productInHuis__input-number-GET').value = quantity;

                const dropdown = newRow.querySelector('select');
                const option = document.createElement('option');
                option.value = products.productNaam;
                option.text = products.productNaam;
                dropdown.add(option);
                dropdown.value = products.productNaam;

                console.log(product.productNaam);

                table.appendChild(newRow);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
