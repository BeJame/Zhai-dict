import moment from 'moment'

const state = () => ({
  totalProgress: [
    {
      word: 'abandon',
      date: '2020-02-02',
      level: 1 //熟练度
    },
  ],
  validDate: '2020-05-06',
  todayWords: [
    {
      word: 'abandon',
      level: 1, //熟练度
      translation: '',
      pronounce: ''
    },
  ],
  todayProgress: {
    'abandon': true, // 拼写正确，计算熟练度
    'aa': false, // 拼写不正确，不计算熟练度
    // 其他未出现的还没有拼写
  }
})

// getters
const getters = {

}

const actions = {
  setTodayWords({ state, rootState, rootGetters }) {
    const amount = rootState['user/config/amountPerDay'],
      reviewWordsArr = rootGetters['getRecitingWords'](Math.floor(amount / 2)),
      newWordsArr = rootGetters['getNewWords'](amount - reviewWordsArr.length),
      reviewWord = reviewWordsArr.map(item => {
        // [item]:
      })
    return state.todayWords
  }
}

const mutations = {
  setTodayProgress(state: any, progress: object) {
    return Object.assign(state.todayProgress, progress)
  },
  // 一般不直接使用该mutation
  setValidDate(state: any, date: string) {
    return state.validDate = date
  },
  updateTodayData(state: any) {
    const learnedWords = state.totalProgress.map(item => item.word)
    const updateTotalProgress = (words: Array<string>, date: string) => {
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
    if (moment(state.validDate).isBefore(undefined, 'day')) {
      // 昨天没有背完，把有背的合并到总进度
      const today = moment().format('YYYY-MM-DD')
      const finished = Object.entries(state.todayProgress).filter(item => item[1] === true).map(item => item[0])
      updateTotalProgress(finished, today)
      state.todayWords = []
      state.todayProgress = {}
      state.validDate = today
    }
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
