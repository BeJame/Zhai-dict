import Taro from '@tarojs/taro'

// @deprecated es6中已有repeat()可用
// 重复amount次mask
export function repeatStrGen(mask: string, amount: number): string {
  // NOTE:据说v8下用+拼接字符串效率也差不多。。
  return new Array(amount > 0 ? amount + 1 : 0).join(mask)
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

/**
 * [min, max]中取amount个整数，只取一个时返回number
 * @param min 随机数下限（包括min）
 * @param max 随机数上限（包括max）
 * @param amount 取随机数的数量
 * @param allowRepeat 是否允许重复
 */
export const getRandomInt = (min: number, max: number, amount = 1, allowRepeat = false) => {
  const gap = max - min + 1
  if (!allowRepeat && amount > gap) {
    throw new Error('区间内没有足够的整数')
  }
  if (amount === 1) {
    const result = Math.floor(Math.random() * gap) + min
    return result
  } else if (allowRepeat) {
    const result: Array<number> = []
    for(let i = 0; i < amount; i++) {
      result.push(Math.floor(Math.random() * gap) + min)
    }
    return result
  } else {
    const result: Set<number> = new Set()
    while(result.size < amount) {
      result.add(Math.floor(Math.random() * gap) + min)
    }
    return Array.from(result)
  }
}
