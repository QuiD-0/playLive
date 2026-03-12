import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import StageManage from '@/components/mypage/StageManage.vue'

vi.mock('@/module/axiosFactory.js', () => ({
    authInstance: {
        get: vi.fn().mockResolvedValue({
            data: {
                message: {
                    title: '테스트 방송',
                    description: '테스트 설명',
                    streamKey: 'abc-123-secret-key',
                }
            }
        }),
        put: vi.fn().mockResolvedValue({}),
    }
}))

vi.mock('@/module/toast.js', () => ({
    successToast: vi.fn(),
    errorToast: vi.fn(),
}))

describe('StageManage - 스트림 키 마스킹', () => {
    let wrapper

    beforeEach(async () => {
        wrapper = mount(StageManage)
        await flushPromises()
    })

    it('기본 상태에서 스트림 키가 마스킹되어 표시된다', () => {
        const input = wrapper.find('.stream__key')
        expect(input.element.value).toBe('*'.repeat('abc-123-secret-key'.length))
    })

    it('보이기 버튼 클릭 시 스트림 키가 노출된다', async () => {
        const showBtn = wrapper.find('.hide')
        await showBtn.trigger('click')

        const input = wrapper.find('.stream__key')
        expect(input.element.value).toBe('abc-123-secret-key')
    })

    it('숨기기 버튼 클릭 시 다시 마스킹된다', async () => {
        const btn = wrapper.find('.hide')
        await btn.trigger('click') // 보이기
        await btn.trigger('click') // 숨기기

        const input = wrapper.find('.stream__key')
        expect(input.element.value).toBe('*'.repeat('abc-123-secret-key'.length))
    })

    it('복사 버튼 클릭 시 클립보드에 실제 키가 복사된다', async () => {
        const writeText = vi.fn().mockResolvedValue(undefined)
        Object.assign(navigator, { clipboard: { writeText } })

        const copyBtn = wrapper.find('.copy')
        await copyBtn.trigger('click')

        expect(writeText).toHaveBeenCalledWith('abc-123-secret-key')
    })
})
