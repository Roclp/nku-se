// const result = document.querySelector('.result')
// const buttons = document.querySelectorAll('button')

// let current = '0';
// let previous = '';
// let operator = '';

// function reset() {
//   current = '0';
//   previous = '';
//   operator = '';
// }

// function appendNumber(number) {
//   if (current === '0') {
//     current = number;
//   } else {
//     current += number;
//   }
// }

// function appendOperator(op) {
//   if (!operator) {
//     operator = op;
//     previous = current;
//     current = '0';
//   } else {
//     calculate();
//     operator = op;
//     previous = current;
//     current = '0';
//   }
// }

// function calculate() {
//   let resultValue;
//   const prev = parseFloat(previous);
//   const curr = parseFloat(current);

//   if (isNaN(prev) || isNaN(curr)) return;

//   switch (operator) {
//     case '+':
//       resultValue = prev + curr;
//       break;
//     case '-':
//       resultValue = prev - curr;
//       break;
//     case '×':
//       resultValue = prev * curr;
//       break;
//     case '÷':
//       resultValue = prev / curr;
//       break;
//     case '%':
//       resultValue = prev % curr;
//       break;
//     default:
//       return;
//   }

//   current = resultValue.toString();
//   operator = '';
//   previous = '';
// }

// function updateResult() {
  
//   result.innerText = current;

// }

// function handleButtonClick(event) {
//   const button = event.target;

//   if (button.classList.contains('number')) {
//     appendNumber(button.innerText);
//   } else if (button.classList.contains('operator')) {
//     appendOperator(button.innerText);
//   } else if (button.classList.contains('equal')) {
//     calculate();
//   } else if (button.classList.contains('clear-entry')) {
//     current = '0';
//   } else if (button.classList.contains('clear')) {
//     reset();
//   }

//   updateResult();
// }

// buttons.forEach(button => {
//   button.addEventListener('click', handleButtonClick)
// })









// const output = document.querySelector(".result");
// const btns = document.querySelectorAll("button");

// let expression = "";
// let prevResult = "";
// let prevOperator = "";

// function calculate(op1, op, op2) {
//   switch (op) {
//     case "+":
//       return Number(op1) + Number(op2);
//     case "-":
//       return Number(op1) - Number(op2);
//     case "×":
//       return Number(op1) * Number(op2);
//     case "÷":
//       return Number(op1) / Number(op2);
//     case "%":
//       return Number(op1) % Number(op2);
//     default:
//       return NaN;
//   }
// }

// function formatResult(result) {
//   const MAX_DIGITS = 10;
//   const DECIMAL_PLACES = 4;
//   let formattedResult = result.toString();

//   if (formattedResult.indexOf(".") !== -1) {
//     formattedResult = Number(formattedResult).toFixed(DECIMAL_PLACES);
//   }

//   if (formattedResult.length > MAX_DIGITS) {
//     if (result >= 1e10 || result <= -1e10) {
//       formattedResult = "Overflow";
//     } else if (result < 1e-9 && result > -1e-9) {
//       formattedResult = "Underflow";
//     } else {
//       const integerDigits = Math.floor(Math.log10(Math.abs(result))) + 1;
//       const decimalDigits = MAX_DIGITS - integerDigits - 1;
//       formattedResult = result.toFixed(decimalDigits);
//     }
//   }

//   return formattedResult;
// }

// function handleClick(event) {
//   const btnText = event.target.textContent;

//   if (btnText === "AC") {
//     expression = "";
//     prevResult = "";
//     prevOperator = "";
//     output.textContent = "0";
//     return;
//   }

//   if (btnText === "CE") {
//     expression = expression.slice(0, -1);
//     output.textContent = expression || "0";
//     return;
//   }

//   if (btnText === "." && expression.slice(-1) === ".") {
//     return;
//   }

//   if (!isNaN(btnText) || btnText === ".") {
//     expression += btnText;
//     output.textContent = expression;
//     return;
//   }

//   if (prevOperator === "") {
//     prevResult = expression;
//     prevOperator = btnText;
//     expression = "";
//     return;
//   }

//   const result = calculate(prevResult, prevOperator, expression);

//   if (isNaN(result)) {
//     output.textContent = "Error";
//     expression = "";
//     prevResult = "";
//     prevOperator = "";
//   } else {
//     prevResult = result;
//     prevOperator = btnText;
//     expression = "";
//     output.textContent = formatResult(result);
//   }
// }

// btns.forEach((btn) => {
//   btn.addEventListener("click", handleClick);
// });







const output = document.querySelector(".result");
const btns = document.querySelectorAll("button");

let expression = "";
let prevResult = "";
let prevOperator = "";

function calculate(op1, op, op2) {
  switch (op) {
    case "+":
      return Number(op1) + Number(op2);
    case "-":
      return Number(op1) - Number(op2);
    case "×":
      return Number(op1) * Number(op2);
    case "÷":
      return Number(op1) / Number(op2);
    case "%":
      return Number(op1) % Number(op2);
    case "^":
      return Math.pow(Number(op1), Number(op2));
    case "log":
      return Math.log10(Number(op2));
    case "ln":
      return Math.log(Number(op2));
    case "sqrt":
      return Math.sqrt(Number(op2));
    case "sin":
      return Math.sin(Number(op2));
    case "cos":
      return Math.cos(Number(op2));
    case "tan":
      return Math.tan(Number(op2));
    default:
      return NaN;
  }
}

function formatResult(result) {
  const MAX_DIGITS = 10;
  const DECIMAL_PLACES = 4;

  if (!isNaN(result)) {
    if (!Number.isInteger(result)) {
      let formattedResult = result.toFixed(DECIMAL_PLACES).toString();
      if (formattedResult.length > MAX_DIGITS) {
        if (result >= 1e10 || result <= -1e10) {
          formattedResult = "Overflow";
        } else if (result < 1e-9 && result > -1e-9) {
          formattedResult = "Underflow";
        } else {
          const integerDigits = Math.floor(Math.log10(Math.abs(result))) + 1;
          const decimalDigits = MAX_DIGITS - integerDigits - 1;
          formattedResult = result.toFixed(decimalDigits).toString();
        }
      }
      return formattedResult;
    } else {
      let formattedResult = result.toString();
      if (formattedResult.length > MAX_DIGITS) {
        if (result >= 1e10 || result <= -1e10) {
          formattedResult = "Overflow";
        } else if (result < 1e-9 && result > -1e-9) {
          formattedResult = "Underflow";
        } else {
          formattedResult = "Error";
        }
      }
      return formattedResult;
    }
  } else {
    return "Error";
  }
}

function handleClick(event) {
  const btnText = event.target.textContent;

  if (btnText === "AC") {
    expression = "";
    prevResult = "";
    prevOperator = "";
    output.textContent = "0";
    return;
  }

  if (btnText === "CE") {
    expression = expression.slice(0, -1);
    output.textContent = expression || "0";
    return;
  }

  if (btnText === "." && expression.slice(-1) === ".") {
    return;
  }

  if (!isNaN(btnText) || btnText === "." || btnText === "A" || btnText === "B" || btnText === "C" || btnText === "D" || btnText === "E" || btnText === "F" || btnText === "o" || btnText === "b") {
    if (btnText === "A" || btnText === "B" || btnText === "C" || btnText === "D" || btnText === "E" || btnText === "F") {
        expression += btnText;
        output.textContent = expression;
        return;
      }
      
      if (btnText === "o" || btnText === "b") {
        expression += btnText;
        output.textContent = expression;
        return;
      }
      
      expression += btnText;
      output.textContent = expression;
      } else {
      if (btnText === "=") {
      const parts = expression.split(" ");
      const operator = parts[1];
      const operand1 = prevResult || parts[0];
      const operand2 = parts[2];
      
        const result = calculate(operand1, operator, operand2);
      
        if (!isNaN(result)) {
          prevResult = result;
          expression = formatResult(result);
          output.textContent = expression;
        } else {
          output.textContent = "Error";
        }
      
        prevOperator = operator;
        return;
      }
      
      if (btnText === "sin" || btnText === "cos" || btnText === "tan" || btnText === "log" || btnText === "ln" || btnText === "sqrt") {
        const operand = expression.slice(prevOperator.length + 1);
        const result = calculate(null, btnText, operand);
      
        if (!isNaN(result)) {
          expression = btnText + "(" + operand + ")";
          prevResult = result;
          output.textContent = formatResult(result);
        } else {
          output.textContent = "Error";
        }
      
        prevOperator = btnText;
        return;
      }
      
      if (prevOperator) {
        const parts = expression.split(" ");
        const operator = parts[1];
        const operand1 = prevResult || parts[0];
        const operand2 = parts[2] || operand1;
      
        const result = calculate(operand1, operator, operand2);
      
        if (!isNaN(result)) {
          prevResult = result;
          expression = prevResult + " " + btnText;
          output.textContent = btnText;
        } else {
          output.textContent = "Error";
        }
      
        prevOperator = btnText;
      } else {
        prevResult = expression;
        expression += " " + btnText + " ";
        prevOperator = btnText;
      }
      }
      }
      
      btns.forEach((btn) => {
      btn.addEventListener("click", handleClick);
      });
      
      
      
      
      
      
