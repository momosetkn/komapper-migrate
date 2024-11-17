description = "komapper-doma-like-extensions"

val kotlinVersion = rootProject.properties["kotlinVersion"] as String
val komapperVersion = rootProject.properties["komapperVersion"] as String

dependencies {
    // reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

//    platform("org.komapper:komapper-platform:$komapperVersion").let {
//        implementation(it)
//        ksp(it)
//    }
    implementation("org.komapper:komapper-starter-jdbc:$komapperVersion")
    implementation("org.komapper:komapper-dialect-postgresql-jdbc:$komapperVersion")
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
