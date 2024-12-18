<script setup>
import ViewCounter from "@/components/live/ViewCounter.vue";
import {computed, onMounted} from "vue";
import clientStore from "@/state/clientStore.js";
import instance from "@/module/axiosFactory.js";

const channel = computed(() => clientStore.state.watchingChannel);

const stageInfo = async () => {
  const response = await instance.get(`/api/stage/info/${channel.value}`);
  console.log(response.data);
}

onMounted(() => {
  stageInfo();
});

</script>

<template>
  <div class="stage__info">
    <div class="stage__title">타이틀 입니당</div>
    <div>
      <div>
        채널아바타
      </div>
      <div>
        <div>채널명</div>
        <div>닉네임</div>
      </div>
      <ViewCounter/>
      <div>uptime</div>
    </div>
  </div>
</template>

<style scoped>
.stage__title {
  font-size: 2em;
  margin-bottom: 10px;
}
</style>
