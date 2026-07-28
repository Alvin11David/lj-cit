const courses = [
  {
    name: "Frontend Development Course",
    track: "frontend",
    weeks: [
      "Week 1: Foundations",
      "Week 2: Git & Github basics",
      "Week 3: CSS & HTML introduction",
      "Week 4 - 5: CSS & HTML",
      "Week 6 - 7: Javascript"
    ]
  },
  {
    name: "Backend Development Course",
    track: "backend",
    weeks: [
      "Week 1: Foundations",
      "Week 2: Git & Github basics",
      "Week 3: Java",
      "Week 4 - 5: Java",
      "Week 6 - 7: Databases"
    ]
  },
  {
    name: "Graphics Design Course",
    track: "graphics",
    weeks: []
  }
];

function displayCourses(courseList) {
  const listElement = document.querySelector("#course-list");
  listElement.innerHTML = "";

  courseList.forEach((course) => {
    let weeksHTML = "";
    if (course.weeks.length > 0) {
      course.weeks.forEach((week) => {
        weeksHTML += `<li>${week}</li>`;
      });
      weeksHTML = `<ul>${weeksHTML}</ul>`;
    }

    listElement.innerHTML += `
      <li class="${course.track}">
        <strong>${course.name}</strong>
        ${weeksHTML}
      </li>
    `;
  });
}

function filterCourses(track) {
  if (track === "all") {
    displayCourses(courses);
  } else {
    const filtered = courses.filter((course) => course.track === track);
    displayCourses(filtered);
  }
}

document.querySelector("#frontendBtn").addEventListener("click", () => filterCourses("frontend"));
document.querySelector("#backendBtn").addEventListener("click", () => filterCourses("backend"));
document.querySelector("#graphicsBtn").addEventListener("click", () => filterCourses("graphics"));
document.querySelector("#allBtn").addEventListener("click", () => filterCourses("all"));

displayCourses(courses);