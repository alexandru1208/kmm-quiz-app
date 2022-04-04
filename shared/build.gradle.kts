import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = config.IOSAppConfig.deploymentTarget
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
//            isStatic = false // SwiftUI preview requires dynamic framework
            export(project(":domain"))
            export(project(":createquiz"))
            export(project(":base"))
//            transitiveExport = true
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(deps.Kotlin.coroutines)
                implementation(deps.Koin.core)
                implementation(project(":sqldelight"))
                implementation(project(":ktor"))
                api(project(":ktor"))
                api(project(":domain"))
                api(project(":createquiz"))
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val iosMain by getting
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