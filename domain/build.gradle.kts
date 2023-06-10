plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlin.logging)
    api(libs.arrow.core)
    api(libs.koin.core)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

tasks.test {
    useJUnitPlatform()
}
