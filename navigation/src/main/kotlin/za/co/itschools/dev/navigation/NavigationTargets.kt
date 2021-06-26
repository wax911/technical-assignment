package za.co.itschools.dev.navigation

import androidx.fragment.app.Fragment
import org.koin.core.component.inject
import za.co.itschools.dev.navigation.provider.INavigationProvider
import za.co.itschools.dev.navigation.router.NavigationRouter

object LoginRouter : NavigationRouter() {
    /**
     * Feature provider contract
     */
    override val provider by inject<Provider>()

    interface Provider : INavigationProvider {
        fun fragment(): Class<out Fragment>
    }

    fun forFragment() = provider.fragment()
}

object EnrolmentRouter : NavigationRouter() {
    /**
     * Feature provider contract
     */
    override val provider by inject<Provider>()

    interface Provider : INavigationProvider {
        fun fragment(): Class<out Fragment>
    }

    fun forFragment() = provider.fragment()
}