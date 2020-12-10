package com.kbds.idpass.presentation.qr

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kbds.idpass.R
import com.kbds.idpass.databinding.ActivityQrEmpInfoRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QREmpInfoRegisterActivity : AppCompatActivity() {

    private val TAG: String = QREmpInfoRegisterActivity::class.java.simpleName

    private val qrViewModel by viewModels<QRViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityQrEmpInfoRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_qr_emp_info_register)
        binding.apply {
            lifecycleOwner = this@QREmpInfoRegisterActivity
            viewModel = qrViewModel
        }

        qrViewModel.validation.observe(this@QREmpInfoRegisterActivity, Observer { isGenerate ->
            if(isGenerate) {
                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this@QREmpInfoRegisterActivity, "필수 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}