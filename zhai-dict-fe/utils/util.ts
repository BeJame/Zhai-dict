import Taro from '@tarojs/taro'

// @deprecated es6中已有repeat()可用
// 重复amount次mask
export function repeatStrGen(mask: string, amount: number): string {
  // NOTE:据说v8下用+拼接字符串效率也差不多。。
  return new Array(amount > 0 ? amount : 0).join(mask)
}


// 通用错误记录
export const logError = (name: string, action: string, info: string = 'empty') => {
  let deviceInfo: Taro.getSystemInfoSync.Result, device = ''
  try {
    deviceInfo = Taro.getSystemInfoSync()
    device = JSON.stringify(deviceInfo)
  } catch (e) {
    console.error('not support getSystemInfoSync api', e.message)
  }
  const time = new Date().toLocaleString()
  console.error(time, name, action, info, device)
  // 第三方日志自动上报
  // if (typeof action !== 'object') {
  // fundebug.notify(name, action, info)
  // }
  // fundebug.notifyError(info, { name, action, device, time })
  if (typeof info === 'object') {
    info = JSON.stringify(info)
  }
}
