const output = document.querySelector('.result');
const clearBtn = document.querySelector('.clear');
const clearEntryBtn = document.querySelector('.clear-entry');
const operatorBtns = document.querySelectorAll('.operator');
const numberBtns = document.querySelectorAll('.number');
const pointBtn = document.querySelector('.point');
const equalBtn = document.querySelector('.equal');

let currentInput = '';
let prevInput = '';
let currentOperator = null;

function appendNumber(number) {
  currentInput += number;
  updateOutput();
}

function appendOperator(operator) {
  if (currentOperator !== null) {
    compute();
  }
  currentOperator = operator;
  prevInput = currentInput;
  currentInput = '';
}

function appendPoint() {
  if (currentInput.indexOf('.') === -1) {
    currentInput += '.';
    updateOutput();
  }
}

function compute() {
  let computation;
  const prev = parseFloat(prevInput);
  const current = parseFloat(currentInput);
  if (isNaN(prev) || isNaN(current)) {
    return;
  }
  switch (currentOperator) {
    case '+':
      computation = prev + current;
      break;
    case '-':
      computation = prev - current;
      break;
    case '*':
      computation = prev * current;
      break;
    case '÷':
      computation = prev / current;
      break;
    case '%':
      computation = prev % current;
      break;
    default:
      return;
  }
  currentInput = computation.toString();
  currentOperator = null;
  prevInput = '';
  updateOutput();
}

function clear() {
  currentInput = '';
  prevInput = '';
  currentOperator = null;
  updateOutput();
}

function clearEntry() {
  currentInput = '';
  updateOutput();
}

function updateOutput() {
  output.innerText = currentInput;
}

numberBtns.forEach((button) => {
  button.addEventListener('click', () => {
    appendNumber(button.innerText);
  });
});

operatorBtns.forEach((button) => {
  button.addEventListener('click', () => {
    appendOperator(button.innerText);
  });
});

pointBtn.addEventListener('click', () => {
  appendPoint();
});

equalBtn.addEventListener('click', () => {
  compute();
});

clearBtn.addEventListener('click', () => {
  clear();
});

clearEntryBtn.addEventListener('click', () => {
  clearEntry();
});
