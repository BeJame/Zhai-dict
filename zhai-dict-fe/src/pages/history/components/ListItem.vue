<template>
  <!-- 这里的style=css是一定要加的 -->
  <view class="word-wrapper" :style="css">
    <view class="word">{{ data[index].word }}</view>
    <view class="translation">{{ data[index].translation }}</view>
    <!-- <text class="icon" @tap="handleTapPron(data[index].pronounce)">[voice]</text> -->
  </view>
</template>

<script>
export default {
  name: 'ListItem',
  props: ['index', 'data', 'css'],
  data() {
    return {

    }
  },
  methods: {
    handleTapPron() {
      Taro.showToast({
        title: '敬请期待！',
        duration: 2000,
        icon: 'none'
      })
    }
  }
}
</script>

<style lang="scss">
.word-wrapper {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  // height: 150px;
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
</style>
