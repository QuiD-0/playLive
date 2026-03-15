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

const mockGet = vi.fn((url) => {
    if (url.includes('/history/')) {
        return Promise.resolve({ data: [] })
    }
    return Promise.resolve({ data: 'room-123' })
})

vi.mock('@/module/axiosFactory.js', () => ({
    instance: {
        get: mockGet,
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

describe('Chatting - 메시지 상한', () => {
    beforeEach(async () => {
        userStoreState.accessToken = 'test-token'
        userStoreState.user = { id: 1, nickname: '테스터' }
        const mod = await import('@/components/live/Chatting.vue')
        Chatting = mod.default
    })

    it('chatList가 1000개를 초과하면 오래된 메시지를 제거한다', async () => {
        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        const wrapper = mount(Chatting, {
            global: { plugins: [router] },
        })
        await flushPromises()

        for (let i = 0; i < 1001; i++) {
            wrapper.vm.chatList.push({ id: `msg-${i}`, nickname: 'user', message: `msg ${i}` })
        }
        // trigger the watch/cap manually if needed
        await flushPromises()

        expect(wrapper.vm.chatList.length).toBeLessThanOrEqual(1000)
    })

    it('입장 시 히스토리 API를 호출한다', async () => {
        const historyData = [
            { id: '1', nickname: 'user1', message: 'hello', regDate: '2026-03-15T10:00:00' },
            { id: '2', nickname: 'user2', message: 'world', regDate: '2026-03-15T10:00:01' },
        ]
        mockGet.mockImplementation((url) => {
            if (url.includes('/history/')) {
                return Promise.resolve({ data: historyData })
            }
            return Promise.resolve({ data: 'room-123' })
        })

        const router = createTestRouter()
        await router.push('/')
        await router.isReady()

        const wrapper = mount(Chatting, {
            global: { plugins: [router] },
        })
        await flushPromises()

        expect(mockGet).toHaveBeenCalledWith(expect.stringContaining('/history/'))
        expect(wrapper.vm.chatList.length).toBe(2)
    })
})
