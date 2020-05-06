import Taro from '@tarojs/taro'
import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'
import createPersistedState from "vuex-persistedstate";
import progress from './modules/progress'
import resource from './modules/resource'
import user from './modules/user'

Vue.use(Vuex)

const getters = {
  getNewWords: (state: any) => (amount: number): Array<any> => {
    return []
  },
  getRecitingWords: (state: any) => (amount: number): Array<any> => {
    return []
  },
  getAllLearned() {

  },
  getAllNotLearned() {

  },
  getAllReciting() {

  },
  getTodayWords () {

  }
}


const debug = process.env.NODE_ENV !== 'production'
export default new Vuex.Store({
  modules: {
    progress,
    resource,
    user
  },
  strict: debug,
  getters,
  plugins: [
    createPersistedState({
      storage: {
        getItem: (key) => Taro.getStorageSync(key),
        setItem: (key, value) => Taro.setStorageSync(key, value),
        removeItem: (key) => Taro.removeStorageSync(key),
      }
    }),
    createLogger()
  ]
})
