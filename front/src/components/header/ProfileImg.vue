<template>
  <img :src="profile" alt="프로필" class="profile__img">
</template>

<script setup>
import {computed, ref} from "vue";

const props = defineProps(['imgPath']);
const AWS_CDN_PATH = import.meta.env.VITE_AWS_CDN_PATH;
const imgPath = ref(props.imgPath);

const profile = computed(() => {
  return imgPath.value === "" ? "/src/assets/avatar.png" : AWS_CDN_PATH + "/" + imgPath;
});

</script>

<style scoped>

.profile__img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

</style>
