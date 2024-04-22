package com.quid.playLive.stage.gateway.api.model

data class NginxNotifyRequest(
    val call: String?,
    val addr: String?,
    val clientid: String?,
    val app: String?,
    val flashVer: String?,
    val swfUrl: String?,
    val tcUrl: String?,
    val pageUrl: String?,
    val name: String?
)