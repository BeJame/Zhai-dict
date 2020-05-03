<template>
  <view id="cKeyboard">
    <view class="first-line line">
      <view v-for="letter in keys[0]" :key="letter" @tap="handleClickLetter(letter)" class="letter" hover-class="letter-hover"
        >{{ letter }}</view>
    </view>
    <view class="second-line line">
      <view v-for="letter in keys[1]" :key="letter" @tap="handleClickLetter(letter)" class="letter" hover-class="letter-hover"
        >{{ letter }}</view>
    </view>
    <view class="third-line line">
      <view v-for="letter in keys[2]" :key="letter" @tap="handleClickLetter(letter)" class="letter" hover-class="letter-hover"
        :class="{'letter-special': letter === '-' || letter === '←'}">{{ letter }}</view>
    </view>
  </view>
</template>

<script>
const EVENT = {
  click: 'click'
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
    handleClickLetter(ch) {
      console.log('tap ' + ch)
      this.$emit(EVENT.click, ch)
    }
  },
}
</script>

<style lang="scss">
#cKeyboard {
  width: 650px;
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
    width: 50px;
    text-align: center;
    padding: 15px 0;
    vertical-align: middle;
    border: 1px solid #fff;
    border-radius: 10px;
  }
  view.letter-hover {
    background: #939699;
  }
  .letter-special {
    background: #303336;
  }
}
</style>
