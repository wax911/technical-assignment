package za.co.itschools.dev.data.enrolment.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EnrolmentModel(
    @SerialName("group_id") val groupId: Long,
    @SerialName("_id") val id: String,
    @SerialName("code") val code: String,
    @SerialName("grade") val grade: String,
    @SerialName("name") val name: String,
    @SerialName("private") val private: Boolean,
    @SerialName("updated") val updated: Long?,
    @SerialName("created") val created: Long,
    @SerialName("year") val year: Int,
    @SerialName("language") val language: String,
    @SerialName("source") val source: String,
    @SerialName("root") val root: String?,
    @SerialName("metadata") val metadata: String,
    @SerialName("deleted") val deleted: Boolean,
    @SerialName("deletedDate") val deletedDate: Long?,
    @SerialName("owner") val owner: Long,
    @SerialName("updatedBy") val updatedBy: Long?,
    @SerialName("deletedBy") val deletedBy: Long?,
    @SerialName("subject") val subject: String?,
    @SerialName("start") val start: Long,
    @SerialName("end") val end: Long,
    @SerialName("systemGroup") val systemGroup: Boolean,
    @SerialName("memberParents") val memberParents: String?,
    @SerialName("educatorControl") val educatorControl: String,
    @SerialName("subject_id") val subjectId: Long?
)