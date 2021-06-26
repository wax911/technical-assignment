package za.co.itschools.dev.data.login.model.container

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import za.co.itschools.dev.data.login.model.LoginModel

@Serializable
internal data class LoginModelContainer(
    @SerialName("user") val user: LoginModel,
    @SerialName("success") val success: Boolean,
    @SerialName("hash") val hash: String,
    @SerialName("maxQuota") val maxQuota: Long,
    @SerialName("quotaUsed") val quotaUsed: Int,
    @SerialName("xkv") val xkv: Int,
    @SerialName("storeUrl") val storeUrl: String,
    @SerialName("maxFileSize") val maxFileSize: FileSizeLimit,
    @SerialName("device-features") val deviceFeatures: DeviceFeatures,
    @SerialName("firstname") val firstname: String,
    @SerialName("surname") val surname: String,
) {

    @Serializable
    data class FileSizeLimit(
        @SerialName("audio") val audio: Int,
        @SerialName("excel") val excel: Int,
        @SerialName("image") val image: Int,
        @SerialName("pdf") val pdf: Int,
        @SerialName("powerpoint") val powerpoint: Int,
        @SerialName("video") val video: Int,
        @SerialName("word") val word: Int,
    )

    @Serializable
    data class DeviceFeatures(
        @SerialName("maxQuota") val maxQuota: Long,
        @SerialName("quotaUsed") val quotaUsed: Int,
        @SerialName("xkv") val xkv: Int,
        @SerialName("storeUrl") val storeUrl: String
    )
}