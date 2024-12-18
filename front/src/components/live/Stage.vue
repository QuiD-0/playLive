<script setup>
import ViewCounter from "@/components/live/ViewCounter.vue";
import {computed} from "vue";
import clientStore from "@/state/clientStore.js";

const channel = computed(() => clientStore.state.watchingChannel);
</script>

<template>
  <ViewCounter/>
</template>

