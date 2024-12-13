<script setup>
import {onMounted, onUnmounted, ref} from 'vue';
import {useRoute} from 'vue-router';
import {getAxios, postAxios} from "@/module/AxiosFactory.js";

const route = useRoute();
const channel = route.params.channel;
const viewerCount = ref(0);

onMounted(() => {
  addViewer();
  getViewerCount();
  heartbeat();

  const viewerCountInterval = setInterval(getViewerCount, 10_000);
  const heartbeatInterval = setInterval(heartbeat, 10_000);

  onUnmounted(() => {
    clearInterval(viewerCountInterval);
    clearInterval(heartbeatInterval);
  });
});

function addViewer() {
  postAxios('/api/stage/viewer/' + channel);
}

function getViewerCount() {
  let callback = function (result) {
    viewerCount.value = result.message;
  };
  getAxios('/api/stage/viewer/' + channel, {}, callback);
}

function heartbeat() {
  postAxios('/api/stage/viewer/' + channel, {}, function () {
  });
}
</script>

<template>
  <div>
    <span>{{ viewerCount }}</span><span>명 시청중</span>
  </div>
</template>
