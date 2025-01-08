package com.quid.playLive.global.s3

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
data class S3Config(
    val bucket: String,
)
