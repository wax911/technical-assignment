package za.co.itschools.dev.data.android.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Core abstract implementation for application preferences
 */
abstract class AbstractPreference @JvmOverloads constructor(
    protected val context: Context,
    sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
) : SharedPreferences by sharedPreferences