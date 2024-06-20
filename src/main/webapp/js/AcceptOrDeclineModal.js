document.addEventListener("DOMContentLoaded", () => {
    const acceptOrDeclineModal = document.getElementById("acceptOrDeclineModal");
    const BtnLink = document.getElementById("acceptInvite");
    const span3 = document.getElementById("span3");

    BtnLink.onclick = function() {
        acceptOrDeclineModal.style.display = "block";
    };

    span3.onclick = function() {
        acceptOrDeclineModal.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target === acceptOrDeclineModal) {
            acceptOrDeclineModal.style.display = "none";
        }
    };

    DropdownInvites();
});

function DropdownInvites() {
    fetch("https://tests-1718633149689.azurewebsites.net/eet-share/huishouden/invites", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdownGebruikers = document.querySelector('#invites__dropdown');
            dropdownGebruikers.innerHTML = "";

            data.forEach(invite => {
                const option = document.createElement('option');
                option.value = invite.hoofd;
                option.text = invite.hoofd;
                dropdownGebruikers.add(option);
            });
        })
        .catch(error => console.error('Error:', error));
}


document.addEventListener("DOMContentLoaded", () => {
    const invitesForm = document.querySelector("#invite__form-accept-decline");

    invitesForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const selectedInvite = document.querySelector("#invites__dropdown").value;
        const submitButton = event.submitter.id;

        if (submitButton === "invite__modal-button-accept") {
            acceptInvite(selectedInvite);
        } else if (submitButton === "invite__modal-button-decline") {
            declineInvite(selectedInvite);
        }

        acceptOrDeclineModal.style.display = "none";
    });
});


function acceptInvite(selectedInvite) {
    fetch("https://tests-1718633149689.azurewebsites.net/eet-share/huishouden/accept", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "gebruiker": selectedInvite,
        })
    })
        .then((res) => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then((data) => {
            console.log("Uitnodiging geaccepteerd:", data);
        })
        .catch((error) => {
            console.error('Fout bij accepteren van uitnodiging:', error);
        });
}

function declineInvite(selectedInvite) {
    fetch("https://tests-1718633149689.azurewebsites.net/eet-share/huishouden/decline", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "gebruiker": selectedInvite,
        })
    })
        .then((res) => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then((data) => {
            console.log("Uitnodiging geweigerd:", data);
        })
        .catch((error) => {
            console.error('Fout bij weigeren van uitnodiging:', error);
        });
}
