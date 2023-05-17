package com.example.customfresco

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class CustomFrescoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}