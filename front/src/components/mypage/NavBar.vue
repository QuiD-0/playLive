<template>
  <div
      @click="setActive('me')"
      :class="['box', { active: activeBox === 'me' }]"
  >
    내 스튜디오
  </div>
  <div
      @click="setActive('profile')"
      :class="['box', { active: activeBox === 'profile' }]"
  >
    프로필
  </div>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const activeBox = ref('me');

const setActive = (box) => {
  activeBox.value = box;
  router.push(box);
};
</script>

<style scoped>
.box {
  padding: 10px;
  margin: 10px;
  cursor: pointer;
  border-radius: 10px;
}

.active {
  color: #000000;
  background-color: #dfe2ea;
}
</style>
