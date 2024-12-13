<script setup>
import Logo from "@/components/header/Logo.vue";
</script>

<template>
  <div class="header">
    <Logo/>
<!--    <SearchBox/>-->
<!--    <Profile/>-->
  </div>
</template>

<style scoped>
.header {
  background-color: #141516;
  color: #dfe2ea;
  height: 60px;
  left: 0;
  min-width: 800px;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 11000;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 100px;
}
</style>
