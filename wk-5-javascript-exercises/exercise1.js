// Traditional function declarations
function add(a, b) {
  return a + b;
}

function subtract(a, b) {
  return a - b;
}

// Arrow function declarations
const multiply = (a, b) => a * b;

const divide = (a, b) => {
  if (b === 0) {
    return "Cannot divide by zero";
  }
  return a / b;
};

// Call each function and log results
console.log(add(10, 5));
console.log(subtract(10, 5));
console.log(multiply(10, 5));
console.log(divide(10, 5));
console.log(divide(10, 0));