import req from '../../utils/request'

export default class MainApi {
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
    return req.get('/config')
  }

  static setConfig(config: any) {
    return req.post('/config', config)
  }

  static getRecord() {
    return req.get('/record')
  }

  static setRecord(record: any) {
    return req.post('/record', record)
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
