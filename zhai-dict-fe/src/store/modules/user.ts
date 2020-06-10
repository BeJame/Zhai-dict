import Api from '../../api/index'

const state = () => ({
  sessionId: '',
  info: {
    avatar: '',
    nickname: ''
  },
  config: {
    amountPerDay: 10, //每日背诵数量
    bookId: 1, //单词书Id
  },
  settings: {
    durationKeepAfterRecite: 1500, //单词拼写完成后停留多长时间（ms）
    tipsDuration: 1000, //提示弹窗的展示时长（ms）
    // howToDealWithTips: 1, // 点击跳过后如何处理 1:再次拼写正确后算作熟练度+1；2:不增加熟练度
    timesToChangeBackground: 1, //背多少个单词换一次背景图
    imagesType: '二次元', // 图片集类型
  },
  collection: [] // 单词收藏
})

const getters = {
  // 获取指定数量收藏单词
  getCollectionWords: (state: any) => (start: number, count: number) => {
    return state.user.collection.slice(start, start + count)
  }
}

const actions = {
  async fetchCollection({ commit }) {
    const res = await Api.getCollection()
    if (res.wordsCollection?.length) {
      const arr = res.wordsCollection.split(';')
      commit('setCollection', arr)
    } else {
      console.log('>>>云端收藏单词为空')
    }
  },
  async syncCollection({ state }) {
    await Api.setCollection(state.collection)
  },
  async fetchSettingAndConfig({ commit }) {
    const res = await Api.getConfig()
    if (res.config) {
      commit('assignConfig', {
        amountPerDay: res.amountPerDay,
        bookId: res.bookId
      })
      delete res.amountPerDay
      delete res.bookId
      commit('setSettings', res)
    }
  },
  async syncSettingAndConfig({ state }) {
    const data = Object.assign({}, state.config, state.settings)
    await Api.setConfig(data)
  },
}

const mutations = {
  setCollection(state: any, collection: Array<any>) {
    state.collection = collection
  },
  addCollection(state: any, word: string) {
    state.collection.push(word)
  },
  cancelCollection(state: any, word: string) {
    const index = state.collection.indexOf(word)
    if (index < 0) {
      console.error('Cancel collection that not exist!!')
    }
    state.collection.splice(index, 1)
  },
  setSettings(state: any, settings: any) {
    state.settings = settings
  },
  assignConfig(state: any, config: any) {
    state.config = {...state.config, ...config}
  },
  setSessionId(state: any, id: string) {
    state.sessionId = id
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
