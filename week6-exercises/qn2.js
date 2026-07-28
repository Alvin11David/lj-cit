const input = document.querySelector("#taskInput");
const addBtn = document.querySelector("#addBtn");
const list = document.querySelector("#taskList");
const taskCount = document.querySelector("#taskCount");

function updateCount() {
  const remainingTasks = list.querySelectorAll("li:not(.done)").length;
  taskCount.textContent = remainingTasks;
}

function addTask() {
  const text = input.value.trim();

  if (text === "") {
    alert("Please type a task first.");
    return;
  }

  const li = document.createElement("li");
  li.textContent = text;

  li.addEventListener("click", () => {
    li.classList.toggle("done");
    updateCount();
  });

  list.appendChild(li);
  input.value = "";
  input.focus();
  updateCount();
}

addBtn.addEventListener("click", addTask);

input.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    event.preventDefault();
    addTask();
  }
});

updateCount();
