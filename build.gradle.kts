import org.jetbrains.intellij.tasks.PatchPluginXmlTask

group = "com.aivinog1"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        jcenter() }
    // @todo #26:30m For now, this version and Kotlin plugin version are duplicate itself's. Needs to move it in a variable.
    dependencies { classpath(kotlin("gradle-plugin", "1.2.30")) }
}

plugins {
    id("org.jetbrains.intellij") version ("0.4.9")
    kotlin("jvm") version ("1.2.30")
    id("io.gitlab.arturbosch.detekt") version("1.0.0-RC16")
}

repositories {
    jcenter()
}

intellij {
    version = "2019.1.3"

    tasks {
        withType<PatchPluginXmlTask> {
            changeNotes("")
        }
    }
}

detekt {
    toolVersion = "1.0.0-RC16"
    input = files("src/main/kotlin")
    filters = ".*/resources/.*,.*/build/.*"
}