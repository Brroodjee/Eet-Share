const token = window.sessionStorage.getItem("myJWT");

document.addEventListener("DOMContentLoaded", () => {
    const createHouseholdModal = document.getElementById("myModal");
    const btn = document.getElementById("myBtn");
    const span1 = document.getElementById("span1");

    btn.onclick = function() {
        createHouseholdModal.style.display = "block";
    }

    span1.onclick = function() {
        createHouseholdModal.style.display = "none";
    }

    GetHuishoudenNaamEnLeden();
})

function GetHuishoudenNaamEnLeden() {
    console.log("Huishouden gegevens ophalen");
    fetch('/eet-share/huishouden', {
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
            console.log("Ontvangen data:", data);
            const huishoudenNaamElement = document.querySelector("#huishouden__h1-naam");
            const huishoudenGrid = document.querySelector(".huishouden__grid");
            const template = document.querySelector("template");

            huishoudenGrid.innerHTML = '';

            if (data.length > 0) {
                const huishouden = data[0];
                huishoudenNaamElement.innerText = huishouden.huishoudenNaam;

                const hoofd = huishouden.Hoofd;
                console.log("Hoofd:", hoofd);

                if (hoofd) {
                    let clone = template.content.cloneNode(true);
                    clone.querySelector(".role").innerText = "Hoofd";
                    clone.querySelector(".name").innerText = hoofd.naam;
                    huishoudenGrid.appendChild(clone);
                } else {
                    console.error("Hoofd object niet gevonden of naam ontbreekt");
                }

                huishouden.leden.forEach(lid => {
                    console.log("Lid:", lid);
                    let clone = template.content.cloneNode(true);
                    clone.querySelector(".role").innerText = "Lid";
                    clone.querySelector(".name").innerText = lid.naam;
                    huishoudenGrid.appendChild(clone);
                });
            } else {
                huishoudenNaamElement.innerText = "Geen huishouden gevonden";
            }
        })
        .catch(error => console.error('Error fetching household data:', error));
}

document.addEventListener("DOMContentLoaded", () => {
    const huishoudenNaamForm = document.querySelector("#huishouden__form-create");
    const huishoudenNaamModal = document.querySelector("#myModal");
    const responseMessageHuishouden = document.querySelector(".responseMessage");
    const progressBarHuishouden = document.querySelector(".progressBar");

    huishoudenNaamForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const huishoudenNaam = document.querySelector("#huishouden__modal-naam").value;
        const submitterButton = event.submitter.id;

        if (submitterButton === "huishouden__modal-button") {

            createHousehold(huishoudenNaam);
            console.log("sluit na 2 sec")
            responseMessageHuishouden.innerText = "huishouden " + huishoudenNaam + " aangemaakt!";
            responseMessageHuishouden.style.color = "green" //https://www.w3schools.com/jsref/prop_style_color.asp
            progressBarHuishouden.style.width = "100%";
            setTimeout(() => {
                huishoudenNaamModal.style.display = "none";
                location.reload()
            }, 2000);
        }
    })
})

function createHousehold(huishoudenNaam) {
    fetch("/eet-share/huishouden/aanmaken", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "huishoudenNaam": huishoudenNaam,
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
