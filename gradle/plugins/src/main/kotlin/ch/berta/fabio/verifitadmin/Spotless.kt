package ch.berta.fabio.verifitadmin

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project

internal fun Project.configureSpotless(spotlessExtension: SpotlessExtension) {
    spotlessExtension.apply {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            ktfmt().kotlinlangStyle()
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("$buildDir/**/*.kts")
            ktfmt().kotlinlangStyle()
        }
    }
}
