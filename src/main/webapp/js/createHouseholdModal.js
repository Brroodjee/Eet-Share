const createHouseholdModal = document.getElementById("myModal");

// Get the button that opens the createHouseholdModal
const btn = document.getElementById("myBtn");

// Get the <span> element that closes the createHouseholdModal
const span1 = document.getElementById("span1");

// When the user clicks the button, open the createHouseholdModal
btn.onclick = function() {
    createHouseholdModal.style.display = "block";
}

// When the user clicks on <span> (x), close the createHouseholdModal
span1.onclick = function() {
    createHouseholdModal.style.display = "none";
}

// https://www.w3schools.com/howto/howto_css_modals.asp