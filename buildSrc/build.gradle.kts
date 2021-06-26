plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven {
        setUrl("https://plugins.gradle.org/m2/")
    }
}

val kotlinVersion = "1.4.32"
val buildToolsVersion = "4.2.1"
val spotlessVersion = "5.14.0"

dependencies {
    /** Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:$buildToolsVersion")

    /** Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    /** Spotless */
    implementation("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")

    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
}