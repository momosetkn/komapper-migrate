pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    val komapperVersion: String by settings
    repositories {
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
        id("org.komapper.gradle") version komapperVersion
    }
}

rootProject.name = "doma-kotlin-migrate"

include("komapper-doma-like-extensions")
include("komapper-doma-like-extensions-integration-test")
