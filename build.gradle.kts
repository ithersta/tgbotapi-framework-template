plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

group = "com.ithersta"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}
