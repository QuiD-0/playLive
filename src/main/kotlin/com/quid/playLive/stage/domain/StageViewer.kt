package com.quid.playLive.stage.domain

data class StageViewer(
    val channel: String,
    val viewers: MutableSet<Viewer>
) {
    constructor(channel: String, uuid: String) : this(channel, mutableSetOf(Viewer(uuid)))

    fun add(uuid: String) = viewers.add(Viewer(uuid))
}
