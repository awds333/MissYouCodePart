package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.model.domain.ContactData
import com.fedchanka.missyou.model.domain.InvitationData
import com.fedchanka.missyou.service.maper.toInvitationData
import com.fedchanka.missyou.service.maper.toInvitationDataList
import com.google.firebase.functions.FirebaseFunctions
import kotlinx.coroutines.tasks.await

class ContactServiceImpl : ContactService {
    private val functions = FirebaseFunctions.getInstance()

    override suspend fun createInvitation(name: String): Result<InvitationData, Throwable> = asResult {
        val data = hashMapOf(
            "name" to name
        )
        val result = functions.getHttpsCallable("createInvitation")
            .call(data)
            .await()

        result.data.toInvitationData()
    }

    override suspend fun deleteInvitation(invitation: InvitationData): Result<Nothing, Throwable> = asEmptyResult {
        val data = hashMapOf(
            "invitationId" to invitation.id
        )
        functions.getHttpsCallable("deleteInvitation").call(data)
    }

    override suspend fun addContact(invitationId: String): Result<ContactData, Throwable> {
        TODO("Not yet implemented")
    }

    override suspend fun removeContact(contact: ContactData): Result<Nothing, Throwable> {
        TODO("Not yet implemented")
    }

    override suspend fun getContacts(): Result<List<ContactData>, Throwable> {
        TODO("Not yet implemented")
    }

    override suspend fun getInvitations(): Result<List<InvitationData>, Throwable> = asResult {
        val result = functions.getHttpsCallable("getInvitations")
            .call()
            .await()

        result.data.toInvitationDataList()
    }
}