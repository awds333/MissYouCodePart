package com.fedchanka.missyou.model.registration

data class RegistrationStepData(
    val stepNumber: Int,
    val targetProperty: Int
)

val RegistrationStepData.isFirstStep: Boolean
    get() = stepNumber == 1

val RegistrationStepData.isLastStep: Boolean
    get() = stepNumber == 3