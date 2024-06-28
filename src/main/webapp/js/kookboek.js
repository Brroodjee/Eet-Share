const token = window.sessionStorage.getItem("myJWT");

let recipes = [];

document.addEventListener("DOMContentLoaded", function() {
    fetchRecipes();
});

function fetchRecipes() {
    fetch('/eet-share/kookboek', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            recipes = data.recepten;
            populateDatalist();
        })
        .catch(error => console.error('Error fetching recipes:', error));
}

function populateDatalist() {
    const datalist = document.getElementById("recipe-suggestions");
    datalist.innerHTML = "";
    recipes.forEach(recipe => {
        const option = document.createElement("option");
        option.value = recipe.receptNaam;
        datalist.appendChild(option);
    });
}

function showSuggestions(query) {
    const suggestions = recipes.filter(recipe => recipe.receptNaam.toLowerCase().includes(query.toLowerCase()));
    const datalist = document.getElementById("recipe-suggestions");
    datalist.innerHTML = "";
    suggestions.forEach(recipe => {
        const option = document.createElement("option");
        option.value = recipe.receptNaam;
        datalist.appendChild(option);
    });
}

function searchRecipe() {

    const query = document.getElementById("search").value;
    const result = recipes.find(recipe => recipe.receptNaam.toLowerCase() === query.toLowerCase());
    if (result) {
        fillForm(result);
    } else {
        alert("No recipes found.");
    }
}

function fillForm(recipe) {
    document.getElementById("dish-name").value = recipe.receptNaam;
    document.getElementById("serving-text").value = recipe.servings;
    document.getElementById("cooking-text").value = recipe.cookingTime;
    document.getElementById("prep-text").value = recipe.preppingTime;
    document.getElementById("instructions-text").value = recipe.beschrijving;
}