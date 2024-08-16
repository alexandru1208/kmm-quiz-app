plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            export(project(":base"))
            export(project(":domain"))
            export(project(":createquiz"))
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.koin.core)
            implementation(project(":sqldelight"))
            implementation(project(":ktor"))
            api(project(":base"))
            api(project(":domain"))
            api(project(":createquiz"))
        }
    }
}

android {
    namespace = "com.mcg.trivia"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17

    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
