import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(deps.Koin.core)
                implementation(deps.Kotlin.coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(deps.AndroidApp.viewModel)
            }
        }
    }
}

android {
    compileSdk = config.AndroidAppConfig.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = config.AndroidAppConfig.minSdk
        targetSdk = config.AndroidAppConfig.targetSdk
    }
}

