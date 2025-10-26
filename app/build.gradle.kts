plugins {
    id("java")
    id("application")
    id("com.github.ben-manes.versions") version "0.51.0" // можно 0.53.0
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("hexlet.code.App")   // для Kotlin DSL надёжнее set(...)
}

tasks {
    test {
        useJUnitPlatform()
    }
}
