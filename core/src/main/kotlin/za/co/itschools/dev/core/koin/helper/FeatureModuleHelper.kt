package za.co.itschools.dev.core.koin.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import timber.log.Timber

/**
 * Module loader helper for dynamic features which is lifecycle aware
 *
 * @param modules A list of modules which need to be loaded
 * @param unloadOnDestroy If the registered module need to be unloaded on lifecycle destroy
 */
class FeatureModuleHelper(
    private val modules: List<Module>,
    private val unloadOnDestroy: Boolean = false
) : LifecycleObserver {

    /**
     * Triggered when the lifecycleOwner reaches it's onCreate state
     *
     * @see [androidx.lifecycle.LifecycleOwner]
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.v(
            "Loading dynamic feature modules: ${modules.size}"
        )
        loadModules()
    }

    /**
     * Triggered when the lifecycleOwner reaches it's onDestroy state
     *
     * @see [androidx.lifecycle.LifecycleOwner]
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (unloadOnDestroy) {
            Timber.v(
                "Unloading ${modules.size} feature modules from global context"
            )
            unloadModules()
        }
    }

    companion object {
        /**
         * load Koin modules in global Koin context
         */
        fun FeatureModuleHelper.loadModules() = loadKoinModules(modules)

        /**
         * unload Koin modules from global Koin context
         */
        fun FeatureModuleHelper.unloadModules() = unloadKoinModules(modules)
    }
}