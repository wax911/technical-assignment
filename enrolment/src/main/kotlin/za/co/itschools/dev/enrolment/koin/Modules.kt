package za.co.itschools.dev.enrolment.koin

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import za.co.itschools.dev.core.koin.helper.FeatureModuleHelper
import za.co.itschools.dev.enrolment.component.adapter.EnrolmentAdapter
import za.co.itschools.dev.enrolment.component.content.EnrolmentContent
import za.co.itschools.dev.enrolment.component.content.viewmodel.EnrolmentContentViewModel
import za.co.itschools.dev.enrolment.provider.FeatureProvider
import za.co.itschools.dev.navigation.EnrolmentRouter

private val contentModule = module {
    fragment {
        EnrolmentContent(
            contentAdapter = EnrolmentAdapter()
        )
    }
}

private val viewModelModule = module {
    viewModel {
        EnrolmentContentViewModel(
            interactor = get()
        )
    }
}

private val providerModule = module {
    factory<EnrolmentRouter.Provider> {
        FeatureProvider()
    }
}

val featureModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule, providerModule)
)