plugins {
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlinBuild.android.application) apply false
    alias(libs.plugins.kotlinBuild.android.library) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.kotlinBuild.dependencyUpdates)
}

tasks.register<Copy>("installPreCommitHook") {
    from(fileTree("./scripts").include("pre-commit"))
    into(".git/hooks")
}
