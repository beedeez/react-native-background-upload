package com.vydia.RNUploader

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.pm.ApplicationInfo
import android.database.Cursor
import android.net.Uri
import android.util.Log
import net.gotev.uploadservice.UploadServiceConfig
import net.gotev.uploadservice.okhttp.OkHttpStack
import okhttp3.OkHttpClient

class UploadInitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        val appContext = context?.applicationContext
        if (appContext is Application) {
            val namespace = appContext.packageName
            // Detect if app is in debug mode
            val isDebug = (appContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

            try {
                UploadServiceConfig.initialize(appContext, namespace, isDebug)

                // Optional: configure the default HTTP stack, timeouts, etc.
                // UploadServiceConfig.httpStack = OkHttpStack(
                //   OkHttpClient().newBuilder()
                //     .connectTimeout(15, TimeUnit.SECONDS)
                //     .writeTimeout(30, TimeUnit.SECONDS)
                //     .readTimeout(30, TimeUnit.SECONDS)
                //     .build()
                // )

                Log.i("UploadInitProvider", "Initialized UploadServiceConfig with namespace=$namespace debug=$isDebug")
            } catch (e: Exception) {
                Log.e("UploadInitProvider", "Failed to initialize UploadServiceConfig", e)
            }
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0
}
