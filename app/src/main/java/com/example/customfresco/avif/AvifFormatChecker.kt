package com.example.customfresco.avif

import android.util.Log
import com.facebook.imageformat.ImageFormat


val AVIF = ImageFormat("AVIF", "avif")
class AvifFormatChecker : ImageFormat.FormatChecker {

    companion object {
        private const val TAG = "AvifFormatChecker"
    }
    override fun getHeaderSize(): Int {
        Log.d(TAG, "getHeaderSize: ")
        return 0
    }

    override fun determineFormat(headerBytes: ByteArray, headerSize: Int): ImageFormat? {
        Log.d(TAG, "determineFormat: ")
        return AVIF
    }
}