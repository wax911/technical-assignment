package za.co.itschools.dev.data.enrolment.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import za.co.itschools.dev.data.api.common.EndpointType
import za.co.itschools.dev.data.enrolment.model.EnrolmentModel

internal interface EnrolmentRemoteSource {

    @GET(EndpointType.ENROLMENT_PATH)
    suspend fun getEnrolmentList(
        @Path("username") username: String
    ): Response<List<EnrolmentModel>>
}