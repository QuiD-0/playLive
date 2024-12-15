<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

const home = () => {
  router.push("/");
};
</script>

<template>
    <div @click="home">
      <img src="@/assets/jaezzck.png" alt="jaezzck Logo"/>
    </div>
</template>

<style scoped>
img {
  height: 30px;
  margin: 0 auto;
  width: 90px;
}
</style>
