import ch.berta.fabio.verifitadmin.configureAndroidCompose
import ch.berta.fabio.verifitadmin.libs

plugins {
    id("com.android.library")
}

android {
    configureAndroidCompose(this)
}

dependencies {
    val bom = libs.findLibrary("androidx-compose-bom").get()
    implementation(platform(bom))
    androidTestImplementation(platform(bom))
}
