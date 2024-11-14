
/* 该计算器有个小bug，对于log，ln，sqrt，sin，cos，tan的计算
    需要先点击数字，再点击对应的运算符
*/


// 获取结果
const result = document.querySelector('.result');

// 获取清除全部AC、清除最后一个CE、等于=、小数点.
const clearBtn = document.querySelector('.clear');
const clearEntryBtn = document.querySelector('.clear-entry');
const equalBtn = document.querySelector('.equal');
const pointBtn = document.querySelector('.point');

const numberBtns = document.querySelectorAll('.number');
const operatorBtns = document.querySelectorAll('.operator');

// 定义两个操作数和一个运算符变量
let num1 = '';
let num2 = '';
let operator = '';

// 清除AC函数   使用字符串计算
function clear() {
  result.textContent = ''; // 清空结果显示区域，设为空字符串
  num1 = ''; 
  num2 = ''; 
  operator = ''; 
}

// 清除单个字符CE函数
function clearEntry() {
  const str = result.textContent; // 获取结果显示区域的文本内容
  if (str !== '') { // 判断结果显示区域是否为空
    const lastChar = str.charAt(str.length - 1); // 获取结果显示区域最后一个字符
    if (isNaN(lastChar) && lastChar !== '.') { // 判断最后一个字符是否是数字或者小数点
      operator = ''; // 如果不是，则将运算符设为空字符串
    }
    result.textContent = str.slice(0, -1); // 删除最后一个字符
  }
}

// 表达式求值函数         
function calculate() {
  let res;
  switch (operator) { 
    case '+':
      res = parseFloat(num1) + parseFloat(num2);
      break;
    case '-':
      res = parseFloat(num1) - parseFloat(num2);
      break;
    case '×':
      res = parseFloat(num1) * parseFloat(num2);
      break;
    case '÷':
      res = parseFloat(num1) / parseFloat(num2);
      break;
    case '%':
      res = parseFloat(num1) % parseFloat(num2);
      break;
    case '^':
      res = Math.pow(parseFloat(num1), parseFloat(num2));
      break;
    case 'log':
      res = Math.log10(parseFloat(num1));
      break;
    case 'ln':
      res = Math.log(parseFloat(num1));
      break;
    case 'sqrt':
      res = Math.sqrt(parseFloat(num1));
      break;
    case 'sin':
      res = Math.sin(parseFloat(num1));
      break;
    case 'cos':
      res = Math.cos(parseFloat(num1));
      break;
    case 'tan':
      res = Math.tan(parseFloat(num1));
      break;
    default:
      res = parseFloat(num1);
  }
  num1 = res; // 计算结果赋值给num1，以便后续计算
  num2 = ''; 
  operator = ''; 
  if (Number.isInteger(res)) { // 判断计算结果是否是整数
    result.textContent = res.toString(); // 如果是，则直接显示计算结果
  } else if (res.toString().split('.')[1].length <= 4) { // 判断小数部分的位数是否超过了四位
    result.textContent = res.toString(); // 如果不超过，则直接显示计算结果
  } else {
    result.textContent = res.toFixed(4); // 保留四位小数，显示计算结果
  }  
}

// 添加数字函数
function addNumber(num) {
  if (operator === '') { // 判断运算符是否为空字符串
    num1 += num; // 如果是，则将数字添加到操作数1之后
    result.textContent += num; // 将数字添加到结果显示区域的文本后面
  } else {
    num2 += num; // 如果不是，则将数字添加到操作数2之后
    result.textContent += num; // 将数字添加到结果显示区域的文本后面
  }
}

// 添加运算符
function addOperator(op) {
  if (num1 !== '') { 
    operator = op; 
    result.textContent += ` ${op} `; 
  } else {
    alert('请输入数字'); // 如果num1为空，提示用户输入数字
  }
}

// 绑定点击事件
clearBtn.addEventListener('click', clear);
clearEntryBtn.addEventListener('click', clearEntry);
equalBtn.addEventListener('click', calculate);
pointBtn.addEventListener('click', () => {
  if (operator === '' && !result.textContent.includes('.')) { // 判断是否可以添加小数点
    num1 += '.'; 
    result.textContent += '.'; 
  } else if (!num2.includes('.')) {
    num2 += '.'; 
    result.textContent += '.'; 
  }
});

numberBtns.forEach(btn => {
  btn.addEventListener('click', () => {
    addNumber(btn.textContent);
  });
});

operatorBtns.forEach(btn => {
  btn.addEventListener('click', () => {
    addOperator(btn.textContent);
  });
});
