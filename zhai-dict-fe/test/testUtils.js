const assert = require('assert')
const assertThrows = assert.throws

const hasRepeatElm = function (arr) {
  return new Set(arr).size !== arr.length
}

const isEqual = function (arr1, arr2) {
  return arr1.sort().toString() == arr2.sort().toString()
}

const getRandomInt = (min, max, amount = 1, allowRepeat = false) => {
  const gap = max - min + 1;
  if (!allowRepeat && amount > gap) {
    throw new Error('区间内没有足够的整数');
  }
  if (amount === 1) {
    const result = Math.floor(Math.random() * gap) + min;
    return result;
  } else if (allowRepeat) {
    const result = [];
    for (let i = 0; i < amount; i++) {
      result.push(Math.floor(Math.random() * gap) + min);
    }
    return result;
  } else {
    const result = new Set();
    while (result.size < amount) {
      result.add(Math.floor(Math.random() * gap) + min);
    }
    return Array.from(result);
  }
}
assert(typeof (getRandomInt(5, 8)) === 'number', '预期为number')
assert(typeof (getRandomInt(5, 5)) === 'number', '预期为number')
assert(getRandomInt(5, 8) >= 5 && getRandomInt(5, 8) <= 8, '预期为true')
assertThrows(() => getRandomInt(5, 4), Error, '区间内没有足够的整数')
assertThrows(() => getRandomInt(1, 4, 5), Error, '区间内没有足够的整数')
assert(isEqual(getRandomInt(5, 5, 3, true), [5, 5, 5]), true)
assert(hasRepeatElm(getRandomInt(5, 10, 5, false)) === false)
assert(hasRepeatElm(getRandomInt(5, 6, 3, true)) === true)


const repeatStrGen = (mask, amount) => {
  return new Array(amount > 0 ? amount + 1 : 0).join(mask);
}
assert(repeatStrGen('1', 3) === '111')
assert(repeatStrGen('1', 6) === '111111')
assert(repeatStrGen('*', 3) === '***')
assert(repeatStrGen('*', -2) === '')
assert(repeatStrGen('a', -5) === '')

console.log('===test ok===')
