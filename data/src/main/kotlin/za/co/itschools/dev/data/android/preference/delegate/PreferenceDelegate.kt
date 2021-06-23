package za.co.itschools.dev.data.android.preference.delegate

import androidx.core.content.edit
import za.co.itschools.dev.data.android.preference.delegate.contract.IPreferenceDelegate
import za.co.itschools.dev.data.settings.contract.AbstractSetting
import kotlin.reflect.KProperty

/**
 * [Boolean] preference delegate for handling preference related
 * operations of getting and saving a value
 */
internal class BooleanPreference(
    override val key: String,
    override val default: Boolean
) : IPreferenceDelegate<Boolean> {

    override fun getValue(
        thisRef: AbstractSetting<Boolean>,
        property: KProperty<*>
    ): Boolean {
        return thisRef.preference.getBoolean(key, default)
    }

    override fun setValue(
        thisRef: AbstractSetting<Boolean>,
        property: KProperty<*>,
        value: Boolean
    ) {
        thisRef.preference.edit {
            putBoolean(key, value)
        }
    }
}