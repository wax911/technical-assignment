package za.co.itschools.dev.data.api.provider.extension

import org.koin.core.scope.Scope
import za.co.itschools.dev.data.api.common.EndpointType
import za.co.itschools.dev.data.api.provider.RetrofitProvider

/**
 * Facade for supplying retrofit interface types, backed by [RetrofitProvider]
 *
 * @param endpointType Type of endpoint we wish to use
 */
internal inline fun <reified T> Scope.api(
    endpointType: EndpointType = EndpointType.DEFAULT
): T = RetrofitProvider.provideRetrofit(
    endpointType,
    this
).create(T::class.java)