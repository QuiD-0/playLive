import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { createRouter, createMemoryHistory } from 'vue-router'
import { defineComponent, h } from 'vue'

const Stub = defineComponent({ render: () => h('div') })

function createTestRouter() {
    return createRouter({
        history: createMemoryHistory(),
        routes: [
            { path: '/', component: Stub },
            { path: '/login', component: Stub },
        ],
    })
}

vi.mock('@/module/axiosFactory.js', () => ({
    instance: {
        get: vi.fn().mockResolvedValue({ data: 'room-123' }),
    },
    authInstance: {
        get: vi.fn().mockResolvedValue({ data: {} }),
    },
}))

vi.mock('@/state/clientStore.js', () => ({
    default: {
        state: { watchingChannel: 'test-channel' },
    },
}))

const userStoreState = {
    user: null,
    accessToken: null,
}

vi.mock('@/state/userStore.js', () => ({
    default: {
        state: userStoreState,
    },
}))

let Chatting

describe('Chatting - 로그인 상태별 UI', () => {
    beforeEach(async () => {
        userStoreState.user = null
        userStoreState.accessToken = null
        const mod = await import('@/components/live/Chatting.vue')
        Chatting = mod.default
    })

    it('비로그인 시 채팅 입력창 대신 로그인 안내가 표시된다', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        const wrapper = mount(Chatting, {
            global: { plugins: [router] },
        })
        await flushPromises()

        expect(wrapper.find('.chatting__login-prompt').exists()).toBe(true)
        expect(wrapper.find('.chatting__login-prompt p').text()).toContain('로그인 후 채팅에 참여할 수 있습니다')
        expect(wrapper.find('.chatting__login-button').exists()).toBe(true)
        expect(wrapper.find('.chatting__container').exists()).toBe(false)
    })

    it('비로그인 시 로그인 버튼이 /login으로 링크된다', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        const wrapper = mount(Chatting, {
            global: { plugins: [router] },
        })
        await flushPromises()

        const loginLink = wrapper.find('.chatting__login-button')
        expect(loginLink.attributes('href')).toBe('/login')
    })

    it('로그인 시 채팅 입력창이 표시된다', async () => {
        userStoreState.accessToken = 'test-token'
        userStoreState.user = { id: 1, nickname: '테스터' }

        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        const wrapper = mount(Chatting, {
            global: { plugins: [router] },
        })
        await flushPromises()

        expect(wrapper.find('.chatting__container').exists()).toBe(true)
        expect(wrapper.find('.chatting__login-prompt').exists()).toBe(false)
    })
})
