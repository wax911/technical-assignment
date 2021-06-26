package za.co.itschools.dev.navigation.provider

import android.content.Context
import android.content.Intent

/**
 * Provider for navigation components
 */
interface INavigationProvider {
    fun activity(context: Context?): Intent? = null
}