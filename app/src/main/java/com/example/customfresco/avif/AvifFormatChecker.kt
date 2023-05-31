package com.example.customfresco.avif

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.facebook.imageformat.ImageFormat
import java.nio.ByteBuffer


val AVIF = ImageFormat("AVIF", "avif")

class AvifFormatChecker(context: Context) : ImageFormat.FormatChecker {

    private val glide = Glide.get(context)

    override fun getHeaderSize() = HEADER_BYTES_REQUIRED_TO_DETERMINE_AVIF_FORMAT

    override fun determineFormat(headerBytes: ByteArray, headerSize: Int): ImageFormat? {
        val type = ImageHeaderParserUtils.getType(
            glide.registry.imageHeaderParsers,
            ByteBuffer.wrap(headerBytes)
        )
        Log.d(TAG, "determineFormat: $type")
        return when (type) {
            ImageHeaderParser.ImageType.AVIF, ImageHeaderParser.ImageType.ANIMATED_AVIF -> AVIF
            else -> null
        }
    }

    companion object {
        private const val HEADER_BYTES_REQUIRED_TO_DETERMINE_AVIF_FORMAT = 36
        const val TAG = "AvifFormatChecker"
    }
}
