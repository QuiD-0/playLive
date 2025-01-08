package com.quid.playLive.global.s3

import org.springframework.stereotype.Component

@Component
class S3ImageHandler(
    private val s3Config: S3Config,
    private val s3Template: S3Template
) {
    fun uploadImage(image: ImageUploadDto) {
    }
}
