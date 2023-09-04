@file:Suppress("UnstableApiUsage")

pluginManagement {
    fun Settings.getEnvOrPropertyOrThrow(key: String): String =
        providers.environmentVariable(key).getOrElse(providers.gradleProperty(key).get())

    includeBuild("gradle/plugins")
    repositories {
        gradlePluginPortal()
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
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VeriFitAdmin"
include(":app")
include(":feature:login")
include(":feature:trainings")
include(":feature:clients")
include(":core:theme")
include(":core:database")
include(":component:auth")
include(":component:clients")
include(":component:trainings")
