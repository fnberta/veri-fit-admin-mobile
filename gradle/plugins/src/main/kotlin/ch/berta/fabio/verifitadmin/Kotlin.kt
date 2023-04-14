package ch.berta.fabio.verifitadmin

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

private const val JDK_VERSION = 17

internal fun Project.configureKotlin(kotlinExtension: KotlinAndroidProjectExtension) {
    kotlinExtension.apply {
        jvmToolchain(JDK_VERSION)
    }
}
