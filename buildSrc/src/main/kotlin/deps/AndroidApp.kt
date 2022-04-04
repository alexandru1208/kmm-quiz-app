package deps

object AndroidApp {

    const val androidXCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidXAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"

    val all = listOf(androidXCore, androidXAppcompat, viewModel)

    object Versions {
        const val coreKtx = "1.2.0"
        const val appcompat = "1.3.1"
        const val viewModel = "2.3.1"
    }
}
