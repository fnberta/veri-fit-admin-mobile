plugins {
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.updates)
    alias(libs.plugins.catalog.update)
}

versionCatalogUpdate {
    keep {
        keepUnusedLibraries.set(true)
        keepUnusedPlugins.set(true)
    }
}

tasks {
    dependencyUpdates {
        val immaturityLevels = listOf("rc", "cr", "m", "beta", "alpha", "preview")
            .map { ".*[.\\-]$it[.\\-\\d]*".toRegex(RegexOption.IGNORE_CASE) }

        fun getImmaturityLevel(version: String): Int =
            immaturityLevels.indexOfLast { version.matches(it) }

        rejectVersionIf { getImmaturityLevel(candidate.version) > getImmaturityLevel(currentVersion) }
    }

    register<Copy>("installPreCommitHook") {
        from(fileTree("./scripts").include("pre-commit"))
        into(".git/hooks")
    }
}