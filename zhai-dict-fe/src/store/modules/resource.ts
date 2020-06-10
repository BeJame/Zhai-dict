import { getRandomInt } from '../../../utils/util'
import vocabulary from '../../../assets/CET6.json'

const state = () => ({
  vocabulary,
  imagesList: (() => {
    const arr: Array<String> = []
    for(let i = 1; i <= 200; i++) {
      arr.push(`https://test-fe.obs.cn-east-2.myhuaweicloud.com/images/magazine/image${i}.jpg`)
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

}

const mutations = {
  setImagesList(state: any, data: any) {
    state.imagesList = data
  },
  setVocabulary(state: any, data: any) {
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
