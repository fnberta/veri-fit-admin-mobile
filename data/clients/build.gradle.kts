plugins {
    id("verifitadmin.android.library")
    id("verifitadmin.android.hilt")
}

android {
    namespace = "ch.berta.fabio.verifitadmin.data.clients"

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
}