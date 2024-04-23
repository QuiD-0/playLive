package com.quid.playLive.stage.usecase

import com.quid.playLive.stage.domain.UptimeDisplay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class OnAirTest {

    private val onAir: OnAir = Mockito.mock(OnAir.StageOnAir::class.java)

    @Test
    fun uptimeCalculate() {
        given(onAir.findBy("test")).willReturn(UptimeDisplay("test", 0, 1000))
        val findBy = onAir.findBy("test")
        assertEquals("00:00:01", findBy.toUptime())
    }

    @Test
    fun uptimeCalculateWhenChannelNotFound() {
        given(onAir.findBy("not")).willReturn(UptimeDisplay("not", 0, 0))
        val findBy = onAir.findBy("not")
        assertEquals("00:00:00", findBy.toUptime())
    }
}