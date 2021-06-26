package za.co.itschools.dev.core.koin

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import za.co.itschools.dev.core.settings.Settings
import za.co.itschools.dev.data.core.koin.dataModules
import za.co.itschools.dev.data.settings.IAuthenticationSetting

private val coreModule = module {
    factory {
        Settings(context = androidContext())
    } bind IAuthenticationSetting::class
}

internal val coreModules = listOf(coreModule) + dataModules