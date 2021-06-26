package za.co.itschools.dev.domain.login.interactor

import kotlinx.coroutines.CoroutineScope
import za.co.itschools.dev.domain.common.DataState
import za.co.itschools.dev.domain.login.model.LoginParam
import za.co.itschools.dev.domain.login.repository.ILoginRepository

interface ILoginUseCase {

    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     * @param param Login parameters for logging in a user
     *
     * @return [DataState]
     */
    operator fun invoke(scope: CoroutineScope, param: LoginParam): DataState<Boolean>

    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    operator fun invoke(scope: CoroutineScope): DataState<Boolean>
}