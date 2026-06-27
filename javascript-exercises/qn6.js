const secret = 56;

function checkguess(guess){
  if (guess === secret) {
    console.log("correct");
  }
  if (guess> secret) {
    console.log("Too high");
  }
  if (guess< secret) {
    console.log("Too low");
  }
}

checkguess(50);
checkguess(89);
checkguess(56);