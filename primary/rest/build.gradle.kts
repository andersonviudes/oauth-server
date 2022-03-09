import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
}

tasks.withType<BootJar> {
    isEnabled = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}