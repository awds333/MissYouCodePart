package com.fedchanka.missyou.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fedchanka.missyou.R
import com.fedchanka.missyou.databinding.FragmentLoginBinding
import com.fedchanka.missyou.ui.ActivityResultFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginFragment : ActivityResultFragment() {
    private val viewModel: LoginViewModel by viewModel {
        parametersOf(this)
    }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}