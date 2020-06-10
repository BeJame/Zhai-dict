import Taro from '@tarojs/taro'
import VirtualList from '@tarojs/components/virtual-list'
import Vue from 'vue'
import api from './api/index'
import store from './store'

Vue.use(VirtualList)

Vue.prototype.$api = api

// 暂时解决app.config.ts无法import图标的问题
require.context('../assets/tabs', false, /\.png$/)

Vue.config.productionTip = false

const App = new Vue({
  store,
  async onLaunch() {
    Taro.showLoading({
      title: '加载中'
    })
    // 检查词库/图库存在
    if (store.state.resource.vocabulary.length === 0) {
      await store.dispatch('resource/fetchWordList')
    }
    if (store.state.resource.imagesList.length === 0) {
      await store.dispatch('resource/fetchImageList')
    }
    // 首次启动直接登陆
    if (!store.state.user.sessionId) {
      try {
        const res = await Taro.login()
        if (res.code) {
          console.log('>>>获取token成功：' + res.code)
          try {
            const res2 = await api.login(res.code)
            store.commit('user/setSessionId', res2.openid)
            // Taro.showToast({
            //   title: '登陆成功！',
            //   duration: 1000
            // })
          } catch(e) {
            console.error(e)
          }
        } else {
          console.error(res)
        }
      } catch(e) {
        console.error(e)
      }
    }
    // 首次打开小程序，缓存为空
    if (store.state.progress.totalProgress.length === 0) {
      Taro.showLoading({
        title: '同步词库与记录...'
      })
      // 同步配置与设置
      await store.dispatch('user/fetchSettingAndConfig')
      // 初始化词库图库
      // await store.dispatch('resource/fetchWordList')
      // await store.dispatch('resource/fetchImageList')
      // 同步单词进度
      await store.dispatch('progress/fetchWordProgress')
      // 云端单词进度为空，则从单词库初始化总进度
      if (store.state.progress.totalProgress.length === 0) {
        await store.dispatch('progress/initTotalProgress')
      }
      // 同步收藏
      await store.dispatch('user/fetchCollection')
    }
    // 更新每日单词
    await store.dispatch('progress/updateTodayData')
    Taro.hideLoading()
  },
  render(h) {
    // this.$slots.default 是将要会渲染的页面
    return h('block', this.$slots.default)
  }
})

export default App
