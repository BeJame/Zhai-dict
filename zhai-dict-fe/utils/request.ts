import Taro from '@tarojs/taro'
import { logError } from './util'

const baseUrl = ''

// Taro.addInterceptor(Taro.interceptors.logInterceptor)
// Taro.addInterceptor(Taro.interceptors.timeoutInterceptor)

const interceptor = function (chain) {
  const requestParams = chain.requestParams
  const { method, data, url } = requestParams
  console.log(`HTTP ${method || 'GET'} --> ${url} data: `, data)
  return chain.proceed(requestParams)
    .then(res => {
      console.log(`HTTP <-- ${url} result:`, res)
      if (res.data.code === 200) {
        return res.data.data
      } else {
        Taro.showToast({
          icon: 'none',
          title: '错误：' + res.data.msg,
          duration: 3000
        })
        return Promise.reject(res)
      }
    })
  }
Taro.addInterceptor(interceptor)


interface requestParam {
  url: string
  data?: any
  config?: Taro.request.Option
}

export default {
  baseRequest(params: requestParam, method: "GET" | "OPTIONS" | "POST" | "PUT" | "DELETE" | "TRACE" | "CONNECT" | undefined) {
    let { url, data, config = {} } = params
    const option = {
      isShowLoading: false,
      loadingText: '正在加载',
      url: baseUrl + (url.startsWith('/') ? '' : '/') + url,
      data: data,
      method: method,
      header: {
        'content-type': 'application/json',
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
      },
      ...config
    }
    return Taro.request(option)
  },
  get(url: string, data?: any, option?: Taro.request.Option) {
    const params = { url, data, option }
    return this.baseRequest(params, 'GET')
  },
  post(url: string, data?: any, option?: Taro.request.Option) {
    const params = { url, data, option }
    return this.baseRequest(params, 'POST')
  }
}
