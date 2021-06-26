package za.co.itschools.dev.login.koin

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import za.co.itschools.dev.core.koin.helper.FeatureModuleHelper
import za.co.itschools.dev.login.component.content.LoginContent
import za.co.itschools.dev.login.component.content.viewmodel.LoginContentViewModel
import za.co.itschools.dev.login.provider.FeatureProvider
import za.co.itschools.dev.navigation.LoginRouter

private val contentModule = module {
    fragment {
        LoginContent()
    }
}

private val viewModelModule = module {
    viewModel {
        LoginContentViewModel(
            interactor = get()
        )
    }
}

private val providerModule = module {
    factory<LoginRouter.Provider> {
        FeatureProvider()
    }
}

val featureModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule, providerModule)
)