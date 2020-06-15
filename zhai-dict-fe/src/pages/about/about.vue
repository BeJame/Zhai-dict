<template>
  <view id="pAbout">
    <view class="block">
      <view class="title">功能介绍</view>
      <view class="content">
        宅单词是一款基于拼写来辅助用户记忆单词的小程序产品，它拥有多元化选择，打卡，生词本，学习计划等功能。
      </view>
    </view>
    <view class="block">
      <view class="title">开发团队</view>
      <view class="content">
        软件工程实践11组-云玩家
      </view>
    </view>
    <view class="block">
      <view class="title">致谢</view>
      <view class="content">
        <view>感谢：咩咩单词@mieruko0713/airingursb、福利单词@easychen 提供的部分开源代码和灵感。</view>
        <view>图片收集于互联网，部分来自 https://www.zhihu.com/question/59157829</view>
      </view>
    </view>
    <view class="block">
      <view class="title">开源地址</view>
      <view class="content">
        https://github.com/BeJame/Zhai-dict
      </view>
    </view>
    <view class="block">
      <view class="title">联系我们</view>
      <view class="content">
        QQ群：810137270
      </view>
    </view>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'

export default {
  name: 'pageAbout',
}
</script>

<style lang="scss">
#pAbout {
  padding: 30px;
}

.block {
  margin-bottom: 60px;
}

.title {
  position: relative;
  margin-bottom: 20px;
  padding-left: 30px;
  color: #303336;
  font-size: 36px;
  letter-spacing: 4px;
  vertical-align: middle;
  &::before {
    content: '';
    position: absolute;
    left: 0;
    width: 8px;
    height: 100%;
    background-color: #78A4FA;
  }
}

.content {
  font-size: 28px;
  color: #909399;
}
</style>
