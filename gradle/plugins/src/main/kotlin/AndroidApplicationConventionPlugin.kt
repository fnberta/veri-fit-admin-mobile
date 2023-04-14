import ch.berta.fabio.verifitadmin.configureKotlinAndroid
import ch.berta.fabio.verifitadmin.configureDetekt
import ch.berta.fabio.verifitadmin.configureSpotless
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

private const val TARGET_SDK = 33

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
            configureSpotless(extensions.getByType())
            configureDetekt(extensions.getByType())
        }
    }
}
