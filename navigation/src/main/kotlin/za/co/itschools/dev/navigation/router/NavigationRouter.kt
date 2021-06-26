package za.co.itschools.dev.navigation.router

import org.koin.core.component.KoinComponent
import za.co.itschools.dev.navigation.provider.INavigationProvider

/**
 * Router for navigation components
 */
abstract class NavigationRouter : KoinComponent {

    /**
     * Feature provider contract
     */
    internal abstract val provider: INavigationProvider
}