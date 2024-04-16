package com.quid.playLive.stage.domain

data class StageInfo(
    val id: Long?,
    val userId: Long,
    val title: String = "[OnAir]",
    val description: String = "",
    val waitingCurtain: String = "/assets/images/waiting-curtain.jpg",
) {
    constructor(userId: Long) : this(null, userId)

    fun updateTitle(title: String, description: String) = copy(title = title, description = description)
    fun updateWaitingCurtain(waitingCurtain: String) = copy(waitingCurtain = waitingCurtain)
}