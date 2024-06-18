import LoginService from "./login-service.js";

const service = new LoginService();

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('createAccount').addEventListener("submit", event => {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('passwordField').value;

        const jsonRequestBody = { username: username, password: password };

        fetch("https://tests-1718633149689.azurewebsites.net/eet-share/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jsonRequestBody)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
            })
            .then(myJson => {
                window.sessionStorage.setItem("myJWT", myJson.Jwt);
                window.location.href = '/html/homepage.html';
            })
            .catch(error => {
                console.error('Registration failed:', error);
                document.querySelector('.form__message--error').textContent = 'Registration failed: ' + error.message;
            });
    });
});
