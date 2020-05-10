import Vue from 'vue'
import store from './store'

// 暂时解决app.config.ts无法import图标的问题
require.context('../assets/tabs', false, /\.png$/)

Vue.config.productionTip = false

const App = new Vue({
  store,
  onShow (options: any) {
  },
  onLaunch() {
    if (this.$store.state.progress.totalProgress.length === 0) {
      this.$store.dispatch('progress/initTotalProgress')
    }
  },
  render(h) {
    // this.$slots.default 是将要会渲染的页面
    return h('block', this.$slots.default)
  }
})

export default App
