const token = window.sessionStorage.getItem("myJWT");

document.addEventListener("DOMContentLoaded", () => {
    const receptForm = document.querySelector("#recept-form");

    receptForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const dishName = document.querySelector("#dish-name").value;
        const servings = document.querySelector("#serving-text").value;
        const cookingTime = document.querySelector("#cooking-text").value;
        const prepTime = document.querySelector("#prep-text").value;
        const instructions = document.querySelector("#instructions-text").value;

        const submitButton = event.submitter.id;

        if (submitButton === "submitbutton") {
            createRecept(dishName, servings, cookingTime, prepTime, instructions);
            console.log(dishName)
            console.log(servings)
            console.log(cookingTime)
            console.log(prepTime)
            console.log(instructions)
        }
    })
})

function createRecept(dishName, servings, cookingTime, prepTime, instructions) {
    fetch("https://tests-1718633149689.azurewebsites.net/eet-share/recepten/testing", {
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
