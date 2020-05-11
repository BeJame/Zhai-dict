<template>
  <view id="cCardProgress" :style="lineStyle">
    <view id="filled" :style="filledStyle"></view>
  </view>
</template>

<script>
export default {
  name: 'cCardProgress',
  props: {
    progress: Number,
    color: String,
    blankColor: String,
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
        background: this.blankColor
      }
    }
  }
}
</script>

<style lang="scss">
#cCardProgress {
  height: 10px;
  border-radius: 10px;
  overflow: hidden;
}
</style>
