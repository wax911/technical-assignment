package za.co.itschools.dev.data.login.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.login.interactor.ILoginUseCase
import za.co.itschools.dev.domain.login.model.LoginParam
import za.co.itschools.dev.domain.login.repository.ILoginRepository

internal class LoginUseCase(
    private val repository: ILoginRepository
) : ILoginUseCase {

    override fun invoke(scope: CoroutineScope, param: LoginParam): DataState<Boolean> {
        val errorFlow = MutableStateFlow<Throwable?>(null)
        val loadFlow = MutableStateFlow<Boolean?>(null)
        val dataFlow = MutableStateFlow<Boolean?>(null)

        scope.launch(Dispatchers.IO) {
            runCatching {
                loadFlow.value = true
                dataFlow.value = repository.loginUser(param)
                loadFlow.value = false
            }.onFailure {
                Timber.e(it)
                errorFlow.value = it
            }
        }

        return DataState(
            data = dataFlow.filterNotNull(),
            isLoading = loadFlow.filterNotNull(),
            error = errorFlow.filterNotNull()
        )
    }

    override fun invoke(scope: CoroutineScope): DataState<Boolean> {
        val errorFlow = MutableStateFlow<Throwable?>(null)
        val loadFlow = MutableStateFlow<Boolean?>(null)
        val dataFlow = MutableStateFlow<Boolean?>(null)

        scope.launch(Dispatchers.IO) {
            runCatching {
                loadFlow.value = true
                dataFlow.value = repository.loginUser()
                loadFlow.value = false
            }.onFailure {
                Timber.e(it)
                errorFlow.value = it
            }
        }

        return DataState(
            data = dataFlow.filterNotNull(),
            isLoading = loadFlow.filterNotNull(),
            error = errorFlow.filterNotNull()
        )
    }
}