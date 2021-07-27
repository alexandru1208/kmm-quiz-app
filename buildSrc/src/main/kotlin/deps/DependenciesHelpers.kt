package deps

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kapt(dependencies: List<String>) {
    dependencies.forEach { add("kapt", it) }
}

fun DependencyHandler.implementation(dependencies: List<String>) {
    dependencies.forEach { add("implementation", it) }
}

fun DependencyHandler.androidTestImplementation(dependencies: List<String>) {
    dependencies.forEach { add("androidTestImplementation", it) }
}

fun DependencyHandler.testImplementation(dependencies: List<String>) {
    dependencies.forEach { add("testImplementation", it) }
}
