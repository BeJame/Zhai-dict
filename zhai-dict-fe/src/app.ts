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
    // 从单词库初始化总进度，首次打开时使用
    if (this.$store.state.progress.totalProgress.length === 0) {
      await this.$store.dispatch('progress/initTotalProgress')
    }
    // 更新每日单词
    await this.$store.dispatch('progress/updateTodayData')
  },
  render(h) {
    // this.$slots.default 是将要会渲染的页面
    return h('block', this.$slots.default)
  }
})

export default App
