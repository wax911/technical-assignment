package za.co.itschools.dev.data.core.cookie

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import timber.log.Timber

/**
 * Cookie jar for storing cookies required by the `users` path,
 * going to keep this simple without any advanced storage and use a
 * hash map instead for O(1) access and insert
 */
internal class CoreCookieJar : CookieJar {

    private val cookieStorage = mutableMapOf<String, List<Cookie>>()

    /**
     * Load cookies from the jar for an HTTP request to [url]. This method returns a possibly
     * empty list of cookies for the network request.
     *
     * Simple implementations will return the accepted cookies that have not yet expired and that
     * [match][Cookie.matches] [url].
     */
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        Timber.d("Requesting cookies from: ${url.host}")
        if (cookieStorage.containsKey(url.host))
            return cookieStorage[url.host].orEmpty()
        return emptyList()
    }

    /**
     * Saves [cookies] from an HTTP response to this store according to this jar's policy.
     *
     * Note that this method may be called a second time for a single HTTP response if the response
     * includes a trailer. For this obscure HTTP feature, [cookies] contains only the trailer's
     * cookies.
     */
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        Timber.d("Cookie received from: ${url.host} -> [${cookies.joinToString()}]")
        cookieStorage[url.host] = cookies
    }
}