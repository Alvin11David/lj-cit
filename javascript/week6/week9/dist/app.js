"use strict";
// =============================================
// Week 9 Exercise — TypeScript Version of Week 7
// CiT Directory + Country Lookup with types
// =============================================
// -----------------------------------------------
// DOM REFERENCES — typed and null checked
// -----------------------------------------------
// country lookup elements
const countryInput = document.querySelector("#countryInput");
const searchBtn = document.querySelector("#searchBtn");
const countryStatus = document.querySelector("#countryStatus");
const countryResult = document.querySelector("#countryResult");
// directory elements
const directoryStatus = document.querySelector("#directoryStatus");
const userCards = document.querySelector("#userCards");
// null check — if any element is missing, stop early
if (!countryInput || !searchBtn || !countryStatus || !countryResult || !directoryStatus || !userCards) {
    console.log("One or more elements not found on the page");
}
// -----------------------------------------------
// HELPER
// -----------------------------------------------
// get initials from a full name e.g. "Alice Nakato" -> "AN"
function getInitials(name) {
    const parts = name.split(" ");
    return parts[0][0] + parts[1][0];
}
// -----------------------------------------------
// COUNTRY LOOKUP — typed async function
// -----------------------------------------------
async function searchCountry() {
    const typed = countryInput.value.trim();
    if (typed === "") {
        alert("Please type a country name first");
        return;
    }
    countryStatus.textContent = "Loading...";
    countryStatus.classList.remove("error");
    countryResult.classList.add("hide");
    try {
        const response = await fetch("https://countries.dev/name/" + typed);
        if (!response.ok) {
            throw new Error("Country not found");
        }
        const data = (await response.json());
        if (!data || data.length === 0) {
            throw new Error("Country not found");
        }
        const country = data[0];
        countryStatus.textContent = "";
        document.querySelector("#countryFlag").textContent = country.flag;
        document.querySelector("#countryName").textContent = country.name;
        document.querySelector("#countryCapital").textContent = country.capital || "N/A";
        document.querySelector("#countryRegion").textContent = country.region;
        document.querySelector("#countryPop").textContent = country.population
            ? country.population.toLocaleString()
            : "N/A";
        countryResult.classList.remove("hide");
    }
    catch (error) {
        countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
        countryStatus.classList.add("error");
        countryResult.classList.add("hide");
    }
}
// button click
searchBtn.addEventListener("click", searchCountry);
// enter key
countryInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        searchCountry();
    }
});
// -----------------------------------------------
// CIT DIRECTORY — typed async function
// -----------------------------------------------
async function loadDirectory() {
    directoryStatus.textContent = "Loading...";
    try {
        const response = await fetch("https://jsonplaceholder.typicode.com/users");
        if (!response.ok) {
            throw new Error("Server error: " + response.status);
        }
        const users = (await response.json());
        directoryStatus.textContent = "";
        users.forEach((user) => {
            const card = document.createElement("div");
            card.classList.add("user-card");
            card.innerHTML = `
        <div class="user-avatar">${getInitials(user.name)}</div>
        <p class="user-name">${user.name}</p>
        <p class="user-email">✉️ ${user.email}</p>
        <p class="user-city">📍 ${user.address.city}</p>
      `;
            userCards.appendChild(card);
        });
    }
    catch (error) {
        directoryStatus.textContent = "Could not load the directory. Please try again later.";
        directoryStatus.classList.add("error");
    }
}
// load directory on page open
loadDirectory();
