package com.kbds.idpass.presentation.qr

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kbds.idpass.data.model.PassInfo
import com.kbds.idpass.data.util.DataUtil
import com.kbds.idpass.databinding.ActivityQrEmpInfoRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QREmpInfoRegisterActivity : AppCompatActivity() {

    private val TAG: String = QREmpInfoRegisterActivity::class.java.simpleName
    lateinit var binding: ActivityQrEmpInfoRegisterBinding

    private val qrViewModel by viewModels<QRViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrEmpInfoRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.apply {
            registerBtn.setOnClickListener {
                var passInfo = PassInfo(idText.text.toString(), passwordText.text.toString(), DataUtil.getDeviceId(contentResolver = contentResolver))
                qrViewModel.generateKBPass(passInfo)
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}