// TODO: Remove once KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("verifitadmin.android-library-hilt")
    alias(libs.plugins.kotlinBuild.android.library)
}

android { namespace = "ch.berta.fabio.verifitadmin.core.database" }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    api(libs.firebase.firestore)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
}
