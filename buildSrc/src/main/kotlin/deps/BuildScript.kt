package deps

object BuildScript {

    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${SqlDelight.Versions.core}"

    object Versions {
        const val androidGradle = "7.1.0-alpha02"
        const val kotlin = "1.6.0"
    }
}