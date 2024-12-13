<script setup>
import {useRoute} from 'vue-router';
import {onMounted} from "vue";
import Hls from 'hls.js';

const route = useRoute();

onMounted(() => {
  const channel = route.params.channel;
  playHls(channel);
});

function playHls(channel) {
  console.log(channel);
  let video = document.getElementById('video-player');
  let videoSrc = "http://119.194.51.71:8000/hls/" + channel + ".m3u8";

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
