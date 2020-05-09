// initial state
const state = () => ({
  sessionId: '',
  info: {
    avatar: '',
    nickname: ''
  },
  config: {
    amountPerDay: 10, //每日背诵数量
    bookId: 123, //单词书Id
  },
  settings: {
    timesToChangeBackground: 1, //背多少个单词换一次背景图
    durationKeepAfterRecite: 1500, //单词拼写完成后停留多长时间（ms）
    tipsDuration: 1000, //提示弹窗的展示时长（ms）
    howToDealWithTips: 1, // 点击跳过后如何处理 1:再次拼写正确后算作熟练度+1；2:不增加熟练度
    imagesType: 'xxx', // 图片集类型
  },
  collection: [] // 单词收藏
})

// getters
const getters = {}

// actions
const actions = {

}

// mutations
const mutations = {
  addCollection(state: any, word: string) {
    state.collection.push(word)
  },
  cancelCollection(state: any, word: string) {
    const index = state.collection.indexOf(word)
    if (index < 0) {
      console.error('cancel collection that not exist!!')
    }
    state.collection.splice(index, 1)
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
