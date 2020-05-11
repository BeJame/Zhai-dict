<template>
  <view id="pCollection">
    <view class="total">共有 {{ $store.state.user.collection.length }} 个收藏</view>
    <view class="word-wrapper" v-for="word in collectedWords" :key="word.content">
      <view class="word">{{ word.content }}</view>
      <view class="translation">{{ word.definition }}</view>
      <text class="icon" @tap="handleTapRemove(word.content)">×</text>
    </view>
    <view class="empty" v-show="collectedWords.length === 0">- 暂无内容 -</view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'

export default {
  name: 'pageCollection',
  components: {

  },
  data() {
    return {

    }
  },
  computed: {
    collectedWords() {
      return this.$store.state.user.collection.map(item => {
        const wordData = this.$store.state.resource.vocabulary.find((item2) => item2.content === item)
        return wordData
      })
    }
  },
  methods: {
    handleTapRemove(word) {
      this.$store.commit('user/cancelCollection', word)
      Taro.showToast({
        title: '取消收藏',
        duration: 1500
      })
    }
  },
}
</script>

<style lang="scss">
#pCollection {
  .total {
    margin: 10px 0;
    text-align: center;
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

    }
    .icon {
      position: absolute;
      right: 30px;
      top: 40px;
      font-size: 50px;
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
