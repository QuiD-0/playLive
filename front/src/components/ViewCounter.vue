<script setup>
import {onBeforeMount, onMounted, onUnmounted, ref} from 'vue';
import {useRoute} from 'vue-router';
import instance from "@/module/AxiosFactory.js";

const route = useRoute();
const channel = route.params.channel;
const viewerCount = ref(0);

onBeforeMount(() => {
  addViewer();
});

onMounted(() => {
  getViewerCount();
  heartbeat();

  const viewerCountInterval = setInterval(getViewerCount, 10_000);
  const heartbeatInterval = setInterval(heartbeat, 10_000);

  onUnmounted(() => {
    clearInterval(viewerCountInterval);
    clearInterval(heartbeatInterval);
  });
});

const addViewer = () => {
  instance.post('/api/stage/viewer/' + channel);
}

const getViewerCount = () => {
  instance.get('/api/stage/viewer/' + channel).then(response => {
    viewerCount.value = response.data.message;
  });
}

const heartbeat = () => {
  instance.post('/api/stage/viewer/' + channel).catch(error => {
    console.error(error);
  });
}
</script>

<template>
  <div>
    <span>{{ viewerCount }}</span><span>명 시청중</span>
  </div>
</template>
