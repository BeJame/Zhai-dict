<template>
  <view id="pHome">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="welcome-text">5月12日 晴 宜赶工期/忌摸鱼</view>
      <view class="check-in-record">
        打卡日历
      </view>
      <image :src="image.decorationCircle" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="body">
      <view class="task-wrapper">
        <view id="todayTask">- 今日任务 -</view>
        <view class="blocks">
          <view class="block">
            <text class="number">{{ $store.getters['progress/todayFinished'].length }}</text>
            <text class="type">已完成</text>
          </view>
          <view class="block">
            <text class="number">{{ $store.state.progress.todayWords.length }}</text>
            <text class="type">目标数</text>
          </view>
        </view>
      </view>
      <view class="book-card">
        <text class="title">普通单词</text>
        <view class="history" @tap="handleTapHistory">记录</view>
        <view class="progress-area">
          <!-- TODO:搞个compute -->
          <view class="progress-text">
            <text>已完成{{ ($store.getters['progress/totalFinished'].length + $store.getters['progress/todayFinished'].length / $store.state.progress.totalProgress.length * 100).toFixed(2) }}%</text>
            <text>{{ $store.getters['progress/totalFinished'].length + $store.getters['progress/todayFinished'].length }}/{{ $store.state.progress.totalProgress.length }}</text>
          </view>
          <cardProgress :progress="$store.getters['progress/totalFinished'].length + $store.getters['progress/todayFinished'].length / $store.state.progress.totalProgress.length * 100" color="#fff" blankColor="#ffffff60"></cardProgress>
        </view>
      </view>
    </view>
    <button @tap="handleTapStart" class="btn-start">开始拼写</button>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import cardProgress from "./components/cardProgress.vue"

import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
// import cardBg from '../../../assets/images/test-cardBg.png'

export default {
  name: 'pHome',
  components: {
    cardProgress
  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
        // cardBg
      }
    }
  },
  methods: {
    async handleTapStart() {
      await this.$store.dispatch('progress/updateTodayData')
      Taro.navigateTo({
        url: '../spell/spell'
      })
    },
    handleTapHistory() {
      Taro.navigateTo({
        url: '../history/history'
      })
    }
  },
}
</script>

<style lang="scss">
@import "../../../styles/common";
$tempCardBg: 'https://i.loli.net/2020/05/11/tlEou1AZpd97SWh.png';

#pHome {
  padding: 30px;
  .header {
    position: relative;
    margin-bottom: 60px;
    padding: 0.1px; /* 避免被子元素margin影响 */
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
      background: $mainColor;
      color: #fff;
      border-radius: 50px;
      font-size: 24px;
      text-align: center;
    }
    #decorationLeft {
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
    background: #f5f5f5;
    background-image: url($tempCardBg);
    background-size: 100% 240px;
    background-repeat: no-repeat;
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
      background: $mainColor;
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
    @include commonButton($mainColor, #fff);
    width: 550px;
    height: 90px;
    line-height: 90px;
    margin: 0 auto;
    border-radius: 99px;
  }
}
</style>
