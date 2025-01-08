package com.quid.playLive.global.s3

import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.security.MessageDigest

data class ImageUploadDto(
    private val fileExtension: String,
    private val byte: ByteArray,
    val contentType: String,
    val inputStream: InputStream,
) {

    constructor(image: MultipartFile) : this(
        fileExtension = image.originalFilename!!.substringAfterLast("."),
        contentType = image.contentType!!,
        inputStream = image.inputStream,
        byte = image.bytes
    )

    fun hashName(): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(byte)
        return hashBytes.joinToString("") { "%02x".format(it) } + "." + fileExtension
    }
}
