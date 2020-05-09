import Vue from 'vue'
import store from './store'

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
