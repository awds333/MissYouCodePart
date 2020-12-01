package com.fedchanka.missyou.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fedchanka.missyou.databinding.FragmentRegisterBinding
import com.fedchanka.missyou.ui.onBackPressed
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {
    private val viewModel: RegistrationViewModel by viewModel()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        activity?.onBackPressed(viewLifecycleOwner) {
            viewModel.back()
        }

        return binding.root
    }

}