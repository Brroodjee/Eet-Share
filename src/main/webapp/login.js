import LoginService from "./login-service.js";

let service = new LoginService();

document.getElementById('login_account').addEventListener('submit', e => {
    e.preventDefault();
    const usernameOrEmail = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    service.login(usernameOrEmail, password).then(() => {
        window.location.reload();
    }).catch(error => {
        console.error('Login failed:', error);
        document.querySelector('.form__message--error').textContent = 'Login failed';
    });
});
