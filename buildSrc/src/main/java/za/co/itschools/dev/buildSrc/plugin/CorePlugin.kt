package za.co.itschools.dev.buildSrc.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import za.co.itschools.dev.buildSrc.components.configureAndroid
import za.co.itschools.dev.buildSrc.components.configureDependencies
import za.co.itschools.dev.buildSrc.components.configureOptions
import za.co.itschools.dev.buildSrc.components.configurePlugins

/**
 * Custom gradle plugin to apply common module configurations
 */
class CorePlugin : Plugin<Project> {

    /**
     * Inspecting available extensions
     */
    @Suppress("UnstableApiUsage")
    internal fun Project.availableExtensions() {
        val extensionSchema = project.extensions.extensionsSchema
        extensionSchema.forEach {
            println("Available extension for module ${project.path}: ${it.name} -> ${it.publicType}")
        }
    }

    /**
     * Apply this plugin to the given target object.
     *
     * @param target The target object
     */
    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureAndroid()
        target.configureOptions()
        target.configureDependencies()
        target.availableExtensions()
    }
}