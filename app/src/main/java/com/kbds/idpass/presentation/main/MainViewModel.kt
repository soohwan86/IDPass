package com.kbds.idpass.presentation.main

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.kbds.idpass.presentation.qr.QRGenerateFragment

class MainViewModel: ViewModel() {

    fun showQRCode(type: String, fragmentManager: FragmentManager) {
        val kbQRGenerateFragment = QRGenerateFragment(type)
        kbQRGenerateFragment.show(fragmentManager, kbQRGenerateFragment.tag)
    }
}