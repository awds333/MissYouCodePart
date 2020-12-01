package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.model.domain.ContactData
import com.fedchanka.missyou.model.domain.InvitationData

interface ContactService {
    suspend fun createInvitation(name: String): Result<InvitationData, Throwable>
    suspend fun deleteInvitation(invitation: InvitationData): Result<Nothing, Throwable>
    suspend fun addContact(invitationId: String): Result<ContactData, Throwable>
    suspend fun removeContact(contact: ContactData): Result<Nothing, Throwable>
    suspend fun getContacts(): Result<List<ContactData>, Throwable>
    suspend fun getInvitations(): Result<List<InvitationData>, Throwable>
}