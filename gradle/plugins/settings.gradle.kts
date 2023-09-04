@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/fnberta/kotlin-build")
            credentials {
                username = getEnvOrPropertyOrThrow("GITHUB_USER")
                password = getEnvOrPropertyOrThrow("GITHUB_TOKEN")
            }
        }
    }
    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
    }
}

fun Settings.getEnvOrPropertyOrThrow(key: String): String =
    providers.environmentVariable(key).getOrElse(providers.gradleProperty(key).get())

include(":convention")
