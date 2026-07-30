// =============================================
// CiT Student Grade Portal — app.js
// Week 8 Capstone
// =============================================

// API base URL — change this to the real backend when it is ready
const API_URL = "http://localhost:8080/api/students";

// -----------------------------------------------
// MOCK DATA — used while backend is not yet ready
// remove this block and set USE_MOCK = false
// once the real API is running
// -----------------------------------------------
const USE_MOCK = true;

let mockStudents = [
  { id: 1, name: "Alice Nakato",  regNumber: "2024001", gpa: 3.75 },
  { id: 2, name: "Bob Okello",    regNumber: "2024002", gpa: 3.10 },
  { id: 3, name: "Carol Auma",    regNumber: "2024003", gpa: 2.85 },
  { id: 4, name: "David Ssekandi",regNumber: "2024004", gpa: 3.50 },
];

let mockNextId = 5;

// mock versions of the API calls
const mock = {
  getAll: () => new Promise((resolve) => setTimeout(() => resolve([...mockStudents]), 3000)),  add: (student) => {
    const newStudent = { id: mockNextId++, ...student };
    mockStudents.push(newStudent);
    return Promise.resolve(newStudent);
  },
  update: (id, updated) => {
    mockStudents = mockStudents.map((s) =>
      s.id === id ? { ...s, ...updated } : s
    );
    return Promise.resolve();
  },
  delete: (id) => {
    mockStudents = mockStudents.filter((s) => s.id !== id);
    return Promise.resolve();
  },
};

// -----------------------------------------------
// SELECT ELEMENTS
// -----------------------------------------------
const studentList = document.querySelector("#studentList");
const addForm     = document.querySelector("#addForm");
const editModal   = document.querySelector("#editModal");
const editForm    = document.querySelector("#editForm");
const cancelEdit  = document.querySelector("#cancelEdit");

// track which student is being edited
let editingId = null;

// -----------------------------------------------
// SECTION 4 — READ: load and display students
// -----------------------------------------------
async function loadStudents() {

  // loading state
  studentList.innerHTML = `<li class="msg-row">Loading students...</li>`;

  try {
    let students;

    if (USE_MOCK) {
      students = await mock.getAll();
    } else {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Failed to load");
      students = await response.json();
    }

    // empty state
    if (students.length === 0) {
      studentList.innerHTML = `<li class="msg-row">No students yet — add one above.</li>`;
      return;
    }

    // success — clear then render each student
    studentList.innerHTML = "";
    students.forEach(renderStudent);

  } catch (error) {
    // error state
    studentList.innerHTML = `<li class="msg-row error">Could not load students. Please try again.</li>`;
  }
}

// build one student row
function renderStudent(student) {
  const li = document.createElement("li");
  li.classList.add("student-row");

  li.innerHTML = `
    <div class="student-info">
      <span class="student-name">${student.name}</span>
      <span class="student-meta">${student.regNumber} &mdash; GPA: ${student.gpa}</span>
    </div>
    <div class="row-btns">
      <button class="btn-edit">Edit</button>
      <button class="btn-delete">Delete</button>
    </div>
  `;

  // wire up edit and delete buttons
  li.querySelector(".btn-edit").addEventListener("click", () => openEditModal(student));
  li.querySelector(".btn-delete").addEventListener("click", () => deleteStudent(student.id));

  studentList.appendChild(li);
}

// -----------------------------------------------
// SECTION 5 — CREATE: add a new student (POST)
// -----------------------------------------------
addForm.addEventListener("submit", async (event) => {
  event.preventDefault();

  const newStudent = {
    name:      document.querySelector("#name").value,
    regNumber: document.querySelector("#reg").value,
    gpa:       Number(document.querySelector("#gpa").value),
  };

  try {
    if (USE_MOCK) {
      await mock.add(newStudent);
    } else {
      await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newStudent),
      });
    }

    addForm.reset();
    loadStudents();

  } catch (error) {
    alert("Could not add student. Please try again.");
  }
});

// -----------------------------------------------
// SECTION 6 — DELETE: remove a student
// -----------------------------------------------
async function deleteStudent(id) {
  const ok = confirm("Are you sure you want to delete this student?");
  if (!ok) return;

  try {
    if (USE_MOCK) {
      await mock.delete(id);
    } else {
      await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    }

    loadStudents();

  } catch (error) {
    alert("Could not delete student. Please try again.");
  }
}

// -----------------------------------------------
// SECTION 6 — UPDATE: edit a student (PUT)
// -----------------------------------------------
function openEditModal(student) {
  editingId = student.id;
  document.querySelector("#editName").value = student.name;
  document.querySelector("#editReg").value  = student.regNumber;
  document.querySelector("#editGpa").value  = student.gpa;
  editModal.classList.remove("hide");
}

function closeEditModal() {
  editingId = null;
  editModal.classList.add("hide");
  editForm.reset();
}

editForm.addEventListener("submit", async (event) => {
  event.preventDefault();

  const updated = {
    name:      document.querySelector("#editName").value,
    regNumber: document.querySelector("#editReg").value,
    gpa:       Number(document.querySelector("#editGpa").value),
  };

  try {
    if (USE_MOCK) {
      await mock.update(editingId, updated);
    } else {
      await fetch(`${API_URL}/${editingId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updated),
      });
    }

    closeEditModal();
    loadStudents();

  } catch (error) {
    alert("Could not update student. Please try again.");
  }
});

// close modal on cancel
cancelEdit.addEventListener("click", closeEditModal);

// close modal if user clicks outside the box
editModal.addEventListener("click", (event) => {
  if (event.target === editModal) {
    closeEditModal();
  }
});

// -----------------------------------------------
// Start — load students when the page opens
// -----------------------------------------------
loadStudents();