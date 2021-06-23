package za.co.itschools.dev.data.settings

import za.co.itschools.dev.data.settings.contract.AbstractSetting

/**
 * Contract for resolving authentication related settings
 */
interface IAuthenticationSetting {
    val isAuthenticated: AbstractSetting<Boolean>
}