package com.kbds.idpass.data.util

import android.content.ContentResolver
import android.os.Build
import android.provider.Settings
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

open class DataUtil {

    companion object {
        fun getTime(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                current.format(formatter).toString()
            } else {
                val currentDateTime = Calendar.getInstance().time
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
            }
        }

        fun getDeviceId(contentResolver: ContentResolver): String {
            return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

}