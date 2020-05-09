import Taro from '@tarojs/taro'
import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'
import createPersistedState from "vuex-persistedstate";
import { getRandomInt } from '../../utils/util'
import progress from './modules/progress'
import resource from './modules/resource'
import user from './modules/user'

Vue.use(Vuex)

const getters = {
  // 获取已学习完的单词，按默认顺序
  getLearnedWords: (state: any) => (count?: number, start?: number) => {
    start = start || 0
    const learned = state.progress.totalProgress.find((item: any) => item.level === 4)
    let result: Array<any> = []
    if (count) {
      result = learned.slice(start, start + count)
    } else {
      result = learned
    }
    // 填充中文含义及发音
    return result.map((item: any) => {
      const wordData = state.resource.vocabulary.find((item2: any) => item2.content === item.word)
      return {
        ...item,
        translation: wordData.definition,
        pronounce: wordData.pron
      }
    })
  },
  // 获取尚未学习的单词，不给定start时为随机获取count个单词
  getNotLearnWords: (state: any) => (count: number, start?: number) => {
    const notLearn: Array<any> = state.progress.totalProgress.filter((item: any) => item.level === 0)
    count = Math.min(notLearn.length, count)
    let result: Array<any> = []
    if (start === undefined || start === null) {
      // 未给定start，获取每日新单词使用
      const randArr = getRandomInt(0, notLearn.length - 1, count) as number[]
      console.log('notLearnInner', randArr,count)
      for(const idx of randArr) {
        result.push(notLearn[idx])
      }
    } else {
      // 给定start，历史记录中使用
      result = notLearn.slice(start, start + count)
    }
    console.log('notLearn: ', result, notLearn)
    return result.map((item: any) => {
      const wordData = state.resource.vocabulary.find((item2: any) => item2.content === item.word)
      return {
        ...item,
        translation: wordData.definition,
        pronounce: wordData.pron
      }
    })
  },
  // 获取正在学习的单词，随机顺序【暂时】，获取到的数量可能小于count
  getLearningWords: (state: any) => (count: number, start?: number) => {
    start = start || 0
    const learning: Array<any> = state.progress.totalProgress.filter((item: any) => item.level !== 0 && item.level !== 4)
    count = Math.min(learning.length, count)
    let result: Array<any> = []
    if (start === undefined || start === null) {
      // 未给定start，获取每日复习的单词使用
      const randArr = getRandomInt(0, learning.length - 1, count) as number[]
      for(const idx of randArr) {
        result.push(learning[idx])
      }
    } else {
      // 给定start，历史记录中使用
      result = learning.slice(start, start + count)
    }
    console.log('learning: ', result, learning)
    return result.map((item: any) => {
      const wordData = state.resource.vocabulary.find((item2: any) => item2.content === item.word)
      // delete item.date
      return {
        ...item,
        translation: wordData.definition,
        pronounce: wordData.pron
      }
    })
  },
  // 读取所有的学习进度（历史记录）
  // getTotalProgress: (state: any) => (start: number, count: number) => {
  //   return state.totalProgress.slice(start, start + count)
  // },
  // 读取今天的单词
  getTodayWords: (state: any, getter: any) => {
    return state.todayWords
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
