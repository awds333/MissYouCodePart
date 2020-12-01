package com.fedchanka.missyou.service

import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.model.registration.RegistrationStepData

interface RegistrationService {
    fun toPreviousStep(): Boolean
    suspend fun toNextStep(property: String): Result<RegistrationStepData, Throwable>
    fun currentStep(): RegistrationStepData
}