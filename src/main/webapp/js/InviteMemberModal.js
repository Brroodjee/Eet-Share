const inviteMember = document.getElementById("inviteModal");

// Get the button that opens the createHouseholdModal
const Btn = document.getElementById("inviteMember");

// Get the <span> element that closes the createHouseholdModal
const span2 = document.getElementById("span2");

// When the user clicks the button, open the createHouseholdModal
Btn.onclick = function() {
    inviteMember.style.display = "block";
}

// When the user clicks on <span> (x), close the createHouseholdModal
span2.onclick = function() {
    inviteMember.style.display = "none";
}

// When the user clicks anywhere outside of the createHouseholdModal, close it
window.onclick = function(event) {
    if (event.target == inviteMember) {
        inviteMember.style.display = "none";
    }
}
// https://www.w3schools.com/howto/howto_css_modals.asp

function DropdownGebruikers() {
    console.log("DropdownGebruikers() function called");

    fetch("https://tests-1718633149689.azurewebsites.net/eet-share/huishouden/gebruikers", {
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

document.addEventListener('DOMContentLoaded', DropdownGebruikers);

document.addEventListener("DOMContentLoaded", () => {
    const inviteForm = document.querySelector("#invite__form-create");
    console.log(inviteForm);
    inviteForm.addEventListener("submit", postData);

    function postData(event) {
        event.preventDefault();
        const selectedUser = document.querySelector("#invite__dropdown").value;
        console.log({
            "gebruiker": selectedUser
        });

        fetch("https://tests-1718633149689.azurewebsites.net/eet-share/huishouden/invite", {
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
});
