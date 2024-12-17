<script setup>
import Thumbnail from "@/components/live/Thumbnail.vue";

defineProps(['live']);
</script>

<template>
  <div class="live__card" @click="$router.push(`/live/${live.username}`)">
    <div class="thumbnail__wrapper">
      <div class="live__mark">LIVE</div>
      <Thumbnail :channel="live.username" />
    </div>
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

.thumbnail__wrapper {
  position: relative;
  width: 100%;
  height: 180px;
}

.live__mark {
  position: absolute;
  top: 8px;
  left: 8px;
  padding: 3px 8px;
  background-color: red;
  color: white;
  font-size: 1rem;
  border-radius: 5px;
  z-index: 10;
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
