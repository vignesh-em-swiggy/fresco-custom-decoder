package com.example.customfresco.avif

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.avif.AvifByteBufferBitmapDecoder
import com.bumptech.glide.integration.avif.AvifStreamBitmapDecoder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.resource.bitmap.Downsampler.DECODE_FORMAT
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.decoder.ImageDecoder
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.CloseableStaticBitmap
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo

class AvifImageDecoder(context: Context) : ImageDecoder {

    private val glide = Glide.get(context)

    private val avifDecoder = AvifStreamBitmapDecoder(
        glide.registry.imageHeaderParsers,
        AvifByteBufferBitmapDecoder(glide.bitmapPool),
        glide.arrayPool
    )

    override fun decode(
        encodedImage: EncodedImage,
        length: Int,
        qualityInfo: QualityInfo,
        options: ImageDecodeOptions
    ): CloseableImage? = encodedImage.inputStream?.let { inputStream ->
        val startTime = System.currentTimeMillis()
        val bitmapResource = avifDecoder.decode(
            inputStream,
            -1,
            -1,
            Options().apply { set(DECODE_FORMAT, DecodeFormat.PREFER_ARGB_8888) }
        ) ?: return null
        Log.d(TAG, "decode: time to decode: ${System.currentTimeMillis() - startTime}")
        return CloseableStaticBitmap.of(
            bitmapResource.get(),
            glide.bitmapPool::put,
            qualityInfo,
            0
        )
    }

    companion object {
        private const val TAG = "AvifImageDecoder"
    }
}
