package za.co.itschools.dev.login.component.content.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import za.co.itschools.dev.core.component.viewmodel.AbstractViewModel
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.login.interactor.ILoginUseCase
import za.co.itschools.dev.domain.login.model.LoginParam

class LoginContentViewModel(
    private val interactor: ILoginUseCase
) : AbstractViewModel<Boolean>() {

    val usernameLiveData = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String?>()

    private val useCaseResult = MutableLiveData<DataState<Boolean>>()

    override val model = Transformations.switchMap(useCaseResult) {
        it.data.asLiveData(viewModelScope.coroutineContext)
    }

    override val isLoading = Transformations.switchMap(useCaseResult) {
        it.isLoading.asLiveData(viewModelScope.coroutineContext)
    }

    override val error = Transformations.switchMap(useCaseResult) {
        it.error.asLiveData(viewModelScope.coroutineContext)
    }

    operator fun invoke(param: LoginParam) {
        val data = interactor(viewModelScope, param)
        useCaseResult.value = data
    }

    private companion object {
        const val KEY_USER_NAME = "username"
        const val KEY_PASSWORD = "password"
    }
}