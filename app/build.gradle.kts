import java.nio.file.Files
import java.nio.file.StandardCopyOption
import org.gradle.api.plugins.quality.Checkstyle

plugins {
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "5.1.0.4882"
    id("com.github.ben-manes.versions") version "0.53.0"
    id("maven-publish")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "hexlet.code"
            artifactId = "app"
            version = "1.0-SNAPSHOT"
        }
    }
}

tasks.register("install") {
    dependsOn("publishToMavenLocal")
}

tasks.register("prepareCliBinary") {
    dependsOn("installDist")
    doLast {
        val from = layout.projectDirectory.file("build/install/app/bin/app").asFile.toPath()
        val to = layout.projectDirectory.file("../code/app/build/install/app/bin/app").asFile.toPath()

        Files.createDirectories(to.parent)
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING)
        try {
            to.toFile().setExecutable(true)
        } catch (_: Exception) {
        }
    }
}

tasks.test {
    dependsOn("installDist")
    dependsOn("prepareCliBinary")
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

checkstyle {
    toolVersion = "10.3.4"
    configFile = file("config/checkstyle/checkstyle.xml")
}

sonarqube {
    properties {
        property("sonar.projectKey", "GaraevIM_java-project-71")
        property("sonar.organization", "garaevim")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
