import {createRouter, createWebHistory} from "vue-router";
import userStore from "@/state/userStore.js";

const routes = [
    {
        path: '/',
        component: () => import("@/components/live/LiveList.vue")
    },
    {
        path: '/login',
        component: () => import("@/components/mypage/Login.vue")
    },
    {
        path: '/signup',
        component: () => import("@/components/mypage/SignUp.vue")
    },
    {
        path: '/live/:channel',
        component: () => import("@/components/live/Live.vue")
    },
    {
        path: '/studio',
        component: () => import("@/components/mypage/Studio.vue"),
        meta: { requiresAuth: true },
        children: [
            { path: 'me', component: () => import("@/components/mypage/StageManage.vue") },
            { path: 'profile', component: () => import("@/components/mypage/ProfileManage.vue") },
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
