package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.UptimeDisplay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class UptimeTest {

    private val uptime: Uptime = Mockito.mock(Uptime.StageUptime::class.java)

    @Test
    fun uptimeCalculate() {
        given(uptime.findBy("test")).willReturn(UptimeDisplay("test", 0, 1000))
        val findBy = uptime.findBy("test")
        assertEquals("00:00:01", findBy.toUptime())
    }

    @Test
    fun uptimeCalculateWhenChannelNotFound() {
        given(uptime.findBy("not")).willReturn(UptimeDisplay("not", 0, 0))
        val findBy = uptime.findBy("not")
        assertEquals("00:00:00", findBy.toUptime())
    }
}