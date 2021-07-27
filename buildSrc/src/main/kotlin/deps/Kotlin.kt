package deps

object Kotlin {

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

    object Versions {
        const val coroutines = "1.5.0-native-mt"
        const val serialization = "1.2.2"
    }
}