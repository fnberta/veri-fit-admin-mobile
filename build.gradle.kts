import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.updates)
    alias(libs.plugins.catalog.update)
}

versionCatalogUpdate {
    keep {
        keepUnusedLibraries.set(true)
        keepUnusedPlugins.set(true)
    }
}

tasks.named("dependencyUpdates", DependencyUpdatesTask::class).configure {
    val immaturityLevels = listOf("rc", "cr", "m", "beta", "alpha", "preview")
    val immaturityRegexes = immaturityLevels.map { ".*[.\\-]$it[.\\-\\d]*".toRegex(RegexOption.IGNORE_CASE) }

    fun immaturityLevel(version: String): Int = immaturityRegexes.indexOfLast { version.matches(it) }

    rejectVersionIf { immaturityLevel(candidate.version) > immaturityLevel(currentVersion) }
}