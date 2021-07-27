buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(deps.BuildScript.kotlinGradle)
        classpath(deps.BuildScript.kotlinSerialization)
        classpath(deps.BuildScript.androidGradle)
        classpath(deps.BuildScript.sqlDelight)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}