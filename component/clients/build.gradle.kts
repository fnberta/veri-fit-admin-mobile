plugins {
    id("verifitadmin.android-library-hilt")
    alias(libs.plugins.kotlinBuild.android.library)
}

android { namespace = "ch.berta.fabio.verifitadmin.component.clients" }

dependencies {
    implementation(project(":core:database"))
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
}
