package za.co.itschools.dev.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Client interceptor to provide content length and accept type in request headers
 */
internal class ClientInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val contentSize = original.body?.contentLength() ?: 0L

        val requestBuilder = original.newBuilder()
            .header(ACCEPT, MIME_TYPE)
            .header(CONTENT_LENGTH, "$contentSize")

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        const val CONTENT_LENGTH = "Content-Length"
        const val MIME_TYPE = "application/json"
        const val ACCEPT = "Accept"
    }
}