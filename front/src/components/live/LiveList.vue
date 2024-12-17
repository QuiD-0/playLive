<script setup>
import {onMounted, ref} from "vue";
import instance from "@/module/axiosFactory.js";
import Thumbnail from "@/components/live/Thumbnail.vue";

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
    <div class="heading">
      Now Live!
    </div>
    <div class="live__box">
      <div
          v-for="live in lives"
          :key="live.id"
          class="live__card"
          @click="$router.push(`/live/${live.username}`)">
        <Thumbnail :channel="live.username"/>
        <div class="title">{{ live.title }}</div>
        <div class="nickname">{{ live.nickname }}</div>
      </div>
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

.live__card {
  display: inline-block;
  width: 300px;
  height: 230px;
  margin: 10px;
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
