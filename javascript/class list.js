// Week 5 Exercise - Process a Class List

const students = [
  { name: "Alice", score: 72 },
  { name: "Bob", score: 45 },
  { name: "Carol", score: 88 },
  { name: "David", score: 39 },
  { name: "Esther", score: 61 },
];

students.forEach((student) => {
  console.log(student.name + ": " + student.score);
});

const passers = students.filter((student) => student.score >= 50);
console.log("Passed:", passers);

const names = students.map((student) => student.name);
console.log("All names:", names);

let total = 0;
students.forEach((student) => {
  total = total + student.score;
});
const average = total / students.length;
console.log("Class average:", average);