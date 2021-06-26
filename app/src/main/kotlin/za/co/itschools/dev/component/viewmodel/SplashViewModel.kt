package za.co.itschools.dev.component.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import za.co.itschools.dev.core.component.viewmodel.AbstractViewModel
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.login.interactor.ILoginUseCase

class SplashViewModel(
    private val interactor: ILoginUseCase
) : AbstractViewModel<Boolean>() {

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

    /**
     * Authenticate previous user
     */
    operator fun invoke() {
        val data = interactor(viewModelScope)
        useCaseResult.postValue(data)
    }
}