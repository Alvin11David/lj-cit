const students = [
  { name: "Alice",   score: 78 },
  { name: "Brian",   score: 45 },
  { name: "Chloe",   score: 92 },
  { name: "Denis",   score: 50 },
  { name: "Evelyn",  score: 33 },
];

students.forEach(student => {
  console.log(`${student.name}: ${student.score}`);
});

const passed =students
.filter(student => student.score >= 50)
console.log(passed);

const names = students.map(student => student.name);
console.log(names);

const total = students.reduce((sum, student) => sum + student.score, 0);
const average = total / students.length;
console.log(`Class average: ${average.toFixed(1)}`);