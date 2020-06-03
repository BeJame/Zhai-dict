<template>
  <view id="pHistory">
    <view class="type-wrapper">
      <view class="type" :class="{'type-active': pageState === 1}" @tap="handleTapType(1)">今日任务</view>
      <view class="type" :class="{'type-active': pageState === 2}" @tap="handleTapType(2)">在学单词</view>
      <view class="type" :class="{'type-active': pageState === 3}" @tap="handleTapType(3)">已学单词</view>
      <view class="type" :class="{'type-active': pageState === 4}" @tap="handleTapType(4)">未学单词</view>
    </view>
    <!-- <view class="word-wrapper">
      <view class="word">abandon</view>
      <view class="translation">抛弃，遗弃</view>
    </view> -->
    <view class="word-wrapper" v-for="word in wordList" :key="word.word">
      <view class="word">{{ word.word }}</view>
      <view class="translation">{{ word.translation }}</view>
      <text class="icon" @tap="handleTapPron(word.pronounce)">[voice]</text>
    </view>
    <view class="empty" v-show="wordList.length === 0">- 暂无内容 -</view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'

export default {
  name: 'pageHistory',
  components: {

  },
  data() {
    return {
      pageState: 1,
      wordList: []
    }
  },
  computed: {
    // wordList() {
    //   console.log(this.$store.getters.getNotLearnWords(99))
    //   return this.$store.getters.getNotLearnWords(99)
    // }
  },
  methods: {
    handleTapType(type) {
      this.pageState = type
      this.getList()
    },
    handleTapPron(word) {
      Taro.showToast({
        title: '敬请期待！',
        duration: 2000,
        icon: 'none'
      })
    },
    getList() {
      let result = []
      switch (this.pageState) {
        case 1: result = this.$store.state.progress.todayWords; break
        case 2: result = this.$store.getters.getLearningWords(99); break
        case 3: result = this.$store.getters.getLearnedWords(99); break
        case 4: result = this.$store.getters.getNotLearnWords(999); break
      }
      console.log(this.pageState, result)
      this.wordList = result
    }
  },
  created() {
    this.getList()
  }
}
</script>

<style lang="scss">
#pHistory {
  .type-wrapper {
    display: flex;
    justify-content: space-between;
    padding: 20px 50px;
    .type {

    }
    .type-active {
      font-weight: bold;
      color: #D0E5A9;
    }
  }
  .word-wrapper {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 150px;
    padding: 30px 20px;
    background: #f7f7f7;
    border-bottom: 1px solid #dddddd;
    .word {
      font-weight: bold;
      // margin-bottom: 10px;
    }
    .translation {
      max-width: 600px;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
    }
    .icon {
      position: absolute;
      right: 30px;
      top: 50px;
      color: #D0E5A9;
    }
  }
  .empty {
    margin-top: 80px;
    font-size: 40px;
    color: #909399;
    text-align: center;
  }
}
</style>
