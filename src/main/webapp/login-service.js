export default class LoginService {
    isLoggedIn() {
        return window.sessionStorage.getItem("myJWT") !== null;
    }

    login(user, password) {
        const jsonRequestBody = { username: user, password: password };
        return fetch("/eet-share/login", {
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
                window.location.href = "/html/homepage.html";
                console.log(myJson)
                return myJson;
            });
    }

    getUser() {
        const token = window.sessionStorage.getItem("myJWT");
        if (!token) {
            return Promise.reject("No token found");
        }

        return fetch("http://localhost:4711/eet-share/users", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Failed to get user info");
                }
            });
    }

    logout() {
        window.sessionStorage.removeItem("myJWT");
        return Promise.resolve();
    }
}