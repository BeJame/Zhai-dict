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
    return getters.todayFinished.filter(item => (state.todayWords.find(item2 => item2.word === item)?.level === 0)).length
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
    console.log('reviewWords', reviewWords)
    console.log('newWords', newWords)
    commit('setTodayWords', reviewWords.concat(newWords))
    console.log('>>>今日单词更新完成')
  },
  async updateTodayData({ state, dispatch, commit, getters }) {
    // if (!state.validDate || moment(state.validDate).isBefore(undefined, 'day')) {
    // 考虑到用户可能把系统时间往前改，为避免永远不更新每日单词导致无法使用，改成判断日期是否一致
    if (!state.validDate || !moment(state.validDate).isSame(undefined, 'day')) {
      // 昨天没有背完，把有背的合并到总进度
      console.log('>>>这是新用户/昨天没有完成，正在合并到总进度')
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
    }
    console.log('>>>昨天已经背完单词或还没有跨天')
    console.log(moment().toString())
  },
  async initTotalProgress({ rootState, commit }) {
    // commit('assignTodayProgress',{
    //   word: 'abandon',
    //   date: '2020-02-02',
    //   level: 1 //熟练度
    // })
    // TODO:sync from cloud
    const initTotal: Array<any> = rootState.resource.vocabulary.map((item: any) => ({
      word: item.content,
      level: 0,
    }))
    commit('setTotalProgress', initTotal)
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
    // return state.totalProgress = [...state.totalProgress, ...progress]
    return state.totalProgress = progress
  },
  setTodayWords(state: any, words: Array<any>) {
    return state.todayWords = words
  },
  setValidDate(state: any, date: string) {
    return state.validDate = date
  },
  clearValidDate(state: any) {
    return state.validDate = ''
  },
  _updateTotalProgress(state: any, { words, date }: { words: Array<string>, date: string }) {
    // const learnedWords: Array<any> = state.totalProgress.filter(item => item.level !== 0 && item.level !== 4)
    // console.log('learnedWords', learnedWords)
    // console.log('words', words)
    words.forEach(word => {
      const index = state.totalProgress.findIndex(item => item.word === word)
      if (index >= 0) {
        state.totalProgress[index].date = date
        state.totalProgress[index].level += 1
      } else {
        throw new Error('cannot find word in history!!')
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
