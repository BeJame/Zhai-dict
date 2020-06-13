<template>
  <view id="pBooks">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="welcome-text">我的学习任务</view>
      <image :src="image.decorationCircle" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="learning-wrapper" v-if="bookList.length && learningBook">
      <image :src="learningBook.image" class="book-img" />
      <view class="learning-body">
        <view class="title">{{ learningBook.name }}</view>
        <view class="plan">
          每日学习<text id="number"> {{ userConfig.amountPerDay }} </text>词
          <picker mode="multiSelector" @ :value="userConfig.amountPerDay"
            :range="[amountList, amountList]" header-text="修改后今日进度将会清零"
            @change="onNowPickerChange"
          >
            <image class="icon" :src="image.edit" />
          </picker>
        </view>
        <view class="progress">
          <view class="progress-text">已完成：{{ finishedAmount + learningAmount }} / {{ totalAmount }} 词</view>
          <smallProgress :progress="(finishedAmount + learningAmount) / totalAmount * 100" color="#87e2d0" blankColor="#aaa"></smallProgress>
        </view>
      </view>
    </view>
    <view class="book-list-wrapper" v-if="bookList.length">
      <view id="titleText">所有单词书</view>
      <view class="book-list" v-for="(book, index) in bookList" :key="book.bookId">
        <view class="book-card">
          <image :src="book.image" class="book-img" />
          <view class="card-body">
            <view class="title">{{ book.name }}<text v-show="book.bookId === learningBook.bookId">（学习中）</text></view>
            <view class="desc">共{{ book.totalWords }}词 | {{ book.description }}</view>
            <view class="btn-book" @tap="handleTapBook($event, index)" v-if="book.bookId !== learningBook.bookId">学习此书</view>
            <view class="btn-book btn-book-locked" v-else>正在学习</view>
          </view>
        </view>
      </view>
      <view id="endText">- 更多词书 敬请期待 -</view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import { mapState, mapMutations } from 'vuex'
import smallProgress from "../../components/smallProgress.vue"

import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
import edit from '../../../assets/icon/edit.png'
import Api from '../../api'

export default {
  name: 'pageBooks',
  components: {
    smallProgress
  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
        edit
      },
      bookList: [
        // {
        //   bookId: 123,
        //   name: '四级词汇',
        //   description: '普通的四级词汇',
        //   image: 'https://s.cn.bing.net/th?id=ODL.ab6c38bf17c9a40a3134e2d05eb459f5&w=94&h=125&c=7&rs=1&qlt=80&dpr=1.25&pid=RichNav',
        //   totalWords: 1000,
        //   learnedWords: 1
        // },
      ],

      amountList: [5, 10, 15, 20, 25, 30],
      amountIndex: 0,
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
    },
    learningBook() {
      // TODO: 暂时处理一些特殊情况
      return this.bookList.find(item => item.bookId === this.userConfig.bookId) || { bookId: this.userConfig.bookId }
    }
  },
  methods: {
    async handleTapBook(e, index) {
      try {
        const { confirm } = await Taro.showModal({
          title: '提示',
          content: '即将更换单词书为《' + this.bookList[index].name + '》，是否确认？',
        })
        if (confirm) {
          Taro.showLoading({
            title: '(1/5)上传进度',
            mask: true
          })
          console.time('同步旧记录')
          await this.$store.dispatch('progress/updateTodayData', true) // 同步之前的记录
          console.timeEnd('同步旧记录')
          console.time('下载')
          Taro.showLoading({
            title: '(2/5)下载中',
            mask: true
          })
          const res = await Api.getBook(this.bookList[index].bookId)
          console.timeEnd('下载')
          if (res) {
            Taro.showLoading({
              title: '(3/5)保存中',
              mask: true
            })
            console.time('保存')
            // TODO: 数量过多会卡死
            this.$store.commit('resource/setVocabulary', res)
            this.$store.commit('user/assignConfig', {
              bookId: this.bookList[index].bookId
            })
            console.timeEnd('保存')
            Taro.showLoading({
              title: '(4/5)进度同步',
              mask: true
            })
            console.time('初始化')
            await this.$store.dispatch('progress/initTotalProgress')
            console.timeEnd('初始化')
            Taro.showLoading({
              title: '(5/5)更新中',
              mask: true
            })
            console.time('更新单词')
            await this.$store.dispatch('progress/updateTodayData', true)
            console.timeEnd('更新单词')
            this.$store.dispatch('user/syncSettingAndConfig')
            Taro.hideLoading()
            Taro.showToast({
              title: '更换完成',
              duration: 1500
            })
          } else {
            Taro.hideLoading()
            Taro.showModal({
              title: '错误',
              content: '下载单词书出错，请重试'
            })
          }
        }
      } catch(e) {
        console.error(e)
        Taro.hideLoading()
      }
    },
    onNowPickerChange(e) {
      // TODO: 临时用两数之和
      Taro.showLoading({
        title: '修改中',
        mask: true
      })
      this.$store.commit('user/assignConfig', {
        amountPerDay: this.amountList[e.detail.value[0]] + this.amountList[e.detail.value[1]]
      })
      this.$store.dispatch('progress/updateTodayData', true)
      this.$store.dispatch('user/syncSettingAndConfig', true)
      Taro.hideLoading()
      Taro.showToast({
        title: '修改完成！'
      })
    }
  },
  async created() {
    this.amountIndex = this.amountList.indexOf(this.userConfig.amountPerDay)
    Taro.showLoading({
      title: '加载列表'
    })
    try {
      const res = await Api.getBookList()
      this.bookList = res.bookList
    } catch(e) {
      console.error(e)
    } finally {
      Taro.hideLoading()
    }
  }
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
      margin-bottom: 30px;
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
    .btn-book-locked {
      background: #b2b2b2;
      color: #fff;
    }
  }

  .book-img {
    width: 240px;
    height: 320px;
  }
}
</style>
