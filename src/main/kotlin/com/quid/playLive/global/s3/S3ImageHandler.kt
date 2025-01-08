package com.quid.playLive.global.s3

import io.awspring.cloud.s3.ObjectMetadata
import io.awspring.cloud.s3.S3Operations
import org.springframework.stereotype.Component

@Component
class S3ImageHandler(
    private val s3Config: S3Config,
    private val s3Template: S3Operations
) {
    fun uploadImage(image: ImageUploadDto) {
        s3Template.upload(
            s3Config.bucket,
            image.hashName(),
            image.inputStream,
            ObjectMetadata.builder()
                .contentType(image.contentType)
                .build()
        )
    }
}
