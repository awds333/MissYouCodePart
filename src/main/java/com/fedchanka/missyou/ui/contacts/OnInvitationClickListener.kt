package com.fedchanka.missyou.ui.contacts

import com.fedchanka.missyou.model.domain.InvitationData

interface OnInvitationClickListener {
    fun onInvitationClick(invitation: InvitationData)
}