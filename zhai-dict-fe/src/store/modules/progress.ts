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
  validDate: '2020-05-06',
  todayWords: [
    // {
    //   word: 'abandon',
    //   level: 1, //熟练度
    //   translation: '',
    //   pronounce: ''
    // },

  ],
  todayProgress: {
    // 'abandon': false, // 拼写正确，计算熟练度
    // 'aa': false, // 拼写不正确或跳过，不计算熟练度
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
  }
}

const actions = {
  readTodayWords({ rootState, rootGetters, commit }) {
    const amount = rootState.user.config.amountPerDay,
      reviewWords: Array<any> = rootGetters['getLearningWords'](Math.floor(amount / 2)),
      newWords: Array<any> = rootGetters['getNotLearnWords'](amount - reviewWords.length)
    // return state.todayWords = reviewWords.concat(newWords)
    console.log(reviewWords, newWords)
    commit('setTodayWords', reviewWords.concat(newWords))
  },
  async updateTodayData({ state, dispatch, commit }) {
    if (!state.validDate || moment(state.validDate).isBefore(undefined, 'day')) {
      // 昨天没有背完，把有背的合并到总进度
      const today = moment().format('YYYY-MM-DD')
      const finished = Object.entries(state.todayProgress).filter(item => item[1] === true).map(item => item[0])
      commit('updateTotalProgress', {
        words: finished,
        date: today
      })
      await dispatch('readTodayWords')
      // state.todayProgress = {}
      // state.validDate = today
      commit('setValidDate', today)
      commit('setTotalProgress', {})
    }
  },
  async initTotalProgress({ rootState, commit }) {
    // commit('setTotalProgress',{
    //   word: 'abandon',
    //   date: '2020-02-02',
    //   level: 1 //熟练度
    // })
    // TODO:sync from cloud
    const initTotal = rootState.resource.vocabulary.map((item: any) => ({
      word: item.content,
      level: 0,
    }))
    commit('setTotalProgress', initTotal)
  }
}

const mutations = {
  setTodayProgress(state: any, progress: object) {
    return Object.assign(state.todayProgress, progress)
  },
  setTotalProgress(state: any, progress: object) {
    return Object.assign(state.totalProgress, progress)
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
  updateTotalProgress(state: any, { words, date }: { words: Array<string>, date: string }) {
    const learnedWords = state.totalProgress.map(item => item.word)
    words.forEach(word => {
      const index = learnedWords.findIndexOf(item => item.word === word)
      if (index >= 0) {
        state.totalProgress[index].date = date
        state.totalProgress[index].level += 1
      } else {
        state.totalProgress.push({
          word,
          date,
          level: 1
        })
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
