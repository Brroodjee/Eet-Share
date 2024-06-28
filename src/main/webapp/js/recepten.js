const token = window.sessionStorage.getItem("myJWT");

document.addEventListener("DOMContentLoaded", () => {
    const receptForm = document.querySelector("#recept-form");

    receptForm.addEventListener("submit", function(event) {
        const progressBarHuishouden = document.querySelector(".progressBar");

        event.preventDefault();

        const dishName = document.querySelector("#dish-name").value;
        const servings = document.querySelector("#serving-text").value;
        const cookingTime = document.querySelector("#cooking-text").value;
        const prepTime = document.querySelector("#prep-text").value;
        const instructions = document.querySelector("#instructions-text").value;

        const submitButton = event.submitter.id;

        if (submitButton === "submitbutton") {
            createRecept(dishName, servings, cookingTime, prepTime, instructions);

            console.log("sluit na 5 sec")
            progressBarHuishouden.style.width = "100%";
            setTimeout(() => {
                location.reload()
            }, 5000);
        }
    })
})

function createRecept(dishName, servings, cookingTime, prepTime, instructions) {
    fetch("/eet-share/recepten/testing", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "dishName": dishName,
            "servings": servings,
            "cookingTime": cookingTime,
            "prepTime": prepTime,
            "instructions": instructions,
        })
    })
        .then((res ) => {
            if (!res.ok) {
                throw new Error("HTTP error! ")
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
