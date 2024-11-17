val artifactIdPrefix: String by project
val productVersion: String by project

plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    `maven-publish`
}

group = "com.github.momosetkn.$artifactIdPrefix"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            freeCompilerArgs.addAll(listOf("-Xjvm-default=all"))
        }
    }

    detekt {
        parallel = true
        autoCorrect = true
        config.from("$rootDir/config/detekt/detekt.yml")
        buildUponDefaultConfig = true
        basePath = rootDir.absolutePath
    }

    ktlint {
        version.set("1.4.1")
        filter {
            exclude { entry ->
                entry.file.toString().contains("/generated/")
            }
        }
    }
}

val libraryProjects =
    subprojects.filter {
        it.name in listOf(
            "komapper-doma-like-extensions",
        )
    }
configure(libraryProjects) {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")

    val sourcesJar by tasks.creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["java"])
                artifact(sourcesJar)
                groupId = "com.github.momosetkn.$artifactIdPrefix"
                artifactId = "$artifactIdPrefix-${project.name}"
                version = productVersion
            }
        }
        repositories {
            maven { url = uri("https://jitpack.io") }
        }
    }
}

dependencies {}
