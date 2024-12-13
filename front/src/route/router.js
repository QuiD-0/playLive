import VideoBox from "@/components/VideoBox.vue";
import Home from "@/components/Home.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/live/:channel',
        component: VideoBox
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
