package com.example.customfresco.avif

import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.util.ByteBufferUtil
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.decoder.ImageDecoder
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo
import org.aomedia.avif.android.AvifDecoder
import java.nio.ByteBuffer

class AvifImageDecoder : ImageDecoder {

    private val bitmapPool = LruBitmapPool(100)

    companion object {
        private const val TAG = "AvifImageDecoder"
    }

    override fun decode(
        encodedImage: EncodedImage,
        length: Int,
        qualityInfo: QualityInfo,
        options: ImageDecodeOptions
    ): CloseableImage? {
        Log.d(TAG, "decode: ${encodedImage.imageFormat} $length, $qualityInfo, $options")

        val bitmap =
            bitmapPool.get(encodedImage.width, encodedImage.height, Bitmap.Config.ARGB_8888)

        val byteBuffer = ByteBufferUtil.fromStream(encodedImage.inputStream!!)
        if (!AvifDecoder.decode(byteBuffer, length, bitmap)) return null
        return AvifClosableImage(bitmap)
    }
}
