package za.co.itschools.dev.data.login.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Only going to model these few attributes
 */
@Serializable
internal data class LoginModel(
    @SerialName("user_id") val user_id: Long,
    @SerialName("_id") val id: String,
    @SerialName("username") val username: String,
    @SerialName("firstname") val firstname: String,
    @SerialName("surname") val surname: String,
    @SerialName("enabled") val enabled: Boolean,
    @SerialName("email") val email: String?,
    @SerialName("grants") val grants: Map<String, String>?,
    @SerialName("deleted") val deleted: Boolean,
    @SerialName("groups") val groups: List<String>,
    @SerialName("customGroups") val customGroups: List<String>,
    @SerialName("roles") val roles: List<String>,
    @SerialName("grade") val grade: String,
)