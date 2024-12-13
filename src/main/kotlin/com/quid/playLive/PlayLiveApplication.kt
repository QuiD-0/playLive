package com.quid.playLive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PlayLiveApplication

fun main(args: Array<String>) {
    runApplication<PlayLiveApplication>(*args)
}
