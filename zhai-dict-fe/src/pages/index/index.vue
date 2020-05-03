<template>
  <view id="pIndex" @keypress="handleKeypress">
    <view class="top">
      <text class="go-back">&lt;</text>
    </view>
    <view class="assist-buttons">
      <text class="jump" v-if="state === 1" @tap="handleTapJump">跳过</text>
      <text class="tips" v-if="state === 2" @tap="handleTapTips">提示</text>
      <text class="collect" v-if="state !== 2" @tap="handleTapCollect">收藏</text>
    </view>
    <view class="body" :class="{'body-large': state !== 2}">
      <view class="word" v-if="state !== 2">shit</view>
      <view class="word word-mask" v-else>s__t</view>
      <view class="translation">n.狗*，垃圾 v.吃*</view>
    </view>
    <view class="main-buttons" v-if="state === 1">
      <button class="known" @tap="handleTapKnown" hover-class="main-buttons-hover">已掌握</button>
      <button class="start" @tap="handleTapStart" hover-class="main-buttons-hover">开始拼写</button>
    </view>
    <SpellBox :length="word.length" :content="userInput" v-if="state === 2"></SpellBox>
    <Keyboard @click="handleTapKb" v-if="state === 2"></Keyboard>
    <view class="progress">
      0/100
    </view>
  </view>
</template>

<script>
import Keyboard from './components/Keyboard.vue'
import SpellBox from './components/SpellBox.vue'

const STATE =  {
  'beforeSpell': 1,
  'spelling': 2,
  'spelled': 3
}

export default {
  name: 'Index',
  components: {
    Keyboard,
    SpellBox
  },
  data() {
    return {
      state: STATE.beforeSpell,

      word: 'abcdefghij',
      userInput: '',
      bgRatio: 0,
    }
  },
  methods: {
    handleTapKb(ch) {
      if (ch === '←') {
        this.userInput = this.userInput.substring(0, this.userInput.length - 1)
      } else if (this.userInput.length < this.word.length) {
        this.userInput += ch
      }
      const input =  this.userInput
      if (this.word.startsWith(input)) {
        this.bgRatio = input.length / this.word.length
        if (this.word.length === input.length) {
          this.state = STATE.spelled
        }
      }
    },
    handleTapKnown() {

    },
    handleTapStart() {
      this.state = STATE.spelling
    },
    handleTapReturn() {

    },
    handleTapJump() {

    },
    handleTapTips() {

    },
    handleTapCollect() {

    },
    onFinishSpelling() {

    },


    handleKeypress(e) {
    }
  },
  created() {
    global.aaa = this.$data
  },
}
</script>

<style lang="scss">
@import "../../../styles/common";

#pIndex {
  min-height: 100vh;
  background: #000;
  padding: 0 20px;
  .assist-buttons {
    text-align: right;
    margin-right: 30px;
  }
  .body {
    .word {
      font: 70px Georgia,serif;
      // font-weight: bold;
      color: #fff;
      text-shadow: 2px 2px 4px #000;
      text-align: center;
    }
    .translation {
      width: 550px;
      margin: 0 auto;
      text-align: center;
      color: #fff;
    }
  }
  .body-large {
    transform: scale(1.4);
    margin-bottom: 100px;
  }
  .main-buttons {
    display: flex;
    justify-content: space-between;
    width: 600px;
    margin: 0 auto;
    .known {
      @include simpleButton(#fff);
      width: 200px;

    }
    .start {
      @include simpleButton(#fff);
      width: 350px;
    }
  }
  .main-buttons-hover {
    background: #303339;
  }
}
</style>
