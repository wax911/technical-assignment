package za.co.itschools.dev.domain.enrolment.entity

sealed class Enrolment {

    abstract val grade: String

    data class Header(
        override val grade: String
    ) : Enrolment()

    data class Item(
        val groupId: Long,
        val id: String,
        val code: String,
        val name: String,
        val private: Boolean,
        val updated: String?,
        val created: String,
        val year: Int,
        val language: String,
        val source: String,
        val root: String?,
        val metadata: String,
        val deleted: Boolean,
        val deletedDate: String?,
        val owner: Long,
        val updatedBy: Long?,
        val deletedBy: Long?,
        val subject: String?,
        val start: String,
        val end: String,
        val systemGroup: Boolean,
        val memberParents: String?,
        val educatorControl: String,
        val subjectId: Long?,
        override val grade: String,
    ) : Enrolment()
}