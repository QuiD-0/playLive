<template>
  <div class="chatting">
    <div class="chatting__box" ref="chatBox" @scroll="handleScroll">
      <div v-for="chat in chatList" :key="chat.id" class="chatting__text">
        {{ chat.nickname }} : {{ chat.message }}
      </div>
    </div>
    <div v-if="isLoggedIn" class="chatting__container">
      <div class="chatting__container__input">
        <textarea ref="chatInput" placeholder="채팅을 입력하세요." @keyup.enter="sendMessage"/>
      </div>
      <div class="chatting__container__button">
        <button @click="sendMessage">→</button>
      </div>
    </div>
    <div v-else class="chatting__login-prompt">
      <p>로그인 후 채팅에 참여할 수 있습니다</p>
      <router-link to="/login" class="chatting__login-button">로그인</router-link>
    </div>
  </div>
</template>

<script setup>
import {computed, nextTick, onMounted, onUnmounted, ref, watch} from "vue";
import userStore from "@/state/userStore.js";
import Chat from "@/model/Chat.js";
import {instance} from "@/module/axiosFactory.js";
import clientStore from "@/state/clientStore.js";

const SERVER_URL = import.meta.env.VITE_SOCKET_URL;
const MAX_MESSAGES = 1000;
const chatroomId = ref("");
const isLoggedIn = computed(() => userStore.state.accessToken != null);
const chatList = ref([]);
const chatBox = ref(null);
const chatInput = ref(null);
const isAutoScroll = ref(true);
let ws = null;
let ping = null;

watch(() => chatList.value.length, (len) => {
  if (len > MAX_MESSAGES) {
    chatList.value.splice(0, len - MAX_MESSAGES);
  }
});

onMounted(() => {
  init();
});

const init = () => {
  let channel = clientStore.state.watchingChannel;
  instance.get(`/api/chat/roomId/${channel}`).then((response) => {
    chatroomId.value = response.data;
    loadHistory();
    if (isLoggedIn.value) {
      connectWebSocket();
    }
  });
};

const loadHistory = () => {
  instance.get(`/api/chat/history/${chatroomId.value}?size=50`).then((response) => {
    chatList.value = response.data;
    nextTick(() => {
      if (chatBox.value) {
        chatBox.value.scrollTop = chatBox.value.scrollHeight;
      }
    });
  });
};

const connectWebSocket = () => {
  const token = userStore.state.accessToken;
  ws = new WebSocket(`${SERVER_URL}/chat?token=${token}`);

  ws.onopen = () => {
    let data = new Chat(chatroomId.value, "", "join");
    ws.send(JSON.stringify(data));
    ping = setInterval(() => pong(), 10_000);
  };

  ws.onmessage = async (event) => {
    let message = JSON.parse(event.data);
    chatList.value.push(message);

    await nextTick();
    if (chatBox.value && isAutoScroll.value) {
      chatBox.value.scrollTo({
        top: chatBox.value.scrollHeight,
        behavior: "smooth",
      });
    }
  };
};

const pong = () => {
  if (ws && ws.readyState === WebSocket.OPEN) {
    let data = new Chat(chatroomId.value, "", "ping");
    ws.send(JSON.stringify(data));
  }
};

onUnmounted(() => {
  if (ws && ws.readyState === WebSocket.OPEN) {
    let data = new Chat(chatroomId.value, "", "leave");
    ws.send(JSON.stringify(data));
    ws.close();
  }
  clearInterval(ping);
});

const handleScroll = () => {
  if (!chatBox.value) return;

  const {scrollTop, scrollHeight, clientHeight} = chatBox.value;
  isAutoScroll.value = scrollTop + clientHeight >= scrollHeight - 50;
};

const sendMessage = () => {
  if (!ws || ws.readyState !== WebSocket.OPEN) return;
  let message = chatInput.value.value;
  let data = new Chat(chatroomId.value, message.trim(), "chat");
  if (data.message.trim() === "") {
    chatInput.value.value = "";
    return;
  }
  ws.send(JSON.stringify(data));
  chatInput.value.value = "";
};
</script>

<style scoped>
.chatting {
  width: 95%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.chatting__box {
  width: 100%;
  font-size: 17px;
  height: calc(100% - 200px);
  overflow-y: scroll;
}

.chatting__container {
  width: 100%;
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chatting__container__input {
  margin-left: 25px;
  width: 80%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.chatting__container__input textarea {
  width: 100%;
  height: 50px;
  padding: 10px 15px;
  border: 1px solid #cccccc;
  border-radius: 8px;
  font-size: 16px;
  font-family: 'Arial', sans-serif;
  resize: none;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: border-color 0.3s, box-shadow 0.3s;
}

.chatting__container__input textarea:focus {
  border-color: #4e4e4e;
  box-shadow: 0 2px 8px rgba(122, 122, 122, 0.2);
  outline: none;
}

.chatting__container__button {
  display: flex;
  justify-content: center;
  align-items: center;
}

.chatting__container__button button {
  width: 40px;
  height: 40px;
  border: none;
  border-radius: 50%;
  font-size: 16px;
  font-family: 'Arial', sans-serif;
  color: #ffffff;
  background-color: #4e4e4e;
  cursor: pointer;
  transition: background-color 0.3s, box-shadow 0.3s;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.chatting__container__button button:hover {
  background-color: #4e4e4e;
  box-shadow: 0 4px 10px rgba(122, 122, 122, 0.2);
}

.chatting__container__button button:active {
  background-color: #4e4e4e;
  box-shadow: 0 2px 5px rgba(122, 122, 122, 0.2);
}

.chatting__text {
  margin: 10px;
  padding: 10px;
  border-radius: 10px;
  background-color: #f5f5f5;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s, box-shadow 0.3s;
}

.chatting__login-prompt {
  width: 100%;
  height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.chatting__login-prompt p {
  font-size: 14px;
  color: #888;
}

.chatting__login-button {
  padding: 8px 20px;
  background-color: #141516;
  color: #dfe2ea;
  border-radius: 5px;
  text-decoration: none;
  font-size: 14px;
  transition: background-color 0.3s;
}

.chatting__login-button:hover {
  background-color: #333;
}
</style>
