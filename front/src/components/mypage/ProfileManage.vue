<script setup>
import ProfileImg from "@/components/header/ProfileImg.vue";
</script>

<template>
  <div class="studio-container">
    <div class="box">
      <div class="studio-header">내 프로필</div>
      <div class="studio-box">
        <div class="form-group">
          <label for="title">닉네임</label>
          <input id="title" type="text" placeholder="닉네임" />
        </div>
        <div class="form-group">
          <label for="description">이메일</label>
          <input id="description" type="text" placeholder="내 이메일" />
        </div>
        <div class="avatar">
          <div>내 프로필</div>
          <ProfileImg :img-path="''"/>
        </div>
        <div class="button">업데이트</div>
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
</style>
