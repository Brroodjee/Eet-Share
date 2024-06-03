document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.querySelector("#createAccount");
    console.log(registerForm)
    registerForm.addEventListener("submit", postData);
    function postData(event){
        event.preventDefault();
        console.log({
            "name": document.querySelector("#username").value,
            "email": document.querySelector("#email").value,
            "wachtwoord": document.querySelector("#passwordField").value,
        })

        const formData = new FormData(registerForm);
        fetch("http://localhost:4711/eet-share/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                "naam": document.querySelector("#username").value,
                "email": document.querySelector("#email").value,
                "wachtwoord": document.querySelector("#passwordField").value,
            })

        }).then((res)=>{
            if(res.ok){
                return res.json()
            }
        }).then((data)=>{
            console.log(data)
        }).catch((error)=>{
            console.log(error)
        })
    }
})
