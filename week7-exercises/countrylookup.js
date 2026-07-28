const countryInput = document.querySelector("#countryInput");
const lookupBtn = document.querySelector("#lookupBtn");
const result = document.querySelector("#result");

lookupBtn.addEventListener("click", async () => {
  const countryName = countryInput.value;

  if (countryName === "") {
    result.textContent = "Please enter a country name first.";
    return;
  }

  // Show a loading message while fetching data
  result.textContent = "Loading...";

  try {
    const response = await fetch(
      `https://restcountries.com/v3.1/name/${countryName}`,
    );

    // Check if the request worked
    if (!response.ok) {
      throw new Error("Country not found");
    }

    // Convert the response to JSON
    const data = await response.json();
    const country = data[0];

    // Pull out the details we want to display
    const capital = country.capital[0];
    const region = country.region;
    const population = country.population;

    // Display the country information on the page
    result.innerHTML = `
      <h3>${country.name.common}</h3>
      <p>Capital: ${capital}</p>
      <p>Region: ${region}</p>
      <p>Population: ${population}</p>
    `;
  } catch (error) {
    result.textContent = "Could not find country. Please check spelling.";
  }
});
