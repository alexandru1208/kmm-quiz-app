package deps

object AndroidApp {

    const val androidXCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidXAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    val all = listOf(androidXCore, androidXAppcompat)

    object Versions {
        const val coreKtx = "1.2.0"
        const val appcompat = "1.3.1"
    }
}
