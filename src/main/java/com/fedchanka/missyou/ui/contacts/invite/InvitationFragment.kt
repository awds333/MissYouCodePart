package com.fedchanka.missyou.ui.contacts.invite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.fedchanka.missyou.R
import com.fedchanka.missyou.databinding.FragmentInvitationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvitationFragment : DialogFragment() {

    private val viewModel: InvitationViewModel by viewModel()
    private lateinit var binding: FragmentInvitationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invitation, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object{
        fun getInstance() : InvitationFragment =
            InvitationFragment()
    }
}