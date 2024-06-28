const token = window.sessionStorage.getItem("myJWT");

document.addEventListener('DOMContentLoaded', (event) => {
    fetch('/eet-share/users/ingelogdeUser', {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            const role = data[0].role;

            const links = {
                huishouden: document.getElementById('huishouden-link'),
                uitnodigen: document.getElementById('uitnodigen-link'),
                accepteren: document.getElementById('accepteren-link'),
                kookboek: document.getElementById('kookboek-link'),
                recepten: document.getElementById('recepten-link'),
                producten: document.getElementById('producten-link'),
                boodschappen: document.getElementById('boodschappen-link'),
                favorieten: document.getElementById('favorieten-link')
            };

            if (role === 'gebruiker') {
                Object.keys(links).forEach(key => {
                    if (key !== 'huishouden') {
                        links[key].classList.add('disabled-link');
                        links[key].href = '#';
                    }
                });
            }
        })
        .catch(error => console.error('Error fetching user role:', error));
});