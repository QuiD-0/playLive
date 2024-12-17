import {createRouter, createWebHistory} from "vue-router";
import Live from "@/components/live/Live.vue";
import LiveList from "@/components/live/LiveList.vue";
import MyPage from "@/components/mypage/MyPage.vue";
import Login from "@/components/mypage/Login.vue";
import SignUp from "@/components/mypage/SignUp.vue";

const routes = [
    {
        path: '/',
        component: LiveList
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/signup',
        component: SignUp
    },
    {
        path: '/live/:channel',
        component: Live
    },
    {
        path: '/me',
        component: MyPage
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
