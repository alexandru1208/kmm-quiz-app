package deps

object SqlDelight {

    const val runtime = "com.squareup.sqldelight:runtime:${Versions.core}"
    const val coroutines = "com.squareup.sqldelight:coroutines-extensions:${Versions.core}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.core}"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.core}"

    object Versions {
        const val core = "1.5.0"
    }
}