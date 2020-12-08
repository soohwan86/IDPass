package com.kbds.idpass.data.repository

import com.google.gson.Gson
import com.kbds.idpass.data.const.Constants
import com.kbds.idpass.data.extension.encodeBase64
import com.kbds.idpass.data.model.EmpInfo
import com.kbds.idpass.data.preferences.PreferencesObject
import com.kbds.idpass.data.util.DataUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBRepository @Inject constructor(
    private val preferencesObject: PreferencesObject
) {
    fun getKbPassData() = preferencesObject.getString(Constants.PREFERENCE.KB_PASS, "")
    fun getReqData(type: String): String = Gson().toJson(EmpInfo(type, getKbPassData(), DataUtil.getTime())).encodeBase64()
}