package za.co.itschools.dev.data.settings.delegate

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.StringRes
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import za.co.itschools.dev.data.android.preference.delegate.BooleanPreference
import za.co.itschools.dev.data.settings.contract.AbstractSetting

private typealias SettingsListener = SharedPreferences.OnSharedPreferenceChangeListener

/**
 * Additional abstraction over [BooleanPreference] to add support for state changes monitoring
 *
 * @param key A [StringRes] used to lookup the key
 * @param default Default value when the underlying preference with [key] does not exist
 * @param resources Resource used to resolve [key]
 * @param preference The shared preference containing preferences
 *
 * @see AbstractSetting
 */
class BooleanSetting(
    @StringRes key: Int,
    default: Boolean,
    resources: Resources,
    preference: SharedPreferences
) : AbstractSetting<Boolean>(preference, default) {

    override val identifier = resources.getString(key)

    override var value by BooleanPreference(identifier, default)

    override val flow by lazy(LazyThreadSafetyMode.NONE) {
        callbackFlow {
            val listener = SettingsListener { _, id ->
                if (id == identifier)
                    sendBlocking(value)
            }
            preference.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { preference.unregisterOnSharedPreferenceChangeListener(listener) }
        }
    }
}