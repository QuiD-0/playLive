<script setup>
import Header from "@/components/header/Header.vue";
import userStore from "@/state/userStore.js";

userStore.commit('setClientUuid');
</script>

<template>
    <Header/>
    <RouterView/>
</template>

<style scoped>
</style>
