<script setup>
import userState from "@/state/userStore.js";
import Profile from "@/components/header/Profile.vue";
import Login from "@/components/header/Login.vue";

const isAvailable = userState.state.user !== null;
</script>

<template>
  <div class="profileContainer">
    <div v-if=isAvailable>
      <Profile/>
    </div>
    <div v-else>
      <Login/>
    </div>
  </div>
</template>

<style scoped>
.profileContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 300px;
}
</style>
