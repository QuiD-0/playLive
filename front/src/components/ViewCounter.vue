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
  postAxios('/api/stage/viewer/' + channel, {}, function (){
    console.log('Viewer added');
  });
}

function getViewerCount() {
  console.log('Getting viewer count');
  getAxios('/api/stage/viewer/' + channel, {}, function (result) {
    viewerCount.value = result.message;
    console.log('Viewer count: ' + viewerCount.value);
  });
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
