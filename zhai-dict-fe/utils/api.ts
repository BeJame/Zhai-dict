import Taro from '@tarojs/taro'
import { logError } from '../utils/util'

const token = ''
const baseUrl = 'localhost:8080'

Taro.addInterceptor(Taro.interceptors.logInterceptor)
Taro.addInterceptor(Taro.interceptors.timeoutInterceptor)

export default {
  baseOptions(params: any, method: "GET" | "OPTIONS" | "HEAD" | "POST" | "PUT" | "DELETE" | "TRACE" | "CONNECT" | undefined) {
    let { url, data, contentType } = params
    // let token = getApp().globalData.token
    // if (!token) login()

    // console.log('[params]', params)
    const option = {
      isShowLoading: false,
      loadingText: '正在加载',
      url: baseUrl + url,
      data: data,
      method: method,
      header: {
        'content-type': contentType,
        // 'token': token
      },
      success(res: any) {
        if (Math.floor(res.statusCode / 100) === 4) {
          return logError('api', 'client error ' + res.statusCode)
        } else if (Math.floor(res.statusCode / 100) === 5) {
          return logError('api', 'server error ' + res.statusCode)
        } else if (res.statusCode === 200) {
          return res.data
        }
      },
      error(e: any) {
        logError('api', '请求接口调用失败', e)
        throw new Error(e)
      }
    }
    return Taro.request(option)
  },
  get(url: string, data: any) {
    const option = { url, data }
    return this.baseOptions(option, 'GET')
  },
  post(url: string, data: any, contentType = 'application/json') {
    const params = { url, data, contentType }
    return this.baseOptions(params, 'POST')
  }
}
