package za.co.itschools.dev.data.enrolment.koin

import org.koin.dsl.module
import za.co.itschools.dev.data.android.database.extionsions.store
import za.co.itschools.dev.data.api.provider.extension.api
import za.co.itschools.dev.data.enrolment.converters.EnrolmentModelConverter
import za.co.itschools.dev.data.enrolment.repository.EnrolmentRepository
import za.co.itschools.dev.data.enrolment.usecase.EnrolmentUseCase
import za.co.itschools.dev.domain.enrolment.interactor.IEnrolmentUseCase
import za.co.itschools.dev.domain.enrolment.repository.IEnrolmentRepository

private val useCaseModule = module {
    factory<IEnrolmentUseCase> {
        EnrolmentUseCase(
            repository = get()
        )
    }
}

private val repositoryModule = module {
    factory<IEnrolmentRepository> {
        EnrolmentRepository(
            localSource = store().loginDao(),
            remoteSource = api(),
            settings = get(),
            converter = get<EnrolmentModelConverter>()
        )
    }
}

private val converterModule = module {
    single {
        EnrolmentModelConverter()
    }
}

internal val enrolmentModules = listOf(
    useCaseModule, repositoryModule, converterModule
)