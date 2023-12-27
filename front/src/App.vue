<script setup>
import '@/assets/main.css'
import { RouterView } from 'vue-router'
import TheHeader from '@/components/layout/TheHeader.vue'
import TheFooter from '@/components/layout/TheFooter.vue'
import TopButtonView from '@/components/icons/TopButtonView.vue'
// import CardListItem from '@/assets/planListView/CardListItem.vue'
// import ProfileInfoView from '@/components/user/ProfileInfo.vue'
import SlideView from '@/assets/planListView/SlideView.vue'
</script>

<template>
  <div id="app">
    <TheHeader></TheHeader>
    <router-view />
    <TopButtonView></TopButtonView>
    <TheFooter></TheFooter>

    <SlideView />
    <!-- <ProfileInfoView /> -->
  </div>
</template>

<style>
#app {
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
