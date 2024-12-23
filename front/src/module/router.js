import {createRouter, createWebHistory} from "vue-router";
import Live from "@/components/live/Live.vue";
import LiveList from "@/components/live/LiveList.vue";
import Login from "@/components/mypage/Login.vue";
import SignUp from "@/components/mypage/SignUp.vue";
import Studio from "@/components/mypage/Studio.vue";
import StageManage from "@/components/mypage/StageManage.vue";
import ProfileManage from "@/components/mypage/ProfileManage.vue";

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
        path: '/studio',
        component: Studio,
        children: [
            { path: 'me', component: StageManage },
            { path: 'profile', component: ProfileManage },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
