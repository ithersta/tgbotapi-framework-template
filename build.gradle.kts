import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.benmanes.versions)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

group = "com.ithersta"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}

subprojects {
    plugins.apply("io.gitlab.arturbosch.detekt")
    plugins.apply("com.diffplug.spotless")

    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            ktlint("0.49.1")
        }
    }

    detekt {
        buildUponDefaultConfig = true
        parallel = true
    }

    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xcontext-receivers"
        }
    }
}
