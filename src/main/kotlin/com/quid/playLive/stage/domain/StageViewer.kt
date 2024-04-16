package com.quid.playLive.stage.domain

data class StageViewer(
    val channel: String,
    val viewers: MutableSet<Viewer>
) {
    fun add(uuid: String) = viewers.add(Viewer(uuid))
}
