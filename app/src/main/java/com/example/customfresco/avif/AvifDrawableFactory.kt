package com.example.customfresco.avif

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.facebook.imagepipeline.drawable.DrawableFactory
import com.facebook.imagepipeline.image.CloseableImage

class AvifDrawableFactory(private val resource: Resources) : DrawableFactory {

    companion object {
        private const val TAG = "AvifDrawableFactory"
    }

    override fun createDrawable(image: CloseableImage): Drawable {
        Log.d(TAG, "createDrawable: ")
        return BitmapDrawable(resource, (image as? AvifClosableImage)?.bitmap)
    }

    override fun supportsImageType(image: CloseableImage): Boolean {
        Log.d(TAG, "supportsImageType: ")
        return image is AvifClosableImage
    }
}