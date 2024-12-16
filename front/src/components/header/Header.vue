<script setup>
import LogoContainer from "@/components/header/LogoContainer.vue";
import ProfileContainer from "@/components/header/ProfileContainer.vue";
</script>

<template>
  <div class="header">
    <LogoContainer/>
    <ProfileContainer/>
  </div>
</template>

<style scoped>
.header {
  background-color: #141516;
  color: #dfe2ea;
  height: 60px;
  left: 0;
  min-width: 800px;
  top: 0;
  width: 100%;
  z-index: 11000;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
}
</style>
