// initial state
const state = () => ({
  sessionId: '',
  info: {
    avatar: '',
    nickname: ''
  },
  config: {
    amountPerDay: 10,
    bookId: 111111,
  },
  settings: {
    timesToChangeBackground: 1,
    durationKeepAfterRecite: 1500,
    howToDealWithTips: 1, // 1:再次拼写正确后算作熟练度+1；2:不增加熟练度
    imageListId: 1,
  },
  collection: []
})

// getters
const getters = {}

// actions
const actions = {
  // getAllProducts ({ commit }) {
  //   shop.getProducts(products => {
  //     commit('setProducts', products)
  //   })
  // }
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
