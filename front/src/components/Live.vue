<script>
import VideoBox from "@/components/VideoBox.vue";
import ViewCounter from "@/components/ViewCounter.vue";

export default {
  name: 'Live',
  components: {
    VideoBox: VideoBox,
    ViewCounter: ViewCounter,
  },
};
</script>

<template>
    <VideoBox />
    <ViewCounter />
</template>
