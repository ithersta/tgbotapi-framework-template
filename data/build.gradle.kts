plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.bundles.exposed)
    implementation(libs.postgresql)
    implementation(libs.kotlin.logging)
    api(libs.koin.core)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

tasks.test {
    useJUnitPlatform()
}
