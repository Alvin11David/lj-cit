// Array of student objects
const students = [
  { name: "Ronald", score: 85 },
  { name: "Sarah", score: 45 },
  { name: "John", score: 72 },  
  { name: "Mary", score: 58 },
  { name: "David", score: 39 },
];

// 1. Use forEach to print every student's name and score
console.log("Student Names and Scores:");
students.forEach((student) => {
  console.log(`${student.name}: ${student.score}`);
});

// 2. Use filter to build a list of students who scored 50 or above
const passedStudents = students.filter((student) => student.score >= 50);

console.log("\nStudents who scored 50 or above:");
console.log(passedStudents);

// 3. Use map to build an array of just the names
const studentNames = students.map((student) => student.name);

console.log("\nStudent Names:");
console.log(studentNames);

// 4. Calculate and print the class average score
const totalScore = students.reduce((sum, student) => sum + student.score, 0);
const averageScore = totalScore / students.length;

console.log("\nClass Average Score:");
console.log(averageScore.toFixed(2));
