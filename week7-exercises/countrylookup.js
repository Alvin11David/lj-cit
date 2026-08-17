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

  countryStatus.textContent = "Loading...";
  countryStatus.classList.remove("error");
  countryResult.classList.add("hide");

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

    document.querySelector("#countryCapital").textContent = country.capital || "N/A";
    document.querySelector("#countryRegion").textContent  = country.region || "N/A";
    document.querySelector("#countryPop").textContent     = country.population ? country.population.toLocaleString() : "N/A";

    countryResult.classList.remove("hide");

  } catch (error) {
    countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
    countryStatus.classList.add("error");
    countryResult.classList.add("hide");
  }
}

searchBtn.addEventListener("click", searchCountry);

countryInput.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    searchCountry();
  }
});
