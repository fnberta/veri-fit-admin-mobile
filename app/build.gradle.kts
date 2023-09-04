// TODO: Remove once KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("verifitadmin.android-application-compose")
    id("verifitadmin.android-application-hilt")
    alias(libs.plugins.kotlinBuild.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "ch.berta.fabio.verifitadmin"

    defaultConfig {
        applicationId = "ch.berta.fabio.verifitadmin"
        versionCode = 1
        versionName = "1.0"
    }
}

tasks.named("build") { dependsOn(project(":").tasks.named("installPreCommitHook")) }

dependencies {
    implementation(project(":feature:login"))
    implementation(project(":feature:trainings"))
    implementation(project(":feature:clients"))
    implementation(project(":core:theme"))
    implementation(project(":component:auth"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.compose.ui.test)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}
