// TODO: Remove once KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("verifitadmin.android-library-compose")
    alias(libs.plugins.kotlinBuild.android.library)
}

android { namespace = "ch.berta.fabio.core.theme" }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
}
