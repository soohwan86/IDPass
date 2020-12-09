package com.kbds.idpass.presentation.qr

import android.content.ContentResolver
import android.content.res.Resources
import android.graphics.Bitmap
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.kbds.idpass.data.const.Constants
import com.kbds.idpass.data.extension.encryptAes256
import com.kbds.idpass.data.model.PassInfo
import com.kbds.idpass.data.preferences.PreferencesObject
import com.kbds.idpass.data.repository.KBRepository
import com.kbds.idpass.data.util.DataUtil
import kotlin.concurrent.timer

class QRViewModel @ViewModelInject constructor (
    private val repository: KBRepository,
    private val preferencesObject: PreferencesObject
): ViewModel() {
    var qrImage = MutableLiveData<Bitmap>()
    var second = MutableLiveData<Int>()
    var validation = MutableLiveData<Boolean>()

    var idText = ""
    var passwordText = ""

    fun qrGenerate(type: String) {
        val width: Int = Resources.getSystem().displayMetrics.widthPixels
        val height: Int = Resources.getSystem().displayMetrics.heightPixels

        var smallerDimension: Int = if (width < height) width else height
        smallerDimension = smallerDimension * 2 / 4

        val qrgEncoder = QRGEncoder(repository.getReqData(type), null, QRGContents.Type.TEXT, smallerDimension)

        qrImage.value = qrgEncoder.encodeAsBitmap()
    }

    fun generateKBPass(contentResolver: ContentResolver) {
        var gson = Gson()
        if(idText.isEmpty() or passwordText.isEmpty()) {
            validation.value = false
        } else {
            var passInfo = PassInfo(idText, passwordText, DataUtil.getDeviceId(contentResolver))
            preferencesObject.putString(Constants.PREFERENCE.KB_PASS, gson.toJson(passInfo).encryptAes256())
            validation.value = true
        }
    }

    fun getTimerSecond(s: Int) {
        var _second = s
        second.value = _second
        timer(period = 1000, initialDelay = 1000) {
            if(_second == 0)
                cancel()
            _second--
            second.postValue(_second)
        }
    }
}