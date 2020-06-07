<template>
  <view id="pHome">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="welcome-text">{{ welcomeText }}</view>
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
        <view class="btn-change" @tap="handleTapChangePlan">更改</view>
        <view class="progress-area">
          <view class="progress-text">
            <!-- 进度 包括learned + learning -->
            <text>进度 {{ ((finishedAmount + learningAmount) / totalAmount * 100).toFixed(2) }}%</text>
            <text>{{ finishedAmount + learningAmount }}/{{ totalAmount }}</text>
          </view>
          <smallProgress :progress="(finishedAmount + learningAmount) / totalAmount * 100" color="#fff" blankColor="#ffffff60"></smallProgress>
        </view>
      </view>
    </view>
    <button @tap="handleTapStart" class="btn-start">开始拼写</button>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import smallProgress from "../../components/smallProgress.vue"

import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'

const doit = ['外出', '看书', '运动', '赶工期']
const dont = ['摸鱼', '划水', '睡懒觉']

export default {
  name: 'pHome',
  components: {
    smallProgress
  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
      }
    }
  },
  computed: {
    welcomeText() {
      const d = new Date()
      let str = `${d.getMonth() + 1}月${d.getDate()}日 `
      str += `宜${doit[(d.getDate() * 66 + 6) % doit.length]}/忌${dont[d.getDate() * 44 % dont.length]}`
      return str
    },
    finishedAmount() {
      return this.$store.getters['progress/learnedAmount'] + this.$store.getters['progress/todayNewLearnedAmount']
    },
    learningAmount() {
      return this.$store.getters['progress/learningAmount']
    },
    totalAmount() {
      return this.$store.getters['progress/totalAmount']
    }
  },
  methods: {
    async handleTapStart() {
      await this.$store.dispatch('progress/updateTodayData')
      Taro.navigateTo({
        url: '../spell/spell'
      })
    },
    handleTapChangePlan() {
      Taro.navigateTo({
        url: '../books/books'
      })
    }
  },
  onLoad() {
    // 临时解决首张图片问题，后续根据图片类型处理
    const bg = 'https://i.loli.net/2020/05/12/1rRd82ljUOQaNZX.jpg'
    Taro.getImageInfo({
      src: bg,
      success: () => {
        this.$store.commit('resource/setFirstBackground', bg)
      }
    })
  }
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
    .btn-change {
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
