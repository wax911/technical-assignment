package za.co.itschools.dev.data.login.koin

import org.koin.dsl.module
import za.co.itschools.dev.data.android.database.extionsions.store
import za.co.itschools.dev.data.api.provider.extension.api
import za.co.itschools.dev.data.login.converters.LoginModelConverter
import za.co.itschools.dev.data.login.repository.LoginRepository
import za.co.itschools.dev.data.login.usecase.LoginUseCase
import za.co.itschools.dev.domain.login.interactor.ILoginUseCase
import za.co.itschools.dev.domain.login.repository.ILoginRepository

private val useCaseModule = module {
    factory<ILoginUseCase> {
        LoginUseCase(
            repository = get()
        )
    }
}

private val repositoryModule = module {
    factory<ILoginRepository> {
        LoginRepository(
            localSource = store().loginDao(),
            remoteSource = api(),
            settings = get(),
            converter = get<LoginModelConverter>()
        )
    }
}

private val converterModule = module {
    single {
        LoginModelConverter()
    }
}

internal val loginModules = listOf(
    useCaseModule,
    repositoryModule,
    converterModule
)