<script setup>
import {useRouter} from "vue-router";
import {ref} from "vue";
import User from "@/model/User.js";
import {errorToast} from "@/module/toast.js";
import {authInstance, instance} from "@/module/axiosFactory.js";
import userStore from "@/state/userStore.js";

const router = useRouter();
const id = ref('');
const password = ref('');

if(userStore.state.accessToken !== null) {
  router.push('/');
}

const login = () => {
  let request = new User(id.value, password.value);
  if (!request.validate()) {
    errorToast(request.message);
    return
  }
  instance.post('/api/member/login', request)
      .then(response => {
        userStore.commit('setAccessToken', response.data.message.accessToken);
        userStore.commit('setRefreshToken', response.data.message.refreshToken);
        getUserInfo();
      })
      .catch(error => {
        errorToast(error.response.data.message);
      });
}

const getUserInfo = () => {
  authInstance.get('/api/member/me')
      .then(response => {
        userStore.commit('setUser', response.data.message);
        router.back();
      })
      .catch(error => {
        console.log(error);
      });
}

const signUpPage = () => {
  router.push('/signup');
};

</script>

<template>
  <div class="login">
    <div class="login__container">
      <div class="login__container__title">
        로그인
      </div>
      <div class="login__container__input">
        <input type="text" placeholder="아이디" v-model="id" @keyup.enter="login"/>
        <input type="password" placeholder="비밀번호" v-model="password" @keyup.enter="login"/>
      </div>
      <div class="login__container__button">
        <button @click="login">로그인</button>
      </div>
      <div class="divider"/>
      <div class="signup__button">
        <button @click="signUpPage">회원가입</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login__container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 400px;
  border: 1px solid #dfe2ea;
  border-radius: 10px;
}

.login__container__title {
  font-size: 24px;
  margin-bottom: 20px;
}

.login__container__input {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.login__container__input input {
  width: 300px;
  height: 30px;
  margin-bottom: 10px;
}

.login__container__button button {
  width: 300px;
  height: 30px;
  background-color: #141516;
  color: #dfe2ea;
  border: none;
  border-radius: 5px;
}

.login__container__button button:hover {
  background-color: #dfe2ea;
  color: #141516;
  cursor: pointer;
  transition: background-color 0.2s, color 0.2s;
}

.divider {
  height: 1px;
  width: 300px;
  background-color: #dfe2ea;
  margin: 5px 0;
}

.signup__button button {
  width: 300px;
  height: 30px;
  background-color: #dfe2ea;
  color: #141516;
  border: none;
  border-radius: 5px;
}

.signup__button button:hover {
  background-color: #141516;
  color: #dfe2ea;
  cursor: pointer;
  transition: background-color 0.2s, color 0.2s;
}

</style>
