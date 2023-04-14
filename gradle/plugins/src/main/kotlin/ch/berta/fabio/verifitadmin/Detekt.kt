package ch.berta.fabio.verifitadmin

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project

internal fun Project.configureDetekt(detektExtension: DetektExtension) {
    detektExtension.apply {
        config = files("$rootDir/detekt.yml")
    }
}
