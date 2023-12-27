<script>
export default {
  data() {
    return {
      scTimer: 0,
      scY: 0
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    handleScroll: function () {
      if (this.scTimer) return
      this.scTimer = setTimeout(() => {
        this.scY = window.scrollY
        clearTimeout(this.scTimer)
        this.scTimer = 0
      }, 100)
    },
    toTop: function () {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      })
    }
  }
}
</script>

<template>
  <transition name="fade">
    <div id="top" class="" v-show="scY > 300" @click="toTop">
      <i class="fas fa-arrow-circle-up fa-2x"></i>
    </div>
  </transition>
</template>

<style>
#top {
  position: fixed;
  bottom: 3%;
  right: 3%;
}
#top i {
  color: rgba(161, 160, 160, 0.623);
}
</style>
