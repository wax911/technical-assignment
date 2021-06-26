package za.co.itschools.dev.data.settings.delegate

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.StringRes
import za.co.itschools.dev.data.android.preference.delegate.BooleanPreference
import za.co.itschools.dev.data.android.preference.delegate.LongPreference
import za.co.itschools.dev.data.settings.contract.AbstractSetting

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
}

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
class LongSetting(
    @StringRes key: Int,
    default: Long,
    resources: Resources,
    preference: SharedPreferences
) : AbstractSetting<Long>(preference, default) {

    override val identifier = resources.getString(key)

    override var value by LongPreference(identifier, default)
}