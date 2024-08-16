plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.sqldelight)
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
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines)
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.stately.common)
            implementation(project(":domain"))
        }

        androidMain.dependencies {
            implementation(libs.sqldelight.android)
        }

        iosMain.dependencies {
            implementation(libs.sqldelight.native)
        }
    }
}

android {
    namespace = "com.mcg.trivia.sqldelight"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}


sqldelight {
    databases {
        create("Database") {
            packageName.set("com.mcg.trivia.db")
        }
    }
    linkSqlite = true
}
