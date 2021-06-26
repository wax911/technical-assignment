package za.co.itschools.dev.data.enrolment.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.domain.enrolment.interactor.IEnrolmentUseCase
import za.co.itschools.dev.domain.enrolment.repository.IEnrolmentRepository

class EnrolmentUseCase(
    private val repository: IEnrolmentRepository
) : IEnrolmentUseCase {

    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    override fun invoke(scope: CoroutineScope): DataState<List<Enrolment>> {
        val errorFlow = MutableStateFlow<Throwable?>(null)
        val loadFlow = MutableStateFlow<Boolean?>(null)
        val dataFlow = MutableStateFlow<List<Enrolment>?>(null)

        scope.launch(Dispatchers.IO) {
            runCatching {
                loadFlow.value = true
                dataFlow.value = repository.getEnrolments()
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