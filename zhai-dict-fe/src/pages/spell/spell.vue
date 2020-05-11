<template>
  <view id="pSpell" @keypress="handleKeypress" :style="{'background-color': `rgba(0, 0, 0, ${1 - bgRatio})`}">
    <view class="bg"></view>
    <view class="header">
      <text class="go-back" @tap="handleTapReturn">&lt;</text>
    </view>
    <view class="assist-buttons">
      <text class="jump" v-show="state === 1" @tap="handleTapJump">跳过</text>
      <text class="tips" v-show="state === 2" @tap="handleTapTips">提示</text>
      <!-- NOTE: 换行会导致text节点内容也换行 -->
      <text class="collect" v-show="state === 1 || state === 3" @tap="handleTapCollect(isWordCollected)">{{ isWordCollected ? '取消收藏' : '收藏'}}</text>
    </view>
    <view class="body" :class="{'body-large': state !== 2}">
      <view class="word" v-if="state !== 2">{{ display.word }}</view>
      <view class="word word-mask" v-else>{{ wordMasked }}</view>
      <view class="translation">{{ display.translation }}</view>
    </view>
    <view class="footer">
      <view class="main-buttons">
        <button class="known" @tap="handleTapKnown" hover-class="main-buttons-hover" v-if="state === 1">已掌握</button>
        <button class="start" @tap="handleTapStart" hover-class="main-buttons-hover" v-if="state === 1">开始拼写</button>
        <button class="next" @tap="handleTapNext" hover-class="main-buttons-hover" v-if="state === 3">下一个</button>
      </view>
      <SpellBox :length="display.word.length" :content="userInput" v-if="state === 2"></SpellBox>
      <Keyboard @tap="handleTapKb" @longpress="handleLongPressKb" v-if="state === 2"></Keyboard>
      <view class="progress">
        {{ stat.learned }} / {{ stat.total }}
      </view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import Keyboard from './components/Keyboard.vue'
import SpellBox from './components/SpellBox.vue'

const STATE =  {
  'beforeSpell': 1,
  'spelling': 2,
  'spelled': 3
}

export default {
  name: 'pSpell',
  components: {
    Keyboard,
    SpellBox
  },
  data() {
    return {
      state: STATE.beforeSpell,

      display: {
        word: 'aa',
        translation: ' n. 放纵\nvt. 放弃,遗弃,沉溺',
        announce: '',
        mastered: false
      },

      waitingList: [],
      stat: {
        learned: 0,
        total: 0
      },

      userInput: '',
      bgRatio: 0,
      // bgCount: 0,
    }
  },
  computed: {
    wordMasked() {
      const word = this.display.word
      const replaceLength = word.substring(1, word.length - 1).length
      return word[0] + '_'.repeat(replaceLength) + word[word.length - 1]
    },
    isWordCollected: {
      get() {
        return this.$store.state.user.collection.includes(this.display.word)
      }
    }
  },
  methods: {
    handleTapKb(ch) {
      if (ch === '←') {
        this.userInput = this.userInput.substring(0, this.userInput.length - 1)
      } else if (this.userInput.length < this.display.word.length) {
        this.userInput += ch
      }
      const input =  this.userInput
      if (this.display.word.startsWith(input)) {
        this.bgRatio = input.length / this.display.word.length
        if (this.display.word.length === input.length) {
          this.display.mastered = true
          setTimeout(() => {
            this.state = STATE.spelled
          }, 400);
        }
      }
    },
    handleLongPressKb(ch) {
      if (ch === '←') {
        this.userInput = ''
        this.bgRatio = 0
      }
    },
    handleTapKnown() {

    },
    handleTapStart() {
      this.state = STATE.spelling
    },
    handleTapNext() {
      // 初始化
      this.userInput = '',
      this.bgRatio = 0,
      this.state = STATE.beforeSpell
      // 对前一个单词做处理
      const word = this.waitingList.shift()
      if (!this.display.mastered) {
        this.waitingList.push(word)
      } else {
        this.stat.learned += 1
      }
      this.$store.commit('progress/setTodayProgress', {
        [this.display.word]: this.display.mastered
      })
      if (this.waitingList.length) {
        // 显示下一个单词
        this.display = this.waitingList[0]
        this.display.mastered = false
      } else {
        Taro.showModal({
          title: '完成',
          content: '您已经背完了今天的单词，可以休息一下啦~',
          showCancel: false,
          success() {
            Taro.navigateBack()
          }
        })
      }
      // TODO:处理背景图片
    },
    handleTapReturn() {
      Taro.navigateBack()
    },
    handleTapJump() {

    },
    handleTapTips() {
      Taro.showToast({
        title: this.display.word,
        duration: 1000,
        icon: 'none'
      })
    },
    handleTapCollect(isCollected) {
      this.$store.commit(isCollected ? 'user/cancelCollection' : 'user/addCollection', this.display.word)
    },


    handleKeypress(e) {
    }
  },
  created() {
    // TODO:尚未检查、处理跨日期
    console.log(this.$store.state)
    const target = this.$store.state.progress.todayWords,
      progress = this.$store.state.progress.todayProgress,
      learned = Object.entries(progress).filter(item => item[1] === true).map(item => item[0])
    this.stat = {
      total: target.length,
      learned: learned.length
    }
    this.waitingList = target.filter(item => !learned.includes(item.word))
    console.log(this.$data)
    if (this.waitingList.length === 0) {
      Taro.showModal({
        title: '提示',
        content: '您已经背完了今天的单词，希望你劳逸结合，明日再战~',
        showCancel: false,
        success() {
          Taro.navigateBack()
        }
      })
    } else {
      this.display = this.waitingList[0]
      this.display.mastered = false
    }
  },
}
</script>

<style lang="scss">
@import "../../../styles/common";

#pSpell {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  min-height: 100vh;
  padding: 20px;
  background: #000;
  transition: background-color .5s;
  color: #fff;
  .bg {
    position: fixed;
    background-image: url("https://img2.woyaogexing.com/2020/05/04/4de96356ee1e4497a575ac1fc6707f7d!1080x1920.jpeg");
    background-size: contain;
    height: 100%;
    width: 100%;
    margin: -20px;
    z-index: -1;
  }
  .assist-buttons {
    text {
      display: inline-block;
      margin-left: 20px;
    }
    text-align: right;
  }
  .body {
    margin-top: 30%;
    .word {
      font: 80px Georgia,serif;
      // font-weight: bold;
      text-shadow: 2px 2px 4px #000;
      text-align: center;
      letter-spacing: 2Px;
    }
    .translation {
      width: 550px;
      margin: 20px auto;
      text-align: center;
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
    .next {
      @include simpleButton(#fff);
      width: 400px;
      margin: 0 auto;
    }
  }
  .main-buttons-hover {
    background: #303339;
  }
  #cSpellBox {
    margin-bottom: 130px;
  }
  #cKeyboard {
    margin-bottom: 40px;
  }
  .progress {
    text-align: center;
    font-size: 28px;
    opacity: .8;
  }
}
</style>