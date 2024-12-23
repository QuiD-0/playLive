<script setup>
import ViewCounter from "@/components/live/ViewCounter.vue";
import {computed, onBeforeUnmount, onMounted, ref} from "vue";
import clientStore from "@/state/clientStore.js";
import {instance} from "@/module/axiosFactory.js";

const AWS_CDN_PATH = import.meta.env.VITE_AWS_CDN_PATH;
const channel = computed(() => clientStore.state.watchingChannel);
const stage = ref({
  title: "",
  avatar: "",
  nickname: "",
  startDateTime: ""
});

const profile = computed(() => {
  return stage.value.avatar === "" ? "/avatar.png" : AWS_CDN_PATH + "/" + stage.value.avatar;
});

const getStageInfo = async () => {
  const response = await instance.get(`/api/stage/info/${channel.value}`);
  stage.value = response.data.message;
  await updateTitle()
}

const updateTitle = async () => {
  document.title = stage.value.nickname + "-" + stage.value.title;
}

const uptimeText = ref('');

const calculateUptime = () => {
  const startDateTime = new Date(stage.value.startDateTime);
  const now = new Date();
  const diff = now - startDateTime;

  const hours = Math.floor(diff / 1000 / 60 / 60)
      .toString()
      .padStart(2, '0');
  const minutes = Math.floor(diff / 1000 / 60 % 60)
      .toString()
      .padStart(2, '0');
  const seconds = Math.floor(diff / 1000 % 60)
      .toString()
      .padStart(2, '0');

  return `${hours}:${minutes}:${seconds}`;
};

let intervalId;

onMounted(async () => {
  await getStageInfo();
  uptimeText.value = calculateUptime();
  intervalId = setInterval(() => {
    uptimeText.value = calculateUptime();
  }, 1000);
});

onBeforeUnmount(() => {
  clearInterval(intervalId);
});
</script>

<template>
  <div class="stage__info">
    <div class="stage__title">{{ stage.title }}</div>
    <div class="channel__box">
      <div class="profile">
        <img :src="profile" alt="프로필" class="profile__img">
      </div>
      <div>
        <div class="channel__user">{{ stage.nickname }}</div>
        <ViewCounter/>
        <div class="uptime">{{ uptimeText }}전 시작됨</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.stage__title {
  font-size: 2em;
  margin-bottom: 10px;
}

.channel__box {
  display: flex;
  align-items: center;
}

.channel__user {
  font-size: 1.4em;
}

.profile {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 3px solid #7e7e7e;
  position: relative;
  margin-right: 10px;
}
.profile__img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.uptime {
  color: #7e7e7e;
}
</style>
