<template>
  <view id="pBooks">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="welcome-text">我的学习任务</view>
      <image :src="image.decorationCircle" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="learning-wrapper">
      <image :src="learningBook.image" class="book-img" />
      <view class="learning-body">
        <view class="title">{{ learningBook.title }}</view>
        <view class="plan">
          每日新学<text id="number"> {{ userConfig.amountPerDay }} </text>词
          <image class="icon" :src="image.edit" />
        </view>
        <view class="progress">
          <view class="progress-text">已完成：{{ finishedAmount + learningAmount }} / {{ totalAmount }} 词</view>
          <cardProgress :progress="(finishedAmount + learningAmount) / totalAmount * 100" color="#87e2d0" blankColor="#aaa"></cardProgress>
        </view>
      </view>
    </view>
    <view class="book-list-wrapper">
      <view id="titleText">所有单词书</view>
      <view class="book-list" v-for="book in bookList" :key="book.bookId">
        <view class="book-card">
          <image :src="book.image" class="book-img" />
          <view class="card-body">
            <view class="title">{{ book.title }}</view>
            <view class="desc">共{{ book.totalWords }}词 | {{ book.description }}</view>
            <view class="btn-book">学习此书</view>
          </view>
        </view>
      </view>
      <view id="endText">- 更多词书 敬请期待 -</view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import { mapState } from 'vuex'
import cardProgress from "../home/components/cardProgress.vue"

import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
import edit from '../../../assets/icon/edit.png'

export default {
  name: 'pageBooks',
  components: {
    cardProgress
  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
        edit
      },
      learningBook: {
        title: '四级词汇',
        image: 'https://s.cn.bing.net/th?id=ODL.ab6c38bf17c9a40a3134e2d05eb459f5&w=94&h=125&c=7&rs=1&qlt=80&dpr=1.25&pid=RichNav',
      },
      bookList: [
        {
          bookId: 123,
          title: '四级词汇',
          description: '普通的四级词汇',
          image: 'https://s.cn.bing.net/th?id=ODL.ab6c38bf17c9a40a3134e2d05eb459f5&w=94&h=125&c=7&rs=1&qlt=80&dpr=1.25&pid=RichNav',
          totalWords: 1000, // 单词书总数
          learnedWords: 1 //手动统计,服务端更新【这玩意应该后期再搞】
        },
      ]
    }
  },
  computed: {
    ...mapState('user', {
      userConfig: state => state.config
    }),
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

  },
}
</script>

<style lang="scss">
@import "../../../styles/common";

#pBooks {
  padding: 30px;
    .header {
    position: relative;
    margin-bottom: 60px;
    padding: 0.1px; /* 避免被子元素margin影响 */
    .welcome-text {
      margin-top: 100px;
      margin-left: 30px;
      font-size: 40px;
      letter-spacing: 2px;
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
  .learning-wrapper {
    display: flex;
    padding: 0 20px;
    .book-img {
      zoom: .7;
    }
    .learning-body {
      display: flex;
      flex-direction: column;
      flex: 1;
      margin-left: 20px;
    }
    .title {
      font-size: 38px;
      margin-bottom: 15px;
    }
    .plan {
      font-size: 30px;
      display: inline-flex;
      align-items: center;
    }
    #number {
      margin: 0 10px;
      font-size: 32px;
      color: $mainColor;
    }
    .icon {
      @include simpleIcon(40px);
      margin-left: 20px;
      opacity: .4;
    }
    .progress {
      margin-top: 70px;
    }
    .progress-text {
      margin-bottom: 8px;
      font-size: 20px;
    }
    // 不知道为什么组件样式未生效
    #cCardProgress {
      height: 10px;
      border-radius: 10px;
      overflow: hidden;
    }
  }
  .book-list-wrapper {
    margin-top: 100px;
    padding: 0 20px;
    #titleText {
      font-size: 28px;
      margin-bottom: 25px;
    }
    #endText {
      margin-top: 40px;
      text-align: center;
      color: #909399;
      font-size: 28px;
    }
    .book-list {

    }
    .book-card {
      display: flex;
      padding: 25px;
      box-shadow: 0 0 4px 0 #bbb;
      border-radius: 10px;
    }
    .book-img {
      zoom: .5;
    }
    .card-body {
      display: flex;
      flex-direction: column;
      flex: 1;
      margin-left: 20px;
    }
    .title {
      margin-bottom: 25px;
    }
    .desc {
      color: #909399;
      font-size: 26px;
    }
    .btn-book {
      box-sizing: border-box;
      width: 150px;
      margin-top: 30px;
      margin-left: auto;
      padding: 2px 6px;
      background: $mainColor;
      color: #fff;
      border-radius: 50px;
      font-size: 24px;
      text-align: center;
    }
  }

  .book-img {
    width: 240px;
    height: 320px;
  }
}
</style>
