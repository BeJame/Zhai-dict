import Api from '../../api/index'
import moment from 'moment'

const state = () => ({
  // 该state要包含词库中的所有单词
  totalProgress: [
    // {
    //   word: 'abandon',
    //   date: '2020-02-02',
    //   level: 1 //熟练度
    // },
  ],
  validDate: '',
  todayWords: [
    // {
    //   word: 'abandon',
    //   level: 1, //熟练度
    //   translation: '',
    //   pronounce: ''
    // },
  ],
  todayProgress: {
    // 'abandon': false, // 拼写正确
    // 'aa': false, // 拼写不正确或跳过
    // 其他未出现的还没有拼写
  }
})

// getters
const getters = {
  totalAmount(state: any) {
    return state.totalProgress.length
  },
  learnedAmount(state: any) {
    return state.totalProgress.filter((item: any) => item.level === 4).length
  },
  learningAmount(state: any) {
    return state.totalProgress.filter((item: any) => item.level !== 4 && item.level !== 0).length
  },
  todayFinished(state: any) {
    return Object.entries(state.todayProgress).filter(item => item[1] === true).map(item => item[0])
  },
  todayNewLearnedAmount(state: any, getters) {
    // TODO:僵硬。看看能不能改。
    // 考虑到目前“已掌握”时的粗糙做法，level只在totalProgress中正确而非todayWords
    return getters.todayFinished.filter(item => (state.totalProgress.find(item2 => item2.word === item)?.level === 0)).length
  },
  // totalFinished(state: any) {
  //   // 有误
  //   return Object.entries(state.totalProgress).filter(item => item[1] === true).map(item => item[0])
  // },
}

const actions = {
  _updateTodayWords({ rootState, rootGetters, commit }) {
    const amount = rootState.user.config.amountPerDay,
      reviewWords: Array<any> = rootGetters['getLearningWords'](Math.floor(amount / 2)),
      newWords: Array<any> = rootGetters['getNotLearnWords'](amount - reviewWords.length)
    // return state.todayWords = reviewWords.concat(newWords)
    commit('setTodayWords', reviewWords.concat(newWords))
    console.log('>>>今日单词更新完成')
  },
  async updateTodayData({ state, dispatch, commit, getters }, force: boolean) {
    // if (!state.validDate || moment(state.validDate).isBefore(undefined, 'day')) {
    // 考虑到用户可能把系统时间往前改，为避免永远不更新每日单词导致无法使用，改成判断日期是否一致
    if (force || !state.validDate || !moment(state.validDate).isSame(undefined, 'day')) {
      // 昨天没有背完，把有背的合并到总进度
      console.log('>>>正在合并单词到总进度')
      const finished = getters.todayFinished
      commit('_updateTotalProgress', {
        words: finished,
        date: state.validDate
      })
      await dispatch('_updateTodayWords')
      // 更新日期、清除昨日剩余进度
      const today = moment().format('YYYY-MM-DD')
      commit('setValidDate', today)
      commit('clearTodayProgress')
      // 同步进度
      dispatch('syncWordProgress')
    }
    console.log('>>>昨天已经背完单词或还没有跨天')
  },
  async initTotalProgress({ rootState, commit }) {
    const { recordList } = await Api.getRecord()
    if (!recordList?.recordList?.length) {
      console.warn('云端无历史记录')
      const initTotal: Array<any> = rootState.resource.vocabulary.map((item: any) => ({
        word: item.content,
        level: 0,
      }))
      commit('setTotalProgress', initTotal)
    } else {
      commit('setTotalProgress', recordList.recordList)
    }
  },
  async fetchWordProgress({ commit }) {
    const { recordList } = await Api.getRecord()
    if (recordList?.recordList?.length){
      commit('setTotalProgress', recordList.recordList)
    } else {
      console.warn('云端无历史记录')
    }
  },
  async syncWordProgress({ state }) {
    await Api.setRecord(state.totalProgress)
  }
}

const mutations = {
  assignTodayProgress(state: any, progress: any) {
    // 需遵循vue的响应规则，或使用Vue.set
    // return Object.assign(state.todayProgress, progress)
    return state.todayProgress = {...state.todayProgress, ...progress}
  },
  clearTodayProgress(state: any) {
    state.todayProgress = {}
  },
  setTotalProgress(state: any, progress: Array<any>) {
    return state.totalProgress = progress
  },
  setTodayWords(state: any, words: Array<any>) {
    return state.todayWords = words
  },
  setWordLevel(state: any, { word, level }: { word: String, level: number}) {
    state.totalProgress.find(item => item.word === word).level = level
  },
  setValidDate(state: any, date: string) {
    return state.validDate = date
  },
  clearValidDate(state: any) {
    return state.validDate = ''
  },
  _updateTotalProgress(state: any, { words, date = state.validDate }: { words: Array<string>, date?: string }) {
    words.forEach(word => {
      const index = state.totalProgress.findIndex(item => item.word === word)
      if (index >= 0) {
        state.totalProgress[index].date = date
        state.totalProgress[index].level += 1
      } else {
        throw new Error('cannot find word(' + word +') in history!')
      }
    })
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
