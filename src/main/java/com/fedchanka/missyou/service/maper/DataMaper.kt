@file:Suppress("UNCHECKED_CAST")
package com.fedchanka.missyou.service.maper

import com.fedchanka.missyou.model.domain.InvitationData

fun Any?.toInvitationDataList(): List<InvitationData> =
    toStringHashMapList()
        .map { it.hashMapToInvitation() }

fun Any?.toInvitationData(): InvitationData =
    toStringHashMap()
        .hashMapToInvitation()

private fun HashMap<String,String>.hashMapToInvitation(): InvitationData =
    InvitationData(id = getOrThrow("id"),
                   name = getOrThrow("name"))

private fun HashMap<String, String>.getOrThrow(key: String): String =
    this[key] ?: throw MappingException("failed to get $key")

private fun Any?.toStringHashMapList(): ArrayList<HashMap<String, String>> =
    this as? ArrayList<HashMap<String, String>> ?: throw MappingException("failed to cast to string hashMap list")

private fun Any?.toStringHashMap(): HashMap<String, String> =
    this as? HashMap<String, String> ?: throw MappingException("failed to cast to string hashMap")

class MappingException(message: String) : Throwable(message)