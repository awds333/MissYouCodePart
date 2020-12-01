package com.fedchanka.missyou.di

import androidx.fragment.app.Fragment
import com.fedchanka.missyou.R
import com.fedchanka.missyou.service.ContactServiceImpl
import com.fedchanka.missyou.service.TokenServiceImpl
import com.fedchanka.missyou.service.manager.LoginManager
import com.fedchanka.missyou.service.manager.RegistrationManager
import com.fedchanka.missyou.service.navigation.BackStackIgnoreNavigationAction
import com.fedchanka.missyou.service.navigation.CreateDialogNavigationAction
import com.fedchanka.missyou.service.navigation.FragmentProvider
import com.fedchanka.missyou.service.navigation.PopBackNavigationAction
import com.fedchanka.missyou.ui.ActivityResultFragment
import com.fedchanka.missyou.ui.Toaster
import com.fedchanka.missyou.ui.contacts.ContactsViewModel
import com.fedchanka.missyou.ui.contacts.invite.InvitationFragmentProvider
import com.fedchanka.missyou.ui.contacts.invite.InvitationViewModel
import com.fedchanka.missyou.ui.heart.HeartViewModel
import com.fedchanka.missyou.ui.login.FacebookLoginPerformer
import com.fedchanka.missyou.ui.login.GoogleLoginPerformer
import com.fedchanka.missyou.ui.login.LoginViewModel
import com.fedchanka.missyou.ui.registration.RegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        HeartViewModel(
            context = androidContext(),
            tokenService = get<TokenServiceImpl>()
        )
    }
    viewModel { (fragment: Fragment) ->
        ContactsViewModel(
            contactService = get<ContactServiceImpl>(),
            goToInvitationCreation = get<CreateDialogNavigationAction> {
                parametersOf(
                    fragment,
                    get<InvitationFragmentProvider>()
                )
            }
        )
    }
    viewModel {
        InvitationViewModel(get<ContactServiceImpl>())
    }
    viewModel { (fragment: Fragment) ->
        RegistrationViewModel(
            toaster = get(),
            registrationService = get<RegistrationManager>(),
            goToLogIn = get<PopBackNavigationAction> { parametersOf(fragment) },
            finishSignUp = get<PopBackNavigationAction> { parametersOf(fragment) }
        )
    }
    viewModel { (fragment: ActivityResultFragment) ->
        LoginViewModel(
            googleLoginPerformer = get<GoogleLoginPerformer> { parametersOf(fragment) },
            facebookLoginPerformer = get<FacebookLoginPerformer> { parametersOf(fragment) },
            loginService = get<LoginManager>(),
            toaster = get(),
            goToSignUp = get<BackStackIgnoreNavigationAction> {
                parametersOf(
                    fragment,
                    R.id.action_loginFragment_to_registrationFragment
                )
            },
            finishLogin = get<PopBackNavigationAction> { parametersOf(fragment) }
        )
    }
}

val uiUtils = module {
    single {
        Toaster(get())
    }
}

val navigation = module {
    factory { (fragment: Fragment) ->
        PopBackNavigationAction(fragment)
    }
    factory { (parent: Fragment, actionId: Int) ->
        BackStackIgnoreNavigationAction(parent, actionId)
    }
    factory { (parent: Fragment, fragmentProvider: FragmentProvider) ->
        CreateDialogNavigationAction(parent, fragmentProvider)
    }
    factory {
        InvitationFragmentProvider()
    }
}

val loginAction = module {
    factory { (fragment: ActivityResultFragment) ->
        GoogleLoginPerformer(fragment)
    }
    factory { (fragment: Fragment) ->
        FacebookLoginPerformer(fragment)
    }
    factory { }
}

val service = module(createdAtStart = true) {
    single {
        LoginManager(androidContext(), get<TokenServiceImpl>())
    }
    /*single {
        RegistrationManager()
    }*/
    single {
        TokenServiceImpl()
    }
    single {
        ContactServiceImpl()
    }
}