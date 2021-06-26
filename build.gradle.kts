
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(za.co.itschools.dev.buildSrc.Libraries.Android.Tools.buildGradle)
        classpath(za.co.itschools.dev.buildSrc.Libraries.JetBrains.Kotlin.Gradle.plugin)
        classpath(za.co.itschools.dev.buildSrc.Libraries.JetBrains.Kotlin.Serialization.serialization)
        classpath(za.co.itschools.dev.buildSrc.Libraries.Koin.Gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            setUrl(za.co.itschools.dev.buildSrc.Libraries.Repositories.jitPack)
        }
    }
}

/** Plugin for koin which adds a gradle task for checking dependencies */
plugins.apply("koin")

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}