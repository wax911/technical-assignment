package za.co.itschools.dev.initializer

import android.content.Context
import android.os.Build
import androidx.startup.Initializer
import timber.log.Timber
import za.co.itschools.dev.BuildConfig
import za.co.itschools.dev.core.initializer.contract.AbstractCoreInitializer
import za.co.itschools.dev.core.initializer.injector.InjectorInitializer
import za.co.itschools.dev.core.koin.helper.FeatureModuleHelper.Companion.loadModules
import za.co.itschools.dev.koin.appModules

class ApplicationInitializer : AbstractCoreInitializer<Unit>() {

    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        appModules.loadModules()
    }

    /**
     * @return A list of dependencies that this [Initializer] depends on. This is
     * used to determine initialization order of [Initializer]s.
     *
     * For e.g. if a [Initializer] `B` defines another
     * [Initializer] `A` as its dependency, then `A` gets initialized before `B`.
     */
    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(InjectorInitializer::class.java)
}