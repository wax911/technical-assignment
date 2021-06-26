import za.co.itschools.dev.buildSrc.Libraries
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
	id("za.co.itschools.dev.plugin")
	id("kotlinx-serialization")
}

tasks.withType(KotlinCompile::class.java) {
	kotlinOptions {
		freeCompilerArgs = listOf(
			"-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
			"-Xopt-in=kotlin.RequiresOptIn"
		)
	}
}

dependencies {
	implementation(Libraries.AndroidX.Preference.preferenceKtx)
	implementation(Libraries.JetBrains.KotlinX.Serialization.json)
}
