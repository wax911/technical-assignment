package za.co.itschools.dev.enrolment.component.content.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import za.co.itschools.dev.core.component.viewmodel.AbstractViewModel
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.domain.enrolment.interactor.IEnrolmentUseCase

class EnrolmentContentViewModel(
    private val interactor: IEnrolmentUseCase
) : AbstractViewModel<List<Enrolment>>() {

    private val useCaseResult = MutableLiveData<DataState<List<Enrolment>>>()

    override val model = Transformations.switchMap(useCaseResult) {
        it.data.asLiveData(viewModelScope.coroutineContext)
    }

    override val isLoading = Transformations.switchMap(useCaseResult) {
        it.isLoading.asLiveData(viewModelScope.coroutineContext)
    }

    override val error = Transformations.switchMap(useCaseResult) {
        it.error.asLiveData(viewModelScope.coroutineContext)
    }

    operator fun invoke() {
        val data = interactor(viewModelScope)
        useCaseResult.value = data
    }
}