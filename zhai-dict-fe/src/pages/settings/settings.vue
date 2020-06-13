<template>
  <view id="pSettings">
    <view class="type">习惯</view>
    <view class="column-picker">
      <picker mode='selector' :value="selectedIndexes[0]" :range="options[0]" @change="onPickerChange($event, 0)">
        <view class="picker-wrapper">
          <view class="item">
            <text>拼写完成停留时间</text>
            <text class="tips">选择 0秒 时手动切换</text>
          </view>
          <text class="value">{{ settings[0] }} 秒</text>
        </view>
      </picker>
    </view>
    <view class="column-picker">
      <picker mode='selector' :value="selectedIndexes[1]" :range="options[1]" @change="onPickerChange($event, 1)">
        <view class="picker-wrapper">
          <text class="item">单词提示浮窗出现时长</text>
          <text class="value">{{ settings[1] }} 秒</text>
        </view>
      </picker>
    </view>
    <view class="type">外观</view>
    <view class="column-picker">
      <picker mode='selector' :value="selectedIndexes[2]" :range="options[2]" @change="onPickerChange($event, 2)">
        <view class="picker-wrapper">
          <text class="item">背景图更换频率</text>
          <text class="value">每 {{ settings[2] }} 词更换</text>
        </view>
      </picker>
    </view>
    <view class="column-picker">
      <picker mode='selector' :value="selectedIndexes[3]" :range="options[3]" @change="onPickerChange($event, 3)">
        <view class="picker-wrapper">
          <text class="item">背景图片集类型</text>
          <text class="value">{{ settings[3] }}</text>
        </view>
      </picker>
    </view>
    <view class="column-picker">
      <picker mode='selector' :value="selectedIndexes[4]" :range="options[4]" @change="onPickerChange($event, 4)">
        <view class="picker-wrapper">
          <text class="item">渐变方式</text>
          <text class="value">{{ settings[4] }}</text>
        </view>
      </picker>
    </view>
    <view class="type">计划</view>
    <view class="column">
      <text class="item">复习比例（%）</text>
      <text class="tips">暂时锁定50%</text>
      <slider :value="reviewRatio" min="30" max="70" block-size="20" step="5" :show-value="true" />
    </view>
    <button class="btn" hover-class="btn--hover" @tap="handleTapSave">保存设置</button>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'
import { mapState } from 'vuex'
import Api from '../../api'

export default {
  name: 'pageSettings',
  components: {

  },
  data() {
    return {
      options: [
        [0, 1, 1.5, 2, 2.5, 3, 4, 5],
        [1, 3, 5],
        [1, 2, 3, 4, 5],
        ['二次元'],
        ['透明度渐变', '模糊渐变'],
      ],
      selectedIndexes: [],
      reviewRatio: 50,
      imageUrlList: [],
    }
  },
  computed: {
    settings() {
      return this.options.map((v, k) => {
        return v[this.selectedIndexes[k]]
      })
    }
  },
  methods: {
    onPickerChange(e, index) {
      // NOTE: 直接修改无法被监听
      // this.selectedIndexes[index] = parseInt(e.detail.value)
      this.$set(this.selectedIndexes, index, parseInt(e.detail.value))
    },
    async handleTapSave() {
      Taro.showLoading({
        title: '保存中...'
      })
      const s = this.settings
      console.log(this.settings, this.selectedIndexes)
      this.$store.commit('user/setSettings', {
        durationKeepAfterRecite: s[0] * 1000, //单词拼写完成后停留多长时间（ms）
        tipsDuration: s[1] * 1000, //提示弹窗的展示时长（ms）
        timesToChangeBackground: s[2], //背多少个单词换一次背景图
        imageType: s[3], // 图片集类型
        transitionType: s[4], // 渐变方式
      })
      this.$store.commit('resource/setImagesList', this.imageUrlList[this.selectedIndexes[3]])
      this.$store.dispatch('resource/fetchFirstBackground')
      try {
        await this.$store.dispatch('user/syncSettingAndConfig')
        Taro.showToast({
          title: '修改成功！',
          success() {
            setTimeout(() => {
              Taro.navigateBack()
            }, 1000);
          }
        })
      } finally {
        Taro.hideLoading()
      }
    }
  },
  async created() {
    const res = await Api.getImageType()
    this.$set(this.options, 3, res.typeList.map(item => item.description))
    this.imageUrlList = res.typeList.map(item => item.urls)
    const settings = Object.values(this.$store.state.user.settings)
    settings[0] /= 1000
    settings[1] /= 1000
    settings.forEach((v, k) => {
      this.selectedIndexes.push(this.options[k].indexOf(v))
    })

    console.log('data', this.$data)
  }
}
</script>

<style lang="scss">
@import "../../../styles/common";

#pSettings {
  height: 100vh;
  background: #f9f9f9;
  .type {
    padding: 40px 0 10px 40px;
    border-bottom: 1px solid #e3e3e3;
    font-size: 36px;
    color: #909399;
  }
  .column {
    background: #fff;
    border-bottom: 1px solid #e3e3e3;
    padding: 20px 40px;
  }
  .column-picker {
    background: #fff;
    border-bottom: 1px solid #e3e3e3;
  }
  .picker-wrapper {
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    width: 100%;
    padding: 20px 40px;
  }
  .item {
    color: #303132;
  }
  .value {
    margin-left: auto;
    color: #909399;
  }
  .tips {
    display: block;
    color: #babbbd;
    font-size: 26px;
  }
  .btn {
    @include commonButton($mainColor, #fff);
    display: block;
    margin: 120px auto;
    width: 650px;
  }
  .btn--hover {
    background: #44ceb2;
  }
  slider {
    margin-left: 0;
    margin-right: 0;
  }
  // switch {
  //   transform: scale(.8);
  // }
}
</style>
