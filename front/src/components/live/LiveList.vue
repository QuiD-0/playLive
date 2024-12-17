<script setup>
import {onMounted, ref} from "vue";
import instance from "@/module/axiosFactory.js";

const THUMBNAIL = import.meta.env.VITE_MEDIA_URL;
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
      <div v-for="live in lives" :key="live.id" class="live__card">
        <img
            class="thumbnail"
            :src="THUMBNAIL+'/thumbnail/' +live.username+'.jpg'"
            alt="thumbnail"
            onerror="this.src='https://afreehp.kr/update/bnr/bnr_penalty_type_1.png'"
        >
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

.thumbnail {
  width: 300px;
  border-radius: 10px;
}
</style>
