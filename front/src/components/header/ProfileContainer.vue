<script setup>
import userState from "@/state/userStore.js";
import Profile from "@/components/header/Profile.vue";
import LoginButton from "@/components/header/LoginButton.vue";
import {computed} from "vue";

const isLoggedIn = computed(() => userState.state.user !== null);
</script>

<template>
  <div class="profile__container">
    <div v-if=isLoggedIn>
      <Profile/>
    </div>
    <div v-else>
      <LoginButton/>
    </div>
  </div>
</template>

<style scoped>
.profile__container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 300px;
}
</style>
