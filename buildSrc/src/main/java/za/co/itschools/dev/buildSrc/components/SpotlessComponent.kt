package za.co.itschools.dev.buildSrc.components

import za.co.itschools.dev.buildSrc.extension.spotlessExtension
import za.co.itschools.dev.buildSrc.common.Versions
import org.gradle.api.Project

internal fun Project.configureSpotless(): Unit = spotlessExtension().run {
    kotlin {
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt", "bin/**/*.kt")
        ktlint(Versions.ktlint).userData(
            mapOf("android" to "true")
        )
    }
}
