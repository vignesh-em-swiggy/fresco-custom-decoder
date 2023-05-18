package com.example.customfresco

import android.app.Application
import com.example.customfresco.avif.AVIF
import com.example.customfresco.avif.AvifDrawableFactory
import com.example.customfresco.avif.AvifFormatChecker
import com.example.customfresco.avif.AvifImageDecoder
import com.facebook.drawee.backends.pipeline.DraweeConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.ImageDecoderConfig


class CustomFrescoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val imageDecoderConfig = ImageDecoderConfig.newBuilder()
            .addDecodingCapability(AVIF, AvifFormatChecker(), AvifImageDecoder())
            .build()


        val pipelineConfig =
            ImagePipelineConfig.newBuilder(this).setImageDecoderConfig(imageDecoderConfig)
                .build()

        val draweeConfig = DraweeConfig.newBuilder()
            .addCustomDrawableFactory(AvifDrawableFactory(resources))
            .build()

        Fresco.initialize(this, pipelineConfig, draweeConfig)

    }
}