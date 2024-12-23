<script setup>
</script>

<template>
  <div class="studio-container">
    <div class="studio-header">내 스튜디오</div>
    <div class="studio-box">
      <div class="form-group">
        <label for="title">방송 제목</label>
        <input id="title" type="text" placeholder="방송 제목을 입력해주세요" />
      </div>
      <div class="form-group">
        <label for="description">방송 설명</label>
        <input id="description" type="text" placeholder="방송 설명을 입력해주세요" />
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

.studio-header {
  width: 800px;
  font-size: 24px;
  margin-bottom: 10px;
  margin-left: 5px;
  text-align: left;
}

.studio-box {
  border: 1px solid #ddd; /* 박스 테두리 */
  border-radius: 8px; /* 둥근 모서리 */
  padding: 20px;
  background-color: #ffffff; /* 박스 배경색 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 박스 그림자 */
}

.form-group {
  margin-bottom: 15px;
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
</style>
