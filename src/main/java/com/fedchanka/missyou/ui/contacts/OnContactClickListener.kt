package com.fedchanka.missyou.ui.contacts

import com.fedchanka.missyou.model.domain.ContactData

interface OnContactClickListener {
    fun onContactClick(contact: ContactData)
}