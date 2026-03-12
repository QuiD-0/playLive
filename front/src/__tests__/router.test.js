import { describe, it, expect, beforeEach } from 'vitest'
import { createRouter, createWebHistory } from 'vue-router'
import { shallowMount } from '@vue/test-utils'
import { defineComponent, h } from 'vue'
import userStore from '@/state/userStore.js'

const Stub = defineComponent({ render: () => h('div') })

function createTestRouter() {
    const router = createRouter({
        history: createWebHistory(),
        routes: [
            { path: '/', component: Stub },
            { path: '/login', component: Stub },
            {
                path: '/studio',
                component: Stub,
                meta: { requiresAuth: true },
                children: [
                    { path: 'me', component: Stub },
                    { path: 'profile', component: Stub },
                ]
            },
        ],
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

    return router
}

describe('Router Guard', () => {
    beforeEach(() => {
        userStore.state.accessToken = null
        userStore.state.user = null
    })

    it('비로그인 시 /studio 접근하면 /login으로 리다이렉트', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        await router.push('/studio')
        expect(router.currentRoute.value.path).toBe('/login')
    })

    it('비로그인 시 /studio/me 접근하면 /login으로 리다이렉트', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        await router.push('/studio/me')
        expect(router.currentRoute.value.path).toBe('/login')
    })

    it('로그인 상태면 /studio 접근 가능', async () => {
        userStore.state.accessToken = 'test-token'

        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        await router.push('/studio')
        expect(router.currentRoute.value.path).toBe('/studio')
    })

    it('공개 페이지는 비로그인도 접근 가능', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        expect(router.currentRoute.value.path).toBe('/')
    })
})
