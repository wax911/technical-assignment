package za.co.itschools.dev.buildSrc.components

import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import za.co.itschools.dev.buildSrc.common.Versions
import za.co.itschools.dev.buildSrc.extension.*
import java.io.File

private fun DefaultConfig.applyAdditionalConfiguration(project: Project) {
    if (project.isAppModule()) {
        applicationId = "za.co.itschools.dev"
        project.baseAppExtension().run {
            buildFeatures {
                viewBinding = true
            }
        }
    }
    else
        consumerProguardFiles.add(File("consumer-rules.pro"))

    if (!project.matchesAppModule()) {
        // checking app module again since the group for app modules is
        // `app-` while the main app is just `app`
        if (!project.isAppModule()) {
            println("Applying view binding feature for module -> ${project.path}")
            project.libraryExtension().buildFeatures {
                viewBinding = true
            }
        }

        println("Applying vector drawables configuration for module -> ${project.path}")
        vectorDrawables.useSupportLibrary = true
    }
}

internal fun Project.configureAndroid(): Unit = baseExtension().run {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        buildToolsVersion = Versions.buildTools
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        applyAdditionalConfiguration(project)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = true
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        map { androidSourceSet ->
            androidSourceSet.java.srcDir(
                "src/${androidSourceSet.name}/kotlin"
            )
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = false
        isIgnoreTestSources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType(KotlinJvmCompile::class.java) {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType(KotlinCompile::class.java) {
        val compilerArgumentOptions = mutableListOf(
            "-Xuse-experimental=kotlin.Experimental",
            "-Xopt-in=kotlin.ExperimentalStdlibApi",
            "-Xopt-in=kotlin.Experimental"
        )

        if (hasCoroutineSupport()) {
            compilerArgumentOptions.add("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
            compilerArgumentOptions.add("-Xopt-in=kotlinx.coroutines.FlowPreview")
        }

        if (isAppModule() || isCoreModule() || isNavigationModule()) {
            compilerArgumentOptions.apply {
                add("-Xopt-in=org.koin.core.component.KoinApiExtension")
                add("-Xopt-in=org.koin.core.KoinExperimentalAPI")
            }
        }

        kotlinOptions {
            allWarningsAsErrors = false
            // Filter out modules that won't be using coroutines
            freeCompilerArgs = compilerArgumentOptions
        }
    }
}
