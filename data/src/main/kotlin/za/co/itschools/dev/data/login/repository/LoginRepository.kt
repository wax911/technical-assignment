package za.co.itschools.dev.data.login.repository

import za.co.itschools.dev.data.core.error.RequestError
import za.co.itschools.dev.data.login.converters.LoginConverter
import za.co.itschools.dev.data.login.model.query.HashType
import za.co.itschools.dev.data.login.source.local.LoginLocalSource
import za.co.itschools.dev.data.login.source.remote.LoginRemoteSource
import za.co.itschools.dev.data.settings.IAuthenticationSetting
import za.co.itschools.dev.domain.login.model.LoginParam
import za.co.itschools.dev.domain.login.repository.ILoginRepository

/**
 * Login repository implementation
 *
 * @param localSource Source for accessing local backed storage
 * @param remoteSource Source for accessing remote resources
 * @param settings Settings facade for authentication settings
 * @param converter Mapper for converting models to entities
 */
internal class LoginRepository(
    private val localSource: LoginLocalSource,
    private val remoteSource: LoginRemoteSource,
    private val settings: IAuthenticationSetting,
    private val converter: LoginConverter
) : ILoginRepository {

    /**
     * Login a user using [param]
     */
    override suspend fun loginUser(param: LoginParam): Boolean {
        val response = remoteSource.loginUser(
            username = param.username,
            password = param.password,
            noHash = HashType.NO_HASH.ordinal
        )

        val body = response.body()

        if (response.isSuccessful && body != null) {
            val entity = converter.from(body)
            val id = localSource.insert(entity)
            if (id != -1L) {
                settings.userId.value = id
                settings.isAuthenticated.value = true
            }
            return true
        }

        throw RequestError(response, "Unable to login user")
    }

    /**
     * Login a user using last saved session details
     */
    override suspend fun loginUser(): Boolean {
        if (settings.isAuthenticated.value) {
            val entity = localSource.getLoginUser(settings.userId.value)
            if (entity != null) {
                val response = remoteSource.loginUser(
                    username = entity.username,
                    password = entity.hash,
                    noHash = HashType.HASHED.ordinal
                )

                return response.isSuccessful && response.body() != null
            }
        }

        throw RequestError("User may not be authenticated or request failed")
    }
}