plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

version = "1.0"

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines.core)
            implementation(project(":domain"))
            implementation(project(":base"))
        }
    }
}

android {
    namespace = "com.mcg.trivia.createquiz"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17

    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}