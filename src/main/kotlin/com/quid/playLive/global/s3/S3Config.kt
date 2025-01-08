package com.quid.playLive.global.s3

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cloud.aws.s3")
data class S3Config(
    val bucket: String,
    val region: String,
    val accessKey: String,
    val secretKey: String
)
