<script setup>
import {onMounted, ref} from "vue";
import instance from "@/module/axiosFactory.js";

const THUMBNAIL = import.meta.env.VITE_MEDIA_URL;
const lives = ref([]);

onMounted( async () => {
  await fetchLives();
});

const fetchLives = async () => {
  let response = await instance.get(`/api/stage/info/list`)
  lives.value = response.data.message.content
};
</script>

<template>
  <div>
    Now Live!
  </div>
  <div v-for="live in lives" :key="live.id">
    <img :src="THUMBNAIL+'/thumbnail/' +live.username+'.jpg'" alt="thumbnail" width="300px"/>
    <h2>{{ live.nickname }}</h2>
    <p>{{ live.title }}</p>
  </div>
</template>

<style scoped>
h2 {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}
</style>
