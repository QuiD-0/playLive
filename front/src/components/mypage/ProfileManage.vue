<script setup>
import {computed, onMounted, ref} from "vue";
import {errorToast, successToast} from "@/module/toast.js";
import {authInstance} from "@/module/axiosFactory.js";

const AWS_CDN_PATH = import.meta.env.VITE_AWS_CDN_PATH;
const avatar = ref("");
const nickName = ref("");
const email = ref("");

const profile = computed(() => {
  return avatar.value === "" ? "/avatar.png" : AWS_CDN_PATH + "/" + avatar.value;
});

onMounted(() => {
  getProfile();
});

const getProfile = () => {
  authInstance.get('/api/auth/member/me').then(response => {
    avatar.value = response.data.message.avatar;
    nickName.value = response.data.message.nickname;
    email.value = response.data.message.email;
  }).catch(_ => {
    errorToast("프로필 정보를 가져오는데 실패했습니다.");
  });
};

const updateProfile = () => {
  let request = {
    "nickName": nickName.value,
  };

  authInstance.put('/api/auth/member', request).then(_ => {
    successToast("업데이트 성공");
  }).catch(_ => {
    errorToast("업데이트 실패");
  });
};

const updateAvatar = () => {
  let data = new FormData();
  let file = document.createElement("input");
  file.type = "file";
  file.accept = "image/*";
  file.click();

  file.onchange = () => {
    data.append("iamge", file.files[0]);
    authInstance.post('/api/auth/member/avatar', data).then(response => {
      avatar.value = response.data.message;
      successToast("프로필 사진 변경 성공");
    }).catch(_ => {
      errorToast("프로필 사진 변경 실패");
    });
  };
}

</script>

<template>
  <div class="studio-container">
    <div class="box">
      <div class="studio-header">내 프로필</div>
      <div class="studio-box">
        <div class="form-group">
          <label for="nickname">닉네임</label>
          <input id="nickname" type="text" placeholder="닉네임" v-model="nickName"/>
        </div>
        <div class="form-group">
          <label for="email">이메일</label>
          <input id="email" type="text" placeholder="내 이메일" disabled :value="email"/>
        </div>
        <div class="avatar">
          <div>내 프로필</div>
          <img :src="profile" alt="프로필" class="profile__img">
          <div @click="updateAvatar">프로필 변경</div>
        </div>
        <div class="button" @click="updateProfile">업데이트</div>
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
  display: flex; /* flexbox 추가 */
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

.button {
  align-self: flex-end; /* 버튼을 오른쪽으로 이동 */
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

.avatar {
  width: 60px;
}

.profile__img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}
</style>
