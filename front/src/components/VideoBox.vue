<script setup>
import {useRoute} from 'vue-router';
import {onMounted} from "vue";
import Hls from 'hls.js';

const MEDIA_URL = import.meta.env.VITE_MEDIA_URL;
const route = useRoute();
const channel = route.params.channel;

onMounted(() => {
  playHls(channel);
});

function playHls(channel) {
  let video = document.getElementById('video-player');
  let videoSrc = MEDIA_URL + `/hls/${channel}.m3u8`;

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
  <video id="video-player" class="m-5"
         poster="https://afreehp.kr/update/bnr/bnr_penalty_type_1.png"
         playsinline="" autoplay muted controls x-webkit-airplay="" webkit-playsinline=""
         controlsList="noplaybackrate nodownload" width="1200px"></video>
</template>

<style scoped>
video::-webkit-media-controls-current-time-display{ display: none !important; }
video::-webkit-media-controls-time-remaining-display { display: none !important; }
video::-webkit-media-controls-timeline { display: none !important; }

video {
  box-shadow: 0 20px 25px -5px rgb(0 0 0 / 0.1);
}
</style>
