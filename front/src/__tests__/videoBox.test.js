import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'

vi.mock('hls.js', () => {
    class HlsMock {
        loadSource() {}
        attachMedia() {}
    }
    HlsMock.isSupported = () => true
    return { default: HlsMock }
})

vi.mock('@/state/clientStore.js', () => ({
    default: {
        state: { watchingChannel: 'test-channel' },
    },
}))

let VideoBox

describe('VideoBox - template ref 사용', () => {
    beforeEach(async () => {
        const mod = await import('@/components/live/VideoBox.vue')
        VideoBox = mod.default
    })

    it('video 요소가 template ref로 연결된다 (id 속성 없음)', () => {
        const wrapper = mount(VideoBox)
        const video = wrapper.find('video')
        expect(video.exists()).toBe(true)
        expect(video.attributes('id')).toBeUndefined()
    })

    it('document.getElementById를 사용하지 않는다', async () => {
        const spy = vi.spyOn(document, 'getElementById')
        mount(VideoBox)
        await flushPromises()
        expect(spy).not.toHaveBeenCalled()
        spy.mockRestore()
    })
})
