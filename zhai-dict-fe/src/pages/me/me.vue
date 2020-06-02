<template>
  <view id="pMe">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="user-info">
        <image :src="image.testAvatar" id="avatar" mode="aspectFit" />
        <view id="name">Be、Jame</view>
      </view>
      <image :src="image.decorationCircle" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="buttons-wrapper">
      <navigator class="button" url="../collection/collection" hover-class="none">
        <image class="icon" :src="image.book1"></image>
        <text class="type">单词收藏</text>
      </navigator>
      <navigator class="button" url="../history/history" hover-class="none">
        <image class="icon" :src="image.book2"></image>
        <text class="type">学习记录</text>
      </navigator>
      <view class="button" @tap="handleTapDev" hover-class="none">
        <image class="icon" :src="image.book4"></image>
        <text class="type">笔记练习</text>
      </view>
    </view>
    <view class="columns-wrapper">
      <navigator class="column" url="../settings/settings">
        <text class="item">自定义设置</text>
        <image class="icon" :src="image.iconRight" />
      </navigator>
      <navigator class="column" url="../books/books">
        <text class="item">单词书选择</text>
        <image class="icon" :src="image.iconRight" />
      </navigator>
      <button plain open-type="feedback" class="column feedback">
        <text class="item">意见反馈</text>
        <image class="icon" :src="image.iconRight" />
      </button>
      <navigator url="../about/about" class="column">
        <text class="item">关于我们</text>
        <image class="icon" :src="image.iconRight" />
      </navigator>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
import iconRight from '../../../assets/images/icon_right.png'
import book1 from '../../../assets/images/book1.png'
import book2 from '../../../assets/images/book2.png'
import book4 from '../../../assets/images/book4.png'
import testAvatar from '../../../assets/images/test-avatar.svg'

export default {
  name: 'pageMe',
  components: {

  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
        iconRight,
        book1,
        book2,
        book4,
        testAvatar
      }
    }
  },
  methods: {
    handleTapDev() {
      Taro.showModal({
        title: '开发中',
        showCancel: false,
        content: '开发中，请等待...'
      })
    }
  },
}
</script>

<style lang="scss">
@import '../../../styles/_common';

#pMe {
  padding: 30px;
  .header {
    position: relative;
    margin-top: 100px;
    padding: 0.1px;
    #avatar {
      display: block;
      width: 150px;
      height: 150px;
      margin: 0 auto;
      border-radius: 100px;
      background: #eee;
    }
    #name {
      margin-top: 20px;
      font-weight: bold;
      text-align: center;
      font-size: 36px;
      letter-spacing: 1px;
    }
    #decorationLeft {
      position: absolute;
      left: 0;
      top: -100px;
      width: 225px;
      height: 150px;
      z-index: -1;
    }
    #decorationRight {
      position: absolute;
      right: -50px;
      top: -60px;
      width: 120px;
      height: 120px;
      z-index: -1;
    }
  }
  .buttons-wrapper {
    margin-top: 80px;
    display: flex;
    justify-content: space-around;
    .button {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .icon {
      @include simpleIcon(80px);
      // background: #eee;
      margin-bottom: 30px;
    }
  }
  .columns-wrapper {
    margin-top: 90px;
    padding: 0 30px;
    .column {
      display: flex;
      align-items: center;
      position: relative;
      line-height: 3;
      border-bottom: 1px solid #00000020;
    }
    .item {

    }
    .icon{
      @include simpleIcon(30px);
      position: absolute;
      right: 5px;
    }

    .feedback {
      padding: 0;
      color: #000;
      background: #fff;
      font-size: 32px;
      &::after {
        border: none;
      }
    }
  }
}
</style>
