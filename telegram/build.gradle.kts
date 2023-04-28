plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlin.logging)
    implementation(libs.tgbotapi.autoconfigure)
    api(libs.tgbotapi.framework)
    ksp(libs.tgbotapi.autoconfigure.ksp)
    api(libs.koin.core)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

tasks.test {
    useJUnitPlatform()
}
