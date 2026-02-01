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

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
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
    }
}