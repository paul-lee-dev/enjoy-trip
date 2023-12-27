<script setup>
</script>

<template>
  <div class="hello">
    <router-view></router-view>
    <nav>
      <router-link to="/plan/create"
        ><button class="btn-get-started color-3">새로운 여행계획</button></router-link
      >
      <router-link to="/plan/list"
        ><button class="btn-get-started color-3">나의 여행계획</button></router-link
      >
    </nav>
  </div>
</template>

<style scoped></style>
