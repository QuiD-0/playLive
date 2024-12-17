<script setup>
import {onMounted, ref} from "vue";
import instance from "@/module/axiosFactory.js";
import LiveCard from "@/components/live/LiveCard.vue";

const lives = ref([]);

onMounted(async () => {
  await fetchLives();
});

const fetchLives = async () => {
  let response = await instance.get(`/api/stage/info/list`)
  lives.value = response.data.message.content
};
</script>

<template>
  <div class="main__container">
    <div class="heading">Now Live!</div>
    <div class="live__box">
      <LiveCard v-for="live in lives" :key="live.id" :live="live"/>
    </div>
  </div>
</template>

<style scoped>

.main__container {
  display: block;
  width: 90%;
  max-width: 1300px;
  margin: 60px auto;
}

.heading {
  display: block;
  font-size: 2rem;
  width: 100%;
}

.live__box {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
}
</style>
