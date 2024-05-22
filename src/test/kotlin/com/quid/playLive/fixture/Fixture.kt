package com.quid.playLive.fixture

import org.mockito.Mockito

class Fixture {
    companion object {
        fun <T> any() = Mockito.any() ?: null as T
    }
}