<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

const loginPage = () => {
  router.push("/login");
};
</script>

<template>
  <div class="login__button" @click="loginPage()">
    로그인
  </div>
</template>

<style scoped>
.login__button {
  color: gainsboro;
  font-size: 17px;
  font-weight: 400;
  padding: 5px 10px;
}
.login__button:hover {
  color: white;
  cursor: pointer;
  transition: color 0.2s, background-color 0.2s;
}
</style>

