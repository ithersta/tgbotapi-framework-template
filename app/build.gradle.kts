plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jib)
    application
}

dependencies {
    implementation(project(":telegram"))
    implementation(project(":data"))
    runtimeOnly(libs.slf4j.simple)
}

application {
    mainClass.set("MainKt")
}

jib {
    from.image = "eclipse-temurin:20-jre"
    container.workingDirectory = "/data"
}
