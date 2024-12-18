<script setup>
import {computed} from "vue";
import clientStore from "@/state/clientStore.js";

const THUMBNAIL = import.meta.env.VITE_MEDIA_URL;

const channel = computed(() => clientStore.state.watchingChannel);

</script>

<template>
  <div class="thumbnail__wrapper">
    <div class="live__mark">LIVE</div>
    <img
        class="thumbnail"
        :src="THUMBNAIL+'/thumbnail/'+channel.value+'.jpg'"
        alt="thumbnail"
        onerror="this.src='https://afreehp.kr/update/bnr/bnr_penalty_type_1.png'">
  </div>
</template>

<style scoped>
.thumbnail {
  width: 300px;
  border-radius: 10px;
}


.thumbnail__wrapper {
  position: relative;
  width: 100%;
}

.live__mark {
  position: absolute;
  top: 8px;
  left: 8px;
  padding: 3px 8px;
  background-color: red;
  color: white;
  font-size: 1rem;
  border-radius: 5px;
  z-index: 10;
}

</style>
