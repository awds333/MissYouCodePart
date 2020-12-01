package com.fedchanka.missyou.service.manager

import com.fedchanka.missyou.model.Result
import com.fedchanka.missyou.model.registration.RegistrationStep
import com.fedchanka.missyou.model.registration.RegistrationStepData
import com.fedchanka.missyou.service.RegistrationService
import com.fedchanka.missyou.service.asResult
import com.fedchanka.missyou.service.registration.EmailStep
import com.fedchanka.missyou.service.registration.toRegistrationStepData
import org.koin.core.scope.Scope
import kotlin.properties.Delegates

class RegistrationManager(private val scope: Scope) : RegistrationService {
    private var step: RegistrationStep by Delegates.observable(scope.get<EmailStep>()) { _, oldStep, newStep ->

    }

    override fun toPreviousStep(): Boolean {
        return false
    }

    override suspend fun toNextStep(property: String): Result<RegistrationStepData, Throwable> = asResult {
        step.toRegistrationStepData()
    }

    override fun currentStep(): RegistrationStepData{
        return step.toRegistrationStepData()
    }
}
