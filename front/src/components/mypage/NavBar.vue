<template>
  <div @click="tab1">탭 1</div>
  <div @click="tab2">탭 2</div>
</template>
<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

const tab1 = () => {
  router.push("me");
};

const tab2 = () => {
  router.push("profile");
};
</script>
