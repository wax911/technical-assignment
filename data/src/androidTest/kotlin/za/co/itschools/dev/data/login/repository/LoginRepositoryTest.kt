package za.co.itschools.dev.data.login.repository

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.every
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import za.co.itschools.dev.data.AbstractTestCase
import za.co.itschools.dev.domain.login.repository.ILoginRepository

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginRepositoryTest : AbstractTestCase() {

    private val repository by inject<ILoginRepository>()

    @Before
    fun setUp() {
        onStartUp()
    }

    @Test
    fun assert_repository_dependency_can_be_constructed() {
        loadKoinModules(
            module {
                factory(override = true) {
                    every { settings.userId.value } returns -1L
                    every { settings.isAuthenticated.value } returns false
                    settings
                }
            }
        )
        assertNotNull(repository)
    }
}