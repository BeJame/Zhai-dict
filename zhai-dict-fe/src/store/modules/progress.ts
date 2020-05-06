// initial state
const state = () => ({
  totalProgress: [
    {
      word: 'abandon',
      date: '2020-02-02',
      level: 1 //熟练度
    },
  ],
  validDate: '2020-05-06',
  todayWords: {
    'abandon': 1,
    'aa': 1,
    'bb': 2,
    'cc': 3,
    'dd': 4
  },
  todayProgress: {
    'abandon': true, // 拼写完成，计算熟练度
    'aa': false, // 拼写未完成，不计算熟练度
    // 其他未出现的还没有拼写
  }
})

// getters
const getters = {
  getTotalProgress: (state: any) => (start: number, count: number) => {
    return state.totalProgress.slice(start, start + count)
  }
}

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
  // setProducts (state, products) {
  //   state.all = products
  // },

  // decrementProductInventory (state, { id }) {
  //   const product = state.all.find(product => product.id === id)
  //   product.inventory--
  // }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
