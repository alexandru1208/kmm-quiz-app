package deps

object Ktor {

    const val core = "io.ktor:ktor-client-core:${Versions.core}"
    const val serialization = "io.ktor:ktor-client-serialization:${Versions.core}"
    const val logging = "io.ktor:ktor-client-logging:${Versions.core}"
    const val androidEngine = "io.ktor:ktor-client-android:${Versions.core}"
    const val iosEngine = "io.ktor:ktor-client-ios:${Versions.core}"

    object Versions {
        const val core = "1.6.1"
    }
}