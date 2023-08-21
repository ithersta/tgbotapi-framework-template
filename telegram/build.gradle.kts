plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlin.logging)
    api(libs.tgbotapi.autoconfigure)
    implementation(libs.kotlinx.datetime)
    implementation(libs.arrow.resilience)
    ksp(libs.tgbotapi.autoconfigure.ksp)
    api(libs.koin.core)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

tasks.test {
    useJUnitPlatform()
}
