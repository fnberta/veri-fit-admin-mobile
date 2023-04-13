plugins {
    id("verifitadmin.android.library")
    id("verifitadmin.android.hilt")
}

android {
    namespace = "ch.berta.fabio.verifitadmin.core.auth"

    kotlinOptions { jvmTarget = "1.8" }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
}
