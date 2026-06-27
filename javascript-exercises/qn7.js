const numbers = [2, 3, 8,13, 21, 34, 55, 89];

const result = numbers
  .filter(n => n % 2 === 0)
  .map(n => n * 10);

console.log(result);