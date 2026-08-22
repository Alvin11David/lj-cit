// Week 10 Exercise - Tailwind CSS
// Same JS logic as Week 7 — only the HTML styling changed


// =============================================
// COUNTRY LOOKUP
// =============================================

const countryInput  = document.querySelector("#countryInput");
const searchBtn     = document.querySelector("#searchBtn");
const countryStatus = document.querySelector("#countryStatus");
const countryResult = document.querySelector("#countryResult");

async function searchCountry() {
  const typed = countryInput.value.trim();

  if (typed === "") {
    alert("Please type a country name first");
    return;
  }

  // loading state
  countryStatus.textContent = "Loading...";
  countryStatus.classList.remove("text-red-500");
  countryStatus.classList.add("text-gray-400");
  countryResult.classList.add("hidden");

  try {
    const response = await fetch("https://countries.dev/name/" + typed);

    if (!response.ok) {
      throw new Error("Country not found");
    }

    const data = await response.json();

    if (!data || data.length === 0) {
      throw new Error("Country not found");
    }

    const country = data[0];

    countryStatus.textContent = "";

    document.querySelector("#countryFlag").textContent    = country.flag || "🏳️";
    document.querySelector("#countryName").textContent    = country.name;
    document.querySelector("#countryCapital").textContent = country.capital || "N/A";
    document.querySelector("#countryRegion").textContent  = country.region || "N/A";
    document.querySelector("#countryPop").textContent     = country.population
      ? country.population.toLocaleString()
      : "N/A";

    countryResult.classList.remove("hidden");

  } catch (error) {
    countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
    countryStatus.classList.remove("text-gray-400");
    countryStatus.classList.add("text-red-500");
    countryResult.classList.add("hidden");
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


// =============================================
// CIT DIRECTORY
// =============================================

const directoryStatus = document.querySelector("#directoryStatus");
const userCards       = document.querySelector("#userCards");

function getInitials(name) {
  const parts = name.split(" ");
  return parts[0][0] + parts[1][0];
}

async function loadDirectory() {
  directoryStatus.textContent = "Loading...";

  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/users");

    if (!response.ok) {
      throw new Error("Server error: " + response.status);
    }

    const users = await response.json();

    directoryStatus.textContent = "";

    users.forEach((user) => {
      const card = document.createElement("div");

      // Tailwind classes applied via JS for dynamically created cards
      card.className = "bg-white rounded-xl shadow p-6 hover:-translate-y-1 transition-transform";

      card.innerHTML = `
        <div class="w-11 h-11 rounded-full bg-cit-navy text-white text-base font-bold flex items-center justify-center mb-4">
          ${getInitials(user.name)}
        </div>
        <p class="text-base font-bold text-gray-800 mb-2">${user.name}</p>
        <p class="text-sm text-gray-400 mb-1">✉️ ${user.email}</p>
        <p class="text-sm text-gray-400">📍 ${user.address.city}</p>
      `;

      userCards.appendChild(card);
    });

  } catch (error) {
    directoryStatus.textContent = "Could not load the directory. Please try again later.";
    directoryStatus.classList.add("text-red-500");
  }
}

loadDirectory();