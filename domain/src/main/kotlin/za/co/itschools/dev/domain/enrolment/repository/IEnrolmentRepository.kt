package za.co.itschools.dev.domain.enrolment.repository

import za.co.itschools.dev.domain.enrolment.entity.Enrolment

interface IEnrolmentRepository {
    /**
     * Gets a list of enrolments
     */
    suspend fun getEnrolments(): List<Enrolment>
}