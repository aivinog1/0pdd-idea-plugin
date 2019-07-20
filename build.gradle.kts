import org.jetbrains.intellij.tasks.PatchPluginXmlTask

group = "com.aivinog1"
version = "1.0-SNAPSHOT"

buildscript {
    repositories { mavenCentral()}
    // @todo #26:30m For now, this version and Kotlin plugin version are duplicate itself's. Needs to move it in a variable.
    dependencies {classpath(kotlin("gradle-plugin", "1.2.30"))}
}

plugins {
    id("org.jetbrains.intellij") version ("0.4.9")
    kotlin("jvm") version ("1.2.30")
}

intellij {
    version= "2019.1.3"

    tasks {
        withType<PatchPluginXmlTask> {
            changeNotes("")
        }
    }
}