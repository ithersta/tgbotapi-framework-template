plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jib)
    application
}

dependencies {
    implementation(project(":telegram"))
    implementation(project(":domain"))
}

application {
    mainClass.set("MainKt")
}
