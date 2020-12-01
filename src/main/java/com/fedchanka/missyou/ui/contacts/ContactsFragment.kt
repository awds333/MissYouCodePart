package com.fedchanka.missyou.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fedchanka.missyou.R
import com.fedchanka.missyou.databinding.FragmentContactsBinding
import com.fedchanka.missyou.model.domain.ContactData
import com.fedchanka.missyou.model.domain.InvitationData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment(){

    private val viewModel: ContactsViewModel by viewModel()
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false)

        binding.viewModel = viewModel
        binding.onContactClick = object : OnContactClickListener {
            override fun onContactClick(contact: ContactData) {
                viewModel.onContactClick(contact)
            }
        }
        binding.onInvitationClick = object : OnInvitationClickListener {
            override fun onInvitationClick(invitation: InvitationData) {
                viewModel.onInvitationClick(invitation)
            }
        }
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        Firebase.auth.currentUser ?: kotlin.run {
            findNavController().navigate(R.id.action_contactsFragment_to_loginFragment)
        }
        super.onStart()
    }
}