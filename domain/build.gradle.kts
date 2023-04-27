plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

dependencies {
    api(libs.koin.core)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

tasks.test {
    useJUnitPlatform()
}
