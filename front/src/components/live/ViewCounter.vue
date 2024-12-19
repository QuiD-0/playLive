<script setup>
import {computed, onMounted, onUnmounted, ref} from 'vue';
import {instance} from "@/module/axiosFactory.js";
import clientStore from "@/state/clientStore.js";

const channel = computed(() => clientStore.state.watchingChannel);
const viewerCount = ref(0);

let viewerCountInterval;
let heartbeatInterval;
onMounted(() => {
  heartbeat();
  getViewerCount();

  viewerCountInterval = setInterval(getViewerCount, 10_000);
  heartbeatInterval = setInterval(heartbeat, 10_000);
});

onUnmounted(() => {
  clearInterval(viewerCountInterval);
  clearInterval(heartbeatInterval);
});

const getViewerCount = () => {
  instance.get(`/api/stage/viewer/${channel.value}`).then(response => {
    viewerCount.value = response.data.message;
  });
};

const heartbeat = () => {
  const request = {
    clientUUID: clientStore.state.clientUUID,
  };
  instance.post(`/api/stage/viewer/${channel.value}`, request);
};
</script>

<template>
  <div class="view-counter">
    <span>{{ viewerCount }}</span><span>명 시청중</span>
  </div>
</template>

<style scoped>
.view-counter {
  color: #7e7e7e;
}
</style>
