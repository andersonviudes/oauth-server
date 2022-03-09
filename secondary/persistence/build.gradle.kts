import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
}

tasks.withType<BootJar> {
    isEnabled = false
}
