<template>
  <view id="pMe">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="user-info">
        <image :src="image.dot" id="avatar" mode="aspectFit" />
        <view id="name">BeJame</view>
      </view>
      <image :src="image.decorationCircle" id="decorationRight" mode="aspectFit" />
    </view>
    <view class="buttons-wrapper">
      <view class="button">
        <image class="icon"></image>
        <text class="type">单词收藏</text>
      </view>
      <view class="button">
        <image class="icon"></image>
        <text class="type">学习记录</text>
      </view>
      <view class="button">
        <image class="icon"></image>
        <text class="type">笔记练习</text>
      </view>
    </view>
    <view class="columns-wrapper">
      <view class="column">
        <text class="item">自定义设置</text>
        <image class="icon" :src="image.iconRight" />
      </view>
      <view class="column">
        <text class="item">排行榜</text>
        <image class="icon" :src="image.iconRight" />
      </view>
      <button plain open-type="feedback" class="column feedback">
        <text class="item">意见反馈</text>
        <image class="icon" :src="image.iconRight" />
      </button>
      <view class="column">
        <text class="item">关于我们</text>
        <image class="icon" :src="image.iconRight" />
      </view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
import iconRight from '../../../assets/images/icon_right.png'

export default {
  name: 'pageMe',
  components: {

  },
  data() {
    return {
      image: {
        dot,
        decorationCircle,
        iconRight
      }
    }
  },
  methods: {

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
      @include simpleIcon(100px);
      background: #eee;
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
