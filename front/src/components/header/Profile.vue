<script setup>
import {ref} from 'vue';
import userStore from "@/state/userStore.js";
import {authInstance} from "@/module/axiosFactory.js";
import ProfileImg from "@/components/header/ProfileImg.vue";

const isModalVisible = ref(false);
const profileRef = ref(null);
const modalStyle = ref({ top: '0px', left: '0px' });

function toggleModal() {
  isModalVisible.value = !isModalVisible.value;
  if (isModalVisible.value && profileRef.value) {
    const rect = profileRef.value.getBoundingClientRect();
    modalStyle.value = {
      top: `${rect.bottom}px`,
      left: `${rect.left}px`
    };
  }
}

const logout = () => {
  authInstance.post("/api/member/logout");
  userStore.commit("logout");
  isModalVisible.value = false;
};

</script>

<template>
  <div>
    <div class="profile" ref="profileRef" @click="toggleModal">
      <ProfileImg :imgPath="userStore.state.user.avatar"/>
    </div>

    <div
        v-if="isModalVisible"
        class="modal"
        :style="modalStyle"
    >
      <div class="modal__content">
        <span class="modal__text__big">{{ userStore.state.user["nickname"] }}</span>
      </div>
      <div class="divider"></div>
      <div class="modal__content__body">
        <div>스튜디오</div>
        <div>내 정보</div>
        <div @click="logout">로그아웃</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 3px solid #7e7e7e;
  position: relative;
  cursor: pointer;
}

.modal {
  position: absolute;
  background: #1d1f22;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  padding: 10px;
  z-index: 10;
  width: 120px;
}

.modal__content {
  font-weight: bold;
  margin-bottom: 10px;
}

.modal__content span {
  text-align: right;
}
.modal__content__body div {
  width: 100%;
  height: 30px;
  cursor: pointer;
  text-align: right;
  border-radius: 5px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.modal__content__body div:hover {
  background-color: #dfe2ea;
  color: #141516;
  transition: background-color 0.2s, color 0.2s;
}

.divider {
  height: 1px;
  width: 100%;
  background-color: #dfe2ea;
  margin: 10px 0;
}

.modal__text__big {
  font-size: 25px;
  font-weight: 100;
}
</style>
