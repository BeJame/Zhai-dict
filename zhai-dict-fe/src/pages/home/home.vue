<template>
  <view id="pHome">
    <view class="header">
      <image src="../../../assets/images/dots.png" id="decorationLeft" mode="aspectFit" />
      <view class="welcome-text">5月9日 晴 宜上分</view>
      <view class="check-in-record">
        打卡日历
      </view>
      <image src="../../../assets/images/icon-2circle.png" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="body">
      <view class="task-wrapper">
        <view id="todayTask">- 今日任务 -</view>
        <view class="blocks">
          <view class="block">
            <text class="number">3</text>
            <text class="type">已完成</text>
          </view>
          <view class="block">
            <text class="number">10</text>
            <text class="type">目标数</text>
          </view>
        </view>
      </view>
      <view class="book-card">
        <text class="title">普通单词</text>
        <view class="history">记录</view>
        <view class="progress-area">
          <view class="progress-text">
            <text>已完成1%</text>
            <text>2/1111词</text>
          </view>
          <cardProgress :progress="15" color="#fff" blankColor="#ffffff60"></cardProgress>
        </view>
      </view>
    </view>
    <button @tap="handleTapStart" class="btn-start">开始拼写</button>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import cardProgress from "./components/cardProgress.vue";

export default {
  name: 'pHome',
  components: {
    cardProgress
  },
  data() {
    return {

    }
  },
  methods: {
    async handleTapStart() {
      await this.$store.dispatch('progress/updateTodayData')
      Taro.navigateTo({
        url: '../spell/spell'
      })
    }
  },
}
</script>

<style lang="scss">
@import "../../../styles/common";
$mainBg: #87e2d0;

#pHome {
  padding: 30px;
  .header {
    position: relative;
    margin-bottom: 60px;
    padding: 1px; /* 避免被子元素margin影响 */
    .welcome-text {
      margin-top: 100px;
      margin-left: 30px;
      font-size: 40px;
    }
    .check-in-record {
      box-sizing: border-box;
      width: 150px;
      margin-top: 30px;
      padding: 6px;
      background: $mainBg;
      color: #fff;
      border-radius: 50px;
      font-size: 24px;
      text-align: center;
    }
    #decorationLeft{
      position: absolute;
      left: 0;
      top: 0;
      width: 225px;
      height: 150px;
      z-index: -1;
    }
    #decorationRight {
      position: absolute;
      right: -50px;
      top: 40px;
      width: 120px;
      height: 120px;
      z-index: -1;
    }
  }
  .task-wrapper {
    margin: 60px 0;
    text-align: center;
    #todayTask {
      color: #babbbd;
      margin-bottom: 30px;
    }
    .blocks {
      display: flex;
      justify-content: space-around;
    }
    .block {
      display: flex;
      flex-direction: column;
    }
    .number {
      font-size: 40px;
      margin-bottom: 10px;
    }
    .type {
      font-size: 28px;
    }
  }
  .book-card {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 100%;
    height: 240px;
    box-sizing: border-box;
    padding: 30px;
    margin-bottom: 80px;
    background: #999;
    border-radius: 26px;
    color: #fff;
    .title {
      margin-top: 10px;
      font-size: 30px;
    }
    .history {
      position: absolute;
      top: 30px;
      right: 0;
      padding: 6px 25px 6px 20px;
      background: $mainBg;
      border-radius: 50px 0 0 50px;
      font-size: 28px;
    }
    .progress-text {
      display: flex;
      justify-content: space-between;
      margin-bottom: 8px;
      font-size: 20px;
    }
  }
  .btn-start {
    @include commonButton($mainBg, #fff);
    width: 550px;
    height: 90px;
    line-height: 90px;
    margin: 0 auto;
    border-radius: 99px;
  }
}
</style>
