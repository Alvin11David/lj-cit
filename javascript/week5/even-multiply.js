
const numbers = [3, 12, 7, 20, 5, 8, 15, 44];

console.log("Original numbers:", numbers);

const evenNumbers = numbers.filter((num) => num % 2 === 0);
console.log("Even numbers:", evenNumbers);

const result = evenNumbers.map((num) => num * 10);
console.log("Even numbers multiplied by 10:", result);