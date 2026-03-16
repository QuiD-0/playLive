import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'

vi.mock('@/module/axiosFactory.js', () => ({
    authInstance: {
        get: vi.fn().mockResolvedValue({
            data: { message: { avatar: '', nickname: 'tester', email: 'test@test.com' } },
        }),
        put: vi.fn().mockResolvedValue({ data: { message: { imgName: 'new-avatar.png' } } }),
    },
}))

vi.mock('@/state/userStore.js', () => ({
    default: {
        state: { accessToken: 'test-token' },
        commit: vi.fn(),
    },
}))

vi.mock('@/module/toast.js', () => ({
    successToast: vi.fn(),
    errorToast: vi.fn(),
}))

let ProfileManage

describe('ProfileManage - 파일 입력 ref 사용', () => {
    beforeEach(async () => {
        const mod = await import('@/components/mypage/ProfileManage.vue')
        ProfileManage = mod.default
    })

    it('hidden file input이 template에 존재한다', async () => {
        const wrapper = mount(ProfileManage)
        await flushPromises()

        const fileInput = wrapper.find('input[type="file"]')
        expect(fileInput.exists()).toBe(true)
        expect(fileInput.attributes('style')).toContain('display: none')
    })

    it('프로필 변경 클릭 시 document.createElement를 호출하지 않는다', async () => {
        const spy = vi.spyOn(document, 'createElement')
        const wrapper = mount(ProfileManage)
        await flushPromises()

        await wrapper.find('.update').trigger('click')
        await flushPromises()

        expect(spy).not.toHaveBeenCalledWith('input')
        spy.mockRestore()
    })
})
