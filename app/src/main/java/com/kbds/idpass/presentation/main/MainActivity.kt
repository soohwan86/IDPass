package com.kbds.idpass.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kbds.idpass.data.const.Constants
import com.kbds.idpass.data.repository.KBRepository
import com.kbds.idpass.databinding.ActivityMainBinding
import com.kbds.idpass.presentation.qr.QREmpInfoRegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: KBRepository

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // 버튼 클릭
        binding.apply {
            // 직원정보등록
            infoRegistBtn.setOnClickListener {
                Intent(this@MainActivity, QREmpInfoRegisterActivity::class.java).apply {
                    getStartActivityForResult.launch(this)
                }
            }
            // QR체크인
            qrCheckinBtn.setOnClickListener {
                if(repository.getKbPassData().isNotEmpty())
                    showQRCode(Constants.PARAM.KB_TYPE_AUTH)
                else
                    Toast.makeText(this@MainActivity, "등록을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 등록 Activity callBack
    private val getStartActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if(activityResult.resultCode == RESULT_OK)
            showQRCode(Constants.PARAM.KB_TYPE_REGISTER)
    }

    private fun showQRCode(type: String) {
        mainViewModel.showQRCode(type, supportFragmentManager)
    }
}