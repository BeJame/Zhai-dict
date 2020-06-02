<template>
  <view class="cSmallProgress" :style="lineStyle" style="height: 5px;border-radius: 5px;overflow: hidden;">
    <view id="filled" :style="filledStyle"></view>
  </view>
</template>

<script>
export default {
  name: 'SmallProgress',
  props: {
    progress: Number,
    color: String,
    blankColor: String,
  },
  options: {
    addGlobalClass: true
  },
  computed: {
    filledStyle() {
      return {
        height: '100%',
        width: this.progress + '%',
        background: this.color
      }
    },
    lineStyle() {
      return {
        background: this.blankColor,
      }
    }
  }
}
</script>

<style lang="scss" scoped>
// "除继承样式外， app.scss 中的样式、组件所在页面的样式，均对自定义组件无效。"
// FIXME: 当组件被复用时，此组件中的样式没有被编译到wxss中，且不能放在全局样式，故暂时使用行内样式
// :host {
//   height: 10px;
//   border-radius: 10px;
//   overflow: hidden;
// }
</style>
