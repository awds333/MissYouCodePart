package com.fedchanka.missyou.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedchanka.missyou.model.registration.RegistrationStepData
import com.fedchanka.missyou.model.registration.isFirstStep
import com.fedchanka.missyou.service.RegistrationService
import com.fedchanka.missyou.service.navigation.NavigationAction
import com.fedchanka.missyou.ui.Toaster

class RegistrationViewModel(
    private val toaster: Toaster,
    private val registrationService: RegistrationService,
    private val goToLogIn: NavigationAction,
    private val finishSignUp: NavigationAction
) : ViewModel() {

    private val registrationStepInProgressLiveData = MutableLiveData(false)
    val registrationStepInProgress: LiveData<Boolean>
        get() = registrationStepInProgressLiveData

    private val backAvailableLiveData = MutableLiveData(false)
    val backAvailable: LiveData<Boolean>
        get() = backAvailableLiveData

    private val targetPropertyLiveData = MutableLiveData(0)
    val targetProperty: LiveData<Int>
        get() = targetPropertyLiveData

    private val stepNumberLiveData = MutableLiveData(1)
    val stepNumber: LiveData<Int>
        get() = stepNumberLiveData

    init {
        updateStep(registrationService.currentStep())
    }

    private fun updateStep(step: RegistrationStepData) {
        backAvailableLiveData.value = !step.isFirstStep
        targetPropertyLiveData.value = step.targetProperty
        stepNumberLiveData.value = step.stepNumber
    }

    fun next() {

    }

    fun back() {

    }

    fun goToLogIn() {
        goToLogIn.navigate()
    }
}