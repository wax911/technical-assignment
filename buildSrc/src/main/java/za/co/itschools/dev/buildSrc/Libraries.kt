package za.co.itschools.dev.buildSrc

import za.co.itschools.dev.buildSrc.common.Versions

object Libraries {

    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val retrofitSerializer =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serializationConverter}"

    object Repositories {
        const val jitPack = "https://www.jitpack.io"
    }

    object Android {
        object Tools {
            private const val version = "4.2.1"
            const val buildGradle = "com.android.tools.build:gradle:$version"
        }
    }

    object AndroidX {
        object Activity {
            private const val version = "1.2.3"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
        }

        object Collection {
            private const val version = "1.1.0"
            const val collectionKtx = "androidx.collection:collection-ktx:$version"
        }

        object Core {
            private const val version = "1.5.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object ConstraintLayout {
            private const val version = "2.0.4"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Fragment {
            private const val version = "1.3.3"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
            const val test = "androidx.fragment:fragment-ktx:fragment-testing$version"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val runTimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveDataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:$version"
        }

        object Preference {
            private const val version = "1.1.1"
            const val preference = "androidx.preference:preference:$version"
            const val preferenceKtx = "androidx.preference:preference-ktx:$version"
        }

        object Recycler {
            private const val version = "1.2.0"
            const val recyclerView = "androidx.recyclerview:recyclerview:$version"
        }

        object Room {
            private const val version = "2.3.0"
            const val compiler = "androidx.room:room-compiler:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val test = "androidx.room:room-testing:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object StartUp {
            private const val version = "1.0.0"
            const val startUpRuntime = "androidx.startup:startup-runtime:$version"
        }

        object SwipeRefresh {
            private const val version = "1.1.0"
            const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val coreKtx = "androidx.test:core-ktx:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Espresso {
                private const val version = "3.3.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
            }

            object Extension {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit:$version"
                const val junitKtx = "androidx.test.ext:junit-ktx:$version"
            }
        }

        object Work {
            private const val version = "2.5.0"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
            const val runtime = "androidx.work:work-runtime:$version"
            const val test = "androidx.work:work-test:$version"
        }
    }

    object CashApp {
        object Copper {
            private const val version = "1.0.0"
            const val copper = "app.cash.copper:copper-flow:$version"
        }

        object Contour {
            private const val version = "1.1.0"
            const val contour = "app.cash.contour:contour:$version"
        }

        object Turbine {
            private const val version = "0.5.0"
            const val turbine = "app.cash.turbine:turbine:$version"
        }
    }

    object Chuncker {
        private const val version = "3.4.0"

        const val debug = "com.github.ChuckerTeam.Chucker:library:$version"
        const val release = "com.github.ChuckerTeam.Chucker:library-no-op:$version"
    }

    object Google {
        object Material {
            private const val version = "1.4.0-beta01"
            const val material = "com.google.android.material:material:$version"
        }

        object Services {
            private const val version = "4.3.8"
            const val googleServices = "com.google.gms:google-services:$version"
        }
    }

    object JetBrains {

        object Kotlin {
            internal const val version = "1.4.32"
            const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
            const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

            object Gradle {
                const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
            }

            object Serialization {
                const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
            }
        }

        object KotlinX {
            object Coroutines {
                private const val version = "1.4.3"
                const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
                const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
                const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
            }

            object DateTime {
                private const val version = "0.2.1"
                const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$version"
            }

            object Serialization {
                private const val version = "1.1.0"
                const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
            }
        }
    }

    object Koin {
        private const val version = "2.2.1"
        const val core = "org.koin:koin-core:$version"
        const val extension = "org.koin:koin-core-ext:$version"
        const val test = "org.koin:koin-test:$version"

        object AndroidX {
            const val scope = "org.koin:koin-androidx-scope:$version"
            const val fragment = "org.koin:koin-androidx-fragment:$version"
            const val viewModel = "org.koin:koin-androidx-viewmodel:$version"
            const val workManager = "org.koin:koin-androidx-workmanager:$version"
            const val compose = "org.koin:koin-androidx-compose:$version"
        }

        object Gradle {
            const val plugin = "org.koin:koin-gradle-plugin:$version"
        }
    }

    object Square {

        object LeakCanary {
            private const val version = "2.7"
            const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"
        }

        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val gsonConverter =  "com.squareup.retrofit2:converter-gson:$version"
        }

        object OkHttp {
            private const val version = "4.10.0-RC1"
            const val okhttp = "com.squareup.okhttp3:okhttp:$version"
            const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
            const val mockServer = "com.squareup.okhttp3:mockwebserver:$version"
        }
    }
}