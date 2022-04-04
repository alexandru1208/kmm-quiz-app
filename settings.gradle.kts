pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "TriviaSV"
include(":androidApp")
include(":shared")
include(":sqldelight")
include(":domain")
include(":ktor")
include(":base")
include(":createquiz")
