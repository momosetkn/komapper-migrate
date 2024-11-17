description = "komapper-doma-like-extensions-integration-test"

val testcontainersVersion = rootProject.properties["testcontainersVersion"] as String
val komapperVersion = rootProject.properties["komapperVersion"] as String
val kotestVersion = rootProject.properties["kotestVersion"] as String

plugins {
    id("com.google.devtools.ksp")
    id("org.komapper.gradle")
}

dependencies {
    implementation(project(":komapper-doma-like-extensions"))

    platform("org.komapper:komapper-platform:$komapperVersion").let {
        implementation(it)
        ksp(it)
    }
    ksp("org.komapper:komapper-processor")
    implementation("org.komapper:komapper-starter-jdbc:$komapperVersion")
    implementation("org.komapper:komapper-dialect-postgresql-jdbc:$komapperVersion")

    // test
    testImplementation("io.kotest:kotest-framework-engine-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("com.zaxxer:HikariCP:6.0.0")
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
//    implementation("org.postgresql:postgresql:42.7.4")
}

tasks.javadoc {
    enabled = false
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=org.komapper.annotation.KomapperExperimentalAssociation")
        }
    }
}
