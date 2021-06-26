package za.co.itschools.dev.data.settings.contract

import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * An abstraction of a setting that is both observable and stateful
 *
 * @param preference Shared preference that will be used for persistence
 * @param default Default value to return
 */
abstract class AbstractSetting<T>(
    internal val preference: SharedPreferences,
    internal val default: T
) {
    /**
     * Key identifier for the setting
     */
    protected abstract val identifier: String

    /**
     * Allow you to set a value for the setting or
     * access a snapshot value of the setting
     */
    abstract var value: T

    /**
     * Readonly observable to listen for changes for the setting
     */
    val flow: Flow<T> by lazy(LazyThreadSafetyMode.NONE) {
        callbackFlow {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, id ->
                if (id == identifier)
                    sendBlocking(value)
            }
            preference.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { preference.unregisterOnSharedPreferenceChangeListener(listener) }
        }
    }
}