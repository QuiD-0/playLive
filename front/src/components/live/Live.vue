<script setup>
import VideoBox from "@/components/live/VideoBox.vue";
import ViewCounter from "@/components/live/ViewCounter.vue";
import NotFound from "@/components/live/NotFound.vue";
import {onBeforeMount, ref} from "vue";
import {useRoute} from 'vue-router';
import instance from "@/module/axiosFactory.js";

const route = useRoute();
const isAvailable = ref(false); // 채널 상태
const isLoading = ref(true); // 로딩 상태

onBeforeMount(async () => {
  await findChannel();
  isLoading.value = false;
});

const findChannel = async () => {
  try {
    const channel = route.params.channel;
    const response = await instance.get(`/api/member/check-available/${channel}`);
    isAvailable.value = response.data.message;
  } catch (error) {
    console.error("Error fetching channel:", error);
    isAvailable.value = false;
  }
};
</script>

<template>
  <div v-if="isLoading" class="loading">
    <div>Loading...</div>
  </div>
  <div v-else>
    <div v-if="isAvailable">
      <VideoBox/>
      <ViewCounter/>
    </div>
    <div v-else>
      <NotFound/>
    </div>
  </div>
</template>

<style scoped>
.loading {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 90vh;
  font-size: 2rem;
}

</style>
