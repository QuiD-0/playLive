<script setup>
import ViewCounter from "@/components/live/ViewCounter.vue";
import {computed, onMounted, reactive} from "vue";
import clientStore from "@/state/clientStore.js";
import {instance} from "@/module/axiosFactory.js";
import ProfileImg from "@/components/header/ProfileImg.vue";

const channel = computed(() => clientStore.state.watchingChannel);
const stage = reactive({
  title: "",
  avatar: "",
  nickname: "",
  startDateTime: ""
});

const getStageInfo = async () => {
  const response = await instance.get(`/api/stage/info/${channel.value}`);
  stage.value = response.data.message;
  await updateTitle()
}

const updateTitle = async () => {
  document.title = stage.nickname + "-" + stage.title;
}

onMounted(() => {
  getStageInfo()
});
</script>

<template>
  <div class="stage__info">
    <div class="stage__title">{{ stage.title }}</div>
    <div>
      <div>
        <ProfileImg :imgPath="stage.avatar"/>
      </div>
      <div>
        <div>{{ stage.nickname }}</div>
        <ViewCounter/>
        <div>uptime</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.stage__title {
  font-size: 2em;
  margin-bottom: 10px;
}
</style>
