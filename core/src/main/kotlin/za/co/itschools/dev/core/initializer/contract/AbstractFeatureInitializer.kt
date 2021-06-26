package za.co.itschools.dev.core.initializer.contract

import androidx.startup.Initializer
import za.co.itschools.dev.core.initializer.injector.InjectorInitializer

/**
 * Contract for feature initializer
 */
abstract class AbstractFeatureInitializer<T> : Initializer<T> {

    /**
     * @return A list of dependencies that this [Initializer] depends on. This is
     * used to determine initialization order of [Initializer]s.
     *
     * By default a feature initializer should only start after koin has been initialized
     */
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(InjectorInitializer::class.java)
    }
}