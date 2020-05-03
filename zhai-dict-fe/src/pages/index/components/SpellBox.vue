<template>
  <view id="cSpellBox">
    <view v-for="n in length" :key="n" class="letter-box">
      {{ content[n - 1] || '' }}
    </view>
  </view>
</template>

<script>
export default {
  name: 'SpellBox',
  props: {
    length: {
      type: Number,
      require: true
    },
    content: String
  },
  data() {
    return {

    }
  },
}
</script>

<style lang="scss">
#cSpellBox {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 700px;
  margin: 0 auto;
  color: #fff;
  .letter-box {
    width: 50px;
    height: 50px;
    line-height: 50px;
    margin: 10px 20px;
    text-align: center;
    border: 1px solid #fff;
    border-radius: 5px;
  }
}
</style>
