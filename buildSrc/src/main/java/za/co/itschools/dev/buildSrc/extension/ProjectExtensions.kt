package za.co.itschools.dev.buildSrc.extension

import com.android.build.api.extension.ApplicationAndroidComponentsExtension
import com.android.build.api.extension.LibraryAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.api.internal.plugins.DefaultArtifactPublicationSet
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.reporting.ReportingExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.testing.internal.KotlinTestsRegistry
import za.co.itschools.dev.buildSrc.Libraries
import za.co.itschools.dev.buildSrc.module.Modules

fun Project.isAppModule() = name == Modules.App.Main.id
fun Project.isDataModule() = name == Modules.App.Data.id
fun Project.isDomainModule() = name == Modules.App.Domain.id
fun Project.isCoreModule() = name == Modules.App.Core.id
fun Project.isNavigationModule() = name == Modules.App.Navigation.id

fun Project.matchesAppModule() = Modules.App.values().any { it.id == name }
fun Project.matchesFeatureModule() = Modules.Feature.values().any { it.id == name }

/**
 * Module that support [Libraries.JetBrains.KotlinX.Coroutines] dependencies
 */
fun Project.hasCoroutineSupport() = name != Modules.App.Navigation.id || name != Modules.App.Domain.id

/**
 * Module that support [Libraries.Koin.AndroidX] dependencies
 */
fun Project.hasKoinAndroidSupport() =
    name != Modules.App.Data.id || name != Modules.App.Core.id

/**
 * Module that support the kotlin annotation processor plugin
 */
fun Project.hasKaptSupport() =
    name != Modules.App.Main.id || name != Modules.App.Data.id || name != Modules.App.Core.id

/**
 * Module that support the kotlin-android-extensions annotation processor plugin
 */
fun Project.hasKotlinAndroidExtensionSupport() =
    name != Modules.App.Domain.id

internal fun Project.baseExtension() =
    extensions.getByType<BaseExtension>()

internal fun Project.baseAppExtension() =
    extensions.getByType<BaseAppModuleExtension>()

internal fun Project.libraryExtension() =
    extensions.getByType<LibraryExtension>()

internal fun Project.dynamicFeatureExtension() =
    extensions.getByType<BaseAppModuleExtension>()

internal fun Project.extraPropertiesExtension() =
    extensions.getByType<ExtraPropertiesExtension>()

internal fun Project.defaultArtifactPublicationSet() =
    extensions.getByType<DefaultArtifactPublicationSet>()

internal fun Project.reportingExtension() =
    extensions.getByType<ReportingExtension>()

internal fun Project.sourceSetContainer() =
    extensions.getByType<SourceSetContainer>()

internal fun Project.javaPluginExtension() =
    extensions.getByType<JavaPluginExtension>()

internal fun Project.variantOutput() =
    extensions.getByType<BaseVariantOutput>()

internal fun Project.kotlinAndroidProjectExtension() =
    extensions.getByType<KotlinAndroidProjectExtension>()

internal fun Project.kotlinTestsRegistry() =
    extensions.getByType<KotlinTestsRegistry>()

internal fun Project.publishingExtension() =
    extensions.getByType<PublishingExtension>()

internal fun Project.spotlessExtension() =
    extensions.getByType<SpotlessExtension>()

internal fun Project.androidComponents() =
    extensions.getByType<ApplicationAndroidComponentsExtension>()

internal fun Project.libraryAndroidComponents() =
    extensions.getByType<LibraryAndroidComponentsExtension>()