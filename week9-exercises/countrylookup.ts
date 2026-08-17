interface Country {
  name: string;
  capital: string;
  region: string;
  population: number;
}

const countryInput  = document.querySelector("#countryInput") as HTMLInputElement;
const searchBtn     = document.querySelector("#searchBtn") as HTMLButtonElement;
const countryStatus = document.querySelector("#countryStatus") as HTMLElement;
const countryResult = document.querySelector("#countryResult") as HTMLElement;

const countryCapital = document.querySelector("#countryCapital") as HTMLElement;
const countryRegion  = document.querySelector("#countryRegion") as HTMLElement;
const countryPop     = document.querySelector("#countryPop") as HTMLElement;

async function searchCountry(): Promise<void> {
  const typed: string = countryInput.value.trim();

  if (typed === "") {
    alert("Please type a country name first");
    return;
  }

  countryStatus.textContent = "Loading...";
  countryStatus.classList.remove("error");
  countryResult.classList.add("hide");

  try {
    const response: Response = await fetch("https://countries.dev/name/" + typed);

    if (!response.ok) {
      throw new Error("Country not found");
    }

    const data: Country[] = await response.json();

    if (!data || data.length === 0) {
      throw new Error("Country not found");
    }

    const country: Country = data[0];

    countryStatus.textContent = "";

    countryCapital.textContent = country.capital || "N/A";
    countryRegion.textContent  = country.region || "N/A";
    countryPop.textContent     = country.population
      ? country.population.toLocaleString()
      : "N/A";

    countryResult.classList.remove("hide");

  } catch (error) {
    countryStatus.textContent = "Could not find that country. Check your spelling and try again.";
    countryStatus.classList.add("error");
    countryResult.classList.add("hide");
  }
}

searchBtn.addEventListener("click", searchCountry);

countryInput.addEventListener("keydown", (event: KeyboardEvent) => {
  if (event.key === "Enter") {
    searchCountry();
  }
});
