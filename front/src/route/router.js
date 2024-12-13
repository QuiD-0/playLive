import {createRouter, createWebHistory} from "vue-router";
import Home from "@/components/Home.vue";
import Live from "@/components/Live.vue";

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/live/:channel',
        component: Live
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
