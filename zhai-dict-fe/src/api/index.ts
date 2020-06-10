import req from '../../utils/request'
import store from '../store/index'

export default class Api {
  static userId = () => store.state.user.sessionId

  static login(code: string) {
    return req.post('/login', { code })
  }

  static getBookList() {
    return req.get('/books/list')
  }

  static getBook(id: string) {
    return req.get(`/books/${id}`)
  }

  static getConfig() {
    return req.get('/config', {
      id: this.userId()
    })
  }

  static setConfig(config: any) {
    return req.post('/config', {
      ...config,
      userId: this.userId()
    })
  }

  static getRecord() {
    return req.get('/record', {
      id: this.userId()
    })
  }

  static setRecord(record: any) {
    return req.post('/record', {
      ...record,
      id: this.userId()
    })
  }

  static getCollection() {
    return req.get('/collection', {
      id: this.userId()
    })
  }

  static setCollection(collection: string | Array<any>) {
    if (typeof collection === 'string') {
      return req.post('/collection', {
        userId: this.userId(),
        wordsCollection: collection
      })
    } else if (Array.isArray(collection)) {
      const collectionStr = collection.join(';')
      return req.post('/collection', {
        userId: this.userId(),
        wordsCollection: collectionStr
      })
    }
  }

  static postCheckIn() {
    return req.post('/finish')
  }

  static getImageType() {
    return req.get('/images', {
      type: 'list'
    })
  }

  static getImageList(type: string) {
    return req.get('/images', { type })
  }
}
