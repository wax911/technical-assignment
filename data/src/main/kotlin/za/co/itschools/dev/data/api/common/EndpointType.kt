package za.co.itschools.dev.data.api.common

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import za.co.itschools.dev.data.BuildConfig

internal enum class EndpointType(val url: HttpUrl) {
    DEFAULT(BuildConfig.apiUrl.toHttpUrl());

    companion object {
        const val LOGIN_PATH = "login"
        const val ENROLMENT_PATH = "users/{username}/enrolment"
    }
}