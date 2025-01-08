package com.vydia.RNUploader

import android.app.Application
import android.os.Build
import android.util.Log
import net.gotev.uploadservice.UploadServiceConfig
import net.gotev.uploadservice.UploadService

class UploaderHelper {
    companion object {
        private const val TAG = "UploaderHelper"

        @JvmStatic
        fun initialize(application: Application) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // For Android 8.0 (API 26) and above, use UploadServiceConfig
                    UploadServiceConfig.initialize(
                        application,
                        application.packageName,
                        BuildConfig.DEBUG
                    )
                } else {
                    // For older Android versions, use the legacy initialization
                    UploadService.NAMESPACE = application.packageName
                    UploadService.UPLOAD_POOL_SIZE = 1 // Or any other value based on your needs
                }
                Log.d(TAG, "Upload service initialized successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to initialize upload service: ${e.message}", e)
            }
        }
    }
}