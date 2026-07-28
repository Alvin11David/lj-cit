const themebutton =  document.querySelector("themebutton");
themebutton.addEventListener("click", () => {
  document.body.classList.toggle("dark-mode");
});