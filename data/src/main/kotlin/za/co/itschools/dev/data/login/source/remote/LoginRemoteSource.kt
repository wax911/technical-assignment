package za.co.itschools.dev.data.login.source.remote

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import za.co.itschools.dev.data.api.common.EndpointType
import za.co.itschools.dev.data.login.model.container.LoginModelContainer

internal interface LoginRemoteSource {

    @FormUrlEncoded
    @POST(EndpointType.LOGIN_PATH)
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("noHash") noHash: Int
    ) : Response<LoginModelContainer>
}