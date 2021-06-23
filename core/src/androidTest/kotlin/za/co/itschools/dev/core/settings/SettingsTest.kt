package za.co.itschools.dev.core.settings

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import za.co.itschools.dev.data.settings.IAuthenticationSetting

@RunWith(AndroidJUnit4ClassRunner::class)
class SettingsTest : KoinTest {

    private val settings by inject<IAuthenticationSetting>()

    @Before
    fun setUp() {
        startKoin {
            androidContext(
                InstrumentationRegistry.getInstrumentation().context
            )
            module {
                factory {
                    Settings(context = androidContext())
                } bind IAuthenticationSetting::class
            }
        }
    }

    fun assure_preference_state_can_be_retained() {
        val default = settings.isAuthenticated.value
        assertEquals(false, default)

        settings.isAuthenticated.value = true

        val actual = settings.isAuthenticated.value
        val expected = true

        assertEquals(expected, actual)
    }
}