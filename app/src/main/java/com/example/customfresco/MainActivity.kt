package com.example.customfresco

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.customfresco.avif.AVIF
import com.example.customfresco.avif.AvifDrawableFactory
import com.example.customfresco.avif.AvifFormatChecker
import com.example.customfresco.avif.AvifImageDecoder
import com.example.customfresco.databinding.ActivityMainBinding
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.decoder.ImageDecoderConfig
import com.facebook.imagepipeline.request.ImageRequestBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri =
            Uri.parse("https://res.cloudinary.com/swiggy/image/upload/f_avif/rng/md/carousel/production/uycdjcbljscpflwcjeob")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loadImage(uri)
    }

    private fun loadImage(uri: Uri) {
        binding.imageView.setImageURI(uri.toString())
    }
}