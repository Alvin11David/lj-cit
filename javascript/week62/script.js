
const input = document.querySelector("#taskInput");
const addBtn = document.querySelector("#addBtn");
const list = document.querySelector("#taskList");
const taskCount = document.querySelector("#taskCount");

function updateCount() {
  const total = list.querySelectorAll("li").length;
  const done = list.querySelectorAll("li.done").length;
  const remaining = total - done;
  taskCount.textContent = remaining + " tasks remaining";
}

function addTask() {
  const text = input.value;

  if (text === "") {
    alert("Please type a task first");
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

  updateCount();
}

addBtn.addEventListener("click", addTask);

input.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    addTask();
  }
});