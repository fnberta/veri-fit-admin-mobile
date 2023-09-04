import ch.berta.fabio.verifitadmin.libs

plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(libs.findLibrary("hilt.android").get())
    ksp(libs.findLibrary("hilt.android.compiler").get())
}
