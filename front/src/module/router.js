import {createRouter, createWebHistory} from "vue-router";
import Live from "@/components/live/Live.vue";
import LiveList from "@/components/live/LiveList.vue";
import Login from "@/components/mypage/Login.vue";
import SignUp from "@/components/mypage/SignUp.vue";
import Studio from "@/components/mypage/Studio.vue";
import StageManage from "@/components/mypage/StageManage.vue";
import ProfileManage from "@/components/mypage/ProfileManage.vue";
import userStore from "@/state/userStore.js";

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
        meta: { requiresAuth: true },
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

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!userStore.state.accessToken) {
            next('/login')
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router
