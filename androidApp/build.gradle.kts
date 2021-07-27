import deps.implementation

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":sqldelight"))
    implementation(project(":ktor"))
    implementation(project(":domain"))
    implementation(deps.AndroidApp.all)
    implementation(deps.Compose.all)
    implementation(deps.Koin.all)
}

android {
    compileSdk = config.AndroidAppConfig.compileSdk
    defaultConfig {
        applicationId = "com.softvision.trivia.android"
        minSdk = config.AndroidAppConfig.minSdk
        targetSdk = config.AndroidAppConfig.targetSdk
        versionCode = config.AndroidAppConfig.versionCode
        versionName = config.AndroidAppConfig.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = deps.Compose.Versions.compose
    }
}