// Week 7 - Fetch and APIs
// Exercise 1: Country Lookup
// Homework 2: CiT Directory


const countryInput  = document.querySelector("#countryInput");
const searchBtn     = document.querySelector("#searchBtn");
const countryStatus = document.querySelector("#countryStatus");
const countryResult = document.querySelector("#countryResult");

async function searchCountry() {
  const typed = countryInput.value.trim();

  // guard against empty input
  if (typed === "") {
    alert("Please type a country name first");
    return;
  }

  // show loading before the fetch
  countryStatus.textContent = "Loading...";
  countryStatus.classList.remove("error");
  countryResult.classList.add("hide");

  try {
    const response = await fetch("https://countries.dev/name/" + typed);

    // check if the server returned a valid response
    if (!response.ok) {
      throw new Error("Country not found");
    }

    const data = await response.json();

    // countries.dev returns an array directly
    if (!data || data.length === 0) {
      throw new Error("Country not found");
    }

    const country = data[0];

    // clear loading message
    countryStatus.textContent = "";

    // fill in the result fields
    document.querySelector("#countryFlag").textContent    = country.flag || "🏳️";
    document.querySelector("#countryName").textContent    = country.name;
    document.querySelector("#countryCapital").textContent = country.capital || "N/A";
    document.querySelector("#countryRegion").textContent  = country.region || "N/A";
    document.querySelector("#countryPop").textContent     = country.population ? country.population.toLocaleString() : "N/A";

    // show the result box
    countryResult.classList.remove("hide");

  } catch (error) {
    countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
    countryStatus.classList.add("error");
    countryResult.classList.add("hide");
  }
}

// button click
searchBtn.addEventListener("click", searchCountry);

// enter key also works
countryInput.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    searchCountry();
  }
});

// HOMEWORK 2 - CiT Directory

const directoryStatus = document.querySelector("#directoryStatus");
const userCards       = document.querySelector("#userCards");

// get the first two initials from a name e.g. "Alice Nakato" -> "AN"
function getInitials(name) {
  const parts = name.split(" ");
  return parts[0][0] + parts[1][0];
}

async function loadDirectory() {
  // show loading before the fetch
  directoryStatus.textContent = "Loading...";

  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/users");

    // check response
    if (!response.ok) {
      throw new Error("Server error: " + response.status);
    }

    const users = await response.json();

    // clear loading message
    directoryStatus.textContent = "";

    // build one card for each user
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

  } catch (error) {
    directoryStatus.textContent = "Could not load the directory. Please try again later.";
    directoryStatus.classList.add("error");
  }
}
loadDirectory();