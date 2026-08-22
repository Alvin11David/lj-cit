const API_URL = "https://app-production-d3cc.up.railway.app/students";

const studentList = document.querySelector("#studentList");
const statusEl = document.querySelector("#status");
const form = document.querySelector("#addForm");

async function loadStudents() {
  //Show loading state
  statusEl.textContent = "Loading students...";
  studentList.innerHTML = "";

  try {
    const response = await fetch(API_URL);
    if (!response.ok) {
      throw new Error("Failed to load students");
    }

    const json = await response.json();
    const students = json.data || [];

    if (students.length === 0) {
      statusEl.textContent = "No students yet — add one above.";
      return;
    }

    // 6. Clear status and render each student
    statusEl.textContent = `Showing ${students.length} student(s)`;
    students.forEach(renderStudent);

  } catch (error) {
    statusEl.textContent = "Could not load students. Please try again.";
    console.log("Error:", error);
  }
}


function renderStudent(student) {
  //Create the list item
  const li = document.createElement("li");
  li.classList.add(
    "bg-white",
    "rounded-lg",
    "shadow",
    "border-t-4",
    "border-cit-blue",
    "p-6"
  );

  //Fill it with content using a template literal
  li.innerHTML = `
    <p class="text-lg font-bold text-gray-800">${student.name}</p>
    <p class="text-sm text-gray-500">Reg Number: ${student.regNumber}</p>
    <p class="text-sm text-gray-500">Course: ${student.course}</p>
    <p class="mt-2 text-sm text-gray-700">
      Marks:
      <span class="font-semibold text-cit-navy">${student.marks}</span>
    </p>
  `;

  // 3. Attach it to the list
  studentList.appendChild(li);
}


// ADD STUDENT
form.addEventListener("submit", async (event) => {
  event.preventDefault(); // stop the page from reloading

  const newStudent = {
    name: document.querySelector("#name").value,
    course: document.querySelector("#course").value,
    marks: Number(document.querySelector("#marks").value),
  };

  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newStudent),
    });

    if (!response.ok) {
      throw new Error("Failed to add student");
    }

    form.reset();
    loadStudents();

  } catch (error) {
    statusEl.textContent = "Could not add student. Please try again.";
    console.log("Error:", error);
  }
});


loadStudents();