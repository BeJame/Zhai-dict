<template>
  <view id="pIndex" @keypress="handleKeypress">
    <view class="header">
      <text class="go-back">&lt;</text>
    </view>
    <view class="assist-buttons">
      <text class="jump" v-if="state === 1" @tap="handleTapJump">跳过</text>
      <text class="tips" v-if="state === 2" @tap="handleTapTips">提示</text>
      <text class="collect" v-if="state !== 2" @tap="handleTapCollect">收藏</text>
    </view>
    <view class="body" :class="{'body-large': state !== 2}">
      <view class="word" v-if="state !== 2">{{ word }}</view>
      <view class="word word-mask" v-else>{{ wordMasked }}</view>
      <view class="translation">{{ translation }}</view>
    </view>
    <view class="footer">
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

      word: 'shitshitshit',
      translation: 'n.狗*，垃圾 v.吃*n.狗*，垃圾 v.吃*n.狗*，垃圾 v.吃*',
      userInput: '',
      bgRatio: 0,
    }
  },
  computed: {
    wordMasked() {
      const word = this.word
      const replaceLength = word.substring(1, word.length - 1).length
      return word[0] + '_'.repeat(replaceLength) + word[word.length - 1]
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
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  min-height: 100vh;
  padding: 20px;
  background: #000;
  .assist-buttons {
    margin-right: 30px;
    color: #fff;
    text-align: right;
  }
  .body {
    margin-top: 30%;
    .word {
      font: 80px Georgia,serif;
      // font-weight: bold;
      color: #fff;
      text-shadow: 2px 2px 4px #000;
      text-align: center;
      letter-spacing: 2Px;
    }
    .translation {
      width: 550px;
      margin: 20px auto;
      text-align: center;
      color: #fff;
    }
  }
  .body-large {
    transform: scale(1.3);
    padding: 100px 0;
  }
  .footer {
    margin-top: auto;
  }
  .main-buttons {
    display: flex;
    justify-content: space-between;
    width: 600px;
    margin: 0 auto;
    margin-bottom: 40px;
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
  #cSpellBox {
    margin-bottom: 130px;
  }
  .progress {
    text-align: center;
    color: #fff;
  }
}
</style>
