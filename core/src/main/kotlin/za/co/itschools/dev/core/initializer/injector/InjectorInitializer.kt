package za.co.itschools.dev.core.initializer.injector

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import za.co.itschools.dev.core.initializer.contract.AbstractCoreInitializer
import za.co.itschools.dev.core.koin.coreModules

class InjectorInitializer : AbstractCoreInitializer<Unit>() {

    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            fragmentFactory()
            modules(coreModules)
        }
    }
}