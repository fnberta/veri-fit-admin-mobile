import ch.berta.fabio.verifitadmin.TARGET_SDK
import ch.berta.fabio.verifitadmin.configureDetekt
import ch.berta.fabio.verifitadmin.configureKotlin
import ch.berta.fabio.verifitadmin.configureKotlinAndroid
import ch.berta.fabio.verifitadmin.configureSpotless
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.application")
                apply("com.diffplug.spotless")
                apply("io.gitlab.arturbosch.detekt")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = TARGET_SDK
            }
            configureKotlin(extensions.getByType())
            configureSpotless(extensions.getByType())
            configureDetekt(extensions.getByType())
        }
    }
}
