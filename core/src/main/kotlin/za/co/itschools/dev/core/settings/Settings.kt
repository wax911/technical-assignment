package za.co.itschools.dev.core.settings

import android.content.Context
import za.co.itschools.dev.core.R
import za.co.itschools.dev.data.android.preference.AbstractPreference
import za.co.itschools.dev.data.settings.IAuthenticationSetting
import za.co.itschools.dev.data.settings.delegate.BooleanSetting

class Settings(context: Context) : AbstractPreference(context), IAuthenticationSetting {

    override val isAuthenticated = BooleanSetting(
        key = R.string.setting_is_authenticated,
        default = false,
        resources = context.resources,
        preference = this
    )
}