<script setup>
import Header from "@/components/header/Header.vue";
</script>

<template>
    <Header class="header"/>
    <RouterView class="main-container"/>
</template>

<style scoped>
.main-container {
  position: fixed;
  top: 60px;
  width: 100%;
  min-height: 800px;
}
</style>
