<template>
  <view id="pMe">
    <view class="header">
      <image :src="image.dot" id="decorationLeft" mode="aspectFit" />
      <view class="user-info">
        <!-- <image :src="image.testAvatar" id="avatar" mode="aspectFit" @tap="handleTapAvatar" v-if="!sessionId" />
        <view id="name" v-if="!sessionId">点击登录</view> -->
        <view id="avatar">
          <open-data type="userAvatarUrl"></open-data>
        </view>
        <view id="name">
          <open-data type="userNickName"></open-data>
        </view>
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
      <navigator class="button"  url="../books/books" hover-class="none">
        <image class="icon" :src="image.book4"></image>
        <text class="type">词书选择</text>
      </navigator>
    </view>
    <view class="columns-wrapper">
      <navigator class="column" url="../settings/settings">
        <text class="item">自定义设置</text>
        <image class="icon" :src="image.iconRight" />
      </navigator>
      <button plain open-type="feedback" class="column feedback">
        <text class="item">意见反馈</text>
        <image class="icon" :src="image.iconRight" />
      </button>
      <navigator url="../about/about" class="column">
        <text class="item">软件说明</text>
        <image class="icon" :src="image.iconRight" />
      </navigator>
    </view>
    <view class="footer">
      <view>v1.4.4 | copyright&copy;云玩家All rights reserved.</view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import Api from '../../api/index'
import { mapState, mapMutations } from 'vuex'
import dot from '../../../assets/images/dots.png'
import decorationCircle from '../../../assets/images/icon-2circle.png'
import iconRight from '../../../assets/images/icon_right.png'
import book1 from '../../../assets/images/book1.png'
import book2 from '../../../assets/images/book2.png'
import book4 from '../../../assets/images/book4.png'

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
      }
    }
  },
  computed: {
    ...mapState('user', {
      sessionId: state => state.sessionId
    }),
  },
  methods: {
    ...mapMutations('user/', [
      'setSessionId'
    ]),
    // async handleTapAvatar() {
    //   try {
    //     const res = await Taro.login()
    //     if (res.code) {
    //       console.log('>>>获取token成功：' + res.code)
    //       try {
    //         const res2 = await Api.login(res.code)
    //         this.setSessionId(res2.openid)
    //         Taro.showToast({
    //           title: '登陆成功！',
    //           duration: 1500
    //         })
    //       } catch(e) {
    //         console.error(e)
    //       }
    //     } else {
    //       console.error(res)
    //     }
    //   } catch(e) {
    //     console.error(e)
    //   }
    // }
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
      box-shadow: 0 0 15px 0 #e5e5e5;
      overflow: hidden;
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
  .footer {
    position: absolute;
    bottom: 10px;
    left: 0;
    width: 100%;
    text-align: center;
    font-size: 24px;
    color: #909399;
  }
}
</style>
