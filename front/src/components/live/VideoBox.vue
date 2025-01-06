<script setup>
import {computed, onMounted} from "vue";
import Hls from 'hls.js';
import clientStore from "@/state/clientStore.js";

const MEDIA_URL = import.meta.env.VITE_MEDIA_URL;
const channel = computed(() => clientStore.state.watchingChannel);

onMounted(() => {
  playHls(channel);
});

function playHls(channel) {
  let video = document.getElementById('video-player');
  let videoSrc = MEDIA_URL + `/hls/${channel.value}.m3u8`;

  if (video.canPlayType('application/vnd.apple.mpegurl')) {
    video.src = videoSrc;
  } else if (Hls.isSupported()) {
    let hls = new Hls();
    hls.loadSource(videoSrc);
    hls.attachMedia(video);
  }
  video.play();
}
</script>

<template>
  <div class="video__container">
    <video id="video-player" class="m-5"
           poster="https://media.istockphoto.com/id/1219150055/vector/please-standby.jpg?s=612x612&w=0&k=20&c=6BMztLSIqWtcwcmRXmo7jOLitrFw7tTBVedLzbIrqZs="
           playsinline="" autoplay muted controls x-webkit-airplay="" webkit-playsinline=""
           controlsList="noplaybackrate nodownload" width="1200px"></video>
  </div>
</template>

<style scoped>
video::-webkit-media-controls-current-time-display {
  display: none !important;
}

video::-webkit-media-controls-time-remaining-display {
  display: none !important;
}

video::-webkit-media-controls-timeline {
  display: none !important;
}

.video__container {
  display: inline-block;
  width: 100%;
  min-width: 800px;
  padding: 0;
}

video {
  width: 100%;
  margin: 0 auto;
  box-shadow: 0 20px 25px -5px rgb(0 0 0 / 0.2);
  border-radius: 10px;
}
</style>
