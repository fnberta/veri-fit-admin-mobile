pluginManagement {
    includeBuild("gradle/plugins")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
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
include(":feature:trainings")
include(":feature:clients")
include(":data:clients")
include(":feature:login")
include(":core:auth")
include(":data:trainings")
