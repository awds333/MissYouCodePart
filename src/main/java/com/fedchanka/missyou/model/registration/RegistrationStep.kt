package com.fedchanka.missyou.model.registration

abstract class RegistrationStep {
    fun previousStep(): RegistrationStep? = null
}