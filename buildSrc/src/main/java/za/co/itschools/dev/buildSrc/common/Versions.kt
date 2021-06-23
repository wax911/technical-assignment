package za.co.itschools.dev.buildSrc.common

object Versions {

    private const val major = 1
    private const val minor = 0
    private const val patch = 0
    private const val candidate = 0

    private const val channel = "dev"

    const val buildTools = "30.0.3"
    const val compileSdk = 30
    const val targetSdk = 30
    const val minSdk = 21

    /**
     * **RR**_X.Y.Z_
     * > **RR** reserved for build flavours and **X.Y.Z** follow the [versionName] convention
     */
    const val versionCode = major.times(10_000) +
            minor.times(1000) +
            patch.times(100) +
            candidate.times(10)

    /**
     * Naming schema: X.Y.Z-variant##
     * > **X**(Major).**Y**(Minor).**Z**(Patch)
     */
    val versionName = if (candidate > 0)
        "$major.$minor.$patch-$channel$candidate"
    else
        "$major.$minor.$patch"

    const val mockk = "1.11.0"
    const val junit = "4.13.2"

    const val timber = "4.7.1"
    const val serializationConverter = "0.8.0"
    const val elements = "1.0.1"
    const val ktlint = "0.40.0"
}