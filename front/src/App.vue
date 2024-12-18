<script setup>
import Header from "@/components/header/Header.vue";
import clientStore from "@/state/clientStore.js";

clientStore.commit('setClientUuid');
</script>

<template>
    <Header/>
    <RouterView/>
</template>

<style scoped>
</style>
