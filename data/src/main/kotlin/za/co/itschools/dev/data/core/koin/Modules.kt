package za.co.itschools.dev.data.core.koin

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.CookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import za.co.itschools.dev.data.BuildConfig
import za.co.itschools.dev.data.android.database.Store
import za.co.itschools.dev.data.api.interceptor.ClientInterceptor
import za.co.itschools.dev.data.core.cookie.CoreCookieJar
import za.co.itschools.dev.data.enrolment.koin.enrolmentModules
import za.co.itschools.dev.data.login.koin.loginModules
import java.util.concurrent.TimeUnit

private val networkModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(
                    ClientInterceptor.MIME_TYPE.toMediaType()
                )
            )
    }
}

private val databaseModule = module {
    single {
        Store.create(
            context = androidContext()
        )
    }
}

private val interceptorModule = module {
    single<CookieJar> {
        CoreCookieJar()
    }

    factory {
        ChuckerInterceptor.Builder(
            context = androidContext()
        ).collector(
            ChuckerCollector(
                context = androidContext(),
                // Toggles visibility of the push notification
                showNotification = true,
                // Allows to customize the retention period of collected data
                retentionPeriod = RetentionManager.Period.ONE_WEEK
            )
        ).maxContentLength(10500L).build()
    }

    factory { (interceptorLogLevel: HttpLoggingInterceptor.Level) ->
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cookieJar(get())
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        when {
            BuildConfig.DEBUG -> {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = interceptorLogLevel
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }
        }

        okHttpClientBuilder
    }
}

val dataModules = listOf(
    networkModule,
    databaseModule,
    interceptorModule,
) + loginModules + enrolmentModules