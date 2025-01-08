package com.vydia.RNUploader

import android.app.Application
import net.gotev.uploadservice.UploadServiceConfig

// Rename to make it clear this is a helper
class UploaderHelper {
    companion object {
        fun initialize(application: Application) {
            // Initialize upload service with the application context and package name
            UploadServiceConfig.initialize(
                application,
                application.packageName,
                BuildConfig.DEBUG
            )
        }
    }
}