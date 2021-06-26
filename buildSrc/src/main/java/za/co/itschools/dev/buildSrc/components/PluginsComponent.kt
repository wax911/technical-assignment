package za.co.itschools.dev.buildSrc.components

import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import za.co.itschools.dev.buildSrc.extension.isAppModule
import za.co.itschools.dev.buildSrc.extension.hasKaptSupport
import za.co.itschools.dev.buildSrc.extension.hasKotlinAndroidExtensionSupport

private fun addAndroidPlugin(project: Project, pluginContainer: PluginContainer) {
    if (project.isAppModule()) pluginContainer.apply("com.android.application")
    else pluginContainer.apply("com.android.library")
}

private fun addKotlinAndroidPlugin(pluginContainer: PluginContainer) {
    pluginContainer.apply("kotlin-android")
    pluginContainer.apply("com.diffplug.spotless")
}

private fun addAnnotationProcessor(project: Project, pluginContainer: PluginContainer) {
    if (project.hasKaptSupport())
        pluginContainer.apply("kotlin-kapt")
}

private fun addKotlinAndroidExtensions(project: Project, pluginContainer: PluginContainer) {
    if (project.hasKotlinAndroidExtensionSupport())
        pluginContainer.apply("kotlin-parcelize")
}

internal fun Project.configurePlugins() {
    addAndroidPlugin(project, plugins)
    addKotlinAndroidPlugin(plugins)
    addKotlinAndroidExtensions(project, plugins)
    addAnnotationProcessor(project, plugins)
}