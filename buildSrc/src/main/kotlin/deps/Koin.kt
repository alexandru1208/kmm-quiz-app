package deps

object Koin {

    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val testJUnit4 = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    val all = listOf(core, android, compose)

    object Versions {
        const val koin = "3.1.2"
    }
}