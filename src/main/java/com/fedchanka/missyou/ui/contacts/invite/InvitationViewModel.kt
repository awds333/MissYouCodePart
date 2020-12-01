package com.fedchanka.missyou.ui.contacts.invite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedchanka.missyou.service.ContactService

class InvitationViewModel(private val contactService: ContactService) : ViewModel(){

    val message = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    fun sendInvitation() {

    }

    fun cancel() {

    }
}