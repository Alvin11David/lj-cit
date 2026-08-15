// =============================================
// Week 9 Exercise — TypeScript Version of Week 7
// CiT Directory + Country Lookup with types
// =============================================


// -----------------------------------------------
// INTERFACES — describe the shape of our API data
// -----------------------------------------------

// shape of one user from jsonplaceholder
interface Address {
  city: string;
}

interface User {
  id: number;
  name: string;
  email: string;
  address: Address;
}

// shape of one country from countries.dev
interface Country {
  name: string;
  capital: string;
  region: string;
  population: number;
  flag: string;
}


// -----------------------------------------------
// DOM REFERENCES — typed and null checked
// -----------------------------------------------

// country lookup elements
const countryInput  = document.querySelector("#countryInput") as HTMLInputElement;
const searchBtn     = document.querySelector("#searchBtn")    as HTMLButtonElement;
const countryStatus = document.querySelector("#countryStatus") as HTMLParagraphElement;
const countryResult = document.querySelector("#countryResult") as HTMLDivElement;

// directory elements
const directoryStatus = document.querySelector("#directoryStatus") as HTMLParagraphElement;
const userCards       = document.querySelector("#userCards")       as HTMLDivElement;

// null check — if any element is missing, stop early
if (!countryInput || !searchBtn || !countryStatus || !countryResult || !directoryStatus || !userCards) {
  console.log("One or more elements not found on the page");
}


// -----------------------------------------------
// HELPER
// -----------------------------------------------

// get initials from a full name e.g. "Alice Nakato" -> "AN"
function getInitials(name: string): string {
  const parts = name.split(" ");
  return parts[0][0] + parts[1][0];
}


// -----------------------------------------------
// COUNTRY LOOKUP — typed async function
// -----------------------------------------------

async function searchCountry(): Promise<void> {
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

    const data = (await response.json()) as Country[];

    if (!data || data.length === 0) {
      throw new Error("Country not found");
    }

    const country: Country = data[0];

    countryStatus.textContent = "";

    (document.querySelector("#countryFlag") as HTMLSpanElement).textContent   = country.flag;
    (document.querySelector("#countryName") as HTMLHeadingElement).textContent = country.name;
    (document.querySelector("#countryCapital") as HTMLSpanElement).textContent = country.capital || "N/A";
    (document.querySelector("#countryRegion") as HTMLSpanElement).textContent  = country.region;
    (document.querySelector("#countryPop") as HTMLSpanElement).textContent     = country.population
      ? country.population.toLocaleString()
      : "N/A";

    countryResult.classList.remove("hide");

  } catch (error) {
    countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
    countryStatus.classList.add("error");
    countryResult.classList.add("hide");
  }
}

// button click
searchBtn.addEventListener("click", searchCountry);

// enter key
countryInput.addEventListener("keydown", (event: KeyboardEvent) => {
  if (event.key === "Enter") {
    searchCountry();
  }
});


// -----------------------------------------------
// CIT DIRECTORY — typed async function
// -----------------------------------------------

async function loadDirectory(): Promise<void> {
  directoryStatus.textContent = "Loading...";

  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/users");

    if (!response.ok) {
      throw new Error("Server error: " + response.status);
    }

    const users = (await response.json()) as User[];

    directoryStatus.textContent = "";

    users.forEach((user: User) => {
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

// load directory on page open
loadDirectory();