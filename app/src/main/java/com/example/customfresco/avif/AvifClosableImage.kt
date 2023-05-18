package com.example.customfresco.avif

import android.graphics.Bitmap
import com.facebook.imagepipeline.image.DefaultCloseableImage

class AvifClosableImage(val bitmap: Bitmap) : DefaultCloseableImage() {
    override fun close() {
        bitmap.recycle()
    }

    override fun getWidth(): Int {
        return bitmap.width
    }

    override fun getHeight(): Int {
        return bitmap.height
    }

    override fun getSizeInBytes(): Int {
        return bitmap.byteCount
    }

    override fun isClosed(): Boolean {
        return bitmap.isRecycled
    }

}