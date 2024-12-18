<script setup>
import VideoBox from "@/components/live/VideoBox.vue";
import NotFound from "@/components/live/NotFound.vue";
import {onBeforeMount, ref} from "vue";
import {useRoute} from 'vue-router';
import instance from "@/module/axiosFactory.js";
import Stage from "@/components/live/Stage.vue";
import clientStore from "@/state/clientStore.js";

const route = useRoute();
const isAvailable = ref(false); // 채널 상태
const isLoading = ref(true); // 로딩 상태
const channel = route.params.channel;

onBeforeMount(async () => {
  await findChannel();
  clientStore.commit('setWatchingChannel', channel);
  isLoading.value = false;
});

const findChannel = async () => {
  try {
    const response = await instance.get(`/api/member/check-available/${channel}`);
    isAvailable.value = response.data.message;
  } catch (error) {
    console.error("Error fetching channel:", error);
    isAvailable.value = false;
  }
};
</script>

<template>
  <div v-if="isLoading" class="loading">
    <div>Loading...</div>
  </div>
  <div v-else>
    <div v-if="isAvailable" class="live__container">
      <div class="live__container__left">
        <VideoBox/>
        <Stage/>
      </div>
      <div class="live__container__right">
        <div>채팅</div>
      </div>
    </div>
    <div v-else>
      <NotFound/>
    </div>
  </div>
</template>

<style scoped>
.loading {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 90vh;
  font-size: 2rem;
}

.live__container {
  display: flex;
  width: 90%;
  margin: 0 auto;
}

.live__container__left {
  flex: 1;
}

.live__container__right {
  max-width: 450px;
  width: 25%;
  background-color: gainsboro;
}

</style>
