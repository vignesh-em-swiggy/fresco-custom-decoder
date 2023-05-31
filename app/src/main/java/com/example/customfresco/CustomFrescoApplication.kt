package com.example.customfresco

import android.app.Application
import com.example.customfresco.avif.AVIF
import com.example.customfresco.avif.AvifFormatChecker
import com.example.customfresco.avif.AvifImageDecoder
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.ImageDecoderConfig


class CustomFrescoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this, buildImagePipelineConfig())
    }

    private fun buildImagePipelineConfig(): ImagePipelineConfig {
        val imageDecoderConfig = ImageDecoderConfig.newBuilder()
            .addDecodingCapability(
                AVIF,
                AvifFormatChecker(baseContext),
                AvifImageDecoder(baseContext)
            )
            .build()

        return ImagePipelineConfig.newBuilder(this).setImageDecoderConfig(imageDecoderConfig)
            .build()
    }
}