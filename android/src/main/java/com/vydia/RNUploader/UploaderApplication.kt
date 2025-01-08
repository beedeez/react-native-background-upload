package com.vydia.RNUploader

import android.app.Application
import net.gotev.uploadservice.UploadServiceConfig

class UploaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize upload service with the application context and package name
        UploadServiceConfig.initialize(
            this,
            packageName,
            BuildConfig.DEBUG
        )
    }
}