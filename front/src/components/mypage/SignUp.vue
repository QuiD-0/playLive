<script setup>
import {authInstance, instance} from "@/module/axiosFactory.js"
import {ref} from "vue";
import {errorToast} from "@/module/toast.js";
import router from "@/module/router.js";
import User from "@/model/User.js";
import userStore from "@/state/userStore.js";

const id = ref('');
const password = ref('');
const email = ref('');
const nickname = ref('');

const signUp = () => {
  let request = new SignUpRequest(id.value, password.value, email.value, nickname.value);
  if (!request.validate()) {
    errorToast(request.message);
    return;
  }
  instance.post('/api/member/register', request)
      .then(_ => {
        login();
      })
      .catch(error => {
        errorToast(error.response.data.message);
      });
};

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
        router.push('/');
      })
      .catch(error => {
        console.log(error);
      });
}


class SignUpRequest{
  constructor(username, password, email, nickname) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
  }
  message = '';

  validate() {
    if (this.username === '') {
      this.message = '아이디를 입력해주세요.';
      return false;
    }
    if (this.password === '') {
      this.message = '비밀번호를 입력해주세요.';
      return false;
    }
    if (this.email === '') {
      this.message = '이메일을 입력해주세요.';
      return false;
    }
    if (this.nickname === '') {
      this.message = '닉네임을 입력해주세요.';
      return false;
    }
    if(this.email.indexOf('@') === -1 || this.email.indexOf('.') === -1){
      this.message = '이메일 형식이 올바르지 않습니다.';
      return false;
    }
    return true;
  }
}
</script>

<template>
  <div class="signup">
    <div class="signup__container">
      <div class="signup__container__title">
        회원가입
      </div>
      <div class="signup__container__input">
        <input type="text" placeholder="아이디" v-model="id"/>
        <input type="password" placeholder="비밀번호" v-model="password"/>
        <input type="email" placeholder="이메일" v-model="email"/>
        <input type="text" placeholder="닉네임" v-model="nickname"/>
      </div>
      <div class="signup__container__button">
        <button @click="signUp">회원가입</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.signup {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.signup__container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 400px;
  border: 1px solid #dfe2ea;
  border-radius: 10px;
}

.signup__container__title {
  font-size: 24px;
  margin-bottom: 20px;
}

.signup__container__input {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.signup__container__input input {
  width: 300px;
  height: 30px;
  margin-bottom: 10px;
}

.signup__container__button button {
  width: 300px;
  height: 30px;
  background-color: #141516;
  color: #dfe2ea;
  border: none;
  border-radius: 5px;
}

.signup__container__button button:hover {
  background-color: #dfe2ea;
  color: #141516;
  cursor: pointer;
  transition: background-color 0.2s, color 0.2s;
}

</style>
