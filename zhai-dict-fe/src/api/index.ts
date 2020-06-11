import req from '../../utils/request'
import store from '../store/index'

export default class Api {
  static userId() {
    return store.state.user.sessionId
  }
  static bookId() {
    return store.state.user.config.bookId
  }

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

  static getRecord(bookId?: string) {
    return req.get('/record', {
      id: this.userId(),
      bookId: bookId || this.bookId()
    })
  }

  static setRecord(record: any, bookId?: string) {
    return req.post('/record', {
      recordList: record,
      userId: this.userId(),
      bookId: bookId || this.bookId()
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
