function GetHuishoudenNaamEnLeden() {
    console.log("Huishouden gegevens ophalen");
    fetch('http://localhost:4711/eet-share/huishouden', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const huishoudenNaamElement = document.getElementById("huishouden__h1-naam");
            const huishoudenGrid = document.querySelector(".huishouden__grid");
            const template = document.querySelector("template");

            if (data.length > 0) {
                const huishouden = data[0];
                huishoudenNaamElement.innerText = huishouden.huishoudenNaam;

                const hoofd = huishouden.Hoofd;
                let clone = template.content.cloneNode(true);
                clone.querySelector(".role").innerText = "Hoofd";
                clone.querySelector(".name").innerText = hoofd.naam;
                huishoudenGrid.appendChild(clone);

                huishouden.leden.forEach(lid => {
                    clone = template.content.cloneNode(true);
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

window.onload = GetHuishoudenNaamEnLeden;
