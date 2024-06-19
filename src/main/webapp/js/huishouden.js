const token = window.sessionStorage.getItem("myJWT");
console.log("Token:", token);  // Toegevoegd om het token te loggen en te controleren
function GetHuishoudenNaamEnLeden() {
    console.log("Huishouden gegevens ophalen");
    fetch('http://localhost:4711/eet-share/huishouden', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const huishoudenNaamElement = document.getElementById("huishouden__h1-naam");
            const huishoudenGrid = document.querySelector(".huishouden__grid");
            const template = document.querySelector("template");

            huishoudenGrid.innerHTML = '';

            if (data.length > 0) {
                const huishouden = data[0];
                huishoudenNaamElement.innerText = huishouden.huishoudenNaam;

                const hoofd = huishouden.Hoofd;
                let clone = template.content.cloneNode(true);
                clone.querySelector(".role").innerText = "Hoofd";
                clone.querySelector(".name").innerText = hoofd.name;
                huishoudenGrid.appendChild(clone);

                huishouden.leden.forEach(lid => {
                    clone = template.content.cloneNode(true);
                    clone.querySelector(".role").innerText = "Lid";
                    clone.querySelector(".name").innerText = lid.name;
                    huishoudenGrid.appendChild(clone);
                });
            } else {
                huishoudenNaamElement.innerText = "Geen huishouden gevonden";
            }
        })
        .catch(error => console.error('Error fetching household data:', error));
}

document.addEventListener("DOMContentLoaded", () => {
    const createForm = document.querySelector("#huishouden__form-create");
    console.log(createForm);
    createForm.addEventListener("submit", postData);

    function postData(event) {
        event.preventDefault();
        console.log({
            "huishoudenNaam": document.querySelector("#huishouden__modal-naam").value,
        });

        fetch("http://localhost:4711/eet-share/huishouden", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
                "huishoudenNaam": document.querySelector("#huishouden__modal-naam").value,
            })
        })
            .then((res) => {
                if (!res.ok) {
                    throw new Error(`HTTP error! status: ${res.status}`);
                }
                return res.json();
            })
            .then((data) => {
                console.log(data);
            })
            .catch((error) => {
                console.error('Error posting household data:', error);
            });
    }
});

window.onload = GetHuishoudenNaamEnLeden;
