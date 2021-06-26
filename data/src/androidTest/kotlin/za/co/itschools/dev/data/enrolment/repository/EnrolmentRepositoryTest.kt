package za.co.itschools.dev.data.enrolment.repository

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import za.co.itschools.dev.data.AbstractTestCase
import za.co.itschools.dev.data.settings.IAuthenticationSetting
import za.co.itschools.dev.domain.enrolment.repository.IEnrolmentRepository

@RunWith(AndroidJUnit4ClassRunner::class)
class EnrolmentRepositoryTest : AbstractTestCase() {

    private val repository by inject<IEnrolmentRepository>()

    @Before
    fun setUp() {
        onStartUp()
    }

    @After
    fun tearDown() {
        onStop()
    }

    @Test
    fun assert_repository_dependency_can_be_constructed() {
        loadKoinModules(
            module {
                factory(override = true) {
                    every { settings.userId.value } returns 1L
                    every { settings.isAuthenticated.value } returns true
                    settings
                }
            }
        )
        assertNotNull(repository)
    }
}