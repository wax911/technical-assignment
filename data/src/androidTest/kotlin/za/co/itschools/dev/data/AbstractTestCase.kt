package za.co.itschools.dev.data

import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import za.co.itschools.dev.data.android.database.Store
import za.co.itschools.dev.data.core.koin.dataModules
import za.co.itschools.dev.data.settings.IAuthenticationSetting


abstract class AbstractTestCase : KoinTest {

    protected val settings = mockk<IAuthenticationSetting>()

    protected fun onStartUp() {
        startKoin {
            androidContext(
                InstrumentationRegistry.getInstrumentation().context
            )
            modules(dataModules)
            loadKoinModules(
                module {
                    single(override = true) {
                        Store.createInMemory(
                            context = androidContext()
                        )
                    }
                }
            )
        }
    }

    protected fun onStop() {
        stopKoin()
    }
}