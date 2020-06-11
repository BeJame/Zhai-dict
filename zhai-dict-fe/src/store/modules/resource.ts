import Api from '../../api/index'
import { getRandomInt } from '../../../utils/util'

const state = () => ({
  vocabulary: [],
  imagesList: (() => {
    const arr: Array<String> = []
    for(let i = 1; i <= 10; i++) {
      arr.push(`https://test-fe.obs.cn-east-2.myhuaweicloud.com/images/comic/${i}.jpg`)
    }
    return arr
  })(),
  firstBackground: ''
})

const getters = {
  getPureWords(state: any) {
    return state.vocabulary.map(item => item.content)
  },
  getImages: (state: any) => (count: number) => {
    // FIXME:需求大于库存时也许会炸
    const len = state.imagesList.length
    const arr: Array<number|String> = getRandomInt(0, len - 1, count)
    // TODO:数组下标到底是个啥类型
    return arr.map(item => state.imagesList[item as any])
  }
}

const actions = {
  async fetchImageList({ commit, rootState }) {
    const res = await Api.getImageType()
    if (res.typeList) {
      const urls = res.typeList.find(item => item.description === rootState.user.settings.imagesType || '二次元').urls
      commit('setImagesList', urls)
    }
  },
  async fetchWordList({ commit, rootState}) {
    const res = await Api.getBook(rootState.user.config.bookId)
    if (res) {
      commit('setVocabulary', res)
    }
  }
}

const mutations = {
  setImagesList(state: any, data: any) {
    state.imagesList = data
  },
  setVocabulary(state: any, data: any) {
    // TODO: 数量过多会卡死
    state.vocabulary = data
  },
  setFirstBackground(state: any, data: string) {
    state.firstBackground = data
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
