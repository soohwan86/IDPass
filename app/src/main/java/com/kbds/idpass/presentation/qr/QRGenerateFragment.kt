package com.kbds.idpass.presentation.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kbds.idpass.databinding.FragmentKbQrGenerateBinding
import com.kbds.idpass.presentation.qr.view.RoundedBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QRGenerateFragment(var type: String) : RoundedBottomSheetDialogFragment() {

    private val TAG: String = QRGenerateFragment::class.java.simpleName
    private lateinit var binding: FragmentKbQrGenerateBinding
    private val viewModel by viewModels<QRViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKbQrGenerateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.qrGenerate(type)
        viewModel.qrImage.observe(this, Observer {
            binding.qrImage.setImageBitmap(it)
        })
    }
}