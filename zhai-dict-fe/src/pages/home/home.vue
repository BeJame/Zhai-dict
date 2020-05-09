<template>
  <view id="pHome">
    <button @tap="handleTapStart">开始拼写</button>
  </view>
</template>

<script>
import Taro from '@tarojs/taro'

export default {
  name: 'pHome',
  components: {

  },
  data() {
    return {

    }
  },
  methods: {
    async handleTapStart() {
      await this.$store.dispatch('progress/updateTodayData')
      Taro.navigateTo({
        url: '../spell/spell'
      })
    }
  },
}
</script>

<style lang="scss">
#pHome {

}
</style>
