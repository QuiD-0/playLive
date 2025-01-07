<script setup>
import VideoBox from "@/components/live/VideoBox.vue";
import NotFound from "@/components/live/NotFound.vue";
import Chatting from "@/components/live/Chatting.vue";
import {onBeforeMount, ref} from "vue";
import {useRoute} from 'vue-router';
import Stage from "@/components/live/Stage.vue";
import clientStore from "@/state/clientStore.js";
import {instance} from "@/module/axiosFactory.js";

const route = useRoute();
const isAvailable = ref(false);
const isLoading = ref(true);
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
        <Chatting/>
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
  margin: 10px auto;
  max-height: calc(100vh - 80px);
  overflow: hidden;
}

.live__container__left {
  flex: 1;
  overflow-y: auto;
  scrollbar-width: none;
  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    display: none;
  }
}

.live__container__right {
  max-width: 450px;
  width: 25%;
  min-height: calc(100vh - 60px);
  position: sticky;
  top: 0;
  margin-left: 10px;
}

</style>
