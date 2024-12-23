<script setup>
import NavBar from "@/components/mypage/NavBar.vue";
</script>

<template>
  <div class="container">
    <div class="navbar">
      <NavBar/>
    </div>
    <div class="content">
      <div class="wrap">
        <RouterView/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
}

.navbar {
  width: 250px;
  border-right: 1px solid #ddd;
  height: 100vh;
  top: 0;
  left: 0;
}

.content {
  flex: 1;
  background-color: #e6e6e6;
}

.wrap {
  display: flex;
  justify-content: center;
  margin: 50px auto;
}
</style>
