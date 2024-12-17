<script setup>
const THUMBNAIL = import.meta.env.VITE_MEDIA_URL;

defineProps(['channel']);
</script>

<template>
  <img
      class="thumbnail"
      :src="THUMBNAIL+'/thumbnail/'+channel+'.jpg'"
      alt="thumbnail"
      onerror="this.src='https://afreehp.kr/update/bnr/bnr_penalty_type_1.png'">
</template>

<style scoped>
.thumbnail {
  width: 300px;
  border-radius: 10px;
}
</style>
