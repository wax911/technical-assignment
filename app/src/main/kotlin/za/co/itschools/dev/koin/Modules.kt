package za.co.itschools.dev.koin

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import za.co.itschools.dev.component.content.SplashContent
import za.co.itschools.dev.component.viewmodel.SplashViewModel
import za.co.itschools.dev.core.koin.helper.FeatureModuleHelper

private val contentModule =  module {
    fragment {
        SplashContent(
            settings = get()
        )
    }
}

private val viewModelModule = module {
    viewModel {
        SplashViewModel(
            interactor = get()
        )
    }
}

internal val appModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule)
)