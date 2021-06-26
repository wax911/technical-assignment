package za.co.itschools.dev.domain.login.repository

import za.co.itschools.dev.domain.login.model.LoginParam

interface ILoginRepository {

    /**
     * Login a user using [param]
     */
    suspend fun loginUser(param: LoginParam): Boolean

    /**
     * Login a user using last saved session details
     */
    suspend fun loginUser(): Boolean
}