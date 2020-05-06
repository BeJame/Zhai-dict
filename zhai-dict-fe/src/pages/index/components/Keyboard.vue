<template>
  <view id="cKeyboard">
    <view class="first-line line">
      <view v-for="letter in keys[0]" :key="letter" @tap="handleTapLetter(letter)" @longpress="handleLongPressLetter(letter)"
        class="letter" hover-class="letter-hover">{{ letter }}</view>
    </view>
    <view class="second-line line">
      <view v-for="letter in keys[1]" :key="letter" @tap="handleTapLetter(letter)" @longpress="handleLongPressLetter(letter)"
        class="letter" hover-class="letter-hover">{{ letter }}</view>
    </view>
    <view class="third-line line">
      <view v-for="letter in keys[2]" :key="letter" @tap="handleTapLetter(letter)" @longpress="handleLongPressLetter(letter)"
        class="letter" hover-class="letter-hover" :class="{'letter-special': letter === '-' || letter === '←'}">{{ letter }}</view>
    </view>
  </view>
</template>

<script>
const EVENT = {
  tap: 'tap',
  longpress: 'longpress'
}

const keys = [
  ['q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'],
  ['a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'],
  ['-', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '←']
]

export default {
  name: 'Keyboard',
  data() {
    return {
      keys,

    }
  },
  methods: {
    handleTapLetter(ch) {
      console.log('tap ' + ch)
      this.$emit(EVENT.tap, ch)
    },
    handleLongPressLetter(ch) {
      console.log('longpress ' + ch)
      this.$emit(EVENT.longpress, ch)
    }
  },
}
</script>

<style lang="scss">
#cKeyboard {
  width: 700px;
  margin: 0 auto;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #fff;
  .line {
    display: flex;
    justify-content: space-between;
    width: 100%;
    margin: 25px 0;
  }

  .letter {
    display: inline-block;
    box-sizing: border-box;
    width: 60px;
    text-align: center;
    height: 80px;
    line-height: 80px;
    vertical-align: middle;
    border: 1px solid #fff;
    border-radius: 10px;
  }
  view.letter-hover {
    background: #9396999f;
  }
  .letter-special {
    font-weight: bold;
    background: #b3b6b931;
  }
}
</style>
