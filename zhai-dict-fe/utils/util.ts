// @deprecated es6中已有repeat()可用
// 重复amount次mask
function repeatStrGen(mask: string, amount: number): string {
  // NOTE:据说v8下用+拼接字符串效率也差不多。。
  return new Array(amount > 0 ? amount : 0).join(mask)
}
