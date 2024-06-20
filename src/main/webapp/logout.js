import LoginService from "./login-service.js";

let service = new LoginService();

document.forms.logout.addEventListener('submit', e => {
    e.preventDefault();
    service.logout().then(() => {
        window.location.href = "../index.html"; // Verwijs naar de loginpagina na logout
    });
});

function refresh() {
    if (service.isLoggedIn()) {
        document.forms.logout.style = "display:block";
    } else {
        document.forms.logout.style = "display:none";
    }
}

refresh();

service.getUser().then(user => {
    if (!user) {
        service.logout();
    } else {
        document.getElementById("user").textContent = user.username;
    }
    refresh();
});
