<script setup>
import Thumbnail from "@/components/live/Thumbnail.vue";

defineProps(['live']);
</script>

<template>
  <div class="live__card" @click="$router.push(`/live/${live.username}`)">
    <Thumbnail :channel="live.username"/>
    <div class="title">{{ live.title }}</div>
    <div class="nickname">{{ live.nickname }}</div>
  </div>
</template>

<style scoped>
.live__card {
  display: inline-block;
  width: 300px;
  height: 230px;
  margin: 10px 20px 10px 0;
}

.title {
  height: 23px;
  font-size: 1.2rem;
}

.nickname {
  font-size: 1rem;
  color: gray;
}
</style>
