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
  font-size: 16px;
  font-weight: 400;
  border: gainsboro 2px solid;
  border-radius: 7px;
  padding: 5px 10px;
}
.login__button:hover {
  color: white;
  background-color: gainsboro;
  cursor: pointer;
  transition: color 0.2s, background-color 0.2s;
}
</style>

