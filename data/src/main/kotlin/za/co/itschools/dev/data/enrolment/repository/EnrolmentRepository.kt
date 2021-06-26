package za.co.itschools.dev.data.enrolment.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import za.co.itschools.dev.data.core.error.RequestError
import za.co.itschools.dev.data.enrolment.converters.EnrolmentConverter
import za.co.itschools.dev.data.enrolment.source.remote.EnrolmentRemoteSource
import za.co.itschools.dev.data.login.source.local.LoginLocalSource
import za.co.itschools.dev.data.settings.IAuthenticationSetting
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.domain.enrolment.repository.IEnrolmentRepository

/**
 * Enrolment repository implementation
 *
 * @param remoteSource Source for accessing remote resources
 * @param converter Mapper for converting models to entities
 */
internal class EnrolmentRepository(
    private val localSource: LoginLocalSource,
    private val remoteSource: EnrolmentRemoteSource,
    private val settings: IAuthenticationSetting,
    private val converter: EnrolmentConverter
) : IEnrolmentRepository {

    /**
     * Gets a list of enrolments
     */
    override suspend fun getEnrolments(): List<Enrolment> {
        val entity = localSource.getLoginUser(settings.userId.value)

        if (entity != null) {
            val response = remoteSource.getEnrolmentList(entity.username)

            val body = response.body()

            if (response.isSuccessful && body != null) {
                // context switching for converter since this doesn't
                // require dispatching on an IO thread, rather we'll use
                // an unconfined/computation thread context
                return withContext(Dispatchers.Default) {
                    converter.from(body)
                }
            }

            throw RequestError(response, "Failed to get enrolment items")
        }

        throw IllegalStateException("You need to be logged in first")
    }
}