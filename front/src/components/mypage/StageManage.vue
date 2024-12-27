<script setup>
import {computed, onMounted, ref} from "vue";
import {authInstance} from "@/module/axiosFactory.js";
import {errorToast, successToast} from "@/module/toast.js";

const title = ref('');
const description = ref('');
const streamKey = ref('');
const streamKeyToggle = ref(true);

onMounted(() => {
  getMyStageInfo();
});

const getMyStageInfo = async () => {
  let response = await authInstance.get('/api/auth/stage/info/me')
  title.value = response.data.message.title;
  description.value = response.data.message.description;
  streamKey.value = response.data.message.streamKey;
};

const updateStageInfo = async () => {
  let request = {
    "title": title.value,
    "description": description.value
  };

  authInstance.put('/api/auth/stage/info',request).then(_ => {
    successToast("업데이트 성공");
  }).catch(_ => {
    errorToast("업데이트 실패");
  });
};

const streamKeyDisplay = computed(() => {
  return streamKeyToggle.value ? '*'.repeat(streamKey.value.length) : streamKey.value;
});

const toggleStreamKey = () => {
  streamKeyToggle.value = !streamKeyToggle.value;
};

const copyStreamKey = () => {
  navigator.clipboard.writeText(streamKey.value).then(_ => {
    successToast("복사 성공");
  }).catch(_ => {
    errorToast("복사 실패");
  });
};

</script>

<template>
  <div class="studio-container">
    <div class="box">
      <div class="studio-header">내 스튜디오</div>
      <div class="studio-box">
        <div class="form-group">
          <label for="title">방송 제목</label>
          <input id="title" type="text" placeholder="방송 제목을 입력해주세요" v-model="title" />
        </div>
        <div class="form-group">
          <label for="description">방송 설명</label>
          <input id="description" type="text" placeholder="방송 설명을 입력해주세요" v-model="description"/>
        </div>
        <div class="button" @click="updateStageInfo">업데이트</div>
      </div>
    </div>
    <div class="box">
      <div class="studio-header">내 스트림키</div>
      <div class="studio-box">
        <div class="stream__key__container">
          <input type="text" disabled :value="streamKeyDisplay" class="stream__key">
          <div v-if="streamKeyToggle"
              class="hide"
              @click="toggleStreamKey">보이기</div>
          <div v-else
              class="hide"
              @click="toggleStreamKey">숨기기</div>
          <div class="copy" @click="copyStreamKey">복사</div>
          <div class="regenerate">스트림키 재생성</div>
        </div>
      </div>
    </div>
    <div class="box">
      <div class="studio-header">방송 히스토리</div>
      <div class="studio-box">
        <div>검색 기간</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-container {
  width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.box {
  margin-bottom: 20px;
}

.studio-header {
  width: 800px;
  font-size: 24px;
  margin-bottom: 10px;
  margin-left: 5px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
  font-size: 14px;
  color: #333;
}

input {
  width: 730px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

input::placeholder {
  color: #aaa;
}

.studio-box {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

.button {
  align-self: flex-end;
  width: 100px;
  height: 40px;
  background-color: #141516;
  color: #dfe2ea;
  border: none;
  border-radius: 5px;
  text-align: center;
  line-height: 40px;
  cursor: pointer;
}

.stream__key__container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.hide {
  background-color: #cfffc3;
  cursor: pointer;
  padding: 10px 15px;
  border-radius: 5px;
}

.copy {
  background-color: #b5d8ff;
  cursor: pointer;
  padding: 10px 15px;
  border-radius: 5px;
}

.regenerate {
  background-color: #ffbbbb;
  cursor: pointer;
  padding: 10px 15px;
  border-radius: 5px;
}

.stream__key {
  width: 450px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
  background-color: #f5f5f5;
}
</style>
