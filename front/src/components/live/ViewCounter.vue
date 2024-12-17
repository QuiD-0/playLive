<script setup>
import {onMounted, onUnmounted, ref} from 'vue';
import {useRoute} from 'vue-router';
import instance from "@/module/axiosFactory.js";
import userStore from "@/state/userStore.js";

const route = useRoute();
const channel = route.params.channel;
const viewerCount = ref(0);

onMounted(() => {
  heartbeat();
  getViewerCount();

  const viewerCountInterval = setInterval(getViewerCount, 10_000);
  const heartbeatInterval = setInterval(heartbeat, 10_000);

  onUnmounted(() => {
    clearInterval(viewerCountInterval);
    clearInterval(heartbeatInterval);
  });
});

const getViewerCount = () => {
  instance.get(`/api/stage/viewer/${channel}`).then(response => {
    viewerCount.value = response.data.message;
  });
}

const heartbeat = () => {
  let request = {
    clientUUID: userStore.state.clientUUID
  }
  instance.post(`/api/stage/viewer/${channel}`, request)
}
</script>

<template>
  <div class="view-counter">
    <span>{{ viewerCount }}</span><span>명 시청중</span>
  </div>
</template>

<style scoped>
</style>
