package za.co.itschools.dev.data.api.provider

import androidx.collection.LruCache
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import retrofit2.Retrofit
import timber.log.Timber
import za.co.itschools.dev.data.BuildConfig
import za.co.itschools.dev.data.api.common.EndpointType
import za.co.itschools.dev.data.api.interceptor.ClientInterceptor

/**
 * Factory to supply types retrofit instances
 */
internal object RetrofitProvider {

    /**
     * Caching retrofit instance with an LruCache:
     * Typically creating a retrofit instance is relatively expensive,
     * so we'll reuse existing instance if we have any stored
     */
    private val retrofitCache = LruCache<EndpointType, Retrofit>(3)


    private fun provideOkHttpClient(endpointType: EndpointType, scope: Scope) : OkHttpClient {
        val builder = scope.get<OkHttpClient.Builder> {
            parametersOf(HttpLoggingInterceptor.Level.HEADERS)
        }

        when (endpointType) {
            EndpointType.DEFAULT -> {
                Timber.d("""
                    Adding request interceptors for endpoint: ${endpointType.name}
                    """.trimIndent()
                )
                builder.addInterceptor(ClientInterceptor())
                if (BuildConfig.DEBUG)
                    builder.addInterceptor(
                        scope.get<ChuckerInterceptor>()
                    )
            }
        }

        return builder.build()
    }

    private fun createRetrofit(endpointType: EndpointType, scope: Scope) : Retrofit {
        return scope.get<Retrofit.Builder>()
            .client(
                provideOkHttpClient(
                    endpointType,
                    scope
                )
            )
            .baseUrl(endpointType.url)
            .build()
    }

    fun provideRetrofit(endpointType: EndpointType, scope: Scope): Retrofit {
        val reference = retrofitCache.get(endpointType)
        return if (reference != null) {
            Timber.d(
                "Using cached retrofit instance for endpoint: ${endpointType.name}"
            )
            reference
        }
        else {
            Timber.d(
                "Creating new retrofit instance for endpoint: ${endpointType.name}"
            )
            val retrofit =
                createRetrofit(
                    endpointType,
                    scope
                )
            retrofitCache.put(endpointType, retrofit)
            retrofit
        }
    }
}