package deps

object Compose {

    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val materialIcons = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExt = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activity}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$${Versions.viewModel}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    val all = listOf(
        runtime,
        runtimeLiveData,
        ui,
        uiTooling,
        foundation,
        material,
        materialIcons,
        materialIconsExt,
        activity,
        viewModel,
        navigation
    )

    object Versions {
        const val compose = "1.1.0-rc01"
        const val activity = "1.3.1"
        const val viewModel = "1.0.0-alpha07"
        const val navigation = "2.4.0-rc01"
    }
}