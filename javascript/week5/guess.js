const secretNumber = 42;
const guess = 60;
 
console.log("Secret number is locked in!");
console.log("My guess is: " + guess);
 
if (guess === secretNumber) {
  console.log("Correct! You guessed it!");
} else if (guess > secretNumber) {
  console.log("Too high! Try a lower number.");
} else {
  console.log("Too low! Try a higher number.");
}
 