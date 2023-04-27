plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jib)
    application
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":telegram"))
    implementation(project(":data"))
}

application {
    mainClass.set("MainKt")
}
