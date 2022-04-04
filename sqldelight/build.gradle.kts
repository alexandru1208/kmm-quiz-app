import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
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
                implementation(deps.SqlDelight.runtime)
                implementation(deps.SqlDelight.coroutines)
                implementation(deps.Koin.core)
                implementation(project(":domain"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(deps.SqlDelight.androidDriver)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(deps.SqlDelight.nativeDriver)

            }
        }
        val iosTest by getting
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

sqldelight {
    database("Database") {
        packageName = "com.softvision.trivia.db"
    }
}