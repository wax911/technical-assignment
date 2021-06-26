package za.co.itschools.dev.data.core.error

import retrofit2.Response

/**
 * Custom error type which could be expanded to multiple types of error for each response error
 */
class RequestError : Throwable {

    constructor(
        response: Response<*>,
        details: String
    ) :super("${response.code()}: $details")

    constructor(
        details: String
    ) :super(details)
}