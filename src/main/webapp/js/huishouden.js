function GetHuishoudenNaam() {
    console.log("huishouden naam ophalen");
    fetch('http://localhost:4711/eet-share/huishouden', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            const huishoudenNaamElement = document.getElementById("huishouden__h1-naam");
            if (data.length > 0) {
                const huishoudenNaam = data[0].huishoudenNaam;
                huishoudenNaamElement.innerText = huishoudenNaam;
            } else {
                huishoudenNaamElement.innerText = "Geen huishouden gevonden";
            }
        })
}

window.onload = GetHuishoudenNaam;