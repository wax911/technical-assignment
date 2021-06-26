package za.co.itschools.dev.data.android.preference.delegate.contract

import za.co.itschools.dev.data.settings.contract.AbstractSetting
import kotlin.properties.ReadWriteProperty

/**
 * Helper interface used for implementing property delegates of read-write properties in settings.
 *
 * @property key lookup key for the preference
 * @property default default value to return
 *
 * @param T the type of the property value.
 */
internal interface IPreferenceDelegate<T> : ReadWriteProperty<AbstractSetting<T>, T> {
    val key: String
    val default: T
}