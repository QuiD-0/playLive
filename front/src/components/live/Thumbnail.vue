<script setup>

const THUMBNAIL = import.meta.env.VITE_MEDIA_URL;

defineProps(["channel"]);
</script>

<template>
  <div class="thumbnail__wrapper">
    <div class="live__mark">LIVE</div>
    <img
        class="thumbnail"
        :src="THUMBNAIL+'/thumbnail/'+channel+'.jpg'"
        alt="thumbnail"
        onerror="this.src='https://media.istockphoto.com/id/1219150055/vector/please-standby.jpg?s=612x612&w=0&k=20&c=6BMztLSIqWtcwcmRXmo7jOLitrFw7tTBVedLzbIrqZs='">
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
