<script setup>
import VideoBox from "@/components/live/VideoBox.vue";
import ViewCounter from "@/components/live/ViewCounter.vue";
import NotFound from "@/components/live/NotFound.vue";

const isAvailable = true;
</script>

<template>
  <div v-if="isAvailable">
    <VideoBox/>
    <ViewCounter/>
  </div>
  <div v-else>
    <NotFound/>
  </div>
</template>
