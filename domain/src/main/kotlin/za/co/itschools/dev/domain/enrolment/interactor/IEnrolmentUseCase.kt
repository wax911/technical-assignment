package za.co.itschools.dev.domain.enrolment.interactor

import kotlinx.coroutines.CoroutineScope
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.enrolment.entity.Enrolment

interface IEnrolmentUseCase {

    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    operator fun invoke(
        scope: CoroutineScope
    ): DataState<List<Enrolment>>
}