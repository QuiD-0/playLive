<template>
  <div class="chatting">
    <div class="chatting__box" ref="chatBox" @scroll="handleScroll">
      <div v-for="chat in chatList" :key="chat.id" class="chatting__text">
        {{ chat.nickname }} : {{ chat.message }}
      </div>
    </div>
    <div class="chatting__container">
      <div class="chatting__container__input">
        <textarea placeholder="채팅을 입력하세요." @keyup.enter="sendMessage"/>
      </div>
      <div class="chatting__container__button">
        <button @click="sendMessage">→</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, nextTick, onMounted, onUnmounted, ref} from "vue";
import userStore from "@/state/userStore.js";
import Chat from "@/model/Chat.js";
import {instance} from "@/module/axiosFactory.js";
import clientStore from "@/state/clientStore.js";

const SERVER_URL = import.meta.env.VITE_SOCKET_URL;
const chatroomId = ref("");
const userId = computed(() => {
  if (userStore.state.user == null) return 0;
  return userStore.state.user.id;
});
const nickname = computed(() => {
  if (userStore.state.user == null) return "익명";
  return userStore.state.user.nickname;
});
const chatList = ref([]);
const chatBox = ref(null);
const isAutoScroll = ref(true);
let ws = new WebSocket(`${SERVER_URL}/chat`);

onMounted(() => {
  init();
  setInterval(() => {
    ping();
  }, 10_000);
});

const init = () => {
  let channel = clientStore.state.watchingChannel;
  instance.get(`/api/chat/roomId/${channel}`).then((response) => {
    chatroomId.value = response.data;
    openWebSocket();
  });
};

const openWebSocket = () => {
  let data = new Chat(chatroomId.value, userId.value, "", "join", nickname.value);
  ws.send(JSON.stringify(data));
};

const ping = () => {
  console.log("ping");
  let data = new Chat(chatroomId.value, userId.value, "", "ping", nickname.value);
  ws.send(JSON.stringify(data));
};

onUnmounted(() => {
  let data = new Chat(chatroomId.value, userId.value, "", "leave", nickname.value);
  ws.send(JSON.stringify(data));
  ws.close();
});

const handleScroll = () => {
  if (!chatBox.value) return;

  const {scrollTop, scrollHeight, clientHeight} = chatBox.value;
  isAutoScroll.value = scrollTop + clientHeight >= scrollHeight - 50;
};

const sendMessage = () => {
  let message = document.querySelector("textarea").value;
  let data = new Chat(chatroomId.value, userId.value, message.trim(), "chat", nickname.value);
  if (data.message.trim() === "") {
    document.querySelector("textarea").value = "";
    return;
  }
  ws.send(JSON.stringify(data));
  document.querySelector("textarea").value = "";
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
</style>
