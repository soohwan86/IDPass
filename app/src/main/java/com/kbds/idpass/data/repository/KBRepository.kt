package com.kbds.idpass.data.repository

import android.content.ContentResolver
import android.provider.Settings
import com.google.gson.Gson
import com.kbds.idpass.data.const.Constants
import com.kbds.idpass.data.extension.encodeBase64
import com.kbds.idpass.data.extension.getCurrentTime
import com.kbds.idpass.data.model.EmpInfo
import com.kbds.idpass.data.preferences.PreferencesObject
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBRepository @Inject constructor(
    private val preferencesObject: PreferencesObject,
    private val contentResolver: ContentResolver
) {
    fun getKbPassData() = preferencesObject.getString(Constants.PREFERENCE.KB_PASS, "")
    fun getReqData(type: String): String = Gson().toJson(EmpInfo(type, getKbPassData(), Calendar.getInstance().getCurrentTime("yyyy.MM.dd HH:mm:ss"))).encodeBase64()
    fun getDeviceId(): String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}