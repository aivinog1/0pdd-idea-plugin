import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

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
    jacoco
}

val junit5Version = "5.3.1"
val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
}

repositories {
    jcenter()
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination  = File("$buildDir/reports/jacoco/report.xml")
        csv.isEnabled = false
        html.isEnabled = false
    }
    executionData(File("build/jacoco/test.exec"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
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
// @todo #22:30m Need to implement job that will create coverage report for Codecov
