document.addEventListener("DOMContentLoaded", () => {
    const inviteMember = document.getElementById("inviteModal");
    const Btn = document.getElementById("inviteMember");
    const span2 = document.getElementById("span2");

    Btn.onclick = function() {
        inviteMember.style.display = "block";
    };

    span2.onclick = function() {
        inviteMember.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target == inviteMember) {
            inviteMember.style.display = "none";
        }
    };

    DropdownGebruikers();
});

function DropdownGebruikers() {
    console.log("DropdownGebruikers() function called");

    fetch("/eet-share/users/gebruikers", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            const dropdownGebruikers = document.querySelector('#invite__dropdown');
            dropdownGebruikers.innerHTML = "";

            data.forEach(gebruiker => {
                const option = document.createElement('option');
                option.value = gebruiker.username;
                option.text = gebruiker.username;
                dropdownGebruikers.add(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    const inviteForm = document.querySelector("#invite__form-create");
    const inviteMember = document.querySelector("#inviteModal");
    const responseMessageInvite = inviteMember.querySelector(".responseMessage");
    const progressBarInvite = inviteMember.querySelector(".progressBar");

    inviteForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const selectedUser = document.querySelector("#invite__dropdown").value;
        const submitBtn = event.submitter.id;

        if (submitBtn === "invite__modal-button") {
            inviteUser(selectedUser);
            responseMessageInvite.innerText = selectedUser + " is uitgenodigd voor uw huishouden!"
            responseMessageInvite.style.color = "green";
            progressBarInvite.style.width = "100%";
            setTimeout(() => {
                location.reload()
            }, 5000);
        }
    })
});

function inviteUser(selectedUser) {
    fetch("/eet-share/huishouden/invite", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "gebruiker": selectedUser,
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
